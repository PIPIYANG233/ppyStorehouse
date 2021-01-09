package tools.mysql.ppy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import tools.qq.ppy.BytesToPath;
import tools.qq.ppy.MakeCaptcha;
import user.qq.ppy.User;

public class UserUpdateOption {
	private User u;

	public UserUpdateOption(User u) {
		this.u = u;
	}
	
	public void updateSig() {
		
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		
		String sql = "update user_information set signature=? where account=?";
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setObject(1, this.u.getText());
			ps.setObject(2, this.u.getAcn());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(ps, conn);
	}
	
	public void updateHead() {
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		
		String sql = "update user_information set head=? where account=?";
		try {
			
			String num = MakeCaptcha.makeCapcha(); 
			ps = conn.prepareStatement(sql);
			ps.setObject(1, BytesToPath.getPath(this.u.getHead(), num));
			ps.setObject(2, this.u.getAcn());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(ps, conn);
	}

	public void updateBack() {
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		
		String sql = "update user_information set background=? where account=?";
		try {
			
			String num = MakeCaptcha.makeCapcha(); 
			ps = conn.prepareStatement(sql);
			ps.setObject(1, BytesToPath.getPath(this.u.getHead(), num));
			ps.setObject(2, this.u.getAcn());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(ps, conn);
	}
	
	public void updateZiLiao() {
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		
		String sql = "update user_information set name=? , sex=? , birthday=? , blood=? where account=?";
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setObject(1, this.u.getName());
			ps.setObject(2, this.u.getSex());
			ps.setObject(3, this.u.getBirthday());
			ps.setObject(4, this.u.getBlood());
			ps.setObject(5, this.u.getAcn());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(ps, conn);
	}

	public void updateName() {
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		
		String sql = "update user_information set name=? where account=?";
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setObject(1, this.u.getName());
			ps.setObject(2, this.u.getAcn());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(ps, conn);
	}
	
	public void updateGroup() {
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		
		String sql = "update group_chat set group_name=?, group_head=?, group_sig=? where group_acn=?";
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setObject(1, this.u.getName());
			ps.setObject(2, BytesToPath.getPath(this.u.getHead(), this.u.getAcn()));
			ps.setObject(3, this.u.getText());
			ps.setObject(4, this.u.getAcn());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(ps, conn);
	}
	
	public void updateStandingUp(String theGroupAcn) {
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		
		String sql = "update group_chat set your_standing=? where group_acn=? and your_acn=?";
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setObject(1, 1);
			ps.setObject(2, theGroupAcn);
			ps.setObject(3, this.u.getAcn());
			ps.executeUpdate();
			
			sql = "insert into groupchat (sender, group_ACN, talks, in_rank) values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, this.u.getAcn());
			ps.setObject(2, theGroupAcn);
			ps.setObject(3,  this.u.getName() + "被设置为管理员");
			ps.setObject(4, 2);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(ps, conn);
	}
	
	public void updateStandingDown(String theGroupAcn) {
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		
		String sql = "update group_chat set your_standing=? where group_acn=? and your_acn=?";
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setObject(1, 0);
			ps.setObject(2, theGroupAcn);
			ps.setObject(3, this.u.getAcn());
			ps.executeUpdate();
			
			sql = "insert into groupchat (sender, group_ACN, talks, in_rank) values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, this.u.getAcn());
			ps.setObject(2, theGroupAcn);
			ps.setObject(3,  this.u.getName() + "管理员资格已取消");
			ps.setObject(4, 2);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(ps, conn);
	}
}
