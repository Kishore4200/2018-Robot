package org.usfirst.frc.team670.robot.commands.components;

import org.usfirst.frc.team670.robot.Robot;
 import org.usfirst.frc.team670.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
 import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 
 public class Encoders_DriveDistance extends Command {
 
 	// private char direction;
 	private TalonSRX leftMotor1;
 	private TalonSRX rightMotor1;
 	private double distanceToTravel;
 	private final double PPR = 1440;
 	private final double DIAMETER = 6;
 	private double encoderTicksToTravel;
 	private double speed;
 	private SensorCollection quadEncoderLeft;
 	private SensorCollection quadEncoderRight;
 
 	public Encoders_DriveDistance(double feet) {
 		// Use requires() here to declare subsystem dependencies
 		leftMotor1 = Robot.driveBase.getLeft();
 		rightMotor1 = Robot.driveBase.getRight();
 		quadEncoderLeft = new SensorCollection(leftMotor1);
 		quadEncoderRight = new SensorCollection(rightMotor1);
 		distanceToTravel = feet;
 		requires(Robot.driveBase);
 	}
 
 	// Called just before this Command runs the first time
 	protected void initialize() {
 		quadEncoderLeft.setQuadraturePosition(0, 0);
 		quadEncoderRight.setQuadraturePosition(0, 0);
 		speed = 0.5;
 	}
 
 	// Called repeatedly when this Command is scheduled to run
 	protected void execute() {
 		Robot.driveBase.drive(speed, speed);
 		SmartDashboard.putString("Left:","" + quadEncoderLeft.getQuadraturePosition());
 		SmartDashboard.putString("Right:",""+ quadEncoderRight.getQuadraturePosition());
 	}
 
 	// Make this return true when this Command no longer needs to run execute()
 	protected boolean isFinished() {
 		if (quadEncoderRight.getQuadraturePosition() >= distanceToTravel*1440) {
 			return true;
 		} else {
 			return false;
 		}
 
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