package org.usfirst.frc.team670.robot.commands.autonomous.helpers;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.SensorThread;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Pivot extends Command {

	private double finalAngle, startAngle, angle;
	
    public Pivot(double angle) {
    	this.angle = angle;
    	Robot.sensors.reset();
    	startAngle = Robot.sensors.getYaw();
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
    	double percent = (finalAngle - Robot.sensors.getYaw())/angle;
    	//double speed = (0.8) - ((2.8)*(Math.pow(percent-0.5, 2)));
    	double speed = 0.1;
    	if(angle > 0)
    		Robot.driveBase.drive(speed, -speed);
    	else if(angle < 0) 
    		Robot.driveBase.drive(-speed, speed);
    	else
    		cancel();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double percent = (finalAngle - Robot.sensors.getYaw())/angle;
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