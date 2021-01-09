package client.qq.ppy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import server.qq.ppy.Server;
import tools.qq.ppy.CloseUtils;
import user.qq.ppy.ChatGroup;
import user.qq.ppy.Massage;
import user.qq.ppy.User;

public class ClientConServer {
	private User u;
	private Socket client;

	public ClientConServer(User u, Socket client) {
		this.u = u;
		this.client = client;
	}

	public ClientConServer(User u) {
		this.u = u;
	}

	public String judgeReg() {

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String captcha = null;
		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			captcha = (String) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		release(oos, ois, client);
		return captcha;
	}

	public String judgeInsert() {

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String acn = null;
		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			acn = (String) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		release(oos, ois, client);
		return acn;
	}

	public String judgeFind() {

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String captcha = null;
		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			captcha = (String) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		release(oos, ois, client);
		return captcha;
	}

	public void judgeUpdate() {

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);
	}

	public Massage judgeEnter() {

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Massage m = null;
		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			m = (Massage) ois.readObject();
			if (m.getCanEnter() == 1) {
				m.setClient(client);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CloseUtils.close(oos, ois, client);
		return m;
	}

	public void judgeClose() {

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);
	}

	public void judgeChangeSig() {

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);
	}

	public void judgeChangeHead() {

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);
	}

	public void judgeChangeBack() {

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);
	}

	public User judgeSearch() {

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		User user = null;

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			user = (User) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);

		if (null == user) {
			return null;
		}
		return user;
	}

	public User judgeSearchGroup() {

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		ChatGroup group = null;

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			group = (ChatGroup) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);

		if (null == group) {
			return null;
		}
		return group;
	}

	public void judgeChangeInformation() {

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);
	}

	public User[] judgeFriends() {

		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Massage m = null;

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			m = (Massage) ois.readObject();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);

		if (null == m || null == m.getUsers() || null == m.getUsers()[0]) {
			return null;
		}
		return m.getUsers();
	}

	public void judgeChat() {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ChatGroup[] judgeChatGroup() {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Massage m = null;

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			m = (Massage) ois.readObject();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);

		if (null == m || null == m.getGroups() || null == m.getGroups()[0]) {
			return null;
		}
		return m.getGroups();
	}

	public void judgeGroupChat() {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void judgeChangeChangyongyu() {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);
	}

	public void judgeAddFriend() {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);

	}

	public void judgeAddGroup() {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);

	}

	public void judgeCreatGroup() {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);
	}
	
	public void judgeDeleteFriend() {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);
	}

	public void judgeDeleteGroup() {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);
	}
	
	public void judgeUpdateGroup() {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);
	}
	
	public void judgeUpdateStanding() {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);
	}

	public void judgeFired() {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		Socket client = null;
		try {
			client = new Socket("localhost", 8848);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			oos.writeObject(this.u);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		release(oos, ois, client);
	}
	
	public void release(ObjectOutputStream oos, ObjectInputStream ois, Socket client) {
		CloseUtils.close(ois, oos, client);
	}

}
