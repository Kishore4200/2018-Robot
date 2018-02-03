package org.usfirst.frc.team670.robot.commands.autonomous.actions;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Encoder_Motion_Magic extends Command {
	// Called just before this Command runs the first time

	private double ticksToTravel;

	private SensorCollection leftEncoder;
	private SensorCollection rightEncoder;

	public Encoder_Motion_Magic(double inches) {
		leftEncoder = Robot.driveBase.getLeft().getSensorCollection();
		rightEncoder = Robot.driveBase.getRight().getSensorCollection();
		this.ticksToTravel = ((inches) / (Math.PI * Constants.DIAMETERinInchesDriveBase)) * Constants.ticksPerRotation;
		SmartDashboard.putString("Left Encoder: ", leftEncoder.getQuadraturePosition() + "");
		SmartDashboard.putString("Right Encoder: ", rightEncoder.getQuadraturePosition() + "");

		requires(Robot.driveBase);
	}

	protected void initialize() {
		leftEncoder.setQuadraturePosition(0, 1);
		rightEncoder.setQuadraturePosition(0, 1);
		SmartDashboard.putString("Target Ticks: ", ticksToTravel + "");
		Robot.driveBase.initMotionMagic(Robot.driveBase.getLeft());
		Robot.driveBase.initMotionMagic(Robot.driveBase.getRight());
	}

	protected void execute() {
		Robot.driveBase.getLeft().set(ControlMode.Position, ticksToTravel);
		Robot.driveBase.getRight().set(ControlMode.Position, ticksToTravel);
		System.out.println(Robot.driveBase.getLeft().getActiveTrajectoryPosition());
		SmartDashboard.putString("Left Encoder: ", leftEncoder.getQuadraturePosition() + "");
		SmartDashboard.putString("Right Encoder: ", rightEncoder.getQuadraturePosition() + "");

	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
