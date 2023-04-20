package com.iweb.service;

import com.iweb.entity.Car;
import com.iweb.service.impl.CarServiceImpl;

import java.util.List;

public interface ICarService {
    boolean save(Car car);
    boolean removeById(Integer carId);
    boolean updateById(Car car);
    Car getOne(Integer carId);
    Car getOne(String carName);
    int count(Car car);
    List<Car> list(Car car);
    static ICarService getInstance() {//用于根据条件获取用户列表。该方法会返回一个包含用户对象的列表。
        return new CarServiceImpl();
    }

}
