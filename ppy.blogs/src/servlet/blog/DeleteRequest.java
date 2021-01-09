package servlet.blog;

import domain.CheckBlog;
import domain.Comment;
import domain.Request;
import service.blogOption.BlogOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteRequest")
public class DeleteRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        CheckBlog checkBlog = (CheckBlog) req.getSession().getAttribute("checkBlog");
        int commentIndex = Integer.valueOf(req.getParameter("commentIndex"));
        int requestIndex = Integer.valueOf(req.getParameter("requestIndex"));
        List<Comment> comments = checkBlog.getComments();
        Comment comment = (comments.get(commentIndex));
        List<Request> requests = comment.getRequests();
        new BlogOptionImp().subRequest(requests.get(requestIndex).getId());
        requests.remove(requestIndex);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
