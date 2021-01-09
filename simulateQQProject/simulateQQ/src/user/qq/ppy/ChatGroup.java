package user.qq.ppy;

import java.io.Serializable;
import java.util.Arrays;

public class ChatGroup extends User implements Serializable{
	private String group_owner;
	private int member_num;
	private User[] members;
	private String adm;
	public String getAdm() {
		return adm;
	}
	public void setAdm(String adm) {
		this.adm = adm;
	}
	public User[] getMembers() {
		return members;
	}
	public void setMembers(User[] members) {
		this.members = members;
	}
	public String getGroup_owner() {
		return group_owner;
	}
	public void setGroup_owner(String group_owner) {
		this.group_owner = group_owner;
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}

	@Override
	public String toString() {
		return "ChatGroup{" +
				"group_owner='" + group_owner + '\'' +
				", member_num=" + member_num +
				", adm='" + adm + '\'' +
				'}';
	}
}
