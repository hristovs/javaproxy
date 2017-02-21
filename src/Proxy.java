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
	public static void checkBlacklist(String str, OutputStream toClient){
		for(int i = 0; i < index; i++){
			if(str.contains(blacklist[i])){
				PrintWriter response = new PrintWriter(toClient);
				response.print("Server is blacklisted");
				response.flush();
				System.out.println("blacklisted");
				System.exit(0); 
			}
		}	
	}
	public static void main(String[] args) throws IOException{
		Proxy proxy = new Proxy();
		blacklist("imgur.com");
		proxy.run();
	} 
	
	public void run(){
		try {
			final byte[] request = new byte[8196]; // byte buffer into which request is read
			byte[] reply = new byte[4096];

			ServerSocket serverSocket = new ServerSocket(10000);
			Socket clientSocket;
			while(true){
				clientSocket = serverSocket.accept(); 
				//client streams
				final InputStream fromClient = clientSocket.getInputStream();
				final OutputStream toClient = clientSocket.getOutputStream();
				InetAddress host= InetAddress.getLocalHost();
				Socket server = new Socket(host, 80); //port 80 

			//server streams
				final InputStream response = server.getInputStream();
				final OutputStream sendOutput = server.getOutputStream();

				Thread t = new Thread() {
					public void run() {
						int len;
						try {
							while ((len = fromClient.read(request)) != -1) {
								String str = new String(request, "UTF-8");
								System.out.println(str);
								checkBlacklist(str, toClient);

								sendOutput.write(request,off,len);
								sendOutput.flush();
							}
						} catch (IOException e) {
						}


						try {
							sendOutput.close();
						} catch (IOException e) {
						}
					}
				};


				t.start();

				int bytesBack;
				while ((bytesBack = response.read(reply)) != -1) {
					toClient.write(reply,off,bytesBack);
					toClient.flush();
				}


			}

		}
		catch(IOException e){

		}


	}

}
