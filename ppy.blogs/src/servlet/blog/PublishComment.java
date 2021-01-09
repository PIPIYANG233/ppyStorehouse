package servlet.blog;

import domain.CheckBlog;
import domain.Comment;
import service.blogOption.BlogOptionImp;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/PublishComment")
public class PublishComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        int userId = Integer.valueOf(req.getParameter("userId"));
        String content = req.getParameter("content");
        int blogId = Integer.valueOf(req.getParameter("blogId"));
        CheckBlog checkBlog = (CheckBlog) req.getSession().getAttribute("checkBlog");

        List<Comment> comments = checkBlog.getComments();
        Comment comment = new Comment();
        int commentId = (int)(Math.random() * 1000000);
        comment.setId(commentId);
        comment.setUser_id(userId);
        comment.setBlog_id(blogId);
        comment.setComment_content(content);
        comment.setCommentUser(new UserOptionImp().getUser(userId));
        comments.add(0, comment);

        new BlogOptionImp().addComment(commentId, userId, blogId, content);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
