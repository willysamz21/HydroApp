package com.cs190.project.HydroApp;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.android.navigationdrawerexample.R;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ToggleButton;

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
        			 JSONObject obj = new JSONObject();;
        			 Bundle b = getArguments();
        			 int position = b.getInt("wirelessID");
        			 try {
						obj.put("wirelessID", MainActivity.wirelessList.get(position).getWirelessID());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			 Log.v("Controller", "obj: " + obj);
        			 if(!lightsOn)
        			 {
        				 MainActivity.socket.emit("wireless:turnOn", obj);
        				 lightsOn = true;
        			 }
        			 else
        			 {
        				 MainActivity.socket.emit("wireless:turnOff", obj);
        				 lightsOn = false;
        			 }
        				 
        			 
        	
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