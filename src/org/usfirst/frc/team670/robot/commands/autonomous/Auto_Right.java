package org.usfirst.frc.team670.robot.commands.autonomous;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.components.Time_Delay;
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
    	
    	addSequential(new Time_Delay(delay));
    	
    	if(t.equals(Target.ScaleStraight)){
			//Right Scale Auto
		}
		else if(t.equals(Target.ScaleOnOtherSide)){
			//Right opposite scale auto
		}
		else if(t.equals(Target.SwitchFromStraight)){
			//Right switch straight auto
		}
		else if(t.equals(Target.SwitchFromSide))
		{
			//Right switch side auto
		}
		else
		{
			//Baseline from Right
		}
    }
}
