package org.usfirst.frc.team670.robot.commands.switches;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.OperatorState;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Sets operator control to a different value
 * 
 * @author vsharma
 */
public class SetOperatorControl extends Command {

	private OperatorState os;
	
    public SetOperatorControl(OperatorState os) {
    	this.os = os;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.oi.setOperatorCommand(os);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}