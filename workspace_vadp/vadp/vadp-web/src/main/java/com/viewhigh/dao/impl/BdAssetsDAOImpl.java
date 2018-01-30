package com.viewhigh.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.viewhigh.dao.IBDAssetsDao;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.entity.BdAssets;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @Author zhaoxizi
 * @Date 18/1/9下午6:36
 */
@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
public class BdAssetsDAOImpl extends APIBaseDao implements IBDAssetsDao {

    public List getById(String id) {
        return this.queryObjects("from BdAssets where  pkAssets = ?", id);
    }

    @Override
    public <T extends Base> boolean beforeInsertDictionaries(String name, T d, List<T> oldList) {
        BdAssets bdAsset=(BdAssets) d;
        List<BdAssets> ba= (List<BdAssets>) oldList;
        //查询出数据库中不存在的记录
        Optional<BdAssets> b=ba.stream().filter((t) -> (StringUtils.equalsIgnoreCase(t.getPkAssets(),bdAsset.getPkAssets()))).findFirst();
        boolean flag=!b.isPresent();
        return  flag;
    }

    @Override
    public List<BdAssets> findAll() {
        String hql = " from BdAssets";
        return queryObjects(hql, null);
    }

    @Override
    public List<BdAssets> findByParam(String accYear, String pkHospital, String assetsCode, String assetsName, String capitalSourceId, String isStopId, Integer standardFlag) {
        String hql = " from BdAssets where 1=1";
        List<Object> params = Lists.newArrayList();
        if (!StrUtil.isEmpty(accYear)) {
            hql += " and accYear like ?";
            params.add("%" + accYear + "%");
        }
        if (!StrUtil.isEmpty(pkHospital)) {
            hql += " and pkHospital like ?";
            params.add("%" + pkHospital + "%");
        }
        if (!StrUtil.isEmpty(assetsCode)) {
            hql += " and assetsCode = ?";
            params.add(assetsCode);
        }
        if (!StrUtil.isEmpty(assetsName)) {
            hql += " and assetsName = ?";
            params.add(assetsName);
        }
        if (!StrUtil.isEmpty(capitalSourceId)) {
            hql += " and capitalSourceId = ?";
            params.add(capitalSourceId);
        }
        if (!StrUtil.isEmpty(isStopId)) {
            hql += " and isStop = ?";
            params.add(new Integer(isStopId));
        }
        if (standardFlag != null) {
            hql += " and isStandard = ?";
            params.add(standardFlag);
        }
        hql += " order by assetsCode asc";
        List<BdAssets>  l= queryObjects(hql, params.toArray());
        return l;
    }

    @Override
    public void deleteByIds(String pkAssets) {

        String sql = "delete from bd_Assets where pk_assets in (" + pkAssets + ")";
        this.update(sql, null);
    }

    @Override
    public void saveBdAssets(List<BdAssets> bdAssetsList) {
        Date date = new Date();
        bdAssetsList.forEach(bdAssets -> {
            if (StrUtil.isEmpty(bdAssets.getPkAssets())) {
                bdAssets.setCreationtime(date);
                bdAssets.setTs(date);
                bdAssets.setPkAssets(UUID.randomUUID().toString().replaceAll("-", ""));
                this.addObject(bdAssets);
            } else {
                bdAssets.setModifiedtime(date);
                this.updateObject(bdAssets);
            }
        });
    }
}
