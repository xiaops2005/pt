package com.viewhigh.vadp.portal.sys.controller;

import com.viewhigh.vadp.common.exception.AppException;
import com.viewhigh.vadp.common.utils.R;
import com.viewhigh.vadp.portal.sys.domain.SysDomain;
import com.viewhigh.vadp.portal.sys.domain.SysUser;
import com.viewhigh.vadp.portal.sys.domain.SysUserRole;
import com.viewhigh.vadp.portal.sys.service.SysUserRoleService;
import com.viewhigh.vadp.portal.sys.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/admin/user")
public class SysUserController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @RequestMapping("/list")
//    @RequiresPermissions("sys:user:list")
    public Page<SysUser> getUsers(@RequestParam(value = "domain", required = true) String domainId, @PageableDefault(value = 15, sort = {"createdDate"}, direction = Sort.Direction.DESC)
            Pageable pageable) {

        Page<SysUser> users = sysUserService.findAll(domainId, pageable);
        return users;
    }


    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
//    @RequiresPermissions("sys:user:create")
    public SysUser create(@RequestBody SysUser user) {
        String createdBy = this.getUserId();
        if (sysUserService.findByUsername(user.getUsername()) != null) {
            //用户名已存在
            throw new AppException("用户名已存在");
        }
        if(user.getRoleIds() == null) user.setRoleIds(new ArrayList<String>());
        SysUser saved = sysUserService.save(user, createdBy);
        return saved;
    }

    /**
     * 根据ID，获取用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = "application/json")

    public SysUser info(@PathVariable("id") String id) {
        SysUser user = sysUserService.findById(id);
        String domainId = user.getDomainId();
        List<String> roleIdList = sysUserRoleService.findAllByUser(id, domainId);
        user.setRoleIds(roleIdList);
        return user;
    }

    /**
     * 修改组织信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = "application/json")
//    @RequiresPermissions("sys:user:update")
    public SysUser update(@RequestBody SysUser user) {
        String modifiedBy = this.getUserId();
        SysUser saved = sysUserService.save(user, modifiedBy);
        return saved;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @RequiresPermissions("sys:user:delete")
    public R delete(@PathVariable("id") String id) {


        return R.ok();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    @RequiresPermissions("sys:user:delete")
    public R deleteBatch(@RequestBody String[] Ids) {
        logger.debug("deleteBatch" + Ids.toString());
        sysUserService.deleteBatch(Ids);
        return R.ok();
    }
}
