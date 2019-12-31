package com.frame.game.snake.panel;

import com.bean.User;
import com.dao.UserDao;
import com.frame.game.snake.MainSnakeFrame;
import com.frame.game.snake.SnakeGameFrame;
import com.frame.game.snake.SnakeScoreFrame;
import com.frame.login.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

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
 * Time：13:22
 * Week：星期五
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 */
public class SnakeGamePanel extends JPanel implements KeyListener, ActionListener {

    ImageIcon title = new ImageIcon(getClass().getResource("/com/images/title.jpg"));
    ImageIcon body = new ImageIcon(getClass().getResource("/com/images/body.jpg"));
    ImageIcon up = new ImageIcon(getClass().getResource("/com/images/up.jpg"));
    ImageIcon down = new ImageIcon(getClass().getResource("/com/images/down.jpg"));
    ImageIcon left = new ImageIcon(getClass().getResource("/com/images/left.jpg"));
    ImageIcon right = new ImageIcon(getClass().getResource("/com/images/right.jpg"));
    ImageIcon food = new ImageIcon(getClass().getResource("/com/images/food.jpg"));

    User user;
    UserDao userDao = new UserDao();
    String old = "";

    int len = 3;
    int score;
    int time = 150;
    int flag =1;
    int[] snakeX = new int[750];
    int[] snakeY = new int[750];
    String direction = "R";//方向
    boolean isStarted = false;
    boolean isFiled = false;
    Timer timer;
    int foodX;
    int foodY;
    Random random = new Random();
    SnakeGameFrame snakeGameFrame1;

    public SnakeGamePanel(SnakeGameFrame snakeGameFrame){
        initSnake();
        snakeGameFrame1 = snakeGameFrame;
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
        snakeGameFrame.addKeyListener(this);
    }

    public void paintComponent(Graphics g){



        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        title.paintIcon(this,g,25,11);

        g.fillRect(25,75,850,600);

        g.setColor(Color.WHITE);
        g.setFont(new Font("宋体",Font.BOLD,20));//设置字体
        g.drawString("Your highest score: " +user.getSnakeScore(),600,35);
        g.drawString("Current score: "+score,655,55);

        if (direction == "R"){
            right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (direction == "L"){
            left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (direction == "U"){
            up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (direction == "D"){
            down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        for (int i = 1; i < len; i++) {
            body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

        food.paintIcon(this,g,foodX,foodY);

        if (isStarted == false){
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial",Font.BOLD,40));
            g.drawString("Press Space to Start",300,300);
            g.drawString("Press ESC to exit",300,350);

        }
        if (isFiled){
            g.setColor(Color.RED);
            g.setFont(new Font("arial",Font.BOLD,40));
            g.drawString("Failed: Press Space to Start",200,300);
            g.drawString("Press ESC to exit",200,350);

            timer.stop();
            time = 150;
            flag = 1;
            if (user.getSnakeScore()<score){
                userDao.insertScore(score);
            }

        }
    }

    public void initSnake(){

        timer = new Timer(time,this);
        len = 3;//定义初始长度
        snakeX[0] = 100;
        snakeY[0] = 100;
        snakeX[1] = 75;
        snakeY[1] = 100;
        snakeX[2] = 50;
        snakeY[2] = 100;
        foodX = 25+25*random.nextInt(34);
        foodY = 75+25*random.nextInt(24);
        user = userDao.select(LoginFrame.name);
        direction = "R";
        old = "R";
        score = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStarted&&!isFiled){
            for (int i = len-1; i > 0; i--) {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            if (direction == "R"){
                snakeX[0] = snakeX[0] +25;
                if (snakeX[0]>850)isFiled = true;
            }else if (direction == "L"){
                snakeX[0] = snakeX[0] - 25;
                if (snakeX[0]<25)isFiled = true;
            }
            else if (direction == "U"){
                snakeY[0] = snakeY[0] - 25;
                if (snakeY[0]<75)isFiled = true;
            }else if (direction == "D"){
                snakeY[0] = snakeY[0] + 25;
                if (snakeY[0]>650) isFiled = true;

            }
            if (snakeX[0]==foodX&&snakeY[0]==foodY){
                int j;
                boolean a;
                len++;
                score += 10;
                do {
                    a = false;
                    foodX = 25+25*random.nextInt(34);
                    foodY = 75+25*random.nextInt(24);
                    for (j = 0;j < 750;j++){
                        if (snakeX[j] ==foodX && snakeY[j] == foodY) {
                            a = true;
                        }
                    }
                }while (a);

                if (score>50*flag&&time>=50){
                    time = time - 50;
                    timer.setDelay(time);
                    flag++;
                }

            }



            for (int i = 1; i < len; i++) {
                if (snakeX[i]==snakeX[0]&&snakeY[i]==snakeY[0]){
                    isFiled = true;
                }
            }
            repaint();
        }



        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE){
            if (isFiled){
                isFiled = false;
                initSnake();
                timer.start();
            }else {
                isStarted = !isStarted;
            }
            repaint();//重画
        }else if (keyCode == KeyEvent.VK_LEFT && old != "R"){
            direction = "L";old = direction;
        }else if (keyCode == KeyEvent.VK_RIGHT && old != "L"){
            direction = "R";old = direction;
        }else if (keyCode == KeyEvent.VK_UP && old != "D"){
            direction = "U";old = direction;
        }else if (keyCode == KeyEvent.VK_DOWN && old != "U"){
            direction = "D";old = direction;
        }else if (keyCode == KeyEvent.VK_ESCAPE) {
            snakeGameFrame1.dispose();
            new MainSnakeFrame("贪吃蛇");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
