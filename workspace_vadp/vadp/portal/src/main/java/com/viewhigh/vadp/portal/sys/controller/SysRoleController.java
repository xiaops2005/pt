package com.viewhigh.vadp.portal.sys.controller;

import com.viewhigh.vadp.common.utils.R;
import com.viewhigh.vadp.portal.sys.domain.SysDomain;
import com.viewhigh.vadp.portal.sys.domain.SysRole;
import com.viewhigh.vadp.portal.sys.service.SysRoleMenuService;
import com.viewhigh.vadp.portal.sys.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/role")
public class SysRoleController extends AbstractController {
    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    /***
     * 根据组织获取所有角色列表
     * @param domainId
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    @RequiresPermissions("sys:role:list")
    public R findAll(@RequestParam(value = "domain", required = true) String domainId) {

        // 只有系统管理员才能查看全部组织
        List<SysRole> roles = sysRoleService.findAllByDomain(domainId);
        return R.ok().put("content", roles);
        // 机构管理查看

        // 用户加入的组织列表

    }

    /**
     * 创建角色
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
//    @RequiresPermissions("sys:role:create")
    public SysRole create(@RequestBody SysRole role) {
        String createdBy = this.getUserId();

        SysRole saved = sysRoleService.save(role, createdBy);
        return saved;
    }

    /**
     * 根据ID，获取角色信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = "application/json")
//    @RequiresPermissions("sys:role:read")
    public SysRole info(@PathVariable("id") String id) {
        SysRole role = sysRoleService.findById(id);
        List<String> menuIdList = sysRoleMenuService.findAllByRole(id);
        role.setMenuIds(menuIdList);
        return role;
    }

    /**
     * 修改角色信息
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = "application/json")
//    @RequiresPermissions("sys:role:update")
    public SysRole update(@RequestBody SysRole role) {
        String createdBy = this.getUserId();
        SysRole saved = sysRoleService.save(role, createdBy);
        return saved;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @RequiresPermissions("sys:role:delete")
    public R delete(@PathVariable("id") String id) {


        return R.ok();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    @RequiresPermissions("sys:role:delete")
    public R deleteBatch(@RequestBody String[] Ids) {
        logger.debug("deleteBatch" + Ids.toString());
        sysRoleService.deleteBatch(Ids);
        return R.ok();
    }
}
