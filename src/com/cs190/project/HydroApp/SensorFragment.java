package com.cs190.project.HydroApp;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs190.project.listviews.SensorsArrayAdapter;
import com.example.android.navigationdrawerexample.R;



public class SensorFragment extends ListFragment{
      
	    
	    public SensorsArrayAdapter adapter; 
	    public View rootView;
		
		public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    
		   
		    
		 
		  }
		
		@Override
		 public View onCreateView(LayoutInflater inflater, ViewGroup container,
		   Bundle savedInstanceState) {
		  return inflater.inflate(R.layout.fragment_sensors, container, false);
		 }
		
       	public void update(String ph, String air, String water, String humidity ) {
       		
       		DecimalFormat df = new DecimalFormat("####0.00");
       		Double d=0.0; 
       		if(air != null){
       			d = Double.parseDouble(air);
	       		d = ((d*9)/5)+32;
	       		air = df.format(d);
	       	}
       		
       		String[] readings = {df.format(new Double(ph)), df.format(new Double(water)), air, humidity};
       		
       		for(int i = 0; i < MainActivity.sensorList.size(); i++)
       		{
       			MainActivity.sensorList.get(i).setReading(readings[i]);
       			
       	
       		}
       		adapter.notifyDataSetChanged();	
   		
		}
       	public void createSensors(){
       	 adapter = new SensorsArrayAdapter(getActivity(),
			        R.layout.fragment_sensors, MainActivity.sensorList);
    				setListAdapter(adapter);
		    
       	}
       	
       

    }