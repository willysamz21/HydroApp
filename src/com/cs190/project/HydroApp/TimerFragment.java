package com.cs190.project.HydroApp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cs190.project.listviews.WirelessArrayAdapter;
import com.example.android.navigationdrawerexample.R;

public class TimerFragment extends ListFragment{

	public WirelessArrayAdapter adapter; 
    public View rootView;
    
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	   
	  }
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        adapter = new WirelessArrayAdapter(activity,
		        R.layout.fragment_timers, MainActivity.wirelessList);
				setListAdapter(adapter);
        
	}
	
	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	   Bundle savedInstanceState) {
	  return inflater.inflate(R.layout.fragment_timers, container, false);
	 }

	  @Override
	  public void onListItemClick(ListView l, View v, int position, long id) {
	    // do something with the data
		  Fragment fragment = new ControllerFragment();
		  FragmentManager fragmentManager = getFragmentManager();
		  fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
		  
	  }
	  
	  public void createWirelessModules(){
		  
		
		    
	  }
      
}
