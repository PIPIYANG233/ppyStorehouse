package dao.blogData;

import dao.userData.GetUserDataImp;
import domain.*;
import service.tools.FileOption;
import service.tools.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GetBlogDataImp implements GetBlogData{
    // 获取单个博客
    @Override
    public Blog getBlog(int blogId) {

        return null;
    }

    // 获取博客集合
    @Override
    public List<Blog> getBlogs(int authorOrUserId, String sql) {
        List<Blog> blogs = new ArrayList<>();
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, authorOrUserId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Blog blog = new Blog();
                // 博客id
                int blogId = resultSet.getInt(1);
                blog.setId(blogId);
                // 博客作者
                blog.setBlog_author_id(resultSet.getInt(2));
                // 博客标题
                blog.setBlog_title(resultSet.getString(3));
                // 博客阅读量
                blog.setBlog_scan_num(resultSet.getInt(4));
                // 博客点赞量
                blog.setBlog_up_num(resultSet.getInt(5));
                // 博客发布时间
                blog.setBlog_pub_date(resultSet.getTimestamp(6));
                // 博客状态
                blog.setStatus(resultSet.getInt(7));
                // 博客收藏量
                blog.setBlog_collect_num(resultSet.getInt(8));
                // 博客标签
                blog.setLabels(getAttributes(blogId, "select label_name from blogs_label where blog_id=? and label_name!=''", connection));
                // 博客分类
                blog.setColumns(getAttributes(blogId, "SELECT mc.column_name from blogs_column as bc INNER JOIN " +
                        "mycolumns as mc ON mc.id = bc.column_id WHERE bc.blog_id = ?", connection));
                // 博客是否原创
                blog.setType(getType(blogId));
                blogs.add(blog);
            }

            if(null != resultSet){
                resultSet.close();
            }
            if(null != preparedStatement){
                preparedStatement.close();
            }
            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return blogs;
    }

    // 获得博客 标签 或 分类
    @Override
    public List<String> getAttributes(int blogId, String sql, Connection connection) {
        List<String> attributes = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, blogId);
            if(null != preparedStatement){
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    attributes.add(resultSet.getString(1));
                }
            }

            if(null != resultSet){
                resultSet.close();
            }

            if(null != preparedStatement){
                preparedStatement.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return attributes;
    }

    // 博客 是否原创
    @Override
    public int getType(int blogId){
        int type = 1;
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "select type from blogs_type where blog_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, blogId);
            if(null != preparedStatement){
                resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    type = resultSet.getInt(1);
                }
            }

            if(null != resultSet){
                resultSet.close();
            }

            if(null != preparedStatement){
                preparedStatement.close();
            }

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return type;
    }

    @Override
    public boolean isUpOrCollectOrFollow(int id1, int id2, String sql) {
        boolean flag = false;
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id1);
            preparedStatement.setInt(2, id2);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                flag = true;
            }

            if(null != resultSet){
                resultSet.close();
            }

            if(null != preparedStatement){
                preparedStatement.close();
            }

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<Comment> getComments(int blogId) {
        List<Comment> comments = new ArrayList<>();
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "select id,user_id,comment_content from blogs_comment where blog_id=? order by num desc";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, blogId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Comment comment = new Comment();
                comment.setBlog_id(blogId);
                int id = resultSet.getInt(1);
                comment.setId(id);
                int userId = resultSet.getInt(2);
                comment.setUser_id(userId);
                comment.setComment_content(resultSet.getString(3));
                comment.setCommentUser(new GetUserDataImp().getUser(userId, connection));
                comment.setRequests(getRequests(id));
                comments.add(comment);
            }

            if(null != resultSet){
                resultSet.close();
            }

            if(null != preparedStatement){
                preparedStatement.close();
            }

            if(null != connection){
                connection.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public List<Request> getRequests(int commentId) {
        List<Request> requests = new ArrayList<>();
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "select id,req_content,comment_user_id,req_user_id from comment_req where comment_id=? order by num desc";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, commentId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Request request = new Request();
                request.setId(resultSet.getInt(1));
                request.setReq_content(resultSet.getString(2));
                int commentUserId = resultSet.getInt(3);
                request.setComment_user_id(commentUserId);
                request.setCommentUser(new GetUserDataImp().getUser(commentUserId, connection));
                int requestUserId = resultSet.getInt(4);
                request.setReq_user_id(requestUserId);
                request.setRequestUser(new GetUserDataImp().getUser(requestUserId, connection));
                requests.add(request);
            }

            if(null != resultSet){
                resultSet.close();
            }

            if(null != preparedStatement){
                preparedStatement.close();
            }

            if(null != connection){
                connection.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public String getBlogContent(String path) {
        String content = "";
        content = FileOption.readFromFile(path);
        return content;
    }
    // 查看博客
    @Override
    public CheckBlog getCheckBlog(Blog blog, User author, int userId) {
        CheckBlog checkBlog = new CheckBlog();
        checkBlog.setBlog(blog);
        checkBlog.setHtmlCode(getBlogContent("F:/ppy.blogs/web/" + "user_blogs/blogs_htmlCode/" + blog.getId() + ".txt"));
        checkBlog.setMarkdown(getBlogContent("F:/ppy.blogs/web/" + "user_blogs/blogs_markdown/" + blog.getId() + ".txt"));
        checkBlog.setAuthor(author);
        List<Blog>recommendBlogs = getBlogs(author.getId(), "select id,blog_author_id,blog_title,blog_scan_num,blog_up_num,blog_pub_date,status,blog_collect_num from user_blogs" +
                " where blog_author_id=? and status=4" + " order by blog_scan_num desc limit 0,3");
        checkBlog.setRecommendBlogs(recommendBlogs);
        if(userId != 0){
            checkBlog.setUp(isUpOrCollectOrFollow(blog.getId(), userId, "select blog_id from blogs_up where blog_id=? and user_id=?"));
            checkBlog.setFollow(isUpOrCollectOrFollow(userId, author.getId(), "select fan_id from user_fans_follow where fan_id=? and follow_id=?"));
            checkBlog.setCollect(isUpOrCollectOrFollow(blog.getId(), userId, "select blog_id from blogs_collect where blog_id=? and user_id=?"));
        }else{
            checkBlog.setUp(false);
            checkBlog.setFollow(false);
            checkBlog.setCollect(false);
        }
        checkBlog.setComments(getComments(blog.getId()));
        return checkBlog;
    }
    // 修改博客
    public CheckBlog getCheckBlog(Blog blog) {
        CheckBlog checkBlog = new CheckBlog();
        checkBlog.setBlog(blog);
        checkBlog.setHtmlCode(getBlogContent("F:/ppy.blogs/web/" + "user_blogs/blogs_htmlCode/" + blog.getId() + ".txt"));
        checkBlog.setMarkdown(getBlogContent("F:/ppy.blogs/web/" + "user_blogs/blogs_markdown/" + blog.getId() + ".txt"));
        checkBlog.setColumnNames(new GetUserDataImp().getColumnNames(blog.getBlog_author_id()));
        return checkBlog;
    }
}
