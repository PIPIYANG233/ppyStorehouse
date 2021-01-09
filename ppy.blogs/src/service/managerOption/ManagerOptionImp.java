package service.managerOption;

import dao.managerData.GetManagerDataImp;
import dao.managerData.UpdateManagerDataImp;
import dao.page.GetPageDataImp;
import domain.Blog;
import domain.User;
import java.util.List;

public class ManagerOptionImp implements ManagerOption{
    @Override
    public List<User> getManagerUsers(int start, int rank) {
        List<User> managerUsers = new GetManagerDataImp().getManagerUsers(start, rank);
        return managerUsers;
    }

    @Override
    public List<Blog> getManagerBlogs(int start, int status) {
        String sql = "select id,blog_author_id,blog_title,blog_scan_num,blog_up_num,blog_collect_num " +
                "from user_blogs where status=? limit " + start + ",8";
        List<Blog> managerBlogs = new GetManagerDataImp().getManagerBlogs(sql, status);
        return managerBlogs;
    }

    @Override
    public List<Blog> getRecommendBlogs() {
        String sql="select tr.today_blog,ub.blog_author_id,ub.blog_title,ub.blog_scan_num,ub.blog_up_num,ub.blog_collect_num " +
                "from today_recommendation as tr inner join user_blogs as ub on tr.today_blog=ub.id";
        return new GetManagerDataImp().getRecommendBlogs(sql);
    }

    @Override
    public List<String> getLabels() {
        List<String> Labels = new GetManagerDataImp().getLabels();
        return Labels;
    }

    @Override
    public int getManagerNum(int managerWhat, Object object, int parametersNum) {
        String sql = "";
        switch (managerWhat){
            case 1 : sql += "select count(id) from user_inf where rank_ <?";break;
            case 2 : sql += "select count(id) from user_blogs where status=?";break;
            case 3 : sql += "select count(today_blog) from today_recommendation";break;
        }
        return new GetPageDataImp().getNum(object, sql, parametersNum);
    }

    @Override
    public int getPages(int num, int size) {
        return new GetPageDataImp().getPages(num, size);
    }

    @Override
    public void updateLabel(String newLabel, int labelId) {
        new UpdateManagerDataImp().updateLabel(newLabel, labelId);
    }
}
