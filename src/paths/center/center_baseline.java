package paths.center;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.actions.Delay;
import org.usfirst.frc.team670.robot.commands.actions.Drive;
import org.usfirst.frc.team670.robot.commands.actions.Pivot;
import org.usfirst.frc.team670.robot.commands.actions.components.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.switches.RunIntake;
import org.usfirst.frc.team670.robot.utilities.Constants;
import org.usfirst.frc.team670.robot.utilities.ElevatorState;
import org.usfirst.frc.team670.robot.utilities.Field;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class center_baseline extends CommandGroup {

    public center_baseline() {
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
    	
    	addSequential(new Drive(Robot.length));
    	addSequential(new Pivot(-90));
    	addSequential(new Drive(Field.ExchangeWidth/2 + Field.CubePileWidth));
    	addSequential(new Pivot(90));
    	addSequential(new Drive(Field.DSToBaseline - Robot.length + Field.TOLERANCE));
    	addSequential(new Delay(2.5));
    	addSequential(new Drive(-(Field.DSToBaseline - Robot.length + Field.TOLERANCE)));
    	addSequential(new Pivot(90));
    	addSequential(new Drive(Field.CubePileWidth));
    	addSequential(new Pivot(90));
    	addSequential(new Drive(Robot.length + Field.TOLERANCE));
    	addSequential(new RunIntake(-0.8, Constants.intakeRunTime));
    	addSequential(new Encoders_Elevator(ElevatorState.EXCHANGE, 0.5));


    }
}
