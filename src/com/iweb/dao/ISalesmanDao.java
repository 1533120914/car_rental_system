package com.iweb.dao;

import com.iweb.dao.impl.SalesmanDaoImpl;
import com.iweb.entity.Salesman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ISalesmanDao {
    int insert (Salesman salesman) throws SQLException;
    int deleteById (Integer salesmanId) throws SQLException;
    int updateById (Salesman salesman) throws SQLException;
    Salesman selectOne(Integer salesmanId) throws SQLException;
    Salesman selectOne(String saleName) throws SQLException;
    int count(Salesman salesman) throws SQLException;
    List<Salesman> selectList(Salesman salesman) throws SQLException;

    static ISalesmanDao getInstance(Connection conn) {
        return new SalesmanDaoImpl(conn);
    }
}
