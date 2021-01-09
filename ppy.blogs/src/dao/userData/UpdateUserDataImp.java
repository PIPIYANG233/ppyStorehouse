package dao.userData;

import dao.Tool;
import dao.blogData.UpdateBlogData;
import dao.blogData.UpdateBlogDataImp;
import service.tools.SendEmail;
import service.tools.Utils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateUserDataImp implements UpdateUserData{
    @Override
    public boolean insert(String name, String email, String pwd) {
        boolean flag = false;
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            // 获得 PreparedStatement ResultSet
            String sql = "select pwd from user_inf where email=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            if(null != preparedStatement){
                resultSet = preparedStatement.executeQuery();
            }

            // 不存在该用户
            if(!resultSet.next()){
                int id = (int) (Math.random() * 1000000);
                flag = SendEmail.sendEmail(email, "注册成功!您的账号为: " + id);
                if(flag){
                    sql = "insert into user_inf(id,user_name,true_name,email,pwd,head_img)values(?,?,?,?,?,?)";
                    Tool.update(id,name, name, email, pwd, "/user_head/10000.jpg", sql, connection);
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
        return flag;
    }

    @Override
    public void updateHead(int userId, String head_img) {
        try{
            Connection connection = Utils.getConnection();
            String sql = "update user_inf set head_img=? where id=? ";
            Tool.update(head_img, userId, sql, connection);

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserInf(String user_name, String true_name, Date birthday, int sex, String introduce, int userId) {
        try{
            Connection connection = Utils.getConnection();

            String sql = "update user_inf set user_name=?, true_name=?, birthday=?, sex=?, introduce=? where id=?";
            Tool.update(user_name, true_name, birthday, sex, introduce, userId, sql, connection);

            if(null != connection){
                connection.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateFan(int userId, int followId, String[] sqls) {
        try{
            Connection connection = Utils.getConnection();

            Tool.update(userId, followId, sqls[0], connection);

            Tool.update(userId, sqls[1], connection);

            Tool.update(followId, sqls[2], connection);

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateManager(int userId, String sql) {
        try{
            Connection connection = Utils.getConnection();;

            Tool.update(userId, sql, connection);

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId) {
        try{
            Connection connection = Utils.getConnection();;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            // 删除用户
            String sql = "";
            sql = "delete from user_inf where id=?";
            Tool.update(userId, sql, connection);
            // 减少 用户粉丝数
            sql = "update user_inf set fans_num=fans_num-1 where id in (select follow_id from user_fans_follow where fan_id=?)";
            Tool.update(userId, sql, connection);
            // 减少 用户关注数
            sql = "update user_inf set follow_num=follow_num-1 where id in (select fan_id from user_fans_follow where follow_id=?)";
            Tool.update(userId, sql, connection);
            // 删除用户 粉丝表 关注表
            sql = "delete from user_fans_follow where fan_id=? or follow_id=?";
            Tool.update(userId, userId, sql, connection);
            // 删除用户 点赞表
            sql = "delete from blogs_up where user_id=?";
            Tool.update(userId, sql, connection);
            // 删除用户 收藏表
            sql = "delete from blogs_collect where user_id=?";
            Tool.update(userId, sql, connection);
            // 删除用户所有分类专栏
            sql = "delete from mycolumns where ownerId=?";
            Tool.update(userId, sql, connection);
            // 删除用户 博客
            sql = "select id from user_blogs where blog_author_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            // 删除用户 所有 博客 评论 回复
            UpdateBlogData updateBlogData = new UpdateBlogDataImp();
            while(resultSet.next()){
                int blogId = resultSet.getInt(1);
                updateBlogData.deleteBlog(blogId, userId);
            }

            if(null != connection){
                connection.close();
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
    }

    @Override
    public boolean insertColumn(String columnName, String columnIntroduce, int ownerId) {
        boolean flag = false;
        try{
            Connection connection = Utils.getConnection();;

            if(!new GetUserDataImp().columnIsExist(columnName, ownerId)){
                // 存储新的分类专栏
                String sql = "insert into mycolumns (column_name,introduce,ownerId) values(?,?,?)";
                Tool.update(columnName, columnIntroduce, ownerId, sql, connection);
                // 更新用户专栏总数
                sql = "update user_inf set column_num=column_num+1 where id=?";
                Tool.update(ownerId, sql, connection);
                flag = true;
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
    public boolean updateColumn(int columnId, String columnName, String introduce, int userId) {
        boolean flag = false;
        try{
            Connection connection = Utils.getConnection();;

            if(!new GetUserDataImp().columnIsExist(columnName, userId, columnId)){
                String sql = "update mycolumns set column_name=?,introduce=? where id=?";
                Tool.update(columnName, introduce, columnId, sql, connection);
                flag = true;
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
    public void deleteColumn(int columnId, int userId) {
        try{
            Connection connection = Utils.getConnection();;

            // 删除指定专栏
            String sql = "delete from mycolumns where id=?";
            Tool.update(columnId, sql, connection);
            // 减少用户专栏总数
            sql = "update user_inf set column_num=column_num-1 where id=?";
            Tool.update(userId, sql, connection);
            // 移除该专栏中所有博客
            sql = "delete from blogs_column where column_id=?";
            Tool.update(columnId, sql, connection);

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeBlogFromColumn(int blogId, int columnId) {
        try{
            Connection connection = Utils.getConnection();;

            // 移除博客
            String sql = "delete from blogs_column where blog_id=? and column_id=?";
            Tool.update(blogId, columnId, sql, connection);

            sql = "insert into blogs_column values(?,?)";
            Tool.update(blogId, 0, sql, connection);

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updatePassword(String email, String newPassword) {
        try{
            Connection connection = Utils.getConnection();;

            // 更新 用户密码
            String sql = "update user_inf set pwd=? where email=?";
            Tool.update(newPassword, email, sql, connection);

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean updatePassword(int userId, String oldPassword, String newPassword) {
        boolean flag = false;
        try{
            Connection connection = Utils.getConnection();;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "select id from user_inf where id=? and pwd=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, oldPassword);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                sql = "update user_inf set pwd=? where id=?";
                Tool.update(newPassword, userId, sql, connection);
                flag = true;
            }

            if(null != resultSet){
                connection.close();
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
}