package com.cs190.project.HydroApp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.navigationdrawerexample.R;

public class StageSetupFragment2 extends Fragment {
	
	public static final String PLANT_INFO = "com.cs190.project.HydroApp.info";
	public static final String NUM_OF_STAGES = "com.cs190.project.HydroApp.numOfStages";
	public static final String LENGTHS = "com.cs190.project.HydroApp.lengths";
	public static final String START_DATE = "com.cs190.project.HydroApp.numOfStages";
	public static final String INDEX = "com.cs190.project.HydroApp.index";

	
	private static final String DIALOG_DATE = "date";
	private static final int REQUEST_DATE = 0;
	
	private Button mDateButton;
	private Button mNextButton;
	private ArrayList<Integer> mLengths;
	private ArrayList<EditText> mEditTexts;
	private Date mDate;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_stagesetup2, parent, false);
		
		RelativeLayout stageLengthPicker = (RelativeLayout) v.findViewById(R.id.stageLengthPicker);
		mEditTexts = new ArrayList<EditText>();
		
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
			mEditTexts.add(days);
			id++;
			rLayout.addView(days);
			
			stageLengthPicker.addView(rLayout);

		}
		
		mDate = new Date();
		mDateButton = (Button) v.findViewById(R.id.datePickerButton);
		mDateButton.setText(new SimpleDateFormat("MMM dd yyyy").format(mDate));
		mDateButton.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				DatePickerFragment dialog = DatePickerFragment.newInstance(mDate);
				dialog.setTargetFragment(StageSetupFragment2.this, REQUEST_DATE);
				dialog.show(fm, DIALOG_DATE);
			}
			
		});
		
		mNextButton= (Button) v.findViewById(R.id.next2);
		mNextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mLengths = new ArrayList<Integer>();
				
				try {
				
					for (int i = 0; i < mEditTexts.size(); i++) {
						int length = Integer.parseInt(mEditTexts.get(i).getText().toString());
						
						if (length <= 0)
							throw new Exception();
						
						mLengths.add(length);
					}
					
					Bundle args = getArguments();
					args.putIntegerArrayList(LENGTHS, mLengths);
					args.putSerializable(START_DATE, mDate);
					args.putInt(INDEX, 0);

					Fragment newFrag = new WirelessSetupFragment();
	        		newFrag.setArguments(args);
	        		
	        		FragmentTransaction ft = getFragmentManager().beginTransaction();
	        		
	        		ft.replace(R.id.fragmentContainer, newFrag);
	        		ft.addToBackStack(null);
	        		
	        		ft.commit();	
					
				} catch (Exception e) {
					Toast.makeText(getActivity(), "Please enter numbers that are greater than 0 in each box",
							Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
		//Plant plant = (Plant) getArguments().getSerializable(PLANT_INFO);
		
		return v;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK)
			return;
		if (requestCode == REQUEST_DATE) {
			Date date = (Date) data.getSerializableExtra(DatePickerFragment.START_DATE);
			mDateButton.setText(new SimpleDateFormat("MMM dd yyyy").format(date));
			mDate = date;
		}
	}

}
