package xvzh.servlets;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xvzh.http.ApiInfo;
import xvzh.http.ApiType;
import xvzh.http.HttpApiMethod;
import xvzh.http.HttpRequest;
import xvzh.http.HttpResponse;
import xvzh.servlets.protocol.AuthedProtocol;
import xvzh.servlets.protocol.DefaultProtocol;
import xvzh.servlets.protocol.Protocol;
import xvzh.test.test1;

public class WeChatServlet extends HttpServlet{
	
	private static final long serialVersionUID = 2769490233827383495L;
	public Map<String, ApiInfo> apiMethods;
	public Map<String, Object> apiObjects;
	private static WeChatServlet weChatServlet;
	
	private WeChatServlet() {
		apiMethods = new HashMap<String,ApiInfo>();
		apiObjects = new HashMap<String,Object>();
//		try {
//			this.loadApis();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public static WeChatServlet getInstance() {
		if(weChatServlet == null) {
			weChatServlet = new WeChatServlet();
			return weChatServlet;
		}	else {
			return weChatServlet;
		}
	}
	
//	private void loadApis() throws Exception{
//		String baseDir = this.getClass().getResource("/").getFile();
//		System.out.println(baseDir);
//		String separator = File.separator;
//		String methodDir = baseDir+ "xvzh" + "/" + "apis";
//		System.out.println(methodDir);
//		File files = new File(methodDir);
//		for(File subFiles : files.listFiles()) {
//			String prefix = subFiles.getName();
//			for(String fileName : subFiles.list()) {
//				fileName = fileName.substring(0,fileName.lastIndexOf("."));
//				if(fileName.endsWith("HttpApi")) {
//					String className = "xvzh.apis." + prefix + "." + fileName;
//					Object apiObject = Class.forName(className).newInstance();
//					Method[] methods = Class.forName(className).getDeclaredMethods();
//					for(Method method : methods) {
//						HttpApiMethod annotation = method.getAnnotation(HttpApiMethod.class);
//						if(annotation != null) {
//							if(annotation.apiType() == ApiType.Default) {
//								apiMethods.put(method.getName().toLowerCase(), new ApiInfo(ApiType.Default, method));
//								apiObjects.put(method.getName().toLowerCase(), apiObject);
//							}
//							if(annotation.apiType() == ApiType.Authed) {
//								apiMethods.put(method.getName().toLowerCase(), new ApiInfo(ApiType.Authed, method));
//								apiObjects.put(method.getName().toLowerCase(), apiObject);
//							}
//						}
//					}
//				}
//			}
//		}
//	}

	public void registApi(Class<?> apiClass) throws InstantiationException, IllegalAccessException {
		Object apiObject = apiClass.newInstance();
		Method[] methods = apiClass.getDeclaredMethods();
		for(Method method : methods) {
			HttpApiMethod annotation = method.getAnnotation(HttpApiMethod.class);
			if(annotation != null) {
				if(annotation.apiType() == ApiType.Default) {
					apiMethods.put(method.getName().toLowerCase(), new ApiInfo(ApiType.Default, method));
					apiObjects.put(method.getName().toLowerCase(), apiObject);
				}
				if(annotation.apiType() == ApiType.Authed) {
					apiMethods.put(method.getName().toLowerCase(), new ApiInfo(ApiType.Authed, method));
					apiObjects.put(method.getName().toLowerCase(), apiObject);
				}
			}
		}
	}
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getPathInfo().substring(1);
		String pathInfo[] = path.split("/");
		String apiName = pathInfo == null ? null : pathInfo[0];
		apiName = apiName.toLowerCase();
		
		if(apiMethods.keySet().contains(apiName) == false) {
			sendResp(response, HttpServletResponse.SC_NOT_FOUND, "API NOT FOUND");
		} else {
			ApiInfo apiInfo = this.apiMethods.get(apiName);
			ApiType apiType = apiInfo.apiType;
			Protocol protocol = this.getProtocol(apiType);
			HttpRequest httpRequest = protocol.parseRequest(request);
			HttpResponse httpResponse = new HttpResponse(response);
			try {
				this.process(httpRequest, httpResponse, apiName);
			} catch (Exception e) {
				e.printStackTrace();
				this.sendResp(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "SERVER ERROR");
			}
			String result = protocol.parseResponse(httpResponse);
			this.sendResp(response, 200, result);
		}
	}
	
	private void sendResp(HttpServletResponse response, int httpCode, String text) throws IOException {
    	if (text == null || text.length() == 0) {
    		response.setStatus(httpCode);
		}else {
			 byte[] data = text.getBytes();
		        response.setStatus(httpCode);
		        response.setContentType("text/html;charset=UTF-8");
		        response.setContentLength(data.length); 

		        OutputStream output = response.getOutputStream();
		        try {
		            output.write(data);
		            output.flush();
		        } finally {
		            output.close();
		        }
		}
       
    }

	private Protocol getProtocol(ApiType apiType) {
		if(apiType == ApiType.Authed) {
			return new AuthedProtocol();
		} else {
			return new DefaultProtocol();
		}
	}
	
	private void process(HttpRequest httpRequest, HttpResponse httpResponse, String apiName) throws Exception {
		Method method = this.apiMethods.get(apiName).apiMethod;
        Object object = this.apiObjects.get(apiName);
        method.setAccessible(true);
        method.invoke(object, httpRequest, httpResponse);
	}
	
	
}
