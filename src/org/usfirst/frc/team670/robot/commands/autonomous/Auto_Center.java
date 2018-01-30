package org.usfirst.frc.team670.robot.commands.autonomous;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.components.Time_Delay;
import org.usfirst.frc.team670.robot.utilities.Path;
import org.usfirst.frc.team670.robot.utilities.Target;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *@author vsharma
 */
public class Auto_Center extends CommandGroup {
	
    public Auto_Center() {
    	
    	String gameLayout = DriverStation.getInstance().getGameSpecificMessage();
		Boolean leftTarget = Robot.prefs.getBoolean("tryLeft", false);
		Boolean rightTarget = Robot.prefs.getBoolean("tryRight", false);
		Target t = Robot.finder.getCenterPositionTarget(gameLayout, leftTarget, rightTarget, Robot.ApproachType.getSelected());
    	double delay = Robot.autonomousDelay.getSelected();
    	
	    Path movePath;
		
		if(t.equals(Target.LeftSwitchSide)){
			//right Scale Auto
			movePath = new Path("paths/center/center_switch_left_side");
		}
		else if(t.equals(Target.LeftSwitchStraight)){
			//right opposite scale auto
			movePath = new Path("paths/center/center_switch_left_straight");
		}
		else if(t.equals(Target.RightSwitchSide)){
			//right switch straight auto
			movePath = new Path("paths/center/center_switch_right_side");
		}
		else if(t.equals(Target.RightSwitchStraight))
		{
			//right switch side auto
			movePath = new Path("paths/center/center_switch_right_straight");
		}
		else
		{
			//Baseline from right
			movePath = new Path("paths/center/center_baseline");
		}
		
		addSequential(new Time_Delay(delay));
		
		if(movePath!=null)
			addSequential(movePath.getCommandGroup());
		
		
	    }
}
