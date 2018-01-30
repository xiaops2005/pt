package com.viewhigh.excel.handler;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.utils.ClazzUtil;
import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import com.viewhigh.vadp.framework.util.StringUtil;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import javax.xml.bind.annotation.XmlElement;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ExcelTemplateHandler extends BaseHibernateDAO {
	public List<FileTemplate> getFileTemplteAll() {
		List<FileTemplate> fileTemplateList = Lists.newArrayList();
		Set<Class<?>> classes = Sets.newLinkedHashSet();
		classes.addAll(ClazzUtil.getClasses("com.viewhigh.entity", Base.class));
		classes.addAll(ClazzUtil.getClasses("com.viewhigh.excel.domain.entity", Base.class));
		classes.forEach(clazz -> {
			FileTemplate fileTemplate = new FileTemplate();
			String tableName = clazz.getName();
			Annotation annotation = clazz.getAnnotation(Table.class);
			if (annotation != null) {
				Table table = (Table) annotation;
				tableName = table.name();
			}
			fileTemplate.setTemplateClass(clazz.getName());
			fileTemplate.setDbTableName(tableName);
			List<ColumnTemplate> columnTemplateList = getAllColumnTemplatesByClazz(clazz.getName());
			columnTemplateList = columnTemplateList.stream().filter(columnTemplate -> {
				return "ID".equals(columnTemplate.getTemplateName());
			}).collect(Collectors.toList());
			if (columnTemplateList.size() > 0) {
				fileTemplate.setPrimaryKey(columnTemplateList.get(0).getDbColumnName());
			}
			fileTemplate.setColumnTemplateList(Lists.newArrayList());
			fileTemplateList.add(fileTemplate);
		});
		return fileTemplateList;
	}

	private List<ColumnTemplate> getAllColumnTemplatesByClazz(String clazz0) {
		Class clazz = null;
		try {
			clazz = Class.forName(clazz0);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return Lists.newArrayList();
		}
		Field[] fs = clazz.getDeclaredFields();
		List<ColumnTemplate> columnTemplateList = Lists.newArrayList();
		for (int i = 0; i < fs.length; i++) {
			Field f = fs[i];
			String propName = f.getName();
			Annotation xmlElementAnnotation = f.getAnnotation(XmlElement.class);
			Annotation columnAnnotation = f.getAnnotation(Column.class);
			String dbColumnName = ClazzUtil.camel2Underline(propName);
			String templateName = "";
			if (xmlElementAnnotation != null) {
				// 强制转化为相应的注解
				XmlElement xmlElement = (XmlElement) xmlElementAnnotation;
				templateName = xmlElement.name();
			}
			if (f.getAnnotation(Id.class) != null) {
				templateName = "ID";
			}
			if (columnAnnotation != null) {
				Column columnElement = (Column) columnAnnotation;
				dbColumnName = columnElement.name();
			}
			ColumnTemplate columnTemplate = new ColumnTemplate();
			columnTemplate.setTemplateName(templateName);
			columnTemplate.setDbColumnName(dbColumnName);
			columnTemplate.setBeanColumnName(propName);
			columnTemplate.setType(ClazzUtil.getDbType(f.getGenericType().toString()));
			columnTemplateList.add(columnTemplate);
		}
		return columnTemplateList;
	}

	public List<FileTemplate> queryFileTemplate(String templateName, String bdTableName) {
		String sql = "from FileTemplate where 1=1";
		List<String> params = Lists.newArrayList();
		if (!StringUtil.isEmpty(templateName)) {
			sql += " and templateName=?";
			params.add(templateName);
		}
		if (!StringUtil.isEmpty(bdTableName)) {
			sql += " and dbTableName=?";
			params.add(bdTableName);
		}
		return queryObjects(sql, params.toArray());
	}

	private List<ColumnTemplate> queryColumnTemplateByTemplateClass(String templateId) {
		String sql = "from ColumnTemplate where templateId = ?";
		return queryObjects(sql, new String[]{templateId});
	}

	private FileTemplate findFileTemplateById(String templateId) {
		String sql = "from FileTemplate where templateId = ?";
		return (FileTemplate) this.queryObject(sql, new String[]{templateId});
	}

	public List<ColumnTemplate> getColumnTemplatesByClazz(String templateId) {
		FileTemplate fileTemplate = findFileTemplateById(templateId);
		List<ColumnTemplate> allColumnTemplateList = getAllColumnTemplatesByClazz(fileTemplate.getTemplateClass());
		List<ColumnTemplate> addedColumnTemplateList = queryColumnTemplateByTemplateClass(templateId);
		List<ColumnTemplate> columnTemplateList = Lists.newArrayList();
		allColumnTemplateList.forEach(columnTemplate -> {
			List<ColumnTemplate> filterColumnTemplateList = addedColumnTemplateList.stream().filter(addedColumnTemplate
					-> columnTemplate.getDbColumnName().equalsIgnoreCase(addedColumnTemplate.getDbColumnName()))
					.collect(Collectors.toList());
			if (filterColumnTemplateList.size() > 0) {
				columnTemplateList.add(filterColumnTemplateList.get(0));
			} else {
				columnTemplate.setTemplateId(templateId);
				columnTemplateList.add(columnTemplate);
			}
		});
		return columnTemplateList;
	}

	@Transactional
	public void saveFileTemplate(List<FileTemplate> fileTemplateList) {
		Date now = new Date();
		fileTemplateList.forEach(fileTemplate -> {
			if (StringUtil.isEmpty(fileTemplate.getTemplateId())) {
				fileTemplate.setTemplateId(UUID.randomUUID().toString().replaceAll("-", ""));
				fileTemplate.setCreationtime(now);
				addObject(fileTemplate);
			} else {
				updateObject(fileTemplate);
			}
		});
	}

	@Transactional
	public void deleteFileTemplate(String templateIds) {
		String sql = "delete from File_Template where template_id in (" + templateIds + ")";
		update(sql, null);
	}

	@Transactional
	public void saveColumnTemplate(String templateId, List<ColumnTemplate> columnTemplateList) {
		String deleteSql = "delete from column_template where template_id=?";
		update(deleteSql, new String[]{templateId});
		columnTemplateList.forEach(columnTemplate -> {
			if (StringUtil.isEmpty(columnTemplate.getId())) {
				columnTemplate.setId(UUID.randomUUID().toString().replaceAll("-",""));
				addObject(columnTemplate);
			} else {
				updateObject(columnTemplate);
			}
		});
	}
}
