package com.viewhigh.vadp.portal.sys.service;


import java.util.List;

public interface SysUserRoleService {



    List<String> findAllByUser(String userId,String domainId);

    void saveOrUpdate(String domainId,String userId, List<String> roleIdList, String operatorId);

    void deleteBatch(String[] Ids);
}
