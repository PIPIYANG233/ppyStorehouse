package client.qq.ppy;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import user.qq.ppy.ChatGroup;
import user.qq.ppy.User;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class CheckGroupInformation extends JFrame {

	private JPanel contentPane;
	private User[] users;

	public CheckGroupInformation(ChatGroup group) {
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
		groupHead.setBounds(142, 10, 120, 120);
		contentPane.add(groupHead);
		
		JLabel groupNmae = new JLabel("   \u7FA4\u6635\u79F0 :"); // 群昵称
		groupNmae.setFont(new Font("宋体", Font.BOLD, 15));
		groupNmae.setForeground(Color.BLACK);
		groupNmae.setBounds(0, 165, 110, 37);
		contentPane.add(groupNmae);
		
		JLabel groupACN = new JLabel("   \u7FA4\u53F7\u7801 :"); // 群号码
		groupACN.setForeground(Color.BLACK);
		groupACN.setFont(new Font("宋体", Font.BOLD, 15));
		groupACN.setBounds(0, 227, 110, 37);
		contentPane.add(groupACN);
		
		JLabel name = new JLabel(group.getName());
		name.setForeground(Color.BLACK);
		name.setFont(new Font("宋体 Light", Font.BOLD, 15));
		name.setBounds(119, 165, 286, 37);
		contentPane.add(name);
		
		JLabel acn = new JLabel(group.getAcn());
		acn.setForeground(Color.BLACK);
		acn.setFont(new Font("宋体 Light", Font.BOLD, 17));
		acn.setBounds(119, 227, 286, 37);
		contentPane.add(acn);
		
		JLabel manager = new JLabel("   \u7FA4\u4E3B/\u7BA1\u7406\u5458:");
		manager.setForeground(Color.BLACK);
		manager.setFont(new Font("宋体", Font.BOLD, 15));
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
		textArea.setFont(new Font("宋体 Light", Font.BOLD, 17));
		textArea.setEnabled(false);
		textArea.setBounds(20, 534, 362, 127);
		textArea.setLineWrap(true);
		textArea.setText(group.getText());
		contentPane.add(textArea);
		
		JLabel groupNmae_1 = new JLabel("   \u7FA4\u4ECB\u7ECD :");
		groupNmae_1.setForeground(Color.BLACK);
		groupNmae_1.setFont(new Font("宋体", Font.BOLD, 15));
		groupNmae_1.setBounds(0, 487, 110, 37);
		contentPane.add(groupNmae_1);
		
		setVisible(true);
	}
}
