package com.viewhigh.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.viewhigh.dao.BdCodeDao;
import com.viewhigh.dao.BdDeptDao;
import com.viewhigh.dao.IBdMaterialsDao;
import com.viewhigh.dao.IReMateDetailDao;
import com.viewhigh.entity.ReMateDetail;
import com.viewhigh.excel.domain.entity.BdCode;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.domain.entity.BdMaterials;
import com.viewhigh.service.BdCodeService;
import com.viewhigh.service.BdMaterialsService;
import com.viewhigh.service.ReMateDetailService;
import com.viewhigh.util.ExcelUtil;
import com.viewhigh.vadp.framework.base.service.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class ReMateDetailServiceImpl extends BaseServiceImpl implements ReMateDetailService {
	@Autowired
	private IReMateDetailDao reMateDetailDao;
	
	@Autowired
	private BdDeptDao bdDeptDao;
	
	@Autowired
	private IBdMaterialsDao bdMaterialsDao;

	@Override
	public List<ReMateDetail> findByParam(Integer year, String pkHospital, String materialsDeptCode, String materialsDeptName, String isCharges, String pkMaterials) {
		return reMateDetailDao.findByParam(year,pkHospital,materialsDeptCode,materialsDeptName,isCharges,pkMaterials);
	}
	
	@Override
	public String exportReMateDetail(Integer year, String pkHospital, String materialsDeptCode, String materialsDeptName,
			String isCharges, String pkMaterials) {
		List<ReMateDetail> reMateDetailList = this.findByParam(year, pkHospital, materialsDeptCode, materialsDeptName, isCharges, pkMaterials);
		List<BdDept> deptList = bdDeptDao.findALl();
		Map<String,BdDept> bdDeptMap = Maps.uniqueIndex(deptList, new Function<BdDept,String>() {  
	          public String apply(BdDept from) {  
	            return from.getPkDept(); 
	    }});
		List<BdMaterials> materialsList = bdMaterialsDao.findAll();
		Map<String,BdMaterials> materialsMap = Maps.uniqueIndex(materialsList, new Function<BdMaterials,String>() {  
	          public String apply(BdMaterials from) {  
	            return from.getPkMaterials();
	    }});
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i < reMateDetailList.size();i++){
			ReMateDetail reMateDetail = reMateDetailList.get(i);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("accYear", reMateDetail.getAccYear());
			jsonObject.put("deptCode", bdDeptMap.get(reMateDetail.getPkDept()).getDeptCode());
			jsonObject.put("deptName", bdDeptMap.get(reMateDetail.getPkDept()).getDeptName());
			jsonObject.put("materialsCode", materialsMap.get(reMateDetail.getPkMaterials()).getMaterialsCode());
			jsonObject.put("materialsName", materialsMap.get(reMateDetail.getPkMaterials()).getMaterialsName());
			jsonObject.put("unitName", materialsMap.get(reMateDetail.getPkMaterials()).getUnitName());
			jsonObject.put("model", materialsMap.get(reMateDetail.getPkMaterials()).getModel());
			jsonObject.put("quantity", reMateDetail.getQuantity());
			jsonObject.put("unitPrice", reMateDetail.getUnitPrice());
			jsonObject.put("amount", reMateDetail.getAmount());
			jsonObject.put("isCharges", reMateDetail.getIsCharges());
			jsonObject.put("pkChargeClass", reMateDetail.getPkChargeClass());
			jsonObject.put("statusId", reMateDetail.getStatusId());
			jsonObject.put("creator", reMateDetail.getCreator());
			jsonObject.put("creationtime", reMateDetail.getCreationtime());
			jsonObject.put("modifier", reMateDetail.getModifier());
			jsonObject.put("modifiedtime", reMateDetail.getModifiedtime());
			jsonObject.put("approver", reMateDetail.getApprover());
			jsonObject.put("approvetime", reMateDetail.getApprovetime());
			jsonObject.put("approvenote", reMateDetail.getApprovenote());
			jsonArray.add(jsonObject);
		}
		Map<String, String> headMap = new LinkedHashMap<String, String>();
		headMap.put("accYear", "年度");
		headMap.put("deptCode", "材料科室编码");
		headMap.put("deptName", "材料科室名称");
		headMap.put("materialsCode", "材料编码");
		headMap.put("materialsName", "材料名称");
		headMap.put("unitName", "计量单位");
		headMap.put("model", "规则型号");
		headMap.put("quantity", "数量");
		headMap.put("unitPrice", "单价");
		headMap.put("amount", "金额");
		headMap.put("isCharges", "是否单收费");
		headMap.put("pkChargeClass", "项目大类");
		headMap.put("statusId", "状态");
		headMap.put("creator", "创建人");
		headMap.put("creationtime", "创建时间");
		headMap.put("modifier", "修改人");
		headMap.put("modifiedtime", "修改时间");
		headMap.put("approver", "审核人");
		headMap.put("approvetime", "审核时间");
		headMap.put("approvenote","审核评语");
		String fileName = "材料明细";
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		ExcelUtil.downloadExcelFile(fileName,headMap,jsonArray,response);
		return null;
	}


}
