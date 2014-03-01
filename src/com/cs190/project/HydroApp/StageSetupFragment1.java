package com.cs190.project.HydroApp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.navigationdrawerexample.R;

public class StageSetupFragment1 extends Fragment {
	
	public static final String PLANT_INFO = "com.cs190.project.HydroApp.info";
	public static final String NUM_OF_STAGES = "com.cs190.project.HydroApp.numOfStages";
	
	TextView textView;
	private Button mPlusButton;
	private Button mMinusButton;
	private Button mNextButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_stagesetup1, parent, false);	
		
		textView = (TextView) v.findViewById(R.id.numOfStages);
		textView.setText("3");
		
		mPlusButton = (Button) v.findViewById(R.id.plusButton);
		mPlusButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CharSequence num = textView.getText();
				int stageNum = Integer.parseInt(num.toString());
				stageNum++;
				textView.setText(Integer.toString(stageNum));
			}
			
		});
		
		mMinusButton = (Button) v.findViewById(R.id.minusButton);
		mMinusButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CharSequence num = textView.getText();
				int stageNum = Integer.parseInt(num.toString());
				
				if (stageNum > 1) {
					stageNum--;
					textView.setText(Integer.toString(stageNum));
				}
			}
			
		});
		
		mNextButton = (Button) v.findViewById(R.id.next1);
		mNextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CharSequence num = textView.getText();
				int stageNum = Integer.parseInt(num.toString());
				
				Bundle args = getArguments();
        		//Bundle args = new Bundle();
        		args.putInt(NUM_OF_STAGES, stageNum);
        		
        		Fragment newFrag = new StageSetupFragment2();
        		newFrag.setArguments(args);
        		
        		FragmentTransaction ft = getFragmentManager().beginTransaction();
        		
        		ft.replace(R.id.fragmentContainer, newFrag);
        		ft.addToBackStack(null);
        		
        		ft.commit();		
			}
			
		});
		//Plant plant = (Plant) getArguments().getSerializable(PLANT_INFO);		
		
		return v;
	}
}
