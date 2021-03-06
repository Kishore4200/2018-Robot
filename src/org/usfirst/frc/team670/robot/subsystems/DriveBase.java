/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.joysticks.Joystick_Drive;
import org.usfirst.frc.team670.robot.utilities.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveBase extends Subsystem {
	private TalonSRX left1, right1, left2, right2;

	// PID VALUES
	/**
	 * Creates the drivetrain, assuming that there are four talons.
	 * 
	 * @param fl
	 *            Front-left Talon ID
	 * @param fr
	 *            Front-right Talon ID
	 * @param bl
	 *            Back-left Talon ID
	 * @param br
	 *            Back-right Talon ID
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

		// Set up feedback sensors
		// Using CTRE_MagEncoder_Relative allows for relative ticks when encoder
		// is zeroed out.
		// This allows us to measure the distance from any given point to any
		// ending point.

	}

	/**
	 * Gets the Talon based on the ID.
	 * 
	 * @param id
	 *            The device ID of the Talon.
	 * @return The Talon bound to the ID port, or {@code null} if no drivetrain
	 *         Talon was found.
	 * 
	 * @see CAN RobotMap.CAN
	 */
	public TalonSRX getTalon(int id) {
		switch (id) {
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
		left1.set(ControlMode.PercentOutput, -left);
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
		setDefaultCommand(new Joystick_Drive());
	}

	public double getLeftEncPositionInFeet() {
		double ticks = left1.getSensorCollection().getQuadraturePosition();
		// Convert encoder ticks to feet
		return ((Math.PI * Constants.DIAMETERinInchesDriveBase) / (Constants.drivebaseTickPerRotation * Constants.gearRatioDB) * ticks) / 12;
	}

	public double getRightEncPositionInFeet() {
		double ticks = right1.getSensorCollection().getQuadraturePosition();
		// Convert encoder ticks to feet
		return ((Math.PI * Constants.DIAMETERinInchesDriveBase) / (Constants.drivebaseTickPerRotation * Constants.gearRatioDB) * ticks) / 12;
	}

	/**
	 * <pre>
	 * s
	 * public double feetToEncoderTicks(double feet)
	 * </pre>
	 * 
	 * Returns a value in ticks based on a certain value in feet using the
	 * Magnetic Encoder.
	 * 
	 * @param feet
	 *            The value in feet
	 * @return The value in ticks
	 */
	public double feetToEncoderTicks(double feet) {
		return (Constants.drivebaseTickPerRotation * Constants.gearRatioDB) / (Math.PI * Constants.DIAMETERinInchesDriveBase) * feet;
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
		talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
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
		talon.config_kP(Constants.kPIDLoopIdx, Constants.Proportion, Constants.kTimeoutMs);
		talon.config_kI(Constants.kPIDLoopIdx, Constants.Integral, Constants.kTimeoutMs);
		talon.config_kD(Constants.kPIDLoopIdx, Constants.Derivative, Constants.kTimeoutMs);
	}

	public void singleStickDrive(Joystick joy) {
		double rSpeed, lSpeed;
		rSpeed = -joy.getX() - joy.getY();
		lSpeed = -joy.getX() + joy.getY();
		lSpeed = 0.5 * Math.pow(lSpeed, 3) + (1 - 0.5) * lSpeed;
		rSpeed = 0.5 * Math.pow(rSpeed, 3) + (1 - 0.5) * rSpeed;
		drive(lSpeed, rSpeed);
	}

	public void fieldDrive(Joystick left, boolean reversed) {
		int reverse = reversed ? -1 : 1;
		double rcw = left.getTwist();
		double forwrd = left.getY() * -1; /* Invert stick Y axis */
		double strafe = left.getX() * reverse;

		/* Adjust Joystick X/Y inputs by navX MXP yaw angle */

		double gyro_degrees = Robot.sensors.getYaw() * reverse;
		double gyro_radians = gyro_degrees * Math.PI / 180;
		double temp = forwrd * Math.cos(gyro_radians) + strafe * Math.sin(gyro_radians);
		strafe = -forwrd * Math.sin(gyro_radians) + strafe * Math.cos(gyro_radians);
		forwrd = temp;

		/* At this point, Joystick X/Y (strafe/forwrd) vectors have been */
		/* rotated by the gyro angle, and can be sent to drive system */
		singleStickDrive(strafe, forwrd);
	}

	public void singleStickDrive(double x, double y) {
		double rSpeed = 0;
		double lSpeed = 0;

		rSpeed = -x - y;
		lSpeed = -x + y;
		lSpeed = 0.5 * Math.pow(lSpeed, 3) + (1 - 0.5) * lSpeed;
		rSpeed = 0.5 * Math.pow(rSpeed, 3) + (1 - 0.5) * rSpeed;
		Robot.driveBase.drive(lSpeed, rSpeed);
	}

	public void singleDrive(Joystick joy) {
		double rSpeed, lSpeed;
		rSpeed = -joy.getX() - joy.getY();
		lSpeed = -joy.getX() + joy.getY();
		lSpeed = 0.5 * Math.pow(lSpeed, 3) + (1 - 0.5) * lSpeed;
		rSpeed = 0.5 * Math.pow(rSpeed, 3) + (1 - 0.5) * rSpeed;
		drive(lSpeed, rSpeed);
	}

	public void singleStickEther(Joystick joy) {
		double lSpeed = 0, rSpeed = 0;

		if (joy.getY() <= 0) {
			lSpeed = -joy.getY() + joy.getX();
			rSpeed = -joy.getY() - joy.getX();
		} else {
			lSpeed = -joy.getY() - joy.getX();
			rSpeed = -joy.getY() + joy.getX();
		}

		double max = Math.abs(lSpeed);

		if (max < Math.abs(rSpeed))
			max = Math.abs(rSpeed);

		if (max > 1) {
			lSpeed /= max;
			rSpeed /= max;
		}

		drive(lSpeed, rSpeed);

	}

}