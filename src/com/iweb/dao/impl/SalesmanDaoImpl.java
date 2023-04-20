package com.iweb.dao.impl;

import com.iweb.dao.BaseDao;
import com.iweb.dao.ISalesmanDao;
import com.iweb.entity.Salesman;
import com.iweb.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SalesmanDaoImpl extends BaseDao implements ISalesmanDao {

    public SalesmanDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    protected Salesman rowToObject(ResultSet resultSet) throws SQLException {
        // 创建一个Salesman对象
        Salesman salesman = null;
        if ( resultSet != null && resultSet.next()) {
            salesman = new Salesman();
            // 根据列名, 依次从resultSet对象(方法的参数)中取出每一列的数据, 调用set方法存储到salesman对象中
            salesman.setId(resultSet.getInt("id"));
            salesman.setSaleName(resultSet.getString("sale_name"));
            salesman.setPassword(resultSet.getString("password"));
            salesman.setSex(resultSet.getString("sex"));
            salesman.setIsLock(resultSet.getInt("is_lock"));
            salesman.setContact(resultSet.getString("contact"));
            salesman.setSal(resultSet.getDouble("sal"));
            salesman.setState(resultSet.getInt("state"));
            // 字符串转为LocalDateTime
            System.out.println(resultSet.getString("create_date"));
            salesman.setCreateDate(resultSet.getString("create_date"));
            salesman.setModifyDate(resultSet.getString("modify_date"));
        }
        // 返回这个salesman对象
        return salesman;
    }

    @Override
    protected List<Salesman> tableToList(ResultSet resultSet) throws SQLException {
        // 创建一个用于存储Salesman对象的ArrayList
        List<Salesman> salesmanList = new ArrayList<>();
        // 判断结果集resultSet不为null
        if ( resultSet != null ){
            // 遍历结果集
            while (resultSet.next()) {
                Salesman salesman = new Salesman();
                // 根据列名, 依次从resultSet对象(方法的参数)中取出每一列的数据, 调用set方法存储到salesman对象中
                salesman.setId(resultSet.getInt("id"));
                salesman.setSaleName(resultSet.getString("sale_name"));
                salesman.setPassword(resultSet.getString("password"));
                salesman.setSex(resultSet.getString("sex"));
                salesman.setContact(resultSet.getString("contact"));
                salesman.setSal(resultSet.getDouble("sal"));
                salesman.setState(resultSet.getInt("state"));
                salesman.setIsLock(resultSet.getInt("is_lock"));
                // 字符串转为LocalDateTime
                System.out.println(resultSet.getString("create_date"));
                salesman.setCreateDate(resultSet.getString("create_date"));
                salesman.setModifyDate(resultSet.getString("modify_date"));
                // 把salesman对象存储到集合中
                salesmanList.add(salesman);
            }
        }
        // 返回存储了salesman对象的集合
        return salesmanList;
    }

    @Override
    public int insert(Salesman salesman) throws SQLException {

 // 调用父类open()方法创建数据库连接, super可以省略
        // 编写添加一个业务员的SQL语句
        // 注意主键id自增长,不需要指定, modify_date是修改日期,在插入的时候不需要指定
        String sql = "INSERT INTO salesman(sale_name,password,sex,contact,sal,state,is_lock,create_date) VALUES(?,?,?,?,?,?,?)";
        // 调用父类的update方法把SQL语句传给父类去执行,返回受影响行数
        // Arrays.asList()方法用于将数组转换为List传给update方法作为第二个参数
        // new Object[]{} 创建一个Object类型的数组, 因为有的数据是字符串, 有的是数字, 有的是浮点数
        int rows = super.update(sql, Arrays.asList(new Object[] {salesman.getSaleName(),salesman.getPassword(),
                salesman.getSex(), salesman.getContact(),salesman.getSal(),salesman.getState(),salesman.getIsLock(),salesman.getCreateDate()}));
        // 在方法return之前,关闭数据库连接
        // 返回这个受影响行数
        return rows;
    }

    @Override
    public int deleteById(Integer salesmanId) throws SQLException {
        String sql = "DELETE FROM salesman WHERE id = ?";
        // 这条SQL语句只有一个? List里面只有一个salesmanId
        int rows = super.update(sql, Arrays.asList(new Object[] {salesmanId}));
        // 把数据库返回的受影响行数作为这个方法的返回值
        return rows;
    }

    @Override
    public int updateById(Salesman salesman) throws SQLException {
        // 注意修改功能的实现要做成动态的, 可以修改任意一个列或者多个列
        // SQL语句要动态拼接

        // 用于存储sql参数的集合
        List<Object> sqlParams = new ArrayList<>();
        // 每次修改都必会更新modify_date列
        String sql = "UPDATE salesman SET modify_date=NOW() ";
        if (salesman.getPassword() != null) {
            sql += ",password=?";
            // 如果修改密码, 把密码添加为sql参数
            sqlParams.add(salesman.getPassword());
        }
        if ( salesman.getContact() != null) {
            sql += ",contact=?";
            // 如果修改联系方式, 把联系方式添加为sql参数
            sqlParams.add(salesman.getContact());
        }
        if ( salesman.getSal() != null) {
            sql += ",sal=?";
            // 如果修改薪资, 把薪资添加为sql参数
            sqlParams.add(salesman.getSal());
        }
        if ( salesman.getState() != null) {
            sql += ",state=?";
            // 如果修改状态, 把状态添加为sql参数
            sqlParams.add(salesman.getState());
        }
        if(salesman.getIsLock()!=null){
            sql+=",is_lock=? ";
            sqlParams.add(salesman.getIsLock());
        }
        // 拼接WHERE 条件
        sql += " WHERE id = ?";
        // 把id添加为sql参数
        sqlParams.add(salesman.getId());
        // 调用父类update方法, 传递sql语句和sql参数集合
        int rows = super.update(sql, sqlParams);

        return rows;
    }

    @Override
    public Salesman selectOne(Integer salesmanId) throws SQLException {

        String sql = "SELECT id,sale_name,password,sex,contact,sal,state,is_lock,DATE_FORMAT(create_date,'%Y-%m-%d %T') AS create_date,DATE_FORMAT(modify_date,'%Y-%m-%d %T') AS modify_date" +
                "  FROM salesman WHERE id = ?";
        Salesman salesman = (Salesman) super.queryOne(sql, Arrays.asList(new Object[]{salesmanId}));

        return salesman;
    }

    @Override
    public Salesman selectOne(String saleName) throws SQLException {
        String sql = "SELECT id,sale_name,password,sex,contact,sal,is_lock,state,DATE_FORMAT(create_date,'%Y-%m-%d %T') AS create_date,DATE_FORMAT(modify_date,'%Y-%m-%d %T') AS modify_date" +
                " FROM salesman WHERE sale_name = ?";
        Salesman salesman = (Salesman) super.queryOne(sql, Arrays.asList(new Object[]{saleName}));
        return salesman;
    }

    @Override
    public int count(Salesman salesman) throws SQLException {
        String sql = "SELECT COUNT(*) FROM salesman "; // 因为下面要拼接, SQL后面留有空格
        String where = "WHERE 1=1 "; // 拼接where条件的字符串 1=1是为了方便拼接AND
        List<Object> sqlParams = new ArrayList<>();
        if (salesman.getSaleName() != null) {
            where += "AND sale_name LIKE CONCAT('%', ?, '%') "; // 拼接根据业务员姓名模糊查询条件 SQL后面留有空格
            sqlParams.add(salesman.getSaleName());
        }
        if (salesman.getSex() != null) {
            where += "AND sex=? "; // 拼接根据业务员性别查询条件 SQL后面留有空格
            sqlParams.add(salesman.getSex());
        }
        if (salesman.getContact() != null) {
            where += "AND contact=? "; // 拼接根据业务员电话号码查询条件 SQL后面留有空格
            sqlParams.add(salesman.getContact());
        }
        if (salesman.getState() != null) {
            where += "AND state=? "; // 拼接根据业务员状态查询条件 SQL后面留有空格
            sqlParams.add(salesman.getState());
        }
        sql += where;
        int count = super.count(sql, sqlParams);

        return count;
    }

    @Override
    public List<Salesman> selectList(Salesman salesman) throws SQLException {
        String sql = "SELECT id,sale_name,password,is_lock,sex,contact,sal,state,DATE_FORMAT(create_date,'%Y-%m-%d %T') AS create_date,DATE_FORMAT(modify_date,'%Y-%m-%d %T') AS modify_date FROM salesman "; // 因为下面要拼接, SQL后面留有空格
        String where = "WHERE 1=1 "; // 拼接where条件的字符串 1=1是为了方便拼接AND
        List<Object> sqlParams = new ArrayList<>();
        if (salesman.getSaleName() != null) {
            where += "AND sale_name LIKE CONCAT('%', ?, '%') "; // 拼接根据业务员姓名模糊查询条件 SQL后面留有空格
            sqlParams.add(salesman.getSaleName());
        }
        if (salesman.getSex() != null) {
            where += "AND sex=? "; // 拼接根据业务员性别查询条件 SQL后面留有空格
            sqlParams.add(salesman.getSex());
        }
        if (salesman.getContact() != null) {
            where += "AND contact=? "; // 拼接根据业务员电话号码查询条件 SQL后面留有空格
            sqlParams.add(salesman.getContact());
        }
        if (salesman.getState() != null) {
            where += "AND state=? "; // 拼接根据业务员状态查询条件 SQL后面留有空格
            sqlParams.add(salesman.getState());
        }
        sql += where;
        List<Salesman>  list = (List<Salesman>) super.query(sql, sqlParams);
        return list;
    }
}
