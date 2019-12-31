package com.frame.game.fivechess;

import com.frame.main.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

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
 * Date：2019/12/25
 * Time：13:23
 * Week：星期三
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 */
public class FiveChessFrame extends JFrame implements MouseListener,Runnable {

    /**
     * 获取显示器宽度和高度
     */
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    //背景图片
    ImageIcon BackgroundImage = new ImageIcon(getClass().getResource("/com/images/background.jpg"));
    //保存棋子的坐标
    int X = 0,Y = 0;
    //保存之前下过的全部棋子的坐标 0--空白棋子 1--黑色棋子 2--白色棋子
    int [][] Chessboard = new int[15][15];
    //标记当前下棋颜色
    boolean isBlack = true;//默认true为黑棋下棋
    //当前游戏是否可以继续
    boolean canPlay = true;//默认可以继续
    int num = 0;//下棋个数
    //保留显示的提示信息
    String message = "黑棋先行";
    //保存可下棋时间
    int maxTime = 0;//最大时间
    int blackTime = 0;
    int whiteTime = 0;   //保存黑白方所剩余的时间
    //双方剩余时间显示信息
    String blackMessage = "无限制";
    String whiteMessage = "无限制";
    //保存棋谱，记录双方每一步落子的位置
    int[] chessX = new int[255];
    int[] chessY = new int[255];
    int countX = 0,countY = 0;
    //悔棋次数
    int maxRegret = 0;
    int whiteRegret = 0;
    int blackRegret = 0;
    //倒计时线程
    Thread thread = new Thread(this);

    public FiveChessFrame(String str) {
        //设置标题
        this.setTitle(str);
        //设置窗口大小
        this.setSize(800,800);
        //设置窗口所在位置
        this.setLocation((width - 800) / 2,(height - 800) / 2);
        //窗体大小不可变
        this.setResizable(false);
        //将窗口的关闭方式设置为默认关闭后程序结束
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //repaint()方法表示重新执行一次paint()方法
        this.repaint();
        ImageIcon Image = new ImageIcon(getClass().getResource("/com/images/JavaGameLogo.jpg"));
        setIconImage(Image.getImage());
        //监听器
        this.addMouseListener(this);
        //显示窗口
        this.setVisible(true);
        thread.start();
        thread.suspend();
    }

