package user.qq.ppy;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

public class User implements Serializable {
	private byte[] back;
	private byte[] head;
	private boolean reg;
	private boolean insert;
	private boolean find;
	private boolean update;// ÷ÿ÷√√‹¬Î
	private boolean enter;
	private boolean close;
	private boolean changeSig;
	private boolean changeHead;
	private boolean ChangeBack;
	private String search;
	private boolean ChangeInformation;
	private boolean friends;
	private boolean chat;
	private String acn;
	private String pwd;
	private String email;
	private String name;
	private String sex;
	private String birthday;
	private String blood;
	private String text;
	private int status;
	private String toFriend;
	private String toGroup;
	private int rank;
	private boolean groupChat;
	private String searchGroup;
	private boolean cyy;
	private boolean addFriend;
	private boolean addGroup;
	private User theAddFriend;
	private ChatGroup theAddGroup;
	private boolean creatGroup;
	private ChatGroup theCreatGroup;
	private boolean deleteFriend;
	private String theDeleteFriend;
	private boolean updateGroup;
	private boolean updateStanding;
	private User theUpdateGroup;
	public boolean isUpdateStanding() {
		return updateStanding;
	}

	public void setUpdateStanding(boolean updateStanding) {
		this.updateStanding = updateStanding;
	}

	public User getTheUpdateGroup() {
		return theUpdateGroup;
	}

	public void setTheUpdateGroup(User theUpdateGroup) {
		this.theUpdateGroup = theUpdateGroup;
	}

	public boolean isUpdateGroup() {
		return updateGroup;
	}

	public void setUpdateGroup(boolean updateGroup) {
		this.updateGroup = updateGroup;
	}

	public String getTheDeleteFriend() {
		return theDeleteFriend;
	}

	public void setTheDeleteFriend(String theDeleteFriend) {
		this.theDeleteFriend = theDeleteFriend;
	}

	public String getTheDeleteGroup() {
		return theDeleteGroup;
	}

	public void setTheDeleteGroup(String theDeleteGroup) {
		this.theDeleteGroup = theDeleteGroup;
	}

	private boolean deleteGroup;
	private String theDeleteGroup;

	public boolean isDeleteFriend() {
		return deleteFriend;
	}

	public void setDeleteFriend(boolean deleteFriend) {
		this.deleteFriend = deleteFriend;
	}

	public boolean isDeleteGroup() {
		return deleteGroup;
	}

	public void setDeleteGroup(boolean deleteGroup) {
		this.deleteGroup = deleteGroup;
	}

	public boolean isCreatGroup() {
		return creatGroup;
	}

	public void setCreatGroup(boolean creatGroup) {
		this.creatGroup = creatGroup;
	}

	public ChatGroup getTheCreatGroup() {
		return theCreatGroup;
	}

	public void setTheCreatGroup(ChatGroup theCreatGroup) {
		this.theCreatGroup = theCreatGroup;
	}

	public User getTheAddFriend() {
		return theAddFriend;
	}

	public void setTheAddFriend(User theAddFriend) {
		this.theAddFriend = theAddFriend;
	}

	public ChatGroup getTheAddGroup() {
		return theAddGroup;
	}

	public void setTheAddGroup(ChatGroup theAddGroup) {
		this.theAddGroup = theAddGroup;
	}

	public boolean isAddFriend() {
		return addFriend;
	}

	public void setAddFriend(boolean addFriend) {
		this.addFriend = addFriend;
	}

	public boolean isAddGroup() {
		return addGroup;
	}

	public void setAddGroup(boolean addGroup) {
		this.addGroup = addGroup;
	}

	public boolean isCyy() {
		return cyy;
	}

	public void setCyy(boolean cyy) {
		this.cyy = cyy;
	}

	public String getChangyongyu() {
		return changyongyu;
	}

	public void setChangyongyu(String changyongyu) {
		this.changyongyu = changyongyu;
	}

	private String changyongyu;

	public String getSearchGroup() {
		return searchGroup;
	}

	public void setSearchGroup(String searchGroup) {
		this.searchGroup = searchGroup;
	}

	public boolean isGroupChat() {
		return groupChat;
	}

	public void setGroupChat(boolean groupChat) {
		this.groupChat = groupChat;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getToFriend() {
		return toFriend;
	}

	public void setToFriend(String toFriend) {
		this.toFriend = toFriend;
	}

	public String getToGroup() {
		return toGroup;
	}

	public void setToGroup(String toGroup) {
		this.toGroup = toGroup;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public byte[] getBack() {
		return back;
	}

	public void setBack(byte[] back) {
		this.back = back;
	}

	public byte[] getHead() {
		return head;
	}

	public void setHead(byte[] head) {
		this.head = head;
	}

	public boolean isGroups() {
		return groups;
	}

	public void setGroups(boolean groups) {
		this.groups = groups;
	}

	private boolean groups;

	public boolean isChat() {
		return chat;
	}

	public void setChat(boolean chat) {
		this.chat = chat;
	}

	public boolean isFriends() {
		return friends;
	}

	public void setFriends(boolean friends) {
		this.friends = friends;
	}

	public boolean isChangeInformation() {
		return ChangeInformation;
	}

	public void setChangeInformation(boolean changeInformation) {
		ChangeInformation = changeInformation;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public boolean isChangeHead() {
		return changeHead;
	}

	public void setChangeHead(boolean changeHead) {
		this.changeHead = changeHead;
	}

	public boolean isChangeBack() {
		return ChangeBack;
	}

	public void setChangeBack(boolean changeBack) {
		ChangeBack = changeBack;
	}

	public boolean isChangeSig() {
		return changeSig;
	}

	public void setChangeSig(boolean changeSig) {
		this.changeSig = changeSig;
	}

	public boolean isClose() {
		return close;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

	public boolean isEnter() {
		return enter;
	}

	public void setEnter(boolean enter) {
		this.enter = enter;
	}

	public boolean isReg() {
		return reg;
	}

	public void setReg(boolean reg) {
		this.reg = reg;
	}

	public boolean isInsert() {
		return insert;
	}

	public void setInsert(boolean insert) {
		this.insert = insert;
	}

	public boolean isFind() {
		return find;
	}

	public void setFind(boolean find) {
		this.find = find;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public String getAcn() {
		return acn;
	}

	public void setAcn(String acn) {
		this.acn = acn;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "User{" +
				"back=" + Arrays.toString(back) +
				", acn='" + acn + '\'' +
				", pwd='" + pwd + '\'' +
				", email='" + email + '\'' +
				", name='" + name + '\'' +
				", sex='" + sex + '\'' +
				", birthday='" + birthday + '\'' +
				", blood='" + blood + '\'' +
				", text='" + text + '\'' +
				", status=" + status +
				'}';
	}
}