package servlet.blog;

import domain.Blog;
import domain.CheckBlog;
import domain.User;
import service.blogOption.BlogOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/UpTheBlog")
public class UpTheBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String isUp = req.getParameter("isUp");
        switch(isUp){
            case "1" : {
                CheckBlog checkBlog = (CheckBlog)req.getSession().getAttribute("checkBlog");
                User me = (User)req.getSession().getAttribute("user");
                new BlogOptionImp().updateUp(true, checkBlog.getBlog().getId(), me.getId(), checkBlog.getAuthor().getId());

                //更新博客点赞状态以及点赞数
                checkBlog.setUp(true);
                Blog blog = checkBlog.getBlog();
                int upNum = blog.getBlog_up_num();
                blog.setBlog_up_num(upNum + 1);
                checkBlog.setBlog(blog);

                //更新作者点赞数
                User author = checkBlog.getAuthor();
                int authorUpNum = author.getUp_num();
                author.setUp_num(authorUpNum + 1);
                checkBlog.setAuthor(author);

                //更新推荐博客里数据
                int index = 0;
                int theBlogId = blog.getId();
                List<Blog> recommendBlogs = checkBlog.getRecommendBlogs();
                for(Blog recommendBlog : recommendBlogs){
                    if(theBlogId == recommendBlog.getId()){
                        recommendBlog.setBlog_up_num(upNum + 1);
                        recommendBlogs.set(index, recommendBlog);
                    }
                    index++;
                }
            }break;
            case "0" : {
                CheckBlog checkBlog = (CheckBlog)req.getSession().getAttribute("checkBlog");
                User me = (User)req.getSession().getAttribute("user");
                new BlogOptionImp().updateUp(false, checkBlog.getBlog().getId(), me.getId(), checkBlog.getAuthor().getId());

                //更新博客点赞状态以及点赞数
                checkBlog.setUp(false);
                Blog blog = checkBlog.getBlog();
                int upNum = blog.getBlog_up_num();
                blog.setBlog_up_num(upNum - 1);
                checkBlog.setBlog(blog);

                //更新作者点赞数
                User author = checkBlog.getAuthor();
                int authorUpNum = author.getUp_num();
                author.setUp_num(authorUpNum - 1);
                checkBlog.setAuthor(author);

                //更新推荐博客里数据
                int index = 0;
                int theBlogId = blog.getId();
                List<Blog> recommendBlogs = checkBlog.getRecommendBlogs();
                for(Blog recommendBlog : recommendBlogs){
                    if(theBlogId == recommendBlog.getId()){
                        recommendBlog.setBlog_up_num(upNum - 1);
                        recommendBlogs.set(index, recommendBlog);
                    }
                    index++;
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
