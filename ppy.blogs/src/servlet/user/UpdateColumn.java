package servlet.user;

import domain.Column;
import domain.ColumnPage;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/UpdateColumn")
public class UpdateColumn extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        int columnId = Integer.valueOf(req.getParameter("columnId"));
        String columnName = req.getParameter("columnName");
        String columnIntroduce = req.getParameter("columnIntroduce");
        int index = Integer.valueOf(req.getParameter("index"));
        int userId = Integer.valueOf(req.getParameter("userId"));

        ColumnPage columnPage = (ColumnPage)req.getSession().getAttribute("columnPage");
        List<Column> columns = columnPage.getColumns();
        Column column = columns.get(index);
        column.setColumn_name(columnName);
        column.setIntroduce(columnIntroduce);

        resp.getWriter().write("" + new UserOptionImp().updateColumn(columnId, columnName, columnIntroduce, userId));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
