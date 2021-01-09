package servlet.user;

import domain.FanPage;
import domain.User;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckUserFans")
public class CheckUserFans extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        User user = (User)req.getSession().getAttribute("user");
        int num = new UserOptionImp().getNum(4, user.getId(), 1);
        int pages = new UserOptionImp().getPages(num, 4);
        FanPage fanPage = null;
        fanPage = (FanPage)req.getSession().getAttribute("fanPage");
        if(null == fanPage){
            fanPage = new UserOptionImp().getFanPage(0, 0, pages, num, user.getId());
            req.getSession().setAttribute("fanPage", fanPage);
        }else{
            int start = Integer.valueOf(req.getParameter("start"));
            int now = (int)(start / 4);
            fanPage = new UserOptionImp().getFanPage(now, start, pages, num, user.getId());
            req.getSession().setAttribute("fanPage", fanPage);
        }
        resp.sendRedirect("/user_jsp/USER_fans.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
