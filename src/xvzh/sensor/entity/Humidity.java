package xvzh.sensor.entity;

import java.util.Date;

/**
 * DHT11湿度传感器数据实体
 * @author Administrator
 *
 */
public class Humidity {
	private float humidity;
	private Date time;
	
	public Humidity(float humidity) {
		this.humidity = humidity;
		this.time = new Date();
	}
	
	public float getHumidity() {
		return humidity;
	}
	public Date getTime() {
		return time;
	}

}
