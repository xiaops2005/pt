package com.viewhigh.vadp.portal.sys.service;


import com.viewhigh.vadp.portal.sys.domain.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuService {



    List<String> findAllByRole(String roleId);

    void saveOrUpdate(String roleId, List<String> menuIdList, String operatorId);

    void deleteBatch(String[] Ids);
}
