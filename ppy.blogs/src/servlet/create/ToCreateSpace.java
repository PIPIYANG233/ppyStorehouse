package servlet.create;

import domain.CheckBlog;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ToCreateSpace")
public class ToCreateSpace extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        req.getSession().removeAttribute("checkBlog");
        int userId = Integer.valueOf(req.getParameter("userId"));
        CheckBlog checkBlog = new CheckBlog();
        checkBlog.setColumnNames(new UserOptionImp().getColumnNames(userId));
        req.getSession().setAttribute("checkBlog", checkBlog);
        resp.sendRedirect("/createspace_jsp/CreateSpace.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
