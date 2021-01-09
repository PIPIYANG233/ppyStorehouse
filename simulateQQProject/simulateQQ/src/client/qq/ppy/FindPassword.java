package client.qq.ppy;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tools.qq.ppy.JudgePWD;
import user.qq.ppy.User;

public class FindPassword extends JFrame {

	private JPanel contentPane;
	private JTextField acn;
	private JPasswordField pwd;
	private JPasswordField pwdAgain;
	private JLabel pwdJL;
	private JLabel pwdAgainJL;
	private JTextField email;
	private JLabel emailJL;
	private JTextField captcha;
	
	private String theACN;
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
	public FindPassword() {
		setTitle("�һ�����");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(1400, 100, 458, 806);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(""));
		lblNewLabel.setBounds(0, 0, 453, 57);
		contentPane.add(lblNewLabel);
		
		acn = new JTextField(); // �˺�
		acn.setBounds(69, 196, 300, 40);
		contentPane.add(acn);
		acn.setColumns(10);
		acn.setCaretColor(Color.BLACK);
		acn.setForeground(Color.BLACK);
		acn.setFont(new Font("����", Font.BOLD, 20));
		
		JLabel acnJL = new JLabel("�˺�");
		acnJL.setBounds(69, 142, 300, 46);
		contentPane.add(acnJL);
		acnJL.setFont(new Font("����", Font.BOLD, 22));
		acnJL.setForeground(Color.BLACK);

		
		pwd = new JPasswordField(); // ����
		pwd.setBounds(69, 527, 300, 40);
		contentPane.add(pwd);
		pwd.setCaretColor(Color.BLACK);
		pwd.setForeground(Color.BLACK);
		pwd.setFont(new Font("����", Font.BOLD, 20));
		
		pwdJL = new JLabel("������");
		pwdJL.setBounds(69, 477, 300, 40);
		contentPane.add(pwdJL);
		pwdJL.setFont(new Font("����", Font.BOLD, 22));
		pwdJL.setForeground(Color.BLACK);
		
		pwdAgain = new JPasswordField(); // ȷ������
		pwdAgain.setBounds(69, 625, 300, 40);
		contentPane.add(pwdAgain);
		pwdAgain.setCaretColor(Color.BLACK);
		pwdAgain.setForeground(Color.BLACK);
		pwdAgain.setFont(new Font("����", Font.BOLD, 20));
		
		pwdAgainJL = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		pwdAgainJL.setBounds(69, 578, 300, 40);
		contentPane.add(pwdAgainJL);
		pwdAgainJL.setFont(new Font("����", Font.BOLD, 22));
		pwdAgainJL.setForeground(Color.BLACK);
		
		email = new JTextField(); // ����
		email.setBounds(69, 296, 300, 40);
		contentPane.add(email);
		email.setColumns(10);
		email.setCaretColor(Color.BLACK);
		email.setForeground(Color.BLACK);
		email.setFont(new Font("����", Font.BOLD, 20));
		
		emailJL = new JLabel("\u90AE\u7BB1");
		emailJL.setBounds(69, 246, 300, 40);
		contentPane.add(emailJL);
		emailJL.setFont(new Font("����", Font.BOLD, 22));
		emailJL.setForeground(Color.BLACK);
		
