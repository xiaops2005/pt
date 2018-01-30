package com.viewhigh.excel.handler;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name = "column_template")
public class ColumnTemplate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5796776674559197647L;

	@Id
	private String id;

	@Column(name = "template_id")
	private String templateId;

	@Column(name = "pos")
	private Integer pos;

	@Column(name = "template_name")
	private String templateName;

	@Column(name = "db_column_name")
	private String dbColumnName;

	@Column(name = "bean_column_name")
	private String beanColumnName;

	@Column(name = "type")
	private String type;

	@Column(name = "is_insert")
	private Integer isInsert;

	@Column(name = "required")
	private Integer required;

	@Column(name = "max_length")
	private Integer maxLength;

	@Column(name = "min_length")
	private Integer minLength;

	@ManyToOne
	@JoinColumn(name = "template_id", referencedColumnName = "template_id", updatable = false, insertable = false)
	@XmlTransient
	private FileTemplate fileTemplate;

	public ColumnTemplate() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getDbColumnName() {
		return dbColumnName;
	}

	public void setDbColumnName(String dbColumnName) {
		this.dbColumnName = dbColumnName;
	}

	public String getBeanColumnName() {
		return beanColumnName;
	}

	public void setBeanColumnName(String beanColumnName) {
		this.beanColumnName = beanColumnName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIsInsert() {
		return isInsert;
	}

	public void setIsInsert(Integer isInsert) {
		this.isInsert = isInsert;
	}

	public Integer getRequired() {
		return required;
	}

	public void setRequired(Integer required) {
		this.required = required;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public Integer getMinLength() {
		return minLength;
	}

	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	@Override
	public String toString() {
		return "[ pos=" + pos 	+ ", ColumnName=" + templateName + ", type=" + type + "]";
	}
	
	

}
