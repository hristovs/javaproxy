import java.io.*;
import java.net.*;


public class Proxy {
	
	
	
	
public static void main(String[] args) throws IOException{
  Proxy proxy = new Proxy();
  proxy.run();
}

public void run(){
	
	try {
	ServerSocket serverSocket = new ServerSocket(10000);
	Socket clientSocket = serverSocket.accept();
	InputStreamReader input = new InputStreamReader(clientSocket.getInputStream());
	BufferedReader buffer = new BufferedReader(input);
	
	String msg = buffer.readLine();
	if(msg!= null){
		String address = msg;
	}
	}
	catch(IOException e){
		
	}
}

}
