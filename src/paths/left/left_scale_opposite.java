package paths.left;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.actions.Drive;
import org.usfirst.frc.team670.robot.commands.actions.Pivot;
import org.usfirst.frc.team670.robot.utilities.Field;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class left_scale_opposite extends CommandGroup {

    public left_scale_opposite() {
        // Add Commands here:
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
    	
    	addSequential(new Drive(Field.DSToSwitch + Field.SwitchWidth - Robot.length + 0.5*(Field.DSToPlatform - Field.DSToSwitch - Field.SwitchWidth)));
    	addSequential(new Pivot(90));
    	addSequential(new Drive(Field.SwitchLength));
    	addSequential(new Pivot(-90));
    	addSequential(new Drive(Field.DSToScale - (Field.DSToSwitch + Field.SwitchWidth - Robot.length + 0.5*(Field.DSToPlatform - Field.DSToSwitch - Field.SwitchWidth)) - Robot.length + Field.TOLERANCE));
    	// RAISE ELEVATOR
    	// DRIVE distance from front of robot to elevator arm
    	// PLACE CUBE
    	addSequential(new Drive(-1.5 * Robot.length));
    	// LOWER ELEVATOR
    }
}
