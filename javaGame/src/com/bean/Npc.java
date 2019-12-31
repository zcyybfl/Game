package com.bean;

import com.dao.ImgDao;

import java.awt.image.BufferedImage;
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
 * Date：2019/12/29
 * Time：23:15
 * Week：星期日
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 *
 * 敌机类
 * 游戏中的敌机：
 * 敌机数量多，java中存放多个数据：使用数组，使用集合。
 * 数量也未知
 */
public class Npc {
    BufferedImage img;
    private int x;//敌机横坐标
    private int y;
    private int w;
    private int h;
    private int sp;
    private int hp;
    private int index;
    private int npcscore;//每个敌机的分数
    public  Npc() {
        //初始化随机数
        Random rd = new Random();
        //生成一个随机数
        index = rd.nextInt(15)+1;
        ImgDao imgDao=new ImgDao();
        String pathString="/com/images/ep"+(index<10?"0":"")+index+".png";
        img = imgDao.getImage(pathString);
        w=img.getWidth();
        h=img.getHeight();
        //确定位置
        //nextInt(num),表示在[0，num)之间的数
        x=rd.nextInt(512-w);
        y=-h/2;
        //确定大小

        sp=17-index;
        hp=index/2+1;
        if (index==15) {
            hp=1000;
            sp=15;
        }
        npcscore=index;
    }
    public boolean shootBy(Fire fire) {

        boolean hit=x<=fire.getX()+fire.getW()&&x>=fire.getX()-w&&y<=fire.getY()+fire.getH()&&y>=fire.getY()-h;
        return hit;

    }
    public boolean hitBy(Hero hero) {

        boolean hit=x<=hero.getX()+hero.getW()&&x>=hero.getX()-w&&y<=hero.getY()+hero.getH()&&y>=hero.getY()-h;
        return hit;

    }
    public void movex () {

    }
    public void movey () {

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
    public int getSp() {
        return sp;
    }
    public void setSp(int sp) {
        this.sp = sp;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public int getNpcscore() {
        return npcscore;
    }
    public void setNpcscore(int npcscore) {
        this.npcscore = npcscore;
    }
}