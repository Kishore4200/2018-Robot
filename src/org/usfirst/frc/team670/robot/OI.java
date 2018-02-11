/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.PrintElevator;
import org.usfirst.frc.team670.robot.commands.actions.components.Encoders_Elevator;
import org.usfirst.frc.team670.robot.commands.autonomous.CancelCommand;
import org.usfirst.frc.team670.robot.commands.switches.DeployIntake;
import org.usfirst.frc.team670.robot.commands.switches.RunIntake;
import org.usfirst.frc.team670.robot.commands.switches.SetDriverControl;
import org.usfirst.frc.team670.robot.commands.switches.SetOperatorControl;
import org.usfirst.frc.team670.robot.utilities.DriverState;
import org.usfirst.frc.team670.robot.utilities.ElevatorState;
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
	
	private Joystick leftDriveStick = new Joystick(RobotMap.leftDriveStick);
	private Joystick rightDriveStick = new Joystick(RobotMap.rightDriveStick);
	private Joystick operatorStick = new Joystick(RobotMap.operatorStick);
	private Joystick arcadeStick = new Joystick(RobotMap.arcadeStick);

	// Operator Controls
	private Button toggleElevator = new JoystickButton(operatorStick, 3);
	private Button toggleIntake = new JoystickButton(operatorStick, 4);

	// Arcade Controls
	private Button intakedeploy = new JoystickButton(arcadeStick, 1);
	private Button intakeretract = new JoystickButton(arcadeStick, 10);

	private Button runIntake = new JoystickButton(arcadeStick, 2);
	private Button stopIntake = new JoystickButton(arcadeStick, 9);

	private Button elevatorExchange = new JoystickButton(arcadeStick, 3);
	private Button elevatorSwitch = new JoystickButton(arcadeStick, 8);

	private Button elevatorScale = new JoystickButton(arcadeStick, 4);

	private Button cancelCommand = new JoystickButton(arcadeStick, 5);

	// Driver Controls
	private Button tankDrive = new JoystickButton(leftDriveStick, 3);
	private Button reverseTankDrive = new JoystickButton(leftDriveStick, 4);
	private Button singleStickDrive = new JoystickButton(leftDriveStick, 5);
	private Button fieldDrive = new JoystickButton(leftDriveStick, 2);
	private Button reverseFieldDrive = new JoystickButton(leftDriveStick, 10);
	private Button printElevator = new JoystickButton(arcadeStick, 6);
	
	public OI() {
		// Arcade buttons
		intakedeploy.whenPressed(new DeployIntake(true));
		intakeretract.whenPressed(new DeployIntake(false));
		runIntake.whenPressed(new RunIntake(1.0, 10));
		stopIntake.whenPressed(new RunIntake(0,0));
		elevatorExchange.whenPressed(new Encoders_Elevator(ElevatorState.EXCHANGE));
		elevatorSwitch.whenPressed(new Encoders_Elevator(ElevatorState.SWITCH));
		elevatorScale.whenPressed(new Encoders_Elevator(ElevatorState.SCALE));
		cancelCommand.whenPressed(new CancelCommand());

		// Driver Controls
		tankDrive.whenPressed(new SetDriverControl(DriverState.TANK));
		reverseTankDrive.whenPressed(new SetDriverControl(DriverState.TANKREVERSE));
		singleStickDrive.whenPressed(new SetDriverControl(DriverState.SINGLE));
		fieldDrive.whenPressed(new SetDriverControl(DriverState.FIELD));
		reverseFieldDrive.whenPressed(new SetDriverControl(DriverState.FIELDREVERSE));
		
		// Operator buttons
		toggleElevator.whenPressed(new SetOperatorControl(OperatorState.ELEVATOR));
		toggleElevator.whenReleased(new SetOperatorControl(OperatorState.NONE));
		toggleIntake.whenPressed(new SetOperatorControl(OperatorState.INTAKE));
		toggleIntake.whenReleased(new SetOperatorControl(OperatorState.NONE));
		
		printElevator.whenPressed(new PrintElevator());
	}

	public Joystick getLeftStick() {
		return leftDriveStick;
	}

	public Joystick getRightStick() {
		return rightDriveStick;
	}

	public Joystick getOperatorStick() {
		return operatorStick;
	}

	public OperatorState getOS() {
		return os;
	}

	public void setOperatorCommand(OperatorState os) {
		this.os = os;
	}

	public DriverState getDS() {
		return ds;
	}

	public void setDriverState(DriverState ds) {
		this.ds = ds;
	}

}
