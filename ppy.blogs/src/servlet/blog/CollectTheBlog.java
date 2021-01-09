package servlet.blog;

import domain.Blog;
import domain.CheckBlog;
import domain.CollectPage;
import domain.User;
import service.blogOption.BlogOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CollectTheBlog")
public class CollectTheBlog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String isCollect = req.getParameter("isCollect");
        switch(isCollect){
            case "1" : {// 收藏
                CheckBlog checkBlog = (CheckBlog)req.getSession().getAttribute("checkBlog");
                User me = (User)req.getSession().getAttribute("user");
                new BlogOptionImp().updateCollect(true, checkBlog.getBlog().getId(), me.getId(), checkBlog.getAuthor().getId());

                //更新博客收藏状态以及收藏数
                checkBlog.setCollect(true);
                Blog blog = checkBlog.getBlog();
                int collectNum = blog.getBlog_collect_num();
                blog.setBlog_collect_num(collectNum + 1);
                checkBlog.setBlog(blog);

                //更新作者收藏数
                User author = checkBlog.getAuthor();
                int authorCollectNumed = author.getCollected_num();
                author.setCollected_num(authorCollectNumed + 1);
                checkBlog.setAuthor(author);
            }   break;
            case "0" : { // 取消收藏
                CheckBlog checkBlog = (CheckBlog)req.getSession().getAttribute("checkBlog");
                User me = (User)req.getSession().getAttribute("user");
                new BlogOptionImp().updateCollect(false, checkBlog.getBlog().getId(), me.getId(), checkBlog.getAuthor().getId());

                //更新博客收藏状态以及收藏数
                checkBlog.setCollect(false);
                Blog blog = checkBlog.getBlog();
                int collectNum = blog.getBlog_collect_num();
                blog.setBlog_collect_num(collectNum - 1);
                checkBlog.setBlog(blog);

                //更新作者收藏数
                User author = checkBlog.getAuthor();
                int authorCollectedNum = author.getCollected_num();
                author.setCollected_num(authorCollectedNum - 1);
                checkBlog.setAuthor(author);
            }   break;
            case "2" : { // 取消收藏
                User me = (User)req.getSession().getAttribute("user");
                int index = Integer.valueOf(req.getParameter("index"));
                CollectPage collectPage = (CollectPage)req.getSession().getAttribute("collectPage");
                List<Blog> collect =collectPage.getCollect();
                Blog theCollect = collect.get(index);
                new BlogOptionImp().updateCollect(false, theCollect.getId(),  me.getId(), theCollect.getBlog_author_id());
                collect.remove(index);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
