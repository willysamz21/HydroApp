package com.cs190.project.HydroApp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.navigationdrawerexample.R;

public class StageSetupFragment2 extends Fragment {
	
	public static final String PLANT_INFO = "com.cs190.project.HydroApp.info";
	public static final String NUM_OF_STAGES = "com.cs190.project.HydroApp.numOfStages";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_stagesetup2, parent, false);
		
		RelativeLayout stageLengthPicker = (RelativeLayout) v.findViewById(R.id.stageLengthPicker);
		
		
		int numOfStages = getArguments().getInt(NUM_OF_STAGES);
		int id = 100;
		
		for (int stage = 0; stage < numOfStages; stage++) {
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.MATCH_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			
			if (stage == 0) {
				//params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
				//params.addRule(RelativeLayout.BELOW, R.id.stageLengthLabel);
			}
			else
				params.addRule(RelativeLayout.BELOW, stage);
			
			RelativeLayout rLayout = new RelativeLayout(getActivity());
			rLayout.setLayoutParams(params);
			rLayout.setId(stage + 1);
			
			RelativeLayout.LayoutParams numParams = new RelativeLayout.LayoutParams(
		            RelativeLayout.LayoutParams.MATCH_PARENT,
		            RelativeLayout.LayoutParams.WRAP_CONTENT);
			TextView num = new TextView(getActivity());
			numParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			num.setLayoutParams(numParams);
			num.setId(id);
			id++;
			num.setText(Integer.toString(stage + 1));
			rLayout.addView(num);
			
			
			RelativeLayout.LayoutParams editParams = new RelativeLayout.LayoutParams(
		            RelativeLayout.LayoutParams.MATCH_PARENT,
		            RelativeLayout.LayoutParams.WRAP_CONTENT);
			EditText days = new EditText(getActivity());
			numParams.addRule(RelativeLayout.RIGHT_OF, id - 1);
			num.setLayoutParams(editParams);
			days.setId(id);
			id++;
			rLayout.addView(days);
			
			stageLengthPicker.addView(rLayout);

		}
		
		//Plant plant = (Plant) getArguments().getSerializable(PLANT_INFO);
		
		return v;
	}

}
