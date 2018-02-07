package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.joysticks.Joystick_Intake;
import org.usfirst.frc.team670.robot.commands.switches.DeployIntake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	private Solenoid deployIntakeElevator, deployGrabber;
	private TalonSRX leftIntake, rightIntake;
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Intake()
	{
		leftIntake = new TalonSRX(RobotMap.intakeLeftTalon);
		rightIntake = new TalonSRX(RobotMap.intakeRightTalon);
		deployIntakeElevator = new Solenoid(RobotMap.intakeDeploy);
		deployGrabber = new Solenoid(RobotMap.clawDeploy);
	}
	
	public void driveIntake(double speed)
	{
		leftIntake.set(ControlMode.PercentOutput, speed);
		leftIntake.set(ControlMode.PercentOutput, speed);
	}
	
	public void deploySupport(boolean deploy)
	{
		deployIntakeElevator.set(deploy);
	}
	
	public void deployIntake(boolean deploy)
	{
		deployGrabber.set(deploy);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Joystick_Intake());
    }
}

