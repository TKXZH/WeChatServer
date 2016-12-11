package xvzh.wechat.util.menu;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import xvzh.wechat.util.AccessTokenManager;

public class MenuCreator {
	public static void createMenu(WeChatMenu menu) throws Exception {
		StringBuffer url = new StringBuffer("https://api.weixin.qq.com/cgi-bin/menu/create?");
		url.append("access_token=" + AccessTokenManager.getAccessToken());
		
		SslContextFactory sslContextFactory = new SslContextFactory();
		HttpClient httpClient = new HttpClient(sslContextFactory);
		httpClient.setFollowRedirects(false);
		httpClient.start();
		
		ContentResponse response = httpClient.POST(url.toString()).content(new StringContentProvider(menu.toString())).send();
		System.out.println(response.getContentAsString());
		httpClient.stop();
	}
	
	
	public static void main(String args[]) throws Exception {
		ClickSingleButton MainButton1_SubButton1 = new ClickSingleButton("温度传感器","xvzh1");
		ClickSingleButton MainButton1_SubButton2 = new ClickSingleButton("湿度传感器","xvzh2");
		ClickSingleButton MainButton1_SubButton3 = new ClickSingleButton("人体感应", "xvzh3");
		ViewSingleButton MainButton1_SubButton4 = new ViewSingleButton("实时视频", "http://xvzh.tunnel.qydev.com/wechat/getVideo");
		CompoundButton MainButton1 = new CompoundButton(MainButton1_SubButton1, MainButton1_SubButton2, MainButton1_SubButton3, MainButton1_SubButton4, "监测");
		
		ClickSingleButton MainButton2_SubButton1 = new ClickSingleButton("开/关灯", "xvzh4");
		ClickSingleButton MainButton2_SubButton2 = new ClickSingleButton("相机左转", "xvzh5");
		ClickSingleButton MainButton2_SubButton3 = new ClickSingleButton("相机右转", "xvzh6");
		ClickSingleButton MainButton2_SubButton4 = new ClickSingleButton("实时拍照", "xvzh7");
		CompoundButton MainButton2 = new CompoundButton(MainButton2_SubButton1, MainButton2_SubButton2, MainButton2_SubButton3, MainButton2_SubButton4, "控制");
//		ViewSingleButton MainButton1 = new ViewSingleButton("视频监控", "http://xvzh.tunnel.qydev.com/wechat/getVideo");
//		ClickSingleButton MainButton2_SubButton1 = new ClickSingleButton("开灯","xvzh2");
//		ClickSingleButton MainButton2_SubButton2 = new ClickSingleButton("关灯","xvzh3");
//		ClickSingleButton MainButton2_SubButton3 = new ClickSingleButton("实时拍照","xvzh4");
//		CompoundButton MainButton2 = new CompoundButton(MainButton2_SubButton1, MainButton2_SubButton2,MainButton2_SubButton3,mainButton2_SubButton4,"控制");
		WeChatMenu weChatMenu = new WeChatMenu(MainButton1, MainButton2);
		MenuCreator.createMenu(weChatMenu);
	}
}
