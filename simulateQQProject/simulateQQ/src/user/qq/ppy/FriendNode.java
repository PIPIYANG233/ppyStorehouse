package user.qq.ppy;

import javax.swing.tree.DefaultMutableTreeNode;

public class FriendNode extends DefaultMutableTreeNode{
	public ChatGroup getGroup() {
		return group;
	}

	public void setGrpup(ChatGroup group) {
		this.group = group;
	}
	protected byte[] icon;
	protected String name;
	protected String sig;
	protected int isOnline;
	protected String acn;
	protected User u;
	protected ChatGroup group;
	
	public FriendNode(byte[] icon, String name, String sig, int isOnline, String acn, User u) {
		super();
		this.icon = icon;
		this.name = name;
		this.sig = sig;
		this.isOnline = isOnline;
		this.acn = acn;
		this.u = u;
		
	}
	
	public FriendNode(byte[] icon, String name, String sig, String acn, ChatGroup group) {
		super();
		this.icon = icon;
		this.name = name;
		this.sig = sig;
		this.acn = acn;
		this.group = group;
	}

	public FriendNode(byte[] icon, String name, String sig, String acn) {
		super();
		this.icon = icon;
		this.name = name;
		this.sig = sig;
		this.acn = acn;
	}

	public byte[] getIcon() {
		return icon;
	}
	public void setIcon(byte[] icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getSig() {
		return sig;
	}
	public void setSig(String sig) {
		this.sig = sig;
	}
	public long getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}
	public String getAcn() {
		return acn;
	}
	public void setAcn(String acn) {
		this.acn = acn;
	}
	
	
}
