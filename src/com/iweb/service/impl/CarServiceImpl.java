package com.iweb.service.impl;

import com.iweb.dao.ICarDao;
import com.iweb.dao.ISalesmanDao;
import com.iweb.entity.Car;
import com.iweb.service.ICarService;
import com.iweb.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CarServiceImpl implements ICarService {

    @Override
    public boolean save(Car car) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            ICarDao salesmanDao = ICarDao.getInstance(conn);
            // 使用salesmanDao调用insert()方法,并把异常捕捉处理,出异常程序不会原地退出
            return salesmanDao.insert(car) == 1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public boolean removeById(Integer carId) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            ICarDao salesmanDao = ICarDao.getInstance(conn);
            return salesmanDao.deleteById(carId) == 1 ? true: false;
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public boolean updateById(Car car) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            ICarDao salesmanDao = ICarDao.getInstance(conn);
            return salesmanDao.updateById(car) >= 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public Car getOne(Integer carId) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            ICarDao salesmanDao = ICarDao.getInstance(conn);
            return salesmanDao.selectOne(carId);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public Car getOne(String carName) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            ICarDao salesmanDao = ICarDao.getInstance(conn);
            return salesmanDao.selectOne(carName);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public int count(Car car) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            ICarDao salesmanDao = ICarDao.getInstance(conn);
            return salesmanDao.count(car);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return 0;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public List<Car> list(Car car) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            ICarDao salesmanDao = ICarDao.getInstance(conn);
            return salesmanDao.selectList(car);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }
}
