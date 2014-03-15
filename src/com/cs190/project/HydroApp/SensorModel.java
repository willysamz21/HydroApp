package com.cs190.project.HydroApp;

import java.util.ArrayList;

import android.widget.ImageView;

public class SensorModel {

	private String _id;
	private Integer max;
	private Integer min;
	private String name;
	private String reading;
	private ArrayList<SensorReading> data = new ArrayList<SensorReading>();
	private Integer trendArrowImageSource;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<SensorReading> getData() {
		return data;
	}

	public void setData(ArrayList<SensorReading> data) {
		this.data = data;
	}
	
	public String getReading(){
		return reading;
	}
	
	public void setReading(String reading){
		this.reading = reading;
	}


	public Integer getTrendArrowImageSource() {
		return trendArrowImageSource;
	}

	public void setTrendArrowImageSource(Integer trendArrowImageSource) {
		this.trendArrowImageSource = trendArrowImageSource;
	}

}