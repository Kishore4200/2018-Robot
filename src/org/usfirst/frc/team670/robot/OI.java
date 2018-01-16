/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team670.robot;

import java.awt.Point;

import org.opencv.core.Scalar;
import org.usfirst.frc.team670.robot.commands.LocatePowerUp;
import org.usfirst.frc.team670.robot.commands.autonomous.CancelCommand;
import org.usfirst.frc.team670.robot.commands.autonomous.helpers.AutoIntake;
import org.usfirst.frc.team670.robot.commands.autonomous.helpers.Climber_Auto;
import org.usfirst.frc.team670.robot.commands.autonomous.helpers.DeployClimber;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private Joystick leftDriveStick = new Joystick(RobotMap.leftDriveStick);
	private Joystick rightDriveStick = new Joystick(RobotMap.rightDriveStick);
	private Joystick operatorStick = new Joystick(RobotMap.operatorStick);
	private Joystick arcadeStick = new Joystick(RobotMap.arcadeStick);
	
	public final Scalar upperHSV = new Scalar(110,101,30);
	public final Scalar lowerHSV = new Scalar(180,255,255);
	//The targetPoint of where the powercube needs to be
	public final Point targetPoint = new Point(640, 360);
	
	
	private Button runClimb = new JoystickButton(arcadeStick, 1);
	private Button reverseClimb = new JoystickButton(arcadeStick, 2);
	private Button deployClimber = new JoystickButton(arcadeStick, 3);	
	
	private Button intake = new JoystickButton(arcadeStick, 4);
	private Button outake = new JoystickButton(arcadeStick, 5);
	
	private Button powerCubeVision = new JoystickButton(arcadeStick, 6);

	//For deploying the climber
	//private Button deployClimber = new JoystickButton(arcadeStick, 6);
	
	private Button cancelCommand = new JoystickButton(arcadeStick, 10);
	
	public OI()
	{
		runClimb.whileHeld(new Climber_Auto());
		reverseClimb.whenPressed(new Climber_Auto());
		deployClimber.whenPressed(new DeployClimber());
		
		intake.whenPressed(new AutoIntake(true, 0.6));
		outake.whenPressed(new AutoIntake(false, 0.6));
		
		powerCubeVision.whenPressed(new LocatePowerUp());
		
		cancelCommand.whenPressed(new CancelCommand());
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
