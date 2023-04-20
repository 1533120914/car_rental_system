package com.iweb.entity;

import java.time.LocalDateTime;

public class RentRecord {
    private Integer id;
    private Integer carId;
    private Integer userId;
    private Integer salesmanId;
    private Integer rentDays;
    private Double prepay;
    private Integer state;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @Override
    public String toString() {
        return "RentRecord{" +
                "id=" + id +
                ", carId=" + carId +
                ", userId=" + userId +
                ", salesmanId=" + salesmanId +
                ", rentDays=" + rentDays +
                ", prepay=" + prepay +
                ", state=" + state +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSalementId() {
        return salesmanId;
    }

    public void setSalementId(Integer salementId) {
        this.salesmanId = salementId;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }
}
