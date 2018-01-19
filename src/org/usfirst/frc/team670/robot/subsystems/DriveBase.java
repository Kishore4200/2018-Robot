/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.joysticks.Joystick_RobotDrive;
import org.usfirst.frc.team670.robot.utilities.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveBase extends Subsystem {
	private TalonSRX left1, right1, left2, right2;
	public static final double WHEEL_DIAMETER = 6.0;
	private static final int MAG_ENCODER_TICKS_PER_REVOLUTION = 1440; //The old values: 1440, new encoder mag: 4096
	public static final double GEAR_RATIO = 1.0;
	
	//PID VALUES
	public final double leftP = 0.1,
			leftI = 0,
			leftD = 0.5;
	public final double rightP = 0.1,
			rightI = 0,
			rightD = 0.5;	
	/**
	 * Creates the drivetrain, assuming that there are four talons.
	 * 
	 * @param fl Front-left Talon ID
	 * @param fr Front-right Talon ID
	 * @param bl Back-left Talon ID
	 * @param br Back-right Talon ID
	 */
	public DriveBase() {
		left1 = new TalonSRX(RobotMap.leftMotor1);
		left2 = new TalonSRX(RobotMap.leftMotor2);
		right1 = new TalonSRX(RobotMap.rightMotor1);
		right2 = new TalonSRX(RobotMap.rightMotor2);
		
		left1.setInverted(true);
		left2.setInverted(true);
		right1.setInverted(false);
		right2.setInverted(false);
		
		left1.setSensorPhase(true);
		left2.setSensorPhase(true);
		right1.setSensorPhase(false);
		right2.setSensorPhase(false);
		
		// Set follower control on back talons.
		left2.set(ControlMode.Follower, RobotMap.leftMotor1);
		right2.set(ControlMode.Follower, RobotMap.rightMotor1);
		initMotor(getLeft());
		initMotor(getRight());
		
		// Set up feedback sensors
		// Using CTRE_MagEncoder_Relative allows for relative ticks when encoder is zeroed out.
		// This allows us to measure the distance from any given point to any ending point.
		
	}
	
	/**
	 * Gets the Talon based on the ID.
	 * 
	 * @param id The device ID of the Talon.
	 * @return The Talon bound to the ID port,
	 *         or {@code null} if no drivetrain Talon was found.
	 *         
	 * @see CAN RobotMap.CAN
	 */
	public TalonSRX getTalon(int id) {
		switch(id) {
			case RobotMap.leftMotor1:
				return left1;
			case RobotMap.rightMotor1:
				return right1;
			case RobotMap.leftMotor2:
				return left2;
			case RobotMap.rightMotor2:
				return right2;
			default: // Not a drivetrain Talon!
				return null;
		}
	}
	
	public void drive(double left, double right) {
		left1.set(ControlMode.PercentOutput, left);
		right1.set(ControlMode.PercentOutput, -right);
	}
	
	public TalonSRX getLeft() {
		return left1;
	}
	
	public TalonSRX getRight() {
		return right1;
	}
	
	public void resetEncoders() {
    	left1.getSensorCollection().setQuadraturePosition(0, 0);
    	right1.getSensorCollection().setQuadraturePosition(0, 0);
    }
	
	/**
	 * Stops the motors by zeroing the left and right Talons.
	 */
	public void stop() {
		left1.set(ControlMode.Velocity, 0);
		right1.set(ControlMode.Velocity, 0);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new Joystick_RobotDrive());
	}
	
	public double getLeftEncPositionInFeet() {
		double ticks = left1.getSensorCollection().getQuadraturePosition();
		//Convert encoder ticks to feet
		return ((Math.PI * WHEEL_DIAMETER) / (MAG_ENCODER_TICKS_PER_REVOLUTION * GEAR_RATIO) * ticks) / 12;
	}
	
	public double getRightEncPositionInFeet() {
		double ticks = right1.getSensorCollection().getQuadraturePosition();
		//Convert encoder ticks to feet
		return ((Math.PI * WHEEL_DIAMETER) / (MAG_ENCODER_TICKS_PER_REVOLUTION * GEAR_RATIO) * ticks) / 12;
	}
	
	/**
     * <pre>s
	 * public double feetToEncoderTicks(double feet)
	 * </pre>
	 * Returns a value in ticks based on a certain value in feet using
	 * the Magnetic Encoder.
	 * @param feet The value in feet
	 * @return The value in ticks
     */
	public double feetToEncoderTicks(double feet) {
		return (MAG_ENCODER_TICKS_PER_REVOLUTION * GEAR_RATIO) / (Math.PI * WHEEL_DIAMETER) * feet;
	}
	
	private void initMotor(TalonSRX _talon) {
		/* lets grab the 360 degree position of the MagEncoder's absolute position */
		int absolutePosition = _talon.getSelectedSensorPosition(Constants.kTimeoutMs) & 0xFFF; /* mask out the bottom12 bits, we don't care about the wrap arounds */
        /* use the low level API to set the quad encoder signal */
        _talon.setSelectedSensorPosition(absolutePosition, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        
        /* choose the sensor and sensor direction */
        //???
        _talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        _talon.setSensorPhase(true);
        
        /* set the peak and nominal outputs, 12V means full */
        _talon.configNominalOutputForward(0, Constants.kTimeoutMs);
        _talon.configNominalOutputReverse(0, Constants.kTimeoutMs);
        _talon.configPeakOutputForward(1, Constants.kTimeoutMs);
        _talon.configPeakOutputReverse(-1, Constants.kTimeoutMs);
        /* set the allowable closed-loop error,
         * Closed-Loop output will be neutral within this range.
         * See Table in Section 17.2.1 for native units per rotation. 
         */
        _talon.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs); /* always servo */
        /* set closed loop gains in slot0 */
        _talon.config_kF(Constants.kPIDLoopIdx, 0.0, Constants.kTimeoutMs);
        _talon.config_kP(Constants.kPIDLoopIdx, 0.1, Constants.kTimeoutMs);
        _talon.config_kI(Constants.kPIDLoopIdx, 0.0, Constants.kTimeoutMs);
        _talon.config_kD(Constants.kPIDLoopIdx, 0.0, Constants.kTimeoutMs);

	}
	
	private void setPIDValues() {
		getLeft().config_kP(0, leftP, 0); //Lets Set this to 1 for testing to see if it works
		getLeft().config_kI(0, leftI, 0);
		getLeft().config_kD(0, leftD, 0);
		getRight().config_kP(0, rightP, 0);
		getRight().config_kI(0, rightI, 0);
		getRight().config_kD(0, rightD, 0);
	}
	
	public void drivePID(double endPosL, double endPosR, double percentVoltage) {
		getLeft().set(ControlMode.Position, endPosL);
		getRight().set(ControlMode.Position, endPosR);
	}
	
	
}