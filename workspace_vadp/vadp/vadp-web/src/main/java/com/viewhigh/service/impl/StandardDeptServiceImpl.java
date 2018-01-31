package com.viewhigh.service.impl;


import java.util.List;

import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viewhigh.dao.IStandardDeptDAO;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.service.IStandardDeptService;
import com.viewhigh.vadp.framework.base.service.BaseServiceImpl;
@Service("standardDeptServiceImpl")
@Transactional
public class StandardDeptServiceImpl extends BaseServiceImpl implements IStandardDeptService {

	@Autowired
	private IStandardDeptDAO dao ;
	
	@Override
	public QueryResult getStandardDept(String hospital,String deptCode, String deptName,String belongSystem, String isStop) {
		return dao.getStandardDept(hospital,deptCode, deptName,belongSystem, isStop);
	}

	@Override
	public String save(List<BdDept> dept) {
		return dao.save(dept);
	}

	@Override
	public String delete(String arr) {
		return dao.delete(arr);
	}

	@Override
	public String startOrStop(String arr, String flag) {
		return dao.startOrStop(arr, flag);
	}

}
