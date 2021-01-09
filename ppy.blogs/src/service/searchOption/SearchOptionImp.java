package service.searchOption;

import dao.page.GetPageDataImp;
import dao.searchData.GetSearchDataImp;
import domain.Blog;
import domain.User;

import java.util.List;

public class SearchOptionImp implements SearchOption{
    @Override
    public int getSearchNum(boolean flag, Object object, int parametersNum) {
        int num = 0;
        // 搜索用户
        if(flag){
            String sql = "select count(id) from user_inf where user_name like ?";
            num += new GetPageDataImp().getNum(object, sql, 1);
        }else{ // 搜索博客
            String sql = "select count(ub.id) from (SELECT distinct " +
                    "ub.id "+
                    "FROM " +
                    "blogs_label as bl " +
                    "inner JOIN " +
                    "user_blogs as ub " +
                    "on " +
                    "bl.blog_id = ub.id " +
                    "inner join (select mc.column_name,bc.blog_id from mycolumns as mc right join blogs_column as bc on mc.id = bc.column_id) as bmc " +
                    "on ub.id = bmc.blog_id " +
                    "where " +
                    "(bl.label_name like ? " +
                    "or " +
                    "ub.blog_title like ? " +
                    "or " +
                    "bmc.column_name like '%" + object + "%')) as ub";
            num += new GetPageDataImp().getNum(object, sql, 2);
        }
        return num;
    }

    @Override
    public int getPages(int num, int size) {
        return new GetPageDataImp().getPages(num, size);
    }

    @Override
    public List<Blog> getSearchBlogs(int sortId, String searchContent, int start) {
        return new GetSearchDataImp().getSearchBlogs(sortId, searchContent, start);
    }

    @Override
    public List<User> getSearchUsers(String searchContent, int start, int userId) {
        return new GetSearchDataImp().getSearchUsers(searchContent, start, userId);
    }

    @Override
    public List<String> getRecommendLabels(String searchContent) {
        return new GetSearchDataImp().getRecommendLabels(searchContent);
    }
}
