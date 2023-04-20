package com.iweb.dao;

import com.iweb.dao.impl.RentRecordDaoImpl;
import com.iweb.entity.RentRecord;
import com.iweb.entity.query.RentRecordQuery;
import com.iweb.entity.vo.RentRecordVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IRentRecordDao {
    int insert (RentRecord rentRecord) throws SQLException;
    int deleteById (Integer rentRecordId) throws SQLException;
    int updateById (RentRecord rentRecord) throws SQLException;
    RentRecord selectOne(Integer rentRecordId) throws SQLException;
    int count(RentRecord rentRecord) throws SQLException;
    List<RentRecordVO> selectList(RentRecordQuery query) throws SQLException;

    static IRentRecordDao getInstance(Connection conn) {
        return new RentRecordDaoImpl(conn);
    }
}
