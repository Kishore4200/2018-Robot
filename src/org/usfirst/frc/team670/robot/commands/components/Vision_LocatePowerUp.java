package org.usfirst.frc.team670.robot.commands.components;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.Auto_Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
/**
 *
 */
public class Vision_LocatePowerUp extends CommandGroup {

    public Vision_LocatePowerUp() {
    	double angle = Robot.sensors.getAngle();
    	addSequential(new NavX_Pivot(angle));
        //Number is the distance to begin intaking at
        addSequential(new Ultrasonic_ObjectDrive(0.1, 10));
        addSequential(new Auto_Intake(true));
    }
}
