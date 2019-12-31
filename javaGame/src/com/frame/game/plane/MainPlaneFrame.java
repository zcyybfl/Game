package com.frame.game.plane;

import com.frame.main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
 * Time：23:30
 * Week：星期日
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 */
public class MainPlaneFrame extends JFrame implements ActionListener {

    private JButton start;
    private JButton exit;
    private JButton query;

    public MainPlaneFrame(String str) {
        this.setTitle(str);
        init();
        this.setSize(512, 600);
        this.setLocationRelativeTo(getOwner());
        this.setLocationRelativeTo(getOwner());//窗口放置到屏幕中央
        ImageIcon Image = new ImageIcon(getClass().getResource("/com/images/JavaGameLogo.jpg"));
        setIconImage(Image.getImage());
        //不允许改变界面大小
        this.setResizable(false);
        //设置默认关闭选项
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void init() {
        this.setLayout(null);

        start = new JButton("开始游戏");
        start.setBounds(220,200,100,30);
        add(start);

        query = new JButton("查看排名");
        query.setBounds(220,250,100,30);
        add(query);

        exit = new JButton("返回");
        exit.setBounds(220,300,100,30);
        add(exit);

        start.addActionListener(this);
        exit.addActionListener(this);
        query.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==start) {
            this.dispose();
            new PlaneGameFrame("飞机大战");
        }
        if (e.getSource() == exit) {
            this.dispose();
            new MainFrame();
        }
        if (e.getSource() == query) {
            new PlaneScoreFrame();
        }
    }
}
