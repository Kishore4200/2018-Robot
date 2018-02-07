package paths.right;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.actions.Drive;
import org.usfirst.frc.team670.robot.commands.actions.Pivot;
import org.usfirst.frc.team670.robot.utilities.Field;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class right_scale_straight extends CommandGroup {

	public right_scale_straight() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		addSequential(new Drive(Field.DSToScale - Robot.length));
    	addSequential(new Pivot(-90));
    	addSequential(new Drive(Field.EdgeToPlatform - Field.SideTriangleWidth - Robot.width));
    	addSequential(new Pivot(90));
    	//RAISE ELEVATOR?
    	//DRIVE UNTIL CUBE CAN BE PLACED, This will depend on elevator placement
    	//PLACE CUBE
    	//BACKUP
    	//LOWER ELEVATOR
		
	}
}
