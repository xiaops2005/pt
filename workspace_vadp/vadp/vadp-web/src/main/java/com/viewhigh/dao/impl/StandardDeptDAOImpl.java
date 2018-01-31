package com.viewhigh.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;
import com.viewhigh.vadp.framework.util.UUIDGenerater;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.viewhigh.dao.IStandardDeptDAO;
import com.viewhigh.excel.domain.entity.BdDept;
@Repository
public class StandardDeptDAOImpl extends BaseHibernateDAO implements IStandardDeptDAO {

	@Override
	public QueryResult getStandardDept(String hospital,String deptCode, String deptName,String belongSystem, String isStop) {
		String sql = " from BdDept where 1=1 and isStandard = 1 ";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(hospital)) {
			sql += " and pkHospital = ?";
			params.add(""+hospital+"");
		}
		if (!StringUtils.isEmpty(deptName)) {
			sql += " and deptName like ? ";
			params.add("%"+deptName+"%");
		}
		if (!StringUtils.isEmpty(deptCode)) {
			sql += "  and deptCode=?";
		 	params.add(deptCode);
		}
		if (!StringUtils.isEmpty(isStop)) {
			sql += "  and isStop=?";
			params.add(isStop);
		}
		if (!StringUtils.isEmpty(belongSystem)) {
			sql += "  and belongSystemId=?";
			params.add(belongSystem);
		}
		sql += " order by deptCode ";
//		return this.query(sql, params.toArray());
		QueryResult qr = this.queryObjectsByPage(sql, params.toArray());
		return qr;
	}

	@Override
	public String save(List<BdDept> dept) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<BdDept> paramList = new ArrayList<BdDept>();
		for(BdDept d : dept) {
			try {
				if(StringUtils.isEmpty(d.getPkDept())) {
					d.setCreationtime(sdf.parse(sdf.format(new Date())));
					d.setPkDept(UUIDGenerater.generateId());
					this.addObject(d);
				}else {
					d.setModifiedtime(sdf.parse(sdf.format(new Date())));
					this.updateObject(d);
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
				return "保存失败";
			}
		}
		return "保存成功";
	}
	public String getCode(String code) {
		try {
			String sql = " from BdDept where 1=1 and deptCode = ? ";
			List<Object> params = new ArrayList<Object>();
			params.add(code);
			QueryResult qr = this.queryObjectsByPage(sql, params.toArray());
			
			if(qr.getResult().size()>0) {
				return "1";
			}else {
				return "0";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public String delete(String arr) {
		try {
			String sql = " DELETE FROM BD_DEPT WHERE PK_DEPT IN ("+arr+") ";
			this.update(sql);
			return "删除成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "删除失败";
		}
	}

	@Override
	public String startOrStop(String arr, String flag) {
		if(flag.equals("1")) {
			try {
				String sql = " UPDATE BD_DEPT SET IS_STOP = '1' WHERE PK_DEPT IN ("+arr+") ";
				this.update(sql);
				return "启用成功";
			} catch (Exception e) {
				e.printStackTrace();
				return "启用失败";
			}
		}else {
			try {
				String sql = " UPDATE BD_DEPT SET IS_STOP = '0' WHERE PK_DEPT IN ("+arr+") ";
				this.update(sql);
				return "停用成功";
			} catch (Exception e) {
				e.printStackTrace();
				return "停用失败";
			}
		}
		
	}
}
