package tools.mysql.ppy;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tools.qq.ppy.BytesToPath;
import tools.qq.ppy.getUserInformation;
import user.qq.ppy.Massage;
import user.qq.ppy.User;

public class UserSaveOption {
	private String acn;
	private String pwd;
	private String toEmailAddress;
	private String name;
	private String sex;
	private String birthday;
	private String blood;
	private String sig;
	
	public UserSaveOption(String acn, String pwd, String toEmailAddress, String name, String sex, String birthday, String blood, String text) {
		this.acn = acn;
		this.pwd = pwd;
		this.toEmailAddress = toEmailAddress;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.blood = blood;
		this.sig = text;
	}

	public UserSaveOption(String acn, String pwd, String toEmailAddress, String name) {
		this.acn = acn;
		this.pwd = pwd;
		this.toEmailAddress = toEmailAddress;
		this.name = name;
	}
	
	public UserSaveOption(String acn, String pwd) {
		this.acn = acn;
		this.pwd = pwd;
	}
	
	public UserSaveOption(String acn) {
		this.acn = acn;
	}
	
	public UserSaveOption(String acn, String pwd, String toEmailAddress) {
		this.acn = acn;
		this.pwd = pwd;
		this.toEmailAddress = toEmailAddress;
	}
	
	public UserSaveOption() {

	}

	public void save() {
		
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		
		String sql = "insert into user_information (account, password, email, name) values(?,?,?,?)";
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setObject(1, this.acn);
			ps.setObject(2, this.pwd);
			ps.setObject(3, this.toEmailAddress);
			ps.setObject(4, this.name);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(ps, conn);
	}
	
	public void updatePWD() {
		
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		
		String sql = "update user_information set password=? where account=?";
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setObject(1, this.pwd);
			ps.setObject(2, this.acn);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(ps, conn);
	}
	
	public boolean changeStatus(int mark) {
		
		boolean flag = true;
		int status = 0;
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql1 = "select account, status from user_information where account =?";
		String sql2 = "update user_information set status=? where account=?";
		try {
			
			ps = conn.prepareStatement(sql1);
			ps.setObject(1, this.acn);
			rs = ps.executeQuery();
			while(rs.next()) {
				status = rs.getInt(2);
			}
			
			if(mark == status) {
				flag = false;
			}
			
			if(flag) {
				ps = conn.prepareStatement(sql2);
				ps.setObject(1, -status);
				ps.setObject(2, this.acn);
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(rs, ps, conn);
		return flag;
	}
	
	public boolean findEmali() {
		
		boolean flag = true;
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql1 = "select account, email from user_information where account =?";
		try {
			String email = "";
			ps = conn.prepareStatement(sql1);
			ps.setObject(1, this.acn);
			rs = ps.executeQuery();
			while(rs.next()) {
				email = rs.getString(2);
			}
			
			if(null == email || email.length() == 0 || !email.equals(this.toEmailAddress)) {
				flag = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(rs, ps, conn);
		return flag;
	}

	public boolean findRepeat() {
		boolean flag = true;
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql1 = "select email, account from user_information where email =?";
		try {
			String mysql_acn = "";
			ps = conn.prepareStatement(sql1);
			ps.setObject(1, this.toEmailAddress);
			rs = ps.executeQuery();
			while(rs.next()) {
				mysql_acn = rs.getString(2);
			}
			
			if(null != mysql_acn && mysql_acn.length() != 0) {
				System.out.println(this.toEmailAddress + " 已被注册: " + mysql_acn);
			}
			
			if(null != mysql_acn && mysql_acn.length() != 0) {
				flag = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(rs, ps, conn);
		return flag;
	}
	
	public Massage findPWD() throws IOException {
		Massage m = new Massage();
		m.setCanEnter(1);
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
			String sql1 = "select account, password from user_information where account =?";
			try {
				String mysql_pwd = "";
				ps = conn.prepareStatement(sql1);
				ps.setObject(1, this.acn);
				rs = ps.executeQuery();
				while(rs.next()) {
					mysql_pwd = rs.getString(2);
				}
				
				if(!mysql_pwd.equals(this.pwd)) {
					m.setCanEnter(2); // 密码不正确
				}
				else {
					if(!changeStatus(1)) {
						m.setCanEnter(3); // 重复登录
					}
					else {
						User user = new User();
						getUserInformation guif = new getUserInformation(this.acn);
						user.setAcn(this.acn);
						user.setPwd(this.pwd);
						user.setEmail(guif.getToEmailAddress());
						user.setName(guif.getName());
						user.setSex(guif.getSex());
						user.setBirthday(guif.getBirthday());
						user.setBlood(guif.getBlood());
						user.setText(guif.getSig());
						user.setStatus(guif.getStatus());
						user.setBack(BytesToPath.getBytes(guif.getBack()));
						user.setHead(BytesToPath.getBytes(guif.getHead()));
						user.setChangyongyu(guif.getCyy());
						m.setU(user);
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		MysqlClose.close(rs, ps, conn);
		return m;
	}
	
	public void updateChangyongyu(User u) {
		Connection conn = MysqlConnect.conect();
		PreparedStatement ps = null;
		
		String sql = "update user_information set common=? where account=?";
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setObject(1, u.getChangyongyu());
			ps.setObject(2, u.getAcn());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(ps, conn);
	}
}
