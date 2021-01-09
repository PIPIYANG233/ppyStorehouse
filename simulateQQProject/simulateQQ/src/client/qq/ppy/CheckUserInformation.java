package client.qq.ppy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import user.qq.ppy.User;

public class CheckUserInformation extends JFrame {

	private JPanel contentPane;
	private JLabel xingbie;
	private JLabel xuexing;
	private JLabel year;
	private JLabel month;
	private JLabel day;
	private User searchUser;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public CheckUserInformation(User searchUser) {
		this.searchUser = searchUser;
		
		String[] birthday = this.searchUser.getBirthday().split("_");
		
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h =	Toolkit.getDefaultToolkit().getScreenSize().height;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((w - 387) / 2, (h - 430) / 2, 387, 430);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\资料账号.png"));
		lblNewLabel.setBounds(10, 114, 33, 26);
		contentPane.add(lblNewLabel);
		
		JLabel yourACN = new JLabel(this.searchUser.getAcn()); // 你的账号
		yourACN.setVerticalAlignment(SwingConstants.TOP);
		yourACN.setHorizontalAlignment(SwingConstants.LEFT);
		yourACN.setFont(new Font("宋体", Font.BOLD, 15));
		yourACN.setBounds(54, 114, 149, 26);
		contentPane.add(yourACN);
		
		ImageIcon touxiang = new ImageIcon(this.searchUser.getHead());
		touxiang.setImage(touxiang.getImage().getScaledInstance(84,84,Image.SCALE_DEFAULT));
		JLabel lblNewLabel_2 = new JLabel(touxiang);
		lblNewLabel_2.setBounds(0, 0, 84, 84);
		contentPane.add(lblNewLabel_2);
		
		JTextArea yourName = new JTextArea();
		yourName.setEnabled(false);
		yourName.setForeground(Color.BLACK);
		yourName.setFont(new Font("宋体", Font.BOLD, 15));
		yourName.setText(this.searchUser.getName());
		yourName.setBounds(112, 22, 234, 34);
		contentPane.add(yourName);
		
		JLabel lblNewLabel_3 = new JLabel(new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\资料性别.png"));
		lblNewLabel_3.setBounds(10, 161, 33, 26);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel(new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\资料生日.png"));
		lblNewLabel_3_1.setBounds(10, 203, 33, 26);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel(new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\资料血型.png"));
		lblNewLabel_3_1_1.setBounds(10, 244, 33, 26);
		contentPane.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel(new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\资料邮箱.png"));
		lblNewLabel_3_1_1_1.setBounds(10, 285, 33, 26);
		contentPane.add(lblNewLabel_3_1_1_1);
		
		JLabel yourEMAIL = new JLabel(this.searchUser.getEmail());
		yourEMAIL.setVerticalAlignment(SwingConstants.TOP);
		yourEMAIL.setHorizontalAlignment(SwingConstants.LEFT);
		yourEMAIL.setFont(new Font("宋体", Font.BOLD, 15));
		yourEMAIL.setBounds(54, 285, 307, 26);
		contentPane.add(yourEMAIL);
		
		xingbie = new JLabel(this.searchUser.getSex());
		xingbie.setFont(new Font("宋体", Font.BOLD, 15));
		xingbie.setText(this.searchUser.getSex());
		xingbie.setBounds(54, 161, 66, 21);
		contentPane.add(xingbie);
		
		xuexing = new JLabel(this.searchUser.getBlood());
		xuexing.setEnabled(false);
		xuexing.setFont(new Font("宋体", Font.BOLD, 15));
		xuexing.setText(this.searchUser.getBlood());
		xuexing.setBounds(54, 244, 66, 21);
		contentPane.add(xuexing);
		
		year = new JLabel(birthday[0]); // 年
		year.setEnabled(false);
		year.setHorizontalAlignment(SwingConstants.CENTER);
		year.setFont(new Font("宋体", Font.BOLD, 15));
		year.setBounds(54, 203, 39, 21);
		contentPane.add(year);
		
		month = new JLabel(birthday[1]); // 月
		month.setEnabled(false);
		month.setHorizontalAlignment(SwingConstants.CENTER);
		month.setFont(new Font("宋体", Font.BOLD, 14));
		month.setBounds(103, 203, 33, 21);
		contentPane.add(month);
		
		day = new JLabel(birthday[2]); // 日
		day.setEnabled(false);
		day.setHorizontalAlignment(SwingConstants.CENTER);
		day.setFont(new Font("宋体", Font.BOLD, 14));
		day.setBounds(146, 203, 33, 21);
		contentPane.add(day);
		setVisible(true);
	}

}
