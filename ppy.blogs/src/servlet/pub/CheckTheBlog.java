package servlet.pub;

import domain.*;
import service.blogOption.BlogOption;
import service.blogOption.BlogOptionImp;
import service.userOption.UserOption;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CheckTheBlog")
public class CheckTheBlog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int flag = Integer.valueOf(req.getParameter("flag"));
        int index = Integer.valueOf(req.getParameter("index"));
        BlogOption blogOption = new BlogOptionImp();
        UserOption userOption = new UserOptionImp();
        Blog blog = null;
        switch(flag){
            case 1 : { // 查看博客
            }
            case 2 : { // 修改自己博客
                BlogPage blogPage = (BlogPage)req.getSession().getAttribute("blogPage");
                List<Blog> blogs = blogPage.getBlogs();
                blog = blogs.get(index);
            }break;
            case 3 : { // 查看收藏博客
                CollectPage collectPage = (CollectPage) req.getSession().getAttribute("collectPage");
                List<Blog> collect = collectPage.getCollect();
                blog = collect.get(index);
            }break;
            case 4 : { // 查看专栏中博客
            };
            case 5 : { // 编辑专栏中博客
                ColumnManagerPage columnManagerPage = (ColumnManagerPage)req.getSession().getAttribute("columnManagerPage");
                List<Blog> columnBLogs = columnManagerPage.getColumnBlogs();
                blog = columnBLogs.get(index);
            }
        }
        if(flag != 2 && flag != 5){
            // 先更新访客量
            if(blog.getStatus() == 4){
                blogOption.updateScan(blog.getId(), blog.getBlog_author_id());
            }
            //获得作者
            User author = userOption.getUser(blog.getBlog_author_id());
            //判断当前用户是否为作者
            User me = (User)req.getSession().getAttribute("user");
            if(author.getId() == me.getId()){
                req.getSession().setAttribute("author", me);
            }else{
                req.getSession().setAttribute("author", author);
            }
            //更新博客访客量
            if(blog.getStatus() == 4){
                int scanNum = blog.getBlog_scan_num();
                blog.setBlog_scan_num(scanNum + 1);
            }
            //获得CheckBlog对象
            CheckBlog checkBlog = blogOption.getCheckBlog(blog, author, me.getId());
            req.getSession().setAttribute("checkBlog", checkBlog);
            resp.sendRedirect("/createspace_jsp/Blog.jsp");
        }else{
            // 编辑博客
            // 获得CheckBlog对象
            CheckBlog checkBlog = blogOption.getCheckBlog(blog);
            req.getSession().setAttribute("checkBlog", checkBlog);
            resp.sendRedirect("/createspace_jsp/CreateSpace.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}