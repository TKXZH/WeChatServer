package xvzh.wechat.util;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 微信token管理器，根据超时时间被动刷新token
 * @author xvzh
 *
 */
public class AccessTokenManager {
	//private static final String AppID = "wx66f32c4a6df1aef7";
	//private static final String AppSecret = "f6e905816ef8974e44babf7b2f86a620";
	
	private static final String TestAppID = "";
	private static final String TestAppSecret = "";
	private static AccessToken accessToken;
	
	public static String getAccessToken() throws Exception {
		if(accessToken == null) {
			accessToken = new AccessToken();
		}
		
		if(accessToken.tokenStr == null || accessToken.expireTime <= System.currentTimeMillis()) {
			refreshAccessToken(accessToken);
		}
		
		return accessToken.tokenStr;
	}
	
	private static void refreshAccessToken(AccessToken accessToken) throws Exception {
		
		StringBuffer url = new StringBuffer("https://api.weixin.qq.com/cgi-bin/token?");
		url.append("grant_type=client_credential");
		url.append("&appid=" + TestAppID);
		url.append("&secret=" + TestAppSecret);
		
		SslContextFactory sslContextFactory = new SslContextFactory();
		HttpClient httpClient = new HttpClient(sslContextFactory);
		httpClient.setFollowRedirects(false);
		httpClient.start();
		
		ContentResponse response = httpClient.GET(url.toString());
		JSONObject json = JSON.parseObject(response.getContentAsString());
		String access_token = json.getString("access_token");
		String expires_in = json.getString("expires_in");
		if(access_token == null) {
			throw new Exception("token access error!!!");
		} else {
			accessToken.setTokenStr(access_token);
			accessToken.setExpireTime(System.currentTimeMillis() + Long.parseLong(expires_in) * 1000);
		}
		httpClient.stop();
		
	}
}

class AccessToken {
	String tokenStr;
	long expireTime;
	
	public String getTokenStr() {
		return tokenStr;
	}
	public void setTokenStr(String tokenStr) {
		this.tokenStr = tokenStr;
	}
	public long getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}
}
