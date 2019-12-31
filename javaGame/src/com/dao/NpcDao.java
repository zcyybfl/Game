package com.dao;

import com.bean.Npc;

import java.util.ArrayList;
import java.util.List;

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
 * Time：23:20
 * Week：星期日
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 */
public class NpcDao {
    //创建敌机集合
    List<Npc> npcs=new ArrayList<Npc>();
    int index=0;//记录执行的次数
    public  void npcEnter(int nd) {
        index++;
        if (index>=nd) {
            //1.创建敌机
            Npc npc = new Npc();
            //2.放入集合中
            npcs.add(npc);
            index=0;
        }

    }
    //敌机移动的方法
    public void npcMove(int x,int y,int nd) {
        for (int i = 0; i < npcs.size(); i++) {
            Npc npc =npcs.get(i);
            if (npc.getIndex()<=4) {
                if (npc.getX()>x+npc.getSp()&&npc.getY()<=y) {
                    npc.setX(npc.getX()-npc.getSp());
                }
                else if (npc.getX()<x-npc.getSp()&&npc.getY()<=y)
                {
                    npc.setX(npc.getX()+npc.getSp());
                }
                if (npc.getY()>=y) {
                    npc.setY(npc.getY()+npc.getSp());
                }
                else if (npc.getY()<y-nd) {
                    npc.setY(npc.getY()+npc.getSp());
                }
            }
            else {
                npc.setY(npc.getY()+npc.getSp());
            }

        }
    }
    public List<Npc> getNpcs() {
        return npcs;
    }
    public void setNpcs(List<Npc> npcs) {
        this.npcs = npcs;
    }

}

