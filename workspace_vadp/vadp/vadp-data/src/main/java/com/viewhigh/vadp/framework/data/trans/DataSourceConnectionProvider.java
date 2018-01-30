package com.viewhigh.vadp.framework.data.trans;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.UnknownUnwrapTypeException;
import org.hibernate.service.spi.Configurable;

import com.viewhigh.vadp.framework.data.cons.DataConfConst;
/**
 * 实现自定义的数据源提共者
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
public class DataSourceConnectionProvider implements ConnectionProvider,Configurable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public void configure(Map configurationValues){
		
		String dataSourceName=(String) configurationValues.get(DataConfConst.DATASOURCE);
		System.out.println(dataSourceName);
		if(this.dataSource==null)
		{
			//定义数据源
			BasicDataSource bds=new BasicDataSource();
			bds.setDriverClassName((String) configurationValues.get(DataConfConst.DRIVER));
			bds.setUsername((String) configurationValues.get(DataConfConst.USER));
			bds.setPassword((String) configurationValues.get(DataConfConst.PASS));
			bds.setMaxActive(Integer.parseInt((String) configurationValues.get(DataConfConst.POOL_SIZE)));
			bds.setUrl((String) configurationValues.get(DataConfConst.URL));
			this.dataSource=bds;
			DataConfConst.DATASOURCE_MAP.put(dataSourceName, bds);
		}
	}
	public boolean isUnwrappableAs(Class unwrapType) {
		// TODO Auto-generated method stub
		return  ConnectionProvider.class.equals( unwrapType ) ||
				DataSourceConnectionProvider.class.isAssignableFrom( unwrapType ) ||
				DataSource.class.isAssignableFrom( unwrapType );
	}

	public <T> T unwrap(Class<T> unwrapType) {
		if ( ConnectionProvider.class.equals( unwrapType ) ||
				DataSourceConnectionProvider.class.isAssignableFrom( unwrapType ) ) {
			return (T) this;
		}
		else if ( DataSource.class.isAssignableFrom( unwrapType ) ) {
			return (T) getDataSource();
		}
		else {
			throw new UnknownUnwrapTypeException( unwrapType );
		}
	}

	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return dataSource.getConnection();
	}

	public void closeConnection(Connection conn) throws SQLException {
		conn.close();

	}

	public boolean supportsAggressiveRelease() {
		// TODO Auto-generated method stub
		return true;
	}

}
