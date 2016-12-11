package xvzh.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Test {
	public static void main(String args[]) throws InterruptedException {
		final GpioController gpio = GpioFactory.getInstance();
		final GpioPinDigitalInput Pin = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(7));
		Pin.addListener(new GpioPinListenerDigital() {
			
			@Override
			public void handleGpioPinDigitalStateChangeEvent(
					GpioPinDigitalStateChangeEvent arg0) {
				if(arg0.getState().isHigh()) {
					System.out.println("有人靠近!!!!!");
				}
				
			}
		});
		Thread.sleep(10000000);
	}
}
