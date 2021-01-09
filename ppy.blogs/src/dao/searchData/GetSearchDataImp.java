package dao.searchData;

import dao.blogData.GetBlogData;
import dao.blogData.GetBlogDataImp;
import domain.Blog;
import domain.User;
import service.tools.Utils;
import service.userOption.UserOption;
import service.userOption.UserOptionImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GetSearchDataImp implements GetSearchData{

    @Override
    public List<Blog> getSearchBlogs(int sortId, String searchContent, int start) {
        GetBlogData getBlogData = new GetBlogDataImp();
        List<Blog> searchBlogs = new ArrayList<>();
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "select distinct " +
                    "bl.blog_id,ub.blog_author_id,ub.blog_title,ub.blog_scan_num,ub.blog_up_num,ub.blog_pub_date,ub.blog_collect_num " +
                    "from " +
                    "blogs_label as bl " +
                    "inner JOIN " +
                    "user_blogs as ub " +
                    "on " +
                    "bl.blog_id = ub.id " +
                    "inner join (select mc.column_name,bc.blog_id from mycolumns as mc right join blogs_column as bc on mc.id = bc.column_id) as bmc " +
                    "on ub.id = bmc.blog_id " +
                    "where " +
                    "(bl.label_name like ? " +
                    "or " +
                    "ub.blog_title like ? " +
                    "or " +
                    "bmc.column_name like ?) " +
                    "order by ";

            switch(sortId){
                case 1 : sql += "ub.blog_pub_date desc limit " + start + ",8";break;
                case 2 : sql += "ub.blog_scan_num desc limit " + start + ",8";break;
                case 3 : sql += "ub.blog_up_num desc limit " + start + ",8";break;
                case 4 : sql += "ub.blog_collect_num desc limit " + start + ",8";break;
            }
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + searchContent + "%");
            preparedStatement.setString(2, "%" + searchContent + "%");
            preparedStatement.setString(3, "%" + searchContent + "%");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Blog searchBlog = new Blog();
                int id = resultSet.getInt(1);
                searchBlog.setId(id);
                searchBlog.setBlog_author_id(resultSet.getInt(2));
                searchBlog.setBlog_title(resultSet.getString(3));
                searchBlog.setBlog_scan_num(resultSet.getInt(4));
                searchBlog.setBlog_up_num(resultSet.getInt(5));
                searchBlog.setBlog_pub_date(resultSet.getTimestamp(6));
                searchBlog.setStatus(4);
                searchBlog.setBlog_collect_num(resultSet.getInt(7));
                searchBlog.setLabels(getBlogData.getAttributes(id, "select label_name from blogs_label where blog_id=? and label_name!=''", connection));
                searchBlog.setColumns(getBlogData.getAttributes(id, "SELECT mc.column_name from blogs_column as bc INNER JOIN " +
                        "mycolumns as mc ON mc.id = bc.column_id WHERE bc.blog_id = ?", connection));
                searchBlog.setType(getBlogData.getType(id));
                searchBlogs.add(searchBlog);
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
        return searchBlogs;
    }

    @Override
    public List<User> getSearchUsers(String searchContent, int start, int userId) {
        UserOption userOption = new UserOptionImp();
        List<User> searchUsers = new ArrayList<>();
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql="select id, user_name,true_name,email,birthday,sex,head_img,fans_num,follow_num,blogs_num,scan_num,up_num,collected_num,collect_num,introduce from user_inf " +
                    "where user_name like ? limit " + start + ",8";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,  "%" + searchContent  + "%");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User searchUser = userOption.getUser(resultSet.getInt(1));
                searchUsers.add(searchUser);
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
        userOption.checkFollow(searchUsers, userId);
        return searchUsers;
    }

    @Override
    public List<String> getRecommendLabels(String searchContent) {
        List<String> recommendLabels = new ArrayList<>();
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "select label_name from blogs_label where label_name like ? limit 0, 10";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + searchContent  + "%");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                recommendLabels.add(resultSet.getString(1));
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
        return recommendLabels;
    }
}