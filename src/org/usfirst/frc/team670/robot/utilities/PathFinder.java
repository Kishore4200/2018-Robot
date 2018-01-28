package org.usfirst.frc.team670.robot.utilities;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;

public class PathFinder 
{	
	
	public PathFinder()
	{
		
	}
	
	public Target getLeftPositionTarget(String layout, Boolean approach) 
	{
			if(layout.charAt(1) == 'L')
			{
				return Target.ScaleStraight;
			}
			else if(layout.charAt(0) == 'L')
			{
				if(approach.equals(true))
				{
					return Target.SwitchFromStraight;
				}
				else if(approach.equals(false))
				{
					return Target.SwitchFromSide;
				}
				else 
				{
					return Target.Baseline;
				}
			}
			else if (layout.charAt(1) == 'R')
			{
				return Target.ScaleOnOtherSide;
			}
			else
			{
				return Target.Baseline;
			}
	}
	
	public Target getRightPositionTarget(String layout, Boolean approach) 
	{
			if(layout.charAt(1) == 'R')
			{
				return Target.ScaleStraight;
			}
			else if(layout.charAt(0) == 'R')
			{
				if(approach.equals(true))
				{
					return Target.SwitchFromStraight;
				}
				else if(approach.equals(false))
				{
					return Target.SwitchFromSide;
				}
				else 
				{
					return Target.Baseline;
				}
			}
			else if (layout.charAt(1) == 'L')
			{
				return Target.ScaleOnOtherSide;
			}
			else
			{
				return Target.Baseline;
			}
	}
	
	public Target getCenterPositionTarget(String layout, TargetList left, TargetList right)
	{
    	Target leftTarget = left.getTarget(layout);
    	Target rightTarget = right.getTarget(layout);
    	return Target.Baseline;
	}
	
}
