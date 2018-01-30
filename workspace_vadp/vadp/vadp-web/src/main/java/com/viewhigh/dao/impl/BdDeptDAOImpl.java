package com.viewhigh.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.viewhigh.dao.BdDeptDao;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.entity.BdDept;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by litianzhu on 2017/12/28.
 */
@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public class BdDeptDAOImpl extends APIBaseDao implements BdDeptDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(BdDeptDAOImpl.class);
	@Override
	public List<BdDept> findALl() {
		String hql = " from BdDept";
		List<BdDept> result = queryObjects(hql, null);

		return result;
	}

	@Override
	public void save(BdDept bdDept) {

		if (getById(bdDept.getPkDept()).size() == 0) {
			addObject(bdDept);
		}
	}

	@Override
	public void save(Map<String, BdDept> map) {
		Set set = map.keySet();
		for (Iterator iter = set.iterator(); iter.hasNext(); ) {
			String key = (String) iter.next();
			BdDept b = map.get(key);
			save(b);
		}
	}

	public List getById(String id) {
		return this.queryObjects("from BdDept where  pkDept = ?", id);
	}

	@Override
	public <T extends Base> boolean beforeInsertDictionaries(String name, T d, List<T> oldList) {
		BdDept bdDept = (BdDept) d;
		List<BdDept> ba = (List<BdDept>) oldList;
		//查询出数据库中不存在的记录
		Optional<BdDept> b = ba.stream().filter((t) -> (StringUtils.equalsIgnoreCase(t.getPkDept(), bdDept.getPkDept()))).findFirst();
		return !b.isPresent();
	}


	@Override
	public List<BdDept> findByParam(Integer year, String pkHospital, String deptCode, String deptName, String belongSystem, Integer isStop) {
		String hql = " from BdDept where 1=1";
		List<Object> params = Lists.newArrayList();
		if (year != null) {
			hql += " and year=?";
			params.add(year);
		}
		if (!StrUtil.isEmpty(pkHospital)) {
			hql += " and pkHospital=?";
			params.add(pkHospital);
		}
		if (!StrUtil.isEmpty(deptCode)) {
			hql += " and deptCode like ?";
			params.add("%" + deptCode + "%");
		}
		if (!StrUtil.isEmpty(deptName)) {
			hql += " and deptName like ?";
			params.add("%" + deptName + "%");
		}
		if (!StrUtil.isEmpty(belongSystem)) {
			hql += " and belongSystem=?";
			params.add(belongSystem);
		}
		if (isStop != null) {
			hql += " and isStop=?";
			params.add(isStop);
		}
		hql += " order by deptCode asc";
		return queryObjects(hql, params.toArray());
	}

	@Override
	public void deleteByIds(String pkDepts) {
		String sql = "delete from Bd_Dept where pk_dept in (" + pkDepts + ")";
		this.update(sql, null);
	}

	@Override
	public void saveBdDept(List<BdDept> bdDeptList) {
		Date date = new Date();
		bdDeptList.forEach(bdDept -> {
			if (StrUtil.isEmpty(bdDept.getPkDept())) {
				bdDept.setCreationtime(date);
				bdDept.setTs(date);
				bdDept.setPkDept(UUID.randomUUID().toString().replaceAll("-", ""));
				this.addObject(bdDept);
			} else {
				bdDept.setModifiedtime(date);
				this.updateObject(bdDept);
			}
		});
	}
}
