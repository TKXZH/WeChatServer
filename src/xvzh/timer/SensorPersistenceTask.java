package xvzh.timer;

import java.util.TimerTask;

import xvzh.sensor.dao.HumanDao;
import xvzh.sensor.dao.HumidityDao;
import xvzh.sensor.dao.TemperatureDao;

public class SensorPersistenceTask extends TimerTask {
	/**
	 * 采样频率，设置定时任务采集传感器信息并入库的触发频率。
	 */
	private FrequencyEnum frequency;
	public SensorPersistenceTask(FrequencyEnum frequency) {
		this.frequency = frequency;
	}
	
	@Override
	public void run() {
//		float temperature = DHT11.getInstance(0).getTemperature();
//		float humidity = DHT11.getInstance(0).getHumidity();
//		boolean status = HCSR501.getInstance(5).getStatus();
		
		float temperature = 2;
		float humidity = 44;
		boolean status = true;
		TemperatureDao.insert(temperature);
		HumidityDao.insert(humidity);
		HumanDao.insert(status);
	}

	public FrequencyEnum getFrequency() {
		return frequency;
	}

	public void setFrequency(FrequencyEnum frequency) {
		this.frequency = frequency;
	}
	
}
