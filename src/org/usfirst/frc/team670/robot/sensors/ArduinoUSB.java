package org.usfirst.frc.team670.robot.sensors;

import edu.wpi.first.wpilibj.SerialPort;

public class ArduinoUSB {
	private SerialPort usb;
	private int address;
	private boolean isConnected;

	/**
	 * @param baud
	 *            Baud Rate of the Arduino connection, typically 19200
	 * @param usbNum
	 *            between 1 and 3 of the usb port to use
	 **/
	public ArduinoUSB(int baud) {
		isConnected = false;
		if(!isConnected)
		{
			try {
				usb = new SerialPort(baud, SerialPort.Port.kUSB);
				isConnected = true;
			} catch (RuntimeException ex) {
				isConnected = false;
			}
		}
		System.out.println("SDFASDFSDFASDF");
		if(!isConnected)
		{
			try {
				usb = new SerialPort(baud, SerialPort.Port.kUSB1);
				isConnected = true;
			} catch (RuntimeException ex) {
				isConnected = false;
			}
		}
		if(!isConnected)
		{
			try {
				usb = new SerialPort(baud, SerialPort.Port.kUSB2);
				isConnected = true;
			} catch (RuntimeException ex) {
				isConnected = false;
			}
		}
		
	}

	/**
	 * @return the number of bytes that were written
	 */
	public int write(String data) {
		if (isConnected)
			return usb.writeString(data);
		return -1;
	}

	public int write(byte[] data) {
		if (isConnected)
			return usb.write(data, data.length);
		return -1;
	}

	public String read() {
		if (isConnected) {
			System.out.println("jhfgjg");
			return usb.readString();
		}
		return "-1";
	}

	/**
	 * Read raw bytes out of the buffer.
	 *
	 * @param count
	 *            The maximum number of bytes to read.
	 * @return An array of the read bytes
	 */
	public byte[] read(int count) {
		if (isConnected)
			return usb.read(count);
		return null;
	}

	public boolean isConnected() {
		return isConnected;
	}
}
