package client.qq.ppy;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import server.qq.ppy.Server;
import server.qq.ppy.ServerChat;
import tools.mysql.ppy.ChatSaveOption;
import tools.qq.ppy.CloseUtils;
import tools.qq.ppy.BytesToPath;
import tools.qq.ppy.JudgeName;
import tools.qq.ppy.PlayVoice;
import user.qq.ppy.TheContent;
import user.qq.ppy.User;

public class QQChat extends JFrame implements Runnable {

	private JPanel contentPane;
	private JTextPane chatArea;
	private StyledDocument doc;
	private Socket client;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private boolean isRun;
	private User myFriend;
	private User me;
	private List<TheContent> tcs;

	public QQChat(User mySelf, User myFriend) {
		me = mySelf;
		this.myFriend = myFriend;

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		isRun = true;

		try {
			client = new Socket("localhost", 8848);
		} catch (Exception e) {
			e.printStackTrace();
		}

		me.setChat(true);
		me.setToFriend(myFriend.getAcn());
		new ClientCheck().checkChat(me, client);
		me.setChat(false);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent e) {
				isRun = false;
				me.setChat(false);
				ServerChat.stopThis();
				release(oos, ois, client);
				Server.userSocket.remove(me.getAcn());
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

		setBounds(100, 100, 673, 523);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.WHITE);
		northPanel.setBounds(0, 0, 657, 277);
		contentPane.add(northPanel);
		northPanel.setLayout(null);

