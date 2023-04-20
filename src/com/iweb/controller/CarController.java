package com.iweb.controller;
import com.iweb.entity.Car;
import com.iweb.service.ICarService;
import com.iweb.util.DateUtil;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
public class CarController {
    static Scanner scan = new Scanner(System.in);
    ICarService carService = ICarService.getInstance();

    public void save() {
        // 创建一个业务员对象
        Car car = new Car();
        System.out.println("输入车名:");
        car.setCarName(scan.next()); // 将控制台的输入直接用set方法存储到业务员对象中
        System.out.println("输入品牌id:");
        car.setBrandId(scan.nextInt());
        System.out.println("输入车的类型:");
        car.setCarType(scan.next());
        System.out.println("输入车座:");
        car.setSeatNum(scan.nextInt());
        System.out.println("输入车辆百公里加速度:");
        car.setAcceCapa(scan.nextDouble());
        System.out.println("输入车辆百公里油耗:");
        car.setOilWear(scan.nextDouble());
        System.out.println("输车辆状态:");
        car.setState(scan.nextInt());
        car.setState(0); // 新添加的车辆状态默认为0, 表示未离职, 1表示离职
        // 新添加的业务员的创建日期默认是当前日期,日期转换为字符串
        car.setCreateDate(DateUtil.localDateTimeToString(LocalDateTime.now()));
        // 使用Service对象调用save()方法, 把存储了输入信息的业务员对象salesman传给save()方法
        if (carService.save(car)) {
            System.out.println("添加车辆成功!");
        } else {
            System.out.println("添加车辆失败!");
        }
    }

    // 删除一个业务员的方法
    public void remove() {
        System.out.println("输入要删除的删除Id:");
        Integer carId = scan.nextInt();
        if(carService.getOne(carId).getState()==1){
            System.out.println("此车已经被删除");
            return;
        }else if(carService.getOne(carId)==null){
            System.out.println("此车不存在");
            return;
        }
        Car car = new Car();
        car.setId(carId);
        car.setState(1);
        // 做假删除, 逻辑删除, 被删除的员工改为离职状态
        if (carService.updateById(car)) {
            System.out.println("删除Id为" + carId + "的车辆成功!");
        } else {
            System.out.println("删除Id为" + carId + "的车辆失败!");
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
        Car car = new Car();
        System.out.println("输入车辆Id:");
        car.setId(scan.nextInt());
        System.out.println("是否车辆名称(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入新的车辆名称:");
            car.setCarName(scan.next());
        }
        System.out.println("是否更新车辆品牌ID(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入新的ID:");
            car.setBrandId(scan.nextInt());
        }
        System.out.println("是否更新车的类型(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入车的类型:");
            car.setCarType(scan.next());
        }
        System.out.println("是否更新车辆状态为出租(y/n)?");
        if (scan.next().equals("y")) {
            // 0是未离职, 1是已出租
            car.setState(1);
        }
        System.out.println("是否更新车座的数量(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入车座数量:");
            car.setSeatNum(scan.nextInt());
        }
        System.out.println("是否更新百公里加速度(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入新的属性:");
            car.setAcceCapa(scan.nextDouble());
        }
        System.out.println("是否更新油耗(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入新的油耗:");
            car.setOilWear(scan.nextDouble());
        }
        System.out.println("是否更新日租金(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入新的日租金:");
            car.setDailyRent(scan.nextDouble());
        }
        if (carService.updateById(car)) {
            System.out.println("更新成功!");
        } else {
            System.out.println("更新失败!");
        }
    }

    // 查询一个业务员详细信息的方法


    public void detail() {
        Car car = null;
        System.out.println("是否根据车辆id查询(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入车辆id:");
            Integer id = scan.nextInt();
            car = carService.getOne(id);
        } else {
            System.out.println("是否根据车辆名称查询(y/n)?");
            if (scan.next().equals("y")) {
                System.out.println("输入车辆姓名:");
                String carName = scan.next();
                car = carService.getOne(carName);
            }
        }
        if ( car == null ) {
            System.out.println("未查到相关数据!");
        }else {
            System.out.println("--------车辆的详细信息-------");
            System.out.println("编号:" + car.getId());
            System.out.println("姓名:" + car.getCarName());
            System.out.println("品牌名称:" + car.getBrandId());
            System.out.println("车辆类型:" + car.getCarType());
            System.out.println("车座数量:" + car.getSeatNum());
            System.out.println("百公里加速度:" + car.getAcceCapa());
            System.out.println("车辆属性:" + (car.getState() == 0 ? "未出售" : "已出售"));
            System.out.println("创建日期:" + car.getCreateDate());
            System.out.println("修改日期:" + car.getModifyDate());
        }
    }
    // 查询业务员列表的方法
    public void list() {
        Car car = new Car();
        System.out.println("是否根据车辆名称模糊查询(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入要查询的车辆名称(支持模糊查询):");
            car.setCarName(scan.next());
        }

        System.out.println("是否根据车辆id查询(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入要查询的车辆id:");
           car.setBrandId(scan.nextInt());
        }
        System.out.println("是否根据百公里油耗查询(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入要查询的业务员电话号码:");
            car.setOilWear(scan.nextDouble());
        }
        System.out.println("是否根据百公里加速度查询(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入加速度");
            car.setAcceCapa(scan.nextDouble());
        }
        System.out.println("是否根据日租金(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入日租金");
            car.setDailyRent(scan.nextDouble());
        }
        System.out.println("是否根据车辆状态(y/n)?");
        if (scan.next().equals("y")) {
            System.out.println("输入车辆状态");
            car.setState(scan.nextInt());
        }
        System.out.println("根据查询条件,共查出" + carService.count(car) + "行数据");
        List<Car> list = carService.list(car);
        if( list == null || list.size() == 0) {
            System.out.println("没有数据!");
        }else {
            for (Car s : list) {
                System.out.print(s.getId());
                System.out.print("\t");
                System.out.print(s.getCarName());
                System.out.print("\t");
                System.out.print(s.getCarType());
                System.out.print("\t");
                System.out.print(s.getAcceCapa());
                System.out.print("\t");
                System.out.print(s.getOilWear());
                System.out.print("\t");
                System.out.print(s.getDailyRent());
                System.out.print("\t");
                System.out.print(s.getCreateDate());
                System.out.print("\t");
                System.out.print(s.getModifyDate());
                System.out.print("\t");
                System.out.print(s.getState() == 0 ? "未出售" : "已出售");
                System.out.print("\n");
            }
        }
    }
}
