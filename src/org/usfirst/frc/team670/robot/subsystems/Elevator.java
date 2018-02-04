package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.joysticks.Joystick_Elevator;
import org.usfirst.frc.team670.robot.utilities.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

	/*
	 * OTHER POSSIBLE WAY OF DOING MOVEMENT
	 * Voltage Applied = Kv * velocity + Ka * acceleration + Ks
	 * 
	 * Ks = static friction and gravitational acceleration
	 * Kv = velocity constant
	 * Ka = acceleration constant, keep at zero and then use data from the other values to solve for it
	 */
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private TalonSRX elevator;
	private SensorCollection encoder;
	private boolean safeSpeed;

	public Elevator()
	{
		safeSpeed = true;
		elevator = new TalonSRX(RobotMap.elevatorMotor);
		encoder = new SensorCollection(elevator);
		encoder.setPulseWidthPosition(0, 0);
	}

	public double getCurrentPosition()
	{
		return encoder.getPulseWidthPosition();
	}

	public void moveElevator(double speed)
	{
		if(safeSpeed && Math.abs(speed) > Constants.elevatorSafeSpeed) {
			speed = (speed > 0) ? Constants.elevatorSafeSpeed : -Constants.elevatorSafeSpeed;
		}
		if(isMoveLegal(speed)) {
			elevator.set(ControlMode.PercentOutput, speed);
		}
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Joystick_Elevator());
	}

	private boolean isMoveLegal(double speed) {
		return ((Constants.elevatorMaxHeight - encoder.getQuadraturePosition() * Constants.ticksPerRotation * Constants.elevatorGearRadius * 2 * Math.PI > 0.1 && speed > 0) || 
				(Constants.elevatorMinHeight - encoder.getQuadraturePosition() * Constants.ticksPerRotation * Constants.elevatorGearRadius * 2 * Math.PI < 0.1 && speed < 0) || speed == 0);
	}

	public void setSafeSpeed(boolean change) {
		safeSpeed = change;
	}

	public TalonSRX getTalon() {
		return elevator;
	}
}

