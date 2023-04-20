package com.iweb.entity;

public class Car {
 private Integer id;
 private String carName;
 private Integer brandId;
 private String carType;
 private Integer seatNum;
 private Double acceCapa;
 private Double oilWear;
 private Double dailyRent;
 private Integer state;
 private String createDate;
 private String modifyDate;

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carName='" + carName + '\'' +
                ", brandId='" + brandId + '\'' +
                ", carType='" + carType + '\'' +
                ", seatNum=" + seatNum +
                ", acceCapa=" + acceCapa +
                ", oilWear=" + oilWear +
                ", dailyRent=" + dailyRent +
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

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    public Double getAcceCapa() {
        return acceCapa;
    }

    public void setAcceCapa(Double acceCapa) {
        this.acceCapa = acceCapa;
    }

    public Double getOilWear() {
        return oilWear;
    }

    public void setOilWear(Double oilWear) {
        this.oilWear = oilWear;
    }

    public Double getDailyRent() {
        return dailyRent;
    }

    public void setDailyRent(Double dailyRent) {
        this.dailyRent = dailyRent;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
}
