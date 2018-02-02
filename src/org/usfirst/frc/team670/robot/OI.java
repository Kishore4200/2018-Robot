/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team670.robot;

import java.awt.Point;

import org.opencv.core.Scalar;
import org.usfirst.frc.team670.robot.commands.Auto_DeployIntake;
import org.usfirst.frc.team670.robot.commands.Auto_Intake;
import org.usfirst.frc.team670.robot.commands.FlipControls;
import org.usfirst.frc.team670.robot.commands.SetOperatorControl;
import org.usfirst.frc.team670.robot.commands.autonomous.CancelCommand;
import org.usfirst.frc.team670.robot.commands.components.Encoders_DriveDistance;
import org.usfirst.frc.team670.robot.commands.components.Encoders_Test;
import org.usfirst.frc.team670.robot.commands.components.Vision_LocatePowerUp;
import org.usfirst.frc.team670.robot.utilities.OperatorState;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * @author vsharma
 */
public class OI {
	
	private OperatorState os = OperatorState.NONE;
	public boolean isControlsStandard = true;
	
	private Joystick leftDriveStick = new Joystick(RobotMap.leftDriveStick);
	private Joystick rightDriveStick = new Joystick(RobotMap.rightDriveStick);
	private Joystick operatorStick = new Joystick(RobotMap.operatorStick);
	private Joystick arcadeStick = new Joystick(RobotMap.arcadeStick);
	private Joystick twistStick = new Joystick(RobotMap.twistStick);
	
	//Operator Controls
	private Button toggleElevator = new JoystickButton(operatorStick, 3);
	private Button toggleClimber = new JoystickButton(operatorStick, 4);
	
	//private Button runClimb = new JoystickButton(arcadeStick, 1);
	//private Button reverseClimb = new JoystickButton(arcadeStick, 2);
	//private Button deployClimber = new	 JoystickButton(arcadeStick, 3);	
	
	private Button intakedeploy = new JoystickButton(arcadeStick, 1);
	private Button intakeretract = new JoystickButton(arcadeStick, 10);
	private Button intake = new JoystickButton(arcadeStick, 2);
	private Button outake = new JoystickButton(arcadeStick, 9);
	
	private Button powerCubeVision = new JoystickButton(getRightStick(), 2);
	
	private Button cancelCommand = new JoystickButton(arcadeStick, 6);
	
	private Button flipControls = new JoystickButton(leftDriveStick, 1);
	private Button encoderTest = new JoystickButton(leftDriveStick, 9);
	
	public OI()
	{
		intakedeploy.whenPressed(new Auto_DeployIntake(true));
		intakeretract.whenPressed(new Auto_DeployIntake(false));
		
		intake.whenPressed(new Auto_Intake(true));
		outake.whenPressed(new Auto_Intake(false));
						
		toggleElevator.whenPressed(new SetOperatorControl(OperatorState.ELEVATOR));
		toggleElevator.whenReleased(new SetOperatorControl(OperatorState.NONE));
		
		cancelCommand.whenPressed(new CancelCommand());
		
		flipControls.whenPressed(new FlipControls());
		
		powerCubeVision.whenPressed(new Encoders_DriveDistance(10));
		
		encoderTest.whenPressed(new Encoders_Test());
	}
	
	public Joystick getLeftStick(){
		return leftDriveStick;
	}
	
	public Joystick getRightStick(){
		return rightDriveStick;
	}
	
	public Joystick getOperatorStick() {
		return operatorStick;
	}
	
	public void setOperatorCommand(OperatorState os)
	{
		this.os = os;
	}
	
	public OperatorState getOS()
	{
		return os;
	}
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
