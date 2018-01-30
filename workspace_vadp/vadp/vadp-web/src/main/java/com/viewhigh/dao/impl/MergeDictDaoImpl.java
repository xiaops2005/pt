package com.viewhigh.dao.impl;

import com.google.common.base.Strings;
import com.viewhigh.dao.MergeDictDao;
import com.viewhigh.entity.BdDeptMerge;
import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import com.viewhigh.vo.MergeDictVO;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by litianzhu on 2018/1/9.
 */
@Transactional
@Repository
public class MergeDictDaoImpl extends BaseHibernateDAO implements MergeDictDao {

    @Override
    public BdDeptMerge findById(String pk) {
        String hql = "from MergeDict d where d.pkDeptMerge = ?";
        return (BdDeptMerge) queryObject(hql, pk);
    }

    @Override
    public List<MergeDictVO> findByParam(BdDeptMerge m) {
        String sql = "select md.pkDeptMerge as pkDeptMerge," +
                "md.pkHospital as pkHospital," +
                "md.mergeCode as mergeCode," +
                "md.mergeName as mergeName," +
                "md.pkDeptSta as pkDeptSta," +
                "md.remark as remark," +
                "md.accYear as accYear," +
                "md.creator as creator," +
                "md.creationtime as creationtime," +
                "md.modifier as modifier," +
                "md.modifiedtime as modifiedtime," +
                "md.modifier as modifier," +
                "md.dr as dr," +
                "md.ts as ts," +
                "md.isStop as isStop," +
                "h.hospitalName as hospitalName " +
                " from BdDeptMerge md, BdHospital h where md.pkHospital = h.hospitalCode";
        if (!Strings.isNullOrEmpty(m.getAccYear())) {
            sql = sql + " and md.accYear = '" + m.getAccYear() + "'";
        }
        if (!Strings.isNullOrEmpty(m.getPkHospital())) {
            sql = sql + " and md.pkHospital = '" + m.getPkHospital() + "'";
        }
        if (!Strings.isNullOrEmpty(m.getMergeCode())) {
            sql = sql + " and md.mergeCode = '" + m.getMergeCode() + "'";
        }
        if (!Strings.isNullOrEmpty(m.getMergeName())) {
            sql = sql + " and md.mergeName = '" + m.getMergeName() + "'";
        }
        if (null != m.getIsStop()) {
            sql = sql + " and md.isStop = '" + m.getIsStop() + "'";
        }
        Query q = getSession().createQuery(sql);
        q.setResultTransformer(Transformers.aliasToBean(MergeDictVO.class));

        return q.list();
    }

    @Override
    public void saveList(List<MergeDictVO> mergeDictVOs) {
        mergeDictVOs.forEach(vo -> {
            BdDeptMerge bdDeptMerge = new BdDeptMerge();
            BeanUtils.copyProperties(vo, bdDeptMerge);
            vo.setCreationtime(new Date());
            addObject(bdDeptMerge);
        });

    }

    @Override
    public void deleteByIds(String ids) {
        String sql = "delete from BD_DEPT_MERGE where PK_DEPT_MERGE in ("+ids+")";
        this.update(sql, null);
    }


}
