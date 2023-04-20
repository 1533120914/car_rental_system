package com.iweb.service.impl;

import com.iweb.dao.IAdminDao;
import com.iweb.dao.ISalesmanDao;
import com.iweb.entity.Admin;
import com.iweb.service.IAdminService;
import com.iweb.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements IAdminService {
    @Override
    public boolean save(Admin admin) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IAdminDao salesmanDao = IAdminDao.getInstance(conn);
            // 使用salesmanDao调用insert()方法,并把异常捕捉处理,出异常程序不会原地退出
            return salesmanDao.insert(admin) == 1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public boolean removeById(Integer adminId) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IAdminDao salesmanDao = IAdminDao.getInstance(conn);
            return salesmanDao.deleteById(adminId) == 1 ? true: false;
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public boolean updateById(Admin admin) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IAdminDao salesmanDao = IAdminDao.getInstance(conn);
            return salesmanDao.updateByOne(admin) >= 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public Admin getOne(Integer adminId) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IAdminDao salesmanDao = IAdminDao.getInstance(conn);
            return salesmanDao.selectOneById(adminId);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public Admin getOne(String adminName) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IAdminDao salesmanDao = IAdminDao.getInstance(conn);
            return salesmanDao.selectOneByName(adminName);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public int count(Admin admin) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IAdminDao salesmanDao = IAdminDao.getInstance(conn);
            return salesmanDao.count(admin);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return 0;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public List<Admin> list(Admin admin) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IAdminDao salesmanDao = IAdminDao.getInstance(conn);
            return salesmanDao.selectList(admin);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }
    }

