package service.managerOption;

import domain.Blog;
import domain.User;

import java.util.List;

public interface ManagerOption {
    // 获得 被管理的用户
    List<User> getManagerUsers(int start, int rank);
    // 获得 被管理的博客
    List<Blog> getManagerBlogs(int start, int status);
    // 获得被推荐的博客
    List<Blog> getRecommendBlogs();
    // 获取 首页标签
    List<String> getLabels();
    // 获得 被管理的用户 被管理的博客 页数
    int getManagerNum(int managerWhat, Object object, int parametersNum);
    // 获得 被管理 页数
    int getPages(int num, int size);
    // 更新 首页标签
    void updateLabel(String newLabel, int labelId);
}
