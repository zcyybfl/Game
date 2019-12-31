package com.dao;

import com.bean.User;
import com.frame.login.LoginFrame;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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
 * Time：11:46
 * Week：星期三
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 * 用户数据库的操作类
 */
public class UserDao {

    User user = new User();
    GetConnection getConnection = new GetConnection();
    PreparedStatement preparedStatement;
    String sql;

    public User select(String UserName) {
        try {
            sql = "SELECT * FROM table_user WHERE username = ?";
            preparedStatement = getConnection.getConn().prepareStatement(sql);
            preparedStatement.setString(1,UserName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setSnakeScore(resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getConnection.closed();
        }
        return user;
    }

    public void insert(User user) {
        try {
            sql = "INSERT INTO table_user(username,password) VALUES(?,?)";
            preparedStatement = getConnection.getConn().prepareStatement(sql);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getConnection.closed();
        }
    }

    //更新贪吃蛇分数
    public void insertScore(int score) {
        try {
            sql = "UPDATE table_user SET snake_score = ? WHERE username = ?";
            preparedStatement = getConnection.getConn().prepareStatement(sql);
            preparedStatement.setInt(1,score);
            preparedStatement.setString(2, LoginFrame.name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getConnection.closed();
        }
    }
    //更新飞机大战分数
    public void updatePlane(int score) {
        try {
            sql = "UPDATE table_user SET plane_score = ? WHERE username = ?";
            preparedStatement = getConnection.getConn().prepareStatement(sql);
            preparedStatement.setInt(1,score);
            preparedStatement.setString(2, LoginFrame.name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getConnection.closed();
        }
    }

    //查询所有
    public List<User> selectAll(){
        List<User> list = new ArrayList<>();
        try {
            String sql = "select *from table_user";
            preparedStatement = getConnection.getConn().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                User user1 = new User();
                user1.setPlaneScore(rs.getInt(5));
                user1.setSnakeScore(rs.getInt(4));
                user1.setUsername(rs.getString(2));
                list.add(user1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            getConnection.closed();
        }
        return list;
    }
}
