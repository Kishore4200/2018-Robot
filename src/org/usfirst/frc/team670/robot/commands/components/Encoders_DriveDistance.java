package org.usfirst.frc.team670.robot.commands.components;

import org.usfirst.frc.team670.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Encoders_DriveDistance extends Command {

	private static double MOVE_THRESHOLD = .1;
	private double distance;
	private double percentVoltage; //Voltage is NOW from [-1, 1]
	private double endPosL, endPosR;
	private double waitTime;

	public Encoders_DriveDistance(double distance, double percentVoltage) {
		this.distance = distance;
		this.percentVoltage = percentVoltage;
		endPosL = distance / (Math.PI * Robot.driveBase.WHEEL_DIAMETER);
		endPosR = -endPosL;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveBase.resetEncoders();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveBase.drivePID(endPosL, endPosR, percentVoltage);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		boolean isFinished = false;

		double leftPos = org.usfirst.frc.team670.robot.Robot.driveBase.getLeft().getSelectedSensorPosition(0);
		double rightPos = Robot.driveBase.getRight().getSelectedSensorPosition(0);

		if ((leftPos > endPosL - MOVE_THRESHOLD && leftPos < endPosL + MOVE_THRESHOLD)
				&& (rightPos > endPosR - MOVE_THRESHOLD && rightPos < endPosR + MOVE_THRESHOLD)) {
			isFinished = true;
		}

		return isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveBase.stop();
		Robot.driveBase.getLeft().getSensorCollection().setQuadraturePosition(0, 10);
		Robot.driveBase.getRight().getSensorCollection().setQuadraturePosition(0, 10);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}