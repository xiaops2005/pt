package com.viewhigh.excel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@XmlRootElement
public class Base implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1664479729754546180L;
	
	public Base() {
		// TODO Auto-generated constructor stub
	}
	
	@JsonIgnore
	@XmlTransient
    Errors errors;
	
	long row;

	public long getRow() {
		return row;
	}

	public void setRow(long row) {
		this.row = row;
	}

	@XmlTransient
	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}
	
	@JsonRawValue
	public String getError(){
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		if(errors !=null && this.errors.hasErrors()){
			
			for(FieldError fieldError: errors.getFieldErrors() )
			{
				builder.append("{ \"column\" : \""+fieldError.getField()+"\",\"rejectedValue\""
						+ ":\""+fieldError.getRejectedValue()+" / "+fieldError.getDefaultMessage());
				builder.append("\",\"message\":\""+fieldError.getDefaultMessage()+" / Invalid Value "+"\"},");
			}
			builder.replace(builder.lastIndexOf(","), builder.length(), "");
		}
		builder.append("]");
		
		return builder.toString();
	}

}
