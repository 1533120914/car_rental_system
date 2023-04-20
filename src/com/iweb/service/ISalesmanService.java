package com.iweb.service;

import com.iweb.entity.Salesman;
import com.iweb.entity.User;
import com.iweb.service.impl.SalesmanServiceImpl;

import java.util.List;

public interface ISalesmanService {
    boolean save(Salesman salesman);

    boolean removeById(Integer salesmanId);

    boolean updateById(Salesman salesman);
    Salesman getOne(Integer salesmanId);
    Salesman getOne(String saleName);
    int count(Salesman salesman);
    List<Salesman> list(Salesman salesman);
    static ISalesmanService getInstance() {//用于根据条件获取用户列表。该方法会返回一个包含用户对象的列表。
        return new SalesmanServiceImpl();
    }

}
