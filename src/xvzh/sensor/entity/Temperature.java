package xvzh.sensor.entity;

import java.util.Date;


public class Temperature {
	private float temperature;
	private Date time;
	public Temperature() {
	 super();	
	}
	public Temperature(float temperature) {
		this.temperature = temperature;
		this.time = new Date();    
	}

	public float getTemperature() {
		return temperature;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
