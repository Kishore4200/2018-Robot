package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.Joystick_Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	private TalonSRX intakeMotor1;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Intake()
	{
		intakeMotor1 = new TalonSRX(RobotMap.intakeMotor1);
	}
	
	public void intake()
	{
		
	}
	
	public void chuck()
	{
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Joystick_Intake());
    }
}

