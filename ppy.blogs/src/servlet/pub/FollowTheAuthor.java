package servlet.pub;

import domain.CheckBlog;
import domain.User;
import service.userOption.UserOptionImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/FollowTheAuthor")
public class FollowTheAuthor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String isFollow = req.getParameter("isFollow");
        CheckBlog checkBlog = (CheckBlog)req.getSession().getAttribute("checkBlog");
        User me = (User)req.getSession().getAttribute("user");
        if(isFollow.equals("1")){
            new UserOptionImp().updateFan(me.getId(), checkBlog.getAuthor().getId(), true);

            //更新博主被关注状态以及粉丝数
            checkBlog.setFollow(true);
            User author = checkBlog.getAuthor();
            int fansNum = author.getFans_num();
            author.setFans_num(fansNum + 1);
            checkBlog.setAuthor(author);
        }else{
            new UserOptionImp().updateFan(me.getId(), checkBlog.getAuthor().getId(), false);

            //更新博主被关注状态以及粉丝数
            checkBlog.setFollow(false);
            User author = checkBlog.getAuthor();
            int fansNum = author.getFans_num();
            author.setFans_num(fansNum - 1);
            checkBlog.setAuthor(author);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
