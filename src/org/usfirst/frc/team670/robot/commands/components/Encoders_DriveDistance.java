package org.usfirst.frc.team670.robot.commands.components;

import org.usfirst.frc.team670.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Uses Talons and mag encoders to drive a set distance.
 */
public class Encoders_DriveDistance extends Command {

	private static double MOVE_THRESHOLD = .1;
	private double distance;
	private double percentVoltage; //Voltage is NOW from [-1, 1]
    private double endPosL, endPosR;
    private double waitTime;
	
    /**
     * 
     * @param distance in inches
     * @param voltage -1.0 to 1.0
     */
    public Encoders_DriveDistance(double distance, double percentVoltage) {
    	this.distance = distance;
    	this.percentVoltage = percentVoltage;
    	endPosL = distance / (Math.PI * Robot.driveBase.WHEEL_DIAMETER);
    	endPosR = -endPosL;
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	Robot.driveBase.resetEncoders();

		Robot.driveBase.getLeft().config_kP(0, Robot.driveBase.leftP, 0);
		Robot.driveBase.getLeft().config_kI(0, Robot.driveBase.leftI, 0);
		Robot.driveBase.getLeft().config_kD(0, Robot.driveBase.leftD, 0);
		Robot.driveBase.getRight().config_kP(0, Robot.driveBase.rightP, 0);
		Robot.driveBase.getRight().config_kI(0, Robot.driveBase.rightI, 0);
		Robot.driveBase.getRight().config_kD(0, Robot.driveBase.rightD, 0);

		Robot.driveBase.getLeft().configNominalOutputForward(0, 0);
		Robot.driveBase.getLeft().configNominalOutputReverse(0, 0);
		Robot.driveBase.getLeft().configPeakOutputForward(percentVoltage, 0);
		Robot.driveBase.getLeft().configPeakOutputReverse(-percentVoltage, 0);
		Robot.driveBase.getRight().configNominalOutputForward(0, 0);
		Robot.driveBase.getRight().configNominalOutputReverse(0, 0);
		Robot.driveBase.getRight().configPeakOutputForward(percentVoltage, 0);
		Robot.driveBase.getRight().configPeakOutputReverse(-percentVoltage, 0);


		Robot.driveBase.getLeft().set(ControlMode.Position, endPosL);
		Robot.driveBase.getRight().set(ControlMode.Position, endPosR);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean isFinished = false;
        
        double leftPos = org.usfirst.frc.team670.robot.Robot.driveBase.getLeft().getSelectedSensorPosition(0);
        double rightPos = Robot.driveBase.getRight().getSelectedSensorPosition(0);

        if ((leftPos > endPosL - MOVE_THRESHOLD && leftPos < endPosL + MOVE_THRESHOLD)
				&& (rightPos > endPosR - MOVE_THRESHOLD && rightPos < endPosR + MOVE_THRESHOLD)) {
   			isFinished = true;
   		}

    	return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {

    	Robot.driveBase.stop();
    	Robot.driveBase.getLeft().getSensorCollection().setQuadraturePosition(0, 10);
    	Robot.driveBase.getRight().getSensorCollection().setQuadraturePosition(0, 10);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

/*
import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class Encoders_DriveDistance extends Command {

	// private char direction;
	private double distanceToTravel;
	private final double PPR = 1440;
	private final double DIAMETER = 0;
	private double encoderTicksToTravel;
	private Encoder encoder;
	private double speed;

	public Encoders_DriveDistance(double inches) {
		// Use requires() here to declare subsystem dependencies
		encoder = new Encoder(3, 4);
		encoder.reset();
		requires(Robot.driveBase);
		distanceToTravel = inches;
		encoderTicksToTravel = (360 / (Math.PI * DIAMETER)) * distanceToTravel;
		speed = 0;
	}

	// Called just before this Command runs the first time
	protected void initialize(double inches) {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		while (encoder.getDistance() < encoderTicksToTravel) {
			Robot.driveBase.drive(speed, speed);
		}
		
		Robot.driveBase.drive(0,  0);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		if (encoder.getDistance() >= encoderTicksToTravel) {
			return true;
		} else {
			return false;
		}

	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.driveBase.drive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
*/
