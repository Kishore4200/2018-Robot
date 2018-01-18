package org.usfirst.frc.team670.robot.commands.components;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Encoders_DriveDistance extends Command {

	// private char direction;
	private double distanceToTravel;
	private final double PPR = 1440;
	private final double DIAMETER = 6;
	private double encoderTicksToTravel;
	private Encoder encoder;
	private double speed;

	public Encoders_DriveDistance(double inches) {
		// Use requires() here to declare subsystem dependencies
		encoder = new Encoder(3, 4);
		distanceToTravel = inches;
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		encoder.reset();
		encoder.setDistancePerPulse(PPR);
		encoderTicksToTravel = (360 / (Math.PI * DIAMETER)) * distanceToTravel;
		speed = 0.5;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
			Robot.driveBase.drive(speed, speed);
			System.out.println(encoder.getDistance());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (encoder.getDistance() >= encoderTicksToTravel) {
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
		end();
	}
}
