package client.qq.ppy;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tools.qq.ppy.JudgeName;
import tools.qq.ppy.JudgePWD;
import user.qq.ppy.User;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JPasswordField pwd;
	private JPasswordField pwdAgain;
	private JLabel pwdJL;
	private JLabel pwdAgainJL;
	private JTextField email;
	private JLabel emailJL;
	private JTextField captcha;
	
	private String theACN;
	private String theName;
	private String thePWD;
	private String thePWDAgain;
	private String theEmail;
	private String theCaptcha;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Register() {
		setTitle("注册账号");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(1400, 100, 458, 806);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\QQ注册.png"));
		lblNewLabel.setBounds(0, 0, 134, 82);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\欢迎注册QQ.png"));
		lblNewLabel_1.setBounds(69, 67, 298, 162);
		contentPane.add(lblNewLabel_1);
		
		name = new JTextField(); // 昵称
		name.setBounds(69, 270, 300, 40);
		contentPane.add(name);
		name.setColumns(10);
		name.setCaretColor(Color.BLACK);
		name.setForeground(Color.BLACK);
		name.setFont(new Font("宋体", Font.BOLD, 20));
		
		JLabel nameJL = new JLabel("\u6635\u79F0");
		nameJL.setBounds(69, 219, 300, 46);
		contentPane.add(nameJL);
		nameJL.setFont(new Font("宋体", Font.BOLD, 22));
		nameJL.setForeground(Color.BLACK);

		
		pwd = new JPasswordField(); // 密码
		pwd.setBounds(69, 369, 300, 40);
		contentPane.add(pwd);
		pwd.setCaretColor(Color.BLACK);
		pwd.setForeground(Color.BLACK);
		pwd.setFont(new Font("宋体", Font.BOLD, 20));
		
		pwdJL = new JLabel("\u5BC6\u7801");
		pwdJL.setBounds(69, 319, 300, 40);
		contentPane.add(pwdJL);
		pwdJL.setFont(new Font("宋体", Font.BOLD, 22));
		pwdJL.setForeground(Color.BLACK);
		
		pwdAgain = new JPasswordField(); // 确认密码
		pwdAgain.setBounds(69, 467, 300, 40);
		contentPane.add(pwdAgain);
		pwdAgain.setCaretColor(Color.BLACK);
		pwdAgain.setForeground(Color.BLACK);
		pwdAgain.setFont(new Font("宋体", Font.BOLD, 20));
		
		pwdAgainJL = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		pwdAgainJL.setBounds(69, 417, 300, 40);
		contentPane.add(pwdAgainJL);
		pwdAgainJL.setFont(new Font("宋体", Font.BOLD, 22));
		pwdAgainJL.setForeground(Color.BLACK);
		
		email = new JTextField(); // 邮箱
		email.setBounds(69, 562, 300, 40);
		contentPane.add(email);
		email.setColumns(10);
		email.setCaretColor(Color.BLACK);
		email.setForeground(Color.BLACK);
		email.setFont(new Font("宋体", Font.BOLD, 20));
		
		emailJL = new JLabel("\u90AE\u7BB1");
		emailJL.setBounds(69, 516, 300, 40);
		contentPane.add(emailJL);
		emailJL.setFont(new Font("宋体", Font.BOLD, 22));
		emailJL.setForeground(Color.BLACK);
		
		JButton getCaptcha = new JButton("\u83B7\u53D6\u9A8C\u8BC1\u7801"); // 获取验证码
		getCaptcha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				getCaptcha.setFont(new Font("宋体", Font.BOLD, 16));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				getCaptcha.setFont(new Font("宋体", Font.BOLD, 14));
			}
		});
		getCaptcha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				theName = name.getText();
				thePWD = new String(pwd.getPassword());
				thePWDAgain = new String(pwdAgain.getPassword());
				theEmail = email.getText();
				
				if(null == theName || null == thePWD || null == thePWDAgain || null == theEmail || theName.length() == 0 || thePWD.length() == 0 || thePWDAgain.length() == 0 || theEmail.length() == 0) {
					JOptionPane.showMessageDialog(null, "昵称或密码或邮箱不能为空", "温馨提示", 2, new ImageIcon("F:\\Myeclipseproject\\QQ项目\\图片\\！！！！.jpg"));
				}
				else {
					if(!JudgeName.judgeName(theName) || !JudgePWD.judgePWD(thePWD)) {
						JOptionPane.showMessageDialog(null, "昵称不能为空\n密码 8~26位 只能由 数字/字母/小数点 组成", "温馨提示", 2, new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\汤姆.jpg"));
					}
					else {
						if(!thePWD.equals(thePWDAgain)) {
							JOptionPane.showMessageDialog(null, "密码不一致", "温馨提示", 2, new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\小新.gif"));
						}
						else {
							User u = new User();
							u.setReg(true);
							u.setName(theName);
							u.setPwd(thePWD);
							u.setEmail(theEmail);
							theCaptcha = new ClientCheck().checkReg(u);
							
							if(theCaptcha.length() == 0) {
								JOptionPane.showMessageDialog(null, "邮箱不正确或重复", "温馨提示", 2, new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\狗头招手.png"));
							}
							
							u.setReg(false);
						}
					}
				}
			}
		});
		getCaptcha.setBounds(246, 624, 119, 40);
		contentPane.add(getCaptcha);
		getCaptcha.setFont(new Font("宋体", Font.BOLD, 14));
		getCaptcha.setForeground(SystemColor.textHighlight);
		getCaptcha.setBackground(new Color(204, 255, 255));
		getCaptcha.setBorderPainted(false);
		getCaptcha.setOpaque(false);
		
		captcha = new JTextField();
		captcha.setBounds(69, 624, 167, 40);
		contentPane.add(captcha);
		captcha.setColumns(10);
		captcha.setCaretColor(Color.BLACK);
		captcha.setForeground(Color.BLACK);
		captcha.setFont(new Font("宋体", Font.BOLD, 20));
		
		JButton finishReg = new JButton("\u5B8C\u6210\u6CE8\u518C"); // 完成注册
		finishReg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				finishReg.setBounds(59, 686, 320, 60);
				finishReg.setFont(new Font("宋体", Font.BOLD, 20));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				finishReg.setBounds(69, 696, 300, 40);
				finishReg.setFont(new Font("宋体", Font.BOLD, 18));
			}
		});
		finishReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(theCaptcha.equals(captcha.getText())) {
					User u = new User();
					u.setInsert(true);
					u.setName(theName);
					u.setPwd(thePWD);
					u.setEmail(theEmail);
					theACN = new ClientCheck().checkInsert(u);
					
					File f = new File("F:\\Myeclipseproject\\用户聊天记录文件\\" + theACN);
					if(!f.exists()) {
						f.mkdirs();
					}
							
					JOptionPane.showMessageDialog(null, "注册成功\n账号为" + theACN, "温馨提示", 2, new ImageIcon("/QQ项目重置/图片/比心.png"));
					u.setInsert(false);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "验证码不正确", "温馨提示", 2, new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\狗头.png"));
				}
			}
		});
		finishReg.setBounds(69, 696, 300, 40);
		contentPane.add(finishReg);
		finishReg.setFont(new Font("宋体", Font.BOLD, 18));
		finishReg.setForeground(Color.BLACK);
		finishReg.setBackground(new Color(100, 149, 237));
		this.setVisible(true);
	}
}
