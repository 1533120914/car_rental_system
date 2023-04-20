package com.iweb;

import com.iweb.controller.*;
import com.iweb.entity.Salesman;
import com.iweb.entity.User;
import com.iweb.entity.query.RentRecordQuery;

import java.util.Scanner;

public class App {
    static Scanner scan = new Scanner(System.in);
    static BrandController brandController=new BrandController();
    static CarController carController = new CarController();
    static SalesmanController salesmanController = new SalesmanController();
    static UserController userController = new UserController();
    static RentRecordController rentRecordController = new RentRecordController();
    static AdminController adminController = new AdminController();

    public static void main(String[] args) {
        homeMenu();
    }

    private static void homeMenu() {
        while (true) {
            System.out.println("*********欢迎使用艾瑞租车系统*********");
            System.out.println("1.业务员端");
            System.out.println("2.用户端");
            System.out.println("3.管理员端");
            System.out.println("输入0退出主菜单");
            System.out.println("请选择:");
            int c = scan.nextInt();
            if ( c == 0 ) break; // 跳出循环, 退出主菜单
            switch (c) {
                case 1:
                    salesmanMenu();
                    break;
                case 2:
                    userMenu();
                    break;
                case 3:
                    adminMenu();
                    break;
                default:
                    System.out.println("输入错误!");
                    break;
            }
        }
        System.out.println("感谢使用,再见!");
    }

    private static void salesmanMenu() {
        System.out.println("Account:");
        String saleName=scan.next();
        Salesman salesman =new Salesman();
        salesman.setSaleName(saleName);
            // 记录用户输入的业务员姓名
            if (!salesmanController.login(saleName)) {
            return ;
            }


        while (true) {
            System.out.println("*********您已进入业务员端*********");
            System.out.println("1.查询汽车");
            System.out.println("2.查看汽车详细信息");
            System.out.println("3.办理汽车租赁");
            System.out.println("4.查看自己办理的业务");
            System.out.println("输入0退出业务员端");
            System.out.println("请选择:");
            int c = scan.nextInt();
            if ( c == 0 ) break; // 跳出循环, 退出主菜单
            switch (c) {
                case 1:carController.list();
                    break;
                case 2:carController.detail();
                    break;
                case 3:
                    rentRecordController.rent();
                    break;
                case 4:
                    rentRecordController.list(salesman);

                    break;
                default:
                    System.out.println("输入错误!");
                    break;
            }
        }
        System.out.println("感谢使用,再见!");
    }
    private static void userMenu() {
        System.out.println("Account:");
        String userName=scan.next();
        User user=new User();
        user.setUserName(userName);
        // 记录用户输入的业务员姓名

        if (!userController.login(userName)) {
            return ;
        }

        while (true) {
            System.out.println("*********您已进入用户端*********");
            System.out.println("1.查询汽车");
            System.out.println("2.查看汽车详细信息");
            System.out.println("3.查看我的租车记录");
            System.out.println("4.删除租车记录");
            System.out.println("输入0退出用户端");
            System.out.println("请选择:");
            int c = scan.nextInt();
            if ( c == 0 ) break; // 跳出循环, 退出主菜单
            switch (c) {
                case 1:carController.list();
                    break;
                case 2:carController.detail();
                    break;
                case 3:
                    rentRecordController.list(user);
                    break;
                case 4:carController.remove();
                    break;
                default:
                    System.out.println("输入错误!");
                    break;
            }
        }
        System.out.println("感谢使用,再见!");
    }

    private static void adminMenu() {
        System.out.println("Account:");
        String adminName=scan.next();
        User user=new User();
        user.setUserName(adminName);
        // 记录用户输入的业务员姓名
        if (!adminController.login(adminName)) {
            return ;
        }
        while (true) {
            System.out.println("*********您已进入管理员端*********");
            System.out.println("1.添加用户 2.修改用户 3.删除用户 4.查询用户 5.查询用户详细信息");
            System.out.println("6.添加业务员 7.修改业务员 8.删除业务员 9.查询业务员 10.查询业务员详细信息");
            System.out.println("11.添加汽车品牌 12.修改汽车品牌 13.删除汽车品牌 14.查询汽车品牌 15.查询品牌详细信息");
            System.out.println("16.添加汽车 17.修改汽车 18.删除汽车 19.查询汽车 20.查询汽车详细信息");
            System.out.println("21.解锁业务员账户 22.解锁用户账户 23.查询租赁信息");
            System.out.println("24.添加管理员 25.修改管理员 26.查询管理员 27.删除管理员 ");
            System.out.println("输入0退出系统");
            System.out.println("请选择:");
            int c = scan.nextInt();
            if ( c == 0 ) break; // 跳出循环, 退出主菜单
            switch (c) {
                case 1:userController.save();
                    break;
                case 2:userController.modify();
                    break;
                case 3:userController.remove();
                    break;
                case 4:userController.list();
                    break;
                case 5:userController.detail();
                    break;
                case 6:
                    salesmanController.save();
                    break;
                case 7:
                    salesmanController.modify();
                    break;
                case 8:
                    salesmanController.remove();
                    break;
                case 9:
                    salesmanController.list();
                    break;
                case 10:
                    salesmanController.detail();
                    break;
                case 11:brandController.save();
                    break;
                case 12:brandController.modify();
                    break;
                case 13:brandController.remove();
                    break;
                case 14:brandController.detail();
                    break;
                case 15:brandController.list();
                    break;
                case 16:carController.save();
                    break;
                case 17:carController.modify();
                    break;
                case 18:carController.remove();
                    break;
                case 19:carController.detail();
                    break;
                case 20:carController.list();
                    break;
                case 21:
                    salesmanController.unLockAccount();
                    break;
                case 22:
                    userController.unLockAccount();
                    break;
                case 23:
                    rentRecordController.list(new RentRecordQuery());
                    break;
                case 24:
                    adminController.save();
                    break;
                case 25:
                    adminController.modify();
                    break;
                case 26:
                    adminController.list();
                    break;
                case 27:
                    adminController.remove();
                    break;
                default:
                    System.out.println("输入错误!");
                    break;
            }
        }
        System.out.println("感谢使用,再见!");
    }
}
