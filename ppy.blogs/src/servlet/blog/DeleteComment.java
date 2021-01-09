package servlet.blog;

import domain.CheckBlog;
import domain.Comment;
import service.blogOption.BlogOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteComment")
public class DeleteComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        CheckBlog checkBlog = (CheckBlog) req.getSession().getAttribute("checkBlog");
        int commentIndex = Integer.valueOf(req.getParameter("commentIndex"));
        List<Comment> comments = checkBlog.getComments();
        new BlogOptionImp().subComment(comments.get(commentIndex).getId());
        comments.remove(commentIndex);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
