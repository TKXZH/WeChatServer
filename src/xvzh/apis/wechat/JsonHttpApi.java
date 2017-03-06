package xvzh.apis.wechat;

import com.alibaba.fastjson.JSONObject;

import xvzh.http.ApiType;
import xvzh.http.HttpApiMethod;
import xvzh.http.HttpRequest;
import xvzh.http.HttpResponse;
import xvzh.wechat.util.JsonDto;

public class JsonHttpApi {
	@HttpApiMethod(apiType = ApiType.Default)
	public void temperature(HttpRequest request, HttpResponse response) {
		JsonDto jsonDto = new JsonDto().setHumanStatus(false)
				.setHumidity(223)
				.setTemperature(35);
		JSONObject json = new JSONObject(jsonDto.getMap());
		response.respStr = json.toJSONString();
	}
}
