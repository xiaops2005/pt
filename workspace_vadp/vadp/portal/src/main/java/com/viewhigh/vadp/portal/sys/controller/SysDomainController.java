package com.viewhigh.vadp.portal.sys.controller;

import com.viewhigh.vadp.common.utils.R;
import com.viewhigh.vadp.portal.sys.domain.SysDomain;
import com.viewhigh.vadp.portal.sys.domain.SysUser;
import com.viewhigh.vadp.portal.sys.service.SysDomainService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.Date;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/admin/domain")
public class SysDomainController extends AbstractController {
    @Autowired
    private SysDomainService sysDomainService;

    /***
     * 所有组织列表
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    @RequiresPermissions(value = "sys:domain:list", logical = Logical.OR)
//    @RequiresRoles(value = "Admin", logical = Logical.OR)
    public Page<SysDomain> findAll(@PageableDefault(value = 15, sort = {"createdDate"}, direction = Sort.Direction.DESC)
                                           Pageable pageable) {
        // 只有系统管理员才能查看全部组织
        Page<SysDomain> domains = sysDomainService.findAll(pageable);
        return domains;
        // 机构管理查看

        // 用户加入的组织列表

    }

    /**
     * 创建组织
     *
     * @param domain
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
//    @RequiresPermissions("sys:domain:create")
    public SysDomain create(@RequestBody SysDomain domain) {
        String createdBy = this.getUserId();

        SysDomain saved = sysDomainService.save(domain, createdBy);
        return saved;
    }

    /**
     * 根据ID，获取组织信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = "application/json")
//    @RequiresPermissions("sys:domain:read")
    public SysDomain info(@PathVariable("id") String id) {
        SysDomain domain = sysDomainService.findById(id);

        return domain;
    }

    /**
     * 修改组织信息
     *
     * @param domain
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = "application/json")
//    @RequiresPermissions("sys:domain:update")
    public SysDomain update(@RequestBody SysDomain domain) {

        SysDomain saved = sysDomainService.save(domain, "test1");
        return saved;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @RequiresPermissions("sys:domain:delete")
    public R delete(@PathVariable("id") String id) {


        return R.ok();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    @RequiresPermissions("sys:domain:delete")
    public R deleteBatch(@RequestBody String[] Ids) {
        logger.debug("deleteBatch" + Ids.toString());
        sysDomainService.deleteBatch(Ids);
        return R.ok();
    }
}
