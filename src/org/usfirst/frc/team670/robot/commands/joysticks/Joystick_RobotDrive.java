package org.usfirst.frc.team670.robot.commands.joysticks;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Joystick_RobotDrive extends Command {

	public Joystick_RobotDrive() {
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
			if(!Robot.oi.isControlsStandard)
			{
				Robot.driveBase.drive(-Robot.oi.getLeftStick().getY(),Robot.oi.getRightStick().getY());
			}
			else
			{
				Robot.driveBase.drive(Robot.oi.getRightStick().getY(),-Robot.oi.getLeftStick().getY());
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
}