package servlet.user;

import domain.FollowPage;
import domain.User;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckUserFollow")
public class CheckUserFollow extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        User user = (User)req.getSession().getAttribute("user");
        int num = new UserOptionImp().getNum(5, user.getId(), 1);
        int pages = new UserOptionImp().getPages(num, 4);
        FollowPage followPage = null;
        followPage = (FollowPage)req.getSession().getAttribute("followPage");
        if(null == followPage){
            followPage = new UserOptionImp().getFollowPage(0, 0, pages, num, user.getId());
            req.getSession().setAttribute("followPage", followPage);
        }else{
            int start = Integer.valueOf(req.getParameter("start"));
            int now = (int)(start / 4);
            followPage = new UserOptionImp().getFollowPage(now, start, pages, num, user.getId());
            req.getSession().setAttribute("followPage", followPage);
        }
        resp.sendRedirect("/user_jsp/USER_att.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
