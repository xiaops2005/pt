package com.viewhigh.vadp.portal.sys.domain;

import com.viewhigh.vadp.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 组织用户角色关系，一个用户在不同的组织中具有不同的角色
 */
@Entity
@Table(name = "td_pt_role_menu")
public class SysRoleMenu extends BaseEntity {

	@Column(name="menu_id")
    private String menuId;
	@Column(name="role_id")
    private String roleId;

    public SysRoleMenu() {
        super();
    }



    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
