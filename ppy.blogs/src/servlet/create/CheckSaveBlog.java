package servlet.create;

import service.blogOption.BlogOption;
import service.blogOption.BlogOptionImp;
import service.tools.FileOption;
import domain.CheckBlog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckSaveBlog")
public class CheckSaveBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        int blog_author_id = Integer.valueOf(req.getParameter("blog_author_id"));
        String blog_title = req.getParameter("blog_title");
        String htmlCode = req.getParameter("test-editormd-htmlCode");
        int status = Integer.valueOf(req.getParameter("status"));
        String markdown = req.getParameter("test-editormd-markdown");
        int type = Integer.valueOf(req.getParameter("blog_type"));
        String[] columns = req.getParameterValues("blogColumns");
        String[] labels = req.getParameterValues("blogLabels");
        boolean flag = false;
        CheckBlog checkBlog = null;
        checkBlog = (CheckBlog)req.getSession().getAttribute("checkBlog");
        int blog_id = 0;
        if(null == checkBlog.getBlog()){
            blog_id = (int) (Math.random() * 1000000);
            flag = true;
        }else{
            blog_id = checkBlog.getBlog().getId();
        }
        //存储博客文本
        FileOption.writeToFile("F:/ppy.blogs/web/" + "user_blogs/blogs_htmlCode/" + blog_id + ".txt", htmlCode);
        //存储博客html
        FileOption.writeToFile("F:/ppy.blogs/web/" + "user_blogs/blogs_markdown/" + blog_id + ".txt", markdown);
        //存入数据库
        BlogOption blogOption = new BlogOptionImp();
        if(flag){
            // 保存博客
            blogOption.saveBlog(blog_id, blog_author_id, blog_title, status, type, labels, columns);
        }else{
            //更新博客
            blogOption.updateRecommendBlog(blog_id, false);
            blogOption.updateBlog(blog_id, blog_author_id, blog_title, status, type, labels, columns);
        }
        //转到用户博客
        resp.sendRedirect("/CheckUserBlogs?start=0&&flag=1");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
