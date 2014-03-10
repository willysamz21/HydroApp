package com.cs190.project.HydroApp;

import java.text.DecimalFormat;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cs190.project.listviews.SensorsArrayAdapter;
import com.example.android.navigationdrawerexample.R;

public class SensorFragment extends ListFragment{

	public SensorsArrayAdapter adapter; 
	public View rootView;
	SensorUniqueFragment fragment = null;
	private Boolean createdyet = false;
     	
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
			d = ((d*9)/5)+32;
			air = df.format(d);
		}
				
		if(fragment!=null)
			fragment.updateFragmentDisplay();

		String[] readings = {ph, water, air, humidity};

		for(int i = 0; i < MainActivity.sensorList.size(); i++)
		{  
			MainActivity.sensorList.get(i).setReading(readings[i]);

 
		}
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