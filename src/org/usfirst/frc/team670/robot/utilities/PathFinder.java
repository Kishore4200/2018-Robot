package org.usfirst.frc.team670.robot.utilities;

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
	
	public Target getCenterPositionTarget(String orientation, String leftTeam, String rightTeam) {
		if (orientation == "RR") {
			if (leftTeam.toLowerCase().equals("scale")) {
				if (rightTeam.toLowerCase().equals("scale")) {
					
				} else if (rightTeam.toLowerCase().equals("switch")) {
					
				} else if (rightTeam.toLowerCase().equals("baseline")) {
					
				}
			} else if (leftTeam.toLowerCase().equals("switch")) {
				if (rightTeam.toLowerCase().equals("scale")) {
					
				} else if (rightTeam.toLowerCase().equals("switch")) {
					
				} else if (rightTeam.toLowerCase().equals("baseline")) {
					
				}
			} else if (leftTeam.toLowerCase().equals("baseline")) {
				if (rightTeam.toLowerCase().equals("scale")) {
					
				} else if (rightTeam.toLowerCase().equals("switch")) {
					
				} else if (rightTeam.toLowerCase().equals("baseline")) {
					
				}
			}
		} else if (orientation == "LL") {
			if (leftTeam.toLowerCase().equals("scale")) {
				if (rightTeam.toLowerCase().equals("scale")) {
					
				} else if (rightTeam.toLowerCase().equals("switch")) {
					
				} else if (rightTeam.toLowerCase().equals("baseline")) {
					
				}
			} else if (leftTeam.toLowerCase().equals("switch")) {
				if (rightTeam.toLowerCase().equals("scale")) {
					
				} else if (rightTeam.toLowerCase().equals("switch")) {
					
				} else if (rightTeam.toLowerCase().equals("baseline")) {
					
				}
			} else if (leftTeam.toLowerCase().equals("baseline")) {
				if (rightTeam.toLowerCase().equals("scale")) {
					
				} else if (rightTeam.toLowerCase().equals("switch")) {
					
				} else if (rightTeam.toLowerCase().equals("baseline")) {
					
				}
			}
		} else if (orientation == "RL") {
			if (leftTeam.toLowerCase().equals("scale")) {
				if (rightTeam.toLowerCase().equals("scale")) {
					
				} else if (rightTeam.toLowerCase().equals("switch")) {
					
				} else if (rightTeam.toLowerCase().equals("baseline")) {
					
				}
			} else if (leftTeam.toLowerCase().equals("switch")) {
				if (rightTeam.toLowerCase().equals("scale")) {
					
				} else if (rightTeam.toLowerCase().equals("switch")) {
					
				} else if (rightTeam.toLowerCase().equals("baseline")) {
					
				}
			} else if (leftTeam.toLowerCase().equals("baseline")) {
				if (rightTeam.toLowerCase().equals("scale")) {
					
				} else if (rightTeam.toLowerCase().equals("switch")) {
					
				} else if (rightTeam.toLowerCase().equals("baseline")) {
					
				}
			}
		} else if (orientation == "LR") {
			if (leftTeam.toLowerCase().equals("scale")) {
				if (rightTeam.toLowerCase().equals("scale")) {
					
				} else if (rightTeam.toLowerCase().equals("switch")) {
					
				} else if (rightTeam.toLowerCase().equals("baseline")) {
					
				}
			} else if (leftTeam.toLowerCase().equals("switch")) {
				if (rightTeam.toLowerCase().equals("scale")) {
					
				} else if (rightTeam.toLowerCase().equals("switch")) {
					
				} else if (rightTeam.toLowerCase().equals("baseline")) {
					
				}
			} else if (leftTeam.toLowerCase().equals("baseline")) {
				if (rightTeam.toLowerCase().equals("scale")) {
					
				} else if (rightTeam.toLowerCase().equals("switch")) {
					
				} else if (rightTeam.toLowerCase().equals("baseline")) {
					
				}
			}
		}
	}
	
}
