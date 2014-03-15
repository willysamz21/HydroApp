package com.cs190.project.HydroApp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.cs190.project.listviews.SensorsArrayAdapter;
import com.example.android.navigationdrawerexample.R;

public class SensorFragment extends ListFragment{

	public SensorsArrayAdapter adapter; 
	public View rootView;
	SensorUniqueFragment fragment = null;
	private Boolean createdyet = false;

	static final int NUMBER_OF_VALUES_FOR_TREND = 3;

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

			SensorModel sensor = MainActivity.sensorList.get(i);
			ArrayList<SensorReading> readValues = sensor.getData();
			Double[] values = new Double[NUMBER_OF_VALUES_FOR_TREND];
			
//			if(i==0){
//				Double[] values_init = {-1.0, -2.0, -3.0};
//				values = Arrays.copyOf(values_init, 3);
//			}else if(i==1){
//				Double[] values_init = {1.0, 2.0, 3.0};
//				values = Arrays.copyOf(values_init, 3);
//			}else if(i==2){
//				Double[] values_init = {1.0, 2.0, -3.0};
//				values = Arrays.copyOf(values_init, 3);
//			}else {
//				Double[] values_init = {1.0, 2.0, 3.0};
//				values = Arrays.copyOf(values_init, 3);
//			}
			
			if(readValues.size() > 0){
				int index1 = 0;
				int index2 = readValues.size() - 1; 
				while(index2 > readValues.size()-1-NUMBER_OF_VALUES_FOR_TREND){
					values[index1] = readValues.get(index2).getValue();
					index1++;
					index2--;
				}
//				Log.d("values = ", Arrays.toString(values));

				boolean trendDown = false; 
				boolean trendUp = false; 
				int indexTrendDown = 0;
				int indexTrendUp = 0;
				while(indexTrendDown<values.length-1){
					if(values[indexTrendDown] > values[indexTrendDown+1])
						indexTrendDown++;
					else
						break;
				}
				if(indexTrendDown == values.length-1)
					trendDown = true;
				while(indexTrendUp<values.length-1){
					if(values[indexTrendUp] < values[indexTrendUp+1])
						indexTrendUp++;
					else
						break;
				}
				if(indexTrendUp == values.length-1)
					trendUp = true;

				if(trendDown)
					MainActivity.sensorList.get(i).setTrendArrowImageSource(R.drawable.downgreenarrow39x39);
				else if(trendUp)
					MainActivity.sensorList.get(i).setTrendArrowImageSource(R.drawable.upgreenarrow39x39);
				else
					MainActivity.sensorList.get(i).setTrendArrowImageSource(-1);
			}
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