package com.iweb.dao;

import com.iweb.dao.impl.AdminDaoImpl;
import com.iweb.entity.Admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IAdminDao {
    int insert(Admin admin) throws SQLException;
    int deleteById(Integer adminId) throws SQLException;
    int updateByOne(Admin admin) throws SQLException;
    Admin selectOneById(Integer adminId) throws SQLException;//通过ID查询出一个
    Admin selectOneByName(String adminName) throws  SQLException;//通过一个对象筛选出表中对象
    int count(Admin admin) throws SQLException;//通过一个对象来统计查询出多少对象
    List<Admin> selectList(Admin admin) throws SQLException;//通过一个对象查出所有有关对象并以链表形式返回
    static IAdminDao getInstance(Connection connection){return new AdminDaoImpl(connection);
    }
}
