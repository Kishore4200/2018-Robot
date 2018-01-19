package org.usfirst.frc.team670.robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *@author vsharma
 */
public class Auto_Right extends CommandGroup {

    public Auto_Right() {
    	String gameLayout = DriverStation.getInstance().getGameSpecificMessage();
    	
    	//Checking and going for the Switch
    	if(gameLayout.charAt(0) == 'R')
		{
			//Put right auto code here for the switch
		} 
    	else if(gameLayout.charAt(1) == 'R'){
			//Put right auto code here for the scale
		}
		else if(gameLayout.charAt(2) == 'R'){
			//Put right auto code here for the switch on the other team's side
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
