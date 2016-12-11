package xvzh.wechat.util;

import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.util.ssl.SslContextFactory;

public class BrowserSimulator {
	private static SslContextFactory sslContextFactory = new SslContextFactory();
	private static HttpClient httpClient = new HttpClient(sslContextFactory);

	public static ContentResponse textPost(String postData, String url)
			throws Exception {
		if (httpClient.isStarted() == false) {
			httpClient.setFollowRedirects(false);
			httpClient.start();
		}
		ContentResponse response = httpClient.POST(url)
				.content(new StringContentProvider(postData)).send();
		return response;
	}

	public static ContentResponse Get(Map<String, String> params, String url)
			throws Exception {
		if (httpClient.isStarted() == false) {
			httpClient.setFollowRedirects(false);
			httpClient.start();
		}
		StringBuffer sb = new StringBuffer(url);
		sb.append("?");
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			String value = params.get(key);
			sb.append(key).append("=").append(value).append("&");
		}
		sb.deleteCharAt(sb.length() - 1);
		url = sb.toString();
		ContentResponse response = httpClient.GET(url);
		return response;
	}
	
	public static ContentResponse fileUpload(String url) throws Exception {
		if (httpClient.isStarted() == false) {
			httpClient.setFollowRedirects(false);
			httpClient.start();
		}

		ContentResponse response = httpClient
				.POST("http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=noewFZ36DcdJCgesA4PtJHHlEy8yTc8XtlpRjluK05iRRXKVPsMBrY42w-Ftb8GHhaMo33oAKyDehrNLC_jPgy5LSKVeLV93CspSPwni1GdDgA-raBixVmJcgXYcV5t1SUXcAIAGIL&type=image")
				.file(Paths.get("F:\\Camera\\20140711_202656.jpg"))
		.send();
		return response;
	}
	
	public static void shutDown() throws Exception {
		httpClient.stop();
	}
	
	public static void main(String args[]) throws Exception {
		ContentResponse response = fileUpload("http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=noewFZ36DcdJCgesA4PtJHHlEy8yTc8XtlpRjluK05iRRXKVPsMBrY42w-Ftb8GHhaMo33oAKyDehrNLC_jPgy5LSKVeLV93CspSPwni1GdDgA-raBixVmJcgXYcV5t1SUXcAIAGIL&type=image");
		System.out.println(response.getContentAsString());
	}
}