    @Override
    public void paint(Graphics g) {
        //双缓冲技术，解决屏幕闪烁
        BufferedImage bufferedImage = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
        Graphics graphics =  bufferedImage.createGraphics();  // 创建画笔
        graphics.setColor(Color.BLACK);
        //绘制背景
        BackgroundImage.paintIcon(this,graphics,0,0);
        //输出标题信息
        graphics.setFont(new Font("黑体",Font.BOLD,30));
        graphics.drawString("游戏信息:      " + message,150,70);
        graphics.drawString("菜单栏",670,70);
        graphics.setFont(new Font("宋体",Font.BOLD,20));
        //输出时间信息
        graphics.drawRect(50,720,284,40);
        graphics.drawRect(354,720,284,40);
        graphics.drawString(" 黑方时间：" + blackMessage,50,750);
        graphics.drawString(" 白方时间：" + whiteMessage,350,750);

        //绘制棋盘
        for (int i = 0;i < 15;i++) {
            graphics.drawLine(50,100 + 42 * i,638,100 + 42 * i);
            graphics.drawLine(50 + 42 * i,100,50 + 42 * i,688);
        }

        //标注点位,点位要减去宽度的一半
        graphics.fillOval(173,223,6,6);
        graphics.fillOval(509,223,6,6);
        graphics.fillOval(173,559,6,6);
        graphics.fillOval(509,559,6,6);
        graphics.fillOval(341,391,6,6);

        //绘制棋子
        for (int i = 0;i < 15;i++) {
            for (int j = 0;j < 15;j++) {
                if (Chessboard[i][j] == 1) {
                    //黑棋
                    int tempX = i * 42 + 50;
                    int tempY = j * 42 + 100;
                    graphics.setColor(Color.BLACK);
                    graphics.fillOval(tempX - 20,tempY - 20,40,40);//实心圆
                    graphics.drawOval(tempX - 20,tempY - 20,40,40);//空心圆
                    if (i == chessX[countX - 1] && j == chessY[countY - 1]) {
                        graphics.setColor(Color.RED);
                        graphics.drawOval(tempX - 20,tempY - 20,40,40);//空心圆
                    }
                }
                if (Chessboard[i][j] == 2) {
                    //白棋
                    int tempX = i * 42 + 50;
                    int tempY = j * 42 + 100;
                    //白色实心圆
                    graphics.setColor(Color.WHITE);
                    graphics.fillOval(tempX - 20,tempY - 20,40,40);
                    //黑色空心圆
                    graphics.setColor(Color.BLACK);
                    graphics.drawOval(tempX - 20,tempY - 20,40,40);
                    if (i == chessX[countX - 1] && j == chessY[countY - 1]) {
                        graphics.setColor(Color.RED);
                        graphics.drawOval(tempX - 20,tempY - 20,40,40);//空心圆
                    }
                }
            }
        }
        graphics.setColor(Color.BLACK);
        //绘制菜单
        graphics.drawRect(670,100,100,40);
        graphics.drawString("重新开始",680,125); //重新开始按钮
        graphics.drawRect(670,180,100,40);
        graphics.drawString("游戏设置",680,205); //游戏设置按钮
        graphics.drawRect(670,260, 100, 40);
        graphics.drawString("游戏说明", 680, 285); // 游戏说明按钮
        graphics.drawRect(670, 340, 100, 40);
        graphics.drawString("其他游戏", 680, 365);  // 其他游戏按钮
        graphics.drawRect(670, 420, 100, 40);
        graphics.drawString("退出游戏", 680, 445);  // 退出游戏按钮
        graphics.drawRect(670, 568, 100, 40);
        graphics.drawString("悔棋", 700, 594);  // 悔棋戏按钮
        graphics.drawRect(670, 648, 100, 40);
        graphics.drawString("认输", 700, 674);  // 认输按钮

        //将按钮画出来
        g.drawImage(bufferedImage,0,0,this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //棋盘有效位置
        X = e.getX();
        Y = e.getY();
        //可以下棋
        if (canPlay == true) {
            if (X >= 50 && X <= 638 && Y >= 100 && Y <= 688) {
                //判断下棋位置
                if ((X - 50) % 42 > 21) {
                    X = (X - 50) / 42 + 1;
                }else {
                    X = (X - 55) / 42;
                }
                if ((Y - 100) % 42 > 21) {
                    Y = (Y - 100) / 42 + 1;
                }else {
                    Y = (Y -100) / 42;
                }
                //判断下棋颜色
                if (Chessboard[X][Y] == 0) {
                    chessX[countX++] = X;
                    chessY[countY++] = Y;
                    if (isBlack) {
                        Chessboard[X][Y] = 1;
                        isBlack = false;
                        message = "轮到白方";
                    }else {
                        Chessboard[X][Y] = 2;
                        isBlack = true;
                        message = "轮到黑方";
                    }
                    //repaint()方法表示重新执行一次paint()方法
                    this.repaint();
                    //判断游戏是否结束
                    boolean flag = this.JudgeWin();
                    if (flag) {
                        JOptionPane.showMessageDialog(this,"游戏结束，"
                                + (Chessboard[X][Y] == 1 ? "黑棋":"白棋" ) + "获胜!");
                        canPlay = false;
                    }else {
                        num = 0;
                        for (int i = 0;i < 15;i++) {
                            for (int j = 0;j < 15;j++) {
                                if (Chessboard[i][j] != 0) {
                                    num++;
                                }
                            }
                        }
                        //平局
                        if (num == 15 * 15) {
                            JOptionPane.showMessageDialog(this,"游戏结束，双方和棋");
                            canPlay = false;
                        }
                    }
                }
            }
        }
        //按钮
        int result;
        //重新开始
        if (X >= 670 && X <= 770 && Y >= 100 && Y <= 140) {
            result = JOptionPane.showConfirmDialog(this, "是否重新开始游戏？",
                    "重新开始",JOptionPane.YES_OPTION);
            if (result == 0) {
                NewGame();
            }
        }
        //游戏设置
        if (X >= 670 && X <= 770 && Y >= 180 && Y <= 220) {
            Object[] ChooseGame = {"游戏时间","悔棋次数","取消"};
            Object[] RegretGame = {0,1,2,3};
            Object[] TimeGame = {"无限制","5分钟","10分钟","30分钟","自定义"};
            int Choose = JOptionPane.showOptionDialog(this,"游戏设置",
                    "游戏设置",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,
                    null,ChooseGame,ChooseGame[0]);
            if (Choose == 0) {
                String Time = (String) JOptionPane.showInputDialog(this,
                        "请选择游戏时间（分钟）:\n", "游戏设置",JOptionPane.PLAIN_MESSAGE,
                        null,TimeGame,"无限制");
                switch (Time) {
                    case "无限制":maxTime = 0;break;
                    case "5分钟":maxTime = 5 * 60;break;
                    case "10分钟":maxTime = 10 * 60;break;
                    case "30分钟":maxTime = 30 * 60;break;
                    case "自定义":Time = JOptionPane.showInputDialog("请输入游戏的最大时间(分钟),输入0，时间无限制：");
                        maxTime = Integer.parseInt(Time) * 60;
                        break;
                }
                try {
                    if (maxTime < 0) {
                        JOptionPane.showMessageDialog(this,"请输入正确时间,不允许输入负数");
                    }
                    if (maxTime >= 0) {
                        result = JOptionPane.showConfirmDialog(this,"设置完成,是否重新开始",
                                null,JOptionPane.YES_NO_OPTION);
                        if (result == 0) {
                            NewGame();
                        }
                    }
                }catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(this,"请输入正确时间");
                }
            }else if (Choose == 1) {
                int Regret = (int) JOptionPane.showInputDialog(this,"请选择悔棋次数:\n",
                        "游戏设置",JOptionPane.PLAIN_MESSAGE,null,RegretGame,"0");
                maxRegret = Regret;
                result = JOptionPane.showConfirmDialog(this,"设置完成,是否重新开始",
                        null,JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    NewGame();
                }
            }
        }
        //游戏规则
        if (X >= 670 && X <= 770 && Y >= 260 && Y <= 300) {
            JOptionPane.showMessageDialog(this, "规则:横竖斜先连成五子者获胜!");
        }
        //其他游戏
        if (X >= 670 && X <= 770 && Y >= 340 && Y <= 380) {
            this.dispose();
            new MainFrame();
        }
        //退出
        if (X >= 670 && X <= 770 && Y >= 420 && Y <= 460) {
            this.dispose();
        }
        //悔棋
        if (X >= 670 && X <= 770 && Y >= 568 && Y <= 608) {
            if (canPlay) {
                if ((isBlack == true ? blackRegret : whiteRegret) < maxRegret) {
                    result = JOptionPane.showConfirmDialog(this,
                            (isBlack == true ?  "白方悔棋,黑方是否同意？" : "黑方悔棋，白方是否同意？")
                                    + "剩余悔棋次数：" + (isBlack == true ? (maxRegret - blackRegret)
                                    : (maxRegret - whiteRegret) + "次"));
                    if (result == 0) {
                        Chessboard[chessX[--countX]][chessY[--countY]] = 0;
                        if (isBlack) {
                            message = "轮到白方";
                            blackRegret++;
                            isBlack = false;
                        }else {
                            message = "轮到黑方";
                            whiteRegret++;
                            isBlack = true;
                        }
                    }
                    this.repaint();
                }else {
                    JOptionPane.showMessageDialog(this,(isBlack == true ? "黑棋" : "白棋")
                            + "剩余悔棋次数：" + (isBlack == true ? (maxRegret - blackRegret)
                            : (maxRegret - whiteRegret)) + "次,无法悔棋");
                }
            }else {
                JOptionPane.showMessageDialog(this, "游戏结束，无法悔棋");
            }
        }
        //认输
        if (X >= 670 && X <= 770 && Y >= 648 && Y <= 688) {
            if (canPlay) {
                result = JOptionPane.showConfirmDialog(this,"是否认输？");
                if (result == 0) {
                    JOptionPane.showMessageDialog(this,
                            (isBlack == true?"黑棋":"白棋") + "认输,游戏结束");
                    canPlay = false;
                }
            }else {
                JOptionPane.showMessageDialog(this, "游戏已结束，无法认输");
            }
        }
    }

