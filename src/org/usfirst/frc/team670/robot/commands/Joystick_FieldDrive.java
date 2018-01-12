package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.SensorThread;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class Joystick_FieldDrive extends Command {

	public Joystick_FieldDrive() {
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Joystick left = Robot.oi.getLeftStick();
		double rcw = left.getTwist();
		double forwrd = left.getY() * -1; /* Invert stick Y axis */
		double strafe = left.getX();
		 
		/* Adjust Joystick X/Y inputs by navX MXP yaw angle */
		
		double gyro_degrees = SensorThread.getYaw();
		double gyro_radians = gyro_degrees * Math.PI/180; 
		double temp = forwrd * Math.cos(gyro_radians) + 
		strafe * Math.sin(gyro_radians);
		strafe = -forwrd * Math.sin(gyro_radians) + strafe * Math.cos(gyro_radians);
		forwrd = temp;

		/* At this point, Joystick X/Y (strafe/forwrd) vectors have been */
		/* rotated by the gyro angle, and can be sent to drive system */
		Robot.driveBase.drive(strafe, forwrd); 
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveBase.drive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}