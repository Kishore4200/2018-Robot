package org.usfirst.frc.team670.robot.commands.actions;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.actions.components.Encoders_Drive;
import org.usfirst.frc.team670.robot.commands.actions.components.Time_Drive;
import org.usfirst.frc.team670.robot.utilities.Constants;

import edu.wpi.first.wpilibj.command.Command;


/**
 * Takes in a distance in inches and picks which pivot command to use
 * 
 * @author shaylan
 */
public class Drive extends Command{

	private double distance;
	private Command com;

	/**
	 * @param degrees Distance in inches
	 */
	public Drive(double distance) {
		requires(Robot.driveBase);
		this.distance = distance;

		if(Robot.driveBase.getLeft().getSensorCollection() != null && Robot.driveBase.getRight().getSensorCollection() != null ) {
			com = new Encoders_Drive(distance);
			Robot.sensors.areEncodersWorking(true);
		}
		else {
			com = new Time_Drive(1/(Constants.wheelVelocity/distance), Constants.timeAutoSpeed);
			Robot.sensors.areEncodersWorking(false);
		}

	}

	@Override
	protected boolean isFinished() {
		return !com.isRunning();
	}

}