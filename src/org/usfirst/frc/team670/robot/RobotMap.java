/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team670.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//DriveBase
	public static final int rightMotor1 = 3;
    public static final int rightMotor2 = 4;
    public static final int leftMotor2 = 2;
    public static final int leftMotor1 = 1;
    
    //Elevator
    public static final int elevatorMotor = 5;
    
    //Intake
    public static final int intakeMotor1 = 6;
    public static final int intakeMotor2 = 9;
    
    //Climber
    public static final int leftWinch = 7;
    public static final int rightWinch = 8;
    
    //Sensor Ports
    public final static Port navXPort = SerialPort.Port.kUSB;
    public static final int UltrasonicIntakeInput = 1;
    public static final int UltrasonicIntakeOutput = 1;
    
    //Joysticks
    public final static int operatorStick = 0;
    public final static int arcadeStick = 3;
    public final static int leftDriveStick = 1;
    public final static int rightDriveStick = 2;
}
