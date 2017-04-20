package xvzh.apis.wechat;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.fabric.Response;

import xvzh.http.ApiType;
import xvzh.http.HttpApiMethod;
import xvzh.http.HttpRequest;
import xvzh.http.HttpResponse;
import xvzh.sensor.DHT11;
import xvzh.sensor.HCSR501;
import xvzh.sensor.ULN2003;
import xvzh.sensor.dao.HumanDao;
import xvzh.sensor.dao.HumidityDao;
import xvzh.sensor.dao.TemperatureDao;
import xvzh.wechat.util.JsonDto;
import xvzh.wechat.util.StreamUtil;

public class JsonHttpApi {
	@HttpApiMethod(apiType = ApiType.Default)
	public void sensor(HttpRequest request, HttpResponse response) {
		//允许跨域请求
		response.response.addHeader("Access-Control-Allow-Origin","*");
		JsonDto jsonDto = new JsonDto()
				.setHumanStatus(HCSR501.getInstance(5).getStatus())
				.setHumidity(DHT11.getInstance(0).getHumidity())
				.setTemperature(DHT11.getInstance(0).getTemperature());
		JSONObject json = new JSONObject(jsonDto.getMap());
		response.respStr = json.toJSONString();
	}
	
	@HttpApiMethod(apiType = ApiType.Default)
	public void home(HttpRequest request, HttpResponse response) {
		//允许跨域访问
		response.response.addHeader("Access-Control-Allow-Origin","*");
		String html = StreamUtil.Inputstr2Str_byteArr(this.getClass().getResourceAsStream("/home.html"), null);
		response.respStr = html;
	}
	
	@HttpApiMethod(apiType = ApiType.Default)
	public void video(HttpRequest request, HttpResponse response) {
		String html = StreamUtil.Inputstr2Str_byteArr(this.getClass().getResourceAsStream("/video.html"), null);
		response.respStr = html;
	}
	
	/**
	 * 查询历史传感器数据信息接口,前端需要提供三个参数
	 * 1.sensor : 传感器类型
	 * 2.begin : 起始时间点(TimeStamp)
	 * 3.end : 截止时间点(TimeStamp)
	 */
	@HttpApiMethod(apiType = ApiType.Default)
	public void history(HttpRequest request, HttpResponse response) {
		response.response.addHeader("Access-Control-Allow-Origin","*");
		String sensor  = request.request.getParameter("sensor");
		String begin = request.request.getParameter("begin");
		String end = request.request.getParameter("end");
		
		if(begin == null || end == null || sensor == null) {
			response.respStr = "缺少参数";
			return;
		}
		
		switch (sensor) {
		case "temperature":
			try {
				Date beginDate = new Date(Long.parseLong(begin));
				Date endDate = new Date(Long.parseLong(end));
				List temperatures = TemperatureDao.select(beginDate, endDate);
				JSONArray json = new JSONArray(temperatures);
				response.respStr = json.toJSONString();
			} catch (Exception e) {
				response.respStr = "参数错误";
			}
			break;
		case "humidity":
			try {
				Date beginDate = new Date(Long.parseLong(begin));
				Date endDate = new Date(Long.parseLong(end));
				List humidities = HumidityDao.select(beginDate, endDate);
				JSONArray json = new JSONArray(humidities);
				response.respStr = json.toJSONString();
			} catch (Exception e) {
				response.respStr = "参数错误";
			}
			break;
		case "human":
			try {
				Date beginDate = new Date(Long.parseLong(begin));
				Date endDate = new Date(Long.parseLong(end));
				List humen = HumanDao.select(beginDate, endDate);
				JSONArray json = new JSONArray(humen);
				response.respStr = json.toJSONString();
			} catch (Exception e) {
				response.respStr = "参数错误";
			}
			break;
		default:
			response.respStr = "无此传感器类型";
		}
		
	}
	
	/**
	 * 旋转步进电机接口， 前端需要提供两个参数
	 * 1. direction : 旋转方向，'true'代表正转,'false'代表反转, 'stop'代表停止(停止时不需要speed参数)
	 * 2. speed : 旋转速度, 一共三级可调, 取值为{'high', 'low', 'moderate'}
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@HttpApiMethod(apiType = ApiType.Default)
	public void motor(HttpRequest request, HttpResponse response) throws Exception {
		String direction = request.request.getParameter("direction");
		String speed = request.request.getParameter("speed");
		response.response.addHeader("Access-Control-Allow-Origin","*");
		if(direction == null) {
			response.respStr = "缺少参数";
			return;
		}
		ULN2003 motor = ULN2003.getInstance(1, 2, 3, 4);
		if(direction.equals("stop")) {
			motor.stop();
			return;
		}
		if(speed == null) {
			response.respStr = "缺少参数";
			return;
		}
		switch (direction) {
		case "true":
			switch (speed) {
			case "high":
				motor.forWardWithHighSpeed();
				break;
			case "moderate":
				motor.forWardWithModerateSpeed();
				break;
			case "low":
				motor.forwardWithLowSpeed();
				break;
			default:
				response.respStr = "指定的速度不正确";
				break;
			}
			break;
			
		case "false":
			switch (speed) {
			case "high":
				motor.reverseWithHighSpeed();
				break;
			case "moderate":
				motor.reverseWithModerateSpeed();
				break;
			case "low":
				motor.reverseWithLowSpeed();
				break;
			default:
				response.respStr = "指定的速度不正确";
				break;
			}
			break;
			
		default:
			response.respStr = "指定的方向不正确"; 
			break;
		}
	}
}
