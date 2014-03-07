package com.cs190.project.HydroApp;

import java.util.ArrayList;
import java.util.List;

public class WirelessModel {

	private String name;
	private String wirelessID;
	private String _id;
	private List<SensorReading> data = new ArrayList<SensorReading>();
	private Timer timer;
	
	public String getName() {
	return name;
	}
	
	public void setName(String name) {
	this.name = name;
	}
	
	public String getWirelessID() {
	return wirelessID;
	}
	
	public void setWirelessID(String wirelessID) {
	this.wirelessID = wirelessID;
	}
	
	public String get_id() {
	return _id;
	}
	
	public void set_id(String _id) {
	this._id = _id;
	}
	
	public List<SensorReading> getData() {
	return data;
	}
	
	public void setData(List<SensorReading> data) {
	this.data = data;
	}
	
	public Timer getTimer() {
	return timer;
	}
	
	public void setTimer(Timer timer) {
	this.timer = timer;
	}
	

}