package server.qq.ppy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import tools.mysql.ppy.ChatSaveOption;
import tools.qq.ppy.CloseUtils;
import user.qq.ppy.TheContent;
import user.qq.ppy.User;

public class ServerChat implements Runnable{
	private User me;
	private User myFriend;
	private Socket meClient;
	private Socket myFriendClient;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private static boolean isRun;
	
	public ServerChat(User me) {
		this.me = me;
		this.meClient = Server.userSocket.get(this.me.getAcn());
		
		isRun = true;
	}
	
	@Override
	public void run() {
		
		while(isRun) {
			
			try {
				
				if(null == ois) {
					ois = new ObjectInputStream(this.meClient.getInputStream());
				}
				
					TheContent tc = (TheContent)ois.readObject();
					new ChatSaveOption().saveChatInformation(tc);
					System.out.println(tc.getSendName() + tc.getTalks() + " " + tc.getTime());
					
					this.myFriend = tc.getReceiver();
					this.myFriendClient = Server.userSocket.get(this.myFriend.getAcn());
					
					if(null != this.myFriendClient) {
						oos = new ObjectOutputStream(this.myFriendClient.getOutputStream());
						oos.writeObject(tc);
						oos.flush();
					}
				
			}catch(Exception e) {
				e.printStackTrace();
				isRun = false;
				
//				CloseUtils.close(this.oos, this.myFriendClient, this.ois, this.meClient);
			}
			
		}
		
		CloseUtils.close(this.oos, this.myFriendClient, this.ois, this.meClient);
	}
	
	public static void stopThis() {
		isRun = false;
	}
	
}
