package org.usfirst.frc.team670.robot.commands.autonomous.actions;

import org.usfirst.frc.team670.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Class that uses to ultrasonic sensor to continue driving until object is within certain limit
 */
public class LiDar_AutoIntake extends Command {

	private double limit, speed;
	
    public LiDar_AutoIntake(double speed, double inchesLimit) {
        this.limit = inchesLimit;
        this.speed = speed;
    	requires(Robot.driveBase);
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
    	if(Robot.sensors.getDistanceIntake() <= limit)
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
