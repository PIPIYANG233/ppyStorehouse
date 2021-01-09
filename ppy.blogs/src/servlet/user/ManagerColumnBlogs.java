package servlet.user;

import domain.ColumnManagerPage;
import domain.User;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ManagerColumnBlogs")
public class ManagerColumnBlogs extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        boolean flag = Boolean.valueOf(req.getParameter("flag"));
        if(flag){ // 从分类专栏打开
            int columnId = Integer.valueOf(req.getParameter("columnId"));
            int num = new UserOptionImp().getNum(7, columnId, 1);
            int pages = new UserOptionImp().getPages(num, 3);
            ColumnManagerPage columnManagerPage = new UserOptionImp().getColumnManagerPage(0, 0, pages, num, columnId);
            columnManagerPage.setColumnName(req.getParameter("columnName"));
            columnManagerPage.setColumnIntroduce(req.getParameter("columnIntroduce"));
            req.getSession().setAttribute("columnManagerPage", columnManagerPage);
        }else{ // 从管理专栏打开
            int start = Integer.valueOf(req.getParameter("start"));
            int now = start / 3;
            ColumnManagerPage columnManagerPage = (ColumnManagerPage)req.getSession().getAttribute("columnManagerPage");
            columnManagerPage.setStart(start);
            columnManagerPage.setNow(now);
            columnManagerPage = new UserOptionImp().getColumnManagerPage(columnManagerPage, columnManagerPage.getColumnId());
            req.getSession().setAttribute("columnManagerPage", columnManagerPage);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}