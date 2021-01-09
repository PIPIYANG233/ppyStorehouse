package servlet.main;

import domain.*;
import service.blogOption.BlogOptionImp;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CheckSearchBlog")
public class CheckSearchBlog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        // 查看博客
        int index = Integer.valueOf(req.getParameter("index"));
        Search search = (Search)req.getSession().getAttribute("search");
        List<Blog> searchBlogs = search.getSearchBlogs();
        Blog searchBlog = searchBlogs.get(index);
        // 保存博客
        // 先更新访客量
        new BlogOptionImp().updateScan(searchBlog.getId(), searchBlog.getBlog_author_id());
        //获得作者
        User author = new UserOptionImp().getUser(searchBlog.getBlog_author_id());
        //判断当前用户是否为作者
        User me = (User)req.getSession().getAttribute("user");
        if(null != me && author.getId() == me.getId()){
            req.getSession().setAttribute("author", me);
        }else{
            req.getSession().setAttribute("author", author);
        }
        //更新博客访客量
        int blog_scan_num = searchBlog.getBlog_scan_num();
        searchBlog.setBlog_scan_num(blog_scan_num + 1);
        //获得CheckBlog对象
        CheckBlog checkBlog = null;
        if(null != me){
            checkBlog = new BlogOptionImp().getCheckBlog(searchBlog, author, me.getId());
        }else{
            checkBlog = new BlogOptionImp().getCheckBlog(searchBlog, author, 0);
        }
        req.getSession().setAttribute("checkBlog", checkBlog);

        resp.sendRedirect("/createspace_jsp/Blog.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
