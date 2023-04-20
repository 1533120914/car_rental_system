package com.iweb.dao.impl;
import com.iweb.dao.BaseDao;
import com.iweb.dao.IBrandDao;
import com.iweb.entity.Brand;
import com.iweb.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrandDaoImpl extends BaseDao implements IBrandDao {
    public BrandDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    protected Brand rowToObject(ResultSet resultSet) throws SQLException {
        // 创建一个brand对象
        Brand brand = null;
        if ( resultSet != null && resultSet.next()) {
            brand = new Brand();
            // 根据列名, 依次从resultSet对象(方法的参数)中取出每一列的数据, 调用set方法存储到brand对象中
            brand.setId(resultSet.getInt("id"));
            brand.setBrandName(resultSet.getString("brand_name"));

        }
        // 返回这个brand对象
        return brand;
    }

    @Override
    protected List<Brand> tableToList(ResultSet resultSet) throws SQLException {
        // 创建一个用于存储brand对象的ArrayList
        List<Brand> brandList = new ArrayList<>();
        // 判断结果集resultSet不为null
        if ( resultSet != null ){
            // 遍历结果集
            while (resultSet.next()) {
                Brand brand = new Brand();
                // 根据列名, 依次从resultSet对象(方法的参数)中取出每一列的数据, 调用set方法存储到brand对象中
                brand.setId(resultSet.getInt("id"));
                brand.setBrandName(resultSet.getString("brand_name"));

                // 把brand对象存储到集合中
                brandList.add(brand);
            }
        }
        // 返回存储了brand对象的集合
        return brandList;
    }

    @Override
    public int insert(Brand brand) throws SQLException {
        // 调用父类open()方法创建数据库连接, super可以省略
        // 编写添加一个业务员的SQL语句
        // 注意主键id自增长,不需要指定, modify_date是修改日期,在插入的时候不需要指定
        String sql = "INSERT INTO brand(brand_name) VALUES(?) ";
        // 调用父类的update方法把SQL语句传给父类去执行,返回受影响行数
        // Arrays.asList()方法用于将数组转换为List传给update方法作为第二个参数
        // new Object[]{} 创建一个Object类型的数组, 因为有的数据是字符串, 有的是数字, 有的是浮点数
        int rows = super.update(sql, Arrays.asList(new Object[] {brand.getBrandName()}));
        // 在方法return之前,关闭数据库连接
        DBUtil.close(conn);
        // 返回这个受影响行数
        return rows;
    }



    @Override
    public int deleteById(Integer brandId) throws SQLException {
        DBUtil.open();
        String sql = "DELETE FROM brand WHERE id = ?";
        // 这条SQL语句只有一个? List里面只有一个brandId
        int rows = super.update(sql, Arrays.asList(new Object[] {brandId}));
        DBUtil.close(conn);
        // 把数据库返回的受影响行数作为这个方法的返回值
        return rows;
    }
    @Override
    public int updateById(Brand brand) throws SQLException {
        // 注意修改功能的实现要做成动态的, 可以修改任意一个列或者多个列
        // SQL语句要动态拼接
        DBUtil.open();
        // 用于存储sql参数的集合
        List<Object> sqlParams = new ArrayList<>();
        String sql = "UPDATE brand SET  ";
        if ( brand.getBrandName() != null) {            sql += "brand_name=? ";

            sqlParams.add(brand.getBrandName());
        }else{
            System.out.println("未输入品牌名字");
            return 0;//若没输入则返回0行受影响
        }

        // 拼接WHERE 条件
        sql += " WHERE id = ?";
        // 把id添加为sql参数
        sqlParams.add(brand.getId());
        // 调用父类update方法, 传递sql语句和sql参数集合
        int rows = super.update(sql, sqlParams);
        DBUtil.close(conn);
        return rows;
    }



    @Override
    public Brand selectOne(Integer brandId) throws SQLException {
        DBUtil.open();
        String sql = "SELECT id,brand_name FROM brand WHERE  id=? ";
        Brand brand = (Brand) super.queryOne(sql, Arrays.asList(new Object[]{brandId}));
        DBUtil.close(conn);
        return brand;
    }

    @Override
    public Brand selectOne(String brandName) throws SQLException {
        DBUtil.open();
        String sql = "SELECT id,brand_name" +
                " FROM brand WHERE brand_name = ?";
        Brand brand = (Brand) super.queryOne(sql, Arrays.asList(new Object[]{brandName}));
        DBUtil.close(conn);
        return brand;
    }

    @Override
    public int count(Brand brand) throws SQLException {
        DBUtil.open();
        String sql = "SELECT COUNT(*) FROM brand "; // 因为下面要拼接, SQL后面留有空格
        String where = "WHERE 1=1 "; // 拼接where条件的字符串 1=1是为了方便拼接AND
        List<Object> sqlParams = new ArrayList<>();
        if (brand.getBrandName() != null) {
            where += "AND brand_name LIKE CONCAT('%', ?, '%') "; // 拼接根据业务员姓名模糊查询条件 SQL后面留有空格
            sqlParams.add(brand.getBrandName());
        }
        sql += where;
        int count = super.count(sql, sqlParams);
        DBUtil.close(conn);
        return count;
    }

    @Override
    public List<Brand> selectList(Brand brand) throws SQLException {
        DBUtil.open();
        String sql = "SELECT id,brand_name FROM brand "; // 因为下面要拼接, SQL后面留有空格
        String where = "WHERE 1=1 "; // 拼接where条件的字符串 1=1是为了方便拼接AND
        List<Object> sqlParams = new ArrayList<>();
        if (brand.getBrandName() != null) {
            where += "AND brand_name LIKE CONCAT('%', ?, '%') "; // 拼接根据业务员姓名模糊查询条件 SQL后面留有空格
            sqlParams.add(brand.getBrandName());
        }
        sql += where;
        List<Brand>  list = (List<Brand>) super.query(sql, sqlParams);
        DBUtil.close(conn);
        return list;
    }


}
