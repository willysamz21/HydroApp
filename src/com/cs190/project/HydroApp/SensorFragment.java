package com.cs190.project.HydroApp;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;

import com.cs190.project.listviews.SensorsArrayAdapter;
import com.example.android.navigationdrawerexample.R;

public class SensorFragment extends ListFragment{

	public SensorsArrayAdapter adapter; 
	public View rootView;
	SensorUniqueFragment fragment = null;
	private Boolean createdyet = false;
	private int readingCounter = 0;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
	} 

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_sensors, container, false);
	}

	public void update(String ph, String air, String water, String humidity ) {
		//if(!createdyet){
		//	return;
		//}
		DecimalFormat df = new DecimalFormat("####0.00");
		Double d=0.0; 
		if(air != null){
			d = Double.parseDouble(air);	
			air = df.format(d);
		}

		if(fragment!=null)
			fragment.updateFragmentDisplay();

		String[] readings = {ph, water, air, humidity};
		readingCounter++;
		for(int i = 0; i < MainActivity.sensorList.size(); i++)
		{  
			if(readingCounter >= 240){
				Calendar c = new GregorianCalendar();
				SensorReading sr = new SensorReading();
				SimpleDateFormat f = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.ENGLISH);
				String dateStr = f.format(c.getTime());
				sr.setDate(dateStr);
				sr.setValue(Double.valueOf((readings[i])));
				MainActivity.sensorList.get(i).getData().add(sr);
				MainActivity.sensorList.get(i).setTrend();
				
			}
			MainActivity.sensorList.get(i).setReading(readings[i]);
			
		}
		if(readingCounter >= 240)
			readingCounter = 0;
		
		adapter.notifyDataSetChanged();	

	}

	public void createSensors(){
		createdyet = true;
		adapter = new SensorsArrayAdapter(getActivity(),
				R.layout.fragment_sensors, MainActivity.sensorList);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		fragment = new SensorUniqueFragment();
		Bundle args = new Bundle();
		args.putString("position", Integer.toString(position));
		fragment.setArguments(args);

		FragmentTransaction transaction = getFragmentManager().beginTransaction();

		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack so the user can navigate back
		transaction.replace(R.id.content_frame, fragment);
		transaction.addToBackStack(null);

		// Commit the transaction
		transaction.commit();

	}



}