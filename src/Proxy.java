import java.io.*;
import java.net.*;
import java.util.Arrays;


public class Proxy {
private static final String[] blacklist = new String[50];
private static int index = 0;
private static final int off = 0; 
	public static void blacklist(String stringToBlacklist){
		blacklist[index] = stringToBlacklist;
		index++;
	}

	public static void main(String[] args) throws IOException{
		Proxy proxy = new Proxy();
		blacklist("imgur.com");
		while(true){
		proxy.run();
		}
	}

	public void run(){

		try {

		
				ServerSocket serverSocket = new ServerSocket(10000);
				Socket clientSocket = serverSocket.accept();

				InputStream incommingIS = clientSocket.getInputStream();
				OutputStream toClient = clientSocket.getOutputStream();

				byte[] request = new byte[8196]; // byte buffer into which request is read
				byte[] reply = new byte[4096];

				int len = incommingIS.read(request); //length of buffered bits
				
				String str = new String(request, "UTF-8");
				System.out.println(str);
				for(int i = 0; i < blacklist.length; i++){
					if(str.contains(blacklist[i])){
				
					String blacklistResponseString = "<h1>Blacklisted</h1>";
					byte[] blacklistResponseBytes = blacklistResponseString.getBytes();
					toClient.write(blacklistResponseBytes, off, blacklistResponseBytes.length );
					System.out.println("blacklisted");
					System.exit(0); 
					}
				}

				if(len > 0){
					//send off to internet
					Socket iNetSocket = new Socket("localhost", 80); //port 80 yo
					InputStream response = iNetSocket.getInputStream();
					OutputStream sendOutput = iNetSocket.getOutputStream();
					sendOutput.write(request,off,len);
					sendOutput.flush();

					int bytesback = response.read(reply);
					toClient.write(reply,off,bytesback);
					toClient.flush();

				}
			          
		}

		catch(IOException e){

		}
	}

}
