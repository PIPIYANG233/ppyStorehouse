package service.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GetHeadPath {
    public static String getHeadPath(int id){
        String path = "";
        try{
            Connection connection = Utils.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String sql = "select head_img from user_inf where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                path += resultSet.getString(1);
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
        return path;
    }
}
