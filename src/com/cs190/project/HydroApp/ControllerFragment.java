package com.cs190.project.HydroApp;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.android.navigationdrawerexample.R;

//This is really the fragment for the sensors, if i have time i will refactor to better naming practices, dp
public class ControllerFragment extends Fragment implements OnClickListener {

	public ControllerFragment() {}
	Boolean lightsOn = false;
	ToggleButton overRide;
	ArrayList<Timer> timers = new ArrayList<Timer>();
	ArrayAdapter<Timer> adapter;
	Bundle b;
	int position;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		b = getArguments();
		position = b.getInt("wirelessID");
		View rootView = inflater.inflate(R.layout.fragment_controller, container, false);

		ImageView signalStrengthImageView = (ImageView)(rootView.findViewById(R.id.signal_strength_image));
		int wirelessID = ((Integer) (getArguments().get("wirelessID"))).intValue();
		int signalStrength = 70;
		if (20<signalStrength && signalStrength<40)
			signalStrengthImageView.setImageResource(R.drawable.statsyssignal1cdma96x96);
		else if (40<signalStrength && signalStrength<60)
			signalStrengthImageView.setImageResource(R.drawable.statsyssignal2cdma96x96);
		else if (60<signalStrength && signalStrength<80)
			signalStrengthImageView.setImageResource(R.drawable.statsyssignal3cdma96x96);
		else if (80<signalStrength)
			signalStrengthImageView.setImageResource(R.drawable.statsyssignal4cdma96x96);

		timers.add(MainActivity.wirelessList.get(position).getTimer());
		adapter = new ArrayAdapter<Timer>(getActivity(), R.layout.timer_list_row, timers);

		ListView listView = (ListView) rootView.findViewById(R.id.listview);
		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();


		overRide = (ToggleButton) rootView.findViewById(R.id.overRide);


		TextView name = (TextView) rootView.findViewById(R.id.label);
		name.setText(MainActivity.wirelessList.get(position).getName());

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
					MainActivity.wirelessList.get(position).setState(true);
					MainActivity.socket.emit("wireless:turnOn", obj);
					lightsOn = true;
				}
				else
				{
					MainActivity.wirelessList.get(position).setState(false);
					MainActivity.socket.emit("wireless:turnOff", obj);
					lightsOn = false;
				}

			}
		});


		return rootView;
	}

	@Override
	public void onClick(View v) {
	}



}