package client.qq.ppy;

import java.net.Socket;

import user.qq.ppy.ChatGroup;
import user.qq.ppy.Massage;
import user.qq.ppy.User;

public class ClientCheck {
	
	public String checkReg(User u) {
		ClientConServer ccs = new ClientConServer(u);
		return ccs.judgeReg();
	}
	
	public String checkInsert(User u) {
		ClientConServer ccs = new ClientConServer(u);
		return ccs.judgeReg();
	}
	
	public String checkFind(User u) {
		ClientConServer ccs = new ClientConServer(u);
		return ccs.judgeFind();
	}
	
	public void checkUpdate(User u) {
		ClientConServer ccs = new ClientConServer(u);
		ccs.judgeUpdate();
	}
	
	public Massage checkEnter(User u) {
		ClientConServer ccs = new ClientConServer(u);
		return ccs.judgeEnter();
	}
	
	public void checkClose(User u) {
		ClientConServer ccs = new ClientConServer(u);
		ccs.judgeClose();
	}
	
	public void checkChangeSig(User u) {
		ClientConServer ccs = new ClientConServer(u);
		ccs.judgeChangeSig();
	}
	
	public void checkChangeHead(User u) {
		ClientConServer ccs = new ClientConServer(u);
		ccs.judgeChangeHead();
	}
	
	public void checkChangeBack(User u) {
		ClientConServer ccs = new ClientConServer(u);
		ccs.judgeChangeBack();
	}
	
	public User checkSearch(User u) {
		ClientConServer ccs = new ClientConServer(u);
		return ccs.judgeSearch();
	}
	
	public User checkSearchGroup(User u) {
		ClientConServer ccs = new ClientConServer(u);
		return ccs.judgeSearchGroup();
	}
	
	public void checkChangeInformation(User u) {
		ClientConServer ccs = new ClientConServer(u);
		ccs.judgeChangeInformation();
	}
	
	public User[] checkFriends(User u) {
		ClientConServer ccs = new ClientConServer(u);
		return ccs.judgeFriends();
	}
	
	public void checkChat(User u, Socket client) {
		ClientConServer ccs = new ClientConServer(u, client);
		ccs.judgeChat();
	}
	
	public ChatGroup[] checkGroups(User u) {
		ClientConServer ccs = new ClientConServer(u);
		return ccs.judgeChatGroup();
	}
	
	public void checkGroupChat(User u, Socket client) {
		ClientConServer ccs = new ClientConServer(u, client);
		ccs.judgeGroupChat();
	}
	
	public void checkChangeChangyongyu(User u) {
		ClientConServer ccs = new ClientConServer(u);
		ccs.judgeChangeChangyongyu();
	}
	
	public void checkAddFriend(User u) {
		ClientConServer ccs = new ClientConServer(u);
		ccs.judgeAddFriend();
	}
	
	public void checkAddGroup(User u) {
		ClientConServer ccs = new ClientConServer(u);
		ccs.judgeAddGroup();
	}
	
	public void checkCreatGroup(User u) {
		ClientConServer ccs = new ClientConServer(u);
		ccs.judgeCreatGroup();
	}
	
	public void checkDeleteFriend(User u) {
		ClientConServer ccs = new ClientConServer(u);
		ccs.judgeDeleteFriend();
	}
	
	public void checkDeleteGroup(User u) {
		ClientConServer ccs = new ClientConServer(u);
		ccs.judgeDeleteGroup();
	}
	
	public void checkUpdateGroup(User u) {
		ClientConServer ccs = new ClientConServer(u);
		ccs.judgeUpdateGroup();
	}
	
	public void checkUpdateStanding(User u) {
		ClientConServer ccs = new ClientConServer(u);
		ccs.judgeUpdateStanding();
	}
	
	public void checkFired(User u) {
		ClientConServer ccs = new ClientConServer(u);
		ccs.judgeFired();
	}
	
}
