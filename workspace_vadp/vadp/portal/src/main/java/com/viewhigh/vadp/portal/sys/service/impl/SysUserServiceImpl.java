package com.viewhigh.vadp.portal.sys.service.impl;

import com.viewhigh.vadp.common.service.BaseService;
import com.viewhigh.vadp.portal.sys.domain.SysDomain;
import com.viewhigh.vadp.portal.sys.domain.SysMembership;
import com.viewhigh.vadp.portal.sys.domain.SysUser;
import com.viewhigh.vadp.portal.sys.domain.SysUserRole;
import com.viewhigh.vadp.portal.sys.persistence.SysDomainDao;
import com.viewhigh.vadp.portal.sys.persistence.SysMembershipDao;
import com.viewhigh.vadp.portal.sys.persistence.SysUserDao;
import com.viewhigh.vadp.portal.sys.service.SysUserRoleService;
import com.viewhigh.vadp.portal.sys.service.SysUserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysUserServiceImpl extends BaseService implements SysUserService {


    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysDomainDao sysDomainDao;

    @Autowired
    private SysMembershipDao sysMembershipDao;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public List<SysUser> findAll() {
        return sysUserDao.findAll();
    }

    @Override
    public Page<SysUser> findAll(String domainId, Pageable pageable) {
        return sysUserDao.findAllByDomainId(domainId, pageable);
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserDao.findByUsername(username);
    }

    @Override
    public SysUser findById(String Id) {
        return sysUserDao.findById(Id);
    }

    @Override
    public SysUser findByEmail(String email) {
        return sysUserDao.findByEmail(email);
    }

    @Override
    public List<SysUser> findByDomain(String domainId) {
        SysDomain domain = sysDomainDao.findById(domainId);

        if (domain == null) {
            logger.debug("未找到指定的组织");
            return new ArrayList<SysUser>();
        }

        List<SysUser> domainUsers = new ArrayList<SysUser>();
        return domainUsers;


    }

    @Override
    public SysUser save(SysUser user, String operatorId) {
        SysUser found = sysUserDao.findById(user.getId());
        SysUser result = null;
        if (null == found) {
            // 新增，设置默认角色
            user.setCreatedBy(operatorId);
            //sha256加密
            String salt =user.getUsername();
            user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
            user.setSalt(salt);
            result= sysUserDao.save(user);
        } else {
            //修改
            found.setLastModifiedBy(operatorId);
            found.setLastModifiedDate(new Date());

            // 加密处理
            //sha256加密
            String salt =user.getUsername();
            found.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());

            found.setSalt(user.getUsername());

            found.setEmail(user.getEmail());
            found.setMobile(user.getMobile());

            result= sysUserDao.save(found);
        }
        //保存角色与用户关系
        sysUserRoleService.saveOrUpdate(user.getDomainId(),user.getId(), user.getRoleIds(),operatorId);
        return result;

    }

    @Override
    public void delete(SysUser user) {
        sysUserDao.delete(user);

    }

    @Override
    public void deleteBatch(String[] Ids) {
        List<SysUser> users = new ArrayList<SysUser>();
        for (String id : Ids) {
            SysUser user = new SysUser();
            user.setId(id);
            users.add(user);
        }
        sysUserDao.deleteInBatch(users);
    }

    @Override
    public void joinDomain(String userId, String domainId, String createdBy) {
        SysMembership found = sysMembershipDao.findByDomainIdAndUserId(domainId, userId);
        if (found == null) {
            SysMembership domainUser = new SysMembership(domainId, userId, createdBy);

            sysMembershipDao.save(domainUser);
        }
    }
    @Override
    public List<String> queryAllMenuId(String userId) {
        return sysUserDao.queryAllMenuId(userId);
    }
}
