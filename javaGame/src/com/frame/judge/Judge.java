package com.frame.judge;

import com.bean.User;
import com.dao.UserDao;

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
 * Time：11:50
 * Week：星期三
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 */
public class Judge {

    static boolean aBoolean;

    User user ;
    UserDao userDao = new UserDao();

    public boolean login(String UserName, String Password) {
        aBoolean = false;
        user = userDao.select(UserName);
        if (UserName.equals(user.getUsername()) && Password.equals(user.getPassword())) {
            return true;
        }else {
            return false;
        }
    }

    public boolean register(String UserName,String Password) {
        aBoolean = false;
        user = userDao.select(UserName);
        if (!UserName.equals(user.getUsername())) {
            user.setUsername(UserName);
            user.setPassword(Password);
            userDao.insert(user);
            return true;
        }else {
            return false;
        }
    }
}
