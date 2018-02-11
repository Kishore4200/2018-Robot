package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.joysticks.Joystick_Elevator;
import org.usfirst.frc.team670.robot.utilities.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
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
		elevator.configForwardSoftLimitThreshold(Constants.maxElevatorTicks, Constants.kTimeoutMs);
		elevator.configForwardSoftLimitThreshold(Constants.minElevatorTicks, Constants.kTimeoutMs);
		elevator.configForwardSoftLimitEnable(true, Constants.kTimeoutMs);
		elevator.configReverseSoftLimitEnable(true, Constants.kTimeoutMs);
	}
	
	public void initPID(TalonSRX talon) {
		int absolutePosition = talon.getSelectedSensorPosition(Constants.kTimeoutMs)
				& 0xFFF; /*
							 * mask out the bottom12 bits, we don't care about
							 * the wrap arounds
							 */
		/* use the low level API to set the quad encoder signal */
		talon.setSelectedSensorPosition(absolutePosition, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

		/* choose the sensor and sensor direction */
		talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		talon.setSensorPhase(true);

		/* set the peak and nominal outputs, 12V means full */
		talon.configNominalOutputForward(0, Constants.kTimeoutMs);
		talon.configNominalOutputReverse(0, Constants.kTimeoutMs);
		talon.configPeakOutputForward(1, Constants.kTimeoutMs);
		talon.configPeakOutputReverse(-1, Constants.kTimeoutMs);
		/*
		 * set the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		talon.configAllowableClosedloopError(0, Constants.kPIDLoopIdx,
				Constants.kTimeoutMs); /* always servo */
		/* set closed loop gains in slot0 */
		talon.config_kF(Constants.kPIDLoopIdx, 0.0, Constants.kTimeoutMs);
		talon.config_kP(Constants.kPIDLoopIdx, Constants.ProportionElevator, Constants.kTimeoutMs);
		talon.config_kI(Constants.kPIDLoopIdx, Constants.IntegralElevator, Constants.kTimeoutMs);
		talon.config_kD(Constants.kPIDLoopIdx, Constants.DerivativeElevator, Constants.kTimeoutMs);
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

