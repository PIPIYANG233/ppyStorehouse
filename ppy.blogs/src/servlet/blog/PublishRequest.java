package servlet.blog;

import domain.CheckBlog;
import domain.Comment;
import domain.Request;
import service.blogOption.BlogOptionImp;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/PublishRequest")
public class PublishRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        int commentId = Integer.valueOf(req.getParameter("commentId"));
        String content = req.getParameter("content");
        int commentUserId = Integer.valueOf(req.getParameter("commentUserId"));
        int userId = Integer.valueOf(req.getParameter("userId"));
        int commentIndex = Integer.valueOf(req.getParameter("commentIndex"));

        CheckBlog checkBlog = (CheckBlog) req.getSession().getAttribute("checkBlog");

        List<Comment> comments = checkBlog.getComments();
        Comment comment = comments.get(commentIndex);
        List<Request> requests = comment.getRequests();
        if(null == requests){
            requests = new ArrayList<>();
        }
        Request request = new Request();
        int reqId = (int)(Math.random() * 1000000);
        request.setId(reqId);
        request.setComment_id(commentId);
        request.setReq_content(content);
        request.setReq_user_id(userId);
        request.setRequestUser(new UserOptionImp().getUser(userId));
        request.setCommentUser(new UserOptionImp().getUser(commentUserId));
        requests.add(0, request);
        comment.setRequests(requests);
        new BlogOptionImp().addRequest(reqId, commentId, content, commentUserId, userId);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
