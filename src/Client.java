import java.io.*;
import java.net.*;



public class Client {
	public static void main(String[] args) throws Exception{
		Client client = new Client();
		client.run();
	}
	
	public void run(){
		try{
			Socket clientSocket = new Socket("localhost", 10000);
			PrintStream output = new PrintStream(clientSocket.getOutputStream());
			output.println("www.google.com");
			
			InputStreamReader message = new InputStreamReader(clientSocket.getInputStream());
			BufferedReader buffer = new BufferedReader(message);
			String msg = buffer.readLine();
			System.out.println(msg);
			
		}catch(IOException e){
			
		}
	}
}
