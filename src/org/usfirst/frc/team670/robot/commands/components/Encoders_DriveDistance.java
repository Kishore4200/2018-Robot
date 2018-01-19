package org.usfirst.frc.team670.robot.commands.components;

import org.usfirst.frc.team670.robot.Robot;
 import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.utilities.Constants;

import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
 import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * drive a certain distance in feet using encoders
 *@author vsharma
 */

 public class Encoders_DriveDistance extends Command {
 
 	// private char direction;
 	private TalonSRX leftMotor1;
 	private TalonSRX rightMotor1;
 	private double ticksToTravel;
 	private final double DIAMETERinInches = 6;
 	private double speed;
 	private SensorCollection quadEncoderLeft;
 	private SensorCollection quadEncoderRight;
 	//true if forward
 	private boolean direction;
 
 	public Encoders_DriveDistance(double feet) {
 		// Use requires() here to declare subsystem dependencies
 		leftMotor1 = Robot.driveBase.getLeft();
 		rightMotor1 = Robot.driveBase.getRight();
 		quadEncoderLeft = new SensorCollection(leftMotor1);
 		quadEncoderRight = new SensorCollection(rightMotor1);
 		if(feet >= 0)
 			direction = true;
 		else
 			direction = false;
 		feet = Math.abs(feet);
 		ticksToTravel = ((feet*12.0)/(Math.PI*DIAMETERinInches)) * Constants.ticksPerRotation;
 		requires(Robot.driveBase);
 	}
 
 	// Called just before this Command runs the first time
 	protected void initialize() {
 		quadEncoderLeft.setQuadraturePosition(0, 0);
 		quadEncoderRight.setQuadraturePosition(0, 0);
 	}
 
 	// Called repeatedly when this Command is scheduled to run
 	protected void execute() {
 		speed = 0.5;
 		
 		if(direction)
 			Robot.driveBase.drive(speed, speed);
 		else
 			Robot.driveBase.drive(-speed, -speed);
 		
 		SmartDashboard.putString("Left:","" + quadEncoderLeft.getQuadraturePosition());
 		SmartDashboard.putString("Right:",""+ quadEncoderRight.getQuadraturePosition());
 	}
 
 	// Make this return true when this Command no longer needs to run execute()
 	protected boolean isFinished() {
 		if(direction)
 		{
 			if (quadEncoderLeft.getQuadraturePosition() >= ticksToTravel && quadEncoderRight.getQuadraturePosition() >= ticksToTravel)
 				return true;
 			else
 				return false;
 		}
 		else if(!direction)
 		{
 			if (quadEncoderLeft.getQuadraturePosition() <= -ticksToTravel && quadEncoderRight.getQuadraturePosition() <= -ticksToTravel)
 				return true;
 			else
 				return false;
 		}
 		else
 			return false;
 
 	}
 
 	// Called once after isFinished returns true
 	protected void end() {
 		Robot.driveBase.drive(0, 0);
 	}
 
 	// Called when another command which requires one or more of the same
 	// subsystems is scheduled to run
 	protected void interrupted() {
 		end();
 	}
 }