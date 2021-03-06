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
import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.domain.entity.BdMaterials;
import com.viewhigh.vadp.framework.util.StringUtil;

/**
 * Created by litianzhu on 2017/12/28.
 */
@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
public class BdMaterialsDAOImpl extends APIBaseDao implements IBdMaterialsDao {

    @Override
    public List<BdMaterials> findAll() {
        String hql = " from BdMaterials";
        List<BdMaterials> result = queryObjects(hql,null);

        return result;
    }

    @Override
    public void save(BdMaterials bdMaterials) {

        if(getById(bdMaterials.getPkMaterials()).size() ==0){
            addObject(bdMaterials);
        }
    }

    @Override
    public void save(Map<String, BdMaterials> map) {
        Set set = map.keySet();
        for(Iterator iter = set.iterator(); iter.hasNext();)
        {
            String key = (String)iter.next();
            BdMaterials b = map.get(key);
            save(b);
        }
    }
    public List getById(String id) {
        return this.queryObjects("from BdMaterials where  pkMaterials = ?", id);
    }
    
    @Override
    public <T extends Base> boolean beforeInsertDictionaries(String name,T d,List<T> oldList) {
        BdMaterials bdMaterials=(BdMaterials) d;
        if(getById(bdMaterials.getPkMaterials()).size() ==0){
            return true;
        }else{
            return false;
        }
    }

	@Override
	public List<BdMaterials> findByParam(Integer year, String pkHospital, String materialsCode, String materialsName, String isSingle, String isStop) {
		String hql = " from BdMaterials where 1=1";
		List<Object> params = Lists.newArrayList();
		if (year != null) {
			hql += " and accYear = ?";
			params.add(year);
		}
		if (!StringUtil.isEmpty(pkHospital)) {
			hql += " and pkHospital = ?";
			params.add(pkHospital);
		}
		if (!StringUtil.isEmpty(materialsCode)) {
			hql += " and materialsCode like ?";
			params.add("%" + materialsCode + "%");
		}
		if (!StringUtil.isEmpty(materialsName)) {
			hql += " and materialsName like ?";
			params.add("%" + materialsName + "%");
		}
		if (!StringUtil.isEmpty(isSingle)) {
			hql += " and isSingle = ?";
			params.add(isSingle);
		}
		if (!StringUtil.isEmpty(isStop)) {
			hql += " and isStop = ?";
			params.add(isStop);
		}
		hql += " order by creationtime asc";
		return queryObjects(hql, params.toArray());
	}

	@Override
	public void deleteByIds(String pkMaterials) {
		String sql = "delete from Bd_Materials where pk_materials in (" + pkMaterials + ")";
		this.update(sql, null);
	}

	@Override
	public void saveBdMaterials(List<BdMaterials> bdMaterialsList) {
		Date date = new Date();
		bdMaterialsList.forEach(bdMaterials -> {
			if (StrUtil.isEmpty(bdMaterials.getPkMaterials())) {
				bdMaterials.setCreationtime(date);
				bdMaterials.setTs(date);
				bdMaterials.setPkHospital(UUID.randomUUID().toString().replaceAll("-", ""));
				this.addObject(bdMaterials);
			} else {
				bdMaterials.setModifiedtime(date);
				this.updateObject(bdMaterials);
			}
		});
	}

}
