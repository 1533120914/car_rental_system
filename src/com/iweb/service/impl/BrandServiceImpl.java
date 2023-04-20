package com.iweb.service.impl;
import com.iweb.dao.IBrandDao;

import com.iweb.entity.Brand;
import com.iweb.service.IBrandService;
import com.iweb.util.DBUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BrandServiceImpl implements IBrandService {

    public boolean save(Brand brand) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IBrandDao branDao = IBrandDao.getInstance(conn);
            // 使用salesmanDao调用insert()方法,并把异常捕捉处理,出异常程序不会原地退出
            return branDao.insert(brand) == 1 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }

    public boolean removeById(Integer brandId) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IBrandDao brandDao = IBrandDao.getInstance(conn);
            return brandDao.deleteById(brandId) == 1 ? true: false;
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }


    public boolean updateById(Brand brand) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IBrandDao brandDao = IBrandDao.getInstance(conn);
            return brandDao.updateById(brand) >= 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return false;
        } finally {
            DBUtil.close(conn);
        }
    }


    public Brand getOne(Integer brandId) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IBrandDao brandDao = IBrandDao.getInstance(conn);
            return brandDao.selectOne(brandId);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }


    public Brand getOne(String brandName) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IBrandDao brandDao = IBrandDao.getInstance(conn);
            return brandDao.selectOne(brandName);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }


    public int count(Brand brand) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IBrandDao brandDao = IBrandDao.getInstance(conn);
            return brandDao.count(brand);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return 0;
        } finally {
            DBUtil.close(conn);
        }
    }


    public List<Brand> list(Brand brand) {
        Connection conn = null;
        try {
            conn = DBUtil.open();
            IBrandDao brandDao = IBrandDao.getInstance(conn);
            return brandDao.selectList(brand);
        } catch (SQLException e) {
            e.printStackTrace();
            // 出异常返回false
            return null;
        } finally {
            DBUtil.close(conn);
        }
    }
}
