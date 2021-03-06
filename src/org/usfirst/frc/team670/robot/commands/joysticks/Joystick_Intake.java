package org.usfirst.frc.team670.robot.commands.joysticks;


import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.OperatorState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Joystick_Intake extends Command {

	public Joystick_Intake() {
		requires(Robot.intake);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Robot.oi.getOS().equals(OperatorState.INTAKE))
			Robot.intake.driveIntake(Robot.oi.getOperatorStick().getY());
		else
			Robot.intake.driveIntake(0);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
//		if(current >= currentLimit)
//		{
//			stopped = true;
//			working = false;
//			return true;
//		}
//		else
			return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.intake.driveIntake(0);
	}
	
//	public static boolean isWorking()
//	{
//		return working;
//	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.intake.driveIntake(0);
	}
}