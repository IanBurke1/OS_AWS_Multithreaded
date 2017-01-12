import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
/*
 * AWS 
 */
public class EchoServer {
//======MAIN METHOD=============================================================================================================================
  public static void main(String[] args) throws Exception {
    ServerSocket m_ServerSocket = new ServerSocket(2004,10); //Server socket listens on a port number for incoming requests.
    int id = 0; //Id used to identify each client
    //Infinite while loop
    while (true) {
      Socket clientSocket = m_ServerSocket.accept(); //this is a blocking method, causing the thread to stop and wait for incoming request
      ClientServiceThread cliThread = new ClientServiceThread(clientSocket, id++); //new thread to handle client request
      cliThread.start();
    }
  }
}

//Inner class
class ClientServiceThread extends Thread {
  Socket clientSocket;
  String message;
  String password;
  int clientID = -1;
  boolean running = true;
  ObjectOutputStream out;
  ObjectInputStream in;
  ArrayList<Account> list = new ArrayList<Account>(); //Create an ArrayList to store user accounts
  Account acc; //Account class variable
  Boolean flag = false; //Flag is used to stop the while loop at a particular instance

  //Constructor passing in client socket and id
  ClientServiceThread(Socket s, int i) {
    clientSocket = s;
    clientID = i;
  }
  //Method for messaging
  void sendMessage(String msg)
	{
		try{//Attempt the following. If something goes wrong, the flow jumps down to catch()
			out.writeObject(msg); 
			out.flush(); //Ensure all data sent by flushing buffers
			System.out.print("client>" + msg);
		}
		catch(IOException ioException){ //Deal with the error here. A try/catch stops a programme crashing on error
			ioException.printStackTrace();
		}
	}
  /*
   * Every thread/Runnable needs a run method. Any objects instantiated inside run() 
   * and not passed as arguments to other objects are thread-safe,
   *  i.e. there is no need to worry about synchronization
   * 
   */
  public void run() {
    System.out.println("Accepted Client : ID - " + clientID + " : Address - "
        + clientSocket.getInetAddress().getHostName());
    try //Attempt the following. If something goes wrong, the flow jumps down to catch()
    {
    	out = new ObjectOutputStream(clientSocket.getOutputStream()); 
		out.flush(); //Ensure all data sent by flushing buffers
		in = new ObjectInputStream(clientSocket.getInputStream());
		System.out.println("Accepted Client : ID - " + clientID + " : Address - "
		        + clientSocket.getInetAddress().getHostName());
		
		sendMessage("Connection successful\nEnter 1 to continue");
		do{
			try//Attempt the following. If something goes wrong, the flow jumps down to catch()
			{
				
				
				//A menu for the user with options to choose from..
				sendMessage("1.New User\n2.Login\n3.Close\n(Enter option 1-3)");
				message = (String)in.readObject(); 
				int option = new Integer(message); //user enter an integer in message
				
				if(option==1){
					//user enters their new account details
					sendMessage("Enter name: ");
					message = (String)in.readObject();
					String name = message;
					
					sendMessage("Enter Address: ");
					message = (String)in.readObject();
					String address = message;
					
					sendMessage("Enter bank A/C number: ");
					message = (String)in.readObject();
					int accNum = new Integer(message);
					
					sendMessage("Enter Username");
					message = (String)in.readObject();
					String username = message;
					
					sendMessage("Enter Password");
					message = (String)in.readObject();
					String password = message;
					
					int balance=0; //balance is set to 0
					//Add account details to ArrayList
					list.add(new Account(name, address, accNum, username, password, balance));
					
					sendMessage("Account: "+list); 
					
					
				}
				else if(option==2){
					sendMessage("Enter Username");
					message = (String)in.readObject();
					System.out.println(message);
					
					sendMessage("Enter Password");
					password = (String)in.readObject();
					System.out.println(password);
					//loop through Array List
					for(int i=0; i<list.size(); i++){
						//If their username = the username they entered and same with password then..
						if(list.get(i).getUsername().equals(message) && list.get(i).getPassword().equals(password))
						{
							//get their account
							acc=list.get(i);
							//while stops
							flag=true;
							sendMessage("Welcome back" + acc.getName() + "\nAccount Details " + acc);
						}
						else{
							//Access Denied
							sendMessage("Login details incorrect");
						}
					}
				}
				
			}
			catch(ClassNotFoundException classnot){ //Deal with the error here. A try/catch stops a programme crashing on error
				System.err.println("Data received in unknown format");
			}
			//If 
    	}while(!message.equals("3"));
      
		System.out.println("Ending Client : ID - " + clientID + " : Address - "
		        + clientSocket.getInetAddress().getHostName());
    } catch (Exception e) { //Deal with the error here. A try/catch stops a programme crashing on error
      e.printStackTrace();
    }
  }
}
