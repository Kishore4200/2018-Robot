package org.usfirst.frc.team670.robot.commands.autonomous;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CancelCommand extends Command {

    public CancelCommand() {
        requires(Robot.driveBase);
        requires(Robot.intake);
        requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveBase.drive(0, 0);
    	Robot.intake.intake(0);
    	Robot.climber.climb(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveBase.drive(0, 0);
    	Robot.intake.intake(0);
    	Robot.climber.climb(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
