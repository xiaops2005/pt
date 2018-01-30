package com.viewhigh.service;

import com.viewhigh.excel.domain.entity.BdCode;

import java.util.List;

public interface BdCodeService {
	public List<BdCode> findByCodeTypeId(String codeTypeId);
}
