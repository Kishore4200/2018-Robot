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
public class Auto_Right extends CommandGroup {

    public Auto_Right() {
    	String gameLayout = DriverStation.getInstance().getGameSpecificMessage();
    	Target t = Robot.finder.getRightPositionTarget(gameLayout, Robot.ApproachType.getSelected());
    	double delay = Robot.autonomousDelay.getSelected();
    	
    	Path movePath;
    	
    	if(t.equals(Target.ScaleStraight)){
			//right Scale Auto
    		movePath = new Path("paths/right/right_scale_straight");
    	}
		else if(t.equals(Target.ScaleOnOtherSide)){
			//right opposite scale auto
    		movePath = new Path("paths/right/right_scale_opposite");
		}
		else if(t.equals(Target.SwitchFromStraight)){
			//right switch straight auto
    		movePath = new Path("paths/right/right_switch_straight");
		}
		else if(t.equals(Target.SwitchFromSide))
		{
			//right switch side auto
    		movePath = new Path("paths/right/right_switch_side");
		}
		else
		{
			//Baseline from right
    		movePath = new Path("paths/right/right_baseline");
		}
    	
    	addSequential(new Time_Delay(delay));
    	
    	if(movePath!=null)
    		addSequential(movePath.getCommandGroup());
    }
}
