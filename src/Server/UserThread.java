package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.util.ArrayList;


public class UserThread extends Thread{
	Socket s ;
	ArrayList<User> userlist;
	
   public UserThread(Socket s,ArrayList<User> userlist){    //���췽��
	   this.s = s;
	   this.userlist = userlist;
   }
   
   public void run(){
	   boolean islogin = false;
	   DataInputStream dis = null;
	   DataOutputStream dos = null;
	   String content;
	  try {
		 dis = new DataInputStream(s.getInputStream());
		 dos = new DataOutputStream(s.getOutputStream());
		 content = dis.readUTF();
		 System.out.println("接收的信息是:"+content);
         if(content.startsWith("login")){
        	 String[] str = content.split(" ");
        	 String username = str[1];
        	 String password = str[2];
        	 islogin = islogin(userlist,username,password);
        	  if(islogin){
        		  System.out.println(username+"登录成功！");
        		  addSocket(username,userlist,s);
                  dos.writeUTF("true");
                  dos.flush();
        	  }else{
        		  System.out.println(username+"登录不成功");
                  dos.writeUTF("false");
                  dos.flush();
              
        	  }
         }
	  } catch (IOException e) {
		e.printStackTrace();
	  }
	  
	   while(islogin){
		    try {
				content = dis.readUTF();
				System.out.println("发送的消息是："+content);
				
				if(content.startsWith("getuserlist")){
					String str ="";
					for(int i = 0; i<userlist.size();i++){
						 str += userlist.get(i).username;
						 str += " ";
					}str += "all";
					dos.writeUTF(str);
					dos.flush();
				}
				else if(content.startsWith("sendmassage")){
                   String reciever = content.split(",")[2];
                   String detail = content.split(",")[3];
                   System.out.println(detail);
                   
                   if(!reciever.equals("all")){
                   User tempuser = findUser(reciever,userlist);
                   try{
                	   
                	   Socket temps = tempuser.socket;
                	   DataOutputStream doss = new DataOutputStream(temps.getOutputStream());
                	   doss.writeUTF("txt");
                	   doss.flush();
                	   doss.writeUTF(detail);
                	   doss.flush();
                	   System.out.println("消息发送成功");
                   }catch(Exception e){
                	   e.printStackTrace();
                   }
				   
				  }else{
					  for(int i = 0 ;i <= userlist.size()-1;i++){
						User tempUer = findUser(userlist.get(i).username,userlist);
						DataOutputStream doss = new DataOutputStream(tempUer.socket.getOutputStream());
						doss.writeUTF("txt");
	                	doss.flush();
						doss.writeUTF(detail);
	                	doss.flush();
						
					  }
				  }
                }
				
//				else if(content.startsWith("regest")){
//					// "regest" + " " + username + " " + password
//					String username = content.split(" ")[1];
//					String password = content.split(" ")[2];
//					Server.User newUser = new Server.User(username, password);
//					userlist.add(newUser);
//				}
				
				else if(content.startsWith("sendfile")){
					String reciever = content.split(",")[2];
					String filepath = content.split(",")[3];
					System.out.println(filepath);
					String[] files = filepath.split("\\\\");
					String filename = files[files.length-1];
					User tempuser = findUser(reciever,userlist);
					 try{
	                	   Socket temps = tempuser.socket;
	                	   DataOutputStream doss = new DataOutputStream(temps.getOutputStream());
	                	   doss.writeUTF("file");
	                	   doss.flush();
	                	   doss.writeUTF(filename);
	                	   doss.flush();

	                	   RandomAccessFile outfile = new RandomAccessFile(filepath, "r");
	                	   int bytesRead = 0 ; 
	                	   while(bytesRead!=-1){
	                		   bytesRead = outfile.read();
	                		   doss.write(bytesRead);
	                		   doss.flush();
//	                		   System.out.println(bytesRead);
	                	   }System.out.println("发送文件成功！");
	                	   
	                   }catch(Exception e){
	                	   e.printStackTrace();
	                   }
					
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	   }
	  
   }
   
   public boolean islogin(ArrayList<User> userlist, String username, String password){  //�����û����������жϸ��û��Ƿ�����ж����½���
	   User tempuser = null;
	   for(int i = 0 ; i < userlist.size() ; i++){
		  tempuser = userlist.get(i);
		  if(tempuser.username.equals(username)&&tempuser.password.equals(password)){
              return true;
          }
	   }
	   return false;
   }
   
   public void addSocket(String username, ArrayList<User> userlist, Socket s){
	   for(int i = 0 ; i < userlist.size() ; i ++){
		 if(userlist.get(i).username.equals(username)){
			 userlist.get(i).socket = s;
		 }
	   }
   }
   public User findUser(String username, ArrayList<User> userlist){
	   for(int i = 0 ; i < userlist.size() ; i++){
		   if(userlist.get(i).username.equals(username)){
			   return userlist.get(i);
		   }
	   }
	  return null;
   }
   
}
