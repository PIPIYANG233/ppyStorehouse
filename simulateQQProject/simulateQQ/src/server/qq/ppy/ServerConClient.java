package server.qq.ppy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import user.qq.ppy.User;

public class ServerConClient implements Runnable{
	private User u;
	private Socket client;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private boolean isRunning;

	public ServerConClient(User u) {
		this.u = u;
		try {
			client = new Socket("localhost", 8848);
			ois = new ObjectInputStream(this.client.getInputStream());
			oos = new ObjectOutputStream(this.client.getOutputStream());
			isRunning = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public void run() {
		
	}
}
