package com.cs190.project.HydroApp;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
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
		// MainActivity.c.setCurrentTimer(s1);
		//MainActivity.c.setSignalStrength(s2);
		ImageView signalStrengthImageView = (ImageView)(rootView.findViewById(R.id.signal_strength_image));
		int wirelessID = ((Integer) (getArguments().get("wirelessID"))).intValue();
		//int signalStrength = MainActivity.wirelessList.get(wirelessID).getSignalStrength().intValue();
		int signalStrength = 70;
		if (20<signalStrength && signalStrength<40)
			signalStrengthImageView.setImageResource(R.drawable.stat_sys_signal_1_cdma);
		else if (40<signalStrength && signalStrength<60)
			signalStrengthImageView.setImageResource(R.drawable.stat_sys_signal_2_cdma);
		else if (60<signalStrength && signalStrength<80)
			signalStrengthImageView.setImageResource(R.drawable.stat_sys_signal_3_cdma);
		else if (80<signalStrength)
			signalStrengthImageView.setImageResource(R.drawable.stat_sys_signal_4_cdma);

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