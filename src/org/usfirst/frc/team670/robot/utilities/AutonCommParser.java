package org.usfirst.frc.team670.robot.utilities;

import java.util.*;

import org.usfirst.frc.team670.robot.commands.components.Encoders_DriveDistance;
import org.usfirst.frc.team670.robot.commands.components.NavX_DriveDistance;
import org.usfirst.frc.team670.robot.commands.components.NavX_Pivot;
import org.usfirst.frc.team670.robot.commands.components.Time_Delay;
import org.usfirst.frc.team670.robot.commands.components.Time_DriveStraight;
import org.usfirst.frc.team670.robot.commands.components.Time_Pivot;
import org.usfirst.frc.team670.robot.commands.components.Vision_LocatePowerUp;

import java.io.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonCommParser {

	/*
  protected static class CommandDefs {
    public List<CommandDef> commands;
  }

  protected static class CommandDef {
    public String command;
    public Double angle;
    public Double distance;
  }
	 */

	public CommandGroup parseCommands(){
		ObjectMapper mapper = new ObjectMapper();
		CommandGroup comms = new CommandGroup();
		/*
    try {
      CommandDefs cmds = mapper.readValue(new File("test.json"), CommandDefs.class);
      for (CommandDef cmdDef : cmds.commands) {
        System.out.println("command: " + cmdDef.command + ", angle: " + cmdDef.angle + ", distance: " + cmdDef.distance);
      }
		 */
		try {
			Map<String, Object> cmds = mapper.readValue(new File("test.json"), new TypeReference<Map<String, Object>>(){});
			for (Map<String, Object> cmd : (List<Map>)cmds.get("commands")) {

				if(((String)cmd.get("command")).equals("drive")) {
					try {
						comms.addSequential(drive(cmds));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else if(((String)cmd.get("command")).equals("pivot")) {
					try {
						comms.addSequential(pivot(cmds));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else if(((String)cmd.get("command")).equals("delay")) {
					try {
						comms.addSequential(delay(cmds));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else if(((String)cmd.get("command")).equals("locatePowerUp")) {
					try {
						comms.addSequential(locate(cmds));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return comms;
	}

	//Separate Command Checkers: check which parameters are given to pick what command to use and if the commands are available
	private Command drive(Map<String, Object> cmd) throws Exception {
		if(((String)cmd.get("distance")) != null) {
			if(AutoCommAvailability.ENC_DRIVE)
				return new Encoders_DriveDistance((Double)cmd.get("distance")); //Idk if the Double cast will work, may have to cast to a String and then parseDouble(
			else if(AutoCommAvailability.NAVX_DRIVE)
				return new NavX_DriveDistance((Double)cmd.get("distance"));
			else
				throw new Exception("INVALID COMMAND PARAMETERS");
		}
		else if(((String)cmd.get("time")) != null && ((String)cmd.get("speed")) != null) {
			if(AutoCommAvailability.TIME_DRIVE)
				return new Time_DriveStraight((Double)cmd.get("time"), (Double)cmd.get("speed"));
			else
				throw new Exception("INVALID COMMAND PARAMETERS");
		}
		else
			throw new Exception("COMMAND NOT AVAILABLE");
	}

	private Command pivot(Map<String, Object> cmd) throws Exception {
		if(((String)cmd.get("angle")) != null) {
			if(AutoCommAvailability.NAVX_PIVOT)
				return new NavX_Pivot((Double)cmd.get("angle")); //Idk if the Double cast will work, may have to cast to a String and then parseDouble(
			else
				throw new Exception("INVALID COMMAND PARAMETERS");
		}
		else if(((String)cmd.get("time")) != null && ((String)cmd.get("speed")) != null) {
			if(AutoCommAvailability.TIME_PIVOT)
				return new Time_Pivot((Double)cmd.get("time"), (Double)cmd.get("speed"));
			else
				throw new Exception("INVALID COMMAND PARAMETERS");
		}
		else
			throw new Exception("COMMAND NOT AVAILABLE");
	}

	private Command delay(Map<String, Object> cmd) throws Exception {
		if(((String)cmd.get("time")) != null) {
			if(AutoCommAvailability.TIME_DELAY)
				return new Time_Delay((Double)cmd.get("time")); //Idk if the Double cast will work, may have to cast to a String and then parseDouble(
			else
				throw new Exception("INVALID COMMAND PARAMETERS");
		}
		else
			throw new Exception("COMMAND NOT AVAILABLE");
	}

	private Command locate(Map<String, Object> cmd) throws Exception {
		if(AutoCommAvailability.POWERUP_LOCATOR)
			return new Vision_LocatePowerUp();
		else
			throw new Exception("COMMAND NOT AVAILABLE");
	}


}
