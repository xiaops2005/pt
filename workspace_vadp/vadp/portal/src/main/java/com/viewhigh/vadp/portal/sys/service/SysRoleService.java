package com.viewhigh.vadp.portal.sys.service;

import com.viewhigh.vadp.portal.sys.domain.SysRole;

import java.util.List;

public interface SysRoleService {

    SysRole findById(String Id);

    List<SysRole> findAllByDomain(String domainId);

    SysRole save(SysRole role, String operatorId);

    void deleteBatch(String[] Ids);
}
