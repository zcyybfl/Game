package com.frame.game.plane.panel;

import com.bean.User;
import com.dao.UserDao;
import com.frame.login.LoginFrame;
import com.model.ScoreTable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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
 * Time：23:58
 * Week：星期日
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 */
public class PlaneScorePanel extends JPanel {
    private JTable table;
    ScoreTable model =new ScoreTable();
    UserDao userDao =new UserDao();
    public PlaneScorePanel() {
        this.setLayout(null);
        init();
    }

    public void init() {

        //滚动条
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0,50,500,150);
        this.add(scrollPane);

        table = new JTable(model);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr.setVerticalAlignment(JLabel.CENTER);
        table.setRowHeight(25);//设置列高
        table.setDefaultRenderer(Object.class,tcr);

        List<User> list = userDao.selectAll();
        User [] user = new User[list.size()];
        User user1;
        for (int i = 0;i < list.size();i++) {
            user[i] = list.get(i);
        }
        for (int i = 0;i < list.size() - 1;i++) {
            for (int j = 0;j < list.size() - 1 - i;j++){
                if (user[j].getPlaneScore()< user[j + 1].getPlaneScore()) {
                    user1 = user[j];
                    user[j] = user[j + 1];
                    user[j + 1] = user1;
                }
            }
        }

        model.setRowCount(0);
        for (int i = 0; i < list.size();i++) {
            model.addRow(new Object[]{i + 1,user[i].getUsername(),user[i].getPlaneScore()});
            if (user[i].getUsername().equals(LoginFrame.name)) {
                JLabel rank = new JLabel("你的名次为:" + ( i + 1));
                rank.setBounds(200,0,350,50);
                add(rank);
            }
        }
        scrollPane.setViewportView(table);
    }
}
