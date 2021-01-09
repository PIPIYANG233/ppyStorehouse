package client.qq.ppy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSend implements Runnable{
	private Socket client;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	
	public ClientSend(Socket client, DataInputStream dis, DataOutputStream dos, int mark) {
		this.client = client;
		try {
			this.dis = new DataInputStream(client.getInputStream());
			this.dos = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		
	}
	
	public void send() {
		
	}
}
