package com.viewhigh.dao;

import com.viewhigh.entity.ApproveRecord;
import com.viewhigh.entity.ReMateDetail;
import com.viewhigh.vadp.framework.data.base.dao.IBaseDao;

import java.util.List;
import java.util.Map;

public interface IApproveRecordDAO extends IBaseDao {
	List<ApproveRecord> findAll();
}
