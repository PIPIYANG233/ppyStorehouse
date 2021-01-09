package servlet.others;

import domain.BlogPage;
import domain.User;
import service.blogOption.BlogOptionImp;
import service.userOption.UserOption;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckOtherUserBlogs")
public class CheckOtherUserBlogs extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        User author = (User)req.getSession().getAttribute("author");
        int start = Integer.valueOf(req.getParameter("start"));
        int now = start / 3;
        int flag = Integer.valueOf(req.getParameter("flag"));
        switch(flag){
            case 1 : {
                // 无搜索内容
                int num = new UserOptionImp().getNum(2, author.getId(), 1);
                int pages = new UserOptionImp().getPages(num, 3);
                BlogPage blogPage = new BlogOptionImp().getBlogPage(false, now, start, pages, num, author.getId());
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
                int num = new UserOptionImp().getSelectedNum(false, blogPage, author.getId());
                blogPage.setNum(num);
                blogPage.setPages(new UserOptionImp().getPages(num, 3));
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
                blogPage = new BlogOptionImp().getBlogPage(false, false, blogPage, author.getId());
                req.getSession().setAttribute("blogPage", blogPage);
            };break;
            case 3 : {
                // 分页
                BlogPage blogPage = (BlogPage)req.getSession().getAttribute("blogPage");
                blogPage.setNow(now);
                blogPage.setStart(start);
                blogPage = new BlogOptionImp().getBlogPage(false, false, blogPage, author.getId());
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
                blogPage = new BlogOptionImp().getBlogPage(false, true, blogPage, author.getId());
                req.getSession().setAttribute("blogPage", blogPage);
            }
        }
        resp.sendRedirect("/others_jsp/other_user_blogs.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}