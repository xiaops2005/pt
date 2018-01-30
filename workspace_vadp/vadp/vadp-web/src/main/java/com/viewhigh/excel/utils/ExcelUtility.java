package com.viewhigh.excel.utils;

import com.viewhigh.excel.domain.BaseVO;
import com.viewhigh.excel.domain.IncomeDetailBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.handler.ColumnTemplate;
import com.viewhigh.excel.handler.FileTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.viewhigh.excel.common.ApplicationConstants.*;

@Component
public class ExcelUtility {

	private final static Logger log = LoggerFactory.getLogger(ExcelUtility.class);

	public static <T extends Base> List<T> readXlFile(Sheet sheet, FileTemplate fileTemplate, Class<T> clazz,
			Validator validator) {
		Long startDate = System.currentTimeMillis();
		log.info("reading excel start time ~~~~~~~~~~~~~~~" + startDate);
		List<T> result = readXlFile(sheet, fileTemplate, clazz, true, -1, -1, validator);
		Long endDate = System.currentTimeMillis();
		log.info("reading excel end time ~~~~~~~~~~~~~~~" + endDate);
		log.info("reading excel cost time ~~~~~~~~~~~~~~~" + (endDate - startDate) + " ms");
		return result;
	}

	public static <T extends Base> List<T> readXlFile(Sheet sheet, FileTemplate fileTemplate, BaseVO vo,
			Validator validator) throws IllegalAccessException {
		return readXlFile(sheet, fileTemplate, vo, true, -1, -1, validator);
	}

	/**
	 * 读取并解析excel指定行数 sheet 工作表 fileTemplate json横版 clazz 要转换的entity类 hasHeaderRow
	 * 是否包括表头 startRow 开始行 endRow 结束行 validator 校验类
	 */
	public static <T extends Base> List<T> readXlFile(Sheet sheet, FileTemplate fileTemplate, Class<T> clazz,
			boolean hasHeaderRow, int startRow, int endRow, Validator validator) {
		List<T> objList = null;
		BindException bindException;
		T obj;
		ColumnTemplate column;

		try {
			// Sheet sheet = workbook.getSheetAt(0);
			if (startRow == -1 && endRow == -1) {
				startRow = sheet.getFirstRowNum();
				endRow = sheet.getLastRowNum();
			}
			// Iterator<Row> rowIterator = sheet.iterator();

			objList = new ArrayList<T>();

			for (int currRow = startRow; currRow <= endRow; currRow++) {

				obj = clazz.newInstance();

				Row row = (Row) sheet.getRow(currRow);
				bindException = new BindException(obj, "" + row.getRowNum());

				if (hasHeaderRow && row.getRowNum() < 1) {
					continue; // just skip the row if row number 0 if header row is true
				}

				/*
				 * Dynamically calculate positions based on Column Name and Bean Name if needed
				 * to not use static pos from conf
				 */
				// 根据json模版转换cell类型
				for (Integer pos : fileTemplate.getColumnTemplatesMap().keySet()) {
					column = fileTemplate.getColumnTemplateByPos(pos);
					switch (column.getType()) {
					case TIMESTAMP:
						try {
							BeanUtils.setProperty(obj, column.getBeanColumnName(),
									getDateCellValue(row.getCell(pos), bindException, column));
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();

						}
						break;
					case DECIMAL:
						BeanUtils.setProperty(obj, column.getBeanColumnName(),
								getDecimalCellValue(row.getCell(pos), bindException, column));
						break;
					case BOOLEAN:
						BeanUtils.setProperty(obj, column.getBeanColumnName(),
								getBooleanCellValue(row.getCell(pos), bindException, column));
						break;

					default:
						BeanUtils.setProperty(obj, column.getBeanColumnName(),
								getStringCellValue(row.getCell(pos), bindException, column));
						break;
					}
				}

				obj.setRow(row.getRowNum() + 1);

				// 执行校验类
				if (validator != null) {
					validator.validate(obj, bindException);
				}

				obj.setErrors(bindException);
				objList.add(obj);
			}
		} catch (Exception e) {
			log.error("File contain some invalid values" + e.getMessage());
			e.printStackTrace();
		}

		return objList;
	}

