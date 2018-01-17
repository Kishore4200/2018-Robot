package org.usfirst.frc.team670.robot.commands.components;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Time_DriveStraight extends Command {

    private double lSpeed = 0, rSpeed = 0, seconds = 0, startAngle;
    
    public Time_DriveStraight(double seconds, double speed) {
        this.lSpeed = speed;
        this.rSpeed = speed;
        this.seconds = seconds;
        startAngle = Robot.sensors.getYaw();
        requires(Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(seconds);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Drive seven feet to baseline
    	
    	/*
    	 * This if-else chain will make the robot compensate for drift from weight or motors
    	 */
    	if(startAngle - getYaw() > 0.5) { 
    		lSpeed += 0.01;
    		rSpeed -= 0.01;
    	}
    	else if(startAngle - getYaw() < 0.5) {
    		rSpeed += 0.01;
    		lSpeed -= 0.01;
    	}
    	
    	Robot.driveBase.drive(lSpeed, rSpeed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
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
    
	private double getYaw() {
		return Robot.sensors.getYaw() + 180;
	}
}