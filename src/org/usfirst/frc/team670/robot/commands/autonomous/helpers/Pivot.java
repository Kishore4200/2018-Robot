package org.usfirst.frc.team670.robot.commands.autonomous.helpers;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Pivot extends Command {

	private double finalAngle, startAngle, angle;
	private double percentComplete;

	/**
	 * 
	 * 
	 * @param angle
	 *            The angle to turn, positive is right, negative is left.
	 */
	public Pivot(double angle) {
		this.angle = angle;
		requires(Robot.driveBase);
		// Use requires() here to declare subsystem dependencies
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		this.startAngle = getYaw();
		this.finalAngle = startAngle + angle;
		System.out.println("started: Pivot");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Percent of the turn left
		// double speed = (0.8) - ((2.8)*(Math.pow(percent-0.5, 2)));

		double speed = 0;
		percentComplete = Math.abs((angle - yawRemaining()) / (angle));

		if (percentComplete <= 0.363) {
			speed = -2.3 * 2.3 * (percentComplete * percentComplete) + 0.8;
		} else {
			speed = 0.1;
		}
//		speed = -3.6 * (percentComplete - .5) * (percentComplete - .5) + 1;

		// double percentComplete = Math.abs((Math.abs(finalAngle) -
		// Math.abs(currentAngle))/angle);
		// if(percentComplete < .5)
		// speed = percentComplete * 2 + 0.09;
		// else
		// speed = 1 - percentComplete + 0.09;

		// double speed = 0.1;
		if (angle > 0)
			Robot.driveBase.drive(speed, -speed);
		else
			Robot.driveBase.drive(-speed, speed);

		System.out.println("Start: " + startAngle);
		System.out.println("Yaw: " + getYaw());
		System.out.println("Percent Complete: " + percentComplete);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (percentComplete >= 0.875) {
			System.out.println("finished");
			return true;
		} else {
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
	
	private double getYaw(){
		return Robot.sensors.getYaw() + 180;
	}
	
	private double yawRemaining(){
		double yaw = getYaw();
		if(angle > 0 && yaw < (startAngle - 10/*Margin of Error Value*/)){ //IF WE WANT TO TURN 360 THIS WON't WORK
			return finalAngle - yaw - 360;
		}
		else if(angle < 0 && yaw > (startAngle + 10/*Margin of Error Value*/)){
			return finalAngle - yaw + 360;
		}
		return finalAngle - yaw;
	}
	
}