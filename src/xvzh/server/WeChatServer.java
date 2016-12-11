package xvzh.server;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import xvzh.servlets.WeChatServlet;

public class WeChatServer {
	//Jetty服务器
	private static Server jettyServer;
	private static List<Handler> handlers;
	
	private static void initServer() {
        if(jettyServer != null) {
            System.err.println("服务器已经初始化!");
        }   else {
            jettyServer = new Server(80);
            handlers = new LinkedList<Handler>();
        }

    }
	
	public void registServlet(String prefix, Servlet servlet) {
        String contexPath = "/" + prefix;
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath(contexPath);
        handler.addServlet(new ServletHolder(servlet),"/*");
        handlers.add(handler);
    }
	
	public void startService() throws Exception{
		initServer();
		registServlet("wechat", WeChatServlet.getInstance());
		Init.init();
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(handlers.toArray(new Handler[0]));
        jettyServer.setHandler(contexts);
        jettyServer.start();
    }
	
}
