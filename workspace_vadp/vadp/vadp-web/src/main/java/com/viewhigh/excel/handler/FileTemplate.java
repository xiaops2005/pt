package com.viewhigh.excel.handler;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@XmlRootElement
@Entity
@Table(name = "file_template")
public class FileTemplate implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3810715254328582921L;

	@Id
	@Column(name = "template_id")
	private String templateId;
	@Column(name = "hospital")
	private String hospital;
	@Column(name = "validator")
	private String validator;

	@Column(name = "template_class")
	private String templateClass;
	@Column(name = "template_name")
	private String templateName;

	@Column(name = "db_table_name")
	private String dbTableName;
	@Column(name = "primary_key")
	private String primaryKey;

	@Column(name = "creator")
	private String creator;
	@Column(name = "creationtime")
	private Date creationtime;

	@OneToMany(mappedBy = "fileTemplate", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private List<ColumnTemplate> columnTemplateList;

	@Transient
	private Map<Integer, ColumnTemplate> columnTemplatesMap;
	@Transient
	private Map<String, Integer> columnTemplatesBeanColumnName;
	@Transient
	private Map<String, Map<String, List<String>>> dictionaries;

	public Map<String, Map<String, List<String>>> getDictionaries() {
		return dictionaries;
	}

	public void setDictionaries(Map<String, Map<String, List<String>>> dictionaries) {
		this.dictionaries = dictionaries;
	}

	public String getValidator() {
		return validator;
	}

	public void setValidator(String validator) {
		this.validator = validator;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public Map<Integer, ColumnTemplate> getColumnTemplatesMap() {
		initColumnTemplatesMap();
		return columnTemplatesMap;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateClass() {
		return templateClass;
	}

	public void setTemplateClass(String templateClass) {
		this.templateClass = templateClass;
	}

	public String getDbTableName() {
		return dbTableName;
	}

	public void setDbTableName(String dbTableName) {
		this.dbTableName = dbTableName;
	}

	public List<ColumnTemplate> getColumnTemplateList() {
		return columnTemplateList;
	}

	public void setColumnTemplateList(List<ColumnTemplate> columnTemplateList) {
		this.columnTemplateList = columnTemplateList;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(Date creationtime) {
		this.creationtime = creationtime;
	}

	@Override
	public String toString() {
		return "FileTemplate [templateId=" + templateId + ", templateName="
				+ templateName + ", templateClass=" + templateClass
				+ ", dbTableName=" + dbTableName + ", columnTemplates="
				+ columnTemplateList + "]";
	}

	public ColumnTemplate getColumnTemplateByPos(int pos) {
		initColumnTemplatesMap();
		return columnTemplatesMap.get(pos);
	}

	public ColumnTemplate getColumnTemplateByBeanColumnName(String columnName) {
		initColumnTemplatesMap();
		return columnTemplatesMap.get(columnTemplatesBeanColumnName.get(columnName));
	}

	private void initColumnTemplatesMap() {
		if (columnTemplatesMap == null && columnTemplateList.size() > 0) {
			columnTemplatesMap = new TreeMap<Integer, ColumnTemplate>();
			columnTemplatesBeanColumnName = new HashMap<String, Integer>();
			for (ColumnTemplate columnTemplate : columnTemplateList) {
				columnTemplatesMap.put(columnTemplate.getPos(), columnTemplate);
				columnTemplatesBeanColumnName.put(columnTemplate.getBeanColumnName(),
						columnTemplate.getPos());
			}
		}
	}

	public void setExtension(String hospital, String creator) {
		this.setHospital(hospital);
		this.setCreator(creator);
		this.setCreationtime(new Timestamp(new Date().getTime()));
	}
}
