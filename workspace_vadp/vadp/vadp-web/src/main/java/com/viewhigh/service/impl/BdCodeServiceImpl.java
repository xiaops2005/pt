package com.viewhigh.service.impl;

import com.viewhigh.dao.BdCodeDao;
import com.viewhigh.excel.domain.entity.BdCode;
import com.viewhigh.service.BdCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BdCodeServiceImpl implements BdCodeService {
	@Autowired
	private BdCodeDao bdCodeDao;


	@Override
	public List<BdCode> findByCodeTypeId(String codeTypeId) {
		return bdCodeDao.findALlById(codeTypeId);
	}
}
