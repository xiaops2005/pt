package com.viewhigh.dao.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.viewhigh.dao.IBdChargeClassDAO;
import com.viewhigh.excel.domain.entity.BdChargesClass;
@Repository
public class BdChargeClassDAOImpl extends BaseHibernateDAO implements IBdChargeClassDAO {

	@Override
	public QueryResult getList(String hospital, String itemCode, String itemName, String isStop) {
		String sql = " from BdChargesClass where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(itemName)) {
			sql += " and className like ?";
			params.add("%"+itemName+"%");
		}
		if (!StringUtils.isEmpty(itemCode)) {
			sql += "  and classCode=?";
			params.add(itemCode);
		}
		if (!StringUtils.isEmpty(hospital)) {
			sql += "  and pkHospital=?";
			params.add(hospital);
		}
		if (!StringUtils.isEmpty(isStop)) {
			sql += "  and isStop=?";
			params.add(isStop);
		}
		
//		return this.query(sql, params.toArray());
		return this.queryObjectsByPage(sql, params.toArray());
	}

	@Override
	public String save(List<BdChargesClass> lst) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<BdChargesClass> paramList = new ArrayList<BdChargesClass>();
		for(BdChargesClass d : lst) {
			try {
				if(StringUtils.isEmpty(d.getPkChargeClass())) {
					d.setCreationTime(sdf.parse(sdf.format(new Date())));
					this.addObject(d);
				}else {
					d.setModifiedTime(sdf.parse(sdf.format(new Date())));
					paramList.add(d);
				}
				
			} catch (ParseException e) {
				return "保存失败";
			}
		}
		if(paramList.size()>0) {
			this.addOrUpdateObjects(paramList);
		}
		return "保存成功";		
	}
	
	public String getCode(String code) {
		try {
			String sql = " from BdChargesClass where 1=1 and classCode = ? ";
			List<Object> params = new ArrayList<Object>();
			params.add(code);
			QueryResult qr = this.queryObjectsByPage(sql, params.toArray());
			if(qr.getResult().size()>0) {
				return "1";
			}else {
				return "0";
			}
		} catch (Exception e) {
			return "";
		}
	}


	@Override
	public String delete(String arr) {
		try {
			String sql = " DELETE FROM bd_charge_class WHERE pk_charge_class IN ("+arr+") ";
			this.update(sql);
			return "删除成功";
		} catch (Exception e) {
			return "删除失败";
		}
	}

	@Override
	public String startOrStop(String arr, String flag) {
		if(flag.equals("1")) {
			try {
				String sql = " UPDATE bd_charge_class SET IS_STOP = '1' WHERE pk_charge_class IN ("+arr+") ";
				this.update(sql);
				return "停用成功";
			} catch (Exception e) {
			}
		}else {
			try {
				String sql = " UPDATE bd_charge_class SET IS_STOP = '0' WHERE pk_charge_class IN ("+arr+") ";
				this.update(sql);
				return "启用成功";
				
			} catch (Exception e) {
			}
		}
		return null;
	}

	@Override
	public QueryResult getHospital() {
		String sql = " from BdHospital ";
		return this.queryObjectsByPage(sql, null);
	}


}
