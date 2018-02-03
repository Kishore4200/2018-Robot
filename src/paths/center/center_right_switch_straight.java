package paths.center;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.autonomous.actions.Encoders_DriveDistance;
import org.usfirst.frc.team670.robot.commands.autonomous.actions.NavX_Pivot;
import org.usfirst.frc.team670.robot.utilities.Field;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class center_right_switch_straight extends CommandGroup {

    public center_right_switch_straight() {
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
    	
    	addSequential(new Encoders_DriveDistance(Robot.length));
    	addSequential(new NavX_Pivot(Math.atan((Field.DSToSwitch-1.5*Robot.length)/(0.5*(Field.SwitchLength-Robot.width-Field.ExchangeWidth)))));
    	addSequential(new Encoders_DriveDistance(Math.sqrt((Math.pow(Field.DSToSwitch-1.5*Robot.length, 2)) + (Math.pow(0.5*(Field.SwitchLength-Robot.width-Field.ExchangeWidth), 2)))));
    	addSequential(new NavX_Pivot(-Math.atan((Field.DSToSwitch-1.5*Robot.length)/(0.5*(Field.SwitchLength-Robot.width-Field.ExchangeWidth)))));
    	addSequential(new Encoders_DriveDistance(0.5*Robot.length));
    	// PLACE CUBE
    	addSequential(new Encoders_DriveDistance(-0.5*Robot.length));
    }
}
