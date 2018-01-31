package org.usfirst.frc.team670.robot.sensors;

import org.usfirst.frc.team670.robot.RobotMap;

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
public class Aggregator extends Thread{
	
	// Sensors
	private AHRS navXMicro;
	private NetworkTable vision;
	private ArduinoUSB ard;
	
	//Booleans
	private boolean isNavXConnected;
			
	public Aggregator(){
		//Check the navXMicro is plugged in
	    try {
			navXMicro = new AHRS(RobotMap.navXPort);
			isNavXConnected = true;
		} catch (RuntimeException ex) {
			isNavXConnected = false;
			DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
			navXMicro = null;
		}
	    
	    ard = new ArduinoUSB(19200, 1);
	    
	    vision = NetworkTable.getTable("vision");
	}
	
	/*@return The distance read in inches by the ultrasonic sensor inside the intake * */
	public double getDistanceIntake()
	{
		double d = 0;
		
		if(ard.isConnected())
			d = Double.parseDouble(getVal(ard.read()));
		else
			d = -1;
		return d;
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
	
	public String getVal(String data) {

		int lastLessThan = data.lastIndexOf('<');
		int lastGreaterThan = data.lastIndexOf('>');

		if (lastGreaterThan != -1 && lastLessThan != -1) {
			if (lastLessThan < lastGreaterThan) {
				return data.substring(lastLessThan + 1, lastGreaterThan);
			} else if (data.lastIndexOf('<', lastGreaterThan) != -1) {
				return data.substring(data.lastIndexOf('<', lastGreaterThan) + 1, data.lastIndexOf('>'));
			}
		}
		return "-1";
	}
}

