package com.frame.game.snake;

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
 * Date：2019/12/27
 * Time：13:17
 * Week：星期五
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 */
public class MainSnakeFrame extends JFrame implements ActionListener {

    private JButton start;
    private JButton exit;
    private JButton query;

    public MainSnakeFrame(String str) {
        this.setTitle(str);
        init();
        this.setSize(500,400);
        this.setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon Image = new ImageIcon(getClass().getResource("/com/images/JavaGameLogo.jpg"));
        setIconImage(Image.getImage());
        this.setResizable(false);
        this.setVisible(true);
    }

    public void init() {

        setLayout(null);

        start = new JButton("开始游戏");
        start.setBounds(200,100,100,30);
        add(start);

        query = new JButton("查看排名");
        query.setBounds(200,150,100,30);
        add(query);

        exit = new JButton("返回");
        exit.setBounds(200,200,100,30);
        add(exit);

        start.addActionListener(this);
        exit.addActionListener(this);
        query.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            this.dispose();
            new SnakeGameFrame("贪吃蛇");
        }
        if (e.getSource() == exit) {
            this.dispose();
            new MainFrame();
        }
        if (e.getSource() == query) {
            new SnakeScoreFrame();
        }
    }
}
