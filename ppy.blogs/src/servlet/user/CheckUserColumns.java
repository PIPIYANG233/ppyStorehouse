package servlet.user;

import domain.ColumnPage;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckUserColumns")
public class CheckUserColumns extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        int userId = Integer.valueOf(req.getParameter("userId"));
        int num = new UserOptionImp().getNum(6, userId, 1);
        int pages = new UserOptionImp().getPages(num, 4);
        int start = Integer.valueOf(req.getParameter("start"));
        int now = start / 4;
        ColumnPage columnPage = new UserOptionImp().getColumnPage(now, start, pages, num, userId);

        req.getSession().setAttribute("columnPage", columnPage);
        resp.sendRedirect("/user_jsp/USER_columns.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
