package com.iweb.entity.vo;

import com.iweb.util.DateUtil;

import java.time.LocalDateTime;

/**
 * 用于封装租赁记录多表查询的数据类
 */
public class RentRecordVO {
    private Integer id; // 租赁订单的id
    private String carName; // 汽车名
    private String username; // 用户名
    private String saleName; // 业务员名
    private Integer rentDays; // 租赁天数
    private Double prepay; // 预付款
    private Integer state; // 状态
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public Integer getRentDays() {
        return rentDays;
    }

    public void setRentDays(Integer rentDays) {
        this.rentDays = rentDays;
    }

    public Double getPrepay() {
        return prepay;
    }

    public void setPrepay(Double prepay) {
        this.prepay = prepay;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreateDate() {
        return DateUtil.localDateTimeToString(this.createDate);
    }

    public void setCreateDate(String createDate) {
        this.createDate = DateUtil.stringToLocalDateTime(createDate);
    }

    public String getModifyDate() {
        return DateUtil.localDateTimeToString(this.modifyDate);
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = DateUtil.stringToLocalDateTime(modifyDate);
    }
}
