package client.qq.ppy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import tools.qq.ppy.BytesToPath;
import tools.qq.ppy.MakeCaptcha;
import user.qq.ppy.ChatGroup;
import user.qq.ppy.FriendNode;
import user.qq.ppy.FriendNodeRender;
import user.qq.ppy.User;

public class FriendList extends JFrame implements Runnable {

	private static User u;
	private JPanel mainPanel;
	private JTextField mySig;
	private static JTextArea myName;
	private static JTree friends;
	private static JTree myGroups;
	private static User[] users;
	private static ChatGroup[] groups;
	private static FriendNode friendRoot;
	private static FriendNode groupRoot;
	private static DefaultTreeModel friendModel;
	private static DefaultTreeModel groupModel;
	private static JScrollPane myFriendPanel;
	private static JScrollPane qun;
	private JTextField groupName;
	private JTextField groupAcn;
	private JLabel queding;

	public static User[] getUsers() {
		return users;
	}

	public static ChatGroup[] getGroups() {
		return groups;
	}

	public FriendList(User user) {
		u = user;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent e) {
				u.setClose(true);
				new ClientCheck().checkClose(u);
			}

			public void windowActivated(WindowEvent arg0) {
			}

			public void windowClosed(WindowEvent arg0) {
			}

			public void windowDeactivated(WindowEvent arg0) {
			}

			public void windowDeiconified(WindowEvent arg0) {
			}

			public void windowIconified(WindowEvent arg0) {
			}

