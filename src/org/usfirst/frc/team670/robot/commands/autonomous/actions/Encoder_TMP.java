package org.usfirst.frc.team670.robot.commands.autonomous.actions;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class Encoder_TMP extends Command {
	// Called just before this Command runs the first time

	private double ticksToTravel;

	public Encoder_TMP(double inches) {
		this.ticksToTravel = ((inches) / (Math.PI * Constants.DIAMETERinInchesDriveBase)) * Constants.ticksPerRotation;
		requires(Robot.driveBase);
	}

	protected void initialize() {
		Robot.driveBase.initTMP(Robot.driveBase.getLeft());
		Robot.driveBase.initTMP(Robot.driveBase.getRight());
	}

	protected void execute() {
		Robot.driveBase.getLeft().set(ControlMode.Position, -ticksToTravel);
		Robot.driveBase.getRight().set(ControlMode.Position, ticksToTravel);
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
