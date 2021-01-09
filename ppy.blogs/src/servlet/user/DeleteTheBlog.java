package servlet.user;

import service.blogOption.BlogOptionImp;
import service.tools.FileOption;
import domain.Blog;
import domain.BlogPage;
import domain.User;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteTheBlog")
public class DeleteTheBlog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        BlogPage blogPage = (BlogPage)req.getSession().getAttribute("blogPage");
        int index = Integer.valueOf(req.getParameter("index"));
        List<Blog> blogs = blogPage.getBlogs();
        FileOption.deleteBlog(blogs.get(index).getId());
        int num = blogPage.getNum() - 1;
        int pages = new UserOptionImp().getPages(num, 3);
        User user = (User)req.getSession().getAttribute("user");
        new BlogOptionImp().deleteBlog(blogs.get(index).getId(), user.getId());
        blogPage = new BlogOptionImp().getBlogPage(true, 0, 0, pages, num, user.getId());
        req.getSession().setAttribute("msg", "删除成功");
        req.getSession().setAttribute("blogPage", blogPage);

        resp.sendRedirect("/user_jsp/USER_blogs.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
