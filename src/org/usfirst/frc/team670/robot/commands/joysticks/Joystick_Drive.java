package org.usfirst.frc.team670.robot.commands.joysticks;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.Constants;
import org.usfirst.frc.team670.robot.utilities.DriverState;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class Joystick_Drive extends Command {

	private double rSpeed;
	private double lSpeed;
	private Joystick joy;

	public Joystick_Drive() {
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		joy = Robot.oi.getLeftStick();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.oi.getDS().equals(DriverState.TANKREVERSE)) {
			// Tank Drive
			Robot.driveBase.drive(Robot.oi.getLeftStick().getY(), Robot.oi.getRightStick().getY());
		} else if (Robot.oi.getDS().equals(DriverState.FIELD)) {
			PartyDrive(joy, true);
		} else {
			Robot.driveBase.drive(-Robot.oi.getLeftStick().getY(), -Robot.oi.getRightStick().getY());
		}
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
		Robot.driveBase.drive(0, 0);
	}

	private void PartyDrive(Joystick left, boolean isNormal)
	{
		double rcw = left.getTwist();
		double forwrd = left.getY() * -1; /* Invert stick Y axis */
		double strafe = left.getX();
		 
		/* Adjust Joystick X/Y inputs by navX MXP yaw angle */
		
		double gyro_degrees = Robot.sensors.getYaw();
		double gyro_radians = gyro_degrees * Math.PI/180; 
		double temp = forwrd * Math.cos(gyro_radians) + 
		strafe * Math.sin(gyro_radians);
		strafe = -forwrd * Math.sin(gyro_radians) + strafe * Math.cos(gyro_radians);
		forwrd = temp;

		/* At this point, Joystick X/Y (strafe/forwrd) vectors have been */
		/* rotated by the gyro angle, and can be sent to drive system */
		singleStickDrive(strafe, forwrd);
	}
	
	public void singleStickDrive(double x, double y) {
		rSpeed = -x - y;
		lSpeed = -x + y;
		lSpeed = 0.5 * Math.pow(lSpeed, 3) + (1 - 0.5) * lSpeed;
		rSpeed = 0.5 * Math.pow(rSpeed, 3) + (1 - 0.5) * rSpeed;
		Robot.driveBase.drive(lSpeed, rSpeed);
	}
}