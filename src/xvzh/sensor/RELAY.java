package xvzh.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class RELAY {
	private static RELAY hcsr501;
	private int pin;
	final GpioController gpio;
	final GpioPinDigitalOutput Pin;
	
	private RELAY(int pin) {
		gpio = GpioFactory.getInstance();
		this.pin = pin;
		Pin = gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(this.pin));
	}
	
	public static RELAY getInstance(int pin) {
		if(hcsr501 == null) {
			hcsr501 = new RELAY(pin);
		}

		return hcsr501;
	}
	
	public void change() {
		Pin.toggle();
	}
	
}
