package com.viewhigh.dao.impl;

import com.viewhigh.dao.BdCodeDao;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.entity.BdCode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 */
@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
public class BdCodeDAOImpl extends APIBaseDao implements BdCodeDao {


    @Override
    public List<?> getDictionariesByKey(String dictionaryName) {
        return   queryObjects("from BdCode  ");
    }

    @Override
    public <T extends Base> boolean beforeInsertDictionaries(String name, T d,List<T> oldList) {
        return false;
    }


    @Override
    public List<BdCode> findALlById(String id) {
        String hql = " from BdCode where codetype_id =  ? ";
        Object o[] = new Object[]{id};
        List<BdCode> result = queryObjects(hql,o);

        return result;
    }

    @Override
    public void save(BdCode bdCode) {

        if(getById(bdCode.getPkCode()).size() ==0){
            addObject(bdCode);
        }
    }

    @Override
    public void save(Map<String, BdCode> map) {
//        Set set = map.keySet();
//        for(Iterator iter = set.iterator(); iter.hasNext();)
//        {
//            String key = (String)iter.next();
//            BdDept b = map.get(key);
//            save(b);
//        }
    }

    @Override
    public List<BdCode> findAll() {
        String hql = "from BdCode";
        List<BdCode> result = queryObjects(hql,null);
        return result;
    }

    public List getById(String id) {
        return this.queryObjects("from BdDept where  pkDept = ?", id);

    }

}
