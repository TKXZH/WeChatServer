package xvzh.apis.wechat;

import java.util.Arrays;

import xvzh.http.ApiType;
import xvzh.http.HttpApiMethod;
import xvzh.http.HttpRequest;
import xvzh.http.HttpResponse;
import xvzh.wechat.util.SHA1;

public class FirstTestHttpApi {
	@HttpApiMethod(apiType = ApiType.Default)
	public void test(HttpRequest request, HttpResponse response)  {
		
		String token = "xvzh2016";
		String signature = request.request.getParameter("signature");
		String timestamp = request.request.getParameter("timestamp");
		String nonce = request.request.getParameter("nonce");
		String echostr = request.request.getParameter("echostr");
		
		String res = "";
		String[] temp = new String[]{token, timestamp, nonce};
		Arrays.sort(temp);
		for(String str : temp) {
			res += str;
		}
		res = SHA1.encrypt(res);
		if(res.equals(signature)) {
			response.respStr = echostr;
		}	else {
			response.respStr = "";
		}
		
	}
}
