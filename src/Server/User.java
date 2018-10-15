package Server;

import java.net.Socket;
import java.util.ArrayList;


public class User {
   String username;
   String password;
   Socket socket = null;
   ArrayList<User> userlist = null;
   public User(String username,String password){
	   this.username = username;
	   this.password = password;
   }
}

