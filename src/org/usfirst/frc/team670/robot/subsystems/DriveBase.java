/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.Joystick_RobotDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveBase extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private TalonSRX leftTalon1;
	private TalonSRX leftTalon2;
	private TalonSRX rightTalon1;
	private TalonSRX rightTalon2;

	public DriveBase() {
		leftTalon1 = new TalonSRX(RobotMap.leftMotor1);
		leftTalon2 = new TalonSRX(RobotMap.leftMotor2);
		rightTalon1 = new TalonSRX(RobotMap.rightMotor1);
		rightTalon2 = new TalonSRX(RobotMap.rightMotor2);
		leftTalon2.set(ControlMode.Follower, RobotMap.leftMotor1);
		rightTalon2.set(ControlMode.Follower, RobotMap.rightMotor1);
	}

	public void drive(double left, double right) {
		leftTalon1.set(ControlMode.PercentOutput, -left);
		rightTalon1.set(ControlMode.PercentOutput, right);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Joystick_RobotDrive());
	}
}
