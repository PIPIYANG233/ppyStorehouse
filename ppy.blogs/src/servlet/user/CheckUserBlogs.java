package servlet.user;

import domain.BlogPage;
import domain.User;
import service.blogOption.BlogOptionImp;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckUserBlogs")
public class CheckUserBlogs extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        User user = (User)req.getSession().getAttribute("user");
        int start = Integer.valueOf(req.getParameter("start"));
        int now = start / 3;
        int flag = Integer.valueOf(req.getParameter("flag"));
        switch(flag){
            case 1 : {
                // 无搜索内容
                int num = new UserOptionImp().getNum(1, user.getId(), 1);
                int pages = new UserOptionImp().getPages(num, 3);
                BlogPage blogPage = new BlogOptionImp().getBlogPage(true, now, start, pages, num, user.getId());
                blogPage.setYear(0);
                blogPage.setMonth(0);
                blogPage.setColumn("");
                blogPage.setType(0);
                blogPage.setMainContent("");
                req.getSession().setAttribute("blogPage", blogPage);
            };break;
            case 2 :{
                // 有搜索内容
                // 重新获得 blogPage
                BlogPage blogPage = (BlogPage)req.getSession().getAttribute("blogPage");
                // 获得 筛选条件
                int year = Integer.valueOf(req.getParameter("year"));
                int month = Integer.valueOf(req.getParameter("month"));
                String column = req.getParameter("column");
                int type = Integer.valueOf(req.getParameter("type"));
                String mainContent = req.getParameter("mainContent");
                blogPage.setNow(now);
                blogPage.setStart(start);
                blogPage.setYear(year);
                blogPage.setMonth(month);
                blogPage.setColumn(column);
                blogPage.setType(type);
                blogPage.setMainContent(mainContent);
                int num = new UserOptionImp().getSelectedNum(true, blogPage, user.getId());
                blogPage.setNum(num);
                blogPage.setPages(new UserOptionImp().getPages(num, 3));
                blogPage = new BlogOptionImp().getBlogPage(true, false, blogPage, user.getId());
                req.getSession().setAttribute("blogPage", blogPage);
            };break;
            case 3 : {
                // 分页
                BlogPage blogPage = (BlogPage)req.getSession().getAttribute("blogPage");
                blogPage.setNow(now);
                blogPage.setStart(start);
                blogPage = new BlogOptionImp().getBlogPage(true, false, blogPage, user.getId());
                req.getSession().setAttribute("blogPage", blogPage);
            }break;
            case 4 : { // 从博客中打开
                String columnName = req.getParameter("columnName");
                BlogPage blogPage = new BlogPage();
                blogPage.setStart(start);
                blogPage.setNow(now);
                blogPage.setYear(0);
                blogPage.setMonth(0);
                blogPage.setColumn(columnName);
                blogPage.setType(0);
                blogPage.setMainContent("");
                int num = new UserOptionImp().getSelectedNum(true, blogPage, user.getId());
                blogPage.setNum(num);
                blogPage.setPages(new UserOptionImp().getPages(num, 3));
                blogPage = new BlogOptionImp().getBlogPage(true, true, blogPage, user.getId());
                req.getSession().setAttribute("blogPage", blogPage);
            }
        }
        resp.sendRedirect("/user_jsp/USER_blogs.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}