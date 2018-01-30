package com.viewhigh.vadp.portal.sys.service;

import com.viewhigh.vadp.portal.sys.domain.SysUser;
import com.viewhigh.vadp.portal.sys.domain.SysUserToken;


import java.util.Set;

/**
 * shiro相关接口
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(String userId);

    SysUserToken queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId
     */
    SysUser queryUser(String userId);
}
