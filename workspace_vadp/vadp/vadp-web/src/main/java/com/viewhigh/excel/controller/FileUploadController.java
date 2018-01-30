package com.viewhigh.excel.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.viewhigh.dao.BdChargesHosDAO;
import com.viewhigh.dao.BdCodeDao;
import com.viewhigh.dao.BdDeptDao;
import com.viewhigh.dao.IBdMaterialsDao;
import com.viewhigh.dao.IAPIBaseDao;
import com.viewhigh.dao.impl.APIBaseDao;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.BaseVO;
import com.viewhigh.excel.domain.ExcelFile;
import com.viewhigh.excel.domain.JsonResult;
import com.viewhigh.excel.domain.ResultStatus;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.domain.entity.BdMaterials;
import com.viewhigh.excel.handler.DataInsertHandler;
import com.viewhigh.excel.handler.FileTemplate;
import com.viewhigh.excel.handler.FileUploadTemplateHandler;
import com.viewhigh.excel.utils.ExcelUtility;
import com.viewhigh.excel.utils.ExcelUtilityParallelProcessor;
import com.viewhigh.vadp.framework.util.SpringContextUtil;


@Controller
public class FileUploadController {

	@Autowired
	ExcelUtilityParallelProcessor excelUtilityParallelProcessor;
	@Autowired
	DataInsertHandler dataInsertHandler;

	@Autowired
	private BdDeptDao bdDeptDAO;
	@Autowired
	private BdCodeDao bdCodeDao;
	@Autowired
	private BdChargesHosDAO bdChargesHosDAO ;
	@Autowired
	private IBdMaterialsDao bdMaterialsDAO;
	@Autowired
	private APIBaseDao apiBaseDao;

	Map<String, List<?>>  dictionaries;


	private final static Logger log = LoggerFactory
			.getLogger(FileUploadController.class);


	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public @ResponseBody
	String provideUploadInfo() {
		return "You can upload a file by posting to this same URL.";
	}


	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody
	JsonResult getJson(@RequestParam("jsonTemplate") String jsonTemplate,
					   @RequestParam("hospital") String hospital,
					   @RequestParam("creator") String creator,
					   @RequestParam("file") MultipartFile file) {
		try {
			return convertXl(file, jsonTemplate, hospital, creator);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(ResultStatus.SERVER_EXCEPTION.getValue(), e.getMessage());
		}
	}

