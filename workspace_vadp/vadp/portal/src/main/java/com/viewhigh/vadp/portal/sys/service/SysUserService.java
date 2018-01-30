package com.viewhigh.vadp.portal.sys.service;

import com.viewhigh.vadp.portal.sys.domain.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SysUserService {

    List<SysUser> findAll();

    Page<SysUser> findAll(String domainId,Pageable pageable);

    SysUser findByUsername(String username);
    SysUser findById(String Id);

    SysUser findByEmail(String email);

    List<SysUser> findByDomain(String domainId);

    SysUser save(SysUser user,String operatorId);

    void delete(SysUser user);

    void deleteBatch(String[] Ids);

    void joinDomain(String userId, String domainId, String createdBy);

    /**
     * 查询用户的所有菜单ID
     */
    List<String> queryAllMenuId(String userId);


}
