package org.usfirst.frc.team670.robot.commands.components;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.Constants;

import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Command;
/**
 * 
 * Uses a PID control loop plus the navX getDisplacement to move a given distance in feet
 * 
 * @author vsharma8363
 *
 */
public class PID_Encoders_DriveDistance extends Command{

	private double distance, finalDistance, initialDisplacement;
    private double integral, derivative, previous_error = 0;
    private double lSpeed, rSpeed;
        
 	private SensorCollection quadEncoderLeft;
 	private SensorCollection quadEncoderRight;

 	private TalonSRX leftMotor1;
 	private TalonSRX rightMotor1;

	public PID_Encoders_DriveDistance(double feet) {
		// Use requires() here to declare subsystem dependencies
		lSpeed = 0;
		rSpeed = 0;
		
		leftMotor1 = Robot.driveBase.getLeft();
 		rightMotor1 = Robot.driveBase.getRight();
 		quadEncoderLeft = new SensorCollection(leftMotor1);
 		quadEncoderRight = new SensorCollection(rightMotor1);
 		distance = ((feet*12.0)/(Math.PI*Constants.DIAMETERinInches)) * Constants.ticksPerRotation;
 		
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		initialDisplacement = 0;
 		quadEncoderLeft.setQuadraturePosition(0, 0);
 		quadEncoderRight.setQuadraturePosition(0, 0);
		finalDistance = initialDisplacement + distance;
		integral = 0;
		previous_error = 0;
		derivative = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double percentComplete = (distance - Math.abs((getDisplacement() - initialDisplacement)))/distance;
		double error = finalDistance - getDisplacement(); // Error = Target - Actual
        this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        derivative = (error - this.previous_error) / .02;
        lSpeed = Constants.leftP*error + Constants.leftI*this.integral + Constants.leftD*derivative;
        rSpeed = Constants.rightP*error + Constants.rightI*this.integral + Constants.rightD*derivative;
        previous_error = error;
		
		if(distance > 0)
			Robot.driveBase.drive(lSpeed, rSpeed);
		else if(distance < 0) 
			Robot.driveBase.drive(-lSpeed, -rSpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		if(finalDistance - getDisplacement() < 0.1)
		{
			return true;
		}
		else 
		{
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
	
	/**
	 * Gets Displacement in feet
	 */
	private double getDisplacement() {
		return (quadEncoderLeft.getQuadraturePosition() + quadEncoderRight.getQuadraturePosition())/2.0;
	}
}
