package com.viewhigh.excel.domain.entity;

import com.viewhigh.excel.domain.Base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name = "bd_dept")
public class BdDept extends Base {

    @Id
//	 @GenericGenerator(name="idGenerator", strategy="uuid")
    //这个是hibernate的注解/生成32位UUID
//	 @GeneratedValue(generator="idGenerator")
    @Column(name = "pk_dept")
    private String pkDept;
    @Column(name = "pk_hospital")
    private String pkHospital;
    @Column(name = "dept_code")
    private String deptCode;
    @Column(name = "dept_name")
    private String deptName;
    @Column(name = "dept_level")
    private int deptLevel;
    @Column(name = "pk_father")
    private String pkFather;
    @Column(name = "is_leaf")
    private String isLeaf;
    @Column(name = "is_stop")
    private String isStop;

    //外键，来源于 bd_code 中 codetype_id = “BELONG_SYSTEM”
    @Column(name = "belong_system_id")
    private String belongSystemId;

    //外键，来源于 bd_code 中 codetype_id = “DEPT_TYPE”
    @Column(name = "dept_type_id")
    private String deptTypeId;


    @Column(name = "is_standard")
    private Integer isStandard;//0 否 、 1 是

    @Column(name = "acc_year")
    private Integer accYear;
    @Column(name = "remark")
    private String remark;
    @Column(name = "creator")
    private String creator;
    @Column(name = "creationtime")
    private java.util.Date creationtime;
    @Column(name = "modifier")
    private String modifier;
    @Column(name = "modifiedtime")
    private Date modifiedtime;
    @Column(name = "dr")
    private Long dr;
    @Column(name = "ts")
    private Date ts;

    @Column(name = "def1")
    private String def1;
    @Column(name = "def2")
    private String def2;
    @Column(name = "def3")
    private String def3;
    @Column(name = "def4")
    private String def4;

    @Column(name = "def5")
    private String def5;

    public String getPkDept() {
        return pkDept;
    }

    public void setPkDept(String pkDept) {
        this.pkDept = pkDept;
    }

    public String getPkHospital() {
        return pkHospital;
    }

    public void setPkHospital(String pkHospital) {
        this.pkHospital = pkHospital;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(int deptLevel) {
        this.deptLevel = deptLevel;
    }

    public String getPkFather() {
        return pkFather;
    }

    public void setPkFather(String pkFather) {
        this.pkFather = pkFather;
    }


    public Integer getAccYear() {
        return accYear;
    }

    public void setAccYear(Integer accYear) {
        this.accYear = accYear;
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

    public Long getDr() {
        return dr;
    }

    public void setDr(Long dr) {
        this.dr = dr;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getIsStop() {
        return isStop;
    }

    public void setIsStop(String isStop) {
        this.isStop = isStop;
    }

    public String getBelongSystemId() {
        return belongSystemId;
    }

    public void setBelongSystemId(String belongSystemId) {
        this.belongSystemId = belongSystemId;
    }

    public String getDeptTypeId() {
        return deptTypeId;
    }

    public void setDeptTypeId(String deptTypeId) {
        this.deptTypeId = deptTypeId;
    }

    public String getDef1() {
        return def1;
    }

    public void setDef1(String def1) {
        this.def1 = def1;
    }

    public String getDef2() {
        return def2;
    }

    public void setDef2(String def2) {
        this.def2 = def2;
    }

    public String getDef3() {
        return def3;
    }

    public void setDef3(String def3) {
        this.def3 = def3;
    }

    public String getDef4() {
        return def4;
    }

    public void setDef4(String def4) {
        this.def4 = def4;
    }

    public String getDef5() {
        return def5;
    }

    public void setDef5(String def5) {
        this.def5 = def5;
    }

    public Integer getIsStandard() {
        return isStandard;
    }

    public void setIsStandard(Integer isStandard) {
        this.isStandard = isStandard;
    }
}
