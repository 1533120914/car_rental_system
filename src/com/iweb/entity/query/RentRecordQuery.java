package com.iweb.entity.query;

import com.iweb.util.DateUtil;

import java.time.LocalDateTime;

/**
 * 封装租赁记录查询条件的类
 */
public class RentRecordQuery {
    // 用于业务员查询的条件,每个业务员只能查属于自己的数据
    private Integer salesmanId;
    // 用于用户的查询条件,每个用户只能查属于自己的数据
    private Integer userId;
    // 可选条件1:根据汽车名称模糊查询
    private String carName;
    // 可选条件2:根据业务员姓名模糊查询
    private String saleName;
    // 可选条件3:根据用户姓名模糊查询
    private String username;
    // 可选条件4:根据租赁状态查询
    private Integer state;
    // 可选条件5:根据订单创建日期区间查询
    private LocalDateTime smallDateTime;
    private LocalDateTime bigDateTime;

    public Integer getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Integer salesmanId) {
        this.salesmanId = salesmanId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSmallDateTime() {
        return DateUtil.localDateTimeToString(this.smallDateTime);
    }

    public void setSmallDateTime(String smallDateTime) {
        this.smallDateTime = DateUtil.stringToLocalDateTime(smallDateTime);
    }

    public String getBigDateTime() {
        return DateUtil.localDateTimeToString(this.bigDateTime);
    }

    public void setBigDateTime(String bigDateTime) {
        this.bigDateTime = DateUtil.stringToLocalDateTime(bigDateTime);
    }
}
