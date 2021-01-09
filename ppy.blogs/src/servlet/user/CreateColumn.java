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

@WebServlet("/CreateColumn")
public class CreateColumn extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String columnName = req.getParameter("columnName");
        columnName = columnName.toUpperCase();
        String columnIntroduce = req.getParameter("columnIntroduce");
        int ownerId = Integer.valueOf(req.getParameter("ownerId"));
        Column column = new UserOptionImp().insertColumn(columnName, columnIntroduce, ownerId);
        if(null == column){
            resp.getWriter().write("false");
        }else{
            ColumnPage columnPage = (ColumnPage)req.getSession().getAttribute("columnPage");
            int columnNum = columnPage.getNum() + 1;
            int pages = new UserOptionImp().getPages(columnNum, 4);
            columnPage = new UserOptionImp().getColumnPage(0, 0, pages,  columnNum, ownerId);
            req.getSession().setAttribute("columnPage", columnPage);
            resp.getWriter().write("true");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
