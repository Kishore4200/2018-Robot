package org.usfirst.frc.team670.robot.commands.autonomous.helpers;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoIntake extends Command {

	boolean intake;
	double speed;
	
    public AutoIntake(boolean intake, double speed) {
    	this.intake = intake;
    	this.speed = speed;
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.intake(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(intake)
    		Robot.intake.intake(speed);
    	else
    		Robot.intake.intake(-speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.sensors.getDistanceIntake() <= 6)
    		return true;
    	else
    		return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.intake(0);
    } 
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
