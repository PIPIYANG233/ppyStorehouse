package dao.blogData;

public interface UpdateBlogData {
    // 重新编辑博客
    void updateBlog(int blogId, int authorId, String blogTitle, int status, int type, String[] labels, String[] columns);
    // 更新博客 标签 或 分类
    void updateAttributes(boolean flag, int blogId, String[] attributes, String deleteSql, String insertSql);
    // 更新博客 是否原创
    void updateType(int type, int blogId, String sql);
    // 更新 访客量
    void updateScan(int blogId, int authorId);
    // 更新博客 点赞量
    void updateUp(int blogId,int userId, int authorId, String[] sqls);
    // 更新博客 收藏量
    void updateCollect(int blogId,int userId, int authorId, String[] sqls);
    // 更新博客 状态
    void updateStatus(int blogId, int status);
    // 更新 博客推荐状态
    void updateRecommendBlog(int blogId, String sql);
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
}