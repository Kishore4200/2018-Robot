package org.usfirst.frc.team670.robot.commands.components;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.Constants;
import edu.wpi.first.wpilibj.command.Command;

public class Encoders_Elevator extends Command {

	// private char direction;
	private double feet, speed;
	
	public Encoders_Elevator(double feet, double speed) {
		this.speed = speed;
		this.feet = feet;
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveBase.drive(speed, speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(feet > 0)
		{
			if (Robot.elevator.getCurrentPosition() >= (this.feet/Constants.DIAMETERinInchesElevator)*Constants.ticksPerRotation)
				return true;
			else
				return false;
		}
		else
		{
			if (Robot.elevator.getCurrentPosition() <= (this.feet/Constants.DIAMETERinInchesElevator)*Constants.ticksPerRotation)
				return true;
			else
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
