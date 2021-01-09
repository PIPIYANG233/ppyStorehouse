package dao.homeData;

import domain.Blog;
import service.blogOption.BlogOptionImp;
import service.tools.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetHomeDataImp implements GetHomeData{
    @Override
    public List<String> getHomeLabels() {
        List<String> homeLabels = new ArrayList<>();
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "select label_name from home_labels";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                homeLabels.add(resultSet.getString(1));
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
        return homeLabels;
    }

    @Override
    public List<Blog> getCenterRecommendBlogs(int labelId, int sortId) {
        List<Blog> centerRecommendBlogs = null;
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            if(labelId == 0){
                String sql = "select id,blog_author_id,blog_title,blog_scan_num,blog_up_num,blog_pub_date,blog_collect_num " +
                        "from user_blogs where status=? order by recommendation desc, ";
                switch(sortId){
                    case 0 : sql += "blog_pub_date desc  limit 0,8";break;
                    case 1 : sql += "blog_scan_num desc  limit 0,8";break;
                    case 2 : sql += "blog_up_num desc  limit 0,8";break;
                    case 3 : sql += "blog_collect_num desc  limit 0,8";break;
                }
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, 4);
            }else{
                String sql = "SELECT " +
                        "bhl.blog_id,ub.blog_author_id,ub.blog_title,ub.blog_scan_num,ub.blog_up_num,ub.blog_pub_date,ub.blog_collect_num " +
                        "FROM " +
                        "(SELECT DISTINCT " +
                        "bl.blog_id " +
                        "from " +
                        "blogs_label as bl " +
                        "INNER JOIN " +
                        "home_labels as hl " +
                        "WHERE " +
                        "bl.label_name = hl.label_name and hl.label_id=?) AS bhl " +
                        "INNER JOIN " +
                        "user_blogs as ub " +
                        "where " +
                        "bhl.blog_id = ub.id and ub.status=? " +
                        "ORDER BY " +
                        "recommendation DESC, ";

                switch(sortId){
                    case 0 : sql += "blog_pub_date desc limit 0,8";break;
                    case 1 : sql += "blog_scan_num desc limit 0,8";break;
                    case 2 : sql += "blog_up_num desc limit 0,8";break;
                    case 3 : sql += "blog_collect_num desc limit 0,8";break;
                }

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, labelId);
                preparedStatement.setInt(2, 4);
            }

            resultSet = preparedStatement.executeQuery();
            centerRecommendBlogs = getBlogs(resultSet);

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
        return centerRecommendBlogs;
    }

    @Override
    public List<Blog> getRightRecommendBlogs() {
        List<Blog> rightRecommendBlogs = null;
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "SELECT " +
                    "tr.today_blog,ub.blog_author_id,ub.blog_title,ub.blog_scan_num,ub.blog_up_num,ub.blog_pub_date,ub.blog_collect_num " +
                    "FROM " +
                    "today_recommendation as tr " +
                    "INNER JOIN " +
                    "user_blogs as ub " +
                    "WHERE " +
                    "tr.today_blog = ub.id and ub.status=? " +
                    "ORDER BY " +
                    "recommendation DESC limit 0,5";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 4);
            resultSet = preparedStatement.executeQuery();
            rightRecommendBlogs = getBlogs(resultSet);

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
        return rightRecommendBlogs;
    }

    public static List<Blog> getBlogs(ResultSet resultSet) throws SQLException {
        List<Blog> blogs = new ArrayList<>();
        while(resultSet.next()){
            Blog blog = new Blog();
            int id = resultSet.getInt(1);
            blog.setId(id);
            blog.setBlog_author_id(resultSet.getInt(2));
            blog.setBlog_title(resultSet.getString(3));
            blog.setBlog_scan_num(resultSet.getInt(4));
            blog.setBlog_up_num(resultSet.getInt(5));
            blog.setBlog_pub_date(resultSet.getTimestamp(6));
            blog.setStatus(4);
            blog.setType(new BlogOptionImp().getType(id));
            blog.setBlog_collect_num(resultSet.getInt(7));
            blog.setLabels(new BlogOptionImp().getAttributes(true, blog.getId()));
            blog.setColumns(new BlogOptionImp().getAttributes(false, blog.getId()));
            blogs.add(blog);
        }
        return blogs;
    }
}