package com.iweb.dao.impl;

import com.iweb.dao.BaseDao;
import com.iweb.dao.IRentRecordDao;
import com.iweb.entity.RentRecord;
import com.iweb.entity.query.RentRecordQuery;
import com.iweb.entity.vo.RentRecordVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RentRecordDaoImpl extends BaseDao implements IRentRecordDao {
    public RentRecordDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    protected Object rowToObject(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    protected Object tableToList(ResultSet resultSet) throws SQLException {
        List<RentRecordVO> list = null;
        if( resultSet != null) {
            list = new ArrayList<>();
            while (resultSet.next()) {
                RentRecordVO vo = new RentRecordVO();
                vo.setId(resultSet.getInt("id"));
                vo.setCarName(resultSet.getString("car_name"));
                vo.setUsername(resultSet.getString("username"));
                vo.setSaleName(resultSet.getString("sale_name"));
                vo.setRentDays(resultSet.getInt("rent_days"));
                vo.setPrepay(resultSet.getDouble("prepay"));
                vo.setState(resultSet.getInt("state"));
                vo.setCreateDate(resultSet.getString("create_date"));
                vo.setModifyDate(resultSet.getString("modify_date"));
                list.add(vo);
            }
        }
        return list;
    }

    @Override
    public int insert(RentRecord rentRecord) throws SQLException {
        String sql = "INSERT INTO rent_record (car_id, user_id, salesman_id,rent_days,prepay,state,create_date) values(?,?,?,?,?,?,?)";
        return super.update(sql, Arrays.asList(new Object[] {rentRecord.getCarId(), rentRecord.getUserId(), rentRecord.getSalementId()
                , rentRecord.getRentDays(),rentRecord.getPrepay(),rentRecord.getState(),rentRecord.getCreateDate()}));
    }

    @Override
    public int deleteById(Integer rentRecordId) throws SQLException {
        return 0;
    }

    @Override
    public int updateById(RentRecord rentRecord) throws SQLException {
        return 0;
    }

    @Override
    public RentRecord selectOne(Integer rentRecordId) throws SQLException {
        return null;
    }

    @Override
    public int count(RentRecord rentRecord) throws SQLException {
        return 0;
    }

    @Override
    public List<RentRecordVO> selectList(RentRecordQuery query) throws SQLException {
        // 连表查询所有的租赁记录的SQL语句
        String sql = "SELECT r.id,c.car_name,u.username,s.sale_name,r.rent_days," +
                "r.prepay,r.state,DATE_FORMAT(r.create_date,'%Y-%m-%d %T') AS create_date,DATE_FORMAT(r.modify_date,'%Y-%m-%d %T') AS modify_date" +
                "  FROM " +
                "rent_record AS r " +
                "INNER JOIN car AS c" +
                "  ON r.car_id=c.id" +
                "  INNER JOIN user AS u" +
                "  ON r.user_id=u.id" +
                "  INNER JOIN salesman AS s" +
                "  ON r.salesman_id=s.id ";
        // 定义查询条件字符串
        String where = "where 1=1 ";
        // 定义存储查询参数的List
        List<Object> sqlParams = new ArrayList<>();
        if (query.getSalesmanId() != null) {
            where += " AND s.id=? ";
            sqlParams.add(query.getSalesmanId());
        }
        if (query.getUserId() != null) {
            where += " AND u.id=? ";
            sqlParams.add(query.getUserId());
        }
        if (query.getCarName() != null) {
            where += " AND c.car_name like CONCAT('%',?,'%') ";
            sqlParams.add(query.getCarName());
        }
        if (query.getSaleName() != null) {
            where += " AND s.sale_name like CONCAT('%',?,'%') ";
            sqlParams.add(query.getSaleName());
        }
        if (query.getUsername() != null) {
            where += " AND u.user_name like CONCAT('%',?,'%') ";
            sqlParams.add(query.getUsername());
        }
        if (query.getState() != null) {
            where += " AND r.state=? ";
            sqlParams.add(query.getState());
        }
        if (query.getSmallDateTime() != null) {
            where += " AND (r.create_date between ? and ?) ";
            sqlParams.add(query.getSmallDateTime());
            sqlParams.add(query.getBigDateTime());
        }
        // 拼接where
        sql += where;
        // 把sql语句和sqlParams传给父类执行, 并接受返回的list
        List<RentRecordVO> list = (List<RentRecordVO>) super.query(sql, sqlParams);
        return list;
    }
}
