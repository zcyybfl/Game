package com.dao;

import com.bean.Hero;
import com.bean.Npc;

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
public class HeroDao {
    public boolean hit(List<Npc> npcs, Hero hreo) {
        for (int i = 0; i <npcs.size(); i++) {
            //获取一个人敌机
            Npc npc=npcs.get(i);
            if (npc.hitBy(hreo)) {
                //撞上了
                npcs.remove(npc);
                hreo.setHp(hreo.getHp()-1);
                hreo.setHl(hreo.getHl()-1);//火力
                if (hreo.getHp()<=0) {
                    return true;
                }
            }
        }
        return false;
    }
}
