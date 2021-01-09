package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Tool {
    public static int getPageNum(int num, int size){
        int pages = 0;
        if(num % size == 0) {
            pages += num / size;
        }else{
            pages += num / size + 1;
        }
        return pages;
    }

    public static void update(Object object1, String sql, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, object1);
        preparedStatement.executeUpdate();

        if(null != preparedStatement){
            preparedStatement.close();
        }
    }

    public static void update(Object object1, Object object2, String sql, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, object1);
        preparedStatement.setObject(2, object2);
        preparedStatement.executeUpdate();

        if(null != preparedStatement){
            preparedStatement.close();
        }
    }

    public static void update(Object object1, Object object2,Object object3, String sql, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, object1);
        preparedStatement.setObject(2, object2);
        preparedStatement.setObject(3, object3);
        preparedStatement.executeUpdate();

        if(null != preparedStatement){
            preparedStatement.close();
        }
    }

    public static void update(Object object1, Object object2,Object object3, Object object4, String sql, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, object1);
        preparedStatement.setObject(2, object2);
        preparedStatement.setObject(3, object3);
        preparedStatement.setObject(4, object4);
        preparedStatement.executeUpdate();

        if(null != preparedStatement){
            preparedStatement.close();
        }
    }

    public static void update(Object object1, Object object2,Object object3, Object object4, Object object5, String sql, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, object1);
        preparedStatement.setObject(2, object2);
        preparedStatement.setObject(3, object3);
        preparedStatement.setObject(4, object4);
        preparedStatement.setObject(5, object5);
        preparedStatement.executeUpdate();

        if(null != preparedStatement){
            preparedStatement.close();
        }
    }

    public static void update(Object object1, Object object2,Object object3, Object object4, Object object5, Object object6, String sql, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, object1);
        preparedStatement.setObject(2, object2);
        preparedStatement.setObject(3, object3);
        preparedStatement.setObject(4, object4);
        preparedStatement.setObject(5, object5);
        preparedStatement.setObject(6, object6);
        preparedStatement.executeUpdate();

        if(null != preparedStatement){
            preparedStatement.close();
        }
    }
}
