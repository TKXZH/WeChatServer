package xvzh.sensor;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;

public class DHT11 {
	private static DHT11 dht11;
	private int pin;
	private static final int MAXTIMINGS = 85;
	private int[] dht11_dat = { 0, 0, 0, 0, 0 };
	private float temperature;
	private float humidity;

	private DHT11(int pin) {
		
		this.pin = pin;
		// setup wiringPi
//		if (Gpio.wiringPiSetup() == -1) {
//			System.out.println(" ==>> GPIO SETUP FAILED");
//			return;
//		}
		GpioFactory.getInstance();

		GpioUtil.export(pin, GpioUtil.DIRECTION_OUT);
	}
	public float getTemperature() {
		while(true) {
			int laststate = Gpio.HIGH;
			int j = 0;
			dht11_dat[0] = dht11_dat[1] = dht11_dat[2] = dht11_dat[3] = dht11_dat[4] = 0;

			Gpio.pinMode(pin, Gpio.OUTPUT);
			Gpio.digitalWrite(pin, Gpio.LOW);
			Gpio.delay(18);

			Gpio.digitalWrite(pin, Gpio.HIGH);
			Gpio.pinMode(pin, Gpio.INPUT);

			for (int i = 0; i < MAXTIMINGS; i++) {
				int counter = 0;
				while (Gpio.digitalRead(pin) == laststate) {
					counter++;
					Gpio.delayMicroseconds(1);
					if (counter == 255) {
						break;
					}
				}

				laststate = Gpio.digitalRead(pin);

				if (counter == 255) {
					break;
				}

				/* ignore first 3 transitions */
				if ((i >= 4) && (i % 2 == 0)) {
					/* shove each bit into the storage bytes */
					dht11_dat[j / 8] <<= 1;
					if (counter > 16) {
						dht11_dat[j / 8] |= 1;
					}
					j++;
				}
			}
			if ((j >= 40) && checkParity()) {
				float h = (float) ((dht11_dat[0] << 8) + dht11_dat[1]) / 10;
				if (h > 100) {
					h = dht11_dat[0]; // for DHT11
				}
				float c = (float) (((dht11_dat[2] & 0x7F) << 8) + dht11_dat[3]) / 10;
				if (c > 125) {
					c = dht11_dat[2]; // for DHT11
				}
				if ((dht11_dat[2] & 0x80) != 0) {
					c = -c;
				}
				//float f = c * 1.8f + 32;
				this.temperature = c;
				this.humidity = h;
				return temperature;
			}
		}
	}
	public float getHumidity() {
		while(true) {
			int laststate = Gpio.HIGH;
			int j = 0;
			dht11_dat[0] = dht11_dat[1] = dht11_dat[2] = dht11_dat[3] = dht11_dat[4] = 0;

			Gpio.pinMode(pin, Gpio.OUTPUT);
			Gpio.digitalWrite(pin, Gpio.LOW);
			Gpio.delay(18);

			Gpio.digitalWrite(pin, Gpio.HIGH);
			Gpio.pinMode(pin, Gpio.INPUT);

			for (int i = 0; i < MAXTIMINGS; i++) {
				int counter = 0;
				while (Gpio.digitalRead(pin) == laststate) {
					counter++;
					Gpio.delayMicroseconds(1);
					if (counter == 255) {
						break;
					}
				}

				laststate = Gpio.digitalRead(pin);

				if (counter == 255) {
					break;
				}

				/* ignore first 3 transitions */
				if ((i >= 4) && (i % 2 == 0)) {
					/* shove each bit into the storage bytes */
					dht11_dat[j / 8] <<= 1;
					if (counter > 16) {
						dht11_dat[j / 8] |= 1;
					}
					j++;
				}
			}
			if ((j >= 40) && checkParity()) {
				float h = (float) ((dht11_dat[0] << 8) + dht11_dat[1]) / 10;
				if (h > 100) {
					h = dht11_dat[0]; // for DHT11
				}
				float c = (float) (((dht11_dat[2] & 0x7F) << 8) + dht11_dat[3]) / 10;
				if (c > 125) {
					c = dht11_dat[2]; // for DHT11
				}
				if ((dht11_dat[2] & 0x80) != 0) {
					c = -c;
				}
				//float f = c * 1.8f + 32;
				this.temperature = c;
				this.humidity = h;
				return humidity;
			}
		}
	}
	public static DHT11 getInstance(int pin) {
		if(dht11 == null) {
			dht11 = new DHT11(pin);
		}
		return dht11;
	}


	private boolean checkParity() {
		return (dht11_dat[4] == ((dht11_dat[0] + dht11_dat[1] + dht11_dat[2] + dht11_dat[3]) & 0xFF));
	}
}

