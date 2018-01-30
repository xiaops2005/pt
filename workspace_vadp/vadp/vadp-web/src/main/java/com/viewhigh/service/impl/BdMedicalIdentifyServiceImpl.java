package com.viewhigh.service.impl;


import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viewhigh.dao.IBdChargeClassDAO;
import com.viewhigh.dao.IBdMedicalIdentifyDAO;
import com.viewhigh.excel.domain.entity.BdChargesClass;
import com.viewhigh.excel.domain.entity.BdMedicalIdentify;
import com.viewhigh.service.IBdChargeClassService;
import com.viewhigh.service.IBdMedicalIdentifyService;
import com.viewhigh.vadp.framework.base.service.BaseServiceImpl;

import java.util.List;

@Service("bdMedicalIdentifyServiceImpl")
@Transactional
public class BdMedicalIdentifyServiceImpl extends BaseServiceImpl implements IBdMedicalIdentifyService {

	@Autowired
	private IBdMedicalIdentifyDAO dao ;

	@Override
	public QueryResult getList(String hospital, String idCode, String idName, String isStop) {
		return dao.getList(hospital, idCode, idName, isStop);
	}

	@Override
	public String save(BdMedicalIdentify b) {
		return dao.save(b);
	}

	@Override
	public void saveList(List<BdMedicalIdentify> list) {
		list.forEach(item ->{
			save(item);
		});
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
