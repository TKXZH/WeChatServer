package xvzh.servlets.protocol;

import javax.servlet.http.HttpServletRequest;

import xvzh.http.HttpRequest;
import xvzh.http.HttpResponse;

public class DefaultProtocol implements Protocol{

	@Override
	public HttpRequest parseRequest(HttpServletRequest request) {
		HttpRequest httpRequest = new HttpRequest(request);
		return httpRequest;
	}

	@Override
	public String parseResponse(HttpResponse response) {
		if(response.respStr == null) {
			return "";
		}	else {
			return response.respStr;
		}
	}

}
