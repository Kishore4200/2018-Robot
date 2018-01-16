package org.usfirst.frc.team670.robot.commands.components;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * Uses a PID control loop plus the navX getDisplacement to move a given distance in feet
 * 
 * @author shayl
 *
 */
public class NavX_DriveDistance extends Command{

	private double distance, finalDistance, initialDisplacement;
    private double integral, derivative, previous_error = 0;
    private double P = 1, I = 0.1, D = 0.1;

	public NavX_DriveDistance(double feet) {
		// Use requires() here to declare subsystem dependencies
		distance = feet;
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		initialDisplacement = getDisplacement();
		finalDistance = getDisplacement() + distance;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double percentComplete = (distance - Math.abs((getDisplacement() - initialDisplacement)))/distance;
		double speed;
		double error = finalDistance - getDisplacement(); // Error = Target - Actual
        this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        derivative = (error - this.previous_error) / .02;
        speed = P*error + I*this.integral + D*derivative;
        previous_error = error;
        if(percentComplete < 0.5){
        	speed = 2*percentComplete;
        }
        else{
        	speed = 1- percentComplete;
        }
        	
		
		if(distance > 0)
			Robot.driveBase.drive(speed, speed);
		else if(distance < 0) 
			Robot.driveBase.drive(-speed, -speed);
		
		SmartDashboard.putString("final distance", finalDistance + "");
		SmartDashboard.putString("displacement", getDisplacement() + "");

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		if(finalDistance - getDisplacement() < 0.1)
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
		return Math.abs(Robot.sensors.getDisplacementY()) * 1/3.28084;
	}
	
}
