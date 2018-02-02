package org.usfirst.frc.team670.robot.commands.joysticks;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.Constants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class Joystick_CombinedDrive extends Command {

	private double rSpeed;
	private double lSpeed, scalar;
	private double angle, newX, newY, centerX, centerY, finalAngle, deadZONE;
	private Joystick joy;

	public Joystick_CombinedDrive() {
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		centerX = 0;
		centerY = 0;
		deadZONE = 1.1;
		scalar = 1.5;
		joy = Robot.oi.getLeftStick();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.oi.isControlsStandard) {
			// Tank Drive
			Robot.driveBase.drive(Robot.oi.getLeftStick().getY(), -Robot.oi.getRightStick().getY());
		} else {
			// Single Joystick Drive
			double twist = joy.getTwist();
			angle = scalar * joy.getTwist();
			if (Math.abs(joy.getY()) < 0.2 && Math.abs(joy.getX()) < 0.2 && Math.abs(twist) > 0.15) {
				System.out.println(twist);
				newX = twist;
				newY = 0;
				singleStickDrive(newX, newY);
			} else {
				newX = centerX + (joy.getX() - centerX) * Math.cos(-angle);
				newY = centerY + (joy.getY() - centerY) * Math.cos(-angle);
				singleStickDrive(newX, newY);
			}
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