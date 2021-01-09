package servlet.pub;

import domain.FollowPage;
import domain.User;
import service.userOption.UserOptionImp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DownTheFollow")
public class DownTheFollow extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int index = Integer.valueOf(req.getParameter("index"));
        User me = (User)req.getSession().getAttribute("user");
        FollowPage followPage = (FollowPage) req.getSession().getAttribute("followPage");
        List<User> follow = followPage.getFollow();
        User theFollow = follow.get(index);
        new UserOptionImp().updateFan(me.getId(), theFollow.getId(), false);
        //更新博主被关注状态以及粉丝数
        //改变用户关注数
        int followNum = me.getFollow_num();
        me.setFollow_num(followNum - 1);
        follow.remove(index);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
