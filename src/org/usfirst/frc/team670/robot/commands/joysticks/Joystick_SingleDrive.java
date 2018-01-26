package org.usfirst.frc.team670.robot.commands.joysticks;

import java.awt.Point;

import org.usfirst.frc.team670.robot.OI;
import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.SensorThread;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class Joystick_SingleDrive extends Command {

	private double rSpeed;
	private double lSpeed;
	private double angle, newX, newY, centerX, centerY;
	private Point joyPoint;
	private Joystick joy;

	public Joystick_SingleDrive() {
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		joy = Robot.oi.getLeftStick();
		centerX = 0;
		centerY = centerX;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		angle = -joy.getTwist();
		newX = centerX + (joy.getX()-centerX)*Math.cos(angle) - (joy.getY()-centerY)*Math.sin(angle);
		newY = centerY + (joy.getX()-centerX)*Math.sin(angle) + (joy.getY()-centerY)*Math.cos(angle);
		singleStickDrive(newX, newY);
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

	public void singleStickDrive(double x, double y) {
		rSpeed = -x - y;
		lSpeed = -x + y;
		lSpeed = 0.5 * Math.pow(lSpeed, 3) + (1 - 0.5) * lSpeed;
		rSpeed = 0.5 * Math.pow(rSpeed, 3) + (1 - 0.5) * rSpeed;
		Robot.driveBase.drive(lSpeed, rSpeed);
	}
}