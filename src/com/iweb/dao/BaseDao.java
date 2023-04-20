package com.iweb.dao;

import java.sql.*;
import java.util.List;

public abstract class BaseDao {


    protected Connection conn; // 成员变量, 用于存储数据库连接对象

    public BaseDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * 执行增删改操作
     * @param sql 需要执行sql语句
     * @param sqlParams 用于替换sql语句中?的参数列表
     * @return 受影响行数
     * @throws SQLException
     */
    protected int update (String sql, List<Object> sqlParams) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(sql);
        if ( sqlParams != null && sqlParams.size()>0 ){
            for (int i = 0; i < sqlParams.size(); i++) {
                statement.setObject((i + 1), sqlParams.get(i));
            }
        }
        System.out.println("---本次执行的sql---");
        System.out.println(sql);
        return statement.executeUpdate();
    }

    /**
     * 执行查询操作
     * @param sql 需要执行sql语句
     * @param sqlParams 用于替换sql语句中?的参数列表
     * @return 对象集合
     * @throws SQLException
     */
    protected Object query (String sql, List<Object> sqlParams) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(sql);
        if ( sqlParams != null && sqlParams.size()>0 ){
            for (int i = 0; i < sqlParams.size(); i++) {
                statement.setObject((i + 1), sqlParams.get(i));
            }
        }
        System.out.println("---本次执行的sql---");
        System.out.println(sql);
        ResultSet resultSet = statement.executeQuery();
        // tableToList在父类中是抽象的方法, 但是子类做了方法重写
        // 父类并还不知道结果集中是哪张表的数据, 因此无法决定用什么对象存储
        // 因此把取结果集数据的代码定义为抽象方法, 让子类去实现取数据的过程, 不同的Dao子类指的取出来的数据应该用什么对象存储
        // 这里调用的是子类重写的方法
        return tableToList(resultSet);
    }

    /**
     * 执行查询一行数据操作
     * @param sql 需要执行sql语句
     * @param sqlParams 用于替换sql语句中?的参数列表
     * @return 对象
     * @throws SQLException
     */
    protected Object queryOne (String sql, List<Object> sqlParams) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(sql);
        if ( sqlParams != null && sqlParams.size()>0 ){
            for (int i = 0; i < sqlParams.size(); i++) {
                statement.setObject((i + 1), sqlParams.get(i));
            }
        }
        System.out.println("---本次执行的sql---");
        System.out.println(sql);
        ResultSet resultSet = statement.executeQuery();
        // tableToList在父类中是抽象的方法, 但是子类做了方法重写
        // 父类并还不知道结果集中是哪张表的数据, 因此无法决定用什么对象存储
        // 因此把取结果集数据的代码定义为抽象方法, 让子类去实现取数据的过程, 不同的Dao子类指的取出来的数据应该用什么对象存储
        // 这里调用的是子类重写的方法t
        try {

            return rowToObject(resultSet);
        }catch(SQLException sqlException){
            System.out.println("不存在");
        }
        return null;
    }

    /**
     * 统计行数
     * @param sql 需要执行sql语句
     * @param sqlParams 用于替换sql语句中?的参数列表
     * @return 行数
     * @throws SQLException
     */
    protected int count (String sql, List<Object> sqlParams) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(sql);
        if ( sqlParams != null && sqlParams.size()>0 ){
            for (int i = 0; i < sqlParams.size(); i++) {
                statement.setObject((i + 1), sqlParams.get(i));
            }
        }
        System.out.println("---本次执行的sql---");
        System.out.println(sql);
        ResultSet resultSet = statement.executeQuery();
        int count = 0;
        if (resultSet != null) {
            // 统计count的sql语句只会查出一行一列, 不需要用循环遍历resultSet
            resultSet.next(); // 只需要让resultSet指向第一行即可
            count = resultSet.getInt(1); // 没有列名, 用第一列的序号1取出数据
        }
        return count;
    }

    /**
     * 子类重写, 将结果集对象中的一行数据转换为一个Java实体对象
     * @param resultSet 结果集对象
     * @return 实体对象
     * @throws SQLException
     */
    protected abstract Object rowToObject(ResultSet resultSet) throws SQLException;

    /**
     * 子类重写, 将结果集对象中数据转换为一个List集合
     * @param resultSet 结果集对象
     * @return List集合
     * @throws SQLException
     */
    protected abstract Object tableToList(ResultSet resultSet) throws SQLException;
}
