package xvzh.sensor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String args[]) throws IOException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(8080);
		Socket socket = serverSocket.accept();
		OutputStream outputStream = socket.getOutputStream();
		File file = new File("e://11");
		FileInputStream fileInputStream = new FileInputStream(file);
		byte[] buffer = new byte[1024*1024*100];
		fileInputStream.read(buffer);
		outputStream.write(buffer);
		outputStream.close();
		socket.close();
	}
}
