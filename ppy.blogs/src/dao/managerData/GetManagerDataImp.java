package dao.managerData;

import dao.blogData.GetBlogData;
import dao.blogData.GetBlogDataImp;
import domain.Blog;
import domain.User;
import service.tools.Utils;
import service.userOption.UserOptionImp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GetManagerDataImp implements GetManagerData{
    @Override
    public List<User> getManagerUsers(int start, int rank) {
        List<User> managerUsers = new ArrayList<>();
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql="select id,user_name,email,birthday,head_img,fans_num,follow_num,blogs_num,scan_num,up_num,collected_num,collect_num,rank_ " +
                    "from user_inf " +
                    "where rank_ < ? " +
                    "limit " + start + ",8";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, rank);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User managerUser = new UserOptionImp().getUser(resultSet.getInt(1));
                managerUsers.add(managerUser);
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
        return managerUsers;
    }

    @Override
    public List<Blog> getManagerBlogs(String sql,int status) {
        GetBlogData getBlogData = new GetBlogDataImp();
        List<Blog> blogs = new ArrayList<>();
        try {
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, status);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Blog blog = new Blog();
                int id = resultSet.getInt(1);
                blog.setId(id);
                blog.setBlog_author_id(resultSet.getInt(2));
                blog.setBlog_title(resultSet.getString(3));
                blog.setBlog_scan_num(resultSet.getInt(4));
                blog.setBlog_up_num(resultSet.getInt(5));
                blog.setBlog_collect_num(resultSet.getInt(6));
                blog.setLabels(getBlogData.getAttributes(id, "select label_name from blogs_label where blog_id=?", connection));
                blog.setColumns(getBlogData.getAttributes(id, "SELECT mc.column_name from blogs_column as bc INNER JOIN " +
                        "mycolumns as mc ON mc.id = bc.column_id WHERE bc.blog_id = ?", connection));
                blog.setType(getBlogData.getType(id));
                blog.setStatus(status);
                blog.setRecommend(isRecommend(id));
                blogs.add(blog);
            }

            if (null != resultSet) {
                resultSet.close();
            }
            if (null != preparedStatement) {
                preparedStatement.close();
            }
            if (null != connection) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogs;
    }

    @Override
    public List<Blog> getRecommendBlogs(String sql) {
        GetBlogData getBlogData = new GetBlogDataImp();
        List<Blog> blogs = new ArrayList<>();
        try {
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Blog blog = new Blog();
                int id = resultSet.getInt(1);
                blog.setId(id);
                blog.setBlog_author_id(resultSet.getInt(2));
                blog.setBlog_title(resultSet.getString(3));
                blog.setBlog_scan_num(resultSet.getInt(4));
                blog.setBlog_up_num(resultSet.getInt(5));
                blog.setBlog_collect_num(resultSet.getInt(6));
                blog.setLabels(getBlogData.getAttributes(id, "select label_name from blogs_label where blog_id=?", connection));
                blog.setColumns(getBlogData.getAttributes(id, "SELECT mc.column_name from blogs_column as bc INNER JOIN " +
                        "mycolumns as mc ON mc.id = bc.column_id WHERE bc.blog_id = ?", connection));
                blog.setType(getBlogData.getType(id));
                blog.setStatus(4);
                blog.setRecommend(true);
                blogs.add(blog);
            }

            if (null != resultSet) {
                resultSet.close();
            }
            if (null != preparedStatement) {
                preparedStatement.close();
            }
            if (null != connection) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return blogs;
    }

    @Override
    public boolean isRecommend(int blogId) {
        boolean flag = false;
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "select today_blog from today_recommendation where today_blog=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, blogId);
            resultSet = preparedStatement.executeQuery();
            flag = resultSet.next();

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
    public List<String> getLabels() {
        List<String> labels = new ArrayList<>();
        try {
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "select label_name from home_labels where label_id>?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 0);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                labels.add(resultSet.getString(1));
            }

            if (null != resultSet) {
                resultSet.close();
            }
            if (null != preparedStatement) {
                preparedStatement.close();
            }
            if (null != connection) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return labels;
    }
}