package com.iweb.entity;

import com.iweb.util.DateUtil;

import java.time.LocalDateTime;

public class Salesman {
 private   Integer id;
 private   String saleName;
 private   String password;
 private   String sex;
 private   String contact;
 private   Double sal;
 private   Integer state;
 private   LocalDateTime createDate;
 private   LocalDateTime modifyDate;
 private   Integer isLock;

    @Override
    public String toString() {
        return "Salesman{" +
                "id=" + id +
                ", saleName='" + saleName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", contact='" + contact + '\'' +
                ", sal=" + sal +
                ", state=" + state +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", isLock=" + isLock +
                '}';
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public static DateUtil getDateUtil() {
        return dateUtil;
    }

    public static void setDateUtil(DateUtil dateUtil) {
        Salesman.dateUtil = dateUtil;
    }

    static DateUtil dateUtil=new DateUtil();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void getone(Salesman salesman){};
    public int getone(int id){
        return id;
    };

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
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
        this.createDate =DateUtil.stringToLocalDateTime( createDate);
    }

    public String getModifyDate() {
        return DateUtil.localDateTimeToString(this.modifyDate);
    }

    public void setModifyDate(String modifyDate) {

        this.modifyDate = DateUtil.stringToLocalDateTime(modifyDate);
    }


}
