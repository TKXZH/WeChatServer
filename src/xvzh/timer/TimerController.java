package xvzh.timer;

import java.util.Timer;

public class TimerController {
	
	private SensorPersistenceTask task;
	public TimerController(SensorPersistenceTask task) {
		this.task = task;
	}
	private Timer timer = new Timer();
	//十分钟采样一次
	private final long high = 600000;
	//半小时采样一次
	private final long mid = 1800000;
	//每小时采样一次
	private final long low = 3600000;
	public void start() {
		long period;
		if(task.getFrequency() == FrequencyEnum.low) {
			period = low;
		} else if (task.getFrequency() == FrequencyEnum.mid) {
			period = mid;
		} else {
			period = high;
		}
		timer.schedule(task, 1000, period);	
	}
}
