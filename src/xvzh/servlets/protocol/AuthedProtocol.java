package xvzh.servlets.protocol;

import javax.servlet.http.HttpServletRequest;

import xvzh.http.HttpRequest;
import xvzh.http.HttpResponse;

public class AuthedProtocol implements Protocol{

	@Override
	public HttpRequest parseRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null; 
	}

	@Override
	public String parseResponse(HttpResponse response) {
		// TODO Auto-generated method stub
		return "OK";
	}


}
