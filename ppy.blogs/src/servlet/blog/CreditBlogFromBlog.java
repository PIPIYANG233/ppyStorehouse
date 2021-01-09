package servlet.blog;

import domain.CheckBlog;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CreditBlogFromBlog")
public class CreditBlogFromBlog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        CheckBlog checkBlog = (CheckBlog)req.getSession().getAttribute("checkBlog");
        // 编辑博客
        // 获得CheckBlog对象
        resp.sendRedirect("/createspace_jsp/CreateSpace.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
