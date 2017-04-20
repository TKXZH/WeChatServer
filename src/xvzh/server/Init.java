package xvzh.server;

import xvzh.apis.wechat.JsonHttpApi;
import xvzh.apis.wechat.MessageServiceHttpApi;
import xvzh.apis.wechat.StaticFileHttpApi;
import xvzh.apis.wechat.VideoHttpApi;
import xvzh.servlets.WeChatServlet;

public class Init {
	//此方法用来加载所有的HttpApi类，避免使用反射加载带来的麻烦
	public static void init() throws InstantiationException, IllegalAccessException {
		WeChatServlet.getInstance().registApi(MessageServiceHttpApi.class);
		WeChatServlet.getInstance().registApi(JsonHttpApi.class);
		WeChatServlet.getInstance().registApi(VideoHttpApi.class);
		WeChatServlet.getInstance().registApi(StaticFileHttpApi.class);
	}
}
