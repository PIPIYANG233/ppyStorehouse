package dao.homeData;

import domain.Blog;

import java.util.List;

public interface GetHomeData {
    // 获得 首页标签
    List<String> getHomeLabels();
    // 获得 中间博客
    List<Blog> getCenterRecommendBlogs(int labelId, int sortId);
    // 获得 管理员推荐博客
    List<Blog> getRightRecommendBlogs();
}
