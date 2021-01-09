package dao.managerData;

import domain.Blog;
import domain.User;
import java.util.List;

public interface GetManagerData {
    // 获得被管理的用户
    List<User> getManagerUsers(int start, int rank);
    // 获得被管理的博客
    List<Blog> getManagerBlogs(String sql, int status);
    // 获得被推荐的博客
    List<Blog> getRecommendBlogs(String sql);
    // 博客是否为推荐博客
    boolean isRecommend(int blogId);
    // 获取首页标签
    List<String> getLabels();
}
