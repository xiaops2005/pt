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
import com.viewhigh.dao.IBdMaterialsDao;
import com.viewhigh.dao.IReMateDetailDao;
import com.viewhigh.entity.ReMateDetail;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.util.Strings;
import com.viewhigh.vadp.framework.util.StringUtil;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
public class ReMateDetailDAOImpl extends APIBaseDao implements IReMateDetailDao {

	@Override
	public List<ReMateDetail> findByParam(Integer year, String pkHospital, String materialsDeptCode, String materialsDeptName, String isCharges, String pkMaterials) {
//		String hql = "from ReMateDetail r left join BdMaterials m with r.pkMaterials=m.pkMaterials left join BdDept d with r.pkDept=d.pkDept where 1=1";
//		String hql = "select r.pkMateOriginal,r.pkHospital,r.accYear,r.pkDept,r.pkMaterials,r.quantity,r.pkDeptMerge,r.pkDeptCost,r.pkChargeClass,r.unitPrice,r.amount,r.isCharges,r.isInherit,r.isPublic,r.statusId,r.creator,r.creationtime,r.modifier,r.modifiedtime,r.dr,r.ts,r.approver,r.approvetime,r.approvenote from ReMateDetail r,BdMaterials m where r.pkMaterials=m.pkMaterials ";
		String hql = "from ReMateDetail r where 1=1";
		List<Object> params = Lists.newArrayList();
		if (year != null) {
			hql += " and r.accYear = ?";
			params.add(year);
		}
		if (!Strings.nullOrUndefined(pkHospital)) {
			hql += " and r.pkHospital = ?";
			params.add(pkHospital);
		}
		if (!Strings.nullOrUndefined(materialsDeptCode)) {
			hql += " and r.pkDept in(select pkDept from BdDept d where d.deptCode like ?)";
			params.add("%" + materialsDeptCode + "%");
		}
		if (!Strings.nullOrUndefined(materialsDeptName)) {
			hql += " and r.pkDept in(select pkDept from BdDept d where d.deptName like ?)";
			params.add("%" + materialsDeptName + "%");
		}
		if (!Strings.nullOrUndefined(isCharges)) {
			hql += " and r.isCharges = ?";
			params.add(isCharges);
		}
		if (!Strings.nullOrUndefined(pkMaterials)) {
			hql += " and r.pkMaterials = ?";
			params.add(pkMaterials);
		}
		hql += " order by r.creationtime desc";
		List<ReMateDetail> list = queryObjects(hql, params.toArray());
		return list;
	}

}
