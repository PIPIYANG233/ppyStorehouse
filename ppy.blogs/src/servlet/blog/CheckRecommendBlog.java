package servlet.blog;

import domain.Blog;
import domain.CheckBlog;
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

@WebServlet("/CheckRecommendBlog")
public class CheckRecommendBlog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int index = Integer.valueOf(req.getParameter("index"));
        CheckBlog checkBlog = (CheckBlog)req.getSession().getAttribute("checkBlog");
        List<Blog> recommendBlogs = checkBlog.getRecommendBlogs();
        Blog recommendBlog = recommendBlogs.get(index);
        //先更新访客量
        new BlogOptionImp().updateScan(recommendBlog.getId(), recommendBlog.getBlog_author_id());
        //判断当前用户是否为作者
        int authorId = recommendBlog.getBlog_author_id();
        User author = null;
        User me = (User)req.getSession().getAttribute("user");
        if(authorId == me.getId()){
            req.getSession().setAttribute("author", me);
            author = me;
        }else{
            //获得作者
            author = new UserOptionImp().getUser(authorId);
            req.getSession().setAttribute("author", author);
        }
        //更新博客访客量
        int scanNum = recommendBlog.getBlog_scan_num();
        recommendBlog.setBlog_scan_num(scanNum + 1);
        recommendBlogs.set(index, recommendBlog);
        checkBlog.setRecommendBlogs(recommendBlogs);
        checkBlog = new BlogOptionImp().getCheckBlog(recommendBlog, author, me.getId());
        req.getSession().setAttribute("checkBlog", checkBlog);

        resp.sendRedirect("/createspace_jsp/Blog.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
