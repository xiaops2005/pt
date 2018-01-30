package com.viewhigh.dao.impl;

import com.viewhigh.dao.BdChargesHosDAO;
import com.viewhigh.dao.BdDeptDao;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.entity.BdChargesHos;
import com.viewhigh.excel.domain.entity.BdDept;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 */
@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
public class BdChargesHosDAOImpl extends  APIBaseDao implements BdChargesHosDAO {

    @Override
    public List<BdChargesHos> findALl() {
        String hql = " from BdChargesHos";
        List<BdChargesHos> result = queryObjects(hql,null);

        return result;
    }

    @Override
    public void save(BdChargesHos charges) {

        if(getById(charges.getPkChargesHos()).size() ==0){
            addObject(charges);
        }
    }

    @Override
    public void save(Map<String, BdChargesHos> map) {
        Set set = map.keySet();
        for(Iterator iter = set.iterator(); iter.hasNext();)
        {
            String key = (String)iter.next();
            BdChargesHos b = map.get(key);
            save(b);
        }
    }
    public List getById(String id) {
        return this.queryObjects("from BdChargesHos where  pk_charges_hos = ?", id);

    }

    @Override
    public <T extends Base> boolean beforeInsertDictionaries(String name,T d,List<T> oldList) {
    	BdChargesHos b=(BdChargesHos) d;
        if(getById(b.getPkChargesHos()).size() ==0){
            return true;
        }else{
            return false;
        }
    }
}
