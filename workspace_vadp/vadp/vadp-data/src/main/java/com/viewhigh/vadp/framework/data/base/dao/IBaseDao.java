package com.viewhigh.vadp.framework.data.base.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.service.spi.ServiceException;

import com.viewhigh.vadp.framework.data.persistence.pagination.Page;
import com.viewhigh.vadp.framework.data.persistence.pagination.PageContext;
import com.viewhigh.vadp.framework.data.persistence.pagination.PageResult;
import com.viewhigh.vadp.framework.data.persistence.pagination.PageUtil;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;
/**
 * BaseDao接口
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
public interface IBaseDao {
	
	/**
	 * 添加对象
	 * 
	 * @param paramObject
	 *            实体类
	 * @return
	 * @throws ServiceException
	 */
	public Serializable addObject(Object paramObject);	
	/**
	 * 删除对象
	 * 
	 * @param paramObject
	 *            实体类
	 * @throws ServiceException
	 */
	public void removeObject(Object paramObject);
	/**
	 * 更新对象
	 * 
	 * @param paramObject
	 *            实体类
	 * @throws ServiceException
	 */
	public void updateObject(Object paramObject);
	/**
	 * HQl语句返回一条数据
	 * 
	 * @param paramString
	 * @return
	 * @throws ServiceException
	 */
	public Object queryObject(String paramString);
	/**
	 * HQl语句返回一条数据
	 * 
	 * @param paramString
	 *            HQL语句
	 * @param paramObject
	 *            参数
	 * @return
	 * @throws ServiceException
	 */
	public Object queryObject(String paramString, Object paramObject);
	/**
	 * HQl语句返回一条数据
	 * 
	 * @param paramString
	 *            HQL语句
	 * @param paramObject
	 *            参数列表
	 * @return
	 * @throws ServiceException
	 */
	public Object queryObject(String paramString, Object[] paramArrayOfObject);
	/**
	 * HQL语句 返回查询列表
	 * @param paramString
	 * @return
	 * @throws ServiceException
	 */
	public List queryObjects(String paramString);
	/**
	 * HQL语句 返回查询列表
	 * @param paramString HQL语句
	 * @param paramObject 参数
	 * @return
	 * @throws ServiceException
	 */
	public List queryObjects(String paramString, Object paramObject) ;
	/**
	 * HQL语句 返回查询列表
	 * @param paramString HQL语句
	 * @param paramArrayOfObject 参数列表
	 * @return
	 * @throws ServiceException
	 */
	public List queryObjects(String paramString, Object[] paramArrayOfObject);
	/**
	 * HQL语句 返回查询列表  -- （分页）
	 * @param paramString HQL语句
	 * @param paramArrayOfObject 参数列表
	 * @param paramInt1 FirstResult
	 * @param paramInt2 MaxResults
	 * @return
	 * @throws ServiceException
	 */
	public List queryObjects(final String paramString, final Object[] paramArrayOfObject, final int paramInt1, final int paramInt2);
	/**
	 * HQL语句 返回查询列表 分页查询
	 * @param paramString
	 * @param paramArrayOfObject
	 * @return
	 * @throws ServiceException
	 */
	public QueryResult queryObjectsByPage(String paramString, Object[] paramArrayOfObject);
	/**
	 * HQL 返回查询总数
	 * @param paramString
	 * @param paramArrayOfObject
	 * @return
	 */
	public int getTotalCount(String paramString, final Object[] paramArrayOfObject);
	/**
	 * 查询分页
	 * @param paramString
	 * @param paramPage
	 * @return
	 */
	public PageResult getPageResult(String paramString, Page paramPage);
	/**
	 * sql 语句更新
	 * @param paramString
	 * @return
	 */
	public int update(String paramString);
	/**
	 * sql语句更新
	 * @param paramString
	 * @param paramArrayOfObject
	 * @return
	 */
	public int update(final String paramString, final Object[] paramArrayOfObject);
	/**
	 * 批量更新
	 * @param paramList
	 */
	public void addOrUpdateObjects(List paramList) ;
	/**
	 * 批量更新
	 * @param paramList
	 * @param paramInt
	 */
	public void addOrUpdateObjects(List paramList, int paramInt) ;
	/**
	 * SQL语句查询分页
	 * @param paramString
	 * @param paramArrayOfObject
	 * @return
	 */
	public QueryResult query(String paramString, Object[] paramArrayOfObject);
	/**
	 * SQL语句查询分页
	 * @param paramString
	 * @param paramArrayOfObject
	 * @return
	 */
	public QueryResult query(final String paramString1, final Object[] paramArrayOfObject, final String paramString2) ;
	/**
	 * SQL 查询总数
	 * @param paramString
	 * @param paramArrayOfObject
	 * @return
	 */
	public int getRecordCount(String paramString, final Object[] paramArrayOfObject);
//	public abstract <T> T save(Object entity);
	public QueryResult queryObjectsByPage(String paramString, Object[] paramArrayOfObject, int pageSize, int pageNum);
}
