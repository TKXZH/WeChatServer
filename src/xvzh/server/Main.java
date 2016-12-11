package xvzh.server;

public class Main {
	public static void main(String args[]) throws Exception {
		WeChatServer server = new WeChatServer();
		server.startService();
	}
}
