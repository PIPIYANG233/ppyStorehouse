package servlet.user;

import domain.User;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/ChangeUserInf")
public class ChangeUserInf extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        User user = (User) req.getSession().getAttribute("user");
        String user_name = req.getParameter("user_name");
        String true_name = req.getParameter("true_name");
        Date birthday = Date.valueOf(req.getParameter("birthday")) ;
        int sex = Integer.valueOf(req.getParameter("sex"));
        String introduce = req.getParameter("introduce");
        user.setUser_name(user_name);
        user.setTrue_name(true_name);
        user.setBirthday(birthday);
        user.setSex(sex);
        user.setIntroduce(introduce);
        new UserOptionImp().updateUserInf(user_name, true_name, birthday, sex, introduce, user.getId());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
