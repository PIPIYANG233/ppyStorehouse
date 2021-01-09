package tools.mysql.ppy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.qq.ppy.BytesToPath;
import tools.qq.ppy.MakeCaptcha;
import user.qq.ppy.TheContent;

public class ChatSaveOption {
	
	public void saveChatInformation(TheContent tc) {
		String sql = "insert into chat (sender, receiver, time, talks, send_receive, sender_name, pic, file_path, file_name, voice) values(?,?,?,?,?,?,?,?,?,?)";
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, tc.getSenderACN());
			ps.setObject(2, tc.getReceiverACN());
			ps.setObject(3, tc.getTime());
			ps.setObject(4, tc.getTalks());
			
			String send_receive;
			if(tc.getSenderACN().compareTo(tc.getReceiverACN()) < 0) {
				send_receive = tc.getSenderACN() + tc.getReceiverACN();
			}
			else {
				send_receive = tc.getReceiverACN() + tc.getSenderACN();
			}
			ps.setObject(5, send_receive);
			ps.setObject(6, tc.getSendName());
			
			String path = "";
			if(null != tc.getPic()) {
				path += BytesToPath.getPath(tc.getPic(), MakeCaptcha.makeCapcha());
			}
			ps.setObject(7, path);
			
			String filePath = "";
			String fileName = "";
			String yy = "";
			if(null != tc.getFile()) {
				filePath += BytesToPath.getPath(tc.getFile(), MakeCaptcha.makeCapcha());
				fileName += tc.getFileName();
			}
			if(null != tc.getYy()) {
				yy += tc.getYy();
			}
			
			ps.setObject(8, filePath);
			ps.setObject(9, fileName);
			ps.setObject(10, yy);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(ps, conn);
	}
	
	public List<TheContent> selectChatInformation(String send_receive) {
		String sql = "select send_receive, sender, receiver, time, talks, pic, sender_name, file_path, file_name, voice from chat where send_receive=?";
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TheContent> tcs = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, send_receive);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				TheContent tc = new TheContent();
				tc.setSenderACN(rs.getString(2));
				tc.setReceiverACN(rs.getString(3));
				tc.setSendName(rs.getString(7));
				tc.setTime(rs.getString(4));
				tc.setTalks(rs.getString(5));
				
				String path = "";
				path += rs.getString(6);
				if(null != path && path.length() != 0) {
					tc.setPic(BytesToPath.getBytes(path));
				}
				
				String filePath = "";
				String fileName = "";
				String yy = "";
				filePath += rs.getString(8);
				fileName += rs.getString(9);
				yy += rs.getString(10);
				if(null != filePath && filePath.length() != 0 && null != fileName && fileName.length() != 0) {
					tc.setFile(BytesToPath.getBytes(filePath));
					tc.setFileName(fileName);
				}
				if(null != yy && !yy.equals("")) {
					tc.setYy(yy);
				}
				tcs.add(tc);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(rs, ps, conn);
		
		if(null != tcs && tcs.size() != 0) {
			return tcs;
		}
		return null;
	}
	
	public void saveGroupChatInformation(TheContent tc) {
		String sql = "insert into groupchat (sender, group_ACN, time, talks, sender_group, sender_name, pic, file_path, file_name, in_rank, voice) values(?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, tc.getSenderACN());
			ps.setObject(2, tc.getReceiverACN());
			ps.setObject(3, tc.getTime());
			ps.setObject(4, tc.getTalks());
			
			ps.setObject(5, "");
			ps.setObject(6, tc.getSendName());
			
			String picPath = "";
			if(null != tc.getPic()) {
				picPath += BytesToPath.getPath(tc.getPic(), MakeCaptcha.makeCapcha());
			}
			ps.setObject(7, picPath);
			
			String filePath = "";
			String fileName = "";
			String yy = "";
			if(null != tc.getFile()) {
				filePath += BytesToPath.getPath(tc.getFile(), MakeCaptcha.makeCapcha());
				fileName += tc.getFileName();
			}
			if(null != tc.getYy()) {
				yy += tc.getYy();
			}
			
			ps.setObject(8, filePath);
			ps.setObject(9, fileName);
			ps.setObject(10, 0);
			ps.setObject(11, yy);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(ps, conn);
	}
	
	public List<TheContent> selectGroupChatInformation(String group_ACN, String member_ACN) {
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TheContent> tcs = new ArrayList<>();
		
		try {
			
			int num = 0;
			String sender_group;
			if(group_ACN.compareTo(member_ACN) < 0) {
				sender_group = group_ACN + member_ACN;
			}
			else {
				sender_group = member_ACN + group_ACN;
			}
			String sql = "select sender_group, in_num from groupchat where sender_group=?";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, sender_group);
			rs = ps.executeQuery();
			while(rs.next()) {
				num = rs.getInt(2);
			}
			
			if(num > 0) {
				sql = "select group_ACN, in_num, sender, group_ACN, time, talks, pic, sender_name, file_path, file_name, in_rank, voice from groupchat where group_ACN=? and in_num>=?";
				ps = conn.prepareStatement(sql);
				ps.setObject(1, group_ACN);
				ps.setObject(2, num);
				
				rs = ps.executeQuery();
				while(rs.next()) {
					TheContent tc = new TheContent();
					tc.setSenderACN(rs.getString(3));
					tc.setReceiverACN(rs.getString(4));
					tc.setSendName(rs.getString(8));
					tc.setTime(rs.getString(5));
					tc.setTalks(rs.getString(6));
					tc.setSender(new UserSelectOption().getUser(tc.getSenderACN()));

					String picPath = "";
					picPath += rs.getString(7);
					if(null != picPath && picPath.length() != 0) {
						tc.setPic(BytesToPath.getBytes(picPath));
					}
					
					String filePath = "";
					String fileName = "";
					String yy = "";
					filePath += rs.getString(9);
					fileName += rs.getString(10);
					yy += rs.getString(12);
					if(null != filePath && filePath.length() != 0 && null != fileName && fileName.length() != 0) {
						tc.setFile(BytesToPath.getBytes(filePath));
						tc.setFileName(fileName);
					}
					tc.setRank(rs.getInt(11));
					if(null != yy && !yy.equals("")) {
						tc.setYy(yy);
					}
					
					tcs.add(tc);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(rs, ps, conn);
		
		if(null != tcs && tcs.size() != 0) {
			return tcs;
		}
		return null;
	}
}
