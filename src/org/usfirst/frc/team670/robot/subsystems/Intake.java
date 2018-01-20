package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.Auto_DeployIntake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	//private Solenoid intakeSolLeft, intakeSolRight, intakeDeploy;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Intake()
	{
	/*	intakeSolLeft = new Solenoid(RobotMap.intakeSolLeft);
		intakeSolRight = new Solenoid(RobotMap.intakeSolRight);
		intakeDeploy = new Solenoid(RobotMap.intakeSolDeploy);*/
	}
	
	public void grab()
	{
	/*	intakeSolLeft.set(false);
		intakeSolRight.set(false);
	*/}
	
	public void release()
	{
		//intakeSolLeft.set(true);
		//intakeSolRight.set(true);
	}
	
	public void deploy()
	{
		//intakeDeploy.set(true);
	}
	
	public void retract()
	{
		//intakeDeploy.set(false);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new Auto_DeployIntake());
    }
}

