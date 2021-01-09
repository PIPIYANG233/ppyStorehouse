package tools.mysql.ppy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import user.qq.ppy.ChatGroup;
import user.qq.ppy.User;

public class UserDeleteOption {
	
	public void deleteFriend(String userAccount, String friendAccount) {
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		
		String sql = "delete from friendship where user_acn=? and friend_acn=?";
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setObject(1, userAccount);
			ps.setObject(2, friendAccount);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(sql);
			ps.setObject(1, friendAccount);
			ps.setObject(2, userAccount);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(ps, conn);
	}
	
	public void deleteGroup(User user, String groupAccount) {
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		ChatGroup group = new UserSelectOption().getChatGroup(groupAccount);
		
		if(!group.getGroup_owner().equals(user.getAcn())) {
			String sql = "delete from group_chat where your_acn=? and group_acn=?";
			try {
				
				ps = conn.prepareStatement(sql);
				ps.setObject(1, user.getAcn());
				ps.setObject(2, groupAccount);
				ps.executeUpdate();
				
				sql = "update group_chat set menber_num=? where group_acn=?";
				ps = conn.prepareStatement(sql);
				ps.setObject(1, group.getMember_num() - 1);
				ps.setObject(2, group.getAcn());
				ps.executeUpdate();
				
				sql = "insert into groupchat (sender, group_ACN, sender_group, talks, in_rank) values(?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setObject(1, user.getAcn());
				ps.setObject(2, group.getAcn());
				ps.setObject(3, user.getAcn());
				ps.setObject(4, user.getName() + "退出本群");
				ps.setObject(5, 2);
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		else {
			String sql = "delete from group_chat where group_acn=?";
			try {
				
				ps = conn.prepareStatement(sql);
				ps.setObject(1, groupAccount);
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			sql = "delete from groupchat where group_acn=?";
			try {
				
				ps = conn.prepareStatement(sql);
				ps.setObject(1, groupAccount);
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		MysqlClose.close(ps, conn);
	}
	
	public void firedGroup(User user, String groupAccount) {
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		ChatGroup group = new UserSelectOption().getChatGroup(groupAccount);
		
		if(!group.getGroup_owner().equals(user.getAcn())) {
			String sql = "delete from group_chat where your_acn=? and group_acn=?";
			try {
				
				ps = conn.prepareStatement(sql);
				ps.setObject(1, user.getAcn());
				ps.setObject(2, groupAccount);
				ps.executeUpdate();
				
				sql = "update group_chat set menber_num=? where group_acn=?";
				ps = conn.prepareStatement(sql);
				ps.setObject(1, group.getMember_num() - 1);
				ps.setObject(2, group.getAcn());
				ps.executeUpdate();
				
				sql = "insert into groupchat (sender, group_ACN, sender_group, talks, in_rank) values(?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setObject(1, user.getAcn());
				ps.setObject(2, group.getAcn());
				ps.setObject(3, user.getAcn());
				ps.setObject(4, user.getName() + "被踢出本群");
				ps.setObject(5, 2);
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		else {
			String sql = "delete from group_chat where group_acn=?";
			try {
				
				ps = conn.prepareStatement(sql);
				ps.setObject(1, groupAccount);
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			sql = "delete from groupchat where group_acn=?";
			try {
				
				ps = conn.prepareStatement(sql);
				ps.setObject(1, groupAccount);
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		MysqlClose.close(ps, conn);
	}
}
