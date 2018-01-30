package com.viewhigh.dao.impl;

import com.viewhigh.dao.ReWageDao;
import com.viewhigh.entity.ReWage;
import com.viewhigh.vadp.framework.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReWageDaoImpl extends APIBaseDao implements ReWageDao {

    private static Logger log = LoggerFactory.getLogger(ReWageDaoImpl.class);

    public List<ReWage> getReWage(String accYar, String pkHospital, String pkDept) {
        String hql = "select m from ReWage m where 1=1";
        List<Object> paramList = new ArrayList<>();
        if (StringUtils.isNotEmpty(accYar)) {
            hql += " and m.accYar=?";
            paramList.add(accYar);
        }
        if (StringUtils.isNotEmpty(pkHospital)) {
            hql += " and m.pkHospital=?";
            paramList.add(pkHospital);
        }
        if (StringUtils.isNotEmpty(pkDept)) {
            hql += " and m.pkDept=?";
            paramList.add(pkDept);
        }
        log.info("查询人员经费数据：" + hql);
        List reWages = queryObjects(hql, paramList.toArray());

        return reWages;
    }
}
