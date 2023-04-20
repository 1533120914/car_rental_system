package com.iweb.controller;

import com.iweb.dao.IUserDao;
import com.iweb.entity.RentRecord;
import com.iweb.entity.Salesman;
import com.iweb.entity.User;
import com.iweb.entity.query.RentRecordQuery;
import com.iweb.entity.vo.RentRecordVO;
import com.iweb.service.IRentRecordService;
import com.iweb.service.ISalesmanService;
import com.iweb.service.IUserService;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class RentRecordController {
    static Scanner scan = new Scanner(System.in);
    IRentRecordService rentRecordService = IRentRecordService.getInstance();
    // 查询租赁记录
    public void rent () {
        RentRecord rentRecord = new RentRecord();
        System.out.println("输入租赁的汽车id:"); // 能租赁的汽车状态必须是0(未租赁)
        rentRecord.setCarId(scan.nextInt());
        System.out.println("输入用户id:");
        rentRecord.setUserId(scan.nextInt());
        // 办理本次租赁的业务员id (业务员登录成功后会保存当前业务员对象,从保存的业务员对象中获取业务员id)
        rentRecord.setSalementId(SalesmanController.salesman.getId());
        System.out.println("输入租赁的天数:");
        rentRecord.setRentDays(scan.nextInt());
        System.out.println("输入预付款:");
        rentRecord.setPrepay(scan.nextDouble());
        // 状态默认为0 (未归还)
        rentRecord.setState(0);
        // 创建日期默认为当前
        rentRecord.setCreateDate(LocalDateTime.now());
        if (rentRecordService.save(rentRecord)) {
            System.out.println("租赁成功!");
            // 此时检查以下租赁记录是否插入
            // 并检查汽车状态是否由0改为了1
        }else {
            System.out.println("租赁失败!");
        }
    }
    public void list(Salesman salesman) {
        ISalesmanService instance = ISalesmanService.getInstance();
        Salesman one = instance.getOne(salesman.getSaleName());
        if(one.getId()==null){
            System.out.println("发生错误未获得此业务员ID");
            return;
        }
        RentRecordQuery query = new RentRecordQuery();
        query.setSalesmanId(one.getId());
        List<RentRecordVO> list = rentRecordService.list(query);
        if (list == null || list.size() == 0) {
            System.out.println("没有查到数据");
        }else {
            for (RentRecordVO vo : list) {
                System.out.print(vo.getId());
                System.out.print("\t");
                System.out.print(vo.getCarName());
                System.out.print("\t");
                System.out.print(vo.getUsername());
                System.out.print("\t");
                System.out.print(vo.getSaleName());
                System.out.print("\t");
                System.out.print(vo.getRentDays());
                System.out.print("\t");
                System.out.print(vo.getPrepay());
                System.out.print("\t");
                System.out.print(vo.getState() == 0 ? "已归还" : "未归还");
                System.out.print("\t");
                System.out.print(vo.getCreateDate());
                System.out.print("\n");
            }
        }

    }
    public void list(User salesman) {
        IUserService instance = IUserService.getInstance();
        User one = instance.getOne(salesman.getUserName());
        if(one.getId()==null){
            System.out.println("发生错误未获得此用户ID");
            return;
        }
        RentRecordQuery query = new RentRecordQuery();
        query.setUserId(one.getId());
        List<RentRecordVO> list = rentRecordService.list(query);
        if (list == null || list.size() == 0) {
            System.out.println("没有查到数据");
        }else {
            for (RentRecordVO vo : list) {
                System.out.print(vo.getId());
                System.out.print("\t");
                System.out.print(vo.getCarName());
                System.out.print("\t");
                System.out.print(vo.getUsername());
                System.out.print("\t");
                System.out.print(vo.getSaleName());
                System.out.print("\t");
                System.out.print(vo.getRentDays());
                System.out.print("\t");
                System.out.print(vo.getPrepay());
                System.out.print("\t");
                System.out.print(vo.getState() == 0 ? "已归还" : "未归还");
                System.out.print("\t");
                System.out.print(vo.getCreateDate());
                System.out.print("\n");
            }
        }

    }
    public void list(RentRecordQuery query) {
        System.out.println("是否根据业务员姓名模糊查询(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入要查询的业务员姓名(支持模糊查询):");
            query.setSaleName(scan.next());
        }
        System.out.println("是否根据用户姓名模糊查询(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入要查询的用户姓名(支持模糊查询):");
            query.setUsername(scan.next());
        }
        System.out.println("是否根据汽车名称模糊查询(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入要查询的汽车名称(支持模糊查询):");
            query.setCarName(scan.next());
        }
        System.out.println("是否根据租赁状态查询(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入要查询的状态(0.未归还 1.已归还):");
            query.setState(scan.nextInt());
        }
        System.out.println("是否根据租赁日期查询(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入要查询的起始日期:");
            query.setSmallDateTime(scan.nextLine());
            System.out.println("输入要查询的结束日期:");
            query.setBigDateTime(scan.nextLine());
        }
        List<RentRecordVO> list = rentRecordService.list(query);
        if (list == null || list.size() == 0) {
            System.out.println("没有查到数据");
        }else {
            for (RentRecordVO vo : list) {
                System.out.print(vo.getId());
                System.out.print("\t");
                System.out.print(vo.getCarName());
                System.out.print("\t");
                System.out.print(vo.getUsername());
                System.out.print("\t");
                System.out.print(vo.getSaleName());
                System.out.print("\t");
                System.out.print(vo.getRentDays());
                System.out.print("\t");
                System.out.print(vo.getPrepay());
                System.out.print("\t");
                System.out.print(vo.getState() == 0 ? "已归还" : "未归还");
                System.out.print("\t");
                System.out.print(vo.getCreateDate());
                System.out.print("\n");
            }
        }
    }

}
