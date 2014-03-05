package com.cs190.project.listviews;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cs190.project.HydroApp.SensorModel;
import com.example.android.navigationdrawerexample.R;

public class SensorsArrayAdapter extends ArrayAdapter<SensorModel> {
	
	private Context context;
	private ArrayList<SensorModel> mSensorList = new ArrayList<SensorModel>();

	public SensorsArrayAdapter(Context context, int resource,
			ArrayList<SensorModel> objects) 
	{
		super(context, resource, objects);
		this.context = context;
		this.mSensorList = objects;
		Log.v("Adapter, ", "count: " + mSensorList.size());
		
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		Log.v("getView: ", "count: " + mSensorList.size());
		LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.sensor_list_row, parent, false);
		
		TextView name = (TextView) rowView.findViewById(R.id.label);
		TextView reading = (TextView) rowView.findViewById(R.id.value);
		name.setText(mSensorList.get(position).getName());
		reading.setText(mSensorList.get(position).getReading());
		
		return rowView;
		

	}

}
