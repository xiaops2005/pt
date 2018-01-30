package com.viewhigh.vadp.portal.sys.service.impl;

import com.viewhigh.vadp.common.service.BaseService;
import com.viewhigh.vadp.common.utils.Constant;
import com.viewhigh.vadp.portal.sys.domain.SysDomain;
import com.viewhigh.vadp.portal.sys.domain.SysMenu;
import com.viewhigh.vadp.portal.sys.persistence.SysMenuDao;
import com.viewhigh.vadp.portal.sys.service.SysMenuService;
import com.viewhigh.vadp.portal.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class SysMenuServiceImpl extends BaseService implements SysMenuService {

    @Autowired
    private SysMenuDao menuDao;

    @Autowired
    private SysUserService sysUserService;
    @Override
    public SysMenu findById(String menuId) {
        return menuDao.findById(menuId);
    }

    @Override
    public List<SysMenu> findByDomain(String domain) {
        return menuDao.findAll();
    }

    @Override
    public List<SysMenu> findByParent(String parent) {
        return menuDao.findByParentId(parent);
    }

    @Override
    public List<SysMenu> findByUser(String userId) {
        //系统管理员，拥有最高权限
        if(Constant.SUPER_ADMIN.contains(userId)){
            return menuDao.findAll();
        }

        //用户菜单列表
        List<String> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenu> getAllMenuList(List<String> menuIdList){
        //查询根菜单列表
        List<SysMenu> menuList =menuDao.findAllByIdIn(menuIdList);


        return menuList;
    }
    @Override
    public SysMenu save(SysMenu menu, String operatorId) {
        SysMenu found = menuDao.findById(menu.getId());
        if (null == found) {
            // 新增
            menu.setCreatedBy(operatorId);
            return menuDao.save(menu);
        } else {
            // 修改

            found.setLastModifiedBy(operatorId);
            found.setLastModifiedDate(new Date());
            found.setName(menu.getName());
            found.setParentId(menu.getParentId());
            found.setUrl(menu.getUrl());
            found.setPerms(menu.getPerms());
            found.setType(menu.getType());
            found.setIcon(menu.getIcon());
            found.setOrderNum(menu.getOrderNum());


            return menuDao.save(found);
        }
    }

    @Override
    public void deleteBatch(String[] Ids) {
        List<SysMenu> menus = new ArrayList<SysMenu>();
        for (String id : Ids) {
            SysMenu menu = new SysMenu();
            menu.setId(id);
            menus.add(menu);
        }
        menuDao.deleteInBatch(menus);
    }
}
