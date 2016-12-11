package xvzh.apis.wechat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import xvzh.http.ApiType;
import xvzh.http.HttpApiMethod;
import xvzh.http.HttpRequest;
import xvzh.http.HttpResponse;

public class VideoHttpApi {
	@HttpApiMethod(apiType = ApiType.Default)
	public void getVideo(HttpRequest request, HttpResponse response) throws IOException {
		String string = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\""+
				"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"+
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">"+
				"<head>"+
				"<title>MJPEG-Streamer</title>"+
				"</head>"+
				"<script type=\"text/javascript\">"+
				"var imageNr = 0;"+
				"var finished = new Array();"+
				"var paused = false;"+
				"function createImageLayer() {"+
				"var img = new Image();"+
				"img.style.position = \"absolute\";"+
				"img.style.zIndex = -1;"+
				"img.onload = imageOnload;"+
				"img.onclick = imageOnclick;"+
				"img.src = \"http://192.168.1.102:8080/?action=snapshot&n=\" + (++imageNr);"+
				"var webcam = document.getElementById(\"webcam\");"+
				"webcam.insertBefore(img, webcam.firstChild);"+
				"}"+


				"function imageOnload() {"+
				"this.style.zIndex = imageNr;"+
				"while (1 < finished.length) {"+
				"var del = finished.shift();"+
				"del.parentNode.removeChild(del);"+
				"}"+
				"finished.push(this);"+
				"if (!paused) createImageLayer();"+
				"}"+

				"function imageOnclick() { "+
				"paused = !paused;"+
				"if (!paused) createImageLayer();"+
				"}"+

				"</script>"+
				"<body onload=\"createImageLayer();\">"+

				"<div id=\"webcam\"><noscript><img src=\"http://192.168.1.108:8080/?action=snapshot\" height=\"1000px\" width=\"2000px\"/></noscript></div>"+

				"</body>"+
				"</html>";
		response.respStr = string;
	}
	
	public String readFromFile(File src) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(
                    src));
            StringBuilder stringBuilder = new StringBuilder();
            String content;
            while((content = bufferedReader.readLine() )!=null){
                stringBuilder.append(content);
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
