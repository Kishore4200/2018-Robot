package org.usfirst.frc.team670.robot.utilities;

public class Constants {
	
	/**
	 * Which PID slot to pull gains from.  Starting 2018, you can choose 
	 * from 0,1,2 or 3.  Only the first two (0,1) are visible in web-based configuration.
	 */
	public static final int kSlotIdx = 0;
	
	/* Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops.  
	 * For now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/*
	 * set to zero to skip waiting for confirmation, set to nonzero to wait
	 * and report to DS if action fails.
	 */
	public static final int kTimeoutMs = 10;
	
	/**
	 * Number of ticks per rotation for the encoder type
	 */
	
	public static final double ticksPerRotation = 1440.0;

	public static final double DIAMETERinInchesDriveBase = 6;
	public static final double DIAMETERinInchesElevator = 6;
	
	public static final double Proportion = 0.5, //Make P higher --> Set to 0.05 yesterday
			Integral = 0.0,
			Derivative = 0.0;

	public static final double joyStickMaxTwist = 90;
	
	public static final double elevatorMaxInFeet = 10;
	}