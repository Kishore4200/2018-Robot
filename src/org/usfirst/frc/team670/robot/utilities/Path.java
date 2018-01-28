package org.usfirst.frc.team670.robot.utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.usfirst.frc.team670.robot.commands.components.Encoders_DriveDistance;
import org.usfirst.frc.team670.robot.commands.components.NavX_DriveDistance;
import org.usfirst.frc.team670.robot.commands.components.NavX_Pivot;
import org.usfirst.frc.team670.robot.commands.components.Time_DriveStraight;
import org.usfirst.frc.team670.robot.commands.components.Time_Pivot;
import org.usfirst.frc.team670.robot.commands.components.Ultrasonic_ObjectDrive;
import org.usfirst.frc.team670.robot.commands.components.Vision_LocatePowerUp;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 *Takes in a text file name and creates an autonomous command group using it which will then run
 *Format the text file as:
 *	command, arg, arg
 *	command, arg
 *	command
 *
 *The keys used for each command are :"EncDrive", "NavXDrive", "NavXPivot", "TimeDrive", 
			"TimePivot", "UltrasonicDrive", "LocatePowerUp"
 *
 *@author shaylandias
 */
public class Path{

//	private ArrayList<Command> commands;
	private CommandGroup commands;
	/**
	 * The keywords for the commands. Change this to match any auto commands.
	 */
	public final String[] possCommands = {"EncDrive", "NavXDrive", "NavXPivot", "TimeDrive", 
			"TimePivot", "UltrasonicDrive", "LocatePowerUp"};	
	
    public Path(String fileName) {
//    		commands = new ArrayList<Command>();
    		readData(fileName);
    }
    
 // Reads in array data from a simple text file containing asterisks (*)
 	private void readData (String filename) {
 		File dataFile = new File(filename);

 		if (dataFile.exists()) {

 			FileReader reader = null;
 			Scanner in = null;
 			try {
 				reader = new FileReader(dataFile);
 				in = new Scanner(reader);

 				while (in.hasNext()) {
 					String line = in.nextLine();
// 					String line = "";
 					String com;
 					double[] args = new double[5];
 					int argInd = 0;
 					int commandInd = 0;
// 					for(int i = 0; i < line.length(); i++) {
// 						if(Character.isLetter(line.charAt(i)) || input.charAt(i) == '-' || input.charAt(i) == ','  || input.charAt(i) == '.' || Character.isDigit(line.charAt(i)))
// 								line += input.charAt(i);
// 					}
 					
 					//Grab the command key and the args from the line
 					for(int i = 0; i < line.length(); i++) {
 						if(line.charAt(i) == ',') {
 							if(commandInd == 0) {
 								commandInd = i;
 							}else {
 								String num = "";
 								for(int j = i+1; j < line.length(); j++) {
 									if(line.charAt(j)!=',')
 										num+= line.charAt(i);
 								}
 								args[argInd] = Double.parseDouble(num);
 								argInd++;
 							}
 						}
 					}
 					com = line.substring(0, commandInd); //Check if this takes in the /n character at 0
 					switch(com) {
 					case "EncDrive": commands.addSequential(new Encoders_DriveDistance(args[0]));
 					case "NavXDrive": commands.addSequential(new NavX_DriveDistance(args[0]));
 					case "NavXPivot": commands.addSequential(new NavX_Pivot(args[0]));
 					case "TimeDrive": commands.addSequential(new Time_DriveStraight(args[0], args[1]));
 					case "TimePivot": commands.addSequential(new Time_Pivot(args[0], args[1]));
 					case "UltrasonicDrive": commands.addSequential(new Ultrasonic_ObjectDrive(args[0], args[1]));
 					case "LocatePowerUp": commands.addSequential(new Vision_LocatePowerUp());
 					}
 					
 				}
 			} catch (IOException ex) {
 				throw new IllegalArgumentException("Data file " + filename + " cannot be read.");
 			} finally {
 				if (in != null)
 					in.close();
 			}

 		} else {
 			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
 		}
 	}
 	
// 	public ArrayList<Command> getCommands() {
// 		return commands;
// 	}
 	
 	public CommandGroup getCommandGroup() {
 		return commands;
 	}
 	
    
}