	private <T extends Base> JsonResult convertXl(MultipartFile file, String jsonTemplate, String hospital, String creator) {
		if (!file.isEmpty()) {
			ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 16);
			try {
				Workbook workbook;
				if (file.getOriginalFilename().endsWith("xls")) {
					workbook = new HSSFWorkbook(file.getInputStream());
				} else if (file.getOriginalFilename().endsWith("xlsx")) {
					workbook = new XSSFWorkbook(file.getInputStream());
				} else {
					return new JsonResult(ResultStatus.UNSUPPORT_FILE.getValue(), "只支持导入xls,xlsx文件！");
				}
				FileTemplate fileTemplate = new FileUploadTemplateHandler(jsonTemplate).getFileTemplate();
				fileTemplate.setExtension(hospital, creator);
				Validator validator = null;
				try {
					validator = (Validator) SpringContextUtil.getBean(fileTemplate.getValidator());
				} catch (NoSuchBeanDefinitionException e) {
					log.info("NoSuchBeanDefined,validator unused!");
				}
				ExcelFile excelFile = new ExcelFile(workbook.getSheetAt(0), true, 2000,
						fileTemplate, Class.forName(fileTemplate.getTemplateName()).asSubclass(Base.class),
						validator
				);

				BaseVO baseVO = getBaseVO(fileTemplate.getTemplateName(), excelFile.getFileTemplate());

				//single thread read
				List<T> list = ExcelUtility.readXlFile(excelFile.getSheet(), excelFile.getFileTemplate(), baseVO, excelFile.getValidator());
				//Multi thread read
//				list = excelUtilityParallelProcessor.readExcelInParallel(excelFile);
				List<T> validateErrorList = list.stream().filter((t) -> (t.getErrors().hasErrors())).collect(Collectors.toList());
				if (validateErrorList.size() == 0) {
					List<T> insertErrorList = dataInsertHandler.insert(excelFile.getFileTemplate(), list, executorService);
					log.info("total:{},success:{},error:{}", list.size(), list.size() - insertErrorList.size(), insertErrorList.size());
					saveBaseCodeDict(baseVO);
					if (insertErrorList.size() > 0) {
						return new JsonResult(ResultStatus.PART_OK.getValue(), "没有完全导入成功", insertErrorList);
					}
				} else {
					return new JsonResult(ResultStatus.VALIDATE_FAIL.getValue(), "验证不通过", validateErrorList);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return new JsonResult(ResultStatus.PARAM_UNMATCH.getValue(), e.getMessage());
			} catch (ClassNotFoundException | ExecutionException e) {
				e.printStackTrace();
				return new JsonResult(ResultStatus.CONNECTION_ERROR.getValue(), e.getMessage());
			} catch (IOException | InterruptedException | NumberFormatException | InstantiationException e) {
				e.printStackTrace();
				return new JsonResult(ResultStatus.SERVER_EXCEPTION.getValue(), e.getMessage());
			} finally {
				try {
					executorService.shutdown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			return new JsonResult(ResultStatus.PARAM_UNMATCH.getValue(), "文件为空");
		}
		return new JsonResult(ResultStatus.OK.getValue(), "完全导入成功！");
	}

	private BaseVO getBaseVO(String clazz, FileTemplate file) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
		List<BdDept> depts = bdDeptDAO.findALl();
		Map<String, BdDept> bdDeptMaps = new HashMap<>();
		for (BdDept b : depts) {
			bdDeptMaps.put(b.getDeptCode(), b);
		}

		List<BdMaterials> bdMaterials = bdMaterialsDAO.findAll();
		Map<String, BdMaterials> bdMaterialsMaps = new HashMap<>();
		for (BdMaterials b : bdMaterials) {
			bdMaterialsMaps.put(b.getMaterialsCode(), b);
		}

		BaseVO baseVO = new BaseVO();
		baseVO.setClassz(clazz);
		baseVO.setBdDeptMap(bdDeptMaps);
		baseVO.setBdMaterialsMap(bdMaterialsMaps);
		//将旧字典表中的数据保存用于比对
        baseVO.setDictionaries(apiBaseDao.getDictionariesByClass(file.getDictionaries()));
        dictionaries=apiBaseDao.getDictionariesByClass(file.getDictionaries());
		return baseVO;
	}
	
	
	
	@RequestMapping(value = "/uploadIncome", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody
	JsonResult getJsonIncome(@RequestParam("jsonTemplate") String jsonTemplate,
			   @RequestParam("hospital") String hospital,
			   @RequestParam("creator") String creator,
			   @RequestParam("file") MultipartFile file) {
		try {
			return convertXlIncome(file, jsonTemplate, hospital, creator);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(ResultStatus.SERVER_EXCEPTION.getValue(), e.getMessage());
		}
	}

	private <T extends Base> JsonResult convertXlIncome(MultipartFile file, String jsonTemplate, String hospital, String creator) {
		if (!file.isEmpty()) {
			ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 16);
			try {
				Workbook workbook;
				if (file.getOriginalFilename().endsWith("xls")) {
					workbook = new HSSFWorkbook(file.getInputStream());
				} else if (file.getOriginalFilename().endsWith("xlsx")) {
					workbook = new XSSFWorkbook(file.getInputStream());
				} else {
					return new JsonResult(ResultStatus.UNSUPPORT_FILE.getValue(), "只支持导入xls,xlsx文件！");
				}
				FileTemplate fileTemplate = new FileUploadTemplateHandler(jsonTemplate).getFileTemplate();
				FileTemplate fileTemplate2 = new FileUploadTemplateHandler("properties/IncomeOtherBeanTemplate.json").getFileTemplate();
				fileTemplate.setExtension(hospital, creator);
				Validator validator = null;
				try {
					validator = (Validator) SpringContextUtil.getBean(fileTemplate.getValidator());
				} catch (NoSuchBeanDefinitionException e) {
					log.info("NoSuchBeanDefined,validator unused!");
				}
				ExcelFile excelFile = new ExcelFile(workbook.getSheetAt(0), true, 2000,
						fileTemplate, Class.forName(fileTemplate.getTemplateName()).asSubclass(Base.class),
						validator
				);

				BaseVO baseVO = getBaseVOIncome(fileTemplate.getTemplateName(), excelFile.getFileTemplate());

				//single thread read
				Map<String, List<T>> map = ExcelUtility.readXlFileIncome(excelFile.getSheet(), excelFile.getFileTemplate(), baseVO, excelFile.getValidator());
				List<T> list = map.get("re_income_detail");
				List<T> listOther = map.get("re_income_other");
				//Multi thread read
//				list = excelUtilityParallelProcessor.readExcelInParallel(excelFile);
				List<T> validateErrorList = list.stream().filter((t) -> (t.getErrors().hasErrors())).collect(Collectors.toList());
				List<T> validateErrorListOther = listOther.stream().filter((t) -> (t.getErrors().hasErrors())).collect(Collectors.toList());
				if (validateErrorList.size() == 0&&validateErrorListOther.size() == 0) {
					List<T> insertErrorList = dataInsertHandler.insert(excelFile.getFileTemplate(), list, executorService);
					List<T> insertErrorList2 = dataInsertHandler.insertOther(fileTemplate2, listOther, executorService);
					saveBaseCodeDictIncome(baseVO);
					if (insertErrorList.size() > 0&&insertErrorList2.size()>0) {
						return new JsonResult(ResultStatus.PART_OK.getValue(), "没有完全导入成功", insertErrorList);
					}
				} else {
					return new JsonResult(ResultStatus.VALIDATE_FAIL.getValue(), "验证不通过", validateErrorList);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return new JsonResult(ResultStatus.PARAM_UNMATCH.getValue(), e.getMessage());
			} catch (ClassNotFoundException | ExecutionException e) {
				e.printStackTrace();
				return new JsonResult(ResultStatus.CONNECTION_ERROR.getValue(), e.getMessage());
			} catch (IOException | InterruptedException | NumberFormatException e) {
				e.printStackTrace();
				return new JsonResult(ResultStatus.SERVER_EXCEPTION.getValue(), e.getMessage());
			} finally {
				try {
					executorService.shutdown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			return new JsonResult(ResultStatus.PARAM_UNMATCH.getValue(), "文件为空");
		}
		return new JsonResult(ResultStatus.OK.getValue(), "完全导入成功！");
	}

	private BaseVO getBaseVOIncome(String clazz, FileTemplate file)  {
		BaseVO baseVO = new BaseVO();
		baseVO.setClassz(clazz);
		try {
            dictionaries=apiBaseDao.getDictionariesByClass(file.getDictionaries());
			baseVO.setDictionaries(dictionaries);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baseVO;
	}

	private void saveBaseCodeDict(BaseVO baseVO) {

	    apiBaseDao.saveDictionaries(baseVO.getDictionaries(),dictionaries);
	}
	private void saveBaseCodeDictIncome(BaseVO baseVO) {
		apiBaseDao.saveDictionaries(baseVO.getDictionaries(),dictionaries);
	}

}
