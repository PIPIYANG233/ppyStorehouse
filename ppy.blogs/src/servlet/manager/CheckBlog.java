package servlet.manager;

import domain.Blog;
import domain.Manager;
import domain.User;
import service.blogOption.BlogOptionImp;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CheckBlog")
public class CheckBlog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        // 查看博客
        int index = Integer.valueOf(req.getParameter("index"));
        Manager manager = (Manager)req.getSession().getAttribute("manager");
        List<Blog> blogs = manager.getBlogs();
        Blog blog = blogs.get(index);
        //获得作者
        User author = new UserOptionImp().getUser(blog.getBlog_author_id());
        //判断当前用户是否为作者
        User me = (User)req.getSession().getAttribute("user");
        if(null != me && author.getId() == me.getId()){
            req.getSession().setAttribute("author", me);
        }else{
            req.getSession().setAttribute("author", author);
        }
        //获得CheckBlog对象
        domain.CheckBlog checkBlog = null;
        if(null != me){
            checkBlog = new BlogOptionImp().getCheckBlog(blog, author, me.getId());
        }else{
            checkBlog = new BlogOptionImp().getCheckBlog(blog, author, 0);
        }
        req.getSession().setAttribute("checkBlog", checkBlog);

        resp.sendRedirect("/createspace_jsp/Blog.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
