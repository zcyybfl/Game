package com.frame.game.plane.panel;

import com.bean.Fire;
import com.bean.Hero;
import com.bean.Npc;
import com.bean.User;
import com.dao.*;
import com.frame.game.plane.MainPlaneFrame;
import com.frame.game.plane.PlaneGameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
 * Date：2019/12/29
 * Time：23:55
 * Week：星期日
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 * 游戏面板：JPanel
 * 1：写一个类继承JPanel
 * 2：写构造方法，初始化属性
 * 画图片：
 * 1.在类中定义图片，并取名
 * 2.在构造方法中，调用工具初始化图片
 * 3.在画图方法paint中画图片
 */
public class PlaneGamePanel extends JPanel {

    //定义背景图
    ImageIcon bg = new ImageIcon(getClass().getResource("/com/images/bg1.jpg"));
    Hero hero = new Hero();
    //创建敌机集合
    NpcDao npcdao = new NpcDao();
    //子弹
    FireDao firedao = new FireDao();
    HeroDao heroDao = new HeroDao();
    UserDao userDao = new UserDao();
    User user = new User();
    //定义分数
    public  int  score=450;
    //定义暂停键
    public boolean stop;
    public int a= 0;
    public int nd= 20;//敌机出现
    //游戏的开关
    boolean gameover;//游戏开始时，gameover为false，结束时为true
    //开始游戏的方法
    public void action() {
        //创建并启动一个线程来控制游戏场景中的物体活动
        //new Thread(){public void run(){....线程要做的事。。。。。}}.start();
        new Thread() {
            public void run() {
                //让敌机一直出现，
                while(true) {
                    if (!gameover&&!stop) {
                        //调用敌机入场
                        npcdao.npcEnter(nd);
                        //敌机移动的方法
                        npcdao.npcMove(hero.getX(),hero.getY(),2);
                        //发射子弹
                        firedao.shoot(hero);
                        //子弹移动
                        firedao.firemove();
                        //判断子弹是否打到敌机
                        score+=firedao.shootNpc(npcdao.getNpcs(),hero);
                        //判断敌机是否撞到英雄级
                        if (heroDao.hit(npcdao.getNpcs(),hero)) {
                            gameover=true;
                            a=1;
                        }
                    }
                    else {
                        if(user.getPlaneScore()<score&&a==1) {
                            userDao.updatePlane(score);
                            a=0;
                        }
                    }
                    if (score>=400) {
                        nd=16;
                        bg = new ImageIcon(getClass().getResource("/com/images/bg2.jpg"));
                    }
                    else  if (score>=800) {
                        nd=14;
                        bg = new ImageIcon(getClass().getResource("/com/images/bg3.jpg"));
                    }else  if (score>=1200) {
                        nd=12;
                        bg = new ImageIcon(getClass().getResource("/com/images/bg4.jpg"));
                    }else  if (score>=1600) {
                        nd=10;
                        bg = new ImageIcon(getClass().getResource("/com/images/bg5.jpg"));
                    }
                    //每执行一次休息一会
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            }
        }.start();
    }
    //构造方法：确定特点
    public PlaneGamePanel(PlaneGameFrame gFrame) {
        //初始化暂停键
        stop=false;
        //初始化结束键
        gameover=false;
        //初始化分数
        score=0;
        //使用鼠标监听，固定格式：
        //创建鼠标适配器
        MouseAdapter adapter =new MouseAdapter() {
            //2.确定需要鼠标监听的事件：事件有：mouseMoved（）监听移动
            //                               mouseCliked（）监听鼠标单击
            //                               mousePressed（）监听鼠标按下去
            //                               mouseEnterd（）监听鼠标移入游戏界面
            //                               mouseExited（）监听鼠标移出游戏界面
            @Override
            public void mouseClicked(MouseEvent e) {
                if (gameover) {
                    //重新创造一个
                    hero=new Hero();
                    gameover=false;
                    score=0;
                    //清空子弹和敌机集合
                    npcdao.getNpcs().clear();
                    firedao.getFires().clear();
                    repaint();
                }
            }
            //MouseEvent 鼠标的事件，记录鼠标做的事情
            public void mouseMoved(MouseEvent e) {
                //执行那些操作
                //让英雄机跟着鼠标移动
                //获取鼠标的横纵坐标
                //super.mouseMoved(e);
                int mx  =e.getX()-hero.getW()/2;
                int my = e.getY()-hero.getH()/2;
                if (!gameover&&!stop) {
                    hero.moveToMouse(mx, my);
                }

                //刷新界面：从新调用paint方法
                repaint();
            }
        };
        //3.将适配器加入到监听器中
        addMouseListener(adapter);
        addMouseMotionListener(adapter);
        //使用键盘监听器
        //1.创建键盘适配器
        KeyAdapter kAdapter  =new KeyAdapter() {
            //2.确定需要监听的键盘事件
            public void keyPressed(KeyEvent e) {
                //当按下键盘的时候，会触发的方法
                //监听键盘的按键，每个按键对应一个数值
                //获取键盘上按键的数值
                int keyCode=e.getKeyCode();
                int kx=hero.getX();
                int ky =hero.getY();
                if (keyCode==KeyEvent.VK_UP) {
                    //按了上键,向上移动
                    ky-=10;
                }else if (keyCode==KeyEvent.VK_DOWN) {
                    //按了下键,向下移动
                    ky+=10;
                }else if (keyCode==KeyEvent.VK_LEFT) {
                    //按了左键,向左移动
                    kx-=10;
                }else if (keyCode==KeyEvent.VK_RIGHT) {
                    //按了右键,向右移动
                    kx+=10;
                }
                else if (keyCode==KeyEvent.VK_SPACE) {
                    stop=!stop;
                }
                else if (keyCode==KeyEvent.VK_ESCAPE) {
                    gFrame.dispose();
                    new MainPlaneFrame("飞机大战");
                }
                hero.moveToKey(kx, ky);
                repaint();
            }
        };
        //3.添加适配器到窗体键盘监听器中
        gFrame.addKeyListener(kAdapter);
        //user=userDao.select(LoginFrame.name);
    }
    //专用画图方法，Graphics g是画笔
    public void paint(Graphics g) {
        //双缓冲技术，解决屏幕闪烁
        BufferedImage bufferedImage = new BufferedImage(512, 768,BufferedImage.TYPE_INT_ARGB);
        Graphics graphics =  bufferedImage.createGraphics();  // 创建画笔
        graphics.setColor(Color.BLACK);
        super.paint(graphics);
        bg.paintIcon(this,graphics,0,0);
        graphics.drawImage(hero.getImg(), hero.getX(), hero.getY(),hero.getW(),hero.getH(), this);
        //遍历敌机集合，画出所有的敌机
        for (int i = 0; i < npcdao.getNpcs().size(); i++) {
            //获取每个敌机
            Npc npc =npcdao.getNpcs().get(i);
            graphics.drawImage(npc.getImg(), npc.getX(), npc.getY(), npc.getW(), npc.getH(), this);
        }
        for (int i = 0; i < firedao.getFires().size(); i++) {
            Fire fire = firedao.getFires().get(i);
            graphics.drawImage(fire.getImg(), fire.getX(), fire.getY(), fire.getW(), fire.getH(),this);
        }
        //画分数
        graphics.setColor(Color.pink);
        graphics.setFont(new Font("楷体",Font.BOLD,25));
        graphics.drawString("分数："+score, 10, 30);
        //画血量
        for (int i = 0; i <hero.getHp(); i++) {
            graphics.drawImage(hero.getImg(), 350+i*40, 20, 30, 30,this);
        }
        //当游戏结束时画出游戏结束
        if (gameover==true) {
            graphics.setColor(Color.black);
            graphics.setFont(new Font("楷体",Font.BOLD,35));
            graphics.drawString("GameOver", 180, 300);
        }
        if (stop==true) {
            graphics.setColor(Color.black);
            graphics.setFont(new Font("楷体",Font.BOLD,35));
            graphics.drawString("游戏暂停了按空格键继续", 60, 300);
        }
        g.drawImage(bufferedImage,0,0,this);
    }
}
