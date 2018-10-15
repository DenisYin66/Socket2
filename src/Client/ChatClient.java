package Client;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//write by yinzhicheng 2018/10/8
public class ChatClient {

	private JFrame frame;
	private JTextField nameTextField;
	private JTextField PasswordTextField;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	String username;
	String password;
	String reciever = null;
	Socket s ;
	JTextArea contentArea;
	JPanel facePanel;

	public static void main(String[] args) throws UnknownHostException {
	
		ChatClient window = new ChatClient();
		window.frame.setVisible(true);
	}

	public ChatClient() throws UnknownHostException {
		initialize();
	}


	private void initialize() throws UnknownHostException {
		boolean islogin = false;
	    try {
			s = new Socket("127.0.0.1",7879);
System.out.println("用户端链接服务器等待验证...");
            dos = new DataOutputStream(s.getOutputStream());
            dis = new DataInputStream(s.getInputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
		frame = new JFrame();
		frame.setBounds(100, 100, 567, 436);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		 
		final JComboBox comboBox = new JComboBox();                          //好友列表
		comboBox.setToolTipText("");
		comboBox.setBounds(166, 50, 314, 24);
		frame.getContentPane().add(comboBox);
		
//		JFileChooser jfc = new JFileChooser();
//		jfc.setVisible(true);
//		frame.getContentPane().add(jfc);
		
		final JButton loginButton = new JButton("\u767B\u5F55");               //登录按钮
		loginButton.setBounds(405, 14, 63, 27);
		frame.getContentPane().add(loginButton);
		
		JButton faceButton = new JButton("\u8868\u60C5");                //添加表情按钮
		faceButton.setBounds(45, 255, 93, 29);
		frame.getContentPane().add(faceButton);
		
		JButton sendButton = new JButton("\u53D1\u9001");                //发送文本按钮
		sendButton.setFont(new Font("宋体", Font.PLAIN, 18));
		sendButton.setBounds(45, 349, 113, 27);
		frame.getContentPane().add(sendButton);
		
		
		
		
		JButton sendFileButton = new JButton("\u53D1\u9001\u6587\u4EF6"); //文件传输按钮
		sendFileButton.setFont(new Font("宋体", Font.PLAIN, 18));
		sendFileButton.setBounds(238, 349, 113, 27);
		frame.getContentPane().add(sendFileButton);
		
		 
		
		
		
		nameTextField = new JTextField();                               //登录姓名
		nameTextField.setBounds(195, 15, 86, 24);
		frame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		PasswordTextField = new JTextField();                           //登录密码
		PasswordTextField.setBounds(295, 13, 86, 24);
		frame.getContentPane().add(PasswordTextField);
		PasswordTextField.setColumns(10);
		
		JLabel titleLabel = new JLabel("Instant Messaging");
		titleLabel.setBackground(Color.yellow);
		titleLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		titleLabel.setBounds(14, 13, 180, 24);
		frame.getContentPane().add(titleLabel);
		
		JLabel receipentlabel = new JLabel("Recipient:");        
		receipentlabel.setFont(new Font("宋体", Font.PLAIN, 18));
		receipentlabel.setBounds(45, 50, 107, 24);
		frame.getContentPane().add(receipentlabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 87, 435, 126);
		frame.getContentPane().add(scrollPane);
		
		
		contentArea = new JTextArea();
		contentArea.setLineWrap(true);
		scrollPane.setViewportView(contentArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(45, 286, 435, 66);
		frame.getContentPane().add(scrollPane_1);
		
//		String path6 = "D:\\socket\\chat\\img\\6.png";
//		ImageIcon icon6 = new ImageIcon(path6);
//		JLabel label6 = new JLabel(icon6);
//		facePanel.add(label6);
//		
		final JTextArea messageArea = new JTextArea();
		scrollPane_1.setViewportView(messageArea);
		messageArea.setLineWrap(true);
		
		JButton regestButton = new JButton("\u6CE8\u518C");
		regestButton.setBounds(472, 14, 63, 27);
		frame.getContentPane().add(regestButton);
		
		facePanel = new JPanel();                                //有关表情的jpanel
		facePanel.setVisible(false);
		facePanel.setBounds(141, 216, 121, 68);
		frame.getContentPane().add(facePanel);
		facePanel.setLayout(new GridLayout(2,3));
		String path1 = "D:\\socket\\chat\\img\\1.png";
		ImageIcon icon1 = new ImageIcon(path1);
		JLabel label1 = new JLabel(icon1);
		label1.setText("");
		facePanel.add(label1);
		String path2 = "D:\\socket\\chat\\img\\2.png";
		ImageIcon icon2 = new ImageIcon(path2);
		JLabel label2 = new JLabel(icon2);
		label2.setText("");
		facePanel.add(label2);
		String path3 = "D:\\socket\\chat\\img\\3.png";
		ImageIcon icon3 = new ImageIcon(path3);
		JLabel label3 = new JLabel(icon3);
		label3.setText("");
		facePanel.add(label3);
		String path4 = "D:\\socket\\chat\\img\\4.png";
		ImageIcon icon4 = new ImageIcon(path4);
		JLabel label4 = new JLabel(icon4);
		label4.setText("");
		facePanel.add(label4);
		String path5 = "D:\\socket\\chat\\img\\5.png";
		ImageIcon icon5 = new ImageIcon(path5);
		JLabel label5 = new JLabel(icon5);
		label5.setText("");
		facePanel.add(label5);
		String path6 = "D:\\socket\\chat\\img\\6.png";
		ImageIcon icon6 = new ImageIcon(path6);
		JLabel label6 = new JLabel(icon6);
		label5.setText("");
		facePanel.add(label6);
		
		//为上面jpanel中的包含表情的jlabel添加按钮事件
		label1.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}public void mousePressed(MouseEvent e) {}public void mouseExited(MouseEvent e) {}public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
			messageArea.append("<(￣ˇ￣)/ ");
			}});
		label2.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}public void mousePressed(MouseEvent e) {}public void mouseExited(MouseEvent e) {}public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
			messageArea.append("╭∩╮(︶︿︶)╭∩╮");
			}});
		label3.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}public void mousePressed(MouseEvent e) {}public void mouseExited(MouseEvent e) {}public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
			messageArea.append("(~*.*~)");
			}});
		label4.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}public void mousePressed(MouseEvent e) {}public void mouseExited(MouseEvent e) {}public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
			messageArea.append("(●﹏●)");
			}});
		label5.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}public void mousePressed(MouseEvent e) {}public void mouseExited(MouseEvent e) {}public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
			messageArea.append(" (ง •̀_•́)ง ");
			}});
		label6.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}public void mousePressed(MouseEvent e) {}public void mouseExited(MouseEvent e) {}public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
			messageArea.append(" "+"∠※ "+" ");
			}});
		sendButton.addActionListener(new ActionListener() {               //给发送文本按钮添加事件sendmessage
			public void actionPerformed(ActionEvent e) {
				String str = messageArea.getText();
				String content = "sendmassage" + "," + username+"," + reciever + "," + str;
				try {
					dos.writeUTF(content);
					dos.flush();
					System.out.println("用户发送请求："+content);
					messageArea.setText("");
					contentArea.append(username + ":    " + str +"    "+ "(" + getTime() + ")" + "\n");
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		regestButton.addActionListener(new ActionListener() {        //为注册按钮添加按钮事件
			public void actionPerformed(ActionEvent e) {
				username = nameTextField.getText();
				password = PasswordTextField.getText();
				String content = "regest" + " " + username + " " + password;
				try {
					dos.writeUTF(content);
					dos.flush();
					System.out.println("用户发送请求: " + content);
					JOptionPane.showMessageDialog(null, "注册成功", "注册提示",JOptionPane.WARNING_MESSAGE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		sendFileButton.addActionListener(new ActionListener() {           //为文件传输按钮添加事件sendfile
			public void actionPerformed(ActionEvent e) {
				JFrame jf = new JFrame();	
				jf.setVisible(true);
				jf.setBounds(700, 352, 525, 372);
				JFileChooser jfc = new JFileChooser();
				jfc.setVisible(true);
				jf.getContentPane().add(jfc);
				jfc.showDialog(new JLabel(), "发送");
				File file = jfc.getSelectedFile();
				String filepath = file.getAbsolutePath();
				String content = "sendfile" + "," + username + "," + reciever + "," + filepath;
				try {
					dos.writeUTF(content);
					dos.flush();
					System.out.println("用户发送请求：" + content);
					contentArea.append(username+": "+"发送文件"+filepath+"成功！"+"\n");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		faceButton.addActionListener(new ActionListener() {                //为表情按钮添加按钮事件
			public void actionPerformed(ActionEvent e) {
                if(facePanel.isVisible()){
                   facePanel.setVisible(false);
                }else{
                	facePanel.setVisible(true);
                }
				
			}
		});
		
		
		loginButton.addActionListener(new ActionListener() {              //给登陆按钮添加监听事件看是否可以登录成功
			public void actionPerformed(ActionEvent e) {                 
				username = nameTextField.getText();
				password = PasswordTextField.getText();
				String content = "login" + " " + username + " " + password;
				try {
					dos.writeUTF(content);
					dos.flush();
					System.out.println("用户发送请求:"+content);
					String str = dis.readUTF();
					System.out.println(str);
				    if(str.equals("true")){
				    	ClientThread client = new ClientThread(s);           //当登录成功时，客户端单独启用一个线程时时接受服务器端消息
						client.start();
						
						dos.writeUTF("getuserlist");                          //获取用户列表
						dos.flush();
						System.out.println("用户发送请求"+"getuserlist");
						String[] s = (dis.readUTF()).split(" ");
						for(int i = 0 ; i<s.length;i++){
							if(s[i]!=" "){
								comboBox.addItem(s[i]);
							}
						}
						System.out.println("用户登录后获取列表成功");
				    }else{
				    	JOptionPane.showMessageDialog(null, "如果没有注册请先注册", "登录提示",JOptionPane.WARNING_MESSAGE);  
				    }
				} catch (IOException e1) {
					System.out.println("发送登陆信息时出错");
					try {
						System.out.println(dis.readUTF());
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					e1.printStackTrace();
				}
				
			}
		});
		
	
		
		comboBox.addActionListener(new ActionListener() {             //获得reciever信息
			public void actionPerformed(ActionEvent e) {
				reciever = comboBox.getSelectedItem().toString();
			}
		});
	}
		
	
	
	
	public class ClientThread extends Thread{                                 //内部的客户端线程类
		Socket s ;
		public ClientThread(Socket s ){
			this.s = s;
		}
		public void run(){
			try {
				System.out.println("客户端接收消息的线程已经启动");
				DataInputStream dis = new DataInputStream(s.getInputStream());
				while(true){
					String command = dis.readUTF();
					if(command.startsWith("txt")){
					   String str = dis.readUTF();
					   contentArea.append(reciever+":     "+str + "(" + getTime() + ")" + "\n");
					}else{
						String filename = dis.readUTF();
						FileOutputStream output = new FileOutputStream(new File("D:\\socket\\chat"+"\\" + filename));
		            	int bytesRead = 0; 
		            	while(bytesRead != 255){      //read返回下一个数据字节
		            	  bytesRead = dis.read();
		            	  output.write(bytesRead);
		            	  output.flush();
//		            	  System.out.println(bytesRead);
		            	}
		            	output.close();
		            	System.out.println("客户端已经跳出循环接收成功！");
		            	contentArea.append(username+": "+"接收文件"+filename+"成功！"+"\n");
					}
				}
				
			} catch (IOException e) {
				System.out.println("内部客户端线程类出了问题~");
				e.printStackTrace();
			}
			
		}
	}


   public String getTime(){
	  Date date = new Date();
	  DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String time = format.format(date);
      return time;
   }
   public void addFace(){
	   //
   }
}
