package servlet.main;

import service.tools.GetSearch;
import domain.Search;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckSearch")
public class CheckSearch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        boolean isAuthor = Boolean.valueOf(req.getParameter("isAuthor"));
        int start = Integer.valueOf(req.getParameter("start"));
        String searchContent = req.getParameter("searchContent");
        searchContent = searchContent.toUpperCase();
        if(isAuthor){
            int userId = Integer.valueOf(req.getParameter("userId"));
            Search search = new GetSearch().getSearch(searchContent ,start, userId);
            req.getSession().setAttribute("search", search);
        }else{
            int sortId = Integer.valueOf(req.getParameter("sortId"));
            Search search = new GetSearch().getSearch(sortId, searchContent ,start);
            req.getSession().setAttribute("search", search);
        }

        resp.sendRedirect("/main_jsp/Search.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
