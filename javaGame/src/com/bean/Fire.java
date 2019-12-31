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
 * Time：23:12
 * Week：星期日
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 *
 * 子弹类
 */
public class Fire {

    private BufferedImage img;
    private int x;//横坐标
    private int y;//纵坐标
    private int w;//宽
    private int h;//高
    private int dir;//移动的方向
    //hx,hy 是英雄机的坐标
    public Fire(int hx,int hy,int hw,int dir) {
        ImgDao imgdao =new ImgDao();
        //获取图片
        img= imgdao.getImage("/com/images/fire.png");
        //确定子弹大下
        w=img.getWidth()/5*2;
        h=img.getHeight()/5*2;
        //子弹位置在英雄机上
        x=hx+hw/2-w/2;
        y=hy;
        this.dir=dir;
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
    public int getDir() {
        return dir;
    }
    public void setDir(int dir) {
        this.dir = dir;
    }
}
