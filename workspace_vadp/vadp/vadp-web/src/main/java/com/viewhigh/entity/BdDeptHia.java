package com.viewhigh.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name = "bd_dept_hia")
public class BdDeptHia {

    @Id
    @GeneratedValue(generator = "pkBdDeptHiaGen")
    @GenericGenerator(name = "pkBdDeptHiaGen", strategy = "uuid") // 这个是hibernate的注解/生成32位UUID
    @Column(name = "pk_dept_hia")
    private String pkDeptHia;//HIA科室主键

    @Column(name = "hia_code")
    private String hiaCode;//HIA科室编码

    @Column(name = "hia_name")
    private String hiaName;//HIA科室名称

    @Column(name = "is_stop")
    private Integer isStop;//是否停用  0 否（默认值）、 1 是

    @Column(name = "remark")
    private String remark;//备注

    @Column(name = "belong_area")
    private String belongArea;//所属区域  多区域集成时使用

    @Column(name = "creator")
    private String creator; //创建人  外键

    @Column(name = "creationtime")
    private Date creationtime;//创建时间

    @Column(name = "modifier")
    private String modifier; //修改人  外键

    @Column(name = "modifiedtime")
    private Date modifiedtime;//修改时间

    @Column(name = "dr")
    private Integer dr; //删除标志  0 初始值、 1 逻辑删除

    @Column(name = "ts")
    private Date ts; //时间戳

    public BdDeptHia() {
    }

    public String getPkDeptHia() {
        return pkDeptHia;
    }

    public void setPkDeptHia(String pkDeptHia) {
        this.pkDeptHia = pkDeptHia;
    }

    public String getHiaCode() {
        return hiaCode;
    }

    public void setHiaCode(String hiaCode) {
        this.hiaCode = hiaCode;
    }

    public String getHiaName() {
        return hiaName;
    }

    public void setHiaName(String hiaName) {
        this.hiaName = hiaName;
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
