package com.viewhigh.vadp.portal.sys.service.impl;

import com.viewhigh.vadp.common.service.BaseService;
import com.viewhigh.vadp.portal.sys.domain.SysRoleMenu;
import com.viewhigh.vadp.portal.sys.domain.SysUserRole;
import com.viewhigh.vadp.portal.sys.persistence.SysRoleMenuDao;

import com.viewhigh.vadp.portal.sys.persistence.SysUserRoleDao;
import com.viewhigh.vadp.portal.sys.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserRoleServiceImpl extends BaseService implements SysUserRoleService {
    @Autowired
    private SysUserRoleDao userRoleDao;



    @Override
    public List<String> findAllByUser(String userId, String domainId) {
        List<SysUserRole> userRoles =  userRoleDao.findByUserIdAndDomainId(userId,domainId);
        List<String> roleIdList = new ArrayList<String>(userRoles.size());
        for (SysUserRole role : userRoles){
            roleIdList.add(role.getRoleId());
        }

        return  roleIdList;

    }


    @Override
    @Transactional
    public void saveOrUpdate(String domainId,String userId, List<String> roleIdList, String operatorId) {

        //先删除角色与菜单关系
        List<SysUserRole> userRoles = userRoleDao.findByUserId(userId);
        userRoleDao.deleteInBatch(userRoles);

        //保存角色与菜单关系
        userRoles = new ArrayList<SysUserRole>(roleIdList.size());
        for (String id : roleIdList) {
            SysUserRole role = new SysUserRole();
            role.setUserId(userId);
            role.setRoleId(id);
            role.setDomainId(domainId);
            role.setCreatedBy(operatorId);
            userRoles.add(role);

        }
        userRoleDao.save(userRoles);
    }

    @Override
    public void deleteBatch(String[] Ids) {

    }
}
