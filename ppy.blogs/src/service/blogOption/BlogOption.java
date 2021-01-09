package service.blogOption;

import domain.*;

import java.sql.Connection;
import java.util.List;

public interface BlogOption {
    // 获得 默认博客分页
    BlogPage getBlogPage(boolean isMyself, int now, int start, int pages, int num, int authorId);
    // 获得 条件博客分页
    BlogPage getBlogPage(boolean isMyself, boolean isFromBlog,  BlogPage blogPage, int authorId);
    // 获取 收藏分页
    CollectPage getCollectPage(int now, int start, int pages, int num, int userId);
    // 重新编辑博客
    void updateBlog(int blogId, int authorId, String blogTitle, int status, int type, String[] labels, String[] columns);
    // 更新博客 分类
    void updateColumns(int blogId, String[] attributes, String deleteSql, String insertSql);
    // 更新 访客量
    void updateScan(int blogId, int authorId);
    // 更新博客 点赞量
    void updateUp(boolean flag, int blogId,int userId, int authorId);
    // 更新博客 收藏量
    void updateCollect(boolean flag, int blogId,int userId, int authorId);
    // 更新博客 状态
    void updateStatus(int blogId, int status);
    // 更新 博客推荐状态
    void updateRecommendBlog(int blogId, boolean flag);
    // 博客 添加评论
    void addComment(int commentId, int userId, int blogId, String content);
    // 博客 删除评论
    void subComment(int commentId);
    // 博客 添加回复
    void addRequest(int requestId, int commentId, String content, int commentUserId, int requestUserId);
    // 博客 删除回复
    void subRequest(int requestId);
    // 删除博客
    void deleteBlog(int blogId, int userId);
    // 保存博客
    void saveBlog(int blogId, int authorId, String blogTitle, int status, int type, String[] labels, String[] columns);
    // 获取 具体一篇博客
    CheckBlog getCheckBlog(Blog blog, User author, int userId);
    // 获取 具体一篇博客
    CheckBlog getCheckBlog(Blog blog);
    // 获取博客 是否原创
    int getType(int blogId);
    // 获得博客 标签 或 分类
    List<String> getAttributes(boolean flag, int blogId);
}
