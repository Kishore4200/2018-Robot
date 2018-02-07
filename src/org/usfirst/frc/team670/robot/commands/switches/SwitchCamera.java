package org.usfirst.frc.team670.robot.commands.switches;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.DriverState;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Flips controls for the driver when called
 * 
 * @author vsharma
 */
public class SwitchCamera extends Command {
		
    public SwitchCamera() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.sensors.switchCam();
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