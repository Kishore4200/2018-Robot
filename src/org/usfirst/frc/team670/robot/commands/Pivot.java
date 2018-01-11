package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Pivot extends Command {

	private double finalAngle, startAngle, angle;
	private char direction;
	
    public Pivot(double angle, char direction) {
    	this.direction = direction;
    	this.angle = angle;
    	Robot.navXMicro.reset();
    	startAngle = Robot.navXMicro.getYaw();
    	this.finalAngle = startAngle + angle;
    	requires(Robot.driveBase);
        // Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Percent of the turn left
    	double percent = (finalAngle - Robot.navXMicro.getYaw())/angle;
    	//double speed = (0.8) - ((2.8)*(Math.pow(percent-0.5, 2)));
    	double speed = 0.1;
    	if(direction == 'r')
    		Robot.driveBase.drive(speed, -speed);
    	else if(direction == 'l') 
    		Robot.driveBase.drive(-speed, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double percent = (finalAngle - Robot.navXMicro.getYaw())/angle;
    	SmartDashboard.putString("Percent:", percent + "");
    	
    	if(percent <= 0)
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