		JLabel friendName = new JLabel(myFriend.getName()); // 好友昵称
		friendName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				friendName.setFont(new Font("宋体", Font.PLAIN, 22));
				friendName.setForeground(new Color(0, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				friendName.setFont(new Font("宋体", Font.PLAIN, 20));
				friendName.setForeground(Color.BLACK);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				new CheckUserInformation(myFriend);
			}
		});
		friendName.setForeground(Color.BLACK);
		friendName.setBackground(Color.WHITE);
		friendName.setHorizontalAlignment(SwingConstants.CENTER);
		friendName.setFont(new Font("宋体", Font.PLAIN, 20));
		friendName.setBounds(-1, -1, 660, 28);
		northPanel.add(friendName);

		chatArea = new JTextPane(); // 消息显示框
		doc = chatArea.getStyledDocument();
		chatArea.setEditable(false);
		chatArea.setFont(new Font("宋体", Font.BOLD, 15));
		chatArea.setBounds(0, 32, 658, 243);

		JScrollPane scrollPane = new JScrollPane(chatArea);
		scrollPane.setBounds(0, 32, 658, 243);
		northPanel.add(scrollPane);

		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.WHITE);
		southPanel.setBounds(0, 276, 657, 208);
		contentPane.add(southPanel);
		southPanel.setLayout(null);

		ImageIcon biaoqing = new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\chat表情.png");
		JLabel bq = new JLabel(biaoqing); // 表情
		bq.setToolTipText("发送表情");
		bq.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				bq.setBorder(BorderFactory.createRaisedBevelBorder());
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				bq.setBorder(null);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				new BiaoQing();
			}
		});
		bq.setBounds(5, 10, 20, 25);
		southPanel.add(bq);

		ImageIcon wenjian = new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\chat文件.png");
		JLabel wj = new JLabel(wenjian); // 文件
		wj.setToolTipText("发送文件");
		wj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				wj.setBorder(BorderFactory.createRaisedBevelBorder());
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				wj.setBorder(null);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser jc = new JFileChooser(); // 文件选择框
				jc.setDialogTitle("请选择文件");
				int click = jc.showOpenDialog(null);// 弹出选择框，返回值代表你鼠标的操作
				if (JFileChooser.APPROVE_OPTION == click) { // 选择了文件并确定
					String path = jc.getSelectedFile().getPath(); // 会得到一个文件类型值

					JPanel filePanel = new JPanel();
					filePanel.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							filePanel.setBorder(BorderFactory.createRaisedBevelBorder());
						}

						@Override
						public void mouseExited(MouseEvent e) {
							filePanel.setBorder(null);
						}

						@Override
						public void mouseClicked(MouseEvent e) {
							File f = new File(path);
							try {
								Desktop.getDesktop().open(f);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					});
					filePanel.setBorder(BorderFactory.createTitledBorder("文件"));
					filePanel.setBackground(Color.WHITE);
					filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.X_AXIS));

					ImageIcon qqIcon = new ImageIcon("");
					qqIcon.setImage(qqIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
					JLabel qqJLabel = new JLabel(qqIcon);
					qqJLabel.setBounds(10, 25, 50, 50);
					filePanel.add(qqJLabel);

					JLabel fileName = new JLabel(jc.getSelectedFile().getName());
					fileName.setFont(new Font("宋体 Light", Font.BOLD, 14));
					fileName.setBounds(70, 25, 260, 50);
					filePanel.add(fileName);

					String time = DateFormat.getDateTimeInstance().format(new Date());
					chatArea.setCaretPosition(doc.getLength());
					ImageIcon ii1 = new ImageIcon(me.getHead());
					ii1.setImage(ii1.getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
					chatArea.insertIcon(ii1);

					SimpleAttributeSet sas1 = new SimpleAttributeSet();
					StyleConstants.setForeground(sas1, new Color(0, 255, 5));
					try {
						doc.insertString(doc.getLength(), me.getName() + " " + time + "\n", sas1);
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}

					chatArea.setCaretPosition(doc.getLength());
					chatArea.insertComponent(filePanel);
					try {
						doc.insertString(doc.getLength(), "\n\n\n", new SimpleAttributeSet());
					} catch (BadLocationException e) {
						e.printStackTrace();
					}

					TheContent tc = new TheContent();
					tc.setFile(BytesToPath.getBytes(path));
					tc.setFileName(jc.getSelectedFile().getName());
					tc.setReceiver(myFriend);
					tc.setTime(time);
					tc.setSendName(me.getName());
					tc.setSender(me);
					tc.setSenderACN(me.getAcn());
					tc.setReceiverACN(myFriend.getAcn());
					if (null == oos) {

						try {
							oos = new ObjectOutputStream(client.getOutputStream());
						} catch (Exception e) {
							System.out.println("文件发送完毕");
						}
					}
					try {
						oos.writeObject(tc);
						oos.flush();
					} catch (Exception e) {
						System.out.println("文件发送完毕");
					}
				}
			}
		});
		wj.setBounds(65, 10, 20, 25);
		southPanel.add(wj);

		ImageIcon tupian = new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\chat图片.png");
		JLabel tp = new JLabel(tupian); // 图片
		tp.setToolTipText("发送图片");
		tp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser jc = new JFileChooser(); // 文件选择框
				jc.setDialogTitle("请选择图片文件");
				int click = jc.showOpenDialog(null);// 弹出选择框，返回值代表你鼠标的操作
				if (JFileChooser.APPROVE_OPTION == click) { // 选择了文件并确定
					String path = jc.getSelectedFile().getPath(); // 会得到一个文件类型值

					ImageIcon pic = new ImageIcon(path);
					int w = pic.getIconWidth();
					int h = pic.getIconHeight();
					if (w > 500 && h > 240) {
						w /= 3;
						h /= 3;
					}
					pic.setImage(pic.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));

					String time = DateFormat.getDateTimeInstance().format(new Date());
					chatArea.setCaretPosition(doc.getLength());
					ImageIcon ii1 = new ImageIcon(me.getHead());
					ii1.setImage(ii1.getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
					chatArea.insertIcon(ii1);
					try {
						SimpleAttributeSet sas1 = new SimpleAttributeSet();
						StyleConstants.setForeground(sas1, new Color(0, 255, 0));
						doc.insertString(doc.getLength(), me.getName() + " " + time + "\n", sas1);

						chatArea.insertIcon(pic);

						doc.insertString(doc.getLength(), "\n\n\n", sas1);

					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}

					TheContent tc = new TheContent();
					tc.setPic(BytesToPath.getBytes(path));
					tc.setReceiver(myFriend);
					tc.setTime(time);
					tc.setSendName(me.getName());
					tc.setSender(me);
					tc.setSenderACN(me.getAcn());
					tc.setReceiverACN(myFriend.getAcn());
					if (null == oos) {

						try {
							oos = new ObjectOutputStream(client.getOutputStream());
						} catch (Exception e) {
							System.out.println("图片发送完毕");
						}
					}
					try {
						oos.writeObject(tc);
						oos.flush();
					} catch (Exception e) {
						System.out.println("图片发送完毕");
					}

				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				tp.setBorder(BorderFactory.createRaisedBevelBorder());
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				tp.setBorder(null);
			}
		});
		tp.setBounds(125, 10, 20, 26);
		southPanel.add(tp);

		ImageIcon yuyin = new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\chat语音.png");
		JLabel yy = new JLabel(yuyin); // 语音
		yy.setToolTipText("长按录制语音");
		yy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				yy.setBorder(BorderFactory.createRaisedBevelBorder());
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				yy.setBorder(null);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				new YY();
			}
		});
		yy.setBounds(185, 10, 20, 26);
		southPanel.add(yy);

		ImageIcon changyongyu = new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\chat常用语.png");
		JLabel cyy = new JLabel(changyongyu); // 常用语
		cyy.setToolTipText("选择常用语");
		cyy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				cyy.setBorder(BorderFactory.createRaisedBevelBorder());
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				cyy.setBorder(null);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				new CYY(me);
			}
		});
		cyy.setBounds(245, 10, 22, 25);
		southPanel.add(cyy);

		JTextArea chatOut = new JTextArea();
		chatOut.setLineWrap(true);
		chatOut.setFont(new Font("宋体", Font.BOLD, 15));
		chatOut.setBounds(0, 45, 658, 124);
		// southPanel.add(chatOut);

		ImageIcon guanbi = new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\chat关闭.png");
		JLabel gb = new JLabel(guanbi);
		gb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				isRun = false;
				me.setChat(false);
				dispose();
				ServerChat.stopThis();
				release(oos, ois, client);
				Server.userSocket.remove(me.getAcn());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				gb.setBorder(BorderFactory.createRaisedBevelBorder());
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				gb.setBorder(null);
			}
		});
		gb.setBounds(469, 169, 76, 30);
		southPanel.add(gb);

		ImageIcon fasong = new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\chat发送.png");
		JLabel fs = new JLabel(fasong);
		fs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (null == oos) {

					try {
						oos = new ObjectOutputStream(client.getOutputStream());
					} catch (Exception e) {
						isRun = false;
						release(oos, ois, client);
						e.printStackTrace();
					}
				}

				String s = chatOut.getText();
				if (!s.equals("") && "\n" != s && null != s && s.length() != 0 && JudgeName.judgeName(s)) {
					String time = DateFormat.getDateTimeInstance().format(new Date());
					chatArea.setCaretPosition(doc.getLength());
					ImageIcon ii1 = new ImageIcon(me.getHead());
					ii1.setImage(ii1.getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
					chatArea.insertIcon(ii1);
					chatOut.setText("");
					try {
						SimpleAttributeSet sas1 = new SimpleAttributeSet();
						StyleConstants.setForeground(sas1, new Color(0, 255, 0));
						doc.insertString(doc.getLength(), me.getName() + " " + time, sas1);

						SimpleAttributeSet sas2 = new SimpleAttributeSet();
						StyleConstants.setBackground(sas2, new Color(0, 255, 0));
						StyleConstants.setForeground(sas2, Color.BLACK);
						doc.insertString(doc.getLength(), "\n" + s + "\n\n\n", sas2);

					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}

					TheContent tc = new TheContent();
					tc.setReceiver(myFriend);
					tc.setTalks(s);
					tc.setTime(time);
					tc.setSendName(me.getName());
					tc.setSender(me);
					tc.setSenderACN(me.getAcn());
					tc.setReceiverACN(myFriend.getAcn());

					try {
						oos.writeObject(tc);
						oos.flush();
					} catch (Exception e) {
						isRun = false;
						release(oos, ois, client);
						e.printStackTrace();
					}
				} else {
					new CYY(me);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				fs.setBorder(BorderFactory.createRaisedBevelBorder());
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				fs.setBorder(null);
			}
		});
		fs.setBounds(566, 170, 68, 30);
		southPanel.add(fs);

		JScrollPane scrollPane_1 = new JScrollPane(chatOut); // 消息输入框
		scrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(0, 45, 658, 124);
		southPanel.add(scrollPane_1);

		new Thread(new Receive()).start();
		setVisible(true);
	}

	@Override
	public void run() {

	}

	class Receive implements Runnable {

		public void run() {

			String send_receive = "";
			if (me.getAcn().compareTo(me.getToFriend()) < 0) {
				send_receive = me.getAcn() + me.getToFriend();
			} else {
				send_receive = me.getToFriend() + me.getAcn();
			}
			tcs = new ChatSaveOption().selectChatInformation(send_receive);
			if (null != tcs && tcs.size() != 0) {
				System.out.println(tcs.size());
				for (TheContent tc : tcs) {
					chatArea.setCaretPosition(doc.getLength());

					ImageIcon ii2 = null;
					if (tc.getSenderACN().equals(me.getAcn())) {
						ii2 = new ImageIcon(me.getHead());
					} else {
						ii2 = new ImageIcon(myFriend.getHead());
					}
					ii2.setImage(ii2.getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
					chatArea.setCaretPosition(doc.getLength());
					chatArea.insertIcon(ii2);

					SimpleAttributeSet sas1 = new SimpleAttributeSet();

					if (tc.getSenderACN().equals(me.getAcn())) {
						StyleConstants.setForeground(sas1, new Color(0, 255, 0));
					} else {
						StyleConstants.setForeground(sas1, new Color(0, 255, 255));
					}
					try {
						doc.insertString(doc.getLength(), tc.getSendName() + " " + tc.getTime() + "\n", sas1);
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}

					if (null != tc.getTalks()) {
						SimpleAttributeSet sas2 = new SimpleAttributeSet();
						if (tc.getSenderACN().equals(me.getAcn())) {
							StyleConstants.setBackground(sas2, new Color(0, 255, 0));
						} else {
							StyleConstants.setBackground(sas2, new Color(0, 255, 255));
						}
						StyleConstants.setForeground(sas2, Color.BLACK);
						try {
							doc.insertString(doc.getLength(), "\n" + tc.getTalks() + "\n\n\n", sas2);
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
					} else if (null != tc.getPic()) {
						ImageIcon pic = new ImageIcon(tc.getPic());
						int w = pic.getIconWidth();
						int h = pic.getIconHeight();
						if (w > 500 && h > 240) {
							w /= 3;
							h /= 3;
						}
						pic.setImage(pic.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));

						chatArea.setCaretPosition(doc.getLength());
						chatArea.insertIcon(pic);

						try {
							doc.insertString(doc.getLength(), "\n\n\n", new SimpleAttributeSet());
						} catch (BadLocationException e) {
							e.printStackTrace();
						}
					} else if (null != tc.getFile()) {
						JPanel filePanel = new JPanel();
						filePanel.setBorder(BorderFactory.createTitledBorder("文件"));
						filePanel.setBackground(Color.WHITE);
						filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.X_AXIS));

						ImageIcon qqIcon = new ImageIcon("C:\\Users\\hp\\Pictures\\Saved Pictures\\QQ.jpg");
						qqIcon.setImage(qqIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
						JLabel qqJLabel = new JLabel(qqIcon);
						qqJLabel.setBounds(10, 25, 50, 50);
						filePanel.add(qqJLabel);

						JLabel fileName = new JLabel(tc.getFileName());
						fileName.setFont(new Font("微软雅黑 Light", Font.BOLD, 14));
						fileName.setBounds(70, 25, 260, 50);
						filePanel.add(fileName);

						chatArea.setCaretPosition(doc.getLength());
						chatArea.insertComponent(filePanel);

						try {
							doc.insertString(doc.getLength(), "\n\n\n", sas1);
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}

						try {
							File f = new File(
									"F:\\Myeclipseproject\\用户聊天记录文件\\" + me.getAcn() + "\\" + tc.getFileName());
							if (!f.exists()) {
								f.createNewFile();
								BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
								bos.write(tc.getFile(), 0, tc.getFile().length);
								bos.close();
							}
							filePanel.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseEntered(MouseEvent e) {
									filePanel.setBorder(BorderFactory.createRaisedBevelBorder());
								}

								@Override
								public void mouseExited(MouseEvent e) {
									filePanel.setBorder(null);
								}

								@Override
								public void mouseClicked(MouseEvent e) {
									try {
										Desktop.getDesktop().open(f);
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
							});
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if(null != tc.getYy()){
						JPanel yyPanel = new JPanel();
						yyPanel.setBorder(BorderFactory.createTitledBorder("语音消息"));
						yyPanel.setLayout(new CardLayout(0, 0));

						JLabel yyJLabel = new JLabel("播 放");
						yyJLabel.setHorizontalAlignment(SwingConstants.CENTER);
						yyJLabel.setBorder(BorderFactory.createRaisedBevelBorder());
						yyJLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
						yyJLabel.setBounds(10, 25, 59, 20);
						yyPanel.add(yyJLabel);

						File f = new File("F:\\Myeclipseproject\\用户聊天记录文件\\" + me.getAcn() + "\\" + dateToStamp(tc.getTime()));
						if (!f.exists()) {
							try {
								f.createNewFile();
								BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
								byte[] yy = BytesToPath.getBytes("test.wav");
								bos.write(yy, 0, yy.length);
								bos.close();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}

						yyPanel.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent arg0) {
								yyJLabel.setBorder(BorderFactory.createRaisedBevelBorder());
							}

							@Override
							public void mouseExited(MouseEvent e) {
								yyJLabel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
							}

							@Override
							public void mouseClicked(MouseEvent e) {
								PlayVoice.playVoiceFile(f.getPath());
							}
						});
						yyPanel.setBackground(Color.WHITE);

						chatArea.setCaretPosition(doc.getLength());
						chatArea.insertComponent(yyPanel);
						try {
							doc.insertString(doc.getLength(), "\n\n\n", new SimpleAttributeSet());
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
					}
				}
				SimpleAttributeSet sas3 = new SimpleAttributeSet();
				StyleConstants.setForeground(sas3, Color.BLACK);
				try {
					doc.insertString(doc.getLength(), "\n以上为历史消息" + "------------------------------------" + "\n\n\n",
							sas3);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}

			while (isRun) {

				try {
					ois = new ObjectInputStream(client.getInputStream());
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				try {
					TheContent tc = (TheContent) ois.readObject();
					chatArea.setCaretPosition(doc.getLength());
					ImageIcon ii2 = new ImageIcon(myFriend.getHead());
					ii2.setImage(ii2.getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
					chatArea.setCaretPosition(doc.getLength());
					chatArea.insertIcon(ii2);

					SimpleAttributeSet sas1 = new SimpleAttributeSet();
					StyleConstants.setForeground(sas1, new Color(0, 255, 255));
					doc.insertString(doc.getLength(), tc.getSendName() + " " + tc.getTime() + "\n", sas1);

					if (null != tc.getTalks()) {
						SimpleAttributeSet sas2 = new SimpleAttributeSet();
						StyleConstants.setForeground(sas2, Color.BLACK);
						StyleConstants.setBackground(sas2, new Color(0, 255, 255));
						doc.insertString(doc.getLength(), "\n" + tc.getTalks() + "\n\n\n", sas2);
					} else if (null != tc.getPic()) {
						ImageIcon pic = new ImageIcon(tc.getPic());
						int w = pic.getIconWidth();
						int h = pic.getIconHeight();
						if (w > 500 && h > 240) {
							w /= 3;
							h /= 3;
						}
						pic.setImage(pic.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));

						chatArea.setCaretPosition(doc.getLength());
						chatArea.insertIcon(pic);

						doc.insertString(doc.getLength(), "\n\n\n", new SimpleAttributeSet());
					} else if (null != tc.getFile()) {
						JPanel filePanel = new JPanel();
						filePanel.setBorder(BorderFactory.createTitledBorder("文件"));
						filePanel.setBackground(Color.WHITE);
						filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.X_AXIS));

						ImageIcon qqIcon = new ImageIcon("C:\\Users\\hp\\Pictures\\Saved Pictures\\QQ.jpg");
						qqIcon.setImage(qqIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
						JLabel qqJLabel = new JLabel(qqIcon);
						qqJLabel.setBounds(10, 25, 50, 50);
						filePanel.add(qqJLabel);

						JLabel fileName = new JLabel(tc.getFileName());
						fileName.setFont(new Font("宋体 Light", Font.BOLD, 14));
						fileName.setBounds(70, 25, 260, 50);
						filePanel.add(fileName);

						chatArea.setCaretPosition(doc.getLength());
						chatArea.insertComponent(filePanel);
						try {
							doc.insertString(doc.getLength(), "\n\n\n", new SimpleAttributeSet());
						} catch (BadLocationException e) {
							e.printStackTrace();
						}

						File f = new File("F:\\Myeclipseproject\\用户聊天记录文件\\" + me.getAcn() + "\\" + tc.getFileName());
						if (!f.exists()) {
							f.createNewFile();
							BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
							bos.write(tc.getFile(), 0, tc.getFile().length);
							bos.close();
						}
						filePanel.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								filePanel.setBorder(BorderFactory.createRaisedBevelBorder());
							}

							@Override
							public void mouseExited(MouseEvent e) {
								filePanel.setBorder(null);
							}

							@Override
							public void mouseClicked(MouseEvent e) {
								try {
									Desktop.getDesktop().open(f);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						});
					} else if(null != tc.getYy()){
						JPanel yyPanel = new JPanel();
						yyPanel.setBorder(BorderFactory.createTitledBorder("语音消息"));
						yyPanel.setLayout(new CardLayout(0, 0));

						JLabel yyJLabel = new JLabel("播 放");
						yyJLabel.setHorizontalAlignment(SwingConstants.CENTER);
						yyJLabel.setBorder(BorderFactory.createRaisedBevelBorder());
						yyJLabel.setFont(new Font("宋体", Font.BOLD, 15));
						yyJLabel.setBounds(10, 25, 59, 20);
						yyPanel.add(yyJLabel);

						File f = new File("F:\\Myeclipseproject\\用户聊天记录文件\\" + me.getAcn() + "\\" + dateToStamp(tc.getTime()));
						if (!f.exists()) {
							try {
								f.createNewFile();
								BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
								byte[] yy = BytesToPath.getBytes("test.wav");
								bos.write(yy, 0, yy.length);
								bos.close();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}

						yyPanel.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent arg0) {
								yyJLabel.setBorder(BorderFactory.createRaisedBevelBorder());
							}

							@Override
							public void mouseExited(MouseEvent e) {
								yyJLabel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
							}

							@Override
							public void mouseClicked(MouseEvent e) {
								PlayVoice.playVoiceFile(f.getPath());
							}
						});
						yyPanel.setBackground(Color.WHITE);

						chatArea.setCaretPosition(doc.getLength());
						chatArea.insertComponent(yyPanel);
						try {
							doc.insertString(doc.getLength(), "\n\n\n", new SimpleAttributeSet());
						} catch (BadLocationException e1) {
							e1.printStackTrace();
						}
					}

				} catch (Exception e) {
					isRun = false;
//					release(oos, ois, client);
					e.printStackTrace();
				}
			}

			release(oos, ois, client);
		}

	}

	public void release(ObjectOutputStream oos, ObjectInputStream ois, Socket client) {
		CloseUtils.close(ois, oos, client);
	}

	public void insertBiaoQing(byte[] biaoqing) {
		ImageIcon pic = new ImageIcon(biaoqing);
		int w = pic.getIconWidth();
		int h = pic.getIconHeight();
		if (w > 500 && h > 240) {
			w /= 3;
			h /= 3;
		}
		pic.setImage(pic.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));

		String time = DateFormat.getDateTimeInstance().format(new Date());
		chatArea.setCaretPosition(doc.getLength());
		ImageIcon ii1 = new ImageIcon(me.getHead());
		ii1.setImage(ii1.getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
		chatArea.insertIcon(ii1);
		try {
			SimpleAttributeSet sas1 = new SimpleAttributeSet();
			StyleConstants.setForeground(sas1, new Color(0, 255, 0));
			doc.insertString(doc.getLength(), me.getName() + " " + time + "\n", sas1);

			chatArea.insertIcon(pic);

			doc.insertString(doc.getLength(), "\n\n\n", sas1);

		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}

		TheContent tc = new TheContent();
		tc.setPic(biaoqing);
		tc.setReceiver(myFriend);
		tc.setTime(time);
		tc.setSendName(me.getName());
		tc.setSender(me);
		tc.setSenderACN(me.getAcn());
		tc.setReceiverACN(myFriend.getAcn());
		if (null == oos) {

			try {
				oos = new ObjectOutputStream(client.getOutputStream());
			} catch (Exception e) {
				System.out.println("图片发送完毕");
			}
		}
		try {
			oos.writeObject(tc);
			oos.flush();
		} catch (Exception e) {
			System.out.println("图片发送完毕");
		}
	}

	public class BiaoQing extends JFrame implements MouseListener {

		private JPanel contentPane;

		public BiaoQing() {

			PointerInfo pinfo = MouseInfo.getPointerInfo();
			Point p = pinfo.getLocation();
			int x = (int) p.getX();
			int y = (int) p.getY();

			setUndecorated(true);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(x, y - 270, 450, 270);
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JPanel jp = new JPanel(new GridLayout(5, 2, 0, 0));
			jp.setBorder(BorderFactory.createTitledBorder("表 情"));
			jp.setBackground(Color.WHITE);
			jp.setBounds(0, 0, 434, 261);
			contentPane.add(jp);

			JLabel[] jls = new JLabel[55];
			String[] names = { "闭嘴", "大哭", "得意", "发呆", "发怒", "尴尬", "害羞", "流泪", "撇嘴", "色", "睡", "微信", "酷", "可爱", "冷汗",
					"呕吐", "调皮", "难过", "抓狂", "呲牙", "惊讶", "傲慢", "偷笑", "白眼", "惊吓", "流汗", "困", "憨笑", "馋", "滑稽", "亲亲", "晕",
					"抠鼻", "擦汗", "嘘", "再见", "谩骂", "奋斗", "疑问", "鼓掌", "玫瑰", "爱心", "胜利", "握手", "强", "弱", "OK", "月亮", "太阳",
					"饭", "药丸", "蛋糕", "庆祝", "礼物", "关闭" };
			for (int i = 0; i < 55; i++) {
				jls[i] = new JLabel("" + i);
				jls[i].setFont(new Font("微软雅黑 Light", Font.BOLD, 1));
				ImageIcon ii = new ImageIcon("F:\\Myeclipseproject\\经典表情\\" + i + ".png");
				jls[i].setIcon(ii);
				jls[i].setToolTipText(names[i]);

				if (i < 54) {
					jls[i].addMouseListener(this);
				}

				jp.add(jls[i]);
			}

			jls[54].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					jls[54].setBorder(BorderFactory.createRaisedBevelBorder());
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					jls[54].setBorder(null);
				}

				@Override
				public void mouseClicked(MouseEvent arg0) {
					dispose();
				}
			});

