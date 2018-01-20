package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.joysticks.Joystick_Elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private TalonSRX elevator;
	private SensorCollection Encoder;
	
	public Elevator()
	{
		elevator = new TalonSRX(RobotMap.elevatorMotor);
		Encoder = new SensorCollection(elevator);
		Encoder.setQuadraturePosition(0, 0);
	}
	
	public double getCurrentPosition()
	{
		return Encoder.getQuadraturePosition();
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
		// TODO Auto-generated method stub
		return elevator;
	}
}

