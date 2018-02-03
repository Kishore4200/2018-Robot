package org.usfirst.frc.team670.robot.commands.autonomous.actions;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * Uses a PID control loop plus the navX getDisplacement to move a given
 * distance in feet
 * 
 * @author vsharma8363
 *
 */
public class Encoders_DriveDistance extends Command {

	private double ticksToTravel, minPercentOutput = 0.05;
	private int numTimesMotorOutput;
	private SensorCollection leftEncoder;
	private SensorCollection rightEncoder;

	public Encoders_DriveDistance(double inches) {

		leftEncoder = Robot.driveBase.getLeft().getSensorCollection();
		rightEncoder = Robot.driveBase.getRight().getSensorCollection();
		this.ticksToTravel = ((inches) / (Math.PI * Constants.DIAMETERinInchesDriveBase)) * Constants.ticksPerRotation;
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
		SmartDashboard.putString("Left Encoder: ", leftEncoder.getQuadraturePosition() + "");
		SmartDashboard.putString("Right Encoder: ", rightEncoder.getQuadraturePosition() + "");
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Math.abs(Robot.driveBase.getLeft().getMotorOutputPercent()) <= minPercentOutput
				&& Math.abs(Robot.driveBase.getRight().getMotorOutputPercent()) <= minPercentOutput)
			numTimesMotorOutput++;

		return (numTimesMotorOutput >= 100);
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
