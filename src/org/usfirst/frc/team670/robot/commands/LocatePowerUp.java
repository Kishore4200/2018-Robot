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
    	double angle = Robot.vision_subsystem.getAngle();
        addSequential(new Pivot(angle));
        //Number is the distance to begin intaking at
        addSequential(new UltrasonicDrive(Robot.sensors.ultrasonic, 10));
        addSequential(new AutoIntake());
    }
}
