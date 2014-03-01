package com.cs190.project.HydroApp;

import com.cs190.project.HydroApp.MainActivity;
import android.app.Fragment;
import android.util.Log;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.android.navigationdrawerexample.R;
//This is really the fragment for the sensors, if i have time i will refactor to better naming practices, dp
public class ControllerFragment extends Fragment implements OnClickListener {
      
        public ControllerFragment() {}
        //Boolean fansOn = MainActivity.c.isFansOn();
        //Boolean lightsOn = MainActivity.c.isLightsOn();
       // Boolean waterPumpOn = MainActivity.c.isWaterPumpOn();
        //Boolean fansOn = true;
        Boolean lightsOn = false;
        //Boolean waterPumpOn = true;
        //ToggleButton fans;
        ToggleButton overRide;
        //ToggleButton waterPump;
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        	
        	 View rootView =  inflater.inflate(R.layout.fragment_controller, container, false);
        	 
        	 //TextView s1 = (TextView)rootView.findViewById(R.id.CurrentTimer);
        	 //TextView s2 = (TextView)rootView.findViewById(R.id.SignalStrength);
        	// MainActivity.c.setCurrentTimer(s1);
        	 //MainActivity.c.setSignalStrength(s2);
        	 
        	 //fans = (ToggleButton) rootView.findViewById(R.id.toggleButton2);	
        	 overRide = (ToggleButton) rootView.findViewById(R.id.overRide);
        	 //waterPump = (ToggleButton) rootView.findViewById(R.id.toggleButton3);
	  		 
//        	 if(fansOn){
//        		 fans.setChecked(true);
//        	 }
//        	 if(waterPumpOn){
//        		waterPump.setChecked(true);
//        	 }
//        	 if(lightsOn){
//        		 lights.setChecked(true);
//        	 }
        	 
//        	 fans.setOnClickListener(new View.OnClickListener(){
//        		 public void onClick(View v){
//        			 fansOn = !fansOn;
//        			 Log.v("Value of fanson", fansOn.toString());
//        		 }
//        	 });
        	 
        	 overRide.setOnClickListener(new View.OnClickListener(){
        		 public void onClick(View v){
        			 lightsOn = !lightsOn;
        			 Log.v("Value of lightson", lightsOn.toString());
        		 }
        	 });
        	 
//        	 waterPump.setOnClickListener(new View.OnClickListener(){
//        		 public void onClick(View v){
//        			 waterPumpOn = !waterPumpOn;
//        			 Log.v("Value of waterPumpon", waterPumpOn.toString());
//        		 }
//        	 });
        	 
        	 return rootView;
        }

		@Override
		public void onClick(View v) {
		}
		
		
		
    }