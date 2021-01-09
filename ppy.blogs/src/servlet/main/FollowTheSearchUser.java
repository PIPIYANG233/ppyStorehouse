package servlet.main;

import domain.Search;
import domain.User;
import service.userOption.UserOptionImp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/FollowTheSearchUser")
public class FollowTheSearchUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        boolean isFollow = Boolean.valueOf(req.getParameter("isFollow"));
        int userId = Integer.valueOf(req.getParameter("userId"));
        int index = Integer.valueOf(req.getParameter("index"));
        Search search = (Search)req.getSession().getAttribute("search");
        List<User> searchUsers = search.getSearchUsers();
        User searchUser = searchUsers.get(index);
        if(isFollow){
            new UserOptionImp().updateFan(userId, searchUser.getId(), true);
            searchUser.setFollow(true);
        }else{
            new UserOptionImp().updateFan(userId, searchUser.getId(), false);
            searchUser.setFollow(false);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}