package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.commands.autonomous.helpers.AutoIntake;
import org.usfirst.frc.team670.robot.commands.autonomous.helpers.Pivot;
import org.usfirst.frc.team670.robot.commands.autonomous.helpers.UltrasonicDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LocatePowerUp extends CommandGroup {

    public LocatePowerUp() {
    	SmartDashboard.putString("IS Vision", Robot.vision_subsystem + "");
    	//double angle = Robot.vision_subsystem.getAngle();
    	double angle = 0;
    	addSequential(new Pivot(angle));
        //Number is the distance to begin intaking at
        addSequential(new UltrasonicDrive(0.1, 10));
        addSequential(new AutoIntake(true, 0.6));
    }
}
