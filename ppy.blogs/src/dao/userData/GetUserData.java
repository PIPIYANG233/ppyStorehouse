package dao.userData;

import domain.Column;
import domain.User;

import java.sql.Connection;
import java.util.List;

public interface GetUserData {
    // 获取 登录用户
    User check(User loginUser);
    // 获取 用户
    User getUser(int userId, Connection connection);
    // 获取 粉丝 关注 收藏 博客 的数量
    int getNum(Object object, String sql, int parametersNum);
    // 获取 粉丝 关注
    List<User> getUsers(int userId, boolean isFan, String sql);
    // 判断 是否关注粉丝
    void checkFollow(List<User> fans, int userId);
    // 获取 用户分类专栏对象
    List<Column> getColumns(int start, int userId);
    // 判断 标签是否存在
    boolean columnIsExist(String columnName, int userId);
    // 判断 标签是否存在
    boolean columnIsExist(String columnName, int userId, int columnId);
    // 获取 分类专栏名称
    List<String> getColumnNames(int userId);
    // 获取 博客发布年份集合
    List<Integer> getYears(int userId);
    // 判断 账号与邮箱是否匹配
    boolean isMatch(String email, String sql);
}