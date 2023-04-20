package com.iweb.service.impl;

import com.iweb.dao.ICarDao;
import com.iweb.dao.IRentRecordDao;
import com.iweb.dao.ISalesmanDao;
import com.iweb.dao.IUserDao;
import com.iweb.entity.Car;
import com.iweb.entity.RentRecord;
import com.iweb.entity.User;
import com.iweb.entity.query.RentRecordQuery;
import com.iweb.entity.vo.RentRecordVO;
import com.iweb.service.IRentRecordService;
import com.iweb.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RentRecordServiceImpl implements IRentRecordService {

    @Override
    public boolean save(RentRecord rentRecord) {
        Connection conn = null;
        try {
            // 开启数据库连接, 并关闭自动提交 === 开启事务
            conn = DBUtil.open(false);
            // 连接对象conn传给carDao
            ICarDao carDao = ICarDao.getInstance(conn);
            // 连接对象conn传给rentRecordDao (此时两个dao对象共享一个conn连接对象)
            IRentRecordDao rentRecordDao = IRentRecordDao.getInstance(conn);
            IUserDao userDao=IUserDao.getInstance(conn);
            User user=new User();
            user.setId(rentRecord.getUserId());
            user.setState(1);
            Car car = new Car();
            car.setId(rentRecord.getCarId());
            car.setState(1);
            // 更新汽车状态
            userDao.updateById(user);
            carDao.updateById(car);
            // 插入租赁记录
            rentRecordDao.insert(rentRecord);
            // 没有出异常 - 提交事务, 出异常 - 进入catch回滚 (如果连接意外关闭,会自动回滚事务)
            conn.commit();
            // 返回true
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // 回滚事务
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            // 返回false
            return false;
        } finally {
            // 关闭连接
            DBUtil.close(conn);
        }
    }

    @Override
    public boolean removeById(Integer rentRecordId) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IRentRecordDao salesmanDao = IRentRecordDao.getInstance(conn);
            return salesmanDao.deleteById(rentRecordId) == 1 ? true: false;
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public boolean updateById(RentRecord rentRecord) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IRentRecordDao salesmanDao = IRentRecordDao.getInstance(conn);
            return salesmanDao.updateById(rentRecord) >= 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public RentRecord getOne(Integer rentRecordId) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IRentRecordDao salesmanDao = IRentRecordDao.getInstance(conn);
            return salesmanDao.selectOne(rentRecordId);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public int count(RentRecord rentRecord) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IRentRecordDao salesmanDao = IRentRecordDao.getInstance(conn);
            return salesmanDao.count(rentRecord);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return 0;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public List<RentRecordVO> list(RentRecordQuery query) {
        Connection conn = null;
        try {

            conn = DBUtil.open();
            IRentRecordDao rentRecordDao = IRentRecordDao.getInstance(conn);
            return rentRecordDao.selectList(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            DBUtil.close(conn);
        }
    }
}
