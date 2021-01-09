package tools.qq.ppy;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tools.mysql.ppy.MysqlClose;
import tools.mysql.ppy.MysqlConnect;

public class getUserInformation {
	private String acn;
	private Connection conn = MysqlConnect.conect();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String pwd;
	private String toEmailAddress;
	private String name;
	private String sex;
	private String birthday;
	private String blood;
	private String sig;
	private int status;
	private String back;
	private String head; 
	private String cyy;
	
	public String getAcn() {
		return acn;
	}

	public String getPwd() {
		return pwd;
	}

	public String getToEmailAddress() {
		return toEmailAddress;
	}

	public String getName() {
		return name;
	}

	public String getSex() {
		return sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getBlood() {
		return blood;
	}

	public String getSig() {
		return sig;
	}

	public int getStatus() {
		return status;
	}

	public String getBack() {
		return back;
	}

	public String getHead() {
		return head;
	}

	public String getCyy() {
		return cyy;
	}

	public getUserInformation(String acn) {
		this.acn = acn;
		
		String sql = "select account, password, email, name, sex, birthday, blood, signature, status, background, head, common  from user_information where account=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setObject(1, this.acn);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				this.pwd = rs.getString(2);
				this.toEmailAddress = rs.getString(3);
				this.name = rs.getString(4);
				this.sex = rs.getString(5);
				this.birthday = rs.getString(6);
				this.blood = rs.getString(7);
				this.sig = rs.getString(8);
				this.status = rs.getInt(9);
				this.back = rs.getString(10);
				this.head = rs.getString(11);
				this.cyy = rs.getString(12);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		MysqlClose.close(rs, ps, conn);
	}
	
}
