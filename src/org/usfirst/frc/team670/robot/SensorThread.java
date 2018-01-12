package org.usfirst.frc.team670.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;

public class SensorThread extends Thread{

	// Data variables from sensors
	private static double Yaw, Velocity, Tilt;

	// Sensors
	private static AHRS navXMicro;
	private static boolean isNavXConnected, aggregate;
	
	//Threads
	private static Thread sensors;
	
	public SensorThread(){
		
		sensors = new Thread("SensorThread") 
		{
		      public void run()
		      {
		    	  //Check to see if navX is connected
					try {
						navXMicro = new AHRS(SerialPort.Port.kUSB);
						isNavXConnected = true;
					} catch (RuntimeException ex) {
						isNavXConnected = false;
						DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
						navXMicro = null;
					}
		    	  
		    	  while(true)
		    	  {
		    		  if(aggregate)
		    		  {
		    			  if(isNavXConnected)
		    			  {
		    				  
		    			  }
		    			  else
		    			  {
		    				  //Set all variables to -1
		    			  }
		    			  //Set all variables
		    		  }
		    		  else
		    		  {
		    			  //If we should not aggregate sensor data
		    		  }
		    	  }
		      }
		};
		// Begin to aggregate sensor data
	}
	
	public void beginAggregating()
	{
		aggregate = true;
	}
	
	public void endAggregation()
	{
		aggregate = false;
	}

	public static void reset() {
		if (isNavXConnected())
			navXMicro.reset();
	}

	public static boolean isNavXConnected() {
		return isNavXConnected;
	}

	public static double getYaw() {
		return Yaw;
	}

	public static double getTilt() {
		return Tilt;
	}

	public static double getVelocity() {
		return Velocity;
	}

	public static double getDisplacementY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
