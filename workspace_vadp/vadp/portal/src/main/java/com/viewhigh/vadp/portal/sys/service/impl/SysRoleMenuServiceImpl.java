package com.viewhigh.vadp.portal.sys.service.impl;

import com.viewhigh.vadp.common.service.BaseService;
import com.viewhigh.vadp.portal.sys.domain.SysRole;
import com.viewhigh.vadp.portal.sys.domain.SysRoleMenu;
import com.viewhigh.vadp.portal.sys.persistence.SysRoleDao;
import com.viewhigh.vadp.portal.sys.persistence.SysRoleMenuDao;
import com.viewhigh.vadp.portal.sys.service.SysRoleMenuService;
import com.viewhigh.vadp.portal.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysRoleMenuServiceImpl extends BaseService implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuDao roleMenuDao;



    @Override
    public List<String> findAllByRole(String roleId) {
        List<SysRoleMenu> roleMenus =  roleMenuDao.findAllByRoleId(roleId);
        List<String> menuIdList = new ArrayList<String>(roleMenus.size());
        for (SysRoleMenu roleMenu : roleMenus){
            menuIdList.add(roleMenu.getMenuId());
        }

        return  menuIdList;

    }


    @Override
    @Transactional
    public void saveOrUpdate(String roleId, List<String> menuIdList, String operatorId) {

        //先删除角色与菜单关系
        List<SysRoleMenu> roleMenus = roleMenuDao.findAllByRoleId(roleId);
        roleMenuDao.deleteInBatch(roleMenus);

        //保存角色与菜单关系
        roleMenus = new ArrayList<SysRoleMenu>(menuIdList.size());
        for (String id : menuIdList) {
            SysRoleMenu menu = new SysRoleMenu();
            menu.setRoleId(roleId);
            menu.setMenuId(id);
            menu.setCreatedBy(operatorId);
            roleMenus.add(menu);

        }
        roleMenuDao.save(roleMenus);
    }

    @Override
    public void deleteBatch(String[] Ids) {

    }
}
