package com.viewhigh.excel.domain;

import org.apache.poi.ss.usermodel.Sheet;
import com.viewhigh.excel.handler.FileTemplate;
import org.springframework.validation.Validator;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class ExcelFile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2174119527939151288L;
	
	Sheet sheet;
	
	boolean hasHeader;
	
	int rowsPerThread = 1000;
	
	FileTemplate fileTemplate;
	
	Class<? extends Base> clazz;
	
	Validator validator;
	
	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	Map<Integer, Limit> sheetThreads;
	
	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public boolean isHasHeader() {
		return hasHeader;
	}

	public void setHasHeader(boolean hasHeader) {
		this.hasHeader = hasHeader;
	}

	public FileTemplate getFileTemplate() {
		return fileTemplate;
	}

	public void setFileTemplate(FileTemplate fileTemplate) {
		this.fileTemplate = fileTemplate;
	}

	public Class<? extends Base> getClazz() {
		return clazz;
	}

	public void setClazz(Class<? extends Base> clazz) {
		this.clazz = clazz;
	}

	public Map<Integer, Limit> getSheetThreads() {
		return sheetThreads;
	}

	public void setSheetThreads(Map<Integer, Limit> sheetThreads) {
		this.sheetThreads = sheetThreads;
	}

	public int getNoOfthreadsToProcess(){
		if(sheet.getLastRowNum()%rowsPerThread > 0 )
			return (sheet.getLastRowNum()/rowsPerThread)+1;
		else
			return (sheet.getLastRowNum()/rowsPerThread);
	}
	
	/**
	 * sheet 工作表
     * hasHeader 是否有表头
     * rowsPerThread 行线程数
     * fileTemplate 工作表处理模版
     * clazz entity类
	 * validator  校验类
	 * */
	
	public ExcelFile(Sheet sheet, boolean hasHeader, int rowsPerThread,
			FileTemplate fileTemplate, Class<? extends Base> clazz,
			Validator validator
			) {
		super();
		this.sheet = sheet;
		this.hasHeader = hasHeader;
		this.rowsPerThread = rowsPerThread;
		this.fileTemplate = fileTemplate;
		this.clazz = clazz;
		this.validator = validator;
		//
		prepareThreads();
	}

	/**
     * 根据表格行数为工作表分配处理线程
     * */
	private void prepareThreads(){
		sheetThreads = new TreeMap<Integer, Limit>();
		//获取线程数量
		int noOfThreads = getNoOfthreadsToProcess();
		Limit limit;
		int startRow;
		int endRow;
		int lastRow = sheet.getLastRowNum();
		for(int i=1;i<=noOfThreads;i++){
			startRow = i * rowsPerThread - rowsPerThread ;
			endRow = (i * rowsPerThread > lastRow ) ? lastRow : i * rowsPerThread-1;
			limit = new Limit();
			limit.setStartRow(startRow);
			limit.setEndRow(endRow);
			//分配每一条线程需要处理的工作表行数
			sheetThreads.put(i, limit);
		}
	}

	@Override
	public String toString() {
		return "ExcelFile [hasHeader=" + hasHeader + ", rowsPerThread="
				+ rowsPerThread + ", sheetThreads=" + sheetThreads.values() + "]";
	}

	
}


