package servlet.manager;

import service.tools.GetManager;
import domain.Manager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckManager")
public class CheckManager extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int sortId = Integer.valueOf(req.getParameter("sortId"));
        if(sortId == 1){
            int start = Integer.valueOf(req.getParameter("start"));
            int rank = Integer.valueOf(req.getParameter("rank"));
            Manager manager = new GetManager().getManager(sortId, start, rank);
            req.getSession().setAttribute("manager", manager);
        }else if(sortId < 5){
            int start = Integer.valueOf(req.getParameter("start"));
            Manager manager = new GetManager().getManager(sortId, start);
            req.getSession().setAttribute("manager", manager);
        }else{
            Manager manager = new GetManager().getManager(sortId);
            req.getSession().setAttribute("manager", manager);
        }
        resp.sendRedirect("/manager_jsp/Manager.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
