package com.cs190.project.HydroApp;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.navigationdrawerexample.R;

public class WirelessSetupFragment extends Fragment {
	
	public static final String NAMES = "com.cs190.project.HydroApp.names";
	
	EditText name1;
	EditText name2;
	EditText name3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_wirelesssetup, parent, false);
		
		name1 = (EditText) v.findViewById(R.id.name1);
		name2 = (EditText) v.findViewById(R.id.name2);
		name3 = (EditText) v.findViewById(R.id.name3);
		
		Button nextButton = (Button) v.findViewById(R.id.next4);
		nextButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				ArrayList<String> names = new ArrayList<String>();
				
				String wName1 = (name1.getText().toString().equals("") ? "Wireless 1" : name1.getText().toString());
				String wName2 = (name2.getText().toString().equals("") ? "Wireless 2" : name2.getText().toString());
				String wName3 = (name3.getText().toString().equals("") ? "Wireless 3" : name3.getText().toString());
				
				names.add(wName1);
				names.add(wName2);
				names.add(wName3);
				
				Bundle args = getArguments();
				args.putStringArrayList(NAMES, names);
				
				Fragment newFrag = new StageConfigFragment();
        		newFrag.setArguments(args);
        		
        		FragmentTransaction ft = getFragmentManager().beginTransaction();
        		
        		ft.replace(R.id.fragmentContainer, newFrag);
        		ft.addToBackStack(null);
        		
        		ft.commit();	
			}
			
		});

		return v;
	}
	
}
