package org.usfirst.frc.team670.robot.commands.components;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * Uses a PID control loop plus the navX getDisplacement to move a given
 * distance in feet
 * 
 * @author vsharma8363
 *
 */
public class Encoders_DriveDistance extends Command {

	private double ticksToTravel;
	private int numTimesUnderOutput;

	public Encoders_DriveDistance(double feet) {

		this.ticksToTravel = ((feet * 12.0) / (Math.PI * Constants.DIAMETERinInchesDriveBase))
				* Constants.ticksPerRotation;
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveBase.initPID(Robot.driveBase.getLeft());
		Robot.driveBase.initPID(Robot.driveBase.getRight());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveBase.getLeft().set(ControlMode.Position, ticksToTravel);
		Robot.driveBase.getRight().set(ControlMode.Position,
				ticksToTravel); /* 50 rotations in either direction */
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		numTimesUnderOutput = (Math.abs(Robot.driveBase.getLeft().getMotorOutputPercent()) < .05)
				? numTimesUnderOutput + 1 : numTimesUnderOutput;

		if (numTimesUnderOutput > 10) {
			return true;
		}
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
