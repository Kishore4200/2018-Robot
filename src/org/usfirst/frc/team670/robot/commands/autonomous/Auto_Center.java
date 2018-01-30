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

	//Look for the left, and right values for the parsed gamelayout
	//Pass left and right into a pathfinding logic sequence
	//Get the target
	//Go for target inside this command group using the path retrieval program
	
    }
}
