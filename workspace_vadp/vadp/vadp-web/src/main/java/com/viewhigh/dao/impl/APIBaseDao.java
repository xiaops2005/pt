package com.viewhigh.dao.impl;

import com.viewhigh.dao.IAPIBaseDao;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.util.Strings;
import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import com.viewhigh.vadp.framework.util.SpringContextUtil;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author zhaoxizi
 * @Date 18/1/2下午3:46
 */
@Service("apiBaseDao")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
public class APIBaseDao extends BaseHibernateDAO implements IAPIBaseDao {


   private IAPIBaseDao base;

    @Override
    public List<?>  getDictionariesByKey(String dictionaryName) {
      return   queryObjects("from "+Strings.toUpperCaseFirstOne(dictionaryName));
    }
    @Override
    public List<?>  getDictionariesByKey(String dictionaryName,Map<String,List<String>> searchCondition) {

            StringBuffer hql=new StringBuffer("from "+Strings.toUpperCaseFirstOne(dictionaryName) + " where 1=1");
            //参数名称
            List<String> paramNames = searchCondition.get("paramNames");
            //参数值
            List<String> defaultValue = searchCondition.get("defaultValue");
            //符号 =? ,like ?
            List<String> operater = searchCondition.get("operater");
            //连接条件 and ,or
            List<String> orand = searchCondition.get("orand");
            //参数类型
            List<String> dataType = searchCondition.get("dataType");

            Object[] paramArrayOfObject=null;
            if(paramNames!=null) {
            paramArrayOfObject=new Object[paramNames.size()];
                for (int i = 0; i < paramNames.size(); i++) {
                    hql.append(" " + orand.get(i) + " " + paramNames.get(i) + " " + operater.get(i) + " ");
                    if ("like".equalsIgnoreCase(operater.get(i).trim())) {
                        paramArrayOfObject[i] = "%" + defaultValue.get(i) + "%";
                    } else {
                        paramArrayOfObject[i] = Strings.parseObject(dataType.get(i), defaultValue.get(i));
                    }
                    hql.append(" ? ");
                }
            }
            return   queryObjects(hql.toString(),paramArrayOfObject);
    }

    @Override
    public <T extends Base> String saveDictionary(T d) {
        return (String) addObject(d);
    }
    @Override
    public Map<String, List<?>> getDictionariesByClass(List<String> dictionaryName) throws ClassNotFoundException {
        Map<String, List<?>> m= new HashMap<>();
        if(dictionaryName!=null) {
            for (String dn : dictionaryName) {
                String beanName = dn + "DAOImpl";
                try {
                    base = (IAPIBaseDao) SpringContextUtil.getBean(Strings.toLowerCaseFirstOne(beanName));
                } catch (Exception e) {
                }
                List<?> l = base.getDictionariesByKey(dn);
                m.put(dn, l);
            }
        }
        return m;
    }

    @Override
    public Map<String, List<?>> getDictionariesByClass(Map<String,Map<String,List<String>>> dictionaries) throws ClassNotFoundException {
        Map<String, List<?>> m = new HashMap<>();
        if (dictionaries != null) {
            for (String dn : dictionaries.keySet()) {
                Map<String,List<String>> searchCondition=dictionaries.get(dn);
                    String beanName = dn + "DAOImpl";
                    try {
                        base = (IAPIBaseDao) SpringContextUtil.getBean(Strings.toLowerCaseFirstOne(beanName));
                    } catch (Exception e) {
                    }
                    List<?> l = base.getDictionariesByKey(dn,searchCondition);
                    m.put(dn, l);
                }
            }
            return m;
        }

        @Override
    public <T extends Base> void saveDictionaries(Map<String, List<?>> newDictionaries,Map<String, List<?>> OldDictionaries) {
        for (String key : newDictionaries.keySet()) {
            String beanName=key+"DAOImpl";
            List<T> list=(List<T>)newDictionaries.get(key);
            List<T> oldList=(List<T>)OldDictionaries.get(key);
            try{
                base= (IAPIBaseDao) SpringContextUtil.getBean(Strings.toLowerCaseFirstOne(beanName));
                    for (T d:list){
                        if( base.beforeInsertDictionaries(key,d,oldList)){
                            base.saveDictionary(d);
                        }
                    }
            }catch (NoSuchBeanDefinitionException e){
                for (T d : list) {
                    if (beforeInsertDictionaries(key, d,oldList)) {
                        saveDictionary(d);
                    }
                }
            }

        }

    }
    @Override
    public <T extends Base> boolean beforeInsertDictionaries(String name,T d,List<T> oldList) {
        return true;
    }

    public <T extends Base>  List<T> getOldDictionaries(){return null; }


}
