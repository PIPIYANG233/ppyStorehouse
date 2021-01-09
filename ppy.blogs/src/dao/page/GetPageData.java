package dao.page;

import domain.*;

public interface GetPageData {
    // 获得 默认博客分页
    BlogPage getBlogPage(int now, int start, int pages, int num, int authorId, String sql);
    // 获得 条件博客分页
    BlogPage getBlogPage(boolean isFromBlog, BlogPage blogPage, int authorId, String sql);
    // 获得 粉丝分页
    FanPage getFanPage(int now, int start, int pages, int num, int userId, String sql);
    // 获得 关注分页
    FollowPage getFollowPage(int now, int start, int pages, int num, int userId, String sql);
    // 获取 收藏分页
    CollectPage getCollectPage(int now, int start, int pages, int num, int userId, String sql);
    // 获取 分类分页
    ColumnPage getColumnPage(int now, int start, int pages, int num, int userId);
    // 获取 分类管理分页
    ColumnManagerPage getColumnManagerPage(int now, int start, int pages, int num, int columnId, String sql);
    // 获取 分类管理分页
    ColumnManagerPage getColumnManagerPage(ColumnManagerPage columnManagerPage, int columnId, String sql);
    // 获取 粉丝 关注 收藏 博客 的数量
    int getNum(Object object, String sql, int parametersNum);
    // 获取 页数
    int getPages(int num, int size);
}
