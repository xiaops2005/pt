package com.viewhigh.dao.impl;

import com.viewhigh.dao.IReAssetsDetailDao;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.entity.ReAssetsDetail;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Author zhaoxizi
 * @Date 18/1/10下午4:36
 */
@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
public class ReAssetsDetailDAOImpl extends APIBaseDao implements IReAssetsDetailDao {

    @Override
    public <T extends Base> boolean beforeInsertDictionaries(String name, T d, List<T> oldList) {
        ReAssetsDetail bdAsset=(ReAssetsDetail) d;
        List<ReAssetsDetail> ba= (List<ReAssetsDetail>) oldList;
        //查询出数据库中不存在的记录
        Optional<ReAssetsDetail> b=ba.stream().filter((t) -> (StringUtils.equalsIgnoreCase(t.getPkAssetsDetail(),bdAsset.getPkAssetsDetail()))).findFirst();
        return  !b.isPresent();
    }
}
