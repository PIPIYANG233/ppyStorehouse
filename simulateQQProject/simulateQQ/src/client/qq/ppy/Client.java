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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import user.qq.ppy.Massage;
import user.qq.ppy.User;

public class Client extends JFrame implements Runnable {

	private JPanel mainPanel;
	private JTextField acn;
	private JPasswordField pwd;

	public static void main(String[] args) {
		new Thread(new Client()).start();
	}

	public Client() {

		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;

		setTitle("仿QQ聊天室");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((w - 422) / 2, (h - 339) / 2, 422, 339);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setBackground(Color.WHITE);
		setContentPane(mainPanel);
		mainPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 97, 426, 203);
		mainPanel.add(panel);
		panel.setLayout(null);

		acn = new JTextField(); // 输入账号
		acn.setOpaque(false);
		acn.setBounds(103, 10, 211, 29);
		panel.add(acn);
		acn.setColumns(10);
		acn.setCaretColor(Color.WHITE);
		acn.setForeground(Color.WHITE);
		acn.setFont(new Font("宋体", Font.BOLD, 20));

		pwd = new JPasswordField(); // 输入密码
		pwd.setOpaque(false);
		pwd.setBounds(103, 56, 211, 29);
		panel.add(pwd);
		pwd.setCaretColor(Color.WHITE);
		pwd.setForeground(Color.WHITE);
		pwd.setFont(new Font("宋体", Font.BOLD, 20));

		JButton enter = new JButton("登录");
		enter.setFont(new Font("宋体", Font.BOLD, 20));
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String zh = acn.getText();
				String mm = new String(pwd.getPassword());
				if (null == zh || null == mm || zh.length() == 0 || mm.length() == 0) {
					JOptionPane.showMessageDialog(null, "账号或密码不能为空", "温馨提示", 2,
							new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\杰瑞柠檬.gif"));
				} else {
					User u = new User();
					u.setAcn(zh);
					u.setPwd(mm);
					u.setEnter(true);

					Massage m = new ClientCheck().checkEnter(u);
					if (m.getCanEnter() == 2) {
						JOptionPane.showMessageDialog(null, "账号或密码不正确", "温馨提示", 2,
								new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\猫吹口哨.gif"));
					} else if (m.getCanEnter() == 1) {
						dispose();
						new Thread(new FriendList(m.getU())).start(); // 获取好友列表
					} else {
						JOptionPane.showMessageDialog(null, "请勿重复登录", "警告", 2,
								new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\死侍.gif"));
					}
					u.setEnter(false);
				}
			}
		});
		enter.setBackground(SystemColor.textHighlight);
		enter.setForeground(Color.WHITE);
		enter.setBounds(103, 124, 211, 36);
		panel.add(enter);

		JCheckBox savePWD = new JCheckBox("记住密码");
		savePWD.setOpaque(false);
		savePWD.setBackground(Color.WHITE);
		savePWD.setBounds(103, 91, 103, 23);
		savePWD.setFont(new Font("宋体", Font.BOLD, 13));
		savePWD.setForeground(Color.WHITE);
		panel.add(savePWD);

		JButton findPWD = new JButton("找回密码"); // 找回密码
		findPWD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				findPWD.setForeground(SystemColor.textHighlight);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				findPWD.setForeground(Color.WHITE);
			}
		});
		findPWD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FindPassword();
			}
		});
		findPWD.setOpaque(false);
		findPWD.setBackground(Color.WHITE);
		findPWD.setBorderPainted(false);
		findPWD.setBounds(224, 91, 90, 23);
		findPWD.setFont(new Font("宋体", Font.BOLD, 13));
		findPWD.setForeground(Color.WHITE);
		panel.add(findPWD);

		JButton regACN = new JButton("注册账号"); // 注册账号
		regACN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				regACN.setForeground(SystemColor.textHighlight);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				regACN.setForeground(Color.WHITE);
			}
		});
		regACN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Register();
			}
		});
		regACN.setBackground(Color.WHITE);
		regACN.setBorderPainted(false);
		regACN.setOpaque(false);
		regACN.setBounds(0, 170, 93, 23);
		regACN.setFont(new Font("宋体", Font.BOLD, 13));
		regACN.setForeground(Color.WHITE);
		panel.add(regACN);

		JLabel acnJL = new JLabel("账号");
		acnJL.setBounds(63, 10, 30, 29);
		acnJL.setFont(new Font("宋体", Font.BOLD, 14));
		acnJL.setForeground(Color.WHITE);
		panel.add(acnJL);

		JLabel pwdJL = new JLabel("密码");
		pwdJL.setBounds(63, 56, 30, 29);
		pwdJL.setFont(new Font("宋体", Font.BOLD, 14));
		pwdJL.setForeground(Color.WHITE);
		panel.add(pwdJL);

		ImageIcon back = new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\君の名.jpg");
		back.setImage(back.getImage().getScaledInstance(426, 300, Image.SCALE_DEFAULT));

		JLabel background = new JLabel(back);
		background.setBounds(0, 0, 406, 300);
		getContentPane().add(background);

	}

	public void run() {
		try {
			Client frame = new Client();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
