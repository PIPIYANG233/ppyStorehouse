package service.userOption;

import domain.*;

import java.sql.Date;
import java.util.List;

public interface UserOption {
    // 用户登录
    User login(User loginUser);
    // 用户注册
    boolean register(String name, String pwd, String email);
    // 更新用户头像
    void updateHead(int userId, String head_img);
    // 更新用户 个人信息
    void updateUserInf(String user_name, String true_name, Date birthday, int sex, String introduce, int userId);
    // 更新用户 粉丝
    void updateFan(int userId, int followId, boolean flag);
    // 更新用户 是否为管理员
    void updateManager(int userId, boolean flag);
    // 删除用户
    void deleteUser(int userId);
    // 获取 用户
    User getUser(int userId);
    // 判断 是否关注粉丝
    void checkFollow(List<User> fans, int userId);
    // 获得粉丝分页
    FanPage getFanPage(int now, int start, int pages, int num, int userId);
    // 获得关注分页
    FollowPage getFollowPage(int now, int start, int pages, int num, int userId);
    // 获取 用户分类专栏分页
    ColumnPage getColumnPage(int now, int start, int pages, int num, int userId);
    // 获取 分类专栏博客分页
    ColumnManagerPage getColumnManagerPage(int now, int start, int pages, int num, int columnId);
    // 获取 分类专栏博客分页
    ColumnManagerPage getColumnManagerPage(ColumnManagerPage columnManagerPage, int columnId);
    // 新建 分类专栏
    Column insertColumn(String columnName, String introduce, int ownerId);
    // 编辑 分类专栏
    boolean updateColumn(int columnId, String columnName, String introduce, int userId);
    // 删除 分类专栏
    void deleteColumn(int columnId, int userId);
    // 获取 用户所有分类专栏的名称
    List<String> getColumnNames(int userId);
    // 移除 分类中博客
    void removeBlogFromColumn(int blogId, int columnId);
    // 判断 账号与邮箱是否匹配
    boolean isMatch(String email);
    // 更新 用户密码
    void updatePassword(String email, String newPassword);
    // 更新 用户密码
    boolean updatePassword(int userId, String oldPassword, String newPassword);
    // 获取 粉丝 关注 收藏 博客 分类专栏 的数量
    int getNum(int option, Object object, int parametersNum);
    // 获取 筛选博客数量
    int getSelectedNum(boolean isMyself, BlogPage blogPage, int userId);
    // 获得 总页数
    int getPages(int num, int size);
}