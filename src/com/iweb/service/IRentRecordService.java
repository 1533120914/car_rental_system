package com.iweb.service;

import com.iweb.entity.RentRecord;
import com.iweb.entity.query.RentRecordQuery;
import com.iweb.entity.vo.RentRecordVO;
import com.iweb.service.impl.RentRecordServiceImpl;

import java.util.List;

public interface IRentRecordService {

    boolean save(RentRecord rentRecord);
    boolean removeById(Integer rentRecordId);
    boolean updateById(RentRecord rentRecord);
    RentRecord getOne(Integer rentRecordId);
    int count(RentRecord rentRecord);
    List<RentRecordVO> list(RentRecordQuery query);

    static IRentRecordService getInstance() {
        return new RentRecordServiceImpl();
    }
}
