package user.qq.ppy;

import java.io.Serializable;
import java.net.Socket;
import java.util.Arrays;

public class Massage implements Serializable{
	private User u;
	private int canEnter;
	private Socket client;
	
	public Socket getClient() {
		return client;
	}
	public void setClient(Socket client) {
		this.client = client;
	}
	private User[] users;
	private ChatGroup[] groups;
	
	public ChatGroup[] getGroups() {
		return groups;
	}
	public void setGroups(ChatGroup[] groups) {
		this.groups = groups;
	}
	public User[] getUsers() {
		return users;
	}
	public void setUsers(User[] users) {
		this.users = users;
	}
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
	public int getCanEnter() {
		return canEnter;
	}
	public void setCanEnter(int canEnter) {
		this.canEnter = canEnter;
	}

	@Override
	public String toString() {
		return "Massage{" +
				"u=" + u +
				", canEnter=" + canEnter +
				", client=" + client +
				", users=" + Arrays.toString(users) +
				", groups=" + Arrays.toString(groups) +
				'}';
	}
}
