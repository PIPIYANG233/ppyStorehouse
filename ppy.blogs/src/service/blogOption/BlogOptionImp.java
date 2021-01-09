package service.blogOption;

import dao.blogData.GetBlogDataImp;
import dao.blogData.UpdateBlogDataImp;
import dao.page.GetPageDataImp;
import domain.*;
import service.tools.Utils;

import java.sql.Connection;
import java.util.List;

public class BlogOptionImp implements BlogOption{

    @Override
    public BlogPage getBlogPage(boolean isMyself, int now, int start, int pages, int num, int authorId) {
        if (isMyself){ // 是自己
            String sql = "select id,blog_author_id,blog_title,blog_scan_num,blog_up_num,blog_pub_date,status,blog_collect_num " +
                    "from user_blogs where blog_author_id=? order by blog_pub_date desc limit " + start + "," + 3;
            return new GetPageDataImp().getBlogPage(now, start, pages, num, authorId, sql);
        }else{ // 不是自己
            String sql = "select id,blog_author_id,blog_title,blog_scan_num,blog_up_num,blog_pub_date,status,blog_collect_num " +
                    "from user_blogs where blog_author_id=? and status=4 order by blog_pub_date desc limit " + start + "," + 3;
            return new GetPageDataImp().getBlogPage(now, start, pages, num, authorId, sql);
        }
    }

    @Override
    public BlogPage getBlogPage(boolean isMyself, boolean isFromBlog, BlogPage blogPage, int authorId) {
        String mainContent = blogPage.getMainContent();
        int year = blogPage.getYear();
        String theYear = "";
        String theMonth = "";
        // 年份
        if(year != 0){
            theYear += year;
            int month = blogPage.getMonth();
            if(month != 0){
                if(month < 10){
                    theMonth += "0" + month;
                }else{
                    theMonth += month;
                }
            }
        }
        // 类型
        String theType = "";
        int type = blogPage.getType();
        if(type != 0){
            theType += type;
        }
        // 专栏
        String column = blogPage.getColumn();
        if(column.equals("")){
            column = "(select bc.blog_id,mc.column_name from blogs_column as bc left join " +
                    "mycolumns as mc on bc.column_id = mc.id) ";
        }else{
            column = "(select bc.blog_id,mc.column_name from blogs_column as bc inner join " +
                    "mycolumns as mc on bc.column_id = mc.id and mc.column_name like '%" + column + "%') ";
        }
        if (isMyself){ // 是自己
            String sql = "select distinct " +
                    "ub.id,ub.blog_author_id,ub.blog_title,ub.blog_scan_num,ub.blog_up_num,ub.blog_pub_date,ub.status,ub.blog_collect_num " +
                    "from user_blogs as ub inner join blogs_type as bt on ub.id = bt.blog_id inner join blogs_label as bl on " +
                    "ub.id = bl.blog_id inner join" +  column + "as bmc on ub.id = bmc.blog_id where ub.blog_author_id=? "+
                    "and bt.type like '%"  + theType + "%' and ( bl.label_name like '%" + mainContent + "%' or " +
                    "ub.blog_title like '%" + mainContent + "%' ) and ub.blog_pub_date like '" + theYear +  "%-" + theMonth + "%' " +
                    "order by blog_pub_date desc limit " + blogPage.getStart() + ",3";
            return new GetPageDataImp().getBlogPage(isFromBlog, blogPage, authorId, sql);
        }else{ // 不是自己
            String sql = "select distinct " +
                    "ub.id,ub.blog_author_id,ub.blog_title,ub.blog_scan_num,ub.blog_up_num,ub.blog_pub_date,ub.status,ub.blog_collect_num " +
                    "from user_blogs as ub inner join blogs_type as bt on ub.id = bt.blog_id inner join blogs_label as bl on " +
                    "ub.id = bl.blog_id inner join " + column + "as bmc on ub.id = bmc.blog_id where ub.blog_author_id=? "+
                    "and bt.type like '%"  + theType + "%' and ( bl.label_name like '%" + mainContent + "%' or " +
                    "ub.blog_title like '%" + mainContent + "%' ) and ub.blog_pub_date like '" + theYear +  "%-" + theMonth + "%' " +
                    "and ub.status=4 order by blog_pub_date desc limit " + blogPage.getStart() + ",3";
            return new GetPageDataImp().getBlogPage(isFromBlog, blogPage, authorId, sql);
        }
    }

    @Override
    public CollectPage getCollectPage(int now, int start, int pages, int num, int userId) {
        String sql = "SELECT " +
                "bc.blog_id, ub.blog_author_id, ub.blog_title, ub.blog_scan_num, ub.blog_up_num, ub.blog_pub_date, ub.status, ub.blog_collect_num " +
                "from " +
                "blogs_collect as bc " +
                "INNER JOIN " +
                "user_blogs as ub " +
                "ON " +
                "bc.blog_id = ub.id " +
                "WHERE " +
                "bc.user_id=? " +
                "and ub.status=4 " +
                "limit " +
                start + "," + 4;
        return new GetPageDataImp().getCollectPage(now, start, pages, num, userId, sql);
    }

