package com.viewhigh.excel.domain.entity;

import com.viewhigh.excel.domain.Base;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
@Entity
@Table(name = "import_excel_demo")
public class Demo extends Base {

	private static final long serialVersionUID = -6451923943121878568L;


    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator="idGenerator")
    private String  id;
	@Column(name="dept")
	private String dept;
    @Column(name="dept_Name")
	private String deptName;
    @Column(name="exec_Dept_Code")
	private String execDeptCode;
    @Column(name="exec_Dept_Name")
	private String execDeptName;
    @Column(name="cost_Dept")
	private String costDept;
    @Column(name="cost_Item_Code")
	private String costItemCode;
    @Column(name="cost_Item_Name")
	private String costItemName;
    @Column(name="item_Type")
	private String itemType;
    @Column(name="executor")
	private String executor;
    @Column(name="price")
	private BigDecimal price;
    @Column(name="amount")
	private Double amount;
    @Column(name="cost")
	private BigDecimal cost;
    @Column(name="itemSuper")
	private String itemSuper;

    public String getExecDeptCode() {
        return execDeptCode;
    }

    public void setExecDeptCode(String execDeptCode) {
        this.execDeptCode = execDeptCode;
    }

    public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getExecDeptName() {
		return execDeptName;
	}

	public void setExecDeptName(String execDeptName) {
		this.execDeptName = execDeptName;
	}

	public String getCostDept() {
		return costDept;
	}

	public void setCostDept(String costDept) {
		this.costDept = costDept;
	}

	public String getCostItemCode() {
		return costItemCode;
	}

	public void setCostItemCode(String costItemCode) {
		this.costItemCode = costItemCode;
	}

	public String getCostItemName() {
		return costItemName;
	}

	public void setCostItemName(String costItemName) {
		this.costItemName = costItemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getItemSuper() {
		return itemSuper;
	}

	public void setItemSuper(String itemSuper) {
		this.itemSuper = itemSuper;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Demo() {
		// TODO Auto-generated constructor stub
	}

	
	

}
