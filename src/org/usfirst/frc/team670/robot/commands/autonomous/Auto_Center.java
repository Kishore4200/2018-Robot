package org.usfirst.frc.team670.robot.commands.autonomous;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.Target;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *@author vsharma
 */
public class Auto_Center extends CommandGroup {

	private Preferences prefs;
	
    public Auto_Center() {
	prefs = Preferences.getInstance();
    	String gameLayout = DriverStation.getInstance().getGameSpecificMessage();
	gameLayout = gameLayout.substring(0, 2);
	String leftTarget = prefs.getString(gameLayout+"_left", "switch");
	String rightTarget = prefs.getString(gameLayout+"_right", "switch");
	Target target = Robot.finder.getCenterPositionTarget(gameLayout, leftTarget, rightTarget);
	//Go for target inside this command group using the path retrieval program
	
	
    }
}
