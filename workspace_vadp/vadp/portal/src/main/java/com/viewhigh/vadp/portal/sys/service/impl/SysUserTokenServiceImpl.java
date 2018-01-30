package com.viewhigh.vadp.portal.sys.service.impl;

import com.viewhigh.vadp.common.utils.R;
import com.viewhigh.vadp.common.utils.TokenGenerator;
import com.viewhigh.vadp.portal.sys.domain.SysUserToken;
import com.viewhigh.vadp.portal.sys.persistence.SysUserTokenDao;
import com.viewhigh.vadp.portal.sys.service.SysUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service("sysUserTokenService")
public class SysUserTokenServiceImpl implements SysUserTokenService {
	@Autowired
	private SysUserTokenDao sysUserTokenDao;
	//12小时后过期
	private final static int EXPIRE = 3600 * 12;

	@Override
	public SysUserToken queryByUserId(String userId) {
		return sysUserTokenDao.findByUserId(userId);
	}

	@Override
	public void save(SysUserToken token){
		sysUserTokenDao.save(token);
	}
	
	@Override
	public void update(SysUserToken token){
		sysUserTokenDao.save(token);
	}

	@Override
	public R createToken(String userId) {
		//生成一个token
		String token =TokenGenerator.generateValue();

		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//判断是否生成过token
		SysUserToken tokenEntity = queryByUserId(userId);
		if(tokenEntity == null){
			tokenEntity = new SysUserToken();
			tokenEntity.setUserId(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);
			tokenEntity.setCreatedBy("system");
			tokenEntity.setCreatedDate(now);
			//保存token
			save(tokenEntity);
		}else{
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//更新token
			update(tokenEntity);
		}

		R r = R.ok().put("token", token).put("expire", expireTime);

		return r;
	}

	@Override
	public void logout(String userId) {
		//生成一个token
//		String token = TokenGenerator.generateValue();

		//修改token
		SysUserToken tokenEntity = queryByUserId(userId);
//		tokenEntity.setUserId(userId);
//		tokenEntity.setToken(token);
//		update(tokenEntity);
		sysUserTokenDao.removeObject(tokenEntity);
	}
}
