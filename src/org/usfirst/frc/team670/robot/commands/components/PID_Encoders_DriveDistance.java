package org.usfirst.frc.team670.robot.commands.components;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * 
 * Uses a PID control loop plus the navX getDisplacement to move a given distance in feet
 * 
 * @author vsharma8363
 *
 */
public class PID_Encoders_DriveDistance extends Command{

	private double ticksToTravel;
	
	public PID_Encoders_DriveDistance(double feet) {
		
 		this.ticksToTravel = ((feet*12.0)/(Math.PI*Constants.DIAMETERinInches)) * Constants.ticksPerRotation;
 		System.out.println("ticks to travel: " + ticksToTravel);
 		SmartDashboard.putString("ticks to travel:", ""+ticksToTravel);
 		
		requires(Robot.driveBase);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.driveBase.initPID(Robot.driveBase.getLeft());
		Robot.driveBase.initPID(Robot.driveBase.getRight());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveBase.getLeft().set(ControlMode.Position, ticksToTravel); 
		Robot.driveBase.getRight().set(ControlMode.Position, ticksToTravel); /* 50 rotations in either direction */
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() 
	{
			return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		//Robot.driveBase.drive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
