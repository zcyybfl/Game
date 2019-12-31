package com.dao;

import java.sql.*;

/**
 * █████▒█      ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 * Project：JavaGame
 * Date：2019/12/18
 * Time：10:29
 * Week：星期三
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 * 数据库的连接
 */
public class GetConnection {

    //JDBC 驱动名及数据库 URL
    private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private String DB_URl = "jdbc:mysql://localhost:3306/JavaGame?useSSL=false&serverTimezone=UTC";
    private String DB_URl = "jdbc:mysql://hsjnb.com:3306/JavaGame?useSSL=false&serverTimezone=UTC";

    // 数据库的用户名与密码，需要根据自己的设置
//    private String USER = "root";
    private String USER = "javagame";
    private String PASSWORD = "root";

    Connection connection = null;

    public GetConnection() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("驱动程序未加载成功");
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        try {
            connection = DriverManager.getConnection(DB_URl,USER,PASSWORD);
        } catch (SQLException e) {
            System.out.println("数据库连接不成功");
            e.printStackTrace();
            connection = null;
        }
        return connection;
    }

    public void closed() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("关闭数据库不成功");
            e.printStackTrace();
        }
    }
}
