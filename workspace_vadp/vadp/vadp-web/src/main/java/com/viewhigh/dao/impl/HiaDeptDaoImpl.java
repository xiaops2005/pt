package com.viewhigh.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.viewhigh.dao.HiaDeptDao;
import com.viewhigh.entity.BdDeptHia;
import com.viewhigh.entity.ReWage;
import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public class HiaDeptDaoImpl extends BaseHibernateDAO implements HiaDeptDao {

    private static Logger log = LoggerFactory.getLogger(HiaDeptDaoImpl.class);

    @Override
    public List<BdDeptHia> queryBdDeptHia(String hiaCode, String isStop) {
        String hql = "select m from BdDeptHia m where 1=1";
        List<Object> paramList = new ArrayList<>();
        if (StringUtils.isNotEmpty(hiaCode)) {
            hql += " and m.hiaCode=?";
            paramList.add(hiaCode);
        }
        if (StringUtils.isNotEmpty(isStop)) {
            hql += " and m.isStop=?";
            paramList.add(isStop);
        }
        log.info("查询hia科室数据：" + hql);
        List reWages = queryObjects(hql, paramList.toArray());

        return reWages;
    }

    @Override
    public void saveHiaDept(List<BdDeptHia> bdDeptHiaList) {
        Date date = new Date();
        bdDeptHiaList.forEach(hiaDept -> {
            if (StrUtil.isEmpty(hiaDept.getPkDeptHia())) {
                hiaDept.setCreationtime(date);
                hiaDept.setTs(date);
                hiaDept.setPkDeptHia(UUID.randomUUID().toString().replaceAll("-", ""));
                this.addObject(hiaDept);
                log.info("hia科室保存操作完成!");
            } else {
                hiaDept.setModifiedtime(date);
                this.updateObject(hiaDept);
                log.info("hia科室更新操作完成!");
            }
        });
    }

    @Override
    public void deleteByIds(String pkDeptHia) {
        String sql = "delete from Bd_Dept_Hia where pk_dept_hia in (" + pkDeptHia + ")";
        log.info("删除语句：" + sql);
        this.update(sql, null);
    }


}
