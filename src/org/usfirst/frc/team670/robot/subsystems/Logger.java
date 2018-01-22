package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class Logger{
	
	private NetworkTable logger;
	private double timesCalled = 0;
	
	public Logger()
	{
		logger = NetworkTable.getTable("logger");	
	}
	
	public void init()
	{
		String eventName = DriverStation.getInstance().getEventName();
		double matchNum = DriverStation.getInstance().getMatchNumber();
    	String matchInfo = eventName + ", Match Number:" + matchNum;
    	logger.putString("fileTitle",matchInfo);
	}

	public void logData()
	{
		new Thread("Log Data") 
		{
		      public void run()
		      {		
		    	String data = "[" + DriverStation.getInstance().getMatchTime() + "]: " + Robot.sensors.toString();
		  		logger.putString("data", data);
		      }
		}.start();
	}

	public boolean shouldLogData() {
		if(timesCalled >= 10)
		{
			timesCalled = 0;
			return true;
		}
		else
		{
			timesCalled++;
			return false;
		}
	}
}

