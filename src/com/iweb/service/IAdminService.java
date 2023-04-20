package com.iweb.service;

import com.iweb.entity.Admin;
import com.iweb.service.impl.AdminServiceImpl;

import java.util.List;

public interface IAdminService {
    boolean save(Admin admin);
    boolean removeById(Integer adminId);
    boolean updateById(Admin admin);
    Admin getOne(Integer adminId);
    Admin getOne(String adminName);
    int count(Admin admin);
    List<Admin> list(Admin admin);
    static AdminServiceImpl getInstance() {//用于根据条件获取用户列表。该方法会返回一个包含用户对象的列表。
        return new AdminServiceImpl();
    }

}
