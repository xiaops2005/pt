package com.viewhigh.excel.handler;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.viewhigh.config.aop.time.LogTime;
import com.viewhigh.config.yml.YmlValue;
import com.viewhigh.excel.domain.Base;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static com.viewhigh.excel.common.ApplicationConstants.*;

@Component
public class DataInsertHandler {

	private final static org.slf4j.Logger log = LoggerFactory
			.getLogger(DataInsertHandler.class);

	@Autowired
	private YmlValue ymlValue;

	private final int rowsPerThread = 500;


	public DataInsertHandler() {
	}

	/**
	 * @param fileTemplate    文件json模版
	 * @param list            待插入实体list
	 * @param executorService 线程池
	 * @param <T>             实体范型
	 * @return List<Integer>
	 * @throws ClassNotFoundException ClassNotFoundException
	 */
	@LogTime
	public <T extends Base> List<T> insert(FileTemplate fileTemplate, List<T> list, ExecutorService executorService) throws ClassNotFoundException, ExecutionException, InterruptedException {
		//获取数据库驱动
		getDriverClass();
		List<T> errorList = Lists.newArrayList();
		List<Future<List<T>>> futures = Lists.newArrayList();
		final int threads = (int) Math.ceil(list.size() / Double.parseDouble(String.valueOf(rowsPerThread)));

		Map<Integer, ColumnTemplate> columnTemplatesMap = fileTemplate.getColumnTemplatesMap();
		String sql = buildSql(fileTemplate, columnTemplatesMap);
		log.info("sql:{}", sql);

		for (int i = 1; i <= threads; i++) {
			final List<T> subList = list.subList((i - 1) * rowsPerThread, rowsPerThread * i >= list.size() ? list.size() : rowsPerThread * i);
			final Integer currentThread = i;
			Callable<List<T>> task = () -> {
				List<T> errorRowIndexs = Lists.newArrayList();
				Long currentRowIndex;
				Connection conn = getConnection();
				PreparedStatement prest = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				for (int x = 0, len = subList.size(); x < len; x++) {
					currentRowIndex = (long) (currentThread - 1) * rowsPerThread + x;
//							log.info("current row {}", currentRowIndex);
					//执行sql处理异常数据
					try {
						//设置sql参数
						setParameters(columnTemplatesMap, fileTemplate, subList.get(x), prest);
//						if (currentRowIndex == 0) {
////							log.info("this is test sql insert exception for row index 0");
////							throw new SQLException("this is test sql insert exception for row index 0");
////						}
						int result = prest.executeUpdate();
						if (result != 1) {
							throw new SQLException();
						}
					} catch (SQLException e) {
//						e.printStackTrace();
						T object = subList.get(x);
						setBindException(object, currentRowIndex, e.getMessage());
						errorRowIndexs.add(object);
						log.info("row {} sql insert exception catched!\ncontinue doing insert...", currentRowIndex);
					}
				}
				//提交数据
				commitAndClose(conn, errorRowIndexs, subList, currentThread);
				return errorRowIndexs;
			};
			futures.add(executorService.submit(task));
		}
		for (Future<List<T>> future : futures) {
			try {
				errorList.addAll(future.get());
			} catch (ExecutionException | InterruptedException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return errorList;
	}

	/**
	 * Object to Map
	 *
	 * @param obj obj
	 * @return Map
	 */
	private Map<?, ?> objectToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		return new org.apache.commons.beanutils.BeanMap(obj);
	}

	/**
	 * 设置错误信息
	 *
	 * @param object          entity
	 * @param currentRowIndex currentRowIndex
	 * @param message         exceptionMessage
	 * @param <T>             t
	 */
	private <T extends Base> void setBindException(T object, Long currentRowIndex, String message) {
		BindException bindException = new BindException(object, currentRowIndex.toString());
		bindException.rejectValue("row", "row.insert.error", message);
		object.setErrors(bindException);
	}

	/**
	 * 获取数据库驱动
	 *
	 * @throws ClassNotFoundException ClassNotFoundException
	 */
	private void getDriverClass() throws ClassNotFoundException {
		try {
			Class.forName(ymlValue.getDriverClassName());
		} catch (ClassNotFoundException e) {
			log.error("没有合适的驱动类");
			throw e;
		}
	}

	/**
	 * 获取数据库链接
	 *
	 * @return Connection Connection
	 */
	private Connection getConnection() throws SQLException {
		Connection conn;
		String url = ymlValue.getUrl();
		String user = ymlValue.getUsername();
		String password = ymlValue.getPassword();
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			log.error("无法打开连接");
			throw e;
		}
		return conn;
	}

