package org.usfirst.frc.team670.robot.commands.autonomous.helpers;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.SensorThread;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	private char direction;
	private double inches, start, finalInches;
	
    public DriveStraight(double inches, char direction) {
        // Use requires() here to declare subsystem dependencies
    	this.direction = direction;
    	this.inches = inches;
    	Robot.sensors.reset();
    	start = Robot.sensors.getDisplacementY();
    	this.finalInches = start + inches;
        requires(Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double percent = (finalInches - Robot.sensors.getDisplacementY())/inches;
    	double speed = (0.8) - ((2.8)*(Math.pow(percent-0.5, 2)));
    	
    	if(direction == 'f')
    		Robot.driveBase.drive(speed, speed);
    	else if(direction == 'b') 
    		Robot.driveBase.drive(-speed, -speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double percent = (finalInches - Robot.sensors.getDisplacementY())/inches;

    	if(percent <= 0.025)
    	{
    		Robot.driveBase.drive(0, 0);
    		return true;
    	}
    	else 
    	{
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
    }
}
