package com.cs190.project.HydroApp;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.cs190.project.listviews.SensorsArrayAdapter;
import com.example.android.navigationdrawerexample.R;
//This is really the fragment for the sensors, if i have time i will refactor to better naming practices, dp
public class SensorFragment extends Fragment {

	Context context;
	View rootView;
	
	public SensorFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		context = container.getContext();
		View rootView = inflater.inflate(R.layout.fragment_sensors, container, false);
		this.rootView = rootView;
		ListView sensorsList = (ListView)rootView.findViewById(R.id.sensorsList);
		String[] labels = {"pH : ", "Air Temp : ", "Humidity : ", "Water Temp : "};    
		
	    final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < labels.length; ++i) {
	      list.add(labels[i]);
	    }
	    SensorsArrayAdapter adapter = new SensorsArrayAdapter(context, android.R.layout.simple_list_item_1, list);
	    sensorsList.setAdapter(adapter);


		return rootView;
	}


	public void update(String ph, String air, String water, String humidity ) {
		Double d=0.0; 
		String airFormated = "";
		if(air != null){
			d = Double.parseDouble(air);
			d = ((d*9)/5)+32;
			airFormated = d.toString() + " F";
		}
		String waterFormated = "";
		if(water != null){
			waterFormated = water.substring(0, 5);
			waterFormated += " F";
		}		
		 
		String humidityFormated = "";
		if(humidity != null){
			humidityFormated = humidity+" %";
		}
		
		TextView t1Val = null;
		TextView t2Val= null;
		TextView t3Val = null;
		TextView t4Val = null;

		ArrayList<View> outView1 = new ArrayList<View>();
		rootView.findViewsWithText(outView1, "pH", View.FIND_VIEWS_WITH_TEXT);
		try{
			TextView t1 = (TextView) outView1.get(0);
			ViewGroup row1 = (ViewGroup) t1.getParent();
			t1Val = (TextView) row1.findViewById(R.id.value);
			t1Val.setText(ph);
		} catch(IndexOutOfBoundsException e){
			System.out.println("index out of bound for TV pH");
		}
		
		ArrayList<View> outView2 = new ArrayList<View>();
		rootView.findViewsWithText(outView2, "Air", View.FIND_VIEWS_WITH_TEXT);
		try{
			TextView t2 = (TextView) outView2.get(0);
			ViewGroup row2 = (ViewGroup) t2.getParent();
			t2Val = (TextView) row2.findViewById(R.id.value);
			t2Val.setText(airFormated);
		} catch(IndexOutOfBoundsException e){
			System.out.println("index out of bound for TV Air");
		}
		
		ArrayList<View> outView3 = new ArrayList<View>();
		rootView.findViewsWithText(outView3, "Humidity", View.FIND_VIEWS_WITH_TEXT);
		try{
			TextView t3 = (TextView) outView3.get(0);
			ViewGroup row3 = (ViewGroup) t3.getParent();
			t3Val = (TextView) row3.findViewById(R.id.value);
			t3Val.setText(humidityFormated);
		} catch(IndexOutOfBoundsException e){
			System.out.println("index out of bound for TV Humidity");
		}
		 	 
		ArrayList<View> outView4 = new ArrayList<View>();
		rootView.findViewsWithText(outView4, "Water", View.FIND_VIEWS_WITH_TEXT);
		try{
			TextView t4 = (TextView) outView4.get(0);
			ViewGroup row4 = (ViewGroup) t4.getParent();
			t4Val = (TextView) row4.findViewById(R.id.value);
			t4Val.setText(waterFormated); 
		} catch(IndexOutOfBoundsException e){
			System.out.println("index out of bound for TV Water");
		}			
  
	}        


}