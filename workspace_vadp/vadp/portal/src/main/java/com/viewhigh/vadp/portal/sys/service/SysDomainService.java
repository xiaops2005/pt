package com.viewhigh.vadp.portal.sys.service;

import com.viewhigh.vadp.portal.sys.domain.SysDomain;
import com.viewhigh.vadp.portal.sys.domain.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface SysDomainService {

    Page<SysDomain> findAll(Pageable pageable);

    SysDomain findById(String domainId);

    List<SysDomain> findAllByName(String name);

    SysDomain save(SysDomain domain, String operatorId);

    void deleteBatch(String[] Ids);
}
