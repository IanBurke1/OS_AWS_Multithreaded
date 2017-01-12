
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client{
	
	Socket requestSocket; //Creating a client socket
	ObjectOutputStream out; //Serialise / marshal a request to the server
 	ObjectInputStream in; //Deserialise / unmarshal a response from server
 	String message="";
 	String ipaddress; //Ip address to connect to server
 	Scanner stdin; //Scanner used to scan in user input
	Client(){} // Null Constructor
	
	//Run method used for threads
	void run()
	{
		stdin = new Scanner(System.in); //scanner input
		
		try{ //Attempt the following. If something goes wrong, the flow jumps down to the catch()
			
			//1. creating a socket to connect to the server
			//System.out.println("Please Enter your IP Address");
			//ipaddress = stdin.next();
			String ipaddress = "127.0.0.1";
			requestSocket = new Socket(ipaddress, 2004);
			System.out.println("Connected to Amazon Web Server");
			
			//2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush(); //Ensure all data sent by flushing buffers
			in = new ObjectInputStream(requestSocket.getInputStream());
			
			//3: Communicating with the server
			do{ //Do the following while...
				try //Attempt the following. If something goes wrong, the flow jumps down to the catch()
				{
						//Reading in the message from the server
						message = (String)in.readObject(); //Deserialise
						System.out.print(message); 
						message = stdin.next();
						//Send message back to server
						sendMessage(message);
						
						
						
				}
				catch(ClassNotFoundException classNot) //Deal with the error here. A try/catch stops a programme crashing on error
				{
					System.err.println("data received in unknown format");
				}
			}while(!message.equals("3")); //loop through the do...while the message does not equal to "bye"
		}
		catch(UnknownHostException unknownHost){ //Deal with the error here. A try/catch stops a programme crashing on error
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException){ //Deal with the error here. A try/catch stops a programme crashing on error
			ioException.printStackTrace();
		}
		finally{
			//4: Closing connection
			try{ //Attempt the following. If something goes wrong, the flow jumps down to the catch()
				in.close(); //Close the InputStream
				out.close(); //Close the OutputStream
				requestSocket.close(); //Close the socket
			}
			catch(IOException ioException){ //Deal with the error here. A try/catch stops a programme crashing on error
				ioException.printStackTrace();
			}
		}
	}
	//A Method passing in a message. Used to send back to server
	void sendMessage(String msg)
	{
		try{ //Attempt the following. If something goes wrong, the flow jumps down to the catch()
			out.writeObject(msg); //Serialise
			out.flush(); //Ensure all data sent by flushing buffers
			System.out.println(msg);
		}
		catch(IOException ioException){ //Deal with the error here. A try/catch stops a programme crashing on error
			ioException.printStackTrace();
		}
	}
//=====MAIN METHOD============================================================================================================================
	public static void main(String args[])
	{
		//Creating an Requester object and calling a method.
		Client client = new Client();
		client.run();
	}
}
