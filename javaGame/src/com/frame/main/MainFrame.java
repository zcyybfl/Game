package com.frame.main;

import com.bean.User;
import com.frame.game.fivechess.FiveChessFrame;
import com.frame.game.plane.MainPlaneFrame;
import com.frame.game.plane.PlaneGameFrame;
import com.frame.game.snake.MainSnakeFrame;
import com.frame.login.LoginFrame;

import javax.swing.*;
import java.awt.*;
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
 * Date：2019/12/18
 * Time：14:58
 * Week：星期三
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 */
public class MainFrame extends JFrame implements ActionListener {

    //整个窗口只有一个菜单条，菜单条中添加菜单菜单中添加菜单项
    private  JMenuBar Menu;//菜单条
    private JMenu ChooseMenu,ExitMenu;//菜单
    private JMenuItem Exit,Logout,Concerning,Help;
    private ImageIcon Image;
    private JLabel GameJLabel;
    private JButton GameOneJButton,GameTwoJButton,GameThreeJButton;

    public MainFrame() {
        this.setTitle("小游戏大合集");
        this.setSize(800,600);
        this.setLocationRelativeTo(getOwner());
        init();
        this.setVisible(true);
    }

    public void init() {
        /**
         * 将当前窗口管理组件的方式设置为空，调用setBounds方法定位组件
         */
        setLayout(null);

        /**
         * 替换原有的logo
         */
        Image = new ImageIcon(getClass().getResource("/com/images/JavaGameLogo.jpg"));
        setIconImage(Image.getImage());

        //菜单条
        Menu = new JMenuBar();

        //菜单
        ChooseMenu = new JMenu("选项");
        ChooseMenu.setFont(new Font("宋体",Font.BOLD,20));
        ExitMenu = new JMenu("退出");
        ExitMenu.setFont(new Font("宋体",Font.BOLD,20));

        //菜单项
        Concerning = new JMenuItem("关于");
        Concerning.setBackground(Color.lightGray);
        Concerning.setFont(new Font("宋体",Font.BOLD,15));
        Logout = new JMenuItem("退出登录");
        Logout.setBackground(Color.lightGray);
        Logout.setFont(new Font("宋体",Font.BOLD,15));
        Exit = new JMenuItem("退出");
        Exit.setBackground(Color.lightGray);
        Exit.setFont(new Font("宋体",Font.BOLD,15));
        Help = new JMenuItem("帮助");
        Help.setBackground(Color.lightGray);
        Help.setFont(new Font("宋体",Font.BOLD,15));

        //将菜单项添加到菜单中
        ChooseMenu.add(Help);
        ChooseMenu.add(Concerning);
        ExitMenu.add(Logout);
        ExitMenu.add(Exit);

        //将菜单添加到菜单条中
        Menu.add(ChooseMenu);
        Menu.add(ExitMenu);
        Menu.setBounds(0,0,800,50);

        //添加菜单
        add(Menu);

        GameJLabel = new JLabel("游   戏");
        GameJLabel.setFont(new Font("宋体",Font.BOLD,40));
        GameJLabel.setBounds(300,100,600,50);
        add(GameJLabel);

        GameOneJButton = new JButton("五子棋");
        GameOneJButton.setFont(new Font("宋体",Font.BOLD,20));
        GameOneJButton.setBounds(300,200,150,50);
        add(GameOneJButton);

        GameTwoJButton = new JButton("贪吃蛇");
        GameTwoJButton.setFont(new Font("宋体",Font.BOLD,20));
        GameTwoJButton.setBounds(300,300,150,50);
        add(GameTwoJButton);

        GameThreeJButton = new JButton("飞机大战");
        GameThreeJButton.setFont(new Font("宋体",Font.BOLD,20));
        GameThreeJButton.setBounds(300,400,150,50);
        add(GameThreeJButton);

        //监听器
        Logout.addActionListener(this);
        Exit.addActionListener(this);
        Concerning.addActionListener(this);
        GameOneJButton.addActionListener(this);
        GameTwoJButton.addActionListener(this);
        GameThreeJButton.addActionListener(this);
        Help.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Help) {
            Object [] HelpGame = {"五子棋","贪吃蛇","飞机大战"};
            String Choose = (String) JOptionPane.showInputDialog(this,
                    null, "游戏帮助",JOptionPane.PLAIN_MESSAGE,
                    null,HelpGame,"五子棋");
            switch (Choose) {
                case "五子棋":
                    JOptionPane.showMessageDialog(getParent(),"五子棋:\n五个棋子先连在一条线，即为胜者" +
                                    "\n三个棋子连在一起需要堵住一端" +
                                    "\n避免出现三三禁手、四四禁手的情形",
                            "帮助", JOptionPane.INFORMATION_MESSAGE);break;
                case "贪吃蛇":JOptionPane.showMessageDialog(getParent(),"用游戏把子上下左右控制蛇的方向，寻找吃的东西，" +
                        "每吃一口就能得到一定的积分，而且蛇的身子会越吃越长，身子越长玩的难度就越大，" +
                        "不能碰墙，不能咬到自己的身体，更不能咬自己的尾巴,空格暂停，esc退出","帮助", JOptionPane.INFORMATION_MESSAGE);break;
                case "飞机大战":JOptionPane.showMessageDialog(getParent(),"躲避导弹，击败飞机，获取胜利，空格暂停，esc退出","帮助", JOptionPane.INFORMATION_MESSAGE);break;
            }
        }
        if (e.getSource() == Concerning) {
            JOptionPane.showMessageDialog(getParent(),"本项目为Java基础工程设计，" +
                    "组长：郑龙涛，组员：黄思捷，向前程","提示框", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == Logout) {
            this.dispose();
            new LoginFrame();
        }
        if (e.getSource() == Exit) {
            this.dispose();
        }
        if (e.getSource() == GameOneJButton) {
            this.dispose();
            new FiveChessFrame("五子棋");
        }
        if (e.getSource() == GameTwoJButton) {
            this.dispose();
            new MainSnakeFrame("贪吃蛇");
        }
        if (e.getSource() == GameThreeJButton) {
            this.dispose();
            new MainPlaneFrame("飞机大战");
        }
    }
}
