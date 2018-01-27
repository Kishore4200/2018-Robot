package org.usfirst.frc.team670.robot.commands.components;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.Constants;

import com.ctre.phoenix.motorcontrol.SensorCollection;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Encoders_Test extends Command {

	// private char direction;

	private SensorCollection leftE, rightE;
	
	public Encoders_Test() {
		leftE = new SensorCollection(Robot.driveBase.getLeft());
		rightE = new SensorCollection(Robot.driveBase.getRight());
		leftE.setPulseWidthPosition(0, 0);
		rightE.setPulseWidthPosition(0, 0);
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putString("Left Encoders", ""+leftE.getPulseWidthPosition());
		SmartDashboard.putString("Right Encoders", ""+rightE.getPulseWidthPosition());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
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
