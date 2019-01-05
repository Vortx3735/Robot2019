package org.usfirst.frc.team3735.robot.util.settings;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Setting extends Func{
	
	private String name;
	private double value;
	
	private boolean isReceiving;
	private boolean isVisible;
	private boolean isListening = false;
	
	private static ArrayList<Setting> settings = new ArrayList<Setting>();
	private static int fIteration;
	
	public Setting(String name, double defaultValue){
		this.name = name;
		this.value = defaultValue;
		
		SmartDashboard.putNumber(name, defaultValue);
		isVisible = true;
		isReceiving = true;
		settings.add(this);
	}
	
	//isListening is different from isReceiving because function onchange is called when a change is detected
	public void setIsListening(boolean val) {
		isListening = val;
		
	}

	
	/*
	 * @param flag	if true, it displays to the SmartDashboard but doesn't take input from it
	 * 				if false, the setting does not appear on the SmartDasboard
	 * 
	 */
	public Setting(String name, double defaultValue, boolean flag){
		this.name = name;
		this.value = defaultValue;
		
		if(flag){
			SmartDashboard.putNumber(name, defaultValue);
			isVisible = true;
		}else{
			isVisible = false;
		}
		isReceiving = false;
	}

	
	public double getValue(){
		return value;
	}
	
	public double getValueFetched() {
		fetch();
		return value;
	}
	
	public void fetch() {
		if(isReceiving) {
			if(isListening) {
				if(value != SmartDashboard.getNumber(name, value)) {
					value = SmartDashboard.getNumber(name, value);
					valueChanged(value);
				}
			}else {
				value = SmartDashboard.getNumber(name, value);
			}
		}
	}
	

	
	public void setValue(double value){
		this.value = value;
		if(isVisible){
			SmartDashboard.putNumber(name, this.value);
		}
	}
	
	public static void fetchAll() {
		for(Setting s : settings) {
			s.fetch();
		}
	}
	
	/**
	 * This method is designed to be called every tick, and only fetches one setting at a time,
	 * which helps by not retrieving everything from the SDB at once every tick, or once every second.
	 * Number of Settings as of 6/29: 19. so every setting is updated every .4 seconds
	 */
	public static void fetchAround() {
		if(settings != null && !settings.isEmpty()) {
			fIteration++;
			if(fIteration >= settings.size()) {
				fIteration = 0;
			}
			settings.get(fIteration).fetch();
		}
	}
	
	//Override me if you want!
	public void valueChanged(double val) {
		
	}
	
	public Func reverse() {
		return new Func() {
			@Override
			public double getValue() {
				return -getValueFetched();
			}
		};
	}
	
	public Func multiply(Func s) {
		return new Func() {
			@Override
			public double getValue() {
				return getValueFetched() * s.getValue();
			}
		};
	}
	
}

