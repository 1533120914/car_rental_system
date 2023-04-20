package com.iweb.controller;

import com.iweb.entity.Brand;
import com.iweb.entity.Salesman;
import com.iweb.service.IBrandService;

import java.util.List;
import java.util.Scanner;

public class BrandController {
    public static Brand brand=null;
    static Scanner scanner=new Scanner(System.in);
    IBrandService brandService=IBrandService.getInstance();
    public void save(){
        Brand brand =new Brand();
        System.out.println("请输入品牌名称");
        brand.setBrandName(scanner.next());
        if(brandService.save(brand)){
            System.out.println("添加汽车品牌成功");

        }
        else{
            System.out.println("添加汽车品牌失败");
        }
    }
    public void remove(){
        System.out.println("请输入您要删除的车辆id");
        Integer brandId=scanner.nextInt();
        Brand brand=new Brand();
        brand.setId(brandId);
        if(brandService.removeById(brandId)){
            System.out.println("删除成功");

        }
        else{
            System.out.println("库中有此品牌的车辆，无法删除");
        }

    }
    public void modify(){
        Brand brand =new Brand();
        System.out.println("请输入品牌id");
        brand.setId(scanner.nextInt());
        System.out.println("请输入新的品牌名称");
        brand.setBrandName(scanner.next());
        if(brandService.updateById(brand)){
            System.out.println("更新成功");
        }else System.out.println("更新失败");
    }
    public void detail()
    {
        Brand brand =null;
        System.out.println("是否根据品牌id进行查询");
        System.out.println("        是或否（y/n）");
        if(scanner.next().equals("y")){
            System.out.println("请输入品牌id");
            brand=brandService.getOne(scanner.nextInt());
        }
        else{
            System.out.println("是否根据品牌名查询");
            if(scanner.next().equals("y")){
                brand=brandService.getOne(scanner.next());
            }
        }
        if(brand ==null){
            System.out.println("未查到相关品牌");
        }
        else{
            System.out.println("------汽车品牌----------");
            System.out.println("编号：" + brand.getId());
            System.out.println("品牌名称："+brand.getBrandName());
        }
    }
    public void list() {
        Brand brand = new Brand();
        System.out.println("是否根据车辆品牌模糊查询(y/n)?");
        if (scanner.next().equals("y")) {
            System.out.println("输入要查询的车辆品牌(支持模糊查询):");
            brand.setBrandName(scanner.next());
        }
        System.out.println("是否根据车辆ID模糊查询(y/n)?");

        if (scanner.next().equals("y")) {
            System.out.println("输入要查询的车辆品牌(支持模糊查询):");
            brand.setId(scanner.nextInt());
        }
        System.out.println("根据查询条件,共查出" + brandService.count(brand) + "行数据");
        List<Brand> list = brandService.list(brand);
        if( list == null || list.size() == 0) {
            System.out.println("没有数据!");
        }else {
            for (Brand s : list) {
                System.out.print(s.getId());
                System.out.print("\t");
                System.out.println("品牌名:  |"+s.getBrandName()+"|");

            }
        }
    }
}
