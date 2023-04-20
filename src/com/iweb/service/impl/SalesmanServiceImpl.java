package com.iweb.service.impl;

import com.iweb.dao.ISalesmanDao;
import com.iweb.entity.Salesman;
import com.iweb.service.ISalesmanService;
import com.iweb.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SalesmanServiceImpl implements ISalesmanService {
    @Override
    public boolean save(Salesman salesman) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            ISalesmanDao salesmanDao = ISalesmanDao.getInstance(conn);
            // 使用salesmanDao调用insert()方法,并把异常捕捉处理,出异常程序不会原地退出
            return salesmanDao.insert(salesman) == 1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public boolean removeById(Integer salesmanId) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            ISalesmanDao salesmanDao = ISalesmanDao.getInstance(conn);
            return salesmanDao.deleteById(salesmanId) == 1 ? true: false;
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public boolean updateById(Salesman salesman) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            ISalesmanDao salesmanDao = ISalesmanDao.getInstance(conn);
            return salesmanDao.updateById(salesman) >= 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public Salesman getOne(Integer salesmanId) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            ISalesmanDao salesmanDao = ISalesmanDao.getInstance(conn);
            return salesmanDao.selectOne(salesmanId);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public Salesman getOne(String saleName) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            ISalesmanDao salesmanDao = ISalesmanDao.getInstance(conn);
            return salesmanDao.selectOne(saleName);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public int count(Salesman salesman) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            ISalesmanDao salesmanDao = ISalesmanDao.getInstance(conn);
            return salesmanDao.count(salesman);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return 0;
        } finally {
            DBUtil.close(conn);
        }
    }

    @Override
    public List<Salesman> list(Salesman salesman) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            ISalesmanDao salesmanDao = ISalesmanDao.getInstance(conn);
            return salesmanDao.selectList(salesman);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }
}
