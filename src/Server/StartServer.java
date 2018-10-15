package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class StartServer {

	public static void main(String[] args) {
		 ArrayList<User> userlist = new ArrayList<User>();
		 User aa = new User("aa","aa");
		 User bb = new User("bb","bb");
		 User cc = new User("cc","cc");
		 User dd = new User("dd","dd");
		 
		 userlist.add(aa);userlist.add(bb);userlist.add(cc);userlist.add(dd);
		 
		 try {
			 ServerSocket ss = new ServerSocket(7879);
			 System.out.println("服务端口已启动");
			 while(true){
				 Socket s = ss.accept();
				 UserThread user = new UserThread(s,userlist);
				 user.start();
			 }
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 
	
	}

}
