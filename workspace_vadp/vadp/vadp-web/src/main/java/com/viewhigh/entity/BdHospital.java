package com.viewhigh.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by litianzhu on 2018/1/10.
 */
@XmlRootElement
@Entity
@Table(name = "BD_HOSPITAL")
public class BdHospital {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid") // 这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "pk_hospital")
    private String pkHospital;

    @Column(name = "hospital_code")
    private String hospitalCode;

    @Column(name = "hospital_name")
    private String hospitalName;
    @Column(name = "hospital_class_id")
    private String hospitalClassId;
    @Column(name = "hospital_type_id")
    private String hospitalTypeId;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "belong_area")
    private String belongArea;
    @Column(name = "CREATOR")
    private String creator;
    @Column(name = "CREATIONTIME")
    private Date creationtime;
    @Column(name = "MODIFIER")
    private String modifier;
    @Column(name = "MODIFIEDTIME")
    private Date modifiedtime;

    @Column(name = "DR")
    private Integer dr;

    @Column(name = "TS")
    private Date ts;


    public String getPkHospital() {
        return pkHospital;
    }

    public void setPkHospital(String pkHospital) {
        this.pkHospital = pkHospital;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalClassId() {
        return hospitalClassId;
    }

    public void setHospitalClassId(String hospitalClassId) {
        this.hospitalClassId = hospitalClassId;
    }

    public String getHospitalTypeId() {
        return hospitalTypeId;
    }

    public void setHospitalTypeId(String hospitalTypeId) {
        this.hospitalTypeId = hospitalTypeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBelongArea() {
        return belongArea;
    }

    public void setBelongArea(String belongArea) {
        this.belongArea = belongArea;
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

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(Date modifiedtime) {
        this.modifiedtime = modifiedtime;
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }
}
