package com.viewhigh.excel.utils;

import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.ExcelFile;
import com.viewhigh.excel.domain.Limit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Callable;

public class ExcelUtilityParallelReader implements  Callable<List<? extends Base>> {

	ExcelFile excelFile;
	
	Limit limit;
	
	boolean isHeaderThread;
	
	private final static Logger log = LoggerFactory
			.getLogger(ExcelUtilityParallelReader.class);
	

	public ExcelUtilityParallelReader(ExcelFile excelFile, Limit limit,boolean isHeaderThread) {
		super();
		this.excelFile = excelFile;
		this.limit = limit;
		this.isHeaderThread = isHeaderThread;
	}

	@Override
	public List<? extends Base> call() throws Exception {
		// TODO Auto-generated method stub
//		log.info("Running Thread for set starting : "+ limit.getStartRow());
		//执行线程方法
		return ExcelUtility.readXlFile(excelFile.getSheet(), excelFile.getFileTemplate(), excelFile.getClazz(), 
				isHeaderThread, limit.getStartRow(), limit.getEndRow(),excelFile.getValidator());

	}
	
	

}
