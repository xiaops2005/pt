package com.viewhigh.vadp.portal.sys.service.impl;

import com.viewhigh.vadp.common.service.BaseService;
import com.viewhigh.vadp.portal.sys.domain.SysRole;
import com.viewhigh.vadp.portal.sys.persistence.SysRoleDao;
import com.viewhigh.vadp.portal.sys.service.SysRoleMenuService;
import com.viewhigh.vadp.portal.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysRoleServiceImpl extends BaseService implements SysRoleService {
    @Autowired
    private SysRoleDao roleDao;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public SysRole findById(String Id) {
        return roleDao.findById(Id);
    }


    @Override
    public List<SysRole> findAllByDomain(String domainId) {
        return roleDao.findAllByDomainId(domainId);
    }


    @Override
    public SysRole save(SysRole role, String operatorId) {
        SysRole found = roleDao.findById(role.getId());
        SysRole result = null;
        if (null == found) {
            // 新增
            role.setCreatedBy(operatorId);
            Byte b = 1;
            role.setStatus(b);
            result= roleDao.save(role);
        } else {
            // 修改

            found.setLastModifiedBy(operatorId);
            found.setLastModifiedDate(new Date());
            Boolean isSystemed = found.getSystemed();
            // 非系统保留记录，可以修改名字
            if ((isSystemed == null) || (!isSystemed)){
                found.setName(role.getName());
            }

            found.setRemark(role.getRemark());
            found.setStatus(role.getStatus());
            result= roleDao.save(found);
        }
        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getId(), role.getMenuIds(),operatorId);
        return result;

    }

    @Override
    public void deleteBatch(String[] Ids) {
        List<SysRole> roles = new ArrayList<SysRole>();
        for (String id : Ids) {
            SysRole role = new SysRole();
            role.setId(id);
            roles.add(role);
        }
        roleDao.deleteInBatch(roles);
    }
}
