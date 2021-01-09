package test.qq.ppy;

import org.junit.Test;

import tools.mysql.ppy.UserDeleteOption;
import tools.mysql.ppy.UserSaveOption;
import tools.mysql.ppy.UserSelectOption;
import tools.mysql.ppy.UserUpdateOption;
import user.qq.ppy.User;

public class UserOptionTest {
	@Test
	public void saveUser() { // 保存用户
		new UserSaveOption("987654", "123456", "2@2.com", "ppy").save();
	}

	@Test
	public void deleteFriend() { // 删除好友
		new UserDeleteOption().deleteFriend("987654", "2497690101");
	}

	@Test
	public void deleteGroup() { // 退出群聊
		User user = new UserSelectOption().getUser("987654");
		new UserDeleteOption().deleteGroup(user, "899854");
	}

	@Test
	public void firedGroup() { // 退出群聊
		User user = new UserSelectOption().getUser("987654");
		new UserDeleteOption().firedGroup(user, "899854");
	}

	@Test
	public void selectUser() { // 查询用户
		System.out.println(new UserSelectOption().getUser("987654"));
	}

	@Test
	public void selectGroupChat() { // 查询群聊
		System.out.println(new UserSelectOption().getChatGroup("899854"));
	}

	@Test
	public void selectGroupChats() { // 查询群聊集合
		System.out.println(new UserSelectOption().getGroups("2497690101"));
	}

	@Test
	public void selectMembers() { // 查询群聊成员
		System.out.println(new UserSelectOption().getMembers("899854"));
	}

	@Test
	public void updateUser() { // 更新用户信息
		User user = new UserSelectOption().getUser("987654");
		user.setText("哈哈哈哈哈哈");
		new UserUpdateOption(user).updateSig();
	}
}
