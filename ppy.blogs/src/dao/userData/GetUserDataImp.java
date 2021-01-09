package dao.userData;

import domain.Column;
import domain.User;
import service.tools.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GetUserDataImp implements GetUserData {
    @Override
    public User check(User loginUser) {
        try{
            // 判断用户账号密码是否正确
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "select count(id) from user_inf where id=? and pwd=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, loginUser.getId());
            preparedStatement.setString(2, loginUser.getPwd());
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                loginUser = getUser(loginUser.getId(), connection);
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
        return loginUser;
    }

    @Override
    public User getUser(int userId, Connection connection) {
        User user = new User();
        user.setId(userId);
        try{
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "select user_name,true_name,email,birthday,sex,head_img,fans_num,follow_num,blogs_num,scan_num,up_num,collected_num,collect_num,column_num,rank_,introduce from user_inf where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            resultSet = preparedStatement.executeQuery();
            if(null != resultSet){
                while(resultSet.next()){
                    user.setUser_name(resultSet.getString(1));
                    user.setTrue_name(resultSet.getString(2));
                    user.setEmail(resultSet.getString(3));
                    user.setBirthday(resultSet.getDate(4));
                    user.setSex(resultSet.getInt(5));
                    user.setHead_img(resultSet.getString(6));
                    user.setFans_num(resultSet.getInt(7));
                    user.setFollow_num(resultSet.getInt(8));
                    user.setBlogs_num(resultSet.getInt(9));
                    user.setScan_num(resultSet.getInt(10));
                    user.setUp_num(resultSet.getInt(11));
                    user.setCollected_num(resultSet.getInt(12));
                    user.setCollect_num(resultSet.getInt(13));
                    user.setColumn_num(resultSet.getInt(14));
                    user.setRank(resultSet.getInt(15));
                    user.setIntroduce(resultSet.getString(16));
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
        return user;
    }

    @Override
    public int getNum(Object object, String sql, int parametersNum) {
        int num = 0;
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            preparedStatement = connection.prepareStatement(sql);
            switch(parametersNum){
                // 一个参数
                case 1 : preparedStatement.setObject(1, object);break;
                case 2 : {// 两个参数
                    preparedStatement.setObject(1, object);
                    preparedStatement.setObject(2, object);
                    break;
                }
            }
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                num = resultSet.getInt(1);
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
        return num;
    }

    @Override
        public List<User> getUsers(int userId, boolean isFan, String sql) {
        List<User> users = new ArrayList<>();
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = getUser(resultSet.getInt(1), connection);
                users.add(user);
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
        if(isFan){
            checkFollow(users, userId);
        }
        return users;
    }

    @Override
    public void checkFollow(List<User> fans, int userId) {
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "";
            sql = "select follow_id from user_fans_follow where fan_id=? and follow_id=?";
            preparedStatement = connection.prepareStatement(sql);
            for(User fan: fans){
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, fan.getId());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    fan.setFollow(true);
                }else{
                    fan.setFollow(false);
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
    }

    // 获取分类专栏对象
    @Override
    public List<Column> getColumns(int start, int userId) {
        List<Column> columns = new ArrayList<>();
        GetUserData getUserData = new GetUserDataImp();
        try{
            Connection  connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "select column_name,introduce,id from mycolumns where ownerId=? order by id desc " +
                    "limit " + start + ",4";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Column column = new Column();
                int columnId = resultSet.getInt(3);
                column.setColumn_name(resultSet.getString(1));
                column.setIntroduce(resultSet.getString(2));
                column.setColumnNum(getUserData.getNum(columnId, "select count(blog_id) from blogs_column where column_id=?", 1));
                column.setOwnerId(userId);
                column.setId(columnId);
                columns.add(column);
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
        return columns;
    }

    @Override
    public boolean columnIsExist(String columnName, int userId) {
        return columnIsExist(columnName, userId, 0);
    }

    @Override
    public boolean columnIsExist(String columnName, int userId, int columnId) {
        boolean flag = false;
        try{
            Connection  connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "";
            if(columnId != 0){
                sql += "select id from mycolumns where column_name=? and ownerId=? and id!=" + columnId;
            }else{
                sql += "select id from mycolumns where column_name=? and ownerId=?";
            }

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, columnName);
            preparedStatement.setInt(2, userId);
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
    public List<String> getColumnNames(int userId) {
        List<String> columnNames = new ArrayList<>();
        try{
            Connection  connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "select column_name from mycolumns where ownerId=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                columnNames.add(resultSet.getString(1));
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
        return columnNames;
    }

    @Override
    public List<Integer> getYears(int userId) {
        List<Integer> years = new ArrayList<>();
        try{
            Connection  connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "select distinct YEAR(blog_pub_date) as year from user_blogs where blog_author_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                years.add(resultSet.getInt(1));
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
        return years;
    }

    @Override
    public boolean isMatch(String email, String sql) {
        boolean flag = false;
        try{
            Connection  connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
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
}