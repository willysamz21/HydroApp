package com.cs190.project.HydroApp;

import com.cs190.project.HydroApp.MainActivity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.navigationdrawerexample.R;

public class SensorFragment extends Fragment {
      
        public SensorFragment() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        	
        	 View rootView =  inflater.inflate(R.layout.fragment_sensors, container, false);
        		
        	 TextView t1 = (TextView)rootView.findViewById(R.id.TV1);
             TextView t2 = (TextView) rootView.findViewById(R.id.T2);
             TextView t3 = (TextView) rootView.findViewById(R.id.T3);
             TextView t4 = (TextView) rootView.findViewById(R.id.T4);
             MainActivity.c.setAirTempText(t2);
	  		 MainActivity.c.setHumidityText(t4);
	  		 MainActivity.c.setPhText(t1);
	  		 MainActivity.c.setWaterTempText(t3);
	  		       	
        	 return rootView;
        }
    }