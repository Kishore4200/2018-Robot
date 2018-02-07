package org.usfirst.frc.team670.robot.commands.actions;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.actions.components.Encoders_Drive;
import org.usfirst.frc.team670.robot.commands.actions.components.Encoders_Pivot;
import org.usfirst.frc.team670.robot.commands.actions.components.NavX_Pivot;
import org.usfirst.frc.team670.robot.commands.actions.components.Time_Pivot;
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
		else if(Robot.driveBase.getLeft().getSensorCollection() != null && Robot.driveBase.getRight().getSensorCollection() != null ) {
			com = new Encoders_Pivot(degrees);
			Robot.sensors.areEncodersWorking(true);
		}
		else {
			com = new Time_Pivot(1/(Constants.wheelVelocity/(Constants.pivotRadius * 2 * Math.PI * (degrees/360))), Constants.timeAutoSpeed);
		}

	}

	@Override
	protected boolean isFinished() {
		return !com.isRunning();
	}

}