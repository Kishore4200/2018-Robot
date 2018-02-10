package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.joysticks.Joystick_Elevator;
import org.usfirst.frc.team670.robot.utilities.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private TalonSRX elevator;
	private SensorCollection encoder;
	
	public Elevator()
	{
		elevator = new TalonSRX(RobotMap.elevatorMotor);
		encoder = new SensorCollection(elevator);
		encoder.setPulseWidthPosition(0, 0);
		elevator.setNeutralMode(NeutralMode.Brake);
		elevator.configForwardSoftLimitThreshold(Constants.maxElevatorTicks, Constants.kTimeoutMs);
		elevator.configForwardSoftLimitThreshold(Constants.minElevatorTicks, Constants.kTimeoutMs);
		elevator.configForwardSoftLimitEnable(true, Constants.kTimeoutMs);
		elevator.configReverseSoftLimitEnable(true, Constants.kTimeoutMs);
	}
	
	public double getCurrentPosition()
	{
		return encoder.getPulseWidthPosition();
	}
	
	public void moveElevator(double speed)
	{
		elevator.set(ControlMode.PercentOutput, speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Joystick_Elevator());
    }
    
	public TalonSRX getTalon() {
		return elevator;
	}
}

