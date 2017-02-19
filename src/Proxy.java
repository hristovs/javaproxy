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
	/*InputStreamReader input = new InputStreamReader(clientSocket.getInputStream());
	BufferedReader buffer = new BufferedReader(input);

	*/
	       InputStream incommingIS = clientSocket.getInputStream();
           byte[] b = new byte[8196]; // byte buffer into which request is read
           int len = incommingIS.read(b); //length of buffered bits
           int off = 0; // offset of 0
           if(len > 0 ){
        	   // formatting
           
           //send off to internet
           Socket iNetSocket = new Socket("localhost", 80); //port 80 yo
           OutputStream sendFormattedOutput = iNetSocket.getOutputStream();
           sendFormattedOutput.write(b,off,len);
           }
           
           }
           
	catch(IOException e){
		
	}
}

}
