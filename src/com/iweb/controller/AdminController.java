package com.iweb.controller;

import com.iweb.entity.Admin;
import com.iweb.entity.Admin;
import com.iweb.service.IAdminService;
import com.iweb.util.LateFiveSecondUtil;

import java.util.List;
import java.util.Scanner;

public class AdminController {
    public static Admin salesman = null; // 管理员存储登录系统的管理员对象
    static Scanner scan = new Scanner(System.in);
    // 拿到管理员的service对象
    IAdminService salesmanService = IAdminService.getInstance();

    // 添加一个管理员的方法
    public void save() {
        // 创建一个管理员对象
        Admin salesman = new Admin();
        System.out.println("输入管理员姓名:");
        salesman.setAdminName(scan.next()); // 将控制台的输入直接用set方法存储到管理员对象中
        System.out.println("输入管理员密码:");
        salesman.setPassWord(scan.next());
        if (salesmanService.save(salesman)) {
            System.out.println("添加管理员成功!");
        } else {
            System.out.println("添加管理员失败!");
        }
    }

    // 删除一个管理员的方法
    public void remove() {
        System.out.println("输入要删除的管理员Id:");
        Integer adminId = scan.nextInt();
       Admin salesman = new Admin();
        salesman.setId(adminId);
         if (salesmanService.removeById(adminId)) {
         System.out.println("删除Id为" + adminId + "的管理员成功!");
         }else {
         System.out.println("删除Id为" + adminId + "的管理员失败!");
         }
    }

    // 更新一个管理员的方法
    public void modify() {
        // 被更新的管理员对象
        Admin salesman = new Admin();
        System.out.println("输入管理员Id:");
        salesman.setId(scan.nextInt());
        System.out.println("是否更新密码(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入新的密码:");
            salesman.setPassWord(scan.next());
        }
        System.out.println("是否更新管理员account(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入新的account:");
            salesman.setAdminName(scan.next());
        }
        if (salesmanService.updateById(salesman)) {
            System.out.println("更新成功!");
        } else {
            System.out.println("更新失败!");
        }
    }

    // 查询一个管理员详细信息的方法
    public void detail() {
        Admin salesman = null;
        System.out.println("是否根据管理员id查询(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入管理员id:");
            Integer id = scan.nextInt();
            salesman = salesmanService.getOne(id);
        } else {
            System.out.println("是否根据管理员姓名查询(y/n)?");
            if (scan.next().equals("y")) {
                System.out.println("输入管理员姓名:");
                String saleName = scan.next();
                salesman = salesmanService.getOne(saleName);
            }
        }
        if (salesman == null) {
            System.out.println("未查到相关数据!");
        } else {
            System.out.println("--------管理员的详细信息-------");
            System.out.println("编号:" + salesman.getId());
            System.out.println("账号:" + salesman.getAdminName());
            System.out.println("密码:" + salesman.getPassWord());

        }
    }

    // 查询管理员列表的方法
    public void list() {
        Admin salesman = new Admin();
        System.out.println("是否根据管理员姓名模糊查询(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入要查询的管理员姓名(支持模糊查询):");
            salesman.setAdminName(scan.next());
        }

        System.out.println("根据查询条件,共查出" + salesmanService.count(salesman) + "行数据");
        List<Admin> list = salesmanService.list(salesman);
        if (list == null || list.size() == 0) {
            System.out.println("没有数据!");
        } else {
            for (Admin s : list) {
                System.out.print(s.getId());
                System.out.print("\t");
                System.out.print(s.getAdminName());
                System.out.print("\t");
                System.out.println(s.getPassWord());

            }
        }
    }

    // 管理员登陆系统的方法
    public boolean  login(String saleName) {
        for (int i = 3; i >=0; i--) {
            // 把输入的管理员姓名作为参数传给下层, 并接收下层返回的管理员对象
            Admin salesman = salesmanService.getOne(saleName);
            // 判断管理员是否存在
            if (salesman == null || salesman.getId() == null) {
                System.out.println("管理员不存在");
                continue;
            } else if (salesman.getIsLock() == 1) {
                System.out.println("管理员已锁定，请联系其他管理员");
                LateFiveSecondUtil.lateTime();
                return false;
            } else {
                System.out.println("请输入密码");
                if (salesman.getPassWord().equals(scan.next())) {
                    System.out.println("密码正确,登录成功");
                    // 将管理员对象保存起来,方便后续的功能知道当前是哪个管理员登录的
                    AdminController.salesman = salesman;
                    return true;
                } else {
                    System.out.println("密码错误,登录失败!");

                    if (i == 0) {
                        // 创建一个用于传给更新方法的Admin对象
                        Admin sm = new Admin();
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



    // 解锁管理员账户方法
    public void unLockAccount() {
        System.out.println("Account:");
        String saleName = scan.next();
        Admin salesman = salesmanService.getOne(saleName);
        if ( salesman == null ){
            System.out.println("账户不存在");
        }else {
            if (salesman.getIsLock() == 0) {
                System.out.println("账户没有被锁定");
            }else {
                Admin sm = new Admin();
                sm.setId(salesman.getId());
                sm.setIsLock(0);
                salesmanService.updateById(sm);
                System.out.println("账户解锁成功!");
            }
        }
    }
}
