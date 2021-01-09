package service.userOption;

import dao.page.GetPageDataImp;
import dao.userData.GetUserDataImp;
import dao.userData.UpdateUserDataImp;
import domain.*;
import service.tools.Utils;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class UserOptionImp implements UserOption{

    @Override
    public User login(User loginUser) {
        return new GetUserDataImp().check(loginUser);
    }

    @Override
    public boolean register(String name, String pwd, String email) {
        return new UpdateUserDataImp().insert(name, pwd, email);
    }

    @Override
    public void updateHead(int userId, String head_img) {
        new UpdateUserDataImp().updateHead(userId, head_img);
    }

    @Override
    public void updateUserInf(String user_name, String true_name, Date birthday, int sex, String introduce, int userId) {
        new UpdateUserDataImp().updateUserInf(user_name, true_name, birthday, sex, introduce, userId);
    }

    @Override
    public void updateFan(int userId, int followId, boolean flag) {
        if(flag){
            String[] sqls = {
                    "insert into user_fans_follow(fan_id, follow_id)values(?,?)",
                    "update user_inf set follow_num=follow_num+1 where id=?",
                    "update user_inf set fans_num=fans_num+1 where id=?"
            };
            new UpdateUserDataImp().updateFan(userId, followId, sqls);
        }else{
            String[] sqls = {
                    "delete from user_fans_follow where fan_id=? and follow_id=?",
                    "update user_inf set follow_num=follow_num-1 where id=?",
                    "update user_inf set fans_num=fans_num-1 where id=?"
            };
            new UpdateUserDataImp().updateFan(userId, followId, sqls);
        }
    }

    @Override
    public void updateManager(int userId, boolean flag) {
        String sql = "";
        if (flag){ // 设为管理员
            sql += "update user_inf set rank_=1 where id=?";
        }else{ // 取消管理员
            sql += "update user_inf set rank_=0 where id=?";
        }
        new UpdateUserDataImp().updateManager(userId, sql);
    }

    @Override
    public void deleteUser(int userId) {
        new UpdateUserDataImp().deleteUser(userId);
    }

    @Override
    public User getUser(int userId) {
        Connection connection = null;
        User user = null;
        try {
            connection = Utils.getConnection();
            user = new GetUserDataImp().getUser(userId, connection);

            if(null != connection){
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void checkFollow(List<User> fans, int userId) {
        new GetUserDataImp().checkFollow(fans, userId);
    }

    @Override
    public FanPage getFanPage(int now, int start, int pages, int num, int userId) {
        String sql = "SELECT " +
                "uff.fan_id,ui.user_name,ui.true_name,ui.email,ui.birthday,ui.sex,ui.head_img,ui.fans_num,ui.follow_num,ui.blogs_num,ui.scan_num,ui.up_num,ui.collected_num,ui.collect_num,ui.introduce " +
                "from " +
                "user_fans_follow as uff " +
                "INNER JOIN " +
                "user_inf as ui " +
                "ON " +
                "uff.fan_id = ui.id " +
                "WHERE " +
                "uff.follow_id=? " +
                "limit " +
                start + "," + 4;
        return new GetPageDataImp().getFanPage(now, start, pages, num, userId, sql);
    }

    @Override
    public FollowPage getFollowPage(int now, int start, int pages, int num, int userId) {
        String sql = "SELECT " +
                "uff.follow_id,ui.user_name,ui.true_name,ui.email,ui.birthday,ui.sex,ui.head_img,ui.fans_num,ui.follow_num,ui.blogs_num,ui.scan_num,ui.up_num,ui.collected_num,ui.collect_num,ui.introduce " +
                "from " +
                "user_fans_follow as uff " +
                "INNER JOIN " +
                "user_inf as ui " +
                "ON " +
                "uff.follow_id = ui.id " +
                "WHERE " +
                "uff.fan_id=? " +
                "limit " +
                start + "," + 4;
        return new GetPageDataImp().getFollowPage(now, start, pages, num, userId, sql);
    }

    @Override
    public ColumnPage getColumnPage(int now, int start, int pages, int num, int userId) {
        return new GetPageDataImp().getColumnPage(now, start, pages, num, userId);
    }

    @Override
    public ColumnManagerPage getColumnManagerPage(int now, int start, int pages, int num, int columnId) {
        String sql = "select id,blog_author_id,blog_title,blog_scan_num,blog_up_num,blog_pub_date,status,blog_collect_num " +
                "from user_blogs where id in (select blog_id from blogs_column where column_id=?) limit " + start + ",3";
        return new GetPageDataImp().getColumnManagerPage(now, start, pages, num, columnId, sql);
    }

    @Override
    public ColumnManagerPage getColumnManagerPage(ColumnManagerPage columnManagerPage, int columnId) {
        String sql = "select id,blog_author_id,blog_title,blog_scan_num,blog_up_num,blog_pub_date,status,blog_collect_num " +
                "from user_blogs where id in (select blog_id from blogs_column where column_id=?) limit " + columnManagerPage.getStart() + ",3";
        return new GetPageDataImp().getColumnManagerPage(columnManagerPage, columnId, sql);
    }

    @Override
    public Column insertColumn(String columnName, String introduce, int ownerId) {
        if(!new UpdateUserDataImp().insertColumn(columnName, introduce, ownerId)){
            return null;
        }else{
            Column column = new Column();
            column.setColumn_name(columnName);
            column.setIntroduce(introduce);
            column.setColumnNum(0);
            column.setOwnerId(ownerId);
            return column;
        }
    }

    @Override
    public boolean updateColumn(int columnId, String columnName, String introduce, int userId) {
        return new UpdateUserDataImp().updateColumn(columnId, columnName, introduce, userId);
    }

    @Override
    public void deleteColumn(int columnId, int userId) {
        new UpdateUserDataImp().deleteColumn(columnId, userId);
    }

    @Override
    public List<String> getColumnNames(int userId) {
        return new GetUserDataImp().getColumnNames(userId);
    }

    @Override
    public void removeBlogFromColumn(int blogId, int columnId) {
        new UpdateUserDataImp().removeBlogFromColumn(blogId, columnId);
    }

    @Override
    public boolean isMatch(String email) {
        String sql = "select id from user_inf where email=?";
        return new GetUserDataImp().isMatch(email, sql);
    }

    @Override
    public void updatePassword(String email, String newPassword) {
        new UpdateUserDataImp().updatePassword(email, newPassword);
    }

    @Override
    public boolean updatePassword(int userId, String oldPassword, String newPassword) {
        return new UpdateUserDataImp().updatePassword(userId, oldPassword, newPassword);
    }

    @Override
    public int getNum(int option, Object object, int parametersNum) {
        switch (option){
            case 1 : { // 获取自己博客数量
                String sql = "select count(id) from user_blogs where blog_author_id=?";
                return new GetUserDataImp().getNum(object, sql, parametersNum);
            }
            case 2 : { // 获取其他用户博客数量
                String sql = "select count(id) from user_blogs where blog_author_id=? and status=4";
                return new GetUserDataImp().getNum(object, sql, parametersNum);
            }
            case 3 : { // 获取用户收藏数量
                String sql = "select count(blog_id) from blogs_collect where user_id=?";
                return new GetUserDataImp().getNum(object, sql, parametersNum);
            }
            case 4 :{ // 获取用户粉丝数量
                String sql = "select count(fan_id) from user_fans_follow where follow_id=?";
                return new GetUserDataImp().getNum(object, sql, parametersNum);
            }
            case 5 :{ // 获取用户关注数量
                String sql = "select count(follow_id) from user_fans_follow where fan_id=?";
                return new GetUserDataImp().getNum(object, sql, parametersNum);
            }
            case 6 : { // 获取用户专栏
                String sql = "select count(column_name) from myColumns where ownerId=?";
                return new GetUserDataImp().getNum(object, sql, parametersNum);
            }
            case 7 :{ // 从分类专栏获取管理专栏
                String sql = "select count(blog_id) from blogs_column where column_id=?";
                return new GetUserDataImp().getNum(object, sql, parametersNum);
            }
        }
        return 0;
    }

    @Override
    public int getSelectedNum(boolean isMyself, BlogPage blogPage, int userId) {
        // 判断参数情况
        String mainContent = blogPage.getMainContent();
        int year = blogPage.getYear();
        String theYear = "";
        String theMonth = "";

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

        String theType = "";
        int type = blogPage.getType();
        if(type != 0){
            theType += type;
        }
        // 专栏
        String column = blogPage.getColumn();
        if(column.equals("")){
            column = "(select bc.blog_id from blogs_column as bc left join " +
                    "mycolumns as mc on bc.column_id = mc.id) ";
        }else{
            column = "(select bc.blog_id from blogs_column as bc inner join " +
                    "mycolumns as mc on bc.column_id = mc.id and mc.column_name like '%" + column + "%') ";
        }
        String sql="";
        if(isMyself){
            sql = "select count(ub.id) " +
                    "from (select distinct ub.id from user_blogs as ub inner join blogs_type as bt on ub.id = bt.blog_id inner join blogs_label as bl on " +
                    "ub.id = bl.blog_id inner join " + column + "as bmc on ub.id = bmc.blog_id where ub.blog_author_id=? "+
                    "and bt.type like '%"  + theType + "%' and ( bl.label_name like '%" + mainContent + "%' or " +
                    "ub.blog_title like '%" + mainContent + "%' ) and ub.blog_pub_date like '" + theYear +  "%-" + theMonth + "%') as ub";
        }else{
            sql = "select count(ub.id) " +
                    "from (select distinct ub.id from user_blogs as ub inner join blogs_type as bt on ub.id = bt.blog_id inner join blogs_label as bl on " +
                    "ub.id = bl.blog_id inner join " + column + "as bmc on ub.id = bmc.blog_id where ub.blog_author_id=? "+
                    "and bt.type like '%"  + theType + "%' and ( bl.label_name like '%" + mainContent + "%' or " +
                    "ub.blog_title like '%" + mainContent + "%' ) and ub.blog_pub_date like '" + theYear +  "%-" + theMonth + "%' and ub.status=4) as ub";
        }
        return new GetUserDataImp().getNum(userId, sql, 1);
    }

    @Override
    public int getPages(int num, int size) {
        return new GetPageDataImp().getPages(num, size);
    }
}