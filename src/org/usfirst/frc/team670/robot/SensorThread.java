package org.usfirst.frc.team670.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Ultrasonic;

public class SensorThread extends Thread{
	
	// Sensors
	private AHRS navXMicro;
	
	//Booleans
	private boolean isNavXConnected;

	public Ultrasonic ultrasonic;
		
	public SensorThread(){
		try {
			navXMicro = new AHRS(RobotMap.navXPort);
			isNavXConnected = true;
		} catch (RuntimeException ex) {
			isNavXConnected = false;
			DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
			navXMicro = null;
		}
	}

	//returns distance in cm of object that is in range of the grabber
	public double getDistanceCube()
	{
		return 0;
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