	private static <T extends Base> List<T> readXlFile(Sheet sheet, FileTemplate fileTemplate, BaseVO vo,
			boolean hasHeaderRow, int startRow, int endRow, Validator validator) throws IllegalAccessException {
		List<T> objList;
		BindException bindException;
		T obj;
		ColumnTemplate column;

		try {
			// Sheet sheet = workbook.getSheetAt(0);
			if (startRow == -1 && endRow == -1) {
				startRow = sheet.getFirstRowNum();
				endRow = sheet.getLastRowNum();
			}
			// Iterator<Row> rowIterator = sheet.iterator();

			objList = new ArrayList<T>();

			for (int currRow = startRow; currRow <= endRow; currRow++) {
				String className = vo.getClassz();
				Class classz = Class.forName(className).asSubclass(Base.class);
				obj = (T) classz.newInstance();

				Row row = (Row) sheet.getRow(currRow);
				bindException = new BindException(obj, "" + row.getRowNum());

				if (hasHeaderRow && row.getRowNum() < 1) {
					matchHeaderRow(fileTemplate, row, bindException);
					if (bindException.hasErrors()) {
						obj.setErrors(bindException);
						objList.add(obj);
						break;
					}
					continue; // just skip the row if row number 0 if header row is true
				}

				/*
				 * Dynamically calculate positions based on Column Name and Bean Name if needed
				 * to not use static pos from conf
				 */
				// 根据json模版转换cell类型
				for (Integer pos : fileTemplate.getColumnTemplatesMap().keySet()) {
					column = fileTemplate.getColumnTemplateByPos(pos);
					Object val = null;
					if ("PK_HOSPITAL".equalsIgnoreCase(column.getDbColumnName())) {
						// 关联医院
						val = fileTemplate.getHospital();
					} else if ("CREATOR".equalsIgnoreCase(column.getDbColumnName())) {
						// 创建人
						val = fileTemplate.getCreator();
					} else if ("CREATIONTIME".equalsIgnoreCase(column.getDbColumnName())) {
						// 创建时间
						val = fileTemplate.getCreationtime();
					} else {
						switch (column.getType()) {
						case TIMESTAMP:
							try {
								val = getDateCellValue(row.getCell(pos), bindException, column);
							} catch (Exception e) {
								e.printStackTrace();
								throw e;
							}
							break;
						case INTEGER:
							val = getIntegerCellValue(row.getCell(pos), bindException, column);
							break;
						case LONG:
						case DOUBLE:
						case DECIMAL:
							val = getDecimalCellValue(row.getCell(pos), bindException, column);
							break;
						case BOOLEAN:
							val = getBooleanCellValue(row.getCell(pos), bindException, column);
							break;
						default:
							Cell cell = row.getCell(pos);
							if (cell != null) {
								cell.setCellType(Cell.CELL_TYPE_STRING);
							}
							val = getStringCellValue(row.getCell(pos), bindException, column);
							break;
						}
					}
					BeanUtils.setProperty(obj, column.getBeanColumnName(), val);
					commonValidate(val, column, bindException);
				}
				obj.setRow(row.getRowNum() + 1);
				vo.setClazz(obj.getClass());
				vo.setObj(obj);
				// 执行校验类
				if (validator != null) {
					validator.validate(vo, bindException);
				}

				obj.setErrors(bindException);
				objList.add(obj);
			}
		} catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException
				| InstantiationException e) {
			log.error("File contain some invalid values" + e.getMessage());
			e.printStackTrace();
			throw new IllegalAccessException(e.getMessage());
		}

