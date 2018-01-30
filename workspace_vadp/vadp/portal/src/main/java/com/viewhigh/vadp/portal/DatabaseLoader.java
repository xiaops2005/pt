package com.viewhigh.vadp.portal;

import com.viewhigh.vadp.portal.sys.domain.SysDomain;
import com.viewhigh.vadp.portal.sys.domain.SysUser;
import com.viewhigh.vadp.portal.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final SysUserService userService;
    private final SysDomainService domainService;

    @Autowired
    public DatabaseLoader(SysUserService userService, SysDomainService domainService) {
        this.userService = userService;
        this.domainService = domainService;
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
//
//        SysUser admin = userService.findByUsername("admin");
//        if (null == admin) {
//            SysDomain domain = new SysDomain();
//            domain.setId("402886615e03d022015e03d032980000");
//            domain.setName("default");
//            domainService.save(domain,"system");
//
//            admin = new SysUser();
//            admin.setId("402889105f0501f5015f050489ad0000");
//            admin.setUsername("admin");
//            admin.setPassword("123456");
//            admin.setSalt("admin");
//            admin.setDomainId(domain.getId());
//            Byte available = 1;
//            admin.setStatus(available);
//            userService.save(admin, "system");
//        }
    }
}
