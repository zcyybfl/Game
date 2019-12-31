package com.frame.game.plane;

import com.frame.game.plane.panel.PlaneGamePanel;

import javax.swing.*;

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
 * Date：2019/12/29
 * Time：23:24
 * Week：星期日
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 */
public class PlaneGameFrame extends JFrame {

    public PlaneGameFrame(String str) {
        //设置标题
        this.setTitle(str);
        //设置大小
        this.setSize(512, 768);
        this.setLocationRelativeTo(getOwner());//窗口放置到屏幕中央
        //不允许改变界面大小
        this.setResizable(false);
        ImageIcon Image = new ImageIcon(getClass().getResource("/com/images/JavaGameLogo.jpg"));
        setIconImage(Image.getImage());
        //设置默认关闭选项
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }

    void init() {
        //创建面板对象
        PlaneGamePanel panel =new PlaneGamePanel(this);
        //调用开始游戏的方法
        panel.action();
        this.add(panel);
        //添加面板
        this.setVisible(true);
    }
}
