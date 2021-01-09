package servlet.main;

import service.tools.SendEmail;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GetCheckCode")
public class GetCheckCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String email = req.getParameter("email");
        if(new UserOptionImp().isMatch(email)){
            int checkCode = (int) (Math.random() * 100000);
            req.getSession().setAttribute("checkCode", checkCode);
            SendEmail.sendEmail(email, "您此次验证码为" + checkCode);
        }else{
            resp.getWriter().write("false");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
