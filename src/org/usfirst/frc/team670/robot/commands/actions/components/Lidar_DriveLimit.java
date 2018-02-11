package org.usfirst.frc.team670.robot.commands.actions.components;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Lidar_DriveLimit extends Command {

	double speed, limit;
	
    public Lidar_DriveLimit(double speed, double limitInches) {
        requires(Robot.driveBase);
        limit = limitInches;
        this.speed = speed;
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
        if(Robot.sensors.getDistanceIntakeInches() <= limit)
        	return true;
        else
        	return false;
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
