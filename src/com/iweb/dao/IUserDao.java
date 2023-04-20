package com.iweb.dao;

import com.iweb.dao.impl.UserDaoImpl;
import com.iweb.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    int delereById(Integer userId) throws SQLException;

    int insert (User user) throws SQLException;

    int deleteById (Integer userId) throws SQLException;
    int updateById (User user) throws SQLException;
    User selectOne(Integer userId) throws SQLException;

    User selectOne(String userName) throws SQLException;

    int count(User user) throws SQLException;
    List<User> selectList(User user) throws SQLException;

    static IUserDao getInstance(Connection conn) {
        return new UserDaoImpl(conn);
    }
}
