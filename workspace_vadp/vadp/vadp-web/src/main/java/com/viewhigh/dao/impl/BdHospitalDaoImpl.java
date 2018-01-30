package com.viewhigh.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.viewhigh.dao.BdHospitalDao;
import com.viewhigh.entity.BdHospital;
import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by litianzhu on 2018/1/10.
 */
@Transactional
@Repository
public class BdHospitalDaoImpl extends BaseHibernateDAO implements BdHospitalDao {
	@Override
	public List<BdHospital> findAll() {
		String hql = " from BdHospital";
		return queryObjects(hql, null);
	}

	@Override
	public List<BdHospital> findByParam(String hospitalCode, String hospitalName, String hospitalTypeId, String hospitalClassId) {
		String hql = " from BdHospital where 1=1";
		List<String> params = Lists.newArrayList();
		if (!StrUtil.isEmpty(hospitalCode)) {
			hql += " and hospitalCode like ?";
			params.add("%" + hospitalCode + "%");
		}
		if (!StrUtil.isEmpty(hospitalName)) {
			hql += " and hospitalName like ?";
			params.add("%" + hospitalName + "%");
		}
		if (!StrUtil.isEmpty(hospitalTypeId)) {
			hql += " and hospitalTypeId = ?";
			params.add(hospitalTypeId);
		}
		if (!StrUtil.isEmpty(hospitalClassId)) {
			hql += " and hospitalClassId = ?";
			params.add(hospitalClassId);
		}
		hql += " order by hospitalCode asc";
		return queryObjects(hql, params.toArray());
	}

	@Override
	public void deleteByIds(String pkHospitals) {
		String sql = "delete from Bd_Hospital where pk_hospital in (" + pkHospitals + ")";
		this.update(sql, null);
	}

	@Override
	public void saveBdHospital(List<BdHospital> bdHospitalList) {
		Date date = new Date();
		bdHospitalList.forEach(bdHospital -> {
			if (StrUtil.isEmpty(bdHospital.getPkHospital())) {
				bdHospital.setCreationtime(date);
				bdHospital.setTs(date);
				bdHospital.setPkHospital(UUID.randomUUID().toString().replaceAll("-", ""));
				this.addObject(bdHospital);
			} else {
				bdHospital.setModifiedtime(date);
				this.updateObject(bdHospital);
			}
		});
	}

}
