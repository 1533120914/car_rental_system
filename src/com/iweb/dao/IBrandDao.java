package com.iweb.dao;

import com.iweb.dao.impl.BrandDaoImpl;
import com.iweb.dao.impl.CarDaoImpl;
import com.iweb.entity.Brand;
import com.iweb.entity.Car;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IBrandDao {


    int insert(Brand brand) throws SQLException;

    int deleteById (Integer brandId) throws SQLException;


    int updateById(Brand brand) throws SQLException;

    Brand selectOne(Integer brandId) throws SQLException;

    Brand selectOne(String brandName) throws SQLException;


    List<Brand> selectList(Brand brand) throws SQLException;

    static IBrandDao getInstance(Connection conn) {
        return new BrandDaoImpl(conn);
    }

    int count(Brand brand) throws SQLException;


}