    //重新开始游戏
    public void NewGame(){
        //清空棋盘棋子
        for (int i = 0;i < 15;i++) {
            for (int j = 0;j < 15;j++) {
                Chessboard[i][j] = 0;
            }
        }
        //清空下棋棋子坐标的记录
        for (int i = 0; i < 15; i++) {
            chessX[i] = 0;
            chessY[i] = 0;
        }
        countX =0;
        countY =0;
        //重置悔棋次数
        blackRegret = 0;
        whiteRegret = 0;
        //重置提示信息
        message = "黑棋先行";
        //黑棋先开始
        isBlack = true;
        //可以游戏
        canPlay = true;
        blackTime = maxTime;
        whiteTime = maxTime;
        if (maxTime == 0) {
            blackMessage = "无限制";
            whiteMessage = "无限制";
        }else {
            blackMessage = (maxTime / 3600) + ":"
                    + (maxTime / 60 - maxTime / 3600 * 60) + ":"
                    + (maxTime - maxTime / 60 * 60);
            whiteMessage = (maxTime / 3600) + ":"
                    + (maxTime / 60 - maxTime / 3600 * 60) + ":"
                    + (maxTime - maxTime / 60 * 60);
        }
        thread.resume();
        this.repaint();
    }

    private boolean JudgeWin() {
        //棋子颜色
        int color = Chessboard[X][Y];//1--黑色  2--白色
        //保存共同颜色棋子个数
        int count = 1;//默认一个
        boolean flag = false;
        count = this.checkCount(1,0,color);//横
        if (count >= 5) {
            flag = true;
        }else {
            count = this.checkCount(0,1,color);//竖
            if (count >= 5) {
                flag = true;
            }else {
                count = this.checkCount(1,-1,color);//判断右上,左下
                if (count >= 5) {
                    flag = true;
                }else {
                    count = this.checkCount(1,1,color);//判断右下,左上
                    if (count >= 5) {
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }

    //判断棋子连接的数量
    public int checkCount(int xChange , int yChange ,int color) {
        int count = 1;
        int tempX = xChange;
        int tempY = yChange;  //保存初始值

        //全局变量x,y最初为鼠标点击的坐标，
        //经下棋方法已经将x,y的范围变成0-14(遍历整个棋盘,寻找相同颜色的棋子)
        while (X + xChange >= 0 && X + xChange < 15
                && Y + yChange >= 0 && Y + yChange < 15
                && color == Chessboard[X + xChange][Y + yChange]) {
            count++;
            if (xChange != 0) {
                xChange++;
            }
            if (yChange != 0) {
                if (yChange > 0) {
                    yChange++;//使棋子沿着右下一条线移动，进行判断
                }else {
                    yChange--;// 使棋子沿着右上一条线移动，进行判断
                }
            }
        }
        xChange = tempX;
        yChange = tempY;
        while (X - xChange >= 0 && X - xChange < 15
                && Y - yChange >= 0 && Y - yChange < 15
                && color == Chessboard[X - xChange][Y - yChange]) {
            count++;
            if (xChange != 0) {
                xChange++;
            }
            if (yChange != 0) {
                if (yChange > 0) {
                    yChange++;//使棋子沿着左上一条线移动，进行判断
                }else {
                    yChange--;//使棋子沿着左下一条线移动，进行判断
                }
            }
        }
        return count;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void run() {
        //判断是否有时间的限制
        if (maxTime > 0) {
            while (true) {
                if (isBlack) {
                    blackTime--;
                    if (blackTime == 0) {
                        JOptionPane.showMessageDialog(this,"黑棋超时，白棋获胜");
                        canPlay = false;
                    }
                }else {
                    whiteTime--;
                    if (whiteTime == 0) {
                        JOptionPane.showMessageDialog(this,"白棋超时，黑棋获胜");
                        canPlay = false;
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                blackMessage = (blackTime / 3600) + ":"
                        + (blackTime / 60 - blackTime / 3600 * 60) + ":"
                        + (blackTime - blackTime / 60 * 60);
                whiteMessage = (whiteTime / 3600) + ":"
                        + (whiteTime / 60 - whiteTime / 3600 * 60) + ":"
                        + (whiteTime - whiteTime / 60 * 60);
                this.repaint();
            }
        }else {

        }
    }
}
