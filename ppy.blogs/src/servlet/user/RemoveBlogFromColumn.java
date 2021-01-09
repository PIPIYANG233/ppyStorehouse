package servlet.user;

import domain.Blog;
import domain.BlogPage;
import domain.ColumnManagerPage;
import domain.User;
import service.blogOption.BlogOptionImp;
import service.tools.FileOption;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RemoveBlogFromColumn")
public class RemoveBlogFromColumn extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int index = Integer.valueOf(req.getParameter("index"));
        int blogId = Integer.valueOf(req.getParameter("blogId"));
        int columnId = Integer.valueOf(req.getParameter("columnId"));
        ColumnManagerPage columnManagerPage = (ColumnManagerPage) req.getSession().getAttribute("columnManagerPage");
        new UserOptionImp().removeBlogFromColumn(blogId, columnId);
        int num = columnManagerPage.getNum() - 1;
        int pages = new UserOptionImp().getPages(num, 3);
        if(pages != columnManagerPage.getPages()){
            columnManagerPage.setPages(pages);
            columnManagerPage = new UserOptionImp().getColumnManagerPage(columnManagerPage, columnManagerPage.getColumnId());
            req.getSession().setAttribute("columnManagerPage", columnManagerPage);
        }else{
            List<Blog> columnBlogs = columnManagerPage.getColumnBlogs();
            columnBlogs.remove(index);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
