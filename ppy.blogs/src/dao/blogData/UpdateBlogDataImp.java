package dao.blogData;

import dao.Tool;
import service.tools.Utils;
import java.sql.Connection;
import java.sql.Timestamp;

public class UpdateBlogDataImp implements UpdateBlogData{
    @Override
    public void updateBlog(int blogId, int authorId, String blogTitle, int status, int type, String[] labels, String[] columns) {
        try{
            Connection connection = Utils.getConnection();
            String sql = "";
            sql = "update user_blogs set blog_title=?,status=? where id=?";
            Tool.update(blogTitle, status, blogId, sql, connection);

            // 更新是否原创
            updateType(type, blogId, "update blogs_type set type=? where blog_id=?");
            // 更新标签
            updateAttributes(true, blogId, labels, "delete from blogs_label where blog_id=?", "insert blogs_label (blog_id, label_name)value(?,?)");
            // 更新分类
            updateAttributes(false, blogId, columns, "delete from blogs_column where blog_id=?", "insert blogs_column (blog_id, column_id) value(?,(SELECT id from mycolumns " +
                    "where column_name=? and ownerId= " + authorId + "))");

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateAttributes(boolean flag, int blogId, String[] attributes, String deleteSql, String insertSql) {
        try{
            Connection connection = Utils.getConnection();

            Tool.update(blogId, deleteSql, connection);

            if(null != attributes){
                for(String attribute : attributes){
                    Tool.update(blogId, attribute, insertSql, connection);
                }
            }else{
                if(flag){ // 更新标签
                    Tool.update(blogId, "", insertSql, connection);
                }else{ // 更新专栏
                    Tool.update(blogId, 0, "insert blogs_column (blog_id, column_id) value(?,?)", connection);
                }
            }

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateType(int type, int blogId, String sql) {
        try{
            Connection connection = Utils.getConnection();

            Tool.update(type, blogId, sql, connection);

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateScan(int blogId, int authorId) {
        try{
            Connection connection = Utils.getConnection();

            String sql = "";
            sql = "update user_blogs set blog_scan_num=blog_scan_num+1,recommendation=recommendation+1 where id=?";
            Tool.update(blogId, sql, connection);

            sql = "update user_inf set scan_num=scan_num+1 where id=?";
            Tool.update(authorId, sql, connection);

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateUp(int blogId, int userId, int authorId, String[] sqls) {
        try{
            Connection connection = Utils.getConnection();
            // 更新点赞表
            Tool.update(blogId, userId, sqls[0], connection);
            // 更新作者被点赞总量
            Tool.update(authorId, sqls[1], connection);
            // 更新博客被点赞量
            Tool.update(blogId, sqls[2], connection);

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateCollect(int blogId, int userId, int authorId, String[] sqls) {
        try{
            Connection connection = Utils.getConnection();
            // 更新收藏表
            Tool.update(userId, blogId, sqls[0], connection);
            // 更新博客被收藏量
            Tool.update(blogId, sqls[1], connection);
            // 更新作者被收藏总量
            Tool.update(authorId, sqls[2], connection);
            // 更新用户的收藏数
            Tool.update(userId, sqls[3], connection);

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateStatus(int blogId, int status) {
        try{
            Connection connection = Utils.getConnection();

            String sql = "update user_blogs set status=? where id=?";
            Tool.update(status, blogId, sql, connection);

            // 假如审核通过则更新发布时间
            if(status == 4){
                sql = "update user_blogs set blog_pub_date=? where id=?";
                Tool.update(new Timestamp(System.currentTimeMillis()), blogId, sql, connection);
            }

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecommendBlog(int blogId, String sql) {
        try{
            Connection connection = Utils.getConnection();

            Tool.update(blogId, sql, connection);

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addComment(int commentId, int userId, int blogId, String content) {
        try{
            Connection connection = Utils.getConnection();

            String sql = "insert into blogs_comment (id, user_id,blog_id,comment_content) value(?,?,?,?)";
            Tool.update(commentId, userId, blogId, content, sql, connection);

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void subComment(int commentId) {
        try{
            Connection connection = Utils.getConnection();

            String sql = "delete from blogs_comment where id=?";
            Tool.update(commentId, sql, connection);

            sql = "delete from comment_req where comment_id=?";
            Tool.update(commentId, sql, connection);

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addRequest(int requestId, int commentId, String content, int commentUserId, int requestUserId) {
        try{
            Connection connection = Utils.getConnection();

            String sql = "insert into comment_req (id,comment_id,req_content,comment_user_id,req_user_id) value(?,?,?,?,?)";
            Tool.update(requestId, commentId, content, commentUserId, requestUserId, sql, connection);

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void subRequest(int requestId) {
        try{
            Connection connection = Utils.getConnection();

            String sql = "delete from comment_req where id=?";
            Tool.update(requestId, sql, connection);

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBlog(int blogId, int userId) {
        try{
            Connection connection = Utils.getConnection();
            String sql="";
            //减少用户收藏数
            sql = "UPDATE " +
                    "user_inf as ui " +
                    "SET " +
                    "ui.collect_num = ui.collect_num-1 " +
                    "WHERE " +
                    "ui.id " +
                    "IN " +
                    " (SELECT " +
                    "bc.user_id " +
                    "FROM " +
                    "blogs_collect as bc " +
                    "WHERE " +
                    "bc.blog_id = ?)";
            Tool.update(blogId, sql, connection);
            // 删除博客
            sql = "delete from user_blogs where id=?";
            Tool.update(blogId, sql, connection);
            // 删除博客回复
            sql = "DELETE FROM " +
                    "comment_req as cr " +
                    "WHERE " +
                    "cr.comment_id " +
                    "IN " +
                    "(SELECT " +
                    "bc.id " +
                    "FROM " +
                    "blogs_comment as bc " +
                    "where " +
                    "bc.blog_id=?)";
            Tool.update(blogId, sql, connection);
            // 删除博客评论
            sql = "delete from blogs_comment where blog_id=?";
            Tool.update(blogId, sql, connection);
            // 删除博客分类
            sql = "delete from blogs_column where blog_id=?";
            Tool.update(blogId, sql, connection);
            // 删除博客标签
            sql = "delete from blogs_label where blog_id=?";
            Tool.update(blogId, sql, connection);
            // 删除博客是否原创
            sql = "delete from blogs_type where blog_id=?";
            Tool.update(blogId, sql, connection);
            // 删除博客点赞表
            sql = "delete from blogs_up where blog_id=?";
            Tool.update(blogId, sql, connection);
            // 删除博客收藏表
            sql = "delete from blogs_collect where blog_id=?";
            Tool.update(blogId, sql, connection);
            // 减少作者博客数
            sql = "update user_inf set blogs_num=blogs_num-1 where id=?";
            Tool.update(userId, sql, connection);

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveBlog(int blogId, int authorId, String blogTitle, int status, int type, String[] labels, String[] columns) {
        try{
            Connection connection = Utils.getConnection();
            String sql = "";
            sql = "insert user_blogs (id,blog_author_id,blog_title,status)value(?,?,?,?)";
            Tool.update(blogId, authorId, blogTitle, status, sql, connection);

            sql = "update user_inf set blogs_num=blogs_num+1 where id=?";
            Tool.update(authorId, sql, connection);

            // 保存是否原创
            sql = "insert blogs_type (blog_id, type)value(?,?)";
            Tool.update(blogId, type, sql, connection);
            // 保存标签
            if(null != labels){
                sql = "insert blogs_label (blog_id, label_name)value(?,?)";
                for(String label : labels){
                    Tool.update(blogId, label, sql, connection);
                }
            }else{
                Tool.update(blogId, "","insert blogs_label values(?,?)", connection);
            }
            // 保存分类
            if(null != columns){
                sql = "insert blogs_column (blog_id, column_id) value(?,(SELECT id from mycolumns where column_name=? and ownerId=" + authorId + "))";
                for(String column : columns){
                    Tool.update(blogId, column, sql, connection);
                }
            }else{
                Tool.update(blogId, 0, "insert into blogs_column values(?,?)", connection);
            }

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}