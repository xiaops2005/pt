package com.viewhigh.vadp.portal.sys.controller;

import com.viewhigh.vadp.common.utils.R;
import com.viewhigh.vadp.portal.sys.domain.SysDomain;
import com.viewhigh.vadp.portal.sys.domain.SysMenu;
import com.viewhigh.vadp.portal.sys.service.ShiroService;
import com.viewhigh.vadp.portal.sys.service.SysMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/admin/menu")
public class SysMenuController extends AbstractController {
    @Autowired
    private SysMenuService menuService;

    @Autowired
    private ShiroService shiroService;

    /***
     * 根据组织获取所有菜单列表
     * @param domain
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    @RequiresPermissions("sys:menu:list")
    public R findAll(String domain) {

        List<SysMenu> menus = menuService.findByDomain(domain);
        return R.ok().put("content", menus);


    }


    /**
     * 创建组织
     *
     * @param menu
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
//    @RequiresPermissions("sys:menu:create")
    public R create(@RequestBody SysMenu menu) {
        String createdBy = this.getUserId();

        SysMenu saved = menuService.save(menu, createdBy);
        return R.ok().put("data", saved);
    }

    /**
     * 根据ID，获取菜单信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = "application/json")
//    @RequiresPermissions("sys:menu:read")
    public R info(@PathVariable("id") String id) {
        SysMenu menu = menuService.findById(id);

        return R.ok().put("data", menu);
    }

    /**
     * 修改组织信息
     *
     * @param menu
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = "application/json")
//    @RequiresPermissions("sys:menu:update")
    public R update(@RequestBody SysMenu menu) {
        String modifiedBy = getUserId();
        SysMenu saved = menuService.save(menu, modifiedBy);
        return R.ok().put("data", saved);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @RequiresPermissions("sys:menu:delete")
    public R delete(@PathVariable("id") String id) {


        return R.ok();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    @RequiresPermissions("sys:menu:delete")
    public R deleteBatch(@RequestBody String[] Ids) {
        logger.debug("deleteBatch" + Ids.toString());
        menuService.deleteBatch(Ids);
        return R.ok();
    }
}
