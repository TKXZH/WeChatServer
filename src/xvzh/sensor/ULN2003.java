package xvzh.sensor;

import com.pi4j.component.motor.impl.GpioStepperMotorComponent;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class ULN2003 {

	private static ULN2003 uln2003;
	private GpioPinDigitalOutput[] pins;
	private GpioStepperMotorComponent motor;
	private final byte[] SINGLE_STEP = { (byte) 0b0001, (byte) 0b0010,
			(byte) 0b0100, (byte) 0b1000 };

	@SuppressWarnings("unused")
	private final byte[] DOUBLE_STEP = { (byte) 0b0011, (byte) 0b0110,
			(byte) 0b1100, (byte) 0b1001 };

	@SuppressWarnings("unused")
	private final byte[] HALF_STEP = { (byte) 0b0001, (byte) 0b0011,
			(byte) 0b0010, (byte) 0b0110, (byte) 0b0100, (byte) 0b1100,
			(byte) 0b1000, (byte) 0b1001 };

	// 4-Step sequence: 32 * 63.68395 = 2037.8864 (2038)
	private final int STEPS_PER_REV = 2038;

	private GpioController gpio = GpioFactory.getInstance();

	private ULN2003(int pin0, int pin1, int pin2, int pin3) {
		pins = new GpioPinDigitalOutput[4];
		pins[0] = gpio.provisionDigitalOutputPin(
				RaspiPin.getPinByAddress(pin0), PinState.LOW);
		pins[1] = gpio.provisionDigitalOutputPin(
				RaspiPin.getPinByAddress(pin1), PinState.LOW);
		pins[2] = gpio.provisionDigitalOutputPin(
				RaspiPin.getPinByAddress(pin2), PinState.LOW);
		pins[3] = gpio.provisionDigitalOutputPin(
				RaspiPin.getPinByAddress(pin3), PinState.LOW);
		gpio.setShutdownOptions(true, PinState.LOW, pins);
		motor = new GpioStepperMotorComponent(pins);
		motor.setStepInterval(2);
		motor.setStepSequence(SINGLE_STEP);
		motor.setStepsPerRevolution(STEPS_PER_REV);
	}

	public static ULN2003 getInstance(int pin0, int pin1, int pin2, int pin3)
			throws Exception {
		if (ULN2003.uln2003 != null) {
			return uln2003;
		} else {
			uln2003 = new ULN2003(pin0, pin1, pin2, pin3);
			return uln2003;
		}
	}

	public synchronized void turnLeft() {
		motor.rotate(0.25);
	}

	public synchronized void turnRight() {
		motor.rotate(-0.25);
	}

	public static void main(String args[]) throws Exception {
		ULN2003 uln2003 = ULN2003.getInstance(1, 2, 3, 4);
		uln2003.turnLeft();
	}
}
