package client.qq.ppy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import tools.qq.ppy.JudgeName;
import tools.qq.ppy.JudgeYearMonthDay;
import user.qq.ppy.User;

public class ChangeUserInformation extends JFrame {

	private JPanel contentPane;
	private JTextField xingbie;
	private JTextField xuexing;
	private JTextField year;
	private JTextField month;
	private JTextField day;
	private User u;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ChangeUserInformation(User u) {
		this.u = u;
		String[] birthday = u.getBirthday().split("_");
		
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h =	Toolkit.getDefaultToolkit().getScreenSize().height;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((w - 387) / 2, (h - 530) / 2, 387, 530);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\资料账号.png"));
		lblNewLabel.setBounds(10, 114, 33, 26);
		contentPane.add(lblNewLabel);
		
		JLabel yourACN = new JLabel(u.getAcn()); // 你的账号
		yourACN.setVerticalAlignment(SwingConstants.TOP);
		yourACN.setHorizontalAlignment(SwingConstants.LEFT);
		yourACN.setFont(new Font("宋体", Font.BOLD, 19));
		yourACN.setBounds(54, 114, 149, 26);
		contentPane.add(yourACN);
		
		ImageIcon touxiang = new ImageIcon(u.getHead());
		touxiang.setImage(touxiang.getImage().getScaledInstance(84,84,Image.SCALE_DEFAULT));
		JLabel lblNewLabel_2 = new JLabel(touxiang);
		lblNewLabel_2.setBounds(0, 0, 84, 84);
		contentPane.add(lblNewLabel_2);
		
		JTextArea yourName = new JTextArea();
		yourName.setEnabled(false);
		yourName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				yourName.setEnabled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				yourName.setEnabled(false);
			}
		});
		yourName.setForeground(Color.LIGHT_GRAY);
		yourName.setFont(new Font("宋体", Font.BOLD, 25));
		yourName.setText(u.getName());
		yourName.setBounds(112, 22, 234, 34);
		contentPane.add(yourName);
		
		JLabel lblNewLabel_3 = new JLabel(new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\资料性别.png"));
		lblNewLabel_3.setBounds(10, 161, 33, 26);
		contentPane.add(lblNewLabel_3);
		
		String[] yourSex = {"", "男", "女"};
		JComboBox<Object> yourSEX = new JComboBox<Object>(yourSex); // 你的性别
		yourSEX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				xingbie.setText((String) yourSEX.getSelectedItem());
			}
		});
		yourSEX.setForeground(Color.BLACK);
		yourSEX.setFont(new Font("宋体", Font.BOLD, 17));
		yourSEX.setOpaque(false);
		yourSEX.setBackground(Color.WHITE);
		yourSEX.setBounds(131, 161, 20, 21);
		//comboBox.add(yourSex);
		contentPane.add(yourSEX);
		
		JLabel lblNewLabel_3_1 = new JLabel(new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\资料生日.png"));
		lblNewLabel_3_1.setBounds(10, 203, 33, 26);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel(new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\资料血型.png"));
		lblNewLabel_3_1_1.setBounds(10, 244, 33, 26);
		contentPane.add(lblNewLabel_3_1_1);
		
		String[] yourBlood = {"", "O", "A", "B", "AB"}; // 你的血型
		JComboBox<Object> yourBLOOD = new JComboBox<Object>(yourBlood);
		yourBLOOD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuexing.setText((String) yourBLOOD.getSelectedItem());
			}
		});
		yourBLOOD.setFont(new Font("宋体", Font.BOLD, 12));
		yourBLOOD.setOpaque(false);
		yourBLOOD.setBackground(Color.WHITE);
		yourBLOOD.setBounds(131, 244, 20, 21);
		contentPane.add(yourBLOOD);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel(new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\资料邮箱.png"));
		lblNewLabel_3_1_1_1.setBounds(10, 285, 33, 26);
		contentPane.add(lblNewLabel_3_1_1_1);
		
		JLabel yourEMAIL = new JLabel(u.getEmail());
		yourEMAIL.setVerticalAlignment(SwingConstants.TOP);
		yourEMAIL.setHorizontalAlignment(SwingConstants.LEFT);
		yourEMAIL.setFont(new Font("宋体", Font.BOLD, 19));
		yourEMAIL.setBounds(54, 285, 307, 26);
		contentPane.add(yourEMAIL);
		
		xingbie = new JTextField(u.getSex());
		xingbie.setEnabled(false);
		xingbie.setFont(new Font("宋体", Font.BOLD, 16));
		xingbie.setText(u.getSex());
		xingbie.setBounds(54, 161, 66, 21);
		contentPane.add(xingbie);
		xingbie.setColumns(10);
		
		xuexing = new JTextField(u.getBlood());
		xuexing.setEnabled(false);
		xuexing.setFont(new Font("宋体", Font.BOLD, 16));
		xuexing.setText(u.getBlood());
		xuexing.setColumns(10);
		xuexing.setBounds(54, 244, 66, 21);
		contentPane.add(xuexing);
		
		year = new JTextField(birthday[0]); // 年
		year.setEnabled(false);
		year.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				year.setEnabled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				year.setEnabled(false);
			}
		});
		year.setHorizontalAlignment(SwingConstants.CENTER);
		year.setFont(new Font("宋体", Font.BOLD, 14));
		year.setColumns(10);
		year.setBounds(54, 203, 39, 21);
		contentPane.add(year);
		
		month = new JTextField(birthday[1]); // 月
		month.setEnabled(false);
		month.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				month.setEnabled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				month.setEnabled(false);
			}
		});
		month.setHorizontalAlignment(SwingConstants.CENTER);
		month.setFont(new Font("宋体", Font.BOLD, 14));
		month.setColumns(10);
		month.setBounds(103, 203, 33, 21);
		contentPane.add(month);
		
		day = new JTextField(birthday[2]); // 日
		day.setEnabled(false);
		day.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				day.setEnabled(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				day.setEnabled(false);
			}
		});
		day.setHorizontalAlignment(SwingConstants.CENTER);
		day.setFont(new Font("宋体", Font.BOLD, 14));
		day.setColumns(10);
		day.setBounds(146, 203, 33, 21);
		contentPane.add(day);
		
		JButton sureCHANGE = new JButton("\u786E\u8BA4\u4FEE\u6539");
		sureCHANGE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String error = "";
				
				if(null == yourName || yourName.getText().length() == 0 || !JudgeName.judgeName(yourName.getText())) {
					error += "昵称不能为空\n";
				}
				
				if(!JudgeYearMonthDay.judge(year.getText(), month.getText(), day.getText())) {
					error += "生日输入有误";
				}
				
				if(null == error || error.length() == 0) {
					u.setChangeInformation(true);
					u.setName(yourName.getText());
					u.setBirthday(year.getText() + "_" + month.getText() + "_" + day.getText());
					u.setSex(xingbie.getText());
					u.setBlood(xuexing.getText());
					new ClientCheck().checkChangeInformation(u);
					
					u.setChangeInformation(false);
					FriendList.setNewName(yourName.getText());
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, error, "温馨提示", 2, new ImageIcon("F:\\Myeclipseproject\\QQ项目\\图片\\！！！！.jpg"));
				}
			}
		});
		sureCHANGE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sureCHANGE.setFont(new Font("宋体", Font.BOLD, 18));
				sureCHANGE.setBounds(49, 387, 267, 44);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sureCHANGE.setFont(new Font("宋体", Font.BOLD, 15));
				sureCHANGE.setBounds(54, 392, 257, 34);
			}
		});
		sureCHANGE.setBackground(SystemColor.textHighlight);
		sureCHANGE.setFont(new Font("宋体", Font.BOLD, 15));
		sureCHANGE.setBounds(54, 392, 257, 34);
		contentPane.add(sureCHANGE);
		setVisible(true);
	}
}
