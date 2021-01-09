package user.qq.ppy;

import java.io.Serializable;
import java.util.Arrays;

public class TheContent implements Serializable{
	private User receiver;
	private String talks;
	private String sendName;
	private String memory;
	private ChatGroup group;
	private User sender;
	private String senderACN;
	private String receiverACN;
	private byte[] pic;
	private String fileName;
	private int rank = 0;
	private String yy;
	public String getYy() {
		return yy;
	}
	public void setYy(String yy) {
		this.yy = yy;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	private byte[] file;
	
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	public User getSender() {
		return sender;
	}
	public String getSenderACN() {
		return senderACN;
	}
	public void setSenderACN(String senderACN) {
		this.senderACN = senderACN;
	}
	public String getReceiverACN() {
		return receiverACN;
	}
	public void setReceiverACN(String receiverACN) {
		this.receiverACN = receiverACN;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public ChatGroup getGroup() {
		return group;
	}
	public void setGroup(ChatGroup group) {
		this.group = group;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	private String time;
	
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public String getTalks() {
		return talks;
	}
	public void setTalks(String talks) {
		this.talks = talks;
	}

	@Override
	public String toString() {
		return "TheContent{" +
				"receiver=" + receiver +
				", talks='" + talks + '\'' +
				", sendName='" + sendName + '\'' +
				", memory='" + memory + '\'' +
				", group=" + group +
				", sender=" + sender +
				", senderACN='" + senderACN + '\'' +
				", receiverACN='" + receiverACN + '\'' +
				", pic=" + Arrays.toString(pic) +
				", fileName='" + fileName + '\'' +
				", rank=" + rank +
				", yy='" + yy + '\'' +
				", file=" + Arrays.toString(file) +
				", time='" + time + '\'' +
				'}';
	}
}
