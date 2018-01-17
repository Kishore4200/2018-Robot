package org.usfirst.frc.team670.robot.commands.autonomous.backups;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BU_Auto_Center extends CommandGroup {
    public BU_Auto_Center() {
    	String gameLayout = DriverStation.getInstance().getGameSpecificMessage();
    	
    	//Checking and going for the Switch
    	if(gameLayout.charAt(0) == 'L')
		{
			//Put center auto code here for the left switch
		}
    	else if(gameLayout.charAt(0) == 'R')
		{
			//Put center auto code here for the right switch
		}
		else
		{
			//Drive to baseline
		}
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
