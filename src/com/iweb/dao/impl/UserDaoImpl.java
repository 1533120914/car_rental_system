package com.iweb.dao.impl;
import com.iweb.dao.BaseDao;
import com.iweb.dao.IUserDao;
import com.iweb.entity.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class UserDaoImpl extends BaseDao implements IUserDao {
    public UserDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    protected User rowToObject(ResultSet resultSet) throws SQLException {
        if (resultSet.isBeforeFirst()) {
            // 让结果集指向第一行
            resultSet.next();
        }
        // 创建一个user对象
        User user  = new User();
        // 根据列名, 依次从resultSet对象(方法的参数)中取出每一列的数据, 调用set方法存储到user对象中
        user.setId(resultSet.getInt("id"));
        user.setUserName(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setContact(resultSet.getString("contact"));
        user.setContact(resultSet.getString("email"));
        user.setState(resultSet.getInt("state"));
        user.setIsLock(resultSet.getInt("is_lock"));
        // 字符串转为LocalDateTime
        System.out.println(resultSet.getString("create_date"));
        user.setCreateDate(resultSet.getString("create_date"));
        user.setModifyDate(resultSet.getString("modify_date"));
        // 返回这个user对象
        return user;
    }
    @Override
    protected List<User> tableToList(ResultSet resultSet) throws SQLException {//创建整个表
        List<User> userList=new ArrayList<>();
        if(resultSet!=null){//结果集不为null
        while(resultSet.next()){//遍历结果集
            User user=rowToObject(resultSet);//调用rowtoobject将这一行数据取出插入到user中
            userList.add(user);//将获取到的user以数组的add方式存入进去
        }
        }//此时应转运完毕
        return userList;//将userList返回

    }

    @Override
    public int delereById(Integer userId) throws SQLException {
        String sql = "DELETE FROM user WHERE id = ?";
        // 这条SQL语句只有一个? List里面只有一个userId
        int rows = super.update(sql, Arrays.asList(new Object[] {userId}));
        // 把数据库返回的受影响行数作为这个方法的返回值
        return rows;
    }

    @Override
    public int insert(User user) throws SQLException {
   //开启连接
        String sql="INSERT INTO user(username,password,contact,email,state,is_lock,create_date) " + "VALUES(?,?,?,?,?,?)";//创建时间为创建的时候的时间此时不加修改时间当需要加入修改时间的时候后再将修改时间加入
        int rows=super.update(sql, Arrays.asList(new Object[]{user.getUserName(),user.getPassword(),user.getContact(),user.getEmail(),user.getState(),user.getCreateDate()}));

        return rows;
    }



    @Override
    public int deleteById(Integer userId) throws SQLException {//伪装删除将state变为1

       String sql="DELETE FROM user WHERE id=?";
       int rows=super.update(sql,Arrays.asList(new Object[] {userId}));

       return rows;
    }

    @Override
    public int updateById(User user) throws SQLException {
        // 注意修改功能的实现要做成动态的, 可以修改任意一个列或者多个列
        // SQL语句要动态拼接
        // 用于存储sql参数的集合
        List<Object> sqlParams = new ArrayList<>();
        // 每次修改都必会更新modify_date列
        String sql = "UPDATE user SET modify_date=NOW() ";
        if (user.getPassword() != null) {
            sql += ",username=?";
            // 如果修改密码, 把密码添加为sql参数
            sqlParams.add(user.getUserName());
        }
        if ( user.getPassword() != null) {
            sql += ",password=?";
            // 如果修改联系方式, 把联系方式添加为sql参数
            sqlParams.add(user.getContact());
        }
        if ( user.getContact() != null) {
            sql += ",contact=?";
            // 如果修改薪资, 把薪资添加为sql参数
            sqlParams.add(user.getContact());
        }
        if ( user.getEmail() != null) {
            sql += ",email=?";
            // 如果修改状态, 把状态添加为sql参数
            sqlParams.add(user.getEmail());
        }
        if(user.getIsLock()!=null){
            sql+=",is_lock=? ";
            sqlParams.add(user.getIsLock());
        }
        if(user.getState()!=null){
            sql+=",state=? ";
            sqlParams.add(user.getState());
        }
        // 拼接WHERE 条件
        sql += " WHERE id = ?";
        // 把id添加为sql参数
        sqlParams.add(user.getId());
        // 调用父类update方法, 传递sql语句和sql参数集合
        int rows = super.update(sql, sqlParams);

        return rows;
    }

    @Override
    public User selectOne(Integer userId) throws SQLException {

        String sql="SELECT id,username,password,contact,email,state,is_lock,DATE_FORMAT(create_date,'%Y-%m-%d %T') AS create_date,DATE_FORMAT(modify_date,'%Y-%m-%d %T') AS modify_date" +
                "FROM user WHERE id= ? ";
        User user=(User) super.queryOne(sql,Arrays.asList(new Object[]{userId}));

        return user;
    }

    @Override
    public User selectOne(String userName) throws SQLException {

        String sql = "SELECT id,username,password,contact,email,state,is_lock,DATE_FORMAT(create_date,'%Y-%m-%d %T') AS create_date,DATE_FORMAT(modify_date,'%Y-%m-%d %T') AS modify_date" +
                " FROM user WHERE username = ?";
        User user=(User)super.queryOne(sql, Arrays.asList(new Object[]{userName}));

       return user;
    }


    @Override
    public int count(User user) throws SQLException {

        String sql = "SELECT COUNT(*) FROM user "; // 因为下面要拼接, SQL后面留有空格
        String where = "WHERE 1=1 "; // 拼接where条件的字符串 1=1是为了方便拼接AND
        List<Object> sqlParams = new ArrayList<>();
        if (user.getUserName() != null) {
            where += "AND username LIKE CONCAT('%', ?, '%') "; // 拼接根据业务员姓名模糊查询条件 SQL后面留有空格
            sqlParams.add(user.getUserName());
        }
        if (user.getContact() != null) {
            where += "AND contact=? "; // 拼接根据业务员性别查询条件 SQL后面留有空格
            sqlParams.add(user.getContact());
        }
        if (user.getEmail() != null) {
            where += "AND email=? "; // 拼接根据业务员电话号码查询条件 SQL后面留有空格
            sqlParams.add(user.getEmail());
        }
        if (user.getState() != null) {
            where += "AND state=? "; // 拼接根据业务员状态查询条件 SQL后面留有空格
            sqlParams.add(user.getState());
        }
        sql += where;
        int count = super.count(sql, sqlParams);

        return count;

    }

    @Override
    public List<User> selectList(User user) throws SQLException {

        String sql = "SELECT id,username,password,contact,email,state,is_lock,DATE_FORMAT(create_date,'%Y-%m-%d %T') AS create_date,DATE_FORMAT(modify_date,'%Y-%m-%d %T') AS modify_date FROM user "; // 因为下面要拼接, SQL后面留有空格
        String where = "WHERE 1=1 "; // 拼接where条件的字符串 1=1是为了方便拼接AND
        List<Object> sqlParams = new ArrayList<>();
        if (user.getUserName()!= null) {
            where += "AND username LIKE CONCAT('%', ?, '%') "; // 拼接根据业务员姓名模糊查询条件 SQL后面留有空格
            sqlParams.add(user.getUserName());
        }

        if (user.getContact() != null) {
            where += "AND contact =? "; // 根据联系方式来查
            sqlParams.add(user.getContact());
        }

        if (user.getEmail() != null) {
            where += "AND email=? "; //根据email来查
            sqlParams.add(user.getEmail());
        }
        if (user.getState() != null) {
            where += "AND state=? "; // 拼接根据业务员状态查询条件 SQL后面留有空格
            sqlParams.add(user.getState());
        }
        sql += where;
        List<User>  list = (List<User>) super.query(sql, sqlParams);

        return list;
    }


}
