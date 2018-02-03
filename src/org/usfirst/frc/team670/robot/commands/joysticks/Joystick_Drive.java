package org.usfirst.frc.team670.robot.commands.joysticks;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.Constants;
import org.usfirst.frc.team670.robot.utilities.DriverState;

import com.ctre.phoenix.motorcontrol.SensorCollection;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class Joystick_Drive extends Command {

	private double rSpeed;
	private double lSpeed;
	private double angle, newX, newY, centerX, centerY;
	private Joystick joy;

	private SensorCollection leftEncoder;
	private SensorCollection rightEncoder;

	public Joystick_Drive() {
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		joy = Robot.oi.getLeftStick();
		leftEncoder = Robot.driveBase.getLeft().getSensorCollection();
		leftEncoder = Robot.driveBase.getLeft().getSensorCollection();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.oi.getDS().equals(DriverState.TANK)) {
			// Tank Drive
			Robot.driveBase.drive(Robot.oi.getLeftStick().getY(), -Robot.oi.getRightStick().getY());
		} else if (Robot.oi.getDS().equals(DriverState.TANKREVERSE)) {
			// Tank Drive
			Robot.driveBase.drive(-Robot.oi.getRightStick().getY(), Robot.oi.getLeftStick().getY());
		} else {
			singleStickDrive(Robot.oi.getLeftStick().getX(), Robot.oi.getLeftStick().getY());
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

	public void singleStickDrive(double x, double y) {
		lSpeed = -x - y;
		rSpeed = -x + y;
		lSpeed = 0.5 * Math.pow(lSpeed, 3) + (1 - 0.5) * lSpeed;
		rSpeed = 0.5 * Math.pow(rSpeed, 3) + (1 - 0.5) * rSpeed;
		Robot.driveBase.drive(lSpeed, rSpeed);
	}
}