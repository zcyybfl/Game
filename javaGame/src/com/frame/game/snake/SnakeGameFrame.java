package com.frame.game.snake;

import com.frame.game.snake.panel.SnakeGamePanel;

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
 * Date：2019/12/27
 * Time：13:18
 * Week：星期五
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 */
public class SnakeGameFrame extends JFrame {

    public SnakeGameFrame(String str) {
        this.setTitle(str);
        this.setSize(900, 720);
        this.setLocationRelativeTo(getOwner());
        ImageIcon Image = new ImageIcon(getClass().getResource("/com/images/JavaGameLogo.jpg"));
        setIconImage(Image.getImage());
        this.setResizable(false);
        this.add(new SnakeGamePanel(this));
        this.setVisible(true);
    }
}
