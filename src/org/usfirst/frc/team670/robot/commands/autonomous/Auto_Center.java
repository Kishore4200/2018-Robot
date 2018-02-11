package org.usfirst.frc.team670.robot.commands.autonomous;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.actions.Delay;
import org.usfirst.frc.team670.robot.constants.Target;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import paths.center.center_baseline;
import paths.center.center_left_switch_side;
import paths.center.center_left_switch_straight;
import paths.center.center_right_switch_side;
import paths.center.center_right_switch_straight;

/**
 *@author vsharma
 */
public class Auto_Center extends CommandGroup {
	
    public Auto_Center() {
    	
    	String gameLayout = DriverStation.getInstance().getGameSpecificMessage();
		Boolean leftTarget = Robot.tryLeft.getSelected();
		Boolean rightTarget = Robot.tryRight.getSelected();
		Target t = Robot.finder.getCenterPositionTarget(gameLayout, leftTarget, rightTarget, Robot.ApproachType.getSelected());
    	double delay = Robot.autonomousDelay.getSelected();
		
    	addSequential(new Delay(delay));
    			
		if(t.equals(Target.LeftSwitchSide))
			addSequential(new center_left_switch_side());
		else if(t.equals(Target.LeftSwitchStraight))
			addSequential(new center_left_switch_straight());
		else if(t.equals(Target.RightSwitchSide))
			addSequential(new center_right_switch_side());
		else if(t.equals(Target.RightSwitchStraight))
			addSequential(new center_right_switch_straight());
		else
			addSequential(new center_baseline());
		
	    }
}
