package com.iweb.controller;

import com.iweb.entity.Salesman;
import com.iweb.service.ISalesmanService;
import com.iweb.util.DateUtil;
import com.iweb.util.LateFiveSecondUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
// 业务员操作的控制层
public class SalesmanController {
    public static Salesman salesman = null; // 用户存储登录系统的业务员对象
    static Scanner scan = new Scanner(System.in);
    // 拿到业务员的service对象
    ISalesmanService salesmanService = ISalesmanService.getInstance();

    // 添加一个业务员的方法
    public void save() {
        // 创建一个业务员对象
        Salesman salesman = new Salesman();
        System.out.println("输入业务员姓名:");
        salesman.setSaleName(scan.next()); // 将控制台的输入直接用set方法存储到业务员对象中
        System.out.println("输入业务员密码:");
        salesman.setPassword(scan.next());
        System.out.println("输入业务员性别:");
        salesman.setSex(scan.next());
        System.out.println("输入业务员联系方式:");
        salesman.setContact(scan.next());
        System.out.println("输入业务员基础月薪:");
        salesman.setSal(scan.nextDouble());
        salesman.setState(0); // 新添加的业务员状态默认为0, 表示未离职, 1表示离职
        // 新添加的业务员的创建日期默认是当前日期,日期转换为字符串
        salesman.setCreateDate(DateUtil.localDateTimeToString(LocalDateTime.now()));
        // 使用Service对象调用save()方法, 把存储了输入信息的业务员对象salesman传给save()方法
        if (salesmanService.save(salesman)) {
            System.out.println("添加业务员成功!");
        } else {
            System.out.println("添加业务员失败!");
        }
    }

    // 删除一个业务员的方法
    public void remove() {
        System.out.println("输入要删除的业务员Id:");
        Integer salesmanId = scan.nextInt();
        Salesman salesman = new Salesman();
        salesman.setId(salesmanId);
        salesman.setState(1);
        // 做假删除, 逻辑删除, 被删除的员工改为离职状态
        if (salesmanService.updateById(salesman)) {
            System.out.println("删除Id为" + salesmanId + "的业务员成功!");
        } else {
            System.out.println("删除Id为" + salesmanId + "的业务员失败!");
        }
        /**
         if (salesmanService.removeById(salesmanId)) {
         System.out.println("删除Id为" + salesmanId + "的业务员成功!");
         }else {
         System.out.println("删除Id为" + salesmanId + "的业务员失败!");
         }**/
    }

