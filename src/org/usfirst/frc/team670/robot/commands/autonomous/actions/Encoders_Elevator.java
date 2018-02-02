package org.usfirst.frc.team670.robot.commands.autonomous.actions;

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
public class Encoders_Elevator extends Command {

	/**
	 * @param state
	 *            1-Exchange/Pickup, 2-Switch, 3-Scale
	 */
	private double targetPulseHeight;
	private boolean isGoingUp;
	private double speed, tolerance;

	public Encoders_Elevator(int state, double speed) {
		tolerance = speed * 100;
		this.speed = speed;
		if (state == 1)
			targetPulseHeight = Constants.elevatorPulseForExchange;
		if (state == 2)
			targetPulseHeight = Constants.elevatorPulseForSwitch;
		if (state == 3)
			targetPulseHeight = Constants.elevatorPulseForScale;
		else
			targetPulseHeight = Constants.elevatorPulseForExchange;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (Math.abs(targetPulseHeight) < Math.abs(Robot.elevator.getCurrentPosition()))
			isGoingUp = false;
		else
			isGoingUp = true;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (isGoingUp)
			Robot.elevator.moveElevator(speed);
		else
			Robot.elevator.moveElevator(-speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		if (isGoingUp && Robot.elevator.getCurrentPosition() >= targetPulseHeight) {
			return true;
		} else if (!isGoingUp && Robot.elevator.getCurrentPosition() <= targetPulseHeight) {
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.moveElevator(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.elevator.moveElevator(0);
		end();
	}
}