package service.searchOption;

import domain.Blog;
import domain.User;

import java.util.List;

public interface SearchOption {
    // 获取 搜索结果数量
    int getSearchNum(boolean flag, Object object, int parametersNum);
    // 获取 搜索页数
    int getPages(int num, int size);
    // 获取 搜索博客
    List<Blog> getSearchBlogs(int sortId, String searchContent, int start);
    // 获取 搜索博主
    List<User> getSearchUsers(String searchContent, int start, int userId);
    // 获取 推荐标签
    List<String> getRecommendLabels(String searchContent);
}
