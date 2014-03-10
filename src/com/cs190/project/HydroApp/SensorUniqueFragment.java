package com.cs190.project.HydroApp;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView; 

import com.example.android.navigationdrawerexample.R;

public class SensorUniqueFragment extends Fragment {

	private int position = -1;
	 
	public SensorUniqueFragment(){}
 
	public void updateFragmentDisplay(){
		View rootView = this.getView();
		if(rootView!=null){
			TextView tvDisplay = (TextView)rootView.findViewById(R.id.displayValue);
			tvDisplay.setText(MainActivity.sensorList.get(position).getReading());		
		}      
	} 

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView =  inflater.inflate(R.layout.fragment_sensorunique, container, false);
		int positionClick = (Integer) Integer.parseInt((String) getArguments().get("position"));
		position = positionClick;
		TextView title = (TextView)rootView.findViewById(R.id.SensorTitle);
		title.setText(MainActivity.sensorList.get(positionClick).getName());
		TextView value = (TextView)rootView.findViewById(R.id.displayValue);
		value.setText(MainActivity.sensorList.get(positionClick).getReading());	
		TextView minTv = (TextView)rootView.findViewById(R.id.MinValue);
		minTv.setText(MainActivity.sensorList.get(positionClick).getMin().toString());	
		TextView maxTv = (TextView)rootView.findViewById(R.id.MaxValue);
		maxTv.setText(MainActivity.sensorList.get(positionClick).getMax().toString());	
		return rootView;
	}




}
