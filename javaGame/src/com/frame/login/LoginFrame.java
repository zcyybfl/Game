package com.frame.login;

import com.frame.judge.Judge;
import com.frame.main.MainFrame;
import com.frame.register.RegisterFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
 * Time：10:52
 * Week：星期三
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 *
 * 登录界面
 */
public class LoginFrame extends JFrame implements ActionListener,KeyListener {

    private JLabel UserNameJLabel,PasswordJLabel,LoginJLabel;
    private JTextField UserNameJTextField;
    private JPasswordField PasswordJPasswordField;
    private JButton LoginJButton,RegisterJButton,ExitJButton;
    private ImageIcon Image;
    public static String name = null;
    
    Judge judge = new Judge();

    public LoginFrame() {
        this.setTitle("登陆界面");
        this.setSize(1200,800);
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

        /**
         * 实例化用户名组件，并通过add（）方法添加到当前窗口中去
         */
        LoginJLabel = new JLabel("登  陆  界  面");
        LoginJLabel.setFont(new Font("宋体",Font.BOLD,40));
        LoginJLabel.setBounds(450,100,400,50);
        add(LoginJLabel);

        UserNameJLabel = new JLabel("用户名");
        UserNameJLabel.setFont(new Font("宋体",Font.BOLD,30));
        UserNameJLabel.setBounds(300,250,200,50);
        add(UserNameJLabel);

        UserNameJTextField = new JTextField();
        UserNameJTextField.setFont(new Font("宋体",Font.BOLD,30));
        UserNameJTextField.setBounds(440,250,400,50);
        add(UserNameJTextField);

        PasswordJLabel = new JLabel("密  码");
        PasswordJLabel.setFont(new Font("宋体",Font.BOLD,30));
        PasswordJLabel.setBounds(300,400,200,50);
        add(PasswordJLabel);

        PasswordJPasswordField = new JPasswordField();
        PasswordJPasswordField.setFont(new Font("宋体",Font.BOLD,30));
        PasswordJPasswordField.setBounds(440,400,400,50);
        add(PasswordJPasswordField);

        LoginJButton = new JButton("登录");
        LoginJButton.setFont(new Font("宋体",Font.BOLD,30));
        LoginJButton.setBounds(450,550,100,50);
        add(LoginJButton);

        RegisterJButton = new JButton("注册");
        RegisterJButton.setFont(new Font("宋体",Font.BOLD,30));
        RegisterJButton.setBounds(650,550,100,50);
        add(RegisterJButton);

        ExitJButton = new JButton("退出");
        ExitJButton.setFont(new Font("宋体",Font.BOLD,30));
        ExitJButton.setBounds(1080,700,100,50);
        add(ExitJButton);

        //添加监听器
        LoginJButton.addActionListener(this);
        RegisterJButton.addActionListener(this);
        ExitJButton.addActionListener(this);
        /**
         * addKeyListener:键盘监听
         */
        UserNameJTextField.addKeyListener(this);
        PasswordJPasswordField.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == LoginJButton) {
            if (!UserNameJTextField.getText().equals("")
                    && !new String(PasswordJPasswordField.getPassword()).equals("")) {
                boolean aBoolean = judge.login(UserNameJTextField.getText(),
                        new String(PasswordJPasswordField.getPassword()));
                if (aBoolean) {
                    JOptionPane.showMessageDialog(getParent(),"登陆成功","登录提示框",
                            JOptionPane.INFORMATION_MESSAGE);
                    name = UserNameJTextField.getText();
                    this.dispose();
                    new MainFrame();
                }else {
                    JOptionPane.showMessageDialog(getParent(),"用户名或密码错误","登录提示框",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(getParent(),"用户名或密码不能为空","注册提示框",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (e.getSource() == RegisterJButton) {
            this.dispose();
            new RegisterFrame();
        }
        if (e.getSource() == ExitJButton) {
            //关闭窗口
            this.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //当按下回车键进入下一个文本框
        if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == UserNameJTextField) {
            e.getComponent().transferFocus();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == PasswordJPasswordField) {
            if (!UserNameJTextField.getText().equals("")
                    && !new String(PasswordJPasswordField.getPassword()).equals("")) {
                boolean aBoolean = judge.login(UserNameJTextField.getText(),
                        new String(PasswordJPasswordField.getPassword()));
                if (aBoolean) {

                    JOptionPane.showMessageDialog(getParent(),"登陆成功","登录提示框",
                            JOptionPane.INFORMATION_MESSAGE);
                    name = UserNameJTextField.getText();
                    this.dispose();
                    new MainFrame();
                }else {
                    JOptionPane.showMessageDialog(getParent(),"用户名或密码错误","登录提示框",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(getParent(),"用户名或密码不能为空","注册提示框",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
