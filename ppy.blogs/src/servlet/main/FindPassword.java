package servlet.main;

import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FindPassword")
public class FindPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
        String checkCode = req.getSession().getAttribute("checkCode") + "";
        req.removeAttribute("checkCode");
        String inputCheckCode = req.getParameter("验证码");
        if(null != checkCode && checkCode.equals(inputCheckCode)){
            new UserOptionImp().updatePassword(email, pwd);
            req.getSession().removeAttribute("user");
            req.getSession().setAttribute("msg", "修改成功!");
            resp.sendRedirect("/main_jsp/LOGIN.jsp");
        }else{
            req.getSession().setAttribute("error", "验证码错误");
            resp.sendRedirect("/main_jsp/FindPassword.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
