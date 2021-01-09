package servlet.others;

import domain.FanPage;
import domain.FollowPage;
import domain.Search;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ToAuthorSpace")
public class ToAuthorSpace extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flag = req.getParameter("flag");
        if(flag.equals("1")){
            req.setCharacterEncoding("utf-8");
            int index = Integer.valueOf(req.getParameter("index"));
            FollowPage followPage = (FollowPage)req.getSession().getAttribute("followPage");
            List<User> follow = followPage.getFollow();
            User theFollow = follow.get(index);
            req.getSession().setAttribute("author", theFollow);
            resp.sendRedirect("/others_jsp/other_user_inf.jsp");
        }else if(flag.equals("2")){
            req.setCharacterEncoding("utf-8");
            int index = Integer.valueOf(req.getParameter("index"));
            FanPage fanPage = (FanPage)req.getSession().getAttribute("fanPage");
            List<User> fans = fanPage.getFans();
            User fan = fans.get(index);
            req.getSession().setAttribute("author", fan);
            resp.sendRedirect("/others_jsp/other_user_inf.jsp");
        }else{
            req.setCharacterEncoding("utf-8");
            int index = Integer.valueOf(req.getParameter("index"));
            Search search = (Search)req.getSession().getAttribute("search");
            List<User> searchUsers = search.getSearchUsers();
            User searchUser = searchUsers.get(index);
            req.getSession().setAttribute("author", searchUser);
            resp.sendRedirect("/others_jsp/other_user_inf.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
