package com.viewhigh.vadp.portal.sys.domain;

import com.viewhigh.vadp.common.domain.BaseEntity;

import javax.persistence.*;

import java.util.*;

/**
 * 组织机构
 */

@Entity
@Table(name = "td_pt_domain")
public class SysDomain extends BaseEntity {


    /**
     * 组织名称
     */
    @Column(name="name",nullable = false, length = 200)
    private String name;

    /**
     * 详细地址
     */
    @Column(name="address")
    private String address;

    /**
     * 联系人
     */
    @Column(name="contact")
    private String contact;

    /**
     * 联系电话
     */
    @Column(name="phone")
    private String phone;

    /**
     * 省份
     */
    @Column(name="province")
    private String province;

    /**
     * 城市
     */
    @Column(name="city")
    private String city;

    /**
     * 行政区
     */
    @Column(name="district")
    private String district;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }


}
