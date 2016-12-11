package xvzh.servlets.protocol;

import javax.servlet.http.HttpServletRequest;

import xvzh.http.HttpRequest;
import xvzh.http.HttpResponse;

public interface Protocol {
	public HttpRequest parseRequest(HttpServletRequest request);
	public String parseResponse(HttpResponse response);
}
