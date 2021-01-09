package client.qq.ppy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import user.qq.ppy.ChatGroup;
import user.qq.ppy.User;

public class SearchUserGroup extends JFrame {

	private JPanel mainPanel;
	private JTextField SearchAcn;
	private User searchUser;
	private ChatGroup searchGroup;
	private User[] users;
	private ChatGroup[] groups;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public SearchUserGroup(User u) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 200, 710, 550);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);

		JPanel northPanel = new JPanel();
		northPanel.setBounds(0, 0, 885, 138);
		northPanel.setOpaque(false);
		mainPanel.add(northPanel);
		northPanel.setLayout(null);

		SearchAcn = new JTextField(); // 搜索框
		SearchAcn.setForeground(Color.BLACK);
		SearchAcn.setFont(new Font("宋体", Font.BOLD, 18));
		SearchAcn.setOpaque(false);
		SearchAcn.setCaretColor(Color.BLACK);
		SearchAcn.setBounds(86, 53, 355, 41);
		northPanel.add(SearchAcn);
		SearchAcn.setColumns(10);

		JButton search = new JButton("\u67E5\u627E"); // 查找按钮
		search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				search.setBounds(474, 48, 119, 50);
				search.setFont(new Font("宋体", Font.BOLD, 20));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				search.setBounds(479, 53, 109, 40);
				search.setFont(new Font("宋体", Font.BOLD, 18));
			}
		});

		search.setForeground(Color.WHITE);
		search.setBackground(SystemColor.textHighlight);
		search.setFont(new Font("宋体", Font.BOLD, 18));
		search.setBorder(null);
		search.setBounds(479, 53, 109, 40);
		northPanel.add(search);

		JPanel southPanel = new JPanel();
		southPanel.setBounds(0, 138, 885, 425);
		southPanel.setOpaque(false);
		mainPanel.add(southPanel);
		southPanel.setLayout(null);

		UIManager.put("TabbedPane.contentOpaque", false);
		JTabbedPane result = new JTabbedPane(JTabbedPane.TOP);
		result.setBounds(0, 0, 694, 373);
		result.setOpaque(false);
		result.setFont(new Font("宋体", Font.BOLD, 12));
		result.setBorder(null);
		result.setBackground(null);
		southPanel.add(result);

		JPanel ren = new JPanel(); // 搜索到的人
		ren.setOpaque(false);
		result.addTab("    人    ", null, ren, null);
		ren.setLayout(null);

		JPanel theInformation = new JPanel();
		theInformation.setOpaque(false);
		theInformation.setBounds(27, 23, 275, 275);
		ren.add(theInformation);
		theInformation.setLayout(null);

		JLabel otherHead = new JLabel(); // 人的头像
		otherHead.setBounds(10, 10, 85, 85);
		theInformation.add(otherHead);

		JTextArea theName = new JTextArea(); // 人的名字
		theName.setLineWrap(true);
		theName.setBounds(137, 26, 128, 33);
		theName.setEnabled(false);
		theName.setOpaque(false);
		theName.setFont(new Font("宋体", Font.BOLD, 20));
		theInformation.add(theName);

		JTextArea theSex = new JTextArea(); // 人的性别
		theSex.setLineWrap(true);
		theSex.setBounds(137, 65, 56, 30);
		theSex.setEnabled(false);
		theSex.setOpaque(false);
		theSex.setFont(new Font("宋体", Font.BOLD, 20));
		theInformation.add(theSex);

		JTextArea theSig = new JTextArea(); // 人的签名
		theSig.setLineWrap(true);
		theSig.setBounds(10, 117, 255, 85);
		theSig.setEnabled(false);
		theSig.setOpaque(false);
		theSig.setFont(new Font("宋体", Font.BOLD, 20));
		theInformation.add(theSig);

		JLabel addTa = new JLabel(); // 加好友
		addTa.setForeground(Color.BLACK);
		addTa.setFont(new Font("宋体", Font.BOLD, 18));
		addTa.setHorizontalAlignment(SwingConstants.LEFT);
		addTa.setBounds(10, 219, 855, 33);
		theInformation.add(addTa);

		JPanel qun = new JPanel();
		qun.setOpaque(false);
		result.addTab("    群    ", null, qun, null);
		qun.setLayout(null);

		JPanel theInformation_1 = new JPanel();
		theInformation_1.setLayout(null);
		theInformation_1.setOpaque(false);
		theInformation_1.setBounds(0, 0, 275, 275);
		qun.add(theInformation_1);

		JLabel otherHead_1 = new JLabel();
		otherHead_1.setBounds(10, 10, 85, 85);
		theInformation_1.add(otherHead_1);

		JTextArea theName_1 = new JTextArea();
		theName_1.setOpaque(false);
		theName_1.setLineWrap(true);
		theName_1.setFont(new Font("宋体", Font.BOLD, 20));
		theName_1.setEnabled(false);
		theName_1.setBounds(137, 26, 128, 33);
		theInformation_1.add(theName_1);

		JTextArea theSex_1 = new JTextArea();
		theSex_1.setOpaque(false);
		theSex_1.setLineWrap(true);
		theSex_1.setFont(new Font("宋体", Font.BOLD, 20));
		theSex_1.setEnabled(false);
		theSex_1.setBounds(137, 65, 56, 30);
		theInformation_1.add(theSex_1);

		JTextArea theSig_1 = new JTextArea();
		theSig_1.setOpaque(false);
		theSig_1.setLineWrap(true);
		theSig_1.setFont(new Font("宋体", Font.BOLD, 20));
		theSig_1.setEnabled(false);
		theSig_1.setBounds(10, 117, 255, 85);
		theInformation_1.add(theSig_1);

		JLabel addTa_1 = new JLabel();
		addTa_1.setHorizontalAlignment(SwingConstants.LEFT);
		addTa_1.setForeground(Color.BLACK);
		addTa_1.setFont(new Font("宋体", Font.BOLD, 18));
		addTa_1.setBounds(10, 219, 255, 33);
		theInformation_1.add(addTa_1);

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String zhanghao = null;
				zhanghao = SearchAcn.getText();
				if (null == zhanghao || zhanghao.length() == 0) {
					zhanghao = "1";
				}
				u.setSearch(zhanghao); // 找用户
				searchUser = new ClientCheck().checkSearch(u);
				u.setSearch(null);
				theName.setText(null);
				theSex.setText(null);
				theSig.setText(null);
				otherHead.setIcon(null);
				addTa.setText(null);
				theInformation.setBorder(null);

				if (null != searchUser) {

					theInformation.add(addTa);
					ImageIcon theHeadPic = new ImageIcon(searchUser.getHead()); // 头像
					theHeadPic.setImage(theHeadPic.getImage().getScaledInstance(85, 85, Image.SCALE_DEFAULT));
					otherHead.setIcon(theHeadPic);

					theName.append(searchUser.getName());
					theSex.append(searchUser.getSex());
					theSig.append(searchUser.getText());
					addTa.setText("+ 好友");
					addTa.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent arg0) {
							addTa.setForeground(Color.GRAY);
							addTa.setFont(new Font("宋体", Font.BOLD, 24));
						}

						@Override
						public void mouseExited(MouseEvent e) {
							addTa.setForeground(Color.BLACK);
							addTa.setFont(new Font("宋体", Font.BOLD, 18));
						}

						@Override
						public void mouseClicked(MouseEvent e) {
							users = FriendList.getUsers();
							if (u.getAcn().equals(searchUser.getAcn())) {
								JOptionPane.showMessageDialog(null, "不能添加自己", "温馨提示", 2,
										new ImageIcon("F:\\Myeclipseproject\\QQ项目\\图片\\！！！！.jpg"));
							} else {
								boolean flag = true;

								if (null != users && null != users[0]) {
									for (int i = 0; null != users[i]; i++) {
										if (searchUser.getAcn().equals(users[i].getAcn())) {
											JOptionPane.showMessageDialog(null, "对方已是您的好友", "温馨提示", 2,
													new ImageIcon("F:\\Myeclipseproject\\QQ项目\\图片\\！！！！.jpg"));
											flag = false;
											break;
										}
									}
								}
								if (flag) {
									u.setAddFriend(true);
									u.setTheAddFriend(searchUser);
									new ClientCheck().checkAddFriend(u);
									u.setAddFriend(false);
									JOptionPane.showMessageDialog(null, "添加成功", "温馨提示", 2,
											new ImageIcon("F:\\Myeclipseproject\\QQ项目\\图片\\死侍.gif"));
								}
							}
						}
					});

					theInformation.setBorder(BorderFactory.createRaisedBevelBorder());

					otherHead.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent arg0) {
							otherHead.setBorder(BorderFactory.createRaisedBevelBorder());
						}

						@Override
						public void mouseExited(MouseEvent e) {
							otherHead.setBorder(null);
						}

						@Override
						public void mouseClicked(MouseEvent e) {
							new CheckUserInformation(searchUser);
						}
					});
				} else {
					otherHead.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent arg0) {
							otherHead.setBorder(null);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							otherHead.setBorder(null);
						}
					});
				}

