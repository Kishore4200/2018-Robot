package org.usfirst.frc.team670.robot.commands.joysticks;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.Constants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class Joystick_SingleDrive extends Command {

	private double rSpeed;
	private double lSpeed, scalar;
	private double angle, newX, newY, centerX, centerY, previousAngle, finalAngle, deadZONE;
	private Joystick joy;

	public Joystick_SingleDrive() {
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		centerX = 0;
		centerY = 0;
		deadZONE = 0.05;
		scalar = 1.5;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		joy = Robot.oi.getLeftStick();
		angle = scalar*joy.getTwist();
		
		previousAngle = angle;
		
		if(joy.getY() < deadZONE && joy.getX() < deadZONE)
		{
			newX = centerX + (joy.getX()-centerX)*Math.cos(-finalAngle) - (joy.getY()-centerY)*Math.sin(-finalAngle);
			newY = centerY + (joy.getX()-centerX)*Math.sin(-finalAngle) + (joy.getY()-centerY)*Math.cos(-finalAngle);
			singleStickDrive(newX, newY);
		}
		else
		{
			double percent = (angle/scalar)/(Constants.joyStickMaxTwist);
			Robot.driveBase.drive(percent, -percent);
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
	}

	public void singleStickDrive(double x, double y) {
		rSpeed = -x - y;
		lSpeed = -x + y;
		lSpeed = 0.5 * Math.pow(lSpeed, 3) + (1 - 0.5) * lSpeed;
		rSpeed = 0.5 * Math.pow(rSpeed, 3) + (1 - 0.5) * rSpeed;
		Robot.driveBase.drive(lSpeed, rSpeed);
	}
}