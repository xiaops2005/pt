package com.viewhigh.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.viewhigh.dao.BdCodeDao;
import com.viewhigh.dao.BdDeptDao;
import com.viewhigh.dao.IApproveRecordDAO;
import com.viewhigh.dao.IBdMaterialsDao;
import com.viewhigh.dao.IReMateDetailDao;
import com.viewhigh.entity.ApproveRecord;
import com.viewhigh.entity.ReMateDetail;
import com.viewhigh.excel.domain.entity.BdCode;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.domain.entity.BdMaterials;
import com.viewhigh.service.BdCodeService;
import com.viewhigh.service.BdMaterialsService;
import com.viewhigh.service.IApproveRecordService;
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
public class ApproveRecordServiceImpl extends BaseServiceImpl implements IApproveRecordService {
	@Autowired
	private IApproveRecordDAO approveRecordDao;

	@Override
	public List<ApproveRecord> findAll() {
		return approveRecordDao.findAll();
	}

}
