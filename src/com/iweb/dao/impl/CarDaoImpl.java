package com.iweb.dao.impl;

import com.iweb.dao.BaseDao;
import com.iweb.dao.ICarDao;
import com.iweb.entity.Car;
import com.iweb.entity.Salesman;
import com.iweb.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarDaoImpl extends BaseDao implements ICarDao {
    public CarDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    protected Car rowToObject(ResultSet resultSet) throws SQLException {
        // 创建一个Salesman对象
        Car car = null;
        if ( resultSet != null && resultSet.next()) {
            car = new Car();
            // 根据列名, 依次从resultSet对象(方法的参数)中取出每一列的数据, 调用set方法存储到car对象中
            car.setId(resultSet.getInt("id"));
            car.setCarName(resultSet.getString("car_name"));
            car.setBrandId(resultSet.getInt("brand_id"));
            car.setCarType(resultSet.getString("car_type"));
            car.setSeatNum(resultSet.getInt("seat_num"));
            car.setAcceCapa(resultSet.getDouble("acce_capa"));
            car.setOilWear(resultSet.getDouble("oil_wear"));
            car.setDailyRent(resultSet.getDouble("daily_rent"));
            car.setState(resultSet.getInt("state"));
            // 字符串转为LocalDateTime
            System.out.println(resultSet.getString("create_date"));
            car.setCreateDate(resultSet.getString("create_date"));
            car.setModifyDate(resultSet.getString("modify_date"));
        }
        // 返回这个car对象
        return car;
    }

    @Override
    protected List<Car> tableToList(ResultSet resultSet) throws SQLException {
        // 创建一个用于存储Salesman对象的ArrayList
        List<Car> salesmanList = new ArrayList<>();
        // 判断结果集resultSet不为null
        if ( resultSet != null ){
            // 遍历结果集
            while (resultSet.next()) {
                Car car = new Car();
                // 根据列名, 依次从resultSet对象(方法的参数)中取出每一列的数据, 调用set方法存储到salesman对象中
                car.setId(resultSet.getInt("id"));
                car.setCarName(resultSet.getString("car_name"));
                car.setBrandId(resultSet.getInt("brand_id"));
                car.setCarType(resultSet.getString("car_type"));
                car.setSeatNum(resultSet.getInt("seat_num"));
                car.setAcceCapa(resultSet.getDouble("acce_capa"));
                car.setState(resultSet.getInt("state"));
                car.setOilWear(resultSet.getDouble("oil_wear"));
                car.setDailyRent(resultSet.getDouble("daily_rent"));
                // 字符串转为LocalDateTime
                System.out.println(resultSet.getString("create_date"));
                car.setCreateDate(resultSet.getString("create_date"));
                car.setModifyDate(resultSet.getString("modify_date"));
                salesmanList.add(car);
            }
        }
        // 返回存储了salesman对象的集合
        return salesmanList;
    }


    @Override
    public int insert(Car car) throws SQLException {
        String sql = "INSERT INTO salesman(car_name,brand_id,car_type,seat_num,acce_capa,oil_wear,daily_rent,state,create_date) VALUES(?,?,?,?,?,?,?)";
        // 调用父类的update方法把SQL语句传给父类去执行,返回受影响行数
        // Arrays.asList()方法用于将数组转换为List传给update方法作为第二个参数
        // new Object[]{} 创建一个Object类型的数组, 因为有的数据是字符串, 有的是数字, 有的是浮点数
        int rows = super.update(sql, Arrays.asList(new Object[] {car.getCarName(),car.getBrandId(),
                car.getBrandId(), car.getCarType(),car.getSeatNum(),car.getAcceCapa(),car.getOilWear(),car.getDailyRent(),car.getState()}));
        // 在方法return之前,关闭数据库连接
        // 返回这个受影响行数
        return rows;
    }

    @Override
    public int deleteById(Integer carId) throws SQLException {
        String sql = "DELETE FROM car WHERE id = ?";
        // 这条SQL语句只有一个? List里面只有一个salesmanId
        int rows = super.update(sql, Arrays.asList(new Object[] {carId}));
        // 把数据库返回的受影响行数作为这个方法的返回值
        return rows;
    }

    @Override
    public int updateById(Car car) throws SQLException {
        String sql = "update car set modify_date=NOW() ";
        List<Object> sqlParams = new ArrayList<>();
        // 如果状态不为null,就更新状态
        if( car.getState() != null) {
            sql += ",state=? ";
            sqlParams.add(car.getState());
        }
        // 如果还需要更新其他字段, 继续判断
        // 此处先略过

        // 拼接where条件
        sql += "where id=? ";
        sqlParams.add(car.getId());
        return super.update(sql, sqlParams);
    }

    @Override
    public Car selectOne(Integer carId) throws SQLException {
        String sql = "SELECT id,car_name,brand_id,car_type,seat_num,acce_capa,oil_wear,daily_rent,state,DATE_FORMAT(create_date,'%Y-%m-%d %T') AS create_date,DATE_FORMAT(modify_date,'%Y-%m-%d %T') AS modify_date" +
                "  FROM car WHERE id = ?";
        Car salesman = (Car) super.queryOne(sql, Arrays.asList(new Object[]{carId}));

        return salesman;
    }

    @Override
    public Car selectOne(String carName) throws SQLException {
        DBUtil.open();
        String sql = "SELECT id,car_name,brand_id,car_type,seat_num,acce_capa,oil_wear,daily_rent,state,DATE_FORMAT(create_date,'%Y-%m-%d %T') AS create_date,DATE_FORMAT(modify_date,'%Y-%m-%d %T') AS modify_date" +
                "  FROM car WHERE car_name = ?";
        Car salesman = (Car) super.queryOne(sql, Arrays.asList(new Object[]{carName}));
        DBUtil.close(conn);
        return salesman;
    }

    @Override
    public int count(Car car) throws SQLException {
        String sql = "SELECT COUNT(*) FROM car "; // 因为下面要拼接, SQL后面留有空格
        String where = "WHERE 1=1 "; // 拼接where条件的字符串 1=1是为了方便拼接AND
        List<Object> sqlParams = new ArrayList<>();
        if (car.getCarName() != null) {
            where += "AND car_name LIKE CONCAT('%', ?, '%') "; // 拼接根据业务员姓名模糊查询条件 SQL后面留有空格
            sqlParams.add(car.getCarName());
        }
        if (car.getCarType() != null) {
            where += "AND car_type=? "; // 拼接根据业务员性别查询条件 SQL后面留有空格
            sqlParams.add(car.getCarType());
        }
        if (car.getSeatNum() != null) {
            where += "AND seat_num=? "; // 拼接根据业务员电话号码查询条件 SQL后面留有空格
            sqlParams.add(car.getSeatNum());
        }
        if (car.getAcceCapa() != null) {
            where += "AND acce_capa=? "; // 拼接根据业务员状态查询条件 SQL后面留有空格
            sqlParams.add(car.getAcceCapa());
        }
        if (car.getOilWear() != null) {
            where += "AND oil_wear=? "; // 拼接根据业务员状态查询条件 SQL后面留有空格
            sqlParams.add(car.getOilWear());
        }
        sql += where;
        int count = super.count(sql, sqlParams);

        return count;
    }

    @Override
    public List<Car> selectList(Car car) throws SQLException {
        String sql = "SELECT id,car_name,brand_id,car_type,seat_num,acce_capa,state,oil_wear,daily_rent,DATE_FORMAT(create_date,'%Y-%m-%d %T') AS create_date,DATE_FORMAT(modify_date,'%Y-%m-%d %T') AS modify_date FROM car "; // 因为下面要拼接, SQL后面留有空格
        String where = "WHERE 1=1 "; // 拼接where条件的字符串 1=1是为了方便拼接AND
        List<Object> sqlParams = new ArrayList<>();
        if (car.getCarName() != null) {
            where += "AND car_name LIKE CONCAT('%', ?, '%') "; // 拼接根据业务员姓名模糊查询条件 SQL后面留有空格
            sqlParams.add(car.getCarName());
        }
        if (car.getCarType() != null) {
            where += "AND car_type LIKE CONCAT('%', ?, '%') "; // 拼接根据业务员姓名模糊查询条件 SQL后面留有空格
            sqlParams.add(car.getCarType());
        }
        if (car.getSeatNum() != null) {
            where += "AND seat_num=? "; // 拼接根据业务员性别查询条件 SQL后面留有空格
            sqlParams.add(car.getSeatNum());
        }
        if (car.getAcceCapa() != null) {
            where += "AND acce_capa=? "; // 拼接根据业务员电话号码查询条件 SQL后面留有空格
            sqlParams.add(car.getAcceCapa());
        }
        if (car.getOilWear() != null) {
            where += "AND oil_wear=? "; // 拼接根据业务员状态查询条件 SQL后面留有空格
            sqlParams.add(car.getOilWear());
        }
        if (car.getDailyRent() != null) {
            where += "AND daily_rent=? "; // 拼接根据业务员状态查询条件 SQL后面留有空格
            sqlParams.add(car.getDailyRent());
        }
        sql += where;
        List<Car>  list = (List<Car>) super.query(sql, sqlParams);
        return list;
    }
}