    // 更新一个业务员的方法
    public void modify() {
        // 被更新的业务员对象
        Salesman salesman = new Salesman();
        System.out.println("输入业务员Id:");
        salesman.setId(scan.nextInt());
        System.out.println("是否更新密码(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入新的密码:");
            salesman.setPassword(scan.next());
        }
        System.out.println("是否更新手机号码(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入新的手机号码:");
            salesman.setContact(scan.next());
        }
        System.out.println("是否更新基础月薪(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入新的基础月薪:");
            salesman.setSal(scan.nextDouble());
        }
        System.out.println("是否更新员工状态为离职(y/n)?");
        if (scan.next().equals("y")) {
            // 0是未离职, 1是已离职
            salesman.setState(1);
        }
        if (salesmanService.updateById(salesman)) {
            System.out.println("更新成功!");
        } else {
            System.out.println("更新失败!");
        }
    }

    // 查询一个业务员详细信息的方法
    public void detail() {
        Salesman salesman = null;
        System.out.println("是否根据业务员id查询(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入业务员id:");
            Integer id = scan.nextInt();
            salesman = salesmanService.getOne(id);
        } else {
            System.out.println("是否根据业务员姓名查询(y/n)?");
            if (scan.next().equals("y")) {
                System.out.println("输入业务员姓名:");
                String saleName = scan.next();
                salesman = salesmanService.getOne(saleName);
            }
        }
        if (salesman == null) {
            System.out.println("未查到相关数据!");
        } else {
            System.out.println("--------业务员的详细信息-------");
            System.out.println("编号:" + salesman.getId());
            System.out.println("姓名:" + salesman.getSaleName());
            System.out.println("密码:" + salesman.getPassword());
            System.out.println("性别:" + salesman.getSex());
            System.out.println("电话:" + salesman.getContact());
            System.out.println("月薪:" + salesman.getSal());
            System.out.println("状态:" + (salesman.getState() == 0 ? "在职" : "离职"));
            System.out.println("创建日期:" + salesman.getCreateDate());
            System.out.println("修改日期:" + salesman.getModifyDate());
        }
    }

    // 查询业务员列表的方法
    public void list() {
        Salesman salesman = new Salesman();
        System.out.println("是否根据业务员姓名模糊查询(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入要查询的业务员姓名(支持模糊查询):");
            salesman.setSaleName(scan.next());
        }
        System.out.println("是否根据业务员性别查询(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入要查询的业务员性别:");
            salesman.setSex(scan.next());
        }
        System.out.println("是否根据业务员电话号码查询(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入要查询的业务员电话号码:");
            salesman.setContact(scan.next());
        }
        System.out.println("是否根据业务员状态查询(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入要查询的业务员状态:(0是在职,1是离职)");
            salesman.setState(scan.nextInt());
        }
        System.out.println("根据查询条件,共查出" + salesmanService.count(salesman) + "行数据");
        List<Salesman> list = salesmanService.list(salesman);
        if (list == null || list.size() == 0) {
            System.out.println("没有数据!");
        } else {
            for (Salesman s : list) {
                System.out.print(s.getId());
                System.out.print("\t");
                System.out.print(s.getSaleName());
                System.out.print("\t");
                System.out.print(s.getPassword());
                System.out.print("\t");
                System.out.print(s.getSex());
                System.out.print("\t");
                System.out.print(s.getContact());
                System.out.print("\t");
                System.out.print(s.getSal());
                System.out.print("\t");
                System.out.print(s.getState() == 0 ? "在职" : "离职");
                System.out.print("\n");
            }
        }
    }

    // 业务员登陆系统的方法
    public boolean  login(String saleName) {

        for (int i = 3; i >=0; i--) {
            // 把输入的业务员姓名作为参数传给下层, 并接收下层返回的业务员对象
            Salesman salesman = salesmanService.getOne(saleName);
            // 判断业务员是否存在
            if (salesman == null || salesman.getId() == null) {
                System.out.println("用户不存在");
                continue;
            } else if (salesman.getIsLock() == 1) {
                System.out.println("用户已锁定，请联系管理员");
                LateFiveSecondUtil.lateTime();
                return false;
            } else {
                System.out.println("请输入密码");
                if (salesman.getPassword().equals(scan.next())) {
                    System.out.println("密码正确,登录成功");
                    // 将业务员对象保存起来,方便后续的功能知道当前是哪个业务员登录的
                    SalesmanController.salesman = salesman;
                    return true;
                } else {
                    System.out.println("密码错误,登录失败!");

                    if (i == 0) {
                        // 创建一个用于传给更新方法的Salesman对象
                        Salesman sm = new Salesman();
                        sm.setId(salesman.getId()); // id作为更新条件
                        sm.setIsLock(1); // 更新isLock列
                        salesmanService.updateById(sm);
                        System.out.println(saleName + "账户已被锁定");
                        LateFiveSecondUtil.lateTime();
                        continue;
                    }
                    System.out.println("剩余" + i + "次机会");
                }
            }
        }
            return false;
        }



    // 解锁业务员账户方法
    public void unLockAccount() {
        System.out.println("Account:");
        String saleName = scan.next();
        Salesman salesman = salesmanService.getOne(saleName);
        if ( salesman == null ){
            System.out.println("账户不存在");
        }else {
            if (salesman.getIsLock() == 0) {
                System.out.println("账户没有被锁定");
            }else {
                Salesman sm = new Salesman();
                sm.setId(salesman.getId());
                sm.setIsLock(0);
                salesmanService.updateById(sm);
                System.out.println("账户解锁成功!");
            }
        }
    }
}
