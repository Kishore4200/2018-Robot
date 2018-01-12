package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private TalonSRX leftWinch, rightWinch;
	
	public Climber()
	{
		leftWinch = new TalonSRX(RobotMap.leftWinch);
		rightWinch = new TalonSRX(RobotMap.rightWinch);
	}
	
	public void climb(double speed)
	{
		leftWinch.set(ControlMode.PercentOutput, speed);
		rightWinch.set(ControlMode.PercentOutput, speed);
	}
	
	public void deployForklifts()
	{
		
	}
	
	
    public void initDefaultCommand() {
        //setDefaultCommand(new MySpecialCommand());
    }
}

