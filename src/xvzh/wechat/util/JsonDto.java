package xvzh.wechat.util;

import java.util.HashMap;
import java.util.Map;

public class JsonDto {
	private float humidity;
	private float temperature;
	private boolean humanStatus;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	public float getHumidity() {
		return humidity;
	}
	public float getTemperature() {
		return temperature;
	}
	public boolean isHumanStatus() {
		return humanStatus;
	}
	public JsonDto setHumidity(float humidity) {
		this.humidity = humidity;
		dataMap.put("humidity", humidity);
		return this;
	}
	public JsonDto setTemperature(float temperature) {
		this.temperature = temperature;
		dataMap.put("temperature", temperature);
		return this;
	}
	public JsonDto setHumanStatus(boolean humanStatus) {
		this.humanStatus = humanStatus;
		dataMap.put("humanStatus", humanStatus);
		return this;
	}
	
	public Map<String, Object> getMap() {
		return this.dataMap;
	}
	
}
