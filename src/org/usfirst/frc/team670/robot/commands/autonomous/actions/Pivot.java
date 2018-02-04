package org.usfirst.frc.team670.robot.commands.autonomous.actions;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.Constants;

import edu.wpi.first.wpilibj.command.Command;


/**
 * Takes in a degree value and picks which pivot command to use
 * 
 * @author shaylan
 */
public class Pivot extends Command{

	private double degrees;
	private Command com;

	/**
	 * @param degrees Angle in degrees
	 */
	public Pivot(double degrees) {
		requires(Robot.driveBase);
		this.degrees = degrees;

		if(Robot.sensors.isNavXConnected()) {
			com = new NavX_Pivot(degrees);
		}
		else {
			com = new Time_Pivot(1/(Constants.wheelVelocity/(Constants.radius * 2 * Math.PI * (degrees/360))), Constants.timeAutoSpeed);
		}

	}

	@Override
	protected boolean isFinished() {
		return !com.isRunning();
	}

}
