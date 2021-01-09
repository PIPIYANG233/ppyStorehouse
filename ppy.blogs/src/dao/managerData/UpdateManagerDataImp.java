package dao.managerData;

import dao.Tool;
import service.tools.Utils;

import java.sql.Connection;

public class UpdateManagerDataImp implements UpdateManagerData{
    @Override
    public void updateLabel(String newLabel, int labelId) {
        try{
            Connection connection = Utils.getConnection();

            String sql = "update home_labels set label_name=? where label_id=?";
            Tool.update(newLabel, labelId, sql, connection);

            if(null != connection){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
