package org.usfirst.frc.team670.robot.commands.components;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Center extends Command {

	Preferences prefs;
	String leftLL, rightLL;

	public Center() {
		prefs = Preferences.getInstance();
		String s = "gell";
	}

	public void robotInit() {
		leftLL = prefs.getString("LL-left", "N/A");
		rightLL = prefs.getString("LL-right", "N/A");
	}

	protected void execute() {
		System.out.println(">>>>");
		System.out.println(prefs.getString("LL-left", "N/A") + ", " + prefs.getString("LL-right", "N/A"));
	}

	public String getLeft() {
		return leftLL;
	}

	public String getRight() {
		return rightLL;
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
