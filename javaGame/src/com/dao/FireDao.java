package com.dao;

import com.bean.Fire;
import com.bean.Hero;
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
 * Time：23:19
 * Week：星期日
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 */
public class FireDao {
    List<Fire> fires = new ArrayList<Fire>();
    //创建子弹画出
    int index = 0;

    public void shoot(Hero hero) {
        index++;
        if (index >= hero.getFsp()) {
            if (hero.getHl() == 1) {
                Fire fire = new Fire(hero.getX(), hero.getY() - 20, hero.getW(), 1);
                fires.add(fire);
            }
            //2
            else if (hero.getHl() == 2) {
                Fire fire2 = new Fire(hero.getX() - hero.getW() / 3, hero.getY(), hero.getW(), 1);
                fires.add(fire2);
                Fire fire3 = new Fire(hero.getX() + hero.getW() / 3, hero.getY(), hero.getW(), 1);
                fires.add(fire3);
            }
            //3
            else {
                Fire fire = new Fire(hero.getX(), hero.getY() - 20, hero.getW(), 1);
                fires.add(fire);
                Fire fire2 = new Fire(hero.getX() - hero.getW() / 3, hero.getY(), hero.getW(), 0);
                fires.add(fire2);
                Fire fire3 = new Fire(hero.getX() + hero.getW() / 3, hero.getY(), hero.getW(), 2);
                fires.add(fire3);
            }
            index = 0;
        }
    }

    //子弹移动
    public void firemove() {
        for (int i = 0; i < fires.size(); i++) {
            Fire fire = fires.get(i);
            if (fire.getDir() == 1) {
                fire.setY(fire.getY() - 15);
            } else if (fire.getDir() == 0) {
                fire.setX(fire.getX() - 1);
                fire.setY(fire.getY() - 15);
            } else {
                fire.setX(fire.getX() + 1);
                fire.setY(fire.getY() - 15);
            }
        }
    }

    public int shootNpc(List<Npc> npcs, Hero hero) {
        //先遍历所有的子弹
        for (int i = 0; i < fires.size(); i++) {
            Fire fire = fires.get(i);
            //遍历所有的敌机
            //每拿一个子弹就判断是否击中敌机
            for (int j = 0; j < npcs.size(); j++) {
                //获取每个敌机
                Npc npc = npcs.get(j);
                if (npc.shootBy(fire)) {
                    //如果敌机被子弹击中
                    //敌机死亡，敌机和子弹都要删除
                    npc.setHp(npc.getHp() - 1);
                    if (npc.getHp() <= 0) {
                        //判断道具机
                        if (npc.getIndex() == 12) {
                            if (hero.getHl() >= 3) {
                                hero.setHp(hero.getHp() + 1);
                                hero.setHl(3);
                            }
                            hero.setHl(hero.getHl() + 1);
                        }
                        if (npc.getIndex() == 13) {
                            hero.setFsp(hero.getFsp() - 1);
                        }
                        npcs.remove(npc);
                        return npc.getNpcscore();
                    }
                    //子弹消失
                    fires.remove(fire);
                }
            }
        }
        return 0;
    }

    public List<Fire> getFires() {
        return fires;
    }

    public void setFires(List<Fire> fires) {
        this.fires = fires;
    }
}
