package org.usfirst.frc.team670.robot.utilities;

public class Constants {
	
	//All PID Variables
	public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 10;
	public static final double Proportion = 0.5, //Make P higher --> Set to 0.05 yesterday
			Integral = 0.0,
			Derivative = 0.0;

	//Ticks for one rotation of a drivebase wheel
	public static final double drivebaseTickPerRotation = 1440.0;

	//Pivot radius of the robot
	public static double pivotRadius;
	
	//Wheel velocity = inches/second at power of timeAutoSpeed;
	public static double wheelVelocity;
	public static double timeAutoSpeed;
		
	//Diameter of wheels in drivebase
	public static final double DIAMETERinInchesDriveBase = 6;
	public static final double gearRatioDB = 1.0;
	//Diameter of wheel in elevator
	public static final double DIAMETERinInchesElevator = 6;
		
	public static final double elevatorPulseForExchange = 0;
	public static final double elevatorPulseForSwitch = 0;
	public static final double elevatorPulseForScale = 0;
	}