//			contentPane.addMouseListener(new MouseAdapter() {
//					@Override
//					public void mouseEntered(MouseEvent e) {
//						jp.addMouseListener(new MouseAdapter() {
//							@Override
//							public void mouseExited(MouseEvent arg0) {
//								dispose();
//							}
//						});
//					}
//				});

			setVisible(true);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			JLabel jl = (JLabel) e.getSource();
			insertBiaoQing(BytesToPath.getBytes("F:\\Myeclipseproject\\经典表情\\" + jl.getText() + ".png"));
			dispose();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			JLabel jl = (JLabel) e.getSource();
			jl.setBorder(BorderFactory.createRaisedBevelBorder());
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			JLabel jl = (JLabel) e.getSource();
			jl.setBorder(null);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public void insertCYY(String cyy) {
		String time = DateFormat.getDateTimeInstance().format(new Date());
		chatArea.setCaretPosition(doc.getLength());
		ImageIcon ii1 = new ImageIcon(me.getHead());
		ii1.setImage(ii1.getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
		chatArea.insertIcon(ii1);
		try {
			SimpleAttributeSet sas1 = new SimpleAttributeSet();
			StyleConstants.setForeground(sas1, new Color(0, 255, 0));
			doc.insertString(doc.getLength(), me.getName() + " " + time, sas1);

			SimpleAttributeSet sas2 = new SimpleAttributeSet();
			StyleConstants.setBackground(sas2, new Color(0, 255, 0));
			StyleConstants.setForeground(sas2, Color.BLACK);
			doc.insertString(doc.getLength(), "\n" + cyy + "\n\n\n", sas2);

		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}

		TheContent tc = new TheContent();
		tc.setReceiver(myFriend);
		tc.setTalks(cyy);
		tc.setTime(time);
		tc.setSendName(me.getName());
		tc.setSender(me);
		tc.setSenderACN(me.getAcn());
		tc.setReceiverACN(myFriend.getAcn());

		try {
			oos.writeObject(tc);
			oos.flush();
		} catch (Exception e) {
			isRun = false;
			release(oos, ois, client);
			e.printStackTrace();
		}
	}

	public class CYY extends JFrame {

		private JPanel contentPane;
		private String[] meCYY;

		public CYY(User me) {

			meCYY = me.getChangyongyu().split("_");

			PointerInfo pinfo = MouseInfo.getPointerInfo();
			Point p = pinfo.getLocation();
			int x = (int) p.getX();
			int y = (int) p.getY();

			this.setUndecorated(true);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(x, y - 232, 261, 232);
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			contentPane.setBorder(BorderFactory.createTitledBorder("常 用 语"));
			panel.setBounds(8, 32, 245, 191);
			contentPane.add(panel);
			panel.setLayout(null);

			JLabel JL1 = new JLabel(new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\钩.png"));
			JL1.setBounds(0, 0, 32, 30);
			panel.add(JL1);

			JTextField tf1 = new JTextField(meCYY[0]);
			tf1.setEnabled(false);
			tf1.setBounds(42, 0, 203, 30);
			tf1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					tf1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					tf1.setBorder(null);
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					if (!tf1.isEnabled()) {
						insertCYY(tf1.getText());
						dispose();
					}
				}
			});
			tf1.setFont(new Font("宋体", Font.PLAIN, 14));
			panel.add(tf1);

			JLabel JL2 = new JLabel(new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\钩.png"));
			JL2.setBounds(0, 40, 32, 30);
			panel.add(JL2);

			JTextField tf2 = new JTextField(meCYY[1]);
			tf2.setEnabled(false);
			tf2.setBounds(42, 40, 203, 30);
			tf2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					tf2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					tf2.setBorder(null);
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					if (!tf2.isEnabled()) {
						insertCYY(tf2.getText());
						dispose();
					}
				}
			});
			tf2.setFont(new Font("宋体", Font.PLAIN, 14));
			panel.add(tf2);

			JLabel JL3 = new JLabel(new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\钩.png"));
			JL3.setBounds(0, 80, 32, 30);
			panel.add(JL3);

			JTextField tf3 = new JTextField(meCYY[2]);
			tf3.setEnabled(false);
			tf3.setBounds(42, 80, 203, 30);
			tf3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					tf3.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					tf3.setBorder(null);
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					if (!tf3.isEnabled()) {
						insertCYY(tf3.getText());
						dispose();
					}
				}
			});
			tf3.setFont(new Font("宋体", Font.PLAIN, 14));
			panel.add(tf3);

			JLabel JL4 = new JLabel(new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\钩.png"));
			JL4.setBounds(0, 120, 32, 30);
			panel.add(JL4);

			JTextField tf4 = new JTextField(meCYY[3]);
			tf4.setEnabled(false);
			tf4.setBounds(42, 120, 203, 30);
			tf4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					tf4.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					tf4.setBorder(null);
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					if (!tf4.isEnabled()) {
						insertCYY(tf4.getText());
						dispose();
					}
				}
			});
			tf4.setFont(new Font("宋体", Font.PLAIN, 14));
			panel.add(tf4);

			JLabel queren = new JLabel("");
			queren.setForeground(Color.GRAY);
			queren.setFont(new Font("宋体", Font.PLAIN, 14));
			queren.setBounds(203, 160, 32, 22);
			panel.add(queren);

			JLabel bj = new JLabel("\u7F16\u8F91");
			bj.setForeground(Color.GRAY);
			bj.setFont(new Font("宋体", Font.PLAIN, 14));
			bj.setBounds(10, 160, 32, 22);
			bj.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					bj.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					bj.setBorder(null);
				}

				@Override
				public void mouseClicked(MouseEvent e) {

					queren.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent arg0) {
							queren.setBorder(BorderFactory.createLineBorder(Color.GRAY));
						}

						@Override
						public void mouseExited(MouseEvent e) {
							queren.setBorder(null);
						}
					});

					tf1.setOpaque(true);
					tf1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					tf1.setEnabled(true);

					tf2.setText(tf2.getText());
					tf2.setOpaque(true);
					tf2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					tf2.setEnabled(true);

					tf3.setText(tf3.getText());
					tf3.setOpaque(true);
					tf3.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					tf3.setEnabled(true);

					tf4.setText(tf4.getText());
					tf4.setOpaque(true);
					tf4.setBorder(BorderFactory.createLineBorder(Color.GRAY));
					tf4.setEnabled(true);

					queren.setText("确认");
					queren.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							tf1.setOpaque(false);
							tf1.setBorder(null);
							tf1.setEnabled(false);

							tf2.setOpaque(false);
							tf2.setBorder(null);
							tf2.setEnabled(false);

							tf3.setOpaque(false);
							tf3.setBorder(null);
							tf3.setEnabled(false);

							tf4.setOpaque(false);
							tf4.setBorder(null);
							tf4.setEnabled(false);

							if (!queren.getText().equals("")) {
								String cyy = "";
								cyy = tf1.getText() + "_" + tf2.getText() + "_" + tf3.getText() + "_" + tf4.getText();
								if (!me.getChangyongyu().equals(cyy)) {
									me.setChangyongyu(cyy);
									me.setCyy(true);
									new ClientCheck().checkChangeChangyongyu(me);
									me.setCyy(false);
								}
							}

							queren.setText("");
							if (queren.getText().equals("")) {
								queren.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseEntered(MouseEvent arg0) {
										queren.setBorder(null);
									}

									@Override
									public void mouseExited(MouseEvent e) {
										queren.setBorder(null);
									}
								});
							}
						}

					});
				}
			});
			panel.add(bj);

			JLabel chacha = new JLabel(new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\叉叉.png"));
			chacha.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					dispose();
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					chacha.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					chacha.setBorder(null);
				}
			});
			chacha.setBounds(225, 10, 29, 20);
			contentPane.add(chacha);

			setVisible(true);
		}
	}

	public String dateToStamp(String s) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String res = "";
		if (!"".equals(s)) {
			try {
				res = String.valueOf(sdf.parse(s).getTime() / 1000);
			} catch (Exception e) {
				System.out.println("传入了null值");
			}
		} else {
			long time = System.currentTimeMillis();
			res = String.valueOf(time / 1000);
		}

		return res;
	}

	public void insertYY() {
		
		chatArea.setCaretPosition(doc.getLength());
		ImageIcon ii1 = new ImageIcon(me.getHead());
		ii1.setImage(ii1.getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT));
		chatArea.insertIcon(ii1);

		String time = DateFormat.getDateTimeInstance().format(new Date());
		SimpleAttributeSet sas1 = new SimpleAttributeSet();
		StyleConstants.setForeground(sas1, new Color(0, 255, 0));
		try {
			doc.insertString(doc.getLength(), me.getName() + " " + time + "\n", sas1);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		
		JPanel yyPanel = new JPanel();
		yyPanel.setBorder(BorderFactory.createTitledBorder("语音消息"));
		yyPanel.setLayout(new CardLayout(0, 0));

		JLabel yyJLabel = new JLabel("播 放");
		yyJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yyJLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		yyJLabel.setFont(new Font("宋体", Font.BOLD, 15));
		yyJLabel.setBounds(10, 25, 59, 20);
		yyPanel.add(yyJLabel);

		File f = new File("F:\\Myeclipseproject\\用户聊天记录文件\\" + me.getAcn() + "\\" + dateToStamp(time));
		if (!f.exists()) {
			try {
				f.createNewFile();
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
				byte[] yy = BytesToPath.getBytes("test.wav");
				bos.write(yy, 0, yy.length);
				bos.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		yyPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				yyJLabel.setBorder(BorderFactory.createRaisedBevelBorder());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				yyJLabel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				PlayVoice.playVoiceFile(f.getPath());
			}
		});
		yyPanel.setBackground(Color.WHITE);

		chatArea.setCaretPosition(doc.getLength());
		chatArea.insertComponent(yyPanel);
		try {
			doc.insertString(doc.getLength(), "\n\n\n", new SimpleAttributeSet());
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}

		TheContent tc = new TheContent();
		tc.setYy(f.getPath());
		tc.setReceiver(myFriend);
		tc.setTime(time);
		tc.setSendName(me.getName());
		tc.setSender(me);
		tc.setSenderACN(me.getAcn());
		tc.setReceiverACN(myFriend.getAcn());
		if (null == oos) {

			try {
				oos = new ObjectOutputStream(client.getOutputStream());
			} catch (Exception e1) {
				System.out.println("语音发送完毕");
			}
		}
		try {
			oos.writeObject(tc);
			oos.flush();
		} catch (Exception e1) {
			System.out.println("语音发送完毕");
		}

	}

	public class YY extends JFrame {
		private JPanel contentPane;
		private AudioFormat audioFormat;
		private TargetDataLine targetDataLine;

		public YY() {
			PointerInfo pinfo = MouseInfo.getPointerInfo();
			Point p = pinfo.getLocation();
			int x = (int) p.getX();
			int y = (int) p.getY();

			this.setUndecorated(true);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(x, y - 22, 91, 22);
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			captureAudio();

			ImageIcon fs = new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\语音发送.png");
			JLabel fasong = new JLabel(fs);
			fasong.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					fasong.setBorder(BorderFactory.createRaisedBevelBorder());
				}

				@Override
				public void mouseExited(MouseEvent e) {
					fasong.setBorder(null);
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					targetDataLine.stop();
					targetDataLine.close();
					dispose();
					insertYY();
				}
			});
			fasong.setBounds(0, 0, 46, 22);
			contentPane.add(fasong);

			ImageIcon qx = new ImageIcon("F:\\Myeclipseproject\\QQ项目重置\\图片\\语音取消.png");
			JLabel quxiao = new JLabel(qx);
			quxiao.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					quxiao.setBorder(BorderFactory.createRaisedBevelBorder());
				}

				@Override
				public void mouseExited(MouseEvent e) {
					quxiao.setBorder(null);
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					targetDataLine.stop();
					targetDataLine.close();
					dispose();
				}
			});
			quxiao.setBounds(45, 0, 46, 22);
			contentPane.add(quxiao);
			setVisible(true);
		}

		public void captureAudio() {
			try {
				audioFormat = getAudioFormat();
				DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
				targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
				new CaptureThread().start();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

		private AudioFormat getAudioFormat() {
			float sampleRate = 8000F;
			int sampleSizeInBits = 16;
			int channels = 2;
			boolean signed = true;
			boolean bigEndian = false;
			return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		}

		class CaptureThread extends Thread {
			public void run() {
				AudioFileFormat.Type fileType = null;
				File audioFile = null;
				fileType = AudioFileFormat.Type.WAVE;
				audioFile = new File("test.wav");
				try {
					targetDataLine.open(audioFormat);
					targetDataLine.start();
					AudioSystem.write(new AudioInputStream(targetDataLine), fileType, audioFile);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
