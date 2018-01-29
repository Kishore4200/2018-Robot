package org.usfirst.frc.team670.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * 
 * Program to consolidate all sensor information - one class to retrieve information from
 * 
 * @author vsharma
 *
 */
public class SensorThread extends Thread{
	
	// Sensors
	private AHRS navXMicro;
	private Ultrasonic ultrasonic;
	private NetworkTable vision;
	
	//Booleans
	private boolean isNavXConnected;
			
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
	    
	    ultrasonic = new Ultrasonic(RobotMap.UltrasonicIntakeOutput, RobotMap.UltrasonicIntakeInput);
		
	    vision = NetworkTable.getTable("vision");
	}
	
	/*@return The distance read in inches by the ultrasonic sensor inside the intake * */
	public double getDistanceIntake()
	{
		if(ultrasonic != null)
			return ultrasonic.getRangeInches();
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
			return navXMicro.getYaw();
		return -1;
	}
	
	public void zeroYaw()
	{
		if(isNavXConnected())
			navXMicro.zeroYaw();
	}

	public double getTilt() {
		if (isNavXConnected())
			return navXMicro.getAngle();
		return -1;	}

	public double getVelocityY() {
		if (isNavXConnected())
			return navXMicro.getVelocityY();
		return -1;	}

	public double getDisplacementX() {
		if (isNavXConnected())
			return navXMicro.getDisplacementX();
		return -1;
	}
	
	public double getDisplacementY() {
		if (isNavXConnected())
			return navXMicro.getDisplacementY();
		return -1;
	}
	
	public double getDisplacementZ() {
		if (isNavXConnected())
			return navXMicro.getDisplacementZ();
		return -1;
	}
	
	@SuppressWarnings("deprecation")
	public double getAngle()
	{
		if(vision == null)
		{
			vision = NetworkTable.getTable("vision");
			return -1;
		}
		else
			return vision.getNumber("angle", -1);
	}
	
	public double getWidth()
	{
		if(vision == null)
		{
			vision = NetworkTable.getTable("vision");
			return -1;
		}
		else
			return vision.getNumber("width", -1);
	}

	public double getHeight()
	{
		if(vision == null)
		{
			vision = NetworkTable.getTable("vision");
			return -1;
		}
		else
			return vision.getNumber("height", -1);
	}
	
	public String toString()
	{
		return "";
	}
}
