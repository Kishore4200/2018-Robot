/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team670.robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team670.robot.commands.ForwardThenRight;
import org.usfirst.frc.team670.robot.commands.autonomous.CancelCommand;
import org.usfirst.frc.team670.robot.commands.components.Center;
import org.usfirst.frc.team670.robot.commands.components.Encoders_DriveDistance;
import org.usfirst.frc.team670.robot.commands.components.NavX_DriveDistance;
import org.usfirst.frc.team670.robot.commands.components.NavX_Pivot;
import org.usfirst.frc.team670.robot.subsystems.Vision;
import org.usfirst.frc.team670.robot.utilities.PathFinder;
import org.usfirst.frc.team670.robot.subsystems.Climber;
import org.usfirst.frc.team670.robot.subsystems.DriveBase;
import org.usfirst.frc.team670.robot.subsystems.Elevator;
import org.usfirst.frc.team670.robot.subsystems.Intake;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 * 
 * @author vsharma
 */
public class Robot extends TimedRobot {
	public static final Elevator elevator = new Elevator();
	public static final DriveBase driveBase = new DriveBase();
	public static final Intake intake = new Intake();
	public static final Climber climber = new Climber();
	public static Vision visionCuboid;
	public static PathFinder finder = new PathFinder();

	public static SensorThread sensors;
	public static OI oi;
	public static Preferences pathList;

	Command m_autonomousCommand;
	public static SendableChooser<Command> m_chooser = new SendableChooser<>();
	public static SendableChooser<Double> autonomousDelay = new SendableChooser<>();
	public static SendableChooser<Boolean> ApproachType = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		sensors = new SensorThread();
		visionCuboid = new Vision();

		m_chooser.addDefault("Do Nothing", new CancelCommand());
		m_chooser.addObject("Turn Right 90 degrees", new NavX_Pivot(90));
		m_chooser.addObject("Turn Left 90 degrees", new NavX_Pivot(-90));
		m_chooser.addObject("Turn Right 60 degrees", new NavX_Pivot(60));
		m_chooser.addObject("Turn Left 60 degrees", new NavX_Pivot(-60));
		m_chooser.addObject("1ft_navX", new NavX_DriveDistance(1));
		m_chooser.addObject("1ft_encoders", new Encoders_DriveDistance(1));
		m_chooser.addObject("3ft_encoders_back", new Encoders_DriveDistance(-3));
		m_chooser.addObject("Drive 1 Foot NavX", new NavX_DriveDistance(1));
		m_chooser.addObject("Drive 3 Foot Forward and Turn NavX", new ForwardThenRight());
		m_chooser.addObject("Center", new Center());

		// autonomousDelay.addDefault("0 Second", 0.0);
		// autonomousDelay.addObject("1 Second", 1.0);
		// autonomousDelay.addObject("2 Second", 2.0);
		// autonomousDelay.addObject("3 Second", 3.0);
		// autonomousDelay.addObject("4 Second", 4.0);
		// autonomousDelay.addObject("5 Second", 5.0);

		ApproachType.addDefault("Straight", true);
		ApproachType.addObject("Side", false);

		SmartDashboard.putData("Auto mode", m_chooser);
		SmartDashboard.putData("Auton Delay", autonomousDelay);
		SmartDashboard.putData("Approach Type", ApproachType);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		// putData();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		putData();
		Scheduler.getInstance().run();
		// logger.logData();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		putData();
		Scheduler.getInstance().run();
		// logger.logData();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	public void putData() {

	}
}
