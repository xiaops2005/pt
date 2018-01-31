package com.viewhigh.service.impl;


import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viewhigh.dao.IBdChargeClassDAO;
import com.viewhigh.excel.domain.entity.BdChargesClass;
import com.viewhigh.service.IBdChargeClassService;
import com.viewhigh.vadp.framework.base.service.BaseServiceImpl;
@Service("bdChargeClassServiceImpl")
@Transactional
public class BdChargeClassServiceImpl extends BaseServiceImpl implements IBdChargeClassService {

	@Autowired
	private IBdChargeClassDAO dao ;

	@Override
	public QueryResult getList(String hospital, String itemCode, String itemName, String isStop) {
		return dao.getList(hospital, itemCode, itemName, isStop);
	}

	@Override
	public String save(List<BdChargesClass> b) {
		return dao.save(b);
	}

	@Override
	public String delete(String arr) {
		String arrs[] = arr.split(",");
		String param = "";
		for(String s :arrs) {
			param  += "'"+s+"',";
		}
		// TODO Auto-generated method stub
		return dao.delete(param.substring(0,param.length()-1));
	}

	@Override
	public String startOrStop(String arr, String flag) {
		String arrs[] = arr.split(",");
		String param = "";
		for(String s :arrs) {
			param  += "'"+s+"',";
		}
		return dao.startOrStop(param.substring(0,param.length()-1), flag);
	}

	@Override
	public QueryResult getHospital() {
		// TODO Auto-generated method stub
		return dao.getHospital();
	}
	
	

}
