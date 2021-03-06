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
	public static final double drivebaseTickPerRotation = 1024.0;

	//Pivot radius of the robot
	public static final double pivotRadius = 17.51;

	//Wheel velocity = inches/second at power of timeAutoSpeed;
	public static final double wheelVelocity = 0; //THESE NEED TO BE SET
	public static final double timeAutoSpeed = 0;

	//Seconds to run intake during auto
	public static final double intakeRunTime = 5;

	//Distance from the front of the robot to the elevator
	public static final double frontToElevator = 12.5;

	//Diameter of wheels in drivebase
	public static final double DIAMETERinInchesDriveBase = 6;
	public static final double gearRatioDB = 1.0;
	//Diameter of wheel in elevator
	public static final double DIAMETERinInchesElevator = 6;

	//Elevator Heights
	public static final double elevatorPulseForExchange = 0; //THESE NEED TO BE SET
	public static final double elevatorPulseForDown = 0;
	public static final double elevatorPulseForSwitch = 0;
	public static final double elevatorPulseForScale = 0;
	public static final double elevatorAutonSpeed = 0.05;

	public static final double maxIntakeVoltage = 0;
	
	//Multiply by distance in inches to get needed ticks
	public static final double elevatorInchesToTicks = 8/3 * drivebaseTickPerRotation / Math.PI * DIAMETERinInchesDriveBase;
	public static final double elevatorTicksToInches = Math.PI * DIAMETERinInchesDriveBase / (8/3 * drivebaseTickPerRotation);

	public static double elevatorTicksToGo(double inches) {
		return inches * elevatorInchesToTicks;
	}

	public static double elevatorInchesToGo(double ticks) {
		return ticks * elevatorTicksToInches;
	}
	
}