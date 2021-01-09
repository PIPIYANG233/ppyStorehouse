package test.qq.ppy;

import org.junit.Test;

import tools.mysql.ppy.UserDeleteOption;
import tools.mysql.ppy.UserSaveOption;
import tools.mysql.ppy.UserSelectOption;
import tools.mysql.ppy.UserUpdateOption;
import user.qq.ppy.User;

public class UserOptionTest {
	@Test
	public void saveUser() { // �����û�
		new UserSaveOption("987654", "123456", "2@2.com", "ppy").save();
	}

	@Test
	public void deleteFriend() { // ɾ������
		new UserDeleteOption().deleteFriend("987654", "2497690101");
	}

	@Test
	public void deleteGroup() { // �˳�Ⱥ��
		User user = new UserSelectOption().getUser("987654");
		new UserDeleteOption().deleteGroup(user, "899854");
	}

	@Test
	public void firedGroup() { // �˳�Ⱥ��
		User user = new UserSelectOption().getUser("987654");
		new UserDeleteOption().firedGroup(user, "899854");
	}

	@Test
	public void selectUser() { // ��ѯ�û�
		System.out.println(new UserSelectOption().getUser("987654"));
	}

	@Test
	public void selectGroupChat() { // ��ѯȺ��
		System.out.println(new UserSelectOption().getChatGroup("899854"));
	}

	@Test
	public void selectGroupChats() { // ��ѯȺ�ļ���
		System.out.println(new UserSelectOption().getGroups("2497690101"));
	}

	@Test
	public void selectMembers() { // ��ѯȺ�ĳ�Ա
		System.out.println(new UserSelectOption().getMembers("899854"));
	}

	@Test
	public void updateUser() { // �����û���Ϣ
		User user = new UserSelectOption().getUser("987654");
		user.setText("������������");
		new UserUpdateOption(user).updateSig();
	}
}