	/**
	 * 提交数据及关闭数据库连接、处理插入失败数据
	 *
	 * @param conn          conn
	 * @param errorList     errorList
	 * @param subList       subList
	 * @param currentThread currentThread
	 * @param <T>           T
	 */
	private <T extends Base> void commitAndClose(Connection conn, List<T> errorList, List<T> subList, Integer currentThread) {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			threadInsertFail(subList, errorList, currentThread);
		} finally {
			try {
				conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * 线程提交失败
	 *
	 * @param subList       subList
	 * @param errorList     errorList
	 * @param currentThread currentThread
	 * @param <T>           TerrorList
	 */
	private <T extends Base> void threadInsertFail(List<T> subList, List<T> errorList, Integer currentThread) {
		Long currentRowIndex;
		List<Long> errorRowNumList = errorList.stream().map(T::getRow).collect(Collectors.toList());
		for (int x = 0, len = subList.size(); x < len; x++) {
			currentRowIndex = (long) (currentThread - 1) * rowsPerThread + x;
			if (!errorRowNumList.contains(currentRowIndex)) {
				T object = subList.get(x);
				setBindException(object, currentRowIndex, "提交到数据库失败");
				errorList.add(object);
			}
		}
	}

	/**
	 * 构建sql
	 *
	 * @param fileTemplate       fileTemplate
	 * @param columnTemplatesMap columnTemplatesMap
	 * @return StringBuilder StringBuilder
	 */
	private String buildSql(FileTemplate fileTemplate, Map<Integer, ColumnTemplate> columnTemplatesMap) {
		List<String> columnNameList = Lists.newArrayList();

		columnNameList.add(fileTemplate.getPrimaryKey());
		Iterator<Integer> iterator = columnTemplatesMap.keySet().iterator();
		while (iterator.hasNext()) {
			Integer key = iterator.next();
			if (fileTemplate.getColumnTemplateByPos(key).getIsInsert() != 0) {
				columnNameList.add(fileTemplate.getColumnTemplateByPos(key).getDbColumnName());
			} else {
				iterator.remove();

			}
		}
		String columns = Joiner.on(",").join(columnNameList);
		String columnValues = Joiner.on(",").join(columnNameList.stream().map((t) -> "?").collect(Collectors.toList()));
		return String.join(" ", "insert into", fileTemplate.getDbTableName(), "(", columns, ") values (", columnValues, ")");
	}

	/**
	 * 设置sql参数
	 *
	 * @param columnTemplatesMap columnTemplatesMap
	 * @param fileTemplate       fileTemplate
	 * @param obj                obj
	 * @param prest              prest
	 * @param <T>                T
	 * @throws SQLException SQLException
	 */
	private <T extends Base> void setParameters(Map<Integer, ColumnTemplate> columnTemplatesMap, FileTemplate fileTemplate, T obj, PreparedStatement prest) throws SQLException {
		int index = 0;
		prest.setString(1, UUID.randomUUID().toString().replaceAll("-", ""));
		for (Integer pos : columnTemplatesMap
				.keySet()) {
			ColumnTemplate column = fileTemplate.getColumnTemplateByPos(pos);
			switch (column.getType()) {
				case DATE:
					prest.setDate(index + 2, (Date) objectToMap(obj).get(column.getBeanColumnName()));
					break;
				case TIMESTAMP:
					prest.setTimestamp(index + 2, (Timestamp) objectToMap(obj).get(column.getBeanColumnName()));
					break;
				case DOUBLE:
					prest.setDouble(index + 2, (Double) objectToMap(obj).get(column.getBeanColumnName()));
					break;
				case DECIMAL:
					prest.setBigDecimal(index + 2, (BigDecimal) objectToMap(obj).get(column.getBeanColumnName()));
					break;
				case BOOLEAN:
					prest.setBoolean(index + 2, (Boolean) objectToMap(obj).get(column.getBeanColumnName()));
					break;
				case INTEGER:
					prest.setInt(index + 2, (Integer) objectToMap(obj).get(column.getBeanColumnName()));
					break;
				case LONG:
					prest.setLong(index + 2, (Long) objectToMap(obj).get(column.getBeanColumnName()));
					break;
				default:
					prest.setString(index + 2, (String) objectToMap(obj).get(column.getBeanColumnName()));
					break;
			}
			index++;
		}
	}

	public <T extends Base> List<T> insertOther(FileTemplate fileTemplate, List<T> list, ExecutorService executorService) throws ClassNotFoundException, ExecutionException, InterruptedException {
		Long startDate = System.currentTimeMillis();
		log.info("insert database start time ~~~~~~~~~~~~~~~" + startDate);
		//获取数据库驱动
		getDriverClass();
		List<T> errorList = Lists.newArrayList();
		List<Future<List<T>>> futures = Lists.newArrayList();
		final int threads = (int) Math.ceil(list.size() / Double.parseDouble(String.valueOf(rowsPerThread)));

		Map<Integer, ColumnTemplate> columnTemplatesMap = fileTemplate.getColumnTemplatesMap();
		String sql = buildSql(fileTemplate, columnTemplatesMap);
		log.info("sql:{}", sql);

		for (int i = 1; i <= threads; i++) {
			final List<T> subList = list.subList((i - 1) * rowsPerThread, rowsPerThread * i >= list.size() ? list.size() : rowsPerThread * i);
			final Integer currentThread = i;
			Callable<List<T>> task = () -> {
				List<T> errorRowIndexs = Lists.newArrayList();
				Long currentRowIndex;
				Connection conn = getConnection();
				PreparedStatement prest = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				for (int x = 0, len = subList.size(); x < len; x++) {
					currentRowIndex = (long) (currentThread - 1) * rowsPerThread + x;
//							log.info("current row {}", currentRowIndex);
					//执行sql处理异常数据
					try {
						//设置sql参数
						setParameters(columnTemplatesMap, fileTemplate, subList.get(x), prest);
//						if (currentRowIndex == 0) {
////							log.info("this is test sql insert exception for row index 0");
////							throw new SQLException("this is test sql insert exception for row index 0");
////						}
						int result = prest.executeUpdate();
						if (result != 1) {
							throw new SQLException();
						}
					} catch (SQLException e) {
//						e.printStackTrace();
						T object = subList.get(x);
						setBindException(object, currentRowIndex, e.getMessage());
						errorRowIndexs.add(object);
						log.info("row {} sql insert exception catched!\ncontinue doing insert...", currentRowIndex);
					}
				}
				//提交数据
				commitAndClose(conn, errorRowIndexs, subList, currentThread);
				return errorRowIndexs;
			};
			futures.add(executorService.submit(task));
		}
		for (Future<List<T>> future : futures) {
			try {
				errorList.addAll(future.get());
			} catch (ExecutionException | InterruptedException e) {
				e.printStackTrace();
				throw e;
			}
		}
		Long endDate = System.currentTimeMillis();
		log.info("insert database end time ~~~~~~~~~~~~~~~" + endDate);
		log.info("insert database cost time ~~~~~~~~~~~~~~~" + (endDate - startDate) + " ms");
		return errorList;
	}
}