			public void windowOpened(WindowEvent arg0) {
			}
		});
		setBounds(100, 100, 389, 680);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);

		JPanel northPanel = new JPanel(); // ------------------------------������-----------------------------------------------------------------------------
		northPanel.setBounds(0, 0, 373, 113);
		northPanel.setOpaque(false);
		mainPanel.add(northPanel);
		northPanel.setLayout(null);

		ImageIcon headPic = new ImageIcon(u.getHead()); // ͷ��
		headPic.setImage(headPic.getImage().getScaledInstance(93, 93, Image.SCALE_DEFAULT));
		JLabel myHead = new JLabel(headPic);
		myHead.addMouseListener(new MouseAdapter() { // ��ͷ��
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser jc = new JFileChooser(); // �ļ�ѡ���
				jc.setDialogTitle("��ѡ��ͼƬ�ļ�");
				int click = jc.showOpenDialog(null);// ����ѡ��򣬷���ֵ���������Ĳ���
				if (JFileChooser.APPROVE_OPTION == click) { // ѡ�����ļ���ȷ��
					String path = jc.getSelectedFile().getPath(); // ��õ�һ���ļ�����ֵ
					ImageIcon newHead = new ImageIcon(path);
					newHead.setImage(newHead.getImage().getScaledInstance(93, 93, Image.SCALE_DEFAULT));
					myHead.setIcon(newHead);

					u.setChangeHead(true);
					u.setHead(BytesToPath.getBytes(path));
					new ClientCheck().checkChangeHead(u);
					u.setChangeHead(false);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				myHead.setBorder(BorderFactory.createRaisedBevelBorder());
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				myHead.setBorder(null);
			}
		});
		myHead.setBounds(10, 10, 89, 93);
		northPanel.add(myHead);

		mySig = new JTextField(u.getText()); // ����ǩ��
		mySig.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {

				if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					u.setChangeSig(true);
					u.setText(mySig.getText());
					new ClientCheck().checkChangeSig(u);
					u.setChangeSig(false);
				}
			}
		});
		mySig.setBounds(109, 82, 254, 21);
		mySig.setOpaque(false);
		mySig.setForeground(Color.BLACK);
		mySig.setCaretColor(Color.BLACK);
		mySig.setFont(new Font("����", Font.BOLD, 12));
		northPanel.add(mySig);
		mySig.setColumns(10);

		myName = new JTextArea(); // �ǳ�
		myName.setEnabled(false);
		myName.setBounds(109, 31, 254, 41);
		myName.setOpaque(false);
		myName.setFont(new Font("����", Font.BOLD, 20));
		myName.append(u.getName());
		northPanel.add(myName);

		JLabel changeZiLiao = new JLabel("\u7F16\u8F91\u8D44\u6599");
		changeZiLiao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				changeZiLiao.setFont(new Font("����", Font.PLAIN, 17));
				changeZiLiao.setForeground(SystemColor.BLACK);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				changeZiLiao.setForeground(Color.LIGHT_GRAY);
				changeZiLiao.setFont(new Font("����", Font.PLAIN, 15));
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				new ChangeUserInformation(u);
			}
		});
		changeZiLiao.setForeground(Color.BLACK);
		changeZiLiao.setFont(new Font("����", Font.PLAIN, 15));
		changeZiLiao.setHorizontalAlignment(SwingConstants.LEFT);
		changeZiLiao.setBounds(302, 0, 71, 27);
		northPanel.add(changeZiLiao);

		JPanel centerPanel = new JPanel(); // ---------------------------------------------�м�-------------------------------------------------------------
		centerPanel.setBounds(0, 112, 373, 486);
		centerPanel.setOpaque(false);
		centerPanel.setBorder(null);
		mainPanel.add(centerPanel);
		centerPanel.setLayout(null);

		UIManager.put("TabbedPane.contentOpaque", false); // ----------���� �� Ⱥ----------------
		JTabbedPane threeOptions = new JTabbedPane(JTabbedPane.TOP);
		threeOptions.setBounds(0, 0, 373, 486);
		threeOptions.setOpaque(false);
		threeOptions.setFont(new Font("����", Font.BOLD, 15));
		threeOptions.setBorder(null);
		threeOptions.setBackground(null);
		centerPanel.add(threeOptions);

		JPanel myFriend = new JPanel(); // ��ϵ��
		myFriend.setOpaque(false);
		threeOptions.addTab("  �� ϵ ��   ", null, myFriend, "�ҵĺ���");
		myFriend.setLayout(null);

		PopupMenu friendPm = new PopupMenu();
		MenuItem friendMI1 = new MenuItem("���ͼ�ʱ��Ϣ");
		friendPm.add(friendMI1);
		MenuItem friendMI2 = new MenuItem("ɾ������");
		friendPm.add(friendMI2);
		MenuItem friendMI3 = new MenuItem("�鿴��������");
		friendPm.add(friendMI3);

		friendRoot = new FriendNode(u.getHead(), u.getName(), u.getText(), u.getStatus(), u.getAcn(), u);
		flashFriendList_friends();
		friendModel = new DefaultTreeModel(friendRoot);
		friends = new JTree(friendRoot);
		friends.setRootVisible(false);
		friends.putClientProperty("JTree.lineStyle", "Horizontal");
		friends.setFont(new Font("����", Font.PLAIN, 20));
		friends.setForeground(Color.BLACK);
		friends.setRowHeight(80);
		friends.setCellRenderer(new FriendNodeRender());
		friends.setOpaque(false);
		ActionListener friendMenuListener = e -> {
			String cmd = e.getActionCommand();
			if (cmd.equals("���ͼ�ʱ��Ϣ")) {
				FriendNode f = (FriendNode) friends.getLastSelectedPathComponent();
				new Thread(new QQChat(u, f.getU())).start();
			} else if(cmd.equals("ɾ������")){
				if (JOptionPane.showConfirmDialog(null, "ȷ��ɾ���ú��ѣ�", "����", JOptionPane.OK_CANCEL_OPTION) == 0) {
					FriendNode f = (FriendNode) friends.getLastSelectedPathComponent();
					u.setDeleteFriend(true);
					u.setTheDeleteFriend(f.getAcn());
					new ClientCheck().checkDeleteFriend(u);
					u.setDeleteFriend(false);
					
					try {
						Thread.sleep(30);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					flash(1);
				}
			}else {
				FriendNode f = (FriendNode) friends.getLastSelectedPathComponent();
				new CheckUserInformation(f.getU());
			}
		};
		friendMI1.addActionListener(friendMenuListener);
		friendMI2.addActionListener(friendMenuListener);
		friendMI3.addActionListener(friendMenuListener);
		friends.add(friendPm);
		friends.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1 && e.getClickCount() == 2) {
					TreePath path = friends.getPathForLocation(e.getX(), e.getY());

					if (path == null) { // JTree��û���κ��ѡ��

						return;

					}

					FriendNode f = (FriendNode) friends.getLastSelectedPathComponent();
					new Thread(new QQChat(u, f.getU())).start();
				} else if (e.getButton() == 3 && e.getClickCount() == 1) {
					TreePath path = friends.getPathForLocation(e.getX(), e.getY());

					if (path == null) { // JTree��û���κ��ѡ��

						return;

					}

					friends.setSelectionPath(path);
					friendPm.show(friends, e.getX(), e.getY());

				}
			}

		});

		myFriendPanel = new JScrollPane(friends);
		myFriendPanel.setOpaque(false);
		myFriendPanel.getViewport().setOpaque(false);
		myFriendPanel.setBounds(0, 0, 368, 455);
		myFriend.add(myFriendPanel);

		JPanel chat = new JPanel(); // Ⱥ��
		chat.setOpaque(false);
		threeOptions.addTab("  Ⱥ ��  ", null, chat, "�ҵ�Ⱥ��");
		chat.setLayout(null);

		PopupMenu groupPm = new PopupMenu();
		MenuItem groupMI1 = new MenuItem("����Ⱥ��Ϣ");
		groupPm.add(groupMI1);
		MenuItem groupMI2 = new MenuItem("�˳�Ⱥ��");
		groupPm.add(groupMI2);
		MenuItem groupMI3 = new MenuItem("�鿴Ⱥ����");
		groupPm.add(groupMI3);

		groupRoot = new FriendNode(u.getHead(), u.getName(), u.getText(), u.getAcn());
		flashFriendList_groups();
		groupModel = new DefaultTreeModel(groupRoot);
		myGroups = new JTree(groupRoot);
		myGroups.setRootVisible(false);
		myGroups.putClientProperty("JTree.lineStyle", "Horizontal");
		myGroups.setFont(new Font("����", Font.PLAIN, 20));
		myGroups.setRowHeight(80);
		myGroups.setCellRenderer(new FriendNodeRender());
		myGroups.setOpaque(false);
		ActionListener groupMenuListener = e -> {
			String cmd = e.getActionCommand();
			if (cmd.equals("����Ⱥ��Ϣ")) {
				FriendNode f = (FriendNode) myGroups.getLastSelectedPathComponent();
				new Thread(new QQGroupChat(u, f.getGroup())).start();
			} else if(cmd.equals("�˳�Ⱥ��")){
				FriendNode f = (FriendNode) myGroups.getLastSelectedPathComponent();
				ChatGroup theDeleteGroup = f.getGroup();
				if (!theDeleteGroup.getGroup_owner().equals(u.getAcn())) {
					if (JOptionPane.showConfirmDialog(null, "ȷ���˳���Ⱥ�ģ�", "����", JOptionPane.OK_CANCEL_OPTION) == 0) {
						u.setDeleteGroup(true);
						u.setTheDeleteGroup(theDeleteGroup.getAcn());
						new ClientCheck().checkDeleteGroup(u);
						u.setDeleteGroup(false);
					}
				} else {
					if (JOptionPane.showConfirmDialog(null, "Ⱥ��ȷ����ɢ��Ⱥ�ģ�", "����", JOptionPane.OK_CANCEL_OPTION) == 0) {
						u.setDeleteGroup(true);
						u.setTheDeleteGroup(theDeleteGroup.getAcn());
						new ClientCheck().checkDeleteGroup(u);
						u.setDeleteGroup(false);
						
						try {
							Thread.sleep(30);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						flash(2);
					}
				}
			}else {
				boolean flag = false;
				FriendNode f = (FriendNode) myGroups.getLastSelectedPathComponent();
				ChatGroup theSearchGroup = f.getGroup();
				
				if(theSearchGroup.getGroup_owner().equals(u.getAcn())) { // Ⱥ��
					flag = true;
				}
				
				if(!flag) { // ����Ա
					String[] adm = theSearchGroup.getAdm().split("_");
					int len = adm.length;
					for(int i = 0; i < len; i++) {
						if(adm[i].equals(u.getAcn())) {
							flag = true;
							break;
						}
					}
				}
				
				if(flag) {
					new ChangeGroupImformation(theSearchGroup);
				}else {
					new CheckGroupInformation(theSearchGroup);
				}
			}
		};
		groupMI1.addActionListener(groupMenuListener);
		groupMI2.addActionListener(groupMenuListener);
		groupMI3.addActionListener(groupMenuListener);
		myGroups.add(groupPm);
		myGroups.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1 && e.getClickCount() == 2) {
					TreePath path = myGroups.getPathForLocation(e.getX(), e.getY());

					if (path == null) { // JTree��û���κ��ѡ��

						return;

					}

					FriendNode f = (FriendNode) myGroups.getLastSelectedPathComponent();

					new Thread(new QQGroupChat(u, f.getGroup())).start();
				} else if (e.getButton() == 3 && e.getClickCount() == 1) {
					TreePath path = myGroups.getPathForLocation(e.getX(), e.getY());

					if (path == null) { // JTree��û���κ��ѡ��

						return;

					}

					myGroups.setSelectionPath(path);
					groupPm.show(myGroups, e.getX(), e.getY());
				}
			}
		});
		qun = new JScrollPane(myGroups);
		qun.setOpaque(false);
		qun.getViewport().setOpaque(false);
		qun.setBounds(0, 0, 368, 455);
		chat.add(qun);

		JPanel function = new JPanel(); // ����
		function.setOpaque(false);
		threeOptions.addTab("  ����  ", null, function, null);
		function.setLayout(null);

		JPanel functionPanel = new JPanel();
		functionPanel.setOpaque(false);
		functionPanel.setBounds(0, 0, 368, 455);
		function.add(functionPanel);

		JButton flash = new JButton("\u5237\u65B0");
		flash.setBorder(null);
		flash.setForeground(Color.BLACK);
		flash.setFont(new Font("����", Font.BOLD, 16));
		flash.setOpaque(false);
		flash.setBackground(Color.WHITE);
		flash.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				friendRoot.removeAllChildren();
				flashFriendList_friends();
				friendModel.reload();

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						friends.updateUI();
					}
				});

				groupRoot.removeAllChildren();
				flashFriendList_groups();
				groupModel.reload();

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						myGroups.updateUI();
					}
				});
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				flash.setFont(new Font("����", Font.BOLD, 16));
				flash.setBounds(146, 5, 75, 35);
				flash.setBorder(BorderFactory.createRaisedBevelBorder());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				flash.setFont(new Font("����", Font.BOLD, 14));
				flash.setBounds(151, 10, 65, 25);
				flash.setBorder(null);
			}
		});
		functionPanel.setLayout(null);
		flash.setBounds(151, 10, 65, 25);
		functionPanel.add(flash);

		groupName = new JTextField();
		groupName.setEditable(false);
		groupName.setEnabled(false);
		groupName.setOpaque(false);
		groupName.setFont(new Font("����", Font.BOLD, 18));
		groupName.setBounds(95, 200, 255, 35);
		functionPanel.add(groupName);
		groupName.setColumns(10);

		JLabel name = new JLabel("");
		name.setFont(new Font("����", Font.BOLD, 18));
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setBounds(0, 200, 65, 35);
		functionPanel.add(name);

		JLabel text = new JLabel("");
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setFont(new Font("����", Font.BOLD, 18));
		text.setBounds(0, 261, 64, 35);
		functionPanel.add(text);

		JTextArea groupText = new JTextArea();
		groupText.setEnabled(false);
		groupText.setOpaque(false);
		groupText.setFont(new Font("����", Font.PLAIN, 17));
		groupText.setBounds(10, 306, 345, 139);
		functionPanel.add(groupText);

		JLabel acn = new JLabel("");
		acn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!queding.getText().equals("")) {
					groupAcn.setText(MakeCaptcha.makeCapcha());
				} else {
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (!queding.getText().equals("")) {
					acn.setBorder(BorderFactory.createRaisedBevelBorder());
				} else {
					acn.setBorder(null);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				acn.setBorder(null);
			}
		});
		acn.setHorizontalAlignment(SwingConstants.CENTER);
		acn.setFont(new Font("����", Font.BOLD, 18));
		acn.setBounds(0, 140, 120, 35);
		functionPanel.add(acn);

		groupAcn = new JTextField();
		groupAcn.setEditable(false);
		groupAcn.setEnabled(false);
		groupAcn.setOpaque(false);
		groupAcn.setFont(new Font("����", Font.BOLD, 18));
		groupAcn.setColumns(10);
		groupAcn.setBounds(150, 140, 200, 35);
		functionPanel.add(groupAcn);

		JLabel head = new JLabel();
		head.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!queding.getText().equals("")) {
					JFileChooser jc = new JFileChooser(); // �ļ�ѡ���
					jc.setDialogTitle("��ѡ��ͼƬ�ļ�");
					int click = jc.showOpenDialog(null);// ����ѡ��򣬷���ֵ���������Ĳ���
					if (JFileChooser.APPROVE_OPTION == click) { // ѡ�����ļ���ȷ��
						head.setText("");
						String path = jc.getSelectedFile().getPath(); // ��õ�һ���ļ�����ֵ
						ImageIcon newHead = new ImageIcon(path);
						newHead.setImage(newHead.getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT));
						head.setIcon(newHead);
						head.setFont(new Font("����", Font.BOLD, 1));
						head.setText(path);
					}
				} else {

				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (!queding.getText().equals("")) {
					head.setBorder(BorderFactory.createLoweredBevelBorder());
				} else {
					head.setBorder(null);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!queding.getText().equals("")) {
					head.setBorder(BorderFactory.createRaisedBevelBorder());
				} else {
					head.setBorder(null);
				}
			}
		});
		head.setHorizontalAlignment(SwingConstants.CENTER);
		head.setBounds(151, 60, 65, 65);
		functionPanel.add(head);

		queding = new JLabel("");
		queding.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!queding.getText().equals("")) {
					if (head.getText().equals("����ͷ��") || groupName.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "ͷ���Ⱥ�ǳƲ���Ϊ��", "��ܰ��ʾ", 2,
								new ImageIcon("F:\\Myeclipseproject\\QQ��Ŀ\\ͼƬ\\��������.jpg"));
					} else {

						ChatGroup g = new ChatGroup();
						g.setGroup_owner(u.getAcn());
						g.setMember_num(1);

						g.setHead(BytesToPath.getBytes(head.getText()));
						head.setText("");
						head.setBorder(null);
						head.setIcon(null);

						g.setName(groupName.getText());
						name.setText("");
						groupName.setEditable(false);
						groupName.setEnabled(false);
						groupName.setText("");

						g.setAcn(groupAcn.getText());
						acn.setText("");
						groupAcn.setText("");
						groupAcn.setEditable(false);

						g.setText(groupText.getText());
						text.setText("");
						groupText.setEnabled(false);
						groupText.setText("");

						queding.setText("");
						queding.setBorder(null);

						u.setCreatGroup(true);
						u.setTheCreatGroup(g);
						new ClientCheck().checkCreatGroup(u);
						u.setCreatGroup(false);
						
						try {
							Thread.sleep(30);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						flash(2);
					}

				} else {

				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (!queding.getText().equals("")) {
					queding.setFont(new Font("����", Font.BOLD, 15));
					queding.setBounds(307, 266, 48, 35);
					queding.setBorder(BorderFactory.createRaisedBevelBorder());
				} else {
					queding.setFont(new Font("����", Font.BOLD, 13));
					queding.setBounds(312, 271, 38, 25);
					queding.setBorder(null);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				queding.setFont(new Font("����", Font.BOLD, 13));
				queding.setBounds(312, 271, 38, 25);
				queding.setBorder(null);
			}
		});
		queding.setFont(new Font("����", Font.BOLD, 13));
		queding.setHorizontalAlignment(SwingConstants.CENTER);
		queding.setBounds(312, 271, 38, 25);
		functionPanel.add(queding);

		JLabel quxiao = new JLabel("");
		quxiao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!queding.getText().equals("")) {

					head.setText("");
					head.setBorder(null);
					head.setIcon(null);

					name.setText("");
					groupName.setEditable(false);
					groupName.setEnabled(false);
					groupName.setText("");

					acn.setText("");
					groupAcn.setText("");
					groupAcn.setEditable(false);

					text.setText("");
					groupText.setEnabled(false);
					groupText.setText("");

					quxiao.setText("");
					quxiao.setBorder(null);

					queding.setText("");

				} else {

				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (!quxiao.getText().equals("")) {
					quxiao.setFont(new Font("����", Font.BOLD, 15));
					quxiao.setBounds(230, 266, 48, 35);
					quxiao.setBorder(BorderFactory.createRaisedBevelBorder());
				} else {
					quxiao.setFont(new Font("����", Font.BOLD, 13));
					quxiao.setBounds(235, 271, 38, 25);
					quxiao.setBorder(null);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quxiao.setFont(new Font("����", Font.BOLD, 13));
				quxiao.setBounds(235, 271, 38, 25);
				quxiao.setBorder(null);
			}
		});
		quxiao.setHorizontalAlignment(SwingConstants.CENTER);
		quxiao.setFont(new Font("����", Font.BOLD, 13));
		quxiao.setBounds(235, 271, 38, 25);
		functionPanel.add(quxiao);

		JLabel buildGroup = new JLabel("\u521B \u5EFA \u7FA4 \u804A");
		buildGroup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (queding.getText().equals("")) {
					head.setText("����ͷ��");
					head.setBorder(BorderFactory.createRaisedBevelBorder());

					name.setText("Ⱥ�ǳ�");
					groupName.setEditable(true);
					groupName.setEnabled(true);

					acn.setText("���Ⱥ�˺�");
					groupAcn.setEditable(true);

					text.setText("Ⱥ����");
					groupText.setEnabled(true);

					quxiao.setText("ȡ��");

					queding.setText("ȷ��");
					System.out.println("��Ⱥ");
				} else {

				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				buildGroup.setFont(new Font("����", Font.BOLD, 15));
				buildGroup.setBorder(BorderFactory.createRaisedBevelBorder());
				buildGroup.setBounds(5, 45, 100, 35);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				buildGroup.setFont(new Font("����", Font.BOLD, 13));
				buildGroup.setBorder(null);
				buildGroup.setBounds(10, 50, 90, 25);
			}
		});
		buildGroup.setFont(new Font("����", Font.BOLD, 13));
		buildGroup.setHorizontalAlignment(SwingConstants.CENTER);
		buildGroup.setBounds(10, 50, 90, 25);
		functionPanel.add(buildGroup);

		JPanel southPanel = new JPanel(); // --------------------------------------------���·�--------------------------------------------------------------
		southPanel.setBounds(0, 598, 373, 43);
		southPanel.setOpaque(false);
		southPanel.setBorder(null);
		mainPanel.add(southPanel);
		southPanel.setLayout(null);

		JButton addFriend = new JButton("+NEW"); // �Ӻ��ѻ�Ⱥ
		addFriend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				addFriend.setForeground(Color.BLACK);
				addFriend.setFont(new Font("����", Font.BOLD | Font.ITALIC, 24));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				addFriend.setForeground(Color.GRAY);
				addFriend.setFont(new Font("����", Font.BOLD | Font.ITALIC, 20));
			}
		});
		addFriend.setOpaque(false);
		addFriend.setBackground(Color.WHITE);
		addFriend.setBorderPainted(false);
		addFriend.setForeground(Color.BLACK);
		addFriend.setFont(new Font("����", Font.BOLD | Font.ITALIC, 20));
		addFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SearchUserGroup(u);
			}
		});
		addFriend.setBounds(0, 0, 187, 43);
		southPanel.add(addFriend);

		JButton newBack = new JButton("Background"); // �����б���

		newBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				newBack.setForeground(Color.BLACK);
				newBack.setFont(new Font("����", Font.BOLD | Font.ITALIC, 24));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				newBack.setForeground(Color.BLACK);
				newBack.setFont(new Font("����", Font.BOLD | Font.ITALIC, 20));
			}
		});
		newBack.setOpaque(false);
		newBack.setBackground(Color.WHITE);
		newBack.setBorderPainted(false);
		newBack.setForeground(Color.BLACK);
		newBack.setFont(new Font("����", Font.BOLD | Font.ITALIC, 20));
		newBack.setBounds(186, 0, 187, 43);
		southPanel.add(newBack);

		ImageIcon back = new ImageIcon(u.getBack());
		back.setImage(back.getImage().getScaledInstance(373, 641, Image.SCALE_DEFAULT));
		JLabel background = new JLabel(back);
		background.setBounds(0, 0, 373, 641);
		getContentPane().add(background);

		newBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jc = new JFileChooser(); // �ļ�ѡ���
				jc.setDialogTitle("��ѡ��ͼƬ�ļ�");
				int click = jc.showOpenDialog(null);// ����ѡ��򣬷���ֵ���������Ĳ���
				if (JFileChooser.APPROVE_OPTION == click) { // ѡ�����ļ���ȷ��
					String path = jc.getSelectedFile().getPath(); // ��õ�һ���ļ�����ֵ
					ImageIcon newBack = new ImageIcon(path);
					newBack.setImage(newBack.getImage().getScaledInstance(373, 641, Image.SCALE_DEFAULT));
					background.setIcon(newBack);

					u.setChangeBack(true);
					u.setHead(BytesToPath.getBytes(path));
					new ClientCheck().checkChangeHead(u);
					u.setChangeBack(false);
				}
			}
		});

		setVisible(true);

	}

	public static void setNewName(String newName) {
		myName.setText(newName);
	} // ��������

	public void addNewFriend(User newFriend) {

	} // �º��Ѽ����б�

	public void addNewGroup(ChatGroup newGroup) {

	} // ��Ⱥ�ļ����б�

	public static void flashFriendList_friends() {
		u.setFriends(true);
		users = new ClientCheck().checkFriends(u);
		u.setFriends(false);

		if (null != users && null != users[0]) {

			for (int i = 0; null != users[i]; i++) {
				FriendNode node = new FriendNode(users[i].getHead(), users[i].getName(), users[i].getText(),
						users[i].getStatus(), users[i].getAcn(), users[i]);
				friendRoot.add(node);
			}

		}
	} // ˢ�º����б�_������

	public static void flashFriendList_groups() {
		u.setGroups(true);
		groups = new ClientCheck().checkGroups(u);
		u.setGroups(false);

		if (null != groups && null != groups[0]) {

			for (int i = 0; null != groups[i]; i++) {
				FriendNode node = new FriendNode(groups[i].getHead(), groups[i].getName(),
						"(" + groups[i].getMember_num() + "��)", groups[i].getAcn(), groups[i]);
				groupRoot.add(node);
			}
		}
	} // ˢ�º����б�_Ⱥ����

	public static void flash(int mark) {
		if (mark == 1) {
			friendRoot.removeAllChildren();
			flashFriendList_friends();
			friendModel.reload();

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					friends.updateUI();
				}
			});
		} else {
			groupRoot.removeAllChildren();
			flashFriendList_groups();
			groupModel.reload();

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					myGroups.updateUI();
				}
			});
		}
	}

	@Override
	public void run() {

	}
}
