package com.viewhigh.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.viewhigh.dao.IReCostDao;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.domain.entity.ReCost;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
public class ReCostDaoImpl extends APIBaseDao implements IReCostDao {

    @Override
    public String saveDept(BdDept dept) {
        return (String)this.addObject(dept);
    }

    @Override
    public  List<ReCost> findALl() {
        return null;
    }

    @Override
    public QueryResult findByParam(String accYear, String pkHospital, String deptCode, String deptName) {
        String hql = " select c.pk_Cost,c.pk_Hospital,c.acc_Year,c.acc_Month,c.pk_Dept,c.dept_Level,c.wage_Amount" +
                ",c.materials_Amount,c.drug_Amount,c.assets_Amount,c.intangible_Amount,c.risk_Funds_Amount" +
                ",c.other_Amount,c.dept_Amount_Sum,c.pk_Dept_Merge,c.status_Id,c.creator,c.creationtime,c.modifier" +
                ",c.modifiedtime,c.approver,c.approvetime,c.approvenote,c.dr,c.ts ,d.dept_code,d.dept_name from Re_Cost c left join bd_dept d on d.pk_dept= c.pk_dept where 1=1";
        List<String> params = Lists.newArrayList();
        if (!StrUtil.isEmpty(accYear)) {
            hql += " and c.acc_year like ?";
            params.add("%" + accYear + "%");
        }
        if (!StrUtil.isEmpty(pkHospital)) {
            hql += " and c.pk_Hospital like ?";
            params.add("%" + pkHospital + "%");
        }
        if (!StrUtil.isEmpty(deptCode)) {
            hql += " and d.dept_Code = ?";
            params.add(deptCode);
        }
        if (!StrUtil.isEmpty(deptName)) {
            hql += " and d.dept_Name = ?";
            params.add(deptName);
        }

//        hql += " order by assetsCode asc";
        QueryResult r= query(hql,params.toArray());
        return r;
    }

    @Override
    public void deleteByIds(String pkCost) {
        String sql = "delete from re_cost where pk_cost in (" + pkCost + ")";
        this.update(sql, null);
    }

    @Override
    public void saveReCost(List<ReCost> reCostList) {
        reCostList=(List<ReCost>)reCostList;
        Date date = new Date();
        reCostList.forEach(reCost1 -> {
            ReCost reCost= (ReCost) reCost1;
            if (StrUtil.isEmpty(reCost.getPkCost())) {
                reCost.setCreationtime(date);
                reCost.setTs(date);
                reCost.setPkCost(UUID.randomUUID().toString().replaceAll("-", ""));
                this.addObject(reCost);
            } else {
                this.updateObject(reCost);
            }
        });
    }


}
