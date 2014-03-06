package com.cs190.project.HydroApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class SensorReading {

	private Double value;
	private String date;
	private String _id;
	private Date convertedDate;
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date){
		this.date = date;
	}
	public Date getConvertedDate() {
		
		Date returnDate = null;
		try {
			returnDate =  new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.ENGLISH).parse(this.date);
		} catch (ParseException e) {
			
			e.printStackTrace();
		};
		return returnDate;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

}