package com.viewhigh.vadp.portal.sys.service.impl;

import com.viewhigh.vadp.common.utils.Constant;
import com.viewhigh.vadp.portal.sys.domain.SysMenu;
import com.viewhigh.vadp.portal.sys.domain.SysUser;
import com.viewhigh.vadp.portal.sys.domain.SysUserToken;
import com.viewhigh.vadp.portal.sys.persistence.SysMenuDao;
import com.viewhigh.vadp.portal.sys.persistence.SysUserDao;
import com.viewhigh.vadp.portal.sys.persistence.SysUserTokenDao;
import com.viewhigh.vadp.portal.sys.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserTokenDao sysUserTokenDao;

    @Override
    public Set<String> getUserPermissions(String userId) {
        List<String> permsList;
        SysUser user = sysUserDao.findById(userId);
        //系统管理员，拥有最高权限
        if (Constant.SUPER_ADMIN.contains(user.getUsername())) {
            List<SysMenu> menuList = sysMenuDao.findAll();
            permsList = new ArrayList<String>(menuList.size());
            for (SysMenu menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = sysUserDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<String>();
        for (String perms : permsList) {
            if (StringUtils.isEmpty(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserToken queryByToken(String token) {
        return sysUserTokenDao.findByToken(token);
    }

    @Override
    public SysUser queryUser(String userId) {
        return sysUserDao.findById(userId);
    }
}
