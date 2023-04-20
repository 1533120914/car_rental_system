package com.iweb.controller;
import com.iweb.entity.Salesman;
import com.iweb.entity.User;
import com.iweb.service.IUserService;
import com.iweb.util.DateUtil;
import com.iweb.util.LateFiveSecondUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;


public class UserController {
    static Scanner scan = new Scanner(System.in);
     public static User user=null;
    IUserService userService=IUserService.getInstance();

    public void unLockAccount() {
        System.out.println("Account:");
        String userName = scan.next();
        User user = userService.getOne(userName);
        if ( user == null ){
            System.out.println("账户不存在");
        }else {
            if (user.getIsLock() == 0) {
                System.out.println("账户没有被锁定");
            }else {
                User sm = new User();
                sm.setId(user.getIsLock());
                sm.setIsLock(0);
                userService.updateById(sm);
                System.out.println("账户解锁成功!");
            }
        }
    }

    public boolean  login(String userName) {
        for (int i = 3; i >=0; i--) {
            // 把输入的业务员姓名作为参数传给下层, 并接收下层返回的业务员对象
            User user = userService.getOne(userName);
            // 判断业务员是否存在
            if (user == null || user.getId() == null) {
                System.out.println("用户不存在");
                return false;
            } else if (user.getIsLock() == 1) {
                System.out.println("用户已锁定，请联系管理员");
                LateFiveSecondUtil.lateTime();
                return false;
            } else {
                System.out.println("请输入密码");
                if (user.getPassword().equals(scan.next())) {
                    System.out.println("密码正确,登录成功");
                    // 将业务员对象保存起来,方便后续的功能知道当前是哪个业务员登录的
                    UserController.user = user;
                    return true;
                } else {
                    System.out.println("密码错误,登录失败!");

                    if (i == 0) {
                        // 创建一个用于传给更新方法的Salesman对象
                        User sm = new User();
                        sm.setId(user.getId()); // id作为更新条件
                        sm.setIsLock(1); // 更新isLock列
                        userService.updateById(sm);
                        System.out.println(userName + "账户已被锁定");
                        LateFiveSecondUtil.lateTime();
                        continue;
                    }
                    System.out.println("剩余" + i + "次机会");
                }
            }
        }
        return false;
    }
    public void save(){
    //创建一个用户对象
        User user=new User();
        System.out.println("输入用户姓名:");
        user.setUserName(scan.next());
        System.out.println("请输入登录密码:");
        user.setPassword(scan.next());
        System.out.println("请输入联系电话");
        user.setContact(scan.next());
        System.out.println("请输入电子邮箱");
        user.setEmail(scan.next());
        user.setState(0);//新添加的用户是0
        user.setCreateDate(DateUtil.localDateTimeToString(LocalDateTime.now()));
        //用户建立创建日期为dateutil类型，localdatetime转字符串，当前时间
        if(userService.save(user)){
            System.out.println("用户添加成功!");
        }
        else System.out.println("用户添加失败");
    }
    public void remove(){
        System.out.println("请输入你要删除的用户id：");
        Integer userId =scan.nextInt();
        User user=new User();
        user.setId(userId);
        user.setState(1);
        if(userService.updateById(user)){
            System.out.println("删除ID为"+userId+"的用户成功!");
        }else{
            System.out.println("删除ID为" + userId + "的用户失败");
        }
    }
    public void modify() {
        // 被更新的用户对象
        User user = new User();
        System.out.println("输入用户名id:");
        user.setId(scan.nextInt());
        System.out.println("是否用户名(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入新的用户名:");
            user.setUserName(scan.next());
        }
        System.out.println("是否输入通过密码(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入新的密码:");
            user.setPassword(scan.next());
        }
        System.out.println("是否更新联系方式(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入新的联系方式:");
            user.setContact(scan.next());
        }
        System.out.println("是否更新用户邮箱(y/n)?");
        if (scan.next().equals("y")) {
            // 0是未离职, 1是已离职
            user.setEmail(scan.next());
        }
        System.out.println("是否更新用户状态(y/n)?");
        if (scan.next().equals("y")) {
            // 0是未离职, 1是已离职
            user.setState(scan.nextInt());
        }
        if(userService.updateById(user)) {
            System.out.println("更新成功!");
        }else {
            System.out.println("更新失败!");
        }
    }
    //查询一个用户的详细的方法
public void detail(){
User user=null;//这是为了确保在后续代码中对该对象的引用不会在未经初始化的情况下出现编译器错误。
    System.out.println("是否要通过用户id来查询(y/n)？");
    if(scan.next().equals("y")){
        System.out.println("请输入你需要查询的id");
        Integer id=scan.nextInt();
        user=userService.getOne(id);
    }else{
        System.out.println("是否通过用户姓名来查询(y/n)");
        if(scan.next().equals("y")){
            System.out.println("请输入你需要查询的用户姓名");
          user=  userService.getOne(scan.next());
        }
    }
    if(user==null){
        System.out.println("未查询相关数据");
    }
    else{
        System.out.println("----------用户的详细信息为------------");
        System.out.println("编号" + user.getId());
        System.out.println("用户姓名" + user.getUserName());
        System.out.println("登录密码" + user.getPassword());
        System.out.println("联系方式" + user.getContact());
        System.out.println("邮件" + user.getEmail());
        System.out.println("状态" + user.getState());
        System.out.println("创建时间" + user.getCreateDate());
        System.out.println("修改时间" + user.getModifyDate());
    }

}//查询用户列表的方法
public void list(){
    User user = new User();
    System.out.println("是否根据用户姓名模糊查询(y/n)?");
    if (scan.next().equals("y")) {
        System.out.println("输入要查询的用户姓名(支持模糊查询):");
        user.setUserName(scan.next());
    }
    System.out.println("是否根据用户联系方式查询(y/n)?");
    if (scan.next().equals("y")) {
        System.out.println("输入要查询的用户联系方式:");
        user.setContact(scan.next());
    }
    System.out.println("是否根据用户电子邮件查询(y/n)?");
    if (scan.next().equals("y")) {
        System.out.println("输入要查询的用户电子邮件:");
        user.setEmail(scan.next());
    }

    System.out.println("是否根据用户状态查询(y/n)?");
    if (scan.next().equals("y")) {
        System.out.println("输入要查询的用户状态:(0是在职,1是离职)");
        user.setState(scan.nextInt());
    }
    System.out.println("根据查询条件,共查出" + userService.count(user) + "行数据");
    List<User> list = userService.list(user);
    if( list == null || list.size() == 0) {
        System.out.println("没有数据!");
    }else {
        for (User u : list) {
            System.out.print(u.getId());
            System.out.print("\t");
            System.out.print(u.getUserName());
            System.out.print("\t");
            System.out.print(u.getContact());
            System.out.print("\t");
            System.out.print(u.getEmail());
            System.out.print("\t");
            System.out.print(u.getState() == 0 ? "买了" : "未买");
            System.out.print("\n");
        }
    }

}
}
