// Code based on https://www.geeksforgeeks.org/socket-programming-in-java/
import java.net.*; 
import java.io.*; 

public class ServerTimeout 
{ 
	//initialize socket and input stream 
	private Socket		 socket = null; 
	private ServerSocket server = null; 
	private DataInputStream in	 = null; 

	// constructor with port 
	public ServerTimeout(int port) 
	{ 
		// starts server and waits for a connection 
		try
		{ 
			server = new ServerSocket(port); 
			System.out.println("ServerTimeout started"); 

			System.out.println("Waiting for a client on port " + port);

			socket = server.accept(); 
			socket.setSoTimeout(5*1000);
			System.out.println("Client accepted"); 

			// takes input from the client socket 
			in = new DataInputStream( 
				new BufferedInputStream(socket.getInputStream())); 

			String line = ""; 

			// reads message from client until "Over" is sent 
			while (!line.equals("Over")) 
			{ 
				try
				{ 
					line = in.readLine(); 
					System.out.println(line); 

				} 
				catch(SocketTimeoutException ste)
				{
					System.out.println("Timeout occurred");
					break;
				}
				catch(IOException i) 
				{ 
					System.out.println(i); 
				} 
			} 
			System.out.println("Closing connection"); 

			// close connection 
			socket.close(); 
			in.close(); 
		} 
		catch(IOException i) 
		{ 
			System.out.println(i); 
		} 
	} 

	public static void main(String args[]) 
	{ 
		ServerTimeout server = new ServerTimeout(5000); 
	} 
} 
