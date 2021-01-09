package servlet.main;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCode")
public class CheckCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int width = 100;
        int height = 50;
        //创建一个对象,在内存中图片(验证码图片对象)
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        //美化图片
        Graphics g = image.createGraphics();

        g.setColor(Color.pink);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.BLACK);
        g.setFont(new Font("楷体",15,20));
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rd = new Random();
        int len = str.length();
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= 4; i++){
            int idex = rd.nextInt(len);
            char ch = str.charAt(idex);
            sb.append(ch);
            g.drawString(ch + "", width / 5 * i, height / 2);
        }
        String checkCode = sb.toString();
        req.getSession().setAttribute("checkCode", checkCode);

        g.setColor(Color.green);
        for(int i = 1; i <= 10; i++){
            int x1 = rd.nextInt(width);
            int x2 = rd.nextInt(width);
            int y1 = rd.nextInt(height);
            int y2 = rd.nextInt(height);
            g.drawLine(x1, x2, y1, y2);
        }
        //将图片输出到页面
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}