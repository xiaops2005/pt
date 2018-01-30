package com.viewhigh.vadp.portal.sys.service;

import com.viewhigh.vadp.portal.sys.domain.SysMenu;

import java.util.List;

public interface SysMenuService {


    SysMenu findById(String menuId);

    List<SysMenu> findByDomain(String domain);

    List<SysMenu> findByParent(String parent);

    List<SysMenu> findByUser(String userId);

    SysMenu save(SysMenu menu, String operatorId);

    void deleteBatch(String[] Ids);

}
