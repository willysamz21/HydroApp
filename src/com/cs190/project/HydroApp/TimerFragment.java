package com.cs190.project.HydroApp;

import com.example.android.navigationdrawerexample.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TimerFragment extends ListFragment{

	@Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    String[] values = new String[] { "Light Timer", "Water Temp Timer", "Air Temp Timer", 
	    		"Power Timer"};
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
	        android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
	  }

	  @Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
	    // do something with the data
		  Fragment fragment = new ControllerFragment();
		  FragmentManager fragmentManager = getFragmentManager();
		  fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
		  
	  }
      
}
