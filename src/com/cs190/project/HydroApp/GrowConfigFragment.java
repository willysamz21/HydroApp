package com.cs190.project.HydroApp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cs190.project.UserConfiguration.JsonManip;
import com.cs190.project.UserConfiguration.Sensors;
import com.cs190.project.UserConfiguration.UserConfigs;
import com.example.android.navigationdrawerexample.R;

public class GrowConfigFragment extends Fragment {
	private UserConfigs mRecConfigs;
	private TextView mPlantName;
	private TextView mRecPh;
	private TextView mRecTemp;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_growconfig, parent, false);
		
		mRecConfigs = JsonManip.jsonToUserConfigs();
		
		mPlantName = (TextView)v.findViewById(R.id.plant_name_label);
		mPlantName.setText(mRecConfigs.getPlantName());
		
		Sensors sensors = mRecConfigs.getStages().get(0).getSensors();
		
		mRecPh = (TextView)v.findViewById(R.id.rec_ph_level);
		mRecPh.setText(String.valueOf(sensors.getPhSensor()));
		
		mRecTemp = (TextView)v.findViewById(R.id.rec_water_temp);
		mRecTemp.setText(String.valueOf(sensors.getTempSensor()));

		return v;
	}

}
