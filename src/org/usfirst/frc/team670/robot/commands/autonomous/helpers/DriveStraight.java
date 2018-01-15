package org.usfirst.frc.team670.robot.commands.autonomous.helpers;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * Uses a PID control loop plus the navX getDisplacement to move a given distance in feet
 * 
 * @author shayl
 *
 */
public class DriveStraight extends Command{

	private double distance, finalDistance;
    private double integral, derivative, previous_error = 0;
    private double P, I, D = 0.1;

	public DriveStraight(double feet) {
		// Use requires() here to declare subsystem dependencies
		distance = feet;
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		finalDistance = getDisplacement() + distance;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double speed;
		double error = finalDistance - getDisplacement(); // Error = Target - Actual
        this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        derivative = (error - this.previous_error) / .02;
        speed = P*error + I*this.integral + D*derivative;
        previous_error = error;
		
		if(distance > 0)
			Robot.driveBase.drive(speed, speed);
		else if(distance < 0) 
			Robot.driveBase.drive(-speed, -speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		if(Math.abs(finalDistance - getDisplacement()) < 0.5)
		{
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
		Robot.driveBase.drive(0, 0);

	}
	
	/**
	 * Gets Displacement in feet
	 */
	private double getDisplacement() {
		return Robot.sensors.getDisplacementY() * 3.28084;
	}
	
}
