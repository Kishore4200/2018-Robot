package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.autonomous.helpers.AutoIntake;
import org.usfirst.frc.team670.robot.commands.autonomous.helpers.Pivot;
import org.usfirst.frc.team670.robot.commands.autonomous.helpers.UltrasonicDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LocatePowerUp extends CommandGroup {

    public LocatePowerUp() {
    	Robot.vision_subsystem.startCapture();
    	double angle = Robot.vision_subsystem.getAngle();
    	Robot.vision_subsystem.endCapture();
        addSequential(new Pivot(angle));
        //Number is the distance to begin intaking at
        addSequential(new UltrasonicDrive(0.1, 10));
        addSequential(new AutoIntake(true, 0.6));
    }
}
