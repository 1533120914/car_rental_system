package com.iweb.dao.impl;
import com.iweb.dao.BaseDao;
import com.iweb.dao.IAdminDao;
import com.iweb.entity.Admin;
import com.iweb.entity.Salesman;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminDaoImpl extends BaseDao implements IAdminDao {
    public AdminDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    protected Object rowToObject(ResultSet resultSet) throws SQLException {
        Admin salesman = null;
        if ( resultSet != null && resultSet.next()) {
            salesman = new Admin();
            // 根据列名, 依次从resultSet对象(方法的参数)中取出每一列的数据, 调用set方法存储到salesman对象中
            salesman.setId(resultSet.getInt("id"));
            salesman.setAdminName(resultSet.getString("admin_name"));
            salesman.setPassWord(resultSet.getString("password"));
            salesman.setIsLock(resultSet.getInt("is_lock"));
        }
        // 返回这个salesman对象
        return salesman;
    }

    @Override
    protected List<Admin> tableToList(ResultSet resultSet) throws SQLException {
        List<Admin> salesmanList = new ArrayList<>();
        // 判断结果集resultSet不为null
        if ( resultSet != null ){
            // 遍历结果集
            while (resultSet.next()) {
                Admin salesman = new Admin();
                // 根据列名, 依次从resultSet对象(方法的参数)中取出每一列的数据, 调用set方法存储到salesman对象中
                salesman.setId(resultSet.getInt("id"));
                salesman.setAdminName(resultSet.getString("admin_name"));
                salesman.setPassWord(resultSet.getString("password"));
                salesman.setIsLock(resultSet.getInt("is_lock"));
                // 把salesman对象存储到集合中
                salesmanList.add(salesman);
            }
        }
        // 返回存储了salesman对象的集合
        return salesmanList;
    }

    @Override
    public int insert(Admin admin) throws SQLException {
        String sql = "INSERT INTO admin(admin_name,password,is_lock) VALUES(?,?,?)";
        // 调用父类的update方法把SQL语句传给父类去执行,返回受影响行数
        // Arrays.asList()方法用于将数组转换为List传给update方法作为第二个参数
        // new Object[]{} 创建一个Object类型的数组, 因为有的数据是字符串, 有的是数字, 有的是浮点数
        int rows = super.update(sql, Arrays.asList(new Object[] {admin.getAdminName(),admin.getPassWord(),1
               }));
        // 在方法return之前,关闭数据库连接
        // 返回这个受影响行数
        return rows;
    }

    @Override
    public int deleteById(Integer adminId) throws SQLException {
        String sql = "DELETE FROM admin WHERE id = ?";
        // 这条SQL语句只有一个? List里面只有一个salesmanId
        int rows = super.update(sql, Arrays.asList(new Object[] {adminId}));
        // 把数据库返回的受影响行数作为这个方法的返回值
        return rows;
    }

    @Override
    public int updateByOne(Admin admin) throws SQLException {
        List<Object> sqlParams = new ArrayList<>();
        // 每次修改都必会更新modify_date列

        if (admin.getAdminName() != null) {
            String sql = "UPDATE admin SET admin_name=? ";
            // 如果修改密码, 把密码添加为sql参数
            sqlParams.add(admin.getAdminName());
            sql += " WHERE id = ?";
            // 把id添加为sql参数
            sqlParams.add(admin.getId());
            // 调用父类update方法, 传递sql语句和sql参数集合
            int rows = super.update(sql, sqlParams);
            return rows;
        }
        if (admin.getPassWord() != null) {
            String sql = "UPDATE admin SET password=? ";
            sqlParams.add(admin.getPassWord());
            sql += " WHERE id = ?";
            // 把id添加为sql参数
            sqlParams.add(admin.getId());
            // 调用父类update方法, 传递sql语句和sql参数集合
            int rows = super.update(sql, sqlParams);
            return rows;
        }

    return 0;
    }

    @Override
    public Admin selectOneById(Integer adminId) throws SQLException {
        String sql = "SELECT id,admin_name,password,is_lock" +
                "  FROM admin WHERE id = ?";
        Admin salesman = (Admin) super.queryOne(sql, Arrays.asList(new Object[]{adminId}));

        return salesman;
    }

    @Override
    public Admin selectOneByName(String adminName) throws SQLException {
        String sql = "SELECT id,admin_name,password,is_lock" +
                " FROM admin WHERE admin_name = ?";
        Admin salesman = (Admin) super.queryOne(sql, Arrays.asList(new Object[]{adminName}));
        return salesman;
    }

    @Override
    public int count(Admin admin) throws SQLException {
        String sql = "SELECT COUNT(*) FROM admin "; // 因为下面要拼接, SQL后面留有空格
        String where = "WHERE 1=1 "; // 拼接where条件的字符串 1=1是为了方便拼接AND
        List<Object> sqlParams = new ArrayList<>();
        if (admin.getAdminName() != null) {
            where += " AND admin_name LIKE CONCAT('%', ?, '%') "; // 拼接根据业务员姓名模糊查询条件 SQL后面留有空格
            sqlParams.add(admin.getAdminName());
        }else{
            return 0;//若未输入名字返回0
        }

        sql += where;
        int count = super.count(sql, sqlParams);

        return count;
    }

    @Override
    public List<Admin> selectList(Admin admin) throws SQLException {
        String sql = "SELECT id,admin_name,password,is_lock From admin "; // 因为下面要拼接, SQL后面留有空格
        String where = "WHERE 1=1 "; // 拼接where条件的字符串 1=1是为了方便拼接AND
        List<Object> sqlParams = new ArrayList<>();
        if (admin.getAdminName() != null) {
            where += "AND admin_name LIKE CONCAT('%', ?, '%') "; // 拼接根据业务员姓名模糊查询条件 SQL后面留有空格
            sqlParams.add(admin.getAdminName());
        }

        sql += where;
        List<Admin>  list = (List<Admin>) super.query(sql, sqlParams);
        return list;
    }
}
