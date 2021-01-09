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

@WebServlet("/DeleteColumn")
public class DeleteColumn extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        int columnId = Integer.valueOf(req.getParameter("columnId"));
        int index = Integer.valueOf(req.getParameter("index"));
        int userId = Integer.valueOf(req.getParameter("userId"));
        ColumnPage columnPage = (ColumnPage)req.getSession().getAttribute("columnPage");
        int columnNum = columnPage.getNum() - 1;
        int pages = new UserOptionImp().getPages(columnNum, 4);
        if(pages != columnPage.getPages()){
            columnPage = new UserOptionImp().getColumnPage(0, 0, pages,  columnNum, userId);
            req.getSession().setAttribute("columnPage", columnPage);
        }else{
            List<Column> columns = columnPage.getColumns();
            columns.remove(index);
        }
        new UserOptionImp().deleteColumn(columnId, userId);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
