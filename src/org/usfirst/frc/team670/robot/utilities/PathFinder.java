package org.usfirst.frc.team670.robot.utilities;

import org.usfirst.frc.team670.robot.Robot;

public class PathFinder 
{	
	private Target left, right;
	
	public PathFinder(Target leftTarget, Target rightTarget)
	{
		this.left = leftTarget;
		this.right = rightTarget;
	}

	public Target getLeftPositionTarget(String layout, DropDown data) 
	{
			if(layout.charAt(1) == 'L')
			{
				return Target.ScaleStraight;
			}
			else if(layout.charAt(0) == 'L')
			{
				if(data.getSide() == "straight")
				{
					return Target.SwitchFromStraight;
				}
				else if(data.getSide() == "side")
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
	
	public Target getRightPositionTarget(String layout, DropDown data) 
	{
			if(layout.charAt(1) == 'R')
			{
				return Target.ScaleStraight;
			}
			else if(layout.charAt(0) == 'R')
			{
				if(data.getSide() == "straight")
				{
					return Target.SwitchFromStraight;
				}
				else if(data.getSide() == "side")
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