		JButton getCaptcha = new JButton("\u83B7\u53D6\u9A8C\u8BC1\u7801"); // ��ȡ��֤��
		getCaptcha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				getCaptcha.setFont(new Font("����", Font.BOLD, 16));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				getCaptcha.setFont(new Font("����", Font.BOLD, 14));
			}
		});
		getCaptcha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				theACN = acn.getText();
				theEmail = email.getText();
				if(null == theACN || null == theEmail || theACN.length() == 0 || theEmail.length() == 0) {
					JOptionPane.showMessageDialog(null, "�˺Ż����䲻��Ϊ��", "��ܰ��ʾ", 2, new ImageIcon("F:\\Myeclipseproject\\QQ��Ŀ\\ͼƬ\\��������.jpg"));
				}
				else {
					User u = new User();
					u.setFind(true);
					u.setAcn(theACN);
					u.setEmail(theEmail);
					theCaptcha = new ClientCheck().checkReg(u);
				
					if(theCaptcha.length() == 0) {
						JOptionPane.showMessageDialog(null, "���䲻��ȷ", "��ܰ��ʾ", 2, new ImageIcon("F:\\Myeclipseproject\\QQ��Ŀ����\\ͼƬ\\��ͷ����.png"));
					}
					u.setFind(false);
				}
			}
		});
		getCaptcha.setBounds(250, 356, 119, 40);
		contentPane.add(getCaptcha);
		getCaptcha.setFont(new Font("����", Font.BOLD, 14));
		getCaptcha.setForeground(SystemColor.textHighlight);
		getCaptcha.setBackground(new Color(204, 255, 255));
		getCaptcha.setBorderPainted(false);
		getCaptcha.setOpaque(false);
		
		captcha = new JTextField();
		captcha.setBounds(69, 356, 167, 40);
		contentPane.add(captcha);
		captcha.setColumns(10);
		captcha.setCaretColor(Color.BLACK);
		captcha.setForeground(Color.BLACK);
		captcha.setFont(new Font("����", Font.BOLD, 20));
		
		JButton finishReg = new JButton("�����������"); // �������
		finishReg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				finishReg.setBounds(59, 686, 320, 60);
				finishReg.setFont(new Font("����", Font.BOLD, 20));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				finishReg.setBounds(69, 696, 300, 40);
				finishReg.setFont(new Font("����", Font.BOLD, 18));
			}
		});
		finishReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				thePWD = new String(pwd.getPassword());
				thePWDAgain = new String(pwdAgain.getPassword());
				if(theCaptcha.equals(captcha.getText())) {
					
					if(null == thePWD || null == thePWDAgain || null == theEmail || thePWD.length() == 0 || thePWDAgain.length() == 0 || theEmail.length() == 0) {
						JOptionPane.showMessageDialog(null, "���벻��Ϊ��", "��ܰ��ʾ", 2, new ImageIcon("F:\\Myeclipseproject\\QQ��Ŀ\\ͼƬ\\��������.jpg"));
					}
					else {
						if(!JudgePWD.judgePWD(thePWD)) {
							JOptionPane.showMessageDialog(null, "���� 8~26λ ֻ���� ����/��ĸ/С���� ���", "��ܰ��ʾ", 2, new ImageIcon("F:\\Myeclipseproject\\QQ��Ŀ����\\ͼƬ\\��ķ.jpg"));
						}
						else {
							if(!thePWD.equals(thePWDAgain)) {
								JOptionPane.showMessageDialog(null, "���벻һ��", "��ܰ��ʾ", 2, new ImageIcon("F:\\Myeclipseproject\\QQ��Ŀ����\\ͼƬ\\С��.gif"));
							}
							else {
								User u = new User();
								u.setUpdate(true);
								u.setAcn(theACN);
								u.setPwd(thePWD);
								new ClientCheck().checkUpdate(u);
								JOptionPane.showMessageDialog(null, "�������óɹ��ɹ�", "��ܰ��ʾ", 2, new ImageIcon("F:\\Myeclipseproject\\QQ��Ŀ����\\ͼƬ\\����.png"));
								u.setUpdate(false);
								dispose();
							}
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "��֤�벻��ȷ", "��ܰ��ʾ", 2, new ImageIcon("F:\\Myeclipseproject\\QQ��Ŀ����\\ͼƬ\\��ͷ.png"));
				}
			}
		});
		finishReg.setBounds(69, 696, 300, 40);
		contentPane.add(finishReg);
		finishReg.setFont(new Font("����", Font.BOLD, 18));
		finishReg.setForeground(Color.BLACK);
		finishReg.setBackground(new Color(100, 149, 237));
		this.setVisible(true);
	}
}
