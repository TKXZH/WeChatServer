package xvzh.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class HCSR501 {
	
	private static HCSR501 hcsr501;
	private boolean status = false;
	private int pin;
	final GpioController gpio;
	final GpioPinDigitalInput Pin;
	
	private HCSR501(int pin) {
		gpio = GpioFactory.getInstance();
		this.pin = pin;
		Pin = gpio.provisionDigitalInputPin(RaspiPin.getPinByAddress(this.pin));
	}
	
	public static HCSR501 getInstance(int pin) {
		if(hcsr501 == null) {
			hcsr501 = new HCSR501(pin);
		}

		return hcsr501;
	}
	
	public void addListener(GpioPinListenerDigital listener) {
		Pin.addListener(listener);
	}
	
	public boolean getStatus() {
		if(Pin.isHigh()) {
			this.status = true;
		} else {
			this.status = false;
		}
		return this.status;
	}
	
	public static void main(String args[]) throws InterruptedException {
		HCSR501 hcsr501 = new HCSR501(7);
		hcsr501.addListener(new GpioPinListenerDigital() {
			
			@Override
			public void handleGpioPinDigitalStateChangeEvent(
					GpioPinDigitalStateChangeEvent arg0) {
				if(arg0.getState().isHigh()) {
					System.out.println("有人靠近!!!");
				}
			}
		});
		Thread.sleep(50000);
	}
}
