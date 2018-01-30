package com.viewhigh.vadp.framework.data.persistence.pagination;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.SessionFactory;

//import com.viewhigh.vadp.framework.ds.DataCenter;
//import com.viewhigh.vadp.framework.util.RequestContextUtil;
import sun.misc.BASE64Encoder;

@SuppressWarnings("all")
public class PageUtil {

	public static QueryResult getQueryResult() {
		PageInfo pi=PageInfoThreadConstant.getPageInfo();
//		HttpServletRequest request = RequestContextUtil.getRequest();
		if (pi == null) {
			return null;
		}
//		DataCenter localObject = (DataCenter) request.getAttribute("queryResult");
		Integer _pageNumber = NumberUtils.toInt(pi.getPageNum(), 1);
		Integer _pageSize = NumberUtils.toInt(pi.getPageSize(), 100);
		Boolean autoCalc = (Boolean) ConvertUtils.convert(pi.getCalc(), Boolean.class);
		QueryResult queryResult = new QueryResult();
		queryResult.setPageSize(_pageSize);
		queryResult.setPageNumber(_pageNumber);
		queryResult.setAutoCalcCount(autoCalc);
		return queryResult;

	}


	private static String getClassNameExceptBaseHibernateDAO(String paramString, StackTraceElement[] paramArrayOfStackTraceElement, int paramInt) {
		if (paramString.equals("com.viewhigh.vadp.framework.base.dao.BaseHibernateDAO")) {
			paramInt++;
			paramString = paramArrayOfStackTraceElement[paramInt].getClassName();
			return getClassNameExceptBaseHibernateDAO(paramString, paramArrayOfStackTraceElement, paramInt);
		}
		return paramString;
	}

	private static String getMethodNameExceptBaseHibernateDAO(String paramString, StackTraceElement[] paramArrayOfStackTraceElement, int paramInt) {
		if (paramString.equals("com.viewhigh.vadp.framework.base.dao.BaseHibernateDAO")) {
			paramInt++;
			paramString = paramArrayOfStackTraceElement[paramInt].getClassName();
			return getMethodNameExceptBaseHibernateDAO(paramString, paramArrayOfStackTraceElement, paramInt);
		}
		return paramArrayOfStackTraceElement[paramInt].getMethodName();
	}
	
	private static String encrypt(String paramString1, String paramString2) throws NoSuchAlgorithmException {
		MessageDigest localMessageDigest = MessageDigest.getInstance(paramString2);
		localMessageDigest.reset();
		byte[] arrayOfByte1 = paramString1.getBytes();
		byte[] arrayOfByte2 = localMessageDigest.digest(arrayOfByte1);
		BASE64Encoder localBASE64Encoder = new BASE64Encoder();
		return localBASE64Encoder.encode(arrayOfByte2);
	}
	

	public static PageContext initPageContext(Class paramClass, String paramString1, String paramString2, Object[] paramArrayOfObject, String paramString3, String paramString4, SessionFactory paramSessionFactory) {
		PageContext localPageContext = new PageContext();

//		if (!paramClass.getName().equals("com.neusoft.unieap.techcomp.ria.common.query.dao.impl.PageQueryDAOImpl")) {
//			
//			StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
//
//			String str2 = getClassNameExceptBaseHibernateDAO(arrayOfStackTraceElement[3].getClassName(), arrayOfStackTraceElement, 3);
//
//			String str3 = getMethodNameExceptBaseHibernateDAO(arrayOfStackTraceElement[3].getClassName(), arrayOfStackTraceElement, 3);
//
//
//			StringBuilder localStringBuilder = new StringBuilder();
//			localStringBuilder.append(str2).append(str3).append(paramString2);
//			if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0)) {
//				localPageContext.setParams(paramArrayOfObject);
//				for (int i = 0; i < paramArrayOfObject.length; i++) {
//					if (paramArrayOfObject[i] != null)
//						localStringBuilder.append(String.valueOf(paramArrayOfObject[i]));
//				}
//			}
//			try {
//				localPageContext.setKey(encrypt(localStringBuilder.toString(), "MD5"));
//			} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
//				localNoSuchAlgorithmException.printStackTrace();
//			}
//			localPageContext.setType(paramString1);
//			localPageContext.setQueryString(paramString2);
//			if (paramString3 != null) {
//				localPageContext.setPojo(paramString3);
//			}
//			if (paramString4 != null) {
//				localPageContext.setDataSourceID(paramString4);
//			}
//			if ((paramSessionFactory != null) && (ContextLoader.getCurrentWebApplicationContext() != null)) {
//				DefaultListableBeanFactory localDefaultListableBeanFactory = (DefaultListableBeanFactory) ContextLoader.getCurrentWebApplicationContext().getAutowireCapableBeanFactory();
//				String[] arrayOfString1 = localDefaultListableBeanFactory.getBeanNamesForType(paramSessionFactory.getClass());
//				if ((arrayOfString1 != null) && (arrayOfString1.length > 0)) {
//					for (String str4 : arrayOfString1) {
//						if (localDefaultListableBeanFactory.getBean(str4) == paramSessionFactory) {
//							localPageContext.setSessionFactoryId(str4);
//							break;
//						}
//					}
//				}
//			}
//		}
		return localPageContext;
	}

	

}
