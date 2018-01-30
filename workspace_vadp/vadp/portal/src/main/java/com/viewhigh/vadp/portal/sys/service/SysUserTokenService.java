package com.viewhigh.vadp.portal.sys.service;

import com.viewhigh.vadp.common.utils.R;
import com.viewhigh.vadp.portal.sys.domain.SysUserToken;

/**
 * 用户Token
 *
 */
public interface SysUserTokenService {

	SysUserToken queryByUserId(String userId);

	void save(SysUserToken token);
	
	void update(SysUserToken token);

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(String userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(String userId);

}
