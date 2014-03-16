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
	private int trend;

	public SensorModel(){
		this.trend = 0;
	}
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
		this.setTrend();
	}
	
	public String getReading(){
		return reading;
	}
	
	public void setReading(String reading){
		this.reading = reading;
	}
	/**
	 * Trend 0 = down, 1 = up -1 = sideways
	 */
	public void setTrend(){
		Double avg= 0.0;
		SensorReading lastReading = this.data.get(this.data.size()-1);
		if(this.data.size() > 11){
			for(int i = this.data.size()-10; i < this.data.size()-1; i++){
				avg+= this.data.get(i).getValue();
			}
		
		avg /= 10;
		
		Double arrow =lastReading.getValue()-avg;
		
		if(arrow > -0.1 && arrow < 0.1 )
			this.trend = -1;
		else if(arrow > 0)
			this.trend = 1;
		else
			this.trend = 0;
		}
	}
	public int getTrend(){
		return this.trend;
	}
	public Integer getTrendArrowImageSource() {
		return trendArrowImageSource;
	}

	public void setTrendArrowImageSource(Integer trendArrowImageSource) {
		this.trendArrowImageSource = trendArrowImageSource;
	}

}