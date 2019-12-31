package com.dao;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
 * Time：23:22
 * Week：星期日
 * Description：TODO
 *
 * @author 黄老板
 * @version 1.0
 *
 * 处理图片的工具类
 *  一般要加static
 *  加static的目的：
 *  可以直接通过类名调用，不用实例化
 */
public class ImgDao {
    //读取指定位置上的图片,BufferedImage 是java中用来表示图片的类
    public BufferedImage getImage(String path)  {
        //加载图片
        //IO流，输送数据的管道
        //ImgDao。class，找到ImgDao类的途径
        //getResource()获取资源，()里是路径
        try {
            BufferedImage img;
            img = ImageIO.read(ImgDao.class.getResource(path));
            return img;
        }
        catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }
}
