package com.viewhigh.vadp.framework.data.persistence.pagination;

/**
 * 保存分页信息到线程变量中
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
public class PageInfoThreadConstant {
	private static final ThreadLocal<PageInfo> pageInfos = new ThreadLocal<PageInfo>();
	
	public static PageInfo getPageInfo() {
		return pageInfos.get();
	}

	public static void setPageInfo(PageInfo info) {
		pageInfos.set(info);
	}

}
