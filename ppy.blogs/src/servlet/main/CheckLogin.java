package servlet.main;

import domain.User;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String code = req.getParameter("验证码");
        String checkCode = (String)req.getSession().getAttribute("checkCode");
        if(null != code && null != checkCode && checkCode.equalsIgnoreCase(code)){
            User loginUser = new User();
            loginUser.setId(Integer.valueOf(req.getParameter("id")));
            loginUser.setPwd(req.getParameter("pwd"));
            loginUser = new UserOptionImp().login(loginUser);
            String email = loginUser.getEmail();
            if(null == email || "".equals(email)){
                req.getSession().setAttribute("error", "账号或密码错误");
                resp.sendRedirect("main_jsp/LOGIN.jsp");
            }else{
                req.getSession().removeAttribute("checkCode");
                req.getSession().setAttribute("user", loginUser);
                resp.sendRedirect("main_jsp/HOME.jsp");
            }
        }else{
            req.getSession().setAttribute("error", "验证码错误");
            resp.sendRedirect("main_jsp/LOGIN.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
