package xvzh.http;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class HttpResponse {
	public HttpServletResponse response;
	public String respStr;
	public Map<String, Object> respMap;
	
	public HttpResponse(HttpServletResponse response) {
		this.response = response;
		respMap = new HashMap<String, Object>();
	}
}