//--------------------------------------------------- 找群聊				
				u.setSearchGroup(zhanghao);
				searchGroup = (ChatGroup) new ClientCheck().checkSearchGroup(u);
				u.setSearchGroup(null); // ++++++
				theName_1.setText(null);
				theSex_1.setText(null);
				theSig_1.setText(null);
				otherHead_1.setIcon(null);
				addTa_1.setText(null);
				theInformation_1.setBorder(null);

				if (null != searchGroup) {

					theInformation_1.add(addTa_1);
					ImageIcon theHeadPic_1 = new ImageIcon(searchGroup.getHead()); // 头像
					theHeadPic_1.setImage(theHeadPic_1.getImage().getScaledInstance(85, 85, Image.SCALE_DEFAULT));
					otherHead_1.setIcon(theHeadPic_1);

					theName_1.append(searchGroup.getName());
					theSex_1.append(searchGroup.getSex());
					theSig_1.append(searchGroup.getText());
					addTa_1.setText("+ 群");
					addTa_1.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent arg0) {
							addTa_1.setForeground(Color.GRAY);
							addTa_1.setFont(new Font("宋体", Font.BOLD, 24));
						}

						@Override
						public void mouseExited(MouseEvent e) {
							addTa_1.setForeground(Color.BLACK);
							addTa_1.setFont(new Font("宋体", Font.BOLD, 18));
						}

						@Override
						public void mouseClicked(MouseEvent e) {
							groups = FriendList.getGroups();
							boolean flag = true;

							if (null != groups && null != groups[0]) {
								for (int i = 0; null != groups[i]; i++) {
									if (searchGroup.getAcn().equals(groups[i].getAcn())) {
										JOptionPane.showMessageDialog(null, "已加入该群聊", "温馨提示", 2,
												new ImageIcon("F:\\Myeclipseproject\\QQ项目\\图片\\！！！！.jpg"));
										flag = false;
										break;
									}
								}
							}
							if (flag) {
								u.setAddGroup(true);
								u.setTheAddGroup(searchGroup);
								new ClientCheck().checkAddGroup(u);
								u.setAddGroup(false);

								try {
									Thread.sleep(3);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								FriendList.flash(2);
							}
						}
					});

					theInformation_1.setBorder(BorderFactory.createRaisedBevelBorder());

					otherHead_1.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent arg0) {
							otherHead_1.setBorder(BorderFactory.createRaisedBevelBorder());
						}

						@Override
						public void mouseExited(MouseEvent e) {
							otherHead_1.setBorder(null);
						}

						@Override
						public void mouseClicked(MouseEvent e) {
							new CheckGroupInformation(searchGroup);
						}
					});
				} else {
					otherHead.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent arg0) {
							otherHead.setBorder(null);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							otherHead.setBorder(null);
						}
					});
				}

			}
		});

		ImageIcon back = new ImageIcon("C:\\Users\\hp\\Pictures\\Saved Pictures\\190623-1493031983968b.jpg");
		back.setImage(back.getImage().getScaledInstance(710, 550, Image.SCALE_DEFAULT));
		JLabel background = new JLabel(back);
		background.setBounds(0, 0, 694, 511);
		mainPanel.add(background);
		setVisible(true);
	}
}
