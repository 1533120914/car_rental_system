package com.iweb.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 数据库连接的工具类
 * (原本写在BaseDao中的数据库连接的代码,交给DBUtil去做)
 * 目的是:不在Dao层创建数据库连接,改为在Service层创建数据库连接
 * 原因是Dao层的方法是原子的,不方便开启事务
 */
public class DBUtil {
    public static    String  inetAddress;
    public static    String  inetAddress1;
    /**
     * 数据库连接信息, 定义为静态常量
     */private static String getIp() {
        Scanner scanner=new Scanner(System.in);

        System.out.println("请输入你的数据库IP");
        inetAddress=scanner.next();
return inetAddress;

    }
    private static String getBag() {
        Scanner scanner=new Scanner(System.in);

        System.out.println("请输入你的库名如：db_03");
        inetAddress1=scanner.next();
        return inetAddress1;
    }
    private static final String URL = "jdbc:mysql://"+getIp()+":3306/"+getBag()+"?characterEncoding=utf-8&useUnicode=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    /**
     * 加载数据库驱动, 写在静态代码块中, 因为静态代码块只会在类加载的时候执行一次, 加载驱动只需要执行一次即可
     */
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启数据库连接, 默认不开事务
     */
    public static Connection open () {
        return open(true);
    }

    /**
     * 开启数据库连接
     * @param autoCommit true表示不开事务, false表示开事务
     */
    public static Connection open (boolean autoCommit) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setAutoCommit(autoCommit);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 关闭数据库连接
     */
    public static void close (Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
