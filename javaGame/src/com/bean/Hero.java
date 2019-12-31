package com.bean;

import com.dao.ImgDao;

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
 * Time：23:14
 * Week：星期日
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 *
 * //定义英雄机的属性
 */
public class Hero {

    private BufferedImage img;
    private int x;//横坐标
    private int y;//纵坐标
    private int w;//宽
    private int h;//高
    private int hp;
    private int hl;//英雄机火力
    private int fsp;//子弹填充速度
    public Hero() {
        ImgDao imgdao =new ImgDao();
        //获取图片
        img= imgdao.getImage("/com/images/hero.png");
        //设置最开始的位置
        x=200;
        y=500;
        //确定大小,使用图片的大小。
        w=img.getWidth();
        h=img.getHeight();
        //确定游戏开始时英雄机的初始血量
        hp=3;
        //
        hl=1;
        //
        fsp=10;
    }
    public void moveToMouse(int x,int y) {
        this.x=x;
        this.y=y;
        if (this.x>512-w) {
            this.x=512-w;
        }
        if (this.x<0) {
            this.x=0;
        }
        if (this.y>768-h-30) {
            this.y=768-h-30;
        }
        if (this.y<0) {
            this.y=0;
        }

    }
    public void moveToKey(int x,int y) {
        this.x=x;
        this.y=y;
        if (this.x>512-w) {
            this.x=512-w;
        }
        if (this.x<0) {
            this.x=0;
        }
        if (this.y>768-h-30) {
            this.y=768-h-30;
        }
        if (this.y<0) {
            this.y=0;
        }
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
        if (this.hp>=4) {
            this.hp=4;
        }
    }
    public int getHl() {
        return hl;
    }
    public void setHl(int hl) {
        this.hl = hl;
        if (this.hl>=3) {
            this.hl=3;
        }
        if (this.hl<=1) {
            this.hl=1;
        }
    }
    public int getFsp() {
        return fsp;
    }
    public void setFsp(int fsp) {
        this.fsp = fsp;
        if (this.fsp<=6) {
            this.fsp=6;
        }
    }
}

