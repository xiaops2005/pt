package com.viewhigh.excel.controller;

import com.viewhigh.excel.handler.ColumnTemplate;
import com.viewhigh.excel.handler.ExcelTemplateHandler;
import com.viewhigh.excel.handler.FileTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class ExcelTemplateController {

	@Autowired
	ExcelTemplateHandler excelTemplateHandler;

	/**
	 * 获取所有可以添加导入模版entity
	 *
	 * @return List
	 */
	@RequestMapping(value = "/get-template-entity", method = RequestMethod.GET)
	public @ResponseBody
	List<FileTemplate> getTemplateEntity() {
		return excelTemplateHandler.getFileTemplteAll();
	}

	/**
	 * 获取实体所有列(包含已添加及未添加)
	 *
	 * @return List
	 */
	@RequestMapping(value = "/get-template-columns", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody
	List<ColumnTemplate> getTemplateColumns(@RequestParam("templateId") String templateId) {
		return excelTemplateHandler.getColumnTemplatesByClazz(templateId);
	}

	/**
	 * 查询当前已添加的entity模版
	 *
	 * @param templateName 模版中文名
	 * @param dbTableName  数据库表名
	 * @return List
	 */
	@RequestMapping(value = "/query-template-entity", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody
	List<FileTemplate> query(@RequestParam("templateName") String templateName, @RequestParam("dbTableName") String dbTableName) {
		return excelTemplateHandler.queryFileTemplate(templateName, dbTableName);
	}

	/**
	 * 保存entity模版
	 *
	 * @param fileTemplateList entity模版列表
	 */
	@RequestMapping(value = "/save-template-entity", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody
	void saveTemplateEntity(@RequestBody List<FileTemplate> fileTemplateList) {
		excelTemplateHandler.saveFileTemplate(fileTemplateList);
	}

	/**
	 * 删除entity模版
	 *
	 * @param templateIds entity模版主键拼接字符串
	 */
	@RequestMapping(value = "/delete-template-entity", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody
	void deleteTemplateEntity(@RequestParam("templateIds") String templateIds) {
		excelTemplateHandler.deleteFileTemplate(templateIds);
	}

	/**
	 * 保存模版列
	 *
	 * @param templateId 模版id
	 */
	@RequestMapping(value = "/save-template-columns", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody
	void saveColumnTemplate(@RequestParam("templateId") String templateId, @RequestBody List<ColumnTemplate> columnTemplateList) {
		excelTemplateHandler.saveColumnTemplate(templateId, columnTemplateList);
	}
}
