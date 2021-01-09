package tools.mysql.ppy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tools.qq.ppy.BytesToPath;
import user.qq.ppy.ChatGroup;
import user.qq.ppy.User;

public class UserSelectOption {
	public User getUser(String userAccount) {
		String sql = "select account, name, email, sex, birthday, blood, signature, head, status, common from user_information where account=?";
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, userAccount);
			rs = ps.executeQuery();
			user = new User();
			user.setAcn(userAccount);
			
			while(rs.next()) {
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setSex(rs.getString(4));
				user.setBirthday(rs.getString(5));
				user.setBlood(rs.getString(6));
				String sig = rs.getString(7);
				if(null == sig){
					user.setText("");
				}else{
					user.setText(sig);
				}
				user.setHead(BytesToPath.getBytes(rs.getString(8)));
				user.setStatus(rs.getInt(9));
				user.setChangyongyu(rs.getString(10));
			}
			
			if(null == user.getName() || user.getName().length() == 0) {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
		
		MysqlClose.close(rs, ps, conn);
		return user;
	}
	
	public User[] getUsers(String userAccount) {
		String sql = "select user_acn, friend_acn from friendship where user_acn=?";
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User[] users = new User[1024];
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, userAccount);
			rs = ps.executeQuery();
			int i = 0;
			
			while(rs.next()) {
				String friendACN = rs.getString(2);
				users[i++] = getUser(friendACN);
			}
			
			if(null == users[0]) {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
		
		MysqlClose.close(rs, ps, conn);
		return users;
	}
	
	public ChatGroup getChatGroup(String groupAccount) {
		String sql = "select group_acn, group_owner, group_name, menber_num , group_head, group_sig from group_chat where group_acn=?";
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ChatGroup group = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, groupAccount);
			rs = ps.executeQuery();
			group = new ChatGroup();
			group.setAcn(groupAccount);
			
			while(rs.next()) {
				group.setGroup_owner(rs.getString(2));
				group.setName(rs.getString(3));
				group.setMember_num(rs.getInt(4));
				group.setHead(BytesToPath.getBytes(rs.getString(5)));
				group.setText(rs.getString(6));
				
				User[] members = null;
				members = getMembers(groupAccount);
				if(null != members && null != members[0]) {
					group.setMembers(members);
				}
			}
			
			sql = "select group_acn, your_standing, your_acn from group_chat where group_acn=? and your_standing=?";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, groupAccount);
			ps.setInt(2, 1);
			rs = ps.executeQuery();
			String adm = "1";
			
			while(rs.next()) {
				adm += "_" + rs.getString(3);
			}
			group.setAdm(adm);
			
			if(null == group.getName() || group.getName().length() == 0) {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
		
		MysqlClose.close(rs, ps, conn);
		return group;
	}
	
	public ChatGroup[] getGroups(String userAccount) {
		String sql = "select your_acn, group_acn from group_chat where your_acn=?";
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ChatGroup[] groups = new ChatGroup[1024];
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, userAccount);
			rs = ps.executeQuery();
			int i = 0;
			
			while(rs.next()) {
				String GroupACN = rs.getString(2);
				groups[i] = getChatGroup(GroupACN);
				i++;
			}
			
			if(null == groups[0]) {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
		
		MysqlClose.close(rs, ps, conn);
		return groups;
	}
	
	public User[] getMembers(String groupAccount) {
		String sql = "select group_acn, your_acn, your_standing, group_name from group_chat where group_acn=?";
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		User[] users = new User[1024];
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, groupAccount);
			rs = ps.executeQuery();
			int i = 0;
			
			while(rs.next()) {
				String memberACN = rs.getString(2);
				users[i] = getUser(memberACN);
				users[i].setRank(rs.getInt(3));
				i++;
			}
			
			if(null == users[0]) {
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
		
		MysqlClose.close(rs, ps, conn);
		return users;
	}
	
	public void saveFriend(String theAcn, User friend) {
		String sql = "insert into friendship (user_acn, friend_acn) values(?,?)";
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, theAcn);
			ps.setObject(2, friend.getAcn());
			ps.executeUpdate();
			
			ps = conn.prepareStatement(sql);
			ps.setObject(1, friend.getAcn());
			ps.setObject(2, theAcn);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(ps, conn);
	}
	
	public void saveAddGroup(User u, ChatGroup group) {
		String sql = "insert into group_chat (group_owner, group_name, group_acn, menber_num, your_standing, your_acn, group_head, group_sig) values(?,?,?,?,?,?,?,?)";
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, group.getGroup_owner());
			ps.setObject(2, group.getName());
			ps.setObject(3, group.getAcn());
			ps.setObject(4, group.getMember_num() + 1);
			ps.setObject(5, 0);
			ps.setObject(6, u.getAcn());
			ps.setObject(7, BytesToPath.getPath(group.getHead(), group.getAcn()));
			ps.setObject(8, group.getText());
			ps.executeUpdate();
			
			sql = "update group_chat set menber_num=? where group_acn=?";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, group.getMember_num() + 1);
			ps.setObject(2, group.getAcn());
			ps.executeUpdate();
			
			String sender_group;
			if(group.getAcn().compareTo(u.getAcn()) < 0) {
				sender_group = group.getAcn() + u.getAcn();
			}
			else {
				sender_group = u.getAcn() + group.getAcn();
			}
			sql = "insert into groupchat (sender, group_ACN, sender_group, talks, in_rank) values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, u.getAcn());
			ps.setObject(2, group.getAcn());
			ps.setObject(3, sender_group);
			ps.setObject(4, "欢迎" + u.getName() + "加入本群");
			ps.setObject(5, 1);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(ps, conn);
	}
	
	public void saveCreatGroup(User u, ChatGroup group) {
		String sql = "insert into group_chat (group_owner, group_name, group_acn, menber_num, your_standing, your_acn, group_head, group_sig) values(?,?,?,?,?,?,?,?)";
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		
		try {
			System.out.println(group.getAcn());
			ps = conn.prepareStatement(sql);
			ps.setObject(1, group.getGroup_owner());
			ps.setObject(2, group.getName());
			ps.setObject(3, group.getAcn());
			ps.setObject(4, group.getMember_num());
			ps.setObject(5, 2);
			ps.setObject(6, u.getAcn());
			ps.setObject(7, BytesToPath.getPath(group.getHead(), group.getAcn()));
			ps.setObject(8, group.getText());
			ps.executeUpdate();
			
			String sender_group;
			if(group.getAcn().compareTo(u.getAcn()) < 0) {
				sender_group = group.getAcn() + u.getAcn();
			}
			else {
				sender_group = u.getAcn() + group.getAcn();
			}
			sql = "insert into groupchat (sender, group_ACN, sender_group, talks, in_rank) values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, u.getAcn());
			ps.setObject(2, group.getAcn());
			ps.setObject(3, sender_group);
			ps.setObject(4, "" + u.getName() + "创建本群");
			ps.setObject(5, 1);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(ps, conn);
	}
}
