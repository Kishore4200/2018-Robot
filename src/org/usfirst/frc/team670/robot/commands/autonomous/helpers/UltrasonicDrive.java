package org.usfirst.frc.team670.robot.commands.autonomous.helpers;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UltrasonicDrive extends Command {

	private double limit;
	private Ultrasonic ultrasonic;
	
    public UltrasonicDrive(Ultrasonic ultrasonic, double limit) {
        this.limit = limit;
        this.ultrasonic = ultrasonic;
    	requires(Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveBase.drive(0.1, 0.1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(ultrasonic.getRangeInches() <= limit)
    		return true;
    	else
    		return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
