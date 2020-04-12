package frc.robot.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Rishi Rao
 *
 */
public class ServerListenerThread extends Thread{

	private int port;
	private String html = "<html><head><title>Test</title></head><body><h1>test</h1></body></html>"; 
	private ServerSocket serverSocket;
	public ServerListenerThread(int port) throws IOException {
		this.port = port;
		this.serverSocket = new ServerSocket(port);
	}
	public void setHtml(String html){
		this.html = html;
	}
	
	@Override
	public void run() {
		try {
			while(serverSocket.isBound() && !serverSocket.isClosed()) {
			//ServerSocket serverSocket = new ServerSocket(port);
			Socket socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			
			
			final String CRLF = "\r\n"; // 13, 10
			String response = "HTTP/1.1 200 OK" + CRLF + "Content-Length: " + html.getBytes().length + CRLF + CRLF + html+ CRLF + CRLF;
			
			outputStream.write(response.getBytes());
			
			
			
			inputStream.close();
			outputStream.close();
			socket.close();
			}
			//serverSocket.close();
		} catch(Exception e) {
			e.printStackTrace();
			
		}
	}

}
