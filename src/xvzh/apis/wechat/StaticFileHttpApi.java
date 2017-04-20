package xvzh.apis.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import xvzh.http.ApiType;
import xvzh.http.HttpApiMethod;
import xvzh.http.HttpRequest;
import xvzh.http.HttpResponse;

public class StaticFileHttpApi {
	@HttpApiMethod(apiType = ApiType.Default)
	public void file(HttpRequest request, HttpResponse response) throws IOException {
		String fileUrl = request.request.getPathInfo().substring(5);
		OutputStream os = null;
		InputStream is = null;
		try {
			os = response.response.getOutputStream();
			response.response.setContentType("video/mpeg4");
			is = this.getClass().getResourceAsStream(fileUrl);
			byte[] buffer = new byte[512];
			int length = -1;
			while((length = is.read(buffer)) != -1) {
				os.write(buffer, 0, length);
				os.flush();
			}
			is.close();
		} catch (Exception e) {
			response.respStr = "文件不存在";
		} 
	}
}
