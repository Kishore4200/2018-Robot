package org.usfirst.frc.team670.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Ultrasonic;

public class SensorThread extends Thread{
	
	// Sensors
	private AHRS navXMicro;
	private SerialPort arduino;
	//Booleans
	private boolean isNavXConnected;
	private boolean isArduinoConnected;
	
	public Ultrasonic ultrasonic;
		
	public SensorThread(){
		//Check the navXMicro is plugged in
	    try {
			navXMicro = new AHRS(RobotMap.navXPort);
			isNavXConnected = true;
		} catch (RuntimeException ex) {
			isNavXConnected = false;
			DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
			navXMicro = null;
		}
	    
	    try {
		    arduino = new SerialPort(19200, SerialPort.Port.kUSB1);
			isArduinoConnected = true;
		} catch (RuntimeException ex) {
			isArduinoConnected = false;
			DriverStation.reportError("Error instantiating Arduino:  " + ex.getMessage(), true);
			arduino = null;
		}
		
	}

	//Example input: distance_powercube:124.5,distance_humanplayer:123.54
	public String getValue(String key)
	{
		String data = arduino.readString();
		String d1 = data.substring(data.indexOf(key)+key.length()+1);
		String d2 = d1.substring(0, d1.indexOf(","));
		return d2;
	}
	
	/*@return The distance read in inches by the ultrasonic sensor inside the intake * */
	public double getDistanceIntake()
	{
		if(isArduinoConnected)
		{
			double distance = Double.parseDouble(getValue("distance_powercube"));
			return distance;
		}
		else
			return -1;
	}
	
	public void reset() {
		if (isNavXConnected())
			navXMicro.reset();
	}

	public boolean isNavXConnected() {
		return isNavXConnected;
	}

	public double getYaw() {
		if (isNavXConnected())
			return -1;
		return navXMicro.getYaw();
	}
	
	public void zeroYaw()
	{
		if(isNavXConnected())
			navXMicro.zeroYaw();
	}

	public double getTilt() {
		if (isNavXConnected())
			return -1;
		return navXMicro.getAngle();	}

	public double getVelocityY() {
		if (isNavXConnected())
			return -1;
		return navXMicro.getVelocityY();	}

	public double getDisplacementY() {
		if (isNavXConnected())
			return -1;
		return navXMicro.getDisplacementY();
	}

}
