package com.frame.register;

import com.frame.judge.Judge;
import com.frame.login.LoginFrame;

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
 * Time：14:59
 * Week：星期三
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 * 注册界面
 */
public class RegisterFrame extends JFrame implements KeyListener, ActionListener {

    private JLabel UserNameJLabel,PasswordJLabel,RegisterJLabel,EnterPasswordJLabel;
    private JTextField UserNameJTextField;
    private JPasswordField PasswordJPasswordField,EnterPasswordJPasswordField;
    private JButton ReturnJButton,RegisterJButton,ExitJButton;
    private ImageIcon Image;

    Judge judge = new Judge();

    public RegisterFrame() {
        this.setTitle("注册界面");
        this.setSize(1200,800);
        this.setLocationRelativeTo(getOwner());
        init();
        this.setVisible(true);
    }

    private void init() {

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
        RegisterJLabel = new JLabel("注  册  界  面");
        RegisterJLabel.setFont(new Font("宋体",Font.BOLD,40));
        RegisterJLabel.setBounds(450,100,400,50);
        add(RegisterJLabel);

        UserNameJLabel = new JLabel("用户名");
        UserNameJLabel.setFont(new Font("宋体",Font.BOLD,30));
        UserNameJLabel.setBounds(300,230,200,50);
        add(UserNameJLabel);

        UserNameJTextField = new JTextField();
        UserNameJTextField.setFont(new Font("宋体",Font.BOLD,30));
        UserNameJTextField.setBounds(440,230,400,50);
        add(UserNameJTextField);

        PasswordJLabel = new JLabel("密  码");
        PasswordJLabel.setFont(new Font("宋体",Font.BOLD,30));
        PasswordJLabel.setBounds(300,340,200,50);
        add(PasswordJLabel);

        PasswordJPasswordField = new JPasswordField();
        PasswordJPasswordField.setFont(new Font("宋体",Font.BOLD,30));
        PasswordJPasswordField.setBounds(440,340,400,50);
        add(PasswordJPasswordField);

        EnterPasswordJLabel = new JLabel("确认密码");
        EnterPasswordJLabel.setFont(new Font("宋体",Font.BOLD,30));
        EnterPasswordJLabel.setBounds(285,450,200,50);
        add(EnterPasswordJLabel);

        EnterPasswordJPasswordField = new JPasswordField();
        EnterPasswordJPasswordField.setFont(new Font("宋体",Font.BOLD,30));
        EnterPasswordJPasswordField.setBounds(440,450,400,50);
        add(EnterPasswordJPasswordField);


        RegisterJButton = new JButton("注册");
        RegisterJButton.setFont(new Font("宋体",Font.BOLD,30));
        RegisterJButton.setBounds(450,580,100,50);
        add(RegisterJButton);

        ReturnJButton = new JButton("返回");
        ReturnJButton.setFont(new Font("宋体",Font.BOLD,30));
        ReturnJButton.setBounds(650,580,100,50);
        add(ReturnJButton);

        ExitJButton = new JButton("退出");
        ExitJButton.setFont(new Font("宋体",Font.BOLD,30));
        ExitJButton.setBounds(1080,700,100,50);
        add(ExitJButton);

        //监听器
        ReturnJButton.addActionListener(this);
        RegisterJButton.addActionListener(this);
        ExitJButton.addActionListener(this);
        /**
         * addKeyListener:键盘监听
         */
        UserNameJTextField.addKeyListener(this);
        PasswordJPasswordField.addKeyListener(this);
        EnterPasswordJPasswordField.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == RegisterJButton) {
            if (!UserNameJTextField.getText().equals("")
                    && !new String(PasswordJPasswordField.getPassword()).equals("")
                    && !new String(EnterPasswordJPasswordField.getPassword()).equals("")) {
                if (new String(PasswordJPasswordField.getPassword())
                        .equals(new String(EnterPasswordJPasswordField.getPassword()))) {
                    boolean aBoolean = judge.register(UserNameJTextField.getText(),
                            new String(PasswordJPasswordField.getPassword()));
                    if (aBoolean) {
                        JOptionPane.showMessageDialog(getParent(),"注册成功","注册提示框",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        new LoginFrame();
                    }else {
                        JOptionPane.showMessageDialog(getParent(),"该用户名已存在","注册提示框",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(getParent(),"两次密码不相同","注册提示框",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(getParent(),"用户名或密码不能为空","注册提示框",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (e.getSource() == ReturnJButton) {
            this.dispose();
            new LoginFrame();
        }
        if (e.getSource() == ExitJButton) {
            this.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER
                && (e.getSource() == UserNameJTextField || e.getSource() == PasswordJPasswordField)) {
            e.getComponent().transferFocus();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == EnterPasswordJPasswordField) {
            if (!UserNameJTextField.getText().equals("")
                    && !new String(PasswordJPasswordField.getPassword()).equals("")
                    && !new String(EnterPasswordJPasswordField.getPassword()).equals("")) {
                if (new String(PasswordJPasswordField.getPassword())
                        .equals(new String(EnterPasswordJPasswordField.getPassword()))) {
                    boolean aBoolean = judge.register(UserNameJTextField.getText(),
                            new String(PasswordJPasswordField.getPassword()));
                    if (aBoolean) {
                        JOptionPane.showMessageDialog(getParent(),"注册成功","注册提示框",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        new LoginFrame();
                    }else {
                        JOptionPane.showMessageDialog(getParent(),"该用户名已存在","注册提示框",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(getParent(),"两次密码不相同","注册提示框",
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
