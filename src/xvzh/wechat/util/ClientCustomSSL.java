package xvzh.wechat.util;

import java.io.File;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * This example demonstrates how to create secure connections with a custom SSL
 * context.
 */
public class ClientCustomSSL {
	/**
	 * 
	 * @param file 文件对象
	 * @return String pictureID
	 * @throws Exception
	 */
	public static String picUpload(File file) throws Exception {
		URI uri = new URIBuilder()
		.setScheme("https")
		.setHost("api.weixin.qq.com")
		.setPath("/cgi-bin/media/upload")
		.setParameter("access_token", AccessTokenManager.getAccessToken())
		.setParameter("type", "image")
		.build();
		System.out.println("called");
		FileBody fileBody = new FileBody(file);
		MultipartEntity reqEntity = new MultipartEntity();
		reqEntity.addPart("media", fileBody);
		HttpPost httppost = new HttpPost(uri);
		httppost.setEntity(reqEntity);
		HttpClient httpClient = HttpClientFactory.getHttpClient();
		HttpResponse response = httpClient.execute(httppost);
		String responseStr = EntityUtils.toString(response.getEntity());
		String media_id = (String)JSONObject.parseObject(responseStr).get("media_id");
		return media_id;
	}
	
	public static String picUploadByByte(byte[] pic) throws Exception {
		URI uri = new URIBuilder()
		.setScheme("https")
		.setHost("api.weixin.qq.com")
		.setPath("/cgi-bin/media/upload")
		.setParameter("access_token", AccessTokenManager.getAccessToken())
		.setParameter("type", "image")
		.build();
		
		ByteArrayBody bab = new ByteArrayBody(pic, "image/jpeg","realTime.jpg");
		MultipartEntity reqEntity = new MultipartEntity();
		reqEntity.addPart("media", bab);
		HttpPost httppost = new HttpPost(uri);
		httppost.setEntity(reqEntity);
		HttpClient httpClient = HttpClientFactory.getHttpClient();
		HttpResponse response = httpClient.execute(httppost);
		String responseStr = EntityUtils.toString(response.getEntity());
		String media_id = (String)JSONObject.parseObject(responseStr).get("media_id");
		return media_id;
	}
	
	public static byte[] getRealTimePic() throws Exception {
		String url = "http://127.0.0.1:8080?action=snapshot";
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = HttpClientFactory.getHttpClient().execute(httpGet);
		byte[] pic = StreamUtil.File2byte(response.getEntity().getContent());
		return pic;
		
	}
	
}
