package servlet.manager;

import domain.Manager;
import service.managerOption.ManagerOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/UpdateLabel")
public class UpdateLabel extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int index = Integer.valueOf(req.getParameter("index"));
        String newLabel = req.getParameter("newLabel");
        Manager manager = (Manager)req.getSession().getAttribute("manager");
        List<String> labels = manager.getLabels();
        labels.set(index - 1, newLabel);
        new ManagerOptionImp().updateLabel(newLabel, index);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
