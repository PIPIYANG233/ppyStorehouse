package dao.userData;

import java.sql.Date;

public interface UpdateUserData {
    // 插入新用户
    boolean insert(String name, String email, String pwd);
    // 更新用户头像
    void updateHead(int userId, String head_img);
    // 更新用户 个人信息
    void updateUserInf(String user_name, String true_name, Date birthday, int sex, String introduce, int userId);
    // 更新用户 粉丝
    void updateFan(int userId, int followId, String[] sqls);
    // 更新用户 是否为管理员
    void updateManager(int userId, String sql);
    // 删除用户
    void deleteUser(int userId);
    // 保存新建分类专栏
    boolean insertColumn(String columnName, String columnIntroduce, int ownerId);
    // 编辑 分类专栏
    boolean updateColumn(int columnId, String columnName, String introduce, int userId);
    // 删除 已有分类专栏
    void deleteColumn(int columnId, int userId);
    // 移除 分类中博客
    void removeBlogFromColumn(int blogId, int columnId);
    // 更新 用户密码
    void updatePassword(String email, String newPassword);
    // 更新 用户密码
    boolean updatePassword(int userId, String oldPassword, String newPassword);
}