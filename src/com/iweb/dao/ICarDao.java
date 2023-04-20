package com.iweb.dao;

import com.iweb.dao.impl.CarDaoImpl;
import com.iweb.entity.Car;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ICarDao {
    int insert (Car car) throws SQLException;
    int deleteById (Integer carId) throws SQLException;
    int updateById (Car car) throws SQLException;
    Car selectOne(Integer carId) throws SQLException;

    Car selectOne(String carName) throws SQLException;

    int count(Car car) throws SQLException;
    List<Car> selectList(Car car) throws SQLException;

    static ICarDao getInstance(Connection conn) {
        return new CarDaoImpl(conn);
    }
}
