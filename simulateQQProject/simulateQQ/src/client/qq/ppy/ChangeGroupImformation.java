package client.qq.ppy;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import tools.qq.ppy.BytesToPath;
import tools.qq.ppy.JudgeName;
import user.qq.ppy.ChatGroup;
import user.qq.ppy.User;

public class ChangeGroupImformation extends JFrame {

	private JPanel contentPane;
	private User[] users;

	public ChangeGroupImformation(ChatGroup group) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 733);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon touxiang = new ImageIcon(group.getHead());
		touxiang.setImage(touxiang.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
		JLabel groupHead = new JLabel(touxiang); // 群头像
		groupHead.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser jc = new JFileChooser(); // 文件选择框
				jc.setDialogTitle("请选择图片文件");
				int click = jc.showOpenDialog(null);// 弹出选择框，返回值代表你鼠标的操作
				if (JFileChooser.APPROVE_OPTION == click) { // 选择了文件并确定
					String path = jc.getSelectedFile().getPath(); // 会得到一个文件类型值
					ImageIcon newHead = new ImageIcon(path);
					newHead.setImage(newHead.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
					groupHead.setFont(new Font("宋体", Font.BOLD, 0));
					groupHead.setText(path);
					groupHead.setIcon(newHead);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				groupHead.setBorder(BorderFactory.createLoweredBevelBorder());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				groupHead.setBorder(BorderFactory.createRaisedBevelBorder());
			}
		});
		groupHead.setBorder(BorderFactory.createRaisedBevelBorder());
		groupHead.setBounds(142, 10, 120, 120);
		contentPane.add(groupHead);
		
		JLabel groupNmae = new JLabel("   \u7FA4\u6635\u79F0 :"); // 群昵称
		groupNmae.setFont(new Font("宋体", Font.BOLD, 17));
		groupNmae.setForeground(Color.LIGHT_GRAY);
		groupNmae.setBounds(0, 165, 110, 37);
		contentPane.add(groupNmae);
		
		JLabel groupACN = new JLabel("   \u7FA4\u53F7\u7801 :"); // 群号码
		groupACN.setForeground(Color.LIGHT_GRAY);
		groupACN.setFont(new Font("宋体", Font.BOLD, 17));
		groupACN.setBounds(0, 227, 110, 37);
		contentPane.add(groupACN);
		
		JTextArea name = new JTextArea(group.getName());
		name.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				name.setEnabled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				name.setEnabled(false);
			}
		});
		name.setEnabled(false);
		name.setForeground(Color.BLACK);
		name.setFont(new Font("宋体 Light", Font.BOLD, 17));
		name.setBounds(119, 165, 286, 37);
		contentPane.add(name);
		
		JLabel acn = new JLabel(group.getAcn());
		acn.setForeground(Color.BLACK);
		acn.setFont(new Font("宋体 Light", Font.BOLD, 17));
		acn.setBounds(119, 227, 286, 37);
		contentPane.add(acn);
		
		JLabel manager = new JLabel("   \u7FA4\u4E3B/\u7BA1\u7406\u5458:");
		manager.setForeground(Color.LIGHT_GRAY);
		manager.setFont(new Font("宋体", Font.BOLD, 17));
		manager.setBounds(0, 289, 145, 37);
		contentPane.add(manager);
		
		JPanel groupMenbers = null;
		users = group.getMembers();
		if(null != users && null != users[0]) {
			
			int len = group.getMember_num();
			int num = 0;
			for(int i = 0; i < len; i++) {
				if(users[i].getRank() > 0) {
					num++;
				}
			}
			
			JLabel[] jls = new JLabel[num];
			JLabel[] jls1 = new JLabel[num];
			groupMenbers = new JPanel(new GridLayout(num, 2)); // 显示群友
			groupMenbers.setBackground(Color.WHITE);
			int j = 0;
			for(int i = 0; i < len; i++) {
				
				if(users[i].getRank() == 2) {
					jls[j] = new JLabel(users[i].getName());
					ImageIcon ii = new ImageIcon(users[i].getHead());
					ii.setImage(ii.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
					jls[j].setIcon(ii);
					ImageIcon ii1 = new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\群主.png");
					jls1[j] = new JLabel(ii1);
					groupMenbers.add(jls[j]);
					groupMenbers.add(jls1[j]);
					j++;
				}
				else if(users[i].getRank() == 1) {
					jls[j] = new JLabel(users[i].getName());
					ImageIcon ii = new ImageIcon(users[i].getHead());
					ii.setImage(ii.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
					jls[j].setIcon(ii);
					ImageIcon ii1 = new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\管理员.png");
					jls1[j] = new JLabel(ii1);
					groupMenbers.add(jls[j]);
					groupMenbers.add(jls1[j]);
					j++;
				}
				
			}
		}
		
		JScrollPane managers = new JScrollPane(groupMenbers); // 群主/管理员
		managers.setBounds(20, 335, 362, 110);
		managers.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		contentPane.add(managers);
		
		JTextArea textArea = new JTextArea(); // 群介绍
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				textArea.setEnabled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				textArea.setEnabled(false);
			}
		});
		textArea.setEnabled(false);
		textArea.setFont(new Font("宋体 Light", Font.BOLD, 17));
		textArea.setBounds(20, 534, 362, 127);
		textArea.setLineWrap(true);
		textArea.setText(group.getText());
		contentPane.add(textArea);
		
		JLabel groupNmae_1 = new JLabel("   \u7FA4\u4ECB\u7ECD :");
		groupNmae_1.setForeground(Color.LIGHT_GRAY);
		groupNmae_1.setFont(new Font("宋体", Font.BOLD, 17));
		groupNmae_1.setBounds(0, 487, 110, 37);
		contentPane.add(groupNmae_1);
		
		JLabel queding = new JLabel("\u4FDD\u5B58");
		queding.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(null != groupHead.getText() && !groupHead.getText().equals("")) {
					group.setHead(BytesToPath.getBytes(groupHead.getText()));
				}
				
				group.setText(textArea.getText());
				
				if(null != name.getText() && !name.getText().equals("") && JudgeName.judgeName(name.getText())) {
					group.setName(name.getText());
					group.setUpdateGroup(true);
					new ClientCheck().checkUpdateGroup(group);
					group.setUpdateGroup(false);
					try {
						Thread.sleep(30);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					FriendList.flash(2);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "群昵称不能为空", "温馨提示", 2,
							new ImageIcon("F:\\Myeclipseproject\\QQ项目\\图片\\！！！！.jpg"));
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				queding.setFont(new Font("宋体", Font.BOLD, 21));
				queding.setBounds(285, 495, 60, 50);
				queding.setBorder(BorderFactory.createRaisedBevelBorder());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				queding.setFont(new Font("宋体", Font.BOLD, 19));
				queding.setBounds(290, 500, 50, 40);
				queding.setBorder(null);
			}
		});
		queding.setBorder(null);
		queding.setHorizontalAlignment(SwingConstants.CENTER);
		queding.setFont(new Font("宋体", Font.BOLD, 19));
		queding.setBounds(290, 500, 50, 40);
		contentPane.add(queding);
		
		setVisible(true);
	}
}
