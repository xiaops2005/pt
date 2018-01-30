package com.viewhigh.vadp.portal.sys.controller;

import com.viewhigh.vadp.common.utils.Constant;
import com.viewhigh.vadp.portal.sys.domain.SysUser;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected String getUserId() {
        return getUser().getUserId();
    }

    protected Boolean hasRole(String role) {
        return SecurityUtils.getSubject().hasRole(role);
    }

    protected Boolean isAdminstrator() {
        return hasRole(Constant.SUPER_ADMIN);
    }
}
