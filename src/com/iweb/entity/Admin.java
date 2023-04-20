package com.iweb.entity;

public class Admin {
   private Integer id;
   private String adminName;
   private String passWord;
   private Integer isLock;

   @Override
   public String toString() {
      return "Admin{" +
              "id=" + id +
              ", adminName='" + adminName + '\'' +
              ", passWord='" + passWord + '\'' +
              ", isLock=" + isLock +
              '}';
   }

   public Integer getIsLock() {
      return isLock;
   }

   public void setIsLock(Integer isLock) {
      this.isLock = isLock;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getAdminName() {
      return adminName;
   }

   public void setAdminName(String adminName) {
      this.adminName = adminName;
   }

   public String getPassWord() {
      return passWord;
   }

   public void setPassWord(String passWord) {
      this.passWord = passWord;
   }
}
