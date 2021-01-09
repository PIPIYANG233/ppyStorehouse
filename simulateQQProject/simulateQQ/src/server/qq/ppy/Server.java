package server.qq.ppy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server implements Runnable {

	private ServerSocket server;
	private Socket client;
	private boolean isRun;
	public static Map<String, Socket> userSocket = new HashMap<>();
	public static Map<String, Socket> userGroupSocket = new HashMap<>();

	public Server() {
		try {
			this.server = new ServerSocket(8848);
		} catch (IOException e) {
			e.printStackTrace();
		}
		isRun = true;
	}

	public static void main(String[] args) {
		System.out.println("-----Server-----");
		new Thread(new Server()).start();
	}

	public void run() {

		while (isRun) {
			try {
				client = server.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			new Thread(new ServerReceiveSend(client)).start();
		}

		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