    @Override
    public void updateBlog(int blogId, int authorId, String blogTitle, int status, int type, String[] labels, String[] columns) {
        new UpdateBlogDataImp().updateBlog(blogId, authorId, blogTitle, status, type, labels, columns);
    }

    @Override
    public void updateColumns(int blogId, String[] attributes, String deleteSql, String insertSql) {

    }

    @Override
    public void updateScan(int blogId, int authorId) {
        new UpdateBlogDataImp().updateScan(blogId, authorId);
    }

    @Override
    public void updateUp(boolean flag, int blogId, int userId, int authorId) {
        if(flag){ //点赞
            String sqls[] = {
                    "insert into blogs_up(blog_id, user_id)values(?,?)",
                    "update user_inf set up_num=up_num+1 where id=?",
                    "update user_blogs set blog_up_num=blog_up_num+1,recommendation=recommendation+5 where id=?"

            };
            new UpdateBlogDataImp().updateUp(blogId, userId, authorId, sqls);
        }else{ // 取消点赞
            String sqls[] = {
                    "delete from blogs_up where blog_id=? and user_id=?",
                    "update user_inf set up_num=up_num-1 where id=?",
                    "update user_blogs set blog_up_num=blog_up_num-1,recommendation=recommendation-5 where id=?"

            };
            new UpdateBlogDataImp().updateUp(blogId, userId, authorId, sqls);
        }
    }

    @Override
    public void updateCollect(boolean flag, int blogId, int userId, int authorId) {
        if(flag){ //收藏
            String[] sqls = {
                    "insert into blogs_collect(user_id, blog_id)values(?,?)",
                    "update user_blogs set blog_collect_num=blog_collect_num+1,recommendation=recommendation+10 where id=?",
                    "update user_inf set collected_num=collected_num+1 where id=?",
                    "update user_inf set collect_num=collect_num+1 where id=?"
            };
            new UpdateBlogDataImp().updateCollect(blogId, userId, authorId, sqls);
        }else{ //取消收藏
            String[] sqls = {
                    "delete from blogs_collect where user_id=? and blog_id=?",
                    "update user_blogs set blog_collect_num=blog_collect_num-1,recommendation=recommendation-10 where id=?",
                    "update user_inf set collected_num=collected_num-1 where id=?",
                    "update user_inf set collect_num=collect_num-1 where id=?"
            };
            new UpdateBlogDataImp().updateCollect(blogId, userId, authorId, sqls);
        }
    }

    @Override
    public void updateStatus(int blogId, int status) {
        new UpdateBlogDataImp().updateStatus(blogId, status);
    }

    @Override
    public void updateRecommendBlog(int blogId, boolean flag) {
        if (flag){ // 推荐博客
            new UpdateBlogDataImp().updateRecommendBlog(blogId, "insert into today_recommendation values(?)");
        }else{ // 删除推荐
            new UpdateBlogDataImp().updateRecommendBlog(blogId, "delete from today_recommendation where today_blog=?");
        }
    }

    @Override
    public void addComment(int commentId, int userId, int blogId, String content) {
        new UpdateBlogDataImp().addComment(commentId, userId, blogId, content);
    }

    @Override
    public void subComment(int commentId) {
        new UpdateBlogDataImp().subComment(commentId);
    }

    @Override
    public void addRequest(int requestId, int commentId, String content, int commentUserId, int requestUserId) {
        new UpdateBlogDataImp().addRequest(requestId, commentId, content, commentUserId, requestUserId);
    }

    @Override
    public void subRequest(int requestId) {
        new UpdateBlogDataImp().subRequest(requestId);
    }

    @Override
    public void deleteBlog(int blogId, int userId) {
        new UpdateBlogDataImp().deleteBlog(blogId, userId);
    }

    @Override
    public void saveBlog(int blogId, int authorId, String blogTitle, int status, int type, String[] labels, String[] columns) {
        new UpdateBlogDataImp().saveBlog(blogId, authorId, blogTitle, status, type, labels, columns);
    }

    @Override
    public CheckBlog getCheckBlog(Blog blog, User author, int userId) {
        return new GetBlogDataImp().getCheckBlog(blog, author, userId);
    }

    @Override
    public CheckBlog getCheckBlog(Blog blog) {
        return new GetBlogDataImp().getCheckBlog(blog);
    }

    @Override
    public int getType(int blogId) {
        return new GetBlogDataImp().getType(blogId);
    }

    @Override
    public List<String> getAttributes(boolean flag, int blogId) {
        List<String> attributes = null;
        try{
            Connection connection = Utils.getConnection();
            if(flag){ // 获取标签
                String sql = "select label_name from blogs_label where blog_id=? and label_name!=''";
                attributes = new GetBlogDataImp().getAttributes(blogId, sql, connection);
                if(null != connection){
                    connection.close();
                }
            }else{ // 获取分类
                String sql = "SELECT mc.column_name from blogs_column as bc INNER JOIN " +
                        "mycolumns as mc ON mc.id = bc.column_id WHERE bc.blog_id = ?";
                attributes = new GetBlogDataImp().getAttributes(blogId, sql, connection);
                if(null != connection){
                    connection.close();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return attributes;
    }
}
