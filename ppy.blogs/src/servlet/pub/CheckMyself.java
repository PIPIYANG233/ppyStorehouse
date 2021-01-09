package servlet.pub;

import domain.User;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckMyself")
public class CheckMyself extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String flag = req.getParameter("flag");
        if(flag.equals("1")){
            User user = (User)req.getSession().getAttribute("user");
            user = new UserOptionImp().getUser(user.getId());
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/user_jsp/USER_inf.jsp");
        }else{
            User author = (User)req.getSession().getAttribute("author");
            author = new UserOptionImp().getUser(author.getId());
            req.getSession().setAttribute("author", author);
            resp.sendRedirect("/others_jsp/other_user_inf.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
