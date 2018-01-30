package com.viewhigh.vadp.portal.sys.service.impl;

import com.viewhigh.vadp.common.service.BaseService;
import com.viewhigh.vadp.portal.sys.domain.SysDomain;
import com.viewhigh.vadp.portal.sys.domain.SysRole;
import com.viewhigh.vadp.portal.sys.domain.SysUser;
import com.viewhigh.vadp.portal.sys.persistence.SysDomainDao;
import com.viewhigh.vadp.portal.sys.persistence.SysMembershipDao;
import com.viewhigh.vadp.portal.sys.service.SysDomainService;
import com.viewhigh.vadp.portal.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysDomainServiceImpl extends BaseService implements SysDomainService {
    @Autowired
    private SysDomainDao domainDao;

    @Autowired
    private SysRoleService roleService;

    private SysMembershipDao domainUserDao;

    @Override
    public Page<SysDomain> findAll(Pageable pageable) {
        return domainDao.findAll(pageable);
    }

    @Override
    public SysDomain findById(String domainId) {
        return domainDao.findById(domainId);
    }


    @Override
    public List<SysDomain> findAllByName(String name) {
        return domainDao.findAllByName(name);
    }


    @Override
    public SysDomain save(SysDomain domain, String operatorId) {
        SysDomain found = domainDao.findById(domain.getId());
        if (null == found) {
            // 新增
            domain.setCreatedBy(operatorId);
            SysDomain saved = domainDao.save(domain);
            createDomainRole("managers", "组织管理员", saved.getId(), operatorId);
            createDomainRole("users", "员工", saved.getId(), operatorId);
            return saved;
            //自动增加组织角色
        } else {
            // 修改

            found.setLastModifiedBy(operatorId);
            found.setLastModifiedDate(new Date());
            found.setName(domain.getName());
            found.setAddress(domain.getAddress());
            found.setContact(domain.getContact());
            found.setPhone(domain.getPhone());
            found.setCity(domain.getCity());

            return domainDao.save(found);
        }

    }

    private void createDomainRole(String name, String remark, String domainId, String operatorId) {
        SysRole role = new SysRole();
        role.setName(name);
        role.setRemark(remark);
        role.setDomainId(domainId);
        role.setSystemed(true);
        List<String> menus = new ArrayList<>(0);
        role.setMenuIds(menus);
        roleService.save(role, operatorId);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        List<SysDomain> domains = new ArrayList<SysDomain>();
        for (String id : Ids) {
            SysDomain domain = new SysDomain();
            domain.setId(id);
            domains.add(domain);
        }
        domainDao.deleteInBatch(domains);

        // 删除组织对应的角色，菜单等
    }
}
