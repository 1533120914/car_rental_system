package com.iweb.service;

import com.iweb.entity.User;
import com.iweb.service.impl.SalesmanServiceImpl;
import com.iweb.service.impl.UserServiceImpl;

import java.util.List;

public interface IUserService {
    boolean updateById(User user);//根据ID更新
    boolean removeById(Integer userId); //根据ID删除
    boolean save(User user);//将对象直接存入
    User getOne(Integer userId);//根据用户ID进行获取单人详细信息
    User getOne(String userName);//根据用户名获取单人详细信息
    int count(User user);//统计用户数量
    List<User> list(User user);//获取用户列表

        //定义了一种获取用户列表的方法。
        // 在实现该接口的类中，需要实现这个方法来实现具体的查询操作。
        // 由于该方法返回一个 List<User> 类型的对象，
        // 因此调用者可以方便地对查询结果进行进一步处理，
        // 例如显示用户信息或进行其他操作。
        // 由于接口是一种约定，因此实现该接口的类必须提供这个方法的实现，以便其他类可以使用该方法来获取用户列表。
        static IUserService getInstance(){//通过此方法可以使其他类调用此端口
            return new UserServiceImpl();
            //这个方法通常被称为 "工厂方法"，因为它的作用是创建并返回一个新的 SalesmanServiceImpl 实例，
            // 以便其他类可以使用它来访问 SalesmanServiceImpl 类中的方法。
            // 由于这个方法是静态的，所以可以通过类名直接调用它，而无需创建 SalesmanServiceImpl 的实例。
            // 这种方法在需要频繁地创建、销毁类的实例时特别有用
            // ，因为它可以将对象的创建和初始化过程封装起来，
            // 使得其他类可以更加方便地使用这些对象。
    }
}
