package com.viewhigh.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 执行办法
 *
 * Created by PeiKai on 2018/1/9.
 */
@Entity
@Table(name = "bd_execute_method")
public class BdExecuteMethod {
    @Id
    @GeneratedValue(generator = "pkExecuteMethodGen")
    @GenericGenerator(name = "pkExecuteMethodGen", strategy = "uuid") // 这个是hibernate的注解/生成32位UUID
    @Column(name = "pk_execute_method")
    private String pkExecuteMethod;

    @Column(name = "pk_hospital")
    private String pkHospital;

    @Column(name = "method_code")
    private String methodCode;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "is_stop")
    private Integer isStop;

    // 备注
    @Column(name = "remark")
    private String remark;

    // 创建人
    @Column(name = "creator")
    private String creator;

    // 创建时间
    @Column(name = "creationtime")
    private Date creationtime;

    // 修改人
    @Column(name = "modifier")
    private String modifier;

    // 修改时间
    @Column(name = "modifiedtime")
    private Date modifiedtime;

    // 删除标志 0 初始值、 1 逻辑删除
    @Column(name = "dr")
    private Integer dr;

    // 时间戳
    @Column(name = "ts")
    private Date ts;

    public String getPkExecuteMethod() {
        return pkExecuteMethod;
    }

    public void setPkExecuteMethod(String pkExecuteMethod) {
        this.pkExecuteMethod = pkExecuteMethod;
    }

    public String getPkHospital() {
        return pkHospital;
    }

    public void setPkHospital(String pkHospital) {
        this.pkHospital = pkHospital;
    }

    public String getMethodCode() {
        return methodCode;
    }

    public void setMethodCode(String methodCode) {
        this.methodCode = methodCode;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getIsStop() {
        return isStop;
    }

    public void setIsStop(Integer isStop) {
        this.isStop = isStop;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
