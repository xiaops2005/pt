package com.viewhigh.dao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.viewhigh.dao.IApproveRecordDAO;
import com.viewhigh.dao.IBdMaterialsDao;
import com.viewhigh.dao.IReMateDetailDao;
import com.viewhigh.entity.ApproveRecord;
import com.viewhigh.entity.ReMateDetail;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.vadp.framework.util.StringUtil;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
public class ApproveRecordDAOImpl extends APIBaseDao implements IApproveRecordDAO {

	@Override
	public List<ApproveRecord> findAll() {
		String hql = "from ApproveRecord order by approvetime desc";
		List<ApproveRecord> list = queryObjects(hql);
		return list;
	}

}
