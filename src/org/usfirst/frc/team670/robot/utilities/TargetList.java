package org.usfirst.frc.team670.robot.utilities;
import java.util.ArrayList;

public class TargetList 
{
	private ArrayList<Target> list;
	private ArrayList<String> combo;
	
	public TargetList()
	{
		
	}
	
	public void add(Target t, String combo)
	{
		list.add(t);
		this.combo.add(combo);
	}
	
	public void remove(Target t)
	{
		int index = list.indexOf(t);
		list.remove(index);
		this.combo.remove(index);
	}
	
	public void clear()
	{
		list.clear();
		this.combo.clear();
	}
	
	/*
	 * @param priorityNumber --> 
	 */
	public Target getTarget(String s)
	{
		int index = this.combo.indexOf(s);
		return this.list.get(index);
	}
}
