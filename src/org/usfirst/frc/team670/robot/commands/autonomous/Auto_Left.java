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
public class Auto_Left extends CommandGroup {

    public Auto_Left() {
    	String gameLayout = DriverStation.getInstance().getGameSpecificMessage();
    	Target t = Robot.finder.getLeftPositionTarget(gameLayout, Robot.ApproachType.getSelected());
    	double delay = Robot.autonomousDelay.getSelected();
    	
    	Path movePath;
    	
    	if(t.equals(Target.ScaleStraight)){
			//Left Scale Auto
    		movePath = new Path(Robot.oi.homePath +"paths/left/left_scale_straight");
    	}
		else if(t.equals(Target.ScaleOnOtherSide)){
			//Left opposite scale auto
    		movePath = new Path(Robot.oi.homePath +"paths/left/left_scale_opposite");
		}
		else if(t.equals(Target.SwitchFromStraight)){
			//Left switch straight auto
    		movePath = new Path(Robot.oi.homePath +"paths/left/left_switch_straight");
		}
		else if(t.equals(Target.SwitchFromSide))
		{
			//Left switch side auto
    		movePath = new Path(Robot.oi.homePath +"paths/left/left_switch_side");
		}
		else
		{
			//Baseline from left
    		movePath = new Path(Robot.oi.homePath +"paths/left/left_baseline");
		}
    	
    	addSequential(new Time_Delay(delay));
    	
    	if(movePath!=null)
    		addSequential(movePath.getCommandGroup());
    }
}
