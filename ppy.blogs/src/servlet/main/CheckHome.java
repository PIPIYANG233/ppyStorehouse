package servlet.main;

import service.tools.GetHome;
import domain.Home;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckHome")
public class CheckHome extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        req.getSession().removeAttribute("search");
        int labelId = Integer.valueOf(req.getParameter("labelId"));
        int sortId = Integer.valueOf(req.getParameter("sortId"));
        Home home = new GetHome().getHome(labelId, sortId);
        req.getSession().setAttribute("home", home);

        resp.sendRedirect("/main_jsp/HOME.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
