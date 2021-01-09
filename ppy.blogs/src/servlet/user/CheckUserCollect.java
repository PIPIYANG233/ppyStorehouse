package servlet.user;

import domain.CollectPage;
import domain.User;
import service.blogOption.BlogOptionImp;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckUserCollect")
public class CheckUserCollect extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        User user = (User)req.getSession().getAttribute("user");
        int num = new UserOptionImp().getNum(3, user.getId(), 1);
        int pages = new UserOptionImp().getPages(num, 4);
        CollectPage collectPage = null;
        collectPage = (CollectPage)req.getSession().getAttribute("collectPage");
        if(null == collectPage){
            collectPage = new BlogOptionImp().getCollectPage(0, 0, pages, num, user.getId());
            req.getSession().setAttribute("collectPage", collectPage);
        }else{
            int start = Integer.valueOf(req.getParameter("start"));
            int now = (int)(start / 4);
            collectPage = new BlogOptionImp().getCollectPage(now, start, pages, num, user.getId());
            req.getSession().setAttribute("collectPage", collectPage);
        }
        resp.sendRedirect("/user_jsp/USER_collect.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
