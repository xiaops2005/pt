package com.viewhigh.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.viewhigh.dao.IBdMedicalIdentifyDAO;
import com.viewhigh.excel.domain.entity.BdChargesClass;
import com.viewhigh.excel.domain.entity.BdMedicalIdentify;
@Repository
public class BdMedicalIdentifyDAOImpl extends BaseHibernateDAO implements IBdMedicalIdentifyDAO {


	@Override
	public QueryResult getList(String hospital, String idCode, String idName, String isStop) {
		String sql = " from BdMedicalIdentify where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		
		if (!StringUtils.isEmpty(idName)) {
			sql += " and identifyName like ?";
			params.add("%"+idName+"%");
		}
		if (!StringUtils.isEmpty(idCode)) {
			sql += "  and identifyCode=?";
			params.add(idCode);
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
	public String save(BdMedicalIdentify b) {
		try {
			if(b.getPkMedicalIdentify().equals("")) {
				if(getCode(b.getIdentifyCode()).equals("1")) {
					return "医护标识编码重复";
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				b.setCreationTime(sdf.parse(sdf.format(new Date())));
				this.addObject(b);
				return "保存成功";
			}else {
				List<BdMedicalIdentify> paramList = new ArrayList<BdMedicalIdentify>();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				b.setModifiedTime(sdf.parse(sdf.format(new Date())));
				paramList.add(b);
				this.addOrUpdateObjects(paramList);
				return "保存成功";
			}
			
		} catch (Exception e) {
			return "保存失败";
		}
	}

	@Override
	public void saveList(List<BdMedicalIdentify> list) {

	}

	public String getCode(String code) {
		try {
			String sql = " from BdMedicalIdentify where 1=1 and identifyCode = ? ";
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
			String sql = " DELETE FROM BD_MEDICAL_IDENTIFY WHERE PK_MEDICAL_IDENTIFY IN ("+arr+") ";
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
				String sql = " UPDATE BD_MEDICAL_IDENTIFY SET IS_STOP = '1' WHERE PK_MEDICAL_IDENTIFY IN ("+arr+") ";
				this.update(sql);
				return "停用成功";
			} catch (Exception e) {
			}
		}else {
			try {
				String sql = " UPDATE BD_MEDICAL_IDENTIFY SET IS_STOP = '0' WHERE PK_MEDICAL_IDENTIFY IN ("+arr+") ";
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
