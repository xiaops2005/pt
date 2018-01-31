package com.viewhigh.vadp.framework.ds;
/**
 * 
 * 请求数据列信息
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
public class Column {

	private Object name;// 名称
	private Object dataType;// 数据类型
	private Boolean nullable;// 是否可以为空
	private Object precision;
	private Integer scale;// 数据类型为数字类型，获取小数点位数信息
	private Integer maxLength;// 最大值长度
	private Integer minLength;// 最小值长度
	private Boolean primaryKey;// 是否为主键
	private Object label;// 显示的名称label
	private Object defaultValue;// 默认值
	private Object format;
	private Long min;// 最小值
	private Long max;// 最大值
	private Object range;// 值的取值范围，默认为空
	private Object past;// 最小时间
	private Object future;// 最大时间整型值
	private Object pattern;// 正则表达式
	private Object prompts;// 根据属性名称获取，校验提示信息，默认为空

	public Object getName() {
		return name;
	}

	public void setName(Object name) {
		this.name = name;
	}

	public Object getDataType() {
		return dataType;
	}

	public void setDataType(Object dataType) {
		this.dataType = dataType;
	}

	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	public Object getPrecision() {
		return precision;
	}

	public void setPrecision(Object precision) {
		this.precision = precision;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
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

	public Boolean getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Object getLabel() {
		return label;
	}

	public void setLabel(Object label) {
		this.label = label;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Object getFormat() {
		return format;
	}

	public void setFormat(Object format) {
		this.format = format;
	}

	public Long getMin() {
		return min;
	}

	public void setMin(Long min) {
		this.min = min;
	}

	public Long getMax() {
		return max;
	}

	public void setMax(Long max) {
		this.max = max;
	}

	public Object getRange() {
		return range;
	}

	public void setRange(Object range) {
		this.range = range;
	}

	public Object getPast() {
		return past;
	}

	public void setPast(Object past) {
		this.past = past;
	}

	public Object getFuture() {
		return future;
	}

	public void setFuture(Object future) {
		this.future = future;
	}

	public Object getPattern() {
		return pattern;
	}

	public void setPattern(Object pattern) {
		this.pattern = pattern;
	}

	public Object getPrompts() {
		return prompts;
	}

	public void setPrompts(Object prompts) {
		this.prompts = prompts;
	}

}
