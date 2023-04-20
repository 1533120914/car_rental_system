package com.iweb.service.impl;
import com.iweb.dao.IUserDao;
import com.iweb.entity.User;
import com.iweb.service.IUserService;
import com.iweb.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements IUserService {

    @Override
    public boolean save(User user) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IUserDao salesmanDao = IUserDao.getInstance(conn);
            // 使用salesmanDao调用insert()方法,并把异常捕捉处理,出异常程序不会原地退出
            return salesmanDao.insert(user) == 1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public boolean removeById(Integer userId) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IUserDao salesmanDao = IUserDao.getInstance(conn);
            return salesmanDao.deleteById(userId) == 1 ? true: false;
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public boolean updateById(User user) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IUserDao salesmanDao = IUserDao.getInstance(conn);
            return salesmanDao.updateById(user) >= 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public User getOne(Integer userId) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IUserDao salesmanDao = IUserDao.getInstance(conn);
            return salesmanDao.selectOne(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public User getOne(String userName) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IUserDao salesmanDao = IUserDao.getInstance(conn);
            return salesmanDao.selectOne(userName);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public int count(User user) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IUserDao salesmanDao = IUserDao.getInstance(conn);
            return salesmanDao.count(user);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return 0;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public List<User> list(User user) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IUserDao salesmanDao = IUserDao.getInstance(conn);
            return salesmanDao.selectList(user);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }
}
