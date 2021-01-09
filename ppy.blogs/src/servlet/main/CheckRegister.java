package servlet.main;

import service.userOption.UserOptionImp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckRegister")
public class CheckRegister extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String code = req.getParameter("验证码");
        String checkCode = (String)req.getSession().getAttribute("checkCode");
        if(null != code && null != checkCode && checkCode.equalsIgnoreCase(code)){
            // 返回的id判断是否注册成功
            boolean flag = new UserOptionImp().register(req.getParameter("name"), req.getParameter("email"), req.getParameter("pwd"));
            if(flag){
                req.getSession().removeAttribute("error");
                resp.sendRedirect("main_jsp/LOGIN.jsp");
            }else{
                req.getSession().setAttribute("error", "邮箱已被注册或邮箱不存在");
                resp.sendRedirect("main_jsp/REGISTER.jsp");
            }
        }else{
            req.getSession().setAttribute("error", "验证码错误");
            resp.sendRedirect("main_jsp/REGISTER.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