		return objList;
	}

	private static void matchHeaderRow(FileTemplate fileTemplate, Row row, BindException bindException) {
		// 匹配列头
		for (Integer pos : fileTemplate.getColumnTemplatesMap().keySet()) {
			ColumnTemplate column = fileTemplate.getColumnTemplateByPos(pos);
			String templateName = column.getTemplateName();
			Cell cell = row.getCell(pos);
			// 过滤
			if (templateName.equals("*")) {
				continue;
			}
			if (cell == null || cell.getStringCellValue() == null || !templateName.equals(cell.getStringCellValue())) {
				bindException.rejectValue(column.getBeanColumnName(), "headerUnmatch",
						String.join("", "列头不匹配，第", String.valueOf(pos + 1), "列列头应为", templateName, "，当前导入模版列头为",
								cell != null && StringUtils.hasText(cell.getStringCellValue())
										? cell.getStringCellValue()
										: "空"));
			}
		}
	}

	private static Date getDateCellValue(Cell cell, Errors result, ColumnTemplate columnTemplate) {
		Date parsedDate = null;
		if (cell == null) {
			return parsedDate;
		}
		try {
			parsedDate = cell.getDateCellValue();
		} catch (IllegalStateException | NumberFormatException e) {
			log.error(e.getMessage());
			result.rejectValue(columnTemplate.getBeanColumnName(), INVALID_DATE_VALUE, cell.toString());
		}
		return parsedDate;
	}

	private static String getStringCellValue(Cell cell, Errors errors, ColumnTemplate columnTemplate) {

		String rtrnVal = "";
		if (cell == null) {
			return rtrnVal;
		}
		try {

			if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				rtrnVal = "" + cell.getNumericCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				rtrnVal = cell.getCellFormula();
			} else {
				rtrnVal = cell.getStringCellValue();
			}
		} catch (Exception exception) {
			log.error(exception.getMessage());
			exception.printStackTrace();
			errors.rejectValue(columnTemplate.getBeanColumnName(), INVALID_STRING_VALUE, cell.toString());
		}
		return rtrnVal;
	}

	private static BigDecimal getDecimalCellValue(Cell cell, Errors errors, ColumnTemplate columnTemplate) {
		BigDecimal rtrnVal = new BigDecimal(0);
		if (cell == null) {
			return rtrnVal;
		}
		try {
			if (cell != null) {
				if (cell.toString().trim().length() == 0)
					return rtrnVal = new BigDecimal(0);
				else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					rtrnVal = BigDecimal.valueOf(cell.getNumericCellValue());
					return rtrnVal = rtrnVal.setScale(3, BigDecimal.ROUND_CEILING).stripTrailingZeros();
				}
			}
		} catch (Exception exception) {
			log.error(exception.getMessage());
			errors.rejectValue(columnTemplate.getBeanColumnName(), INVALID_DECIMAL_VALUE, cell.toString());
		}

		return rtrnVal;
	}

	private static Integer getIntegerCellValue(Cell cell, Errors errors, ColumnTemplate columnTemplate) {
		Integer rtrnVal = null;
		if (cell == null) {
			return null;
		}
		try {
			if (cell != null) {
				if (cell.toString().trim().length() == 0)
					return rtrnVal;
				else {
					rtrnVal = new Double(cell.getNumericCellValue()).intValue();
				}
			}
		} catch (Exception exception) {
			log.error(exception.getMessage());
			errors.rejectValue(columnTemplate.getBeanColumnName(), INVALID_INTEGER_VALUE, cell.toString());
		}

		return rtrnVal;
	}

	private static Boolean getBooleanCellValue(Cell cell, Errors errors, ColumnTemplate columnTemplate) {
		boolean rtrnVal = false;
		if (cell == null) {
			return rtrnVal;
		}
		try {
			if (cell != null) {
				if (cell.toString().trim().length() == 0)
					return rtrnVal;
				else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
					rtrnVal = cell.getBooleanCellValue();
					return rtrnVal;
				}
			}
		} catch (IllegalStateException exception) {
			log.error(exception.getMessage());
			errors.rejectValue(columnTemplate.getBeanColumnName(), INVALID_DECIMAL_VALUE, cell.toString());
		}

		return rtrnVal;
	}

	private static void commonValidate(Object val, ColumnTemplate column, Errors bindException) {
		int maxLength = column.getMaxLength();
		int minLength = column.getMinLength();
		int required = column.getRequired();
		if (required == 1 && (val == null || val.toString().length() == 0)) {
			bindException.rejectValue(column.getBeanColumnName(), ROW_REQUIRED, "不能为空");
		}
		if (maxLength > 0 && val != null && val.toString().length() > maxLength) {
			bindException.rejectValue(column.getBeanColumnName(), ROW_MAX_LENGTH, "超出最大长度" + maxLength);
		}
		if (minLength > 0 && val != null && val.toString().length() < minLength) {
			bindException.rejectValue(column.getBeanColumnName(), ROW_MIN_LENGTH, "低于最小长度" + minLength);
		}
	}

	public static <T extends Base> Map<String, List<T>> readXlFileIncome(Sheet sheet, FileTemplate fileTemplate, BaseVO vo,
			Validator validator) throws IllegalAccessException {
		Long startDate = System.currentTimeMillis();
		log.info("reading excel start time ~~~~~~~~~~~~~~~" + startDate);
		Map<String, List<T>> result = readXlFileIncome(sheet, fileTemplate, vo, true, -1, -1, validator);
		Long endDate = System.currentTimeMillis();
		log.info("reading excel end time ~~~~~~~~~~~~~~~" + endDate);
		log.info("reading excel cost time ~~~~~~~~~~~~~~~" + (endDate - startDate) + " ms");
		return result;
	}

	private static <T extends Base> Map<String, List<T>> readXlFileIncome(Sheet sheet, FileTemplate fileTemplate, BaseVO vo,
			boolean hasHeaderRow, int startRow, int endRow, Validator validator) throws IllegalAccessException {
		List<T> objList;
		Map<String, List<T>> map = new HashMap<String, List<T>>();
		List<T> objList2;
		BindException bindException;
		T obj;
		ColumnTemplate column;

		try {
			// Sheet sheet = workbook.getSheetAt(0);
			if (startRow == -1 && endRow == -1) {
				startRow = sheet.getFirstRowNum();
				endRow = sheet.getLastRowNum();
			}
			// Iterator<Row> rowIterator = sheet.iterator();

			objList = new ArrayList<T>();
			objList2 = new ArrayList<T>();

			for (int currRow = startRow; currRow <= endRow; currRow++) {
				String className = vo.getClassz();
				Class classz = Class.forName(className).asSubclass(Base.class);
				obj = (T) classz.newInstance();

				Row row = (Row) sheet.getRow(currRow);
				bindException = new BindException(obj, "" + row.getRowNum());

				if (hasHeaderRow && row.getRowNum() < 1) {
					matchHeaderRow(fileTemplate, row, bindException);
					if (bindException.hasErrors()) {
						obj.setErrors(bindException);
						objList.add(obj);
						break;
					}
					continue; // just skip the row if row number 0 if header row is true
				}

				/*
				 * Dynamically calculate positions based on Column Name and Bean Name if needed
				 * to not use static pos from conf
				 */
				// 根据json模版转换cell类型
				for (Integer pos : fileTemplate.getColumnTemplatesMap().keySet()) {
					column = fileTemplate.getColumnTemplateByPos(pos);
					Object val = null;
					if (column.getDbColumnName().equalsIgnoreCase("PK_HOSPITAL")) {
						// 关联医院
						val = fileTemplate.getHospital();
					} else if (column.getDbColumnName().equalsIgnoreCase("CREATOR")) {
						// 创建人
						val = fileTemplate.getCreator();
					} else if (column.getDbColumnName().equalsIgnoreCase("CREATIONTIME")) {
						// 创建时间
						val = fileTemplate.getCreationtime();
					} else {
						switch (column.getType()) {
						case TIMESTAMP:
							try {
								val = getDateCellValue(row.getCell(pos), bindException, column);
							} catch (Exception e) {
								e.printStackTrace();
								throw e;
							}
							break;
						case INTEGER:
							val = getIntegerCellValue(row.getCell(pos), bindException, column);
							break;
						case LONG:
						case DOUBLE:
						case DECIMAL:
							val = getDecimalCellValue(row.getCell(pos), bindException, column);
							break;
						case BOOLEAN:
							val = getBooleanCellValue(row.getCell(pos), bindException, column);
							break;
						default:
							Cell cell = row.getCell(pos);
							if (cell != null) {
								cell.setCellType(Cell.CELL_TYPE_STRING);
							}
							val = getStringCellValue(row.getCell(pos), bindException, column);
							break;
						}
					}
					BeanUtils.setProperty(obj, column.getBeanColumnName(), val);
					commonValidate(val, column, bindException);
				}
				obj.setRow(row.getRowNum() + 1);
				vo.setClazz(obj.getClass());
				vo.setObj(obj);
				// 执行校验类
				if (validator != null) {
					validator.validate(vo, bindException);
				}
				IncomeDetailBean idb = (IncomeDetailBean)obj;
				obj.setErrors(bindException);
//				objList.add(obj);
				if(idb.getChargesType().contains("药")||idb.getChargesType().contains("材料")||idb.getChargesType().contains("材")||idb.getChargesType().contains("煎药")) {
					objList2.add(obj);
				}else {
					objList.add(obj);
				}
			}
			map.put("re_income_detail", objList);
			map.put("re_income_other", objList2);
		} catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException
				| InstantiationException e) {
			log.error("File contain some invalid values" + e.getMessage());
			e.printStackTrace();
			throw new IllegalAccessException(e.getMessage());
		}

		return map;
//		return objList;
	}

}
