/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.Auto_DeployIntake;
import org.usfirst.frc.team670.robot.commands.Auto_RunIntake;
import org.usfirst.frc.team670.robot.commands.Auto_StopIntake;
import org.usfirst.frc.team670.robot.commands.SetDriverControl;
import org.usfirst.frc.team670.robot.commands.SetOperatorControl;
import org.usfirst.frc.team670.robot.commands.SwitchCamera;
import org.usfirst.frc.team670.robot.commands.autonomous.actions.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.autonomous.primary.CancelCommand;
import org.usfirst.frc.team670.robot.utilities.DriverState;
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
	private DriverState ds = DriverState.TANK;
	
	public static String homePath = "/home/lvuser/paths/";
	
	private Joystick leftDriveStick = new Joystick(RobotMap.leftDriveStick);
	private Joystick rightDriveStick = new Joystick(RobotMap.rightDriveStick);
	private Joystick operatorStick = new Joystick(RobotMap.operatorStick);
	private Joystick arcadeStick = new Joystick(RobotMap.arcadeStick);
	
	//Operator Controls
	private Button toggleElevator = new JoystickButton(operatorStick, 3);
	private Button toggleIntake = new JoystickButton(operatorStick, 4);
	
	//Arcade Controls
	private Button intakedeploy = new JoystickButton(arcadeStick, 1);
	private Button intakeretract = new JoystickButton(arcadeStick, 10);
	
	private Button runIntake = new JoystickButton(arcadeStick, 2);
	private Button stopIntake = new JoystickButton(arcadeStick, 9);
	
	private Button elevatorExchange = new JoystickButton(arcadeStick, 3);
	private Button elevatorSwitch = new JoystickButton(arcadeStick, 8);
	
	private Button elevatorScale = new JoystickButton(arcadeStick, 4);
	
	private Button cancelCommand = new JoystickButton(arcadeStick, 5);
	
	//Driver Controls
	private Button tankDrive = new JoystickButton(leftDriveStick, 3);
	private Button reverseTankDrive = new JoystickButton(leftDriveStick, 4);
	private Button singleStickDrive = new JoystickButton(leftDriveStick, 5);
	
	private Button camSwitch = new JoystickButton(rightDriveStick, 2);

	
	public OI()
	{
		//Arcade buttons
		intakedeploy.whenPressed(new Auto_DeployIntake(true));
		intakeretract.whenPressed(new Auto_DeployIntake(false));
		runIntake.whenPressed(new Auto_RunIntake(1.0));
		stopIntake.whenPressed(new Auto_StopIntake());
		elevatorExchange.whenPressed(new Encoders_Elevator(1, 0.75));
		elevatorSwitch.whenPressed(new Encoders_Elevator(2, 0.75));
		elevatorScale.whenPressed(new Encoders_Elevator(3, 0.75));
		cancelCommand.whenPressed(new CancelCommand());
						
		//Driver Controls
		tankDrive.whenPressed(new SetDriverControl(DriverState.TANK));
		reverseTankDrive.whenPressed(new SetDriverControl(DriverState.TANKREVERSE));
		singleStickDrive.whenPressed(new SetDriverControl(DriverState.SINGLE));		
		camSwitch.whenPressed(new SwitchCamera());
		
		//Operator buttons
		toggleElevator.whenPressed(new SetOperatorControl(OperatorState.ELEVATOR));
		toggleElevator.whenReleased(new SetOperatorControl(OperatorState.NONE));
		toggleIntake.whenPressed(new SetOperatorControl(OperatorState.INTAKE));
		toggleIntake.whenReleased(new SetOperatorControl(OperatorState.NONE));
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
	
	public OperatorState getOS()
	{
		return os;
	}
	
	public void setOperatorCommand(OperatorState os)
	{
		this.os = os;
	}
	
	public DriverState getDS()
	{
		return ds;
	}
	
	public void setDriverState(DriverState ds)
	{
		this.ds = ds;
	}
	
}
