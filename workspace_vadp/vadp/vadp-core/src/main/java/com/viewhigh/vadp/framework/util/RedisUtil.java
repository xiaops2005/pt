package com.viewhigh.vadp.framework.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 
 * Redis帮助类
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年5月31日
 * 修改日期: 2017年5月31日
 */
@SuppressWarnings("unchecked")
public class RedisUtil {

	private static RedisTemplate redisTemplate = null;

	public static RedisTemplate getRedisTemplate() {
		if (redisTemplate == null) {
			redisTemplate = (RedisTemplate) SpringContextUtil.getBean("redisTemplate");
		}
		return redisTemplate;
	}

	public static void hmset(final byte[] key, final Map<byte[], byte[]> hash) {
		getRedisTemplate().execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.hMSet(key, hash);
				return null;
			}
		});
	}

	public static void expire(final byte[] key, final long maxInactiveInterval) {
		getRedisTemplate().execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.expire(key, maxInactiveInterval);
			}
		});
	}

	public static void hset(final byte[] key, final byte[] field, final byte[] value) {
		getRedisTemplate().execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.hSet(key, field, value);
				return null;
			}
		});
	}

	public static byte[] hget(final byte[] key, final byte[] field) {
		return (byte[]) getRedisTemplate().execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] value = connection.hGet(key, field);
				return value;
			}
		});
	}

	public static void hdel(final byte[] key, final byte[] field) {
		getRedisTemplate().execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.hDel(key, field);
			}
		});
	}

	public static void del(final byte[]... keys) {
		getRedisTemplate().execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.del(keys);
			}
		});
	}

	public static List<byte[]> hmget(final byte[] key, final byte[]... fields) {
		return (List<byte[]>) getRedisTemplate().execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.hMGet(key, fields);
			}
		});
	}

	public static Set<byte[]> hkeys(final byte[] key) {
		return (Set<byte[]>) getRedisTemplate().execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.hKeys(key);
			}
		});
	}

	public static boolean setNX(final byte[] key, final byte[] value) {
		return (boolean) getRedisTemplate().execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.setNX(key, value);
			}
		});
	}

	public static byte[] get(final byte[] key) {
		return (byte[]) getRedisTemplate().execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] value = connection.get(key);
				return value;
			}
		});
	}

	public static byte[] getSet(final byte[] key, final byte[] oldvalue) {
		return (byte[]) getRedisTemplate().execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] value = connection.getSet(key, oldvalue);
				return value;
			}
		});
	}
}
