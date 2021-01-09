package dao.blogData;

import domain.*;

import java.sql.Connection;
import java.util.List;

public interface GetBlogData {
    // 获取单个博客
    Blog getBlog(int blogId);
    // 获取博客集合
    List<Blog> getBlogs(int authorOrUserId, String sql);
    // 获得博客 标签 或 分类
    List<String> getAttributes(int blogId, String sql, Connection connection);
    // 博客 是否原创
    int getType(int blogId);
    // 博客是否被 点赞 收藏， 作者是否被 关注
    boolean isUpOrCollectOrFollow(int id1, int id2, String sql);
    // 获取博客 评论
    List<Comment> getComments(int blogId);
    // 获取博客 评论回复
    List<Request> getRequests(int commentId);
    // 获取博客 html内容 与 markdown 内容
    String getBlogContent(String path);
    // 查看博客 或 修改博客
    CheckBlog getCheckBlog(Blog blog, User author, int userId);
}
