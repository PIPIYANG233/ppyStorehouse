package server.qq.ppy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

import tools.mysql.ppy.UserDeleteOption;
import tools.mysql.ppy.UserSaveOption;
import tools.mysql.ppy.UserSelectOption;
import tools.mysql.ppy.UserUpdateOption;
import tools.qq.ppy.CloseUtils;
import tools.qq.ppy.MakeCaptcha;
import tools.qq.ppy.SendEmail;
import user.qq.ppy.ChatGroup;
import user.qq.ppy.Massage;
import user.qq.ppy.User;

public class ServerReceiveSend implements Runnable {
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private User u;
	private Socket client;

	public ServerReceiveSend(Socket client) {
		this.client = client;

		try {
			ois = new ObjectInputStream(this.client.getInputStream());
			oos = new ObjectOutputStream(this.client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int receive() {
		try {
			this.u = (User) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (this.u.isReg()) {
			return 1;
		} else if (this.u.isInsert()) {
			return -1;
		} else if (this.u.isFind()) {
			return 2;
		} else if (this.u.isUpdate()) {
			return -2;
		} else if (this.u.isEnter()) {
			return 3;
		} else if (this.u.isClose()) {
			return -3;
		} else if (this.u.isChangeSig()) {
			return 4;
		} else if (this.u.isChangeHead()) {
			return 5;
		} else if (this.u.isChangeBack()) {
			return 6;
		} else if (null != this.u.getSearch()) {
			return 7;
		} else if (this.u.isChangeInformation()) {
			return 8;
		} else if (this.u.isFriends()) {
			return 9;
		} else if (this.u.isChat()) {
			Server.userSocket.put(this.u.getAcn(), this.client);
			return 10;
		} else if (this.u.isGroups()) {
			return 11;
		} else if (this.u.isGroupChat()) {
			Server.userGroupSocket.put(this.u.getAcn(), this.client);
			return 12;
		} else if (null != this.u.getSearchGroup()) {
			return 13;
		} else if (this.u.isCyy()) {
			return 14;
		} else if (this.u.isAddFriend()) {
			return 15;
		} else if (this.u.isAddGroup()) {
			return 16;
		} else if (this.u.isCreatGroup()) {
			return 17;
		} else if (this.u.isDeleteFriend()) {
			return 18;
		} else if (this.u.isDeleteGroup()) {
			return 19;
		} else if (this.u.isUpdateGroup()) {
			return 20;
		} else if (this.u.isUpdateStanding()) {
			return 21;
		}
		return 0;
	}

	public void send(int mark) {

		if (mark == 1) {
			System.out.println("���û�: " + this.u.getEmail() + "����ע���˺�");
			String captcha = MakeCaptcha.makeCapcha();
			if (!new UserSaveOption(this.u.getAcn(), this.u.getPwd(), this.u.getEmail()).findRepeat()) {
				captcha = "";
			} else {
				if (!SendEmail.send(this.u.getEmail(), captcha)) {
					System.out.println(this.u.getEmail() + "Ϊ��Ч����");
					captcha = "";
				}
			}

			try {
				oos.writeObject(captcha);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			release();
		} else if (mark == -1) {
			System.out.println("���û�" + this.u.getEmail() + "�����˺�");
			String acn = "";
			Random random = new Random();

			char[] s1 = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
			acn += s1[random.nextInt(8)];

			char[] s2 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
			for (int i = 0; i < 9; i++)
				acn += s2[random.nextInt(9)];

			new UserSaveOption(acn, this.u.getPwd(), this.u.getEmail(), this.u.getName()).save();

			try {
				oos.writeObject(acn);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			release();
		} else if (mark == 2) {
			System.out.println("�û�: " + this.u.getAcn() + "������������");
			String captcha = MakeCaptcha.makeCapcha();
			if (!SendEmail.send(this.u.getEmail(), captcha)
					|| !new UserSaveOption(this.u.getAcn(), this.u.getPwd(), this.u.getEmail()).findEmali()) {
				captcha = "";
			}

			try {
				oos.writeObject(captcha);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			release();
		} else if (mark == -2) {
			System.out.println("�û�: " + this.u.getAcn() + "��������ɹ�");
			new UserSaveOption(this.u.getAcn(), this.u.getPwd()).updatePWD();
			release();
		} else if (mark == 3) {
			Massage m = null;
			try {
				m = new UserSaveOption(this.u.getAcn(), this.u.getPwd()).findPWD();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (m.getCanEnter() == 1) {
				System.out.println("�û�: " + this.u.getAcn() + "---" + "�ɹ���¼");
			}

			try {
				oos.writeObject(m);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			release();
		} else if (mark == -3) {
			if (new UserSaveOption(this.u.getAcn(), this.u.getPwd()).changeStatus(-1)) {
				Server.userSocket.remove(this.u.getAcn(), this.client);
				System.out.println("�û�: " + this.u.getAcn() + "---" + "������");
			}
			release();
		} else if (mark == 4) {
			new UserUpdateOption(this.u).updateSig();
			System.out.println("�û�: " + this.u.getAcn() + "---" + "ǩ�����ĳɹ�");
			release();
		} else if (mark == 5) {
			new UserUpdateOption(this.u).updateHead();
			System.out.println("�û�: " + this.u.getAcn() + "---" + "��ͷ��ɹ�");
			release();
		} else if (mark == 6) {
			new UserUpdateOption(this.u).updateBack();
			System.out.println("�û�: " + this.u.getAcn() + "---" + "�������ɹ�");
			release();
		} else if (mark == 7) {
			User user = null;
			user = new UserSelectOption().getUser(this.u.getSearch());
			if (null != user) {
				System.out
						.println("�û�: " + this.u.getAcn() + "---" + "����" + " �û�: " + this.u.getSearch() + "�ɹ�" + user);
			} else {
				System.out.println("�û�: " + this.u.getAcn() + "---" + "����" + " �û�: " + this.u.getSearch() + "ʧ��");
			}

			try {
				oos.writeObject(user);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			release();
		} else if (mark == 8) {
			new UserUpdateOption(this.u).updateZiLiao();
			System.out.println("�û�: " + this.u.getAcn() + "---" + "���ϸ��ĳɹ�");
			release();
		} else if (mark == 9) {
			Massage m = new Massage();
			m.setUsers(new UserSelectOption().getUsers(this.u.getAcn()));
			if (null == m.getUsers() || null == m.getUsers()[0].getAcn() || m.getUsers()[0].getAcn().length() == 0) {
				System.out.println("�û�: " + this.u.getAcn() + "---" + "�޺���");
			} else {
				System.out.println("�û�: " + this.u.getAcn() + "---" + "��ȡ���ѳɹ�");
			}

			try {
				oos.writeObject(m);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			release();
		} else if (mark == 10) {
			new Thread(new ServerChat(this.u)).start();
		} else if (mark == 11) {
			Massage m = new Massage();
			m.setGroups(new UserSelectOption().getGroups(this.u.getAcn()));
			if (null == m.getGroups() || null == m.getGroups()[0]) {
				System.out.println("�û�: " + this.u.getAcn() + "---" + "��Ⱥ��");
			} else {
				System.out.println("�û�: " + this.u.getAcn() + "---" + "��ȡȺ�ĳɹ�");
			}

			try {
				oos.writeObject(m);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			release();
		} else if (mark == 12) {
			new Thread(new ServerGroupChat(this.u)).start();
		} else if (mark == 13) {
			ChatGroup group = null;
			group = new UserSelectOption().getChatGroup(this.u.getSearchGroup());
			if (null != group) {
				System.out.println(
						"�û�: " + this.u.getAcn() + "---" + "����" + " Ⱥ��: " + this.u.getSearchGroup() + "�ɹ�" + group);
			} else {
				System.out.println("�û�: " + this.u.getAcn() + "---" + "����" + " Ⱥ��: " + this.u.getSearchGroup() + "ʧ��");
			}

			try {
				oos.writeObject(group);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			release();
		} else if (mark == 14) {
			System.out.println("�û�: " + this.u.getAcn() + "���ó�����ɹ�");
			new UserSaveOption().updateChangyongyu(this.u);
			release();
		} else if (mark == 15) {
			System.out.println("�û�: " + this.u.getAcn() + "��Ӻ��ѳɹ�");
			new UserSelectOption().saveFriend(this.u.getAcn(), this.u.getTheAddFriend());
			release();
		} else if (mark == 16) {
			System.out.println("�û�: " + this.u.getAcn() + "���Ⱥ�ĳɹ�");
			new UserSelectOption().saveAddGroup(this.u, this.u.getTheAddGroup());
			release();
		} else if (mark == 17) {
			System.out.println("�û�: " + this.u.getAcn() + "����Ⱥ�ĳɹ�");
			new UserSelectOption().saveCreatGroup(this.u, this.u.getTheCreatGroup());
			release();
		} else if (mark == 18) {
			System.out.println("�û�: " + this.u.getAcn() + "ɾ�����ѳɹ�");
			new UserDeleteOption().deleteFriend(this.u.getAcn(), this.u.getTheDeleteFriend());
			release();
		} else if (mark == 19) {
			System.out.println("�û�: " + this.u.getAcn() + "�˳�Ⱥ�ĳɹ�");
			new UserDeleteOption().deleteGroup(this.u, this.u.getTheDeleteGroup());
			release();
		} else if (mark == 20) {
			System.out.println("�û�: " + this.u.getAcn() + "����Ⱥ�����ϳɹ�");
			new UserUpdateOption(this.u).updateGroup();
			release();
		} else if (mark == 21) {

			if (this.u.getRank() == 0) {
				System.out.println("�û�: " + this.u.getAcn() + "��ְ�ɹ�");
				new UserUpdateOption(this.u).updateStandingUp(this.u.getTheUpdateGroup().getAcn());
				release();
			} else {
				System.out.println("�û�: " + this.u.getAcn() + "��ְ�ɹ�");
				new UserUpdateOption(this.u).updateStandingDown(this.u.getTheUpdateGroup().getAcn());
				release();
			}
		} else {
			System.out.println("�û�: " + this.u.getAcn() + "���ɹ��߳�");
			new UserDeleteOption().firedGroup(this.u, this.u.getTheDeleteGroup());
			release();
		}
	}

	public void run() {
		send(receive());
//		release();
	}

	public void release() {
		CloseUtils.close(oos, ois, client);
	}
}
