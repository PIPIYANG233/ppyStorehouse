package server.qq.ppy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import tools.mysql.ppy.ChatSaveOption;
import tools.qq.ppy.CloseUtils;
import user.qq.ppy.ChatGroup;
import user.qq.ppy.TheContent;
import user.qq.ppy.User;

public class ServerGroupChat implements Runnable{
	private User me;
	private ChatGroup group;
	private Socket meClient;
	private Socket membersClient;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private static boolean isRun;
	
	
	
	public ServerGroupChat(User me) {
		this.me = me;
		this.meClient = Server.userGroupSocket.get(this.me.getAcn());
		
		isRun = true;
	}

	public void run() {
		
		while(isRun) {
			
			try {
				if(null == ois) {
					ois = new ObjectInputStream(this.meClient.getInputStream());
				}
				
				TheContent tc = (TheContent)ois.readObject();
				new ChatSaveOption().saveGroupChatInformation(tc);
				System.out.println(tc.getSendName() + tc.getTalks() + " " + tc.getTime());
				
				this.group = tc.getGroup();
				int len = this.group.getMember_num();
				User[] members = this.group.getMembers();
				for(int i = 0; i < len; i++) {
					
					this.membersClient = Server.userGroupSocket.get(members[i].getAcn());
					if(null != this.membersClient && this.meClient != this.membersClient) {
						
						oos = new ObjectOutputStream(this.membersClient.getOutputStream());
						oos.writeObject(tc);
						oos.flush();
					}
					
				}
			}catch(Exception e) {
//				e.printStackTrace();
				isRun = false;
				CloseUtils.close(this.oos, this.membersClient, this.ois, this.meClient);
			}
		}
		
		CloseUtils.close(this.oos, this.membersClient, this.ois, this.meClient);
	}
	
	public static void stopThis() {
		isRun = false;
	}
}
