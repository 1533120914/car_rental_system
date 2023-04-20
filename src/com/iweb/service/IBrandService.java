package com.iweb.service;

import com.iweb.entity.Brand;

import com.iweb.service.impl.BrandServiceImpl;

import java.util.List;

public interface IBrandService {
    boolean save(Brand brand);
    boolean removeById(Integer brandId);
    boolean updateById(Brand brand);
    Brand getOne(Integer brandId);
    Brand getOne(String brandName);
    int count(Brand brand);
    List<Brand> list(Brand brand);
    static BrandServiceImpl getInstance() {//用于根据条件获取用户列表。该方法会返回一个包含用户对象的列表。
        return new BrandServiceImpl();
    }

}
