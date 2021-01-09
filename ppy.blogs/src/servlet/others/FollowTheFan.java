package servlet.others;

import domain.FanPage;
import domain.User;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/FollowTheFan")
public class FollowTheFan extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String isFollow = req.getParameter("isFollow");
        int index = Integer.valueOf(req.getParameter("index"));
        User me = (User)req.getSession().getAttribute("user");
        FanPage fanPage = (FanPage) req.getSession().getAttribute("fanPage");
        List<User> fans = fanPage.getFans();
        User fan = fans.get(index);

        if(isFollow.equals("1")){
            new UserOptionImp().updateFan(me.getId(), fan.getId(), true);
            //更新博主被关注状态以及粉丝数
            fan.setFollow(true);
            int fanNum = fan.getFans_num();
            fan.setFollow_num(fanNum + 1);
            fans.set(index, fan);
            fanPage.setFans(fans);
            //改变用户关注数
            int followNum = me.getFollow_num();
            me.setFollow_num(followNum + 1);

        }else{
            new UserOptionImp().updateFan(me.getId(), fan.getId(), false);
            //更新博主被关注状态以及粉丝数
            fan.setFollow(false);
            int fanNum = fan.getFans_num();
            fan.setFollow_num(fanNum - 1);
            fans.set(index, fan);
            fanPage.setFans(fans);
            //改变用户关注数
            int followNum = me.getFollow_num();
            me.setFollow_num(followNum - 1);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
