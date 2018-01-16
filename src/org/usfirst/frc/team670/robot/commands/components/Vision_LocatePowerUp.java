package org.usfirst.frc.team670.robot.commands.components;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Vision_LocatePowerUp extends CommandGroup {

    public Vision_LocatePowerUp() {
    	SmartDashboard.putString("IS Vision", Robot.vision_subsystem + "");
    	//double angle = Robot.vision_subsystem.getAngle();
    	double angle = 0;
    	addSequential(new NavX_Pivot(angle));
        //Number is the distance to begin intaking at
        addSequential(new Ultrasonic_ObjectDrive(0.1, 10));
       // addSequential(new AutoIntake(true, 0.6));
    }
}
