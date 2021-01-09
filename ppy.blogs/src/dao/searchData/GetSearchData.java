package dao.searchData;

import domain.Blog;
import domain.User;

import java.util.List;

public interface GetSearchData {
    // 获取 搜索博客
    List<Blog> getSearchBlogs(int sortId, String searchContent, int start);
    // 获取 搜索博主
    List<User> getSearchUsers(String searchContent, int start, int userId);
    // 获取 推荐标签
    List<String> getRecommendLabels(String searchContent);
}
