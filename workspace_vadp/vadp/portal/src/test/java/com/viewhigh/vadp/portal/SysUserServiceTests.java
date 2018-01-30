package com.viewhigh.vadp.portal;

import com.viewhigh.vadp.portal.sys.domain.SysDomain;
import com.viewhigh.vadp.portal.sys.domain.SysUser;

import com.viewhigh.vadp.portal.sys.service.SysDomainService;
import com.viewhigh.vadp.portal.sys.service.SysUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SysUserServiceTests {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysDomainService domainService;

    private List<SysDomain> domains = new ArrayList<SysDomain>();

    private List<SysUser> users = new ArrayList<SysUser>();

    @Test
    @Before
    public void contextLoads() {

        List<SysDomain> found = domainService.findAllByName("组织0");
        if (found.size() == 0) {
            domains.add(createDomain("组织0"));

        }

        found = domainService.findAllByName("组织1");
        if (found.size() == 0) {
            domains.add(createDomain("组织1"));

        }

        SysUser user = userService.findByUsername("test1");
        if (user == null) {

            users.add(createUser("test1"));
        }

        user = userService.findByUsername("test2");
        if (user == null) {

            users.add(createUser("test2"));
        }
    }

    private SysDomain createDomain(String name) {
        SysDomain domain = new SysDomain();
        domain.setName(name);

        return domainService.save(domain,"test1");
    }

    private SysUser createUser(String name) {
        SysUser user = new SysUser(name, "123", "123", "tests");

        return userService.save(user,"tests");
    }

    @Test
    public void findDomain() {
        List<SysDomain> domains = domainService.findAllByName("组织1");

        Assert.assertTrue(domains.size() > 0);
    }

    @Test
    public void assignDomain() {
        SysUser user = userService.findByUsername("test1");

        SysDomain domain = domainService.findAllByName("组织1").get(0);
        userService.joinDomain(user.getUserId(), domain.getId(), "tests");
    }

    @Test
    public void findDomainByUser(){
        SysUser user = userService.findByUsername("test1");
//        List<SysDomain> domains = domainService.findAllByUser(user.getId());
//        Assert.assertTrue(domains.size() > 0);
    }
}
