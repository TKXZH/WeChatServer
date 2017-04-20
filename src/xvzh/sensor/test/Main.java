package xvzh.sensor.test;

import java.io.IOException;
import xvzh.sensor.dao.HumanDao;
import xvzh.timer.FrequencyEnum;
import xvzh.timer.SensorPersistenceTask;
import xvzh.timer.TimerController;

public class Main {
	public static void main(String args[]) throws IOException {
		HumanDao.insert(true);
		new TimerController(new SensorPersistenceTask(FrequencyEnum.high)).start();
	}
	
}
