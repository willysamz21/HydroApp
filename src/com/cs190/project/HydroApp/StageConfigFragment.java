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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cs190.project.UserConfiguration.Plant;
import com.cs190.project.UserConfiguration.Stage;
import com.example.android.navigationdrawerexample.R;

public class StageConfigFragment extends Fragment {
	
	static int picker;
	
	private static final String DIALOG_TIME = "time";
	private static final int REQUEST_TIME = 0;
	
	private Date mStartTime;
	private Date mEndTime;
	private Button mStartTimeButton;
	private Button mEndTimeButton;
	private Button mAddTimerButton;
	
	RelativeLayout timerText;
	TextView phMin;
	TextView phMax;
	TextView wTempMin;
	TextView wTempMax;
	TextView aTempMin;
	TextView aTempMax;
	TextView humMin;
	TextView humMax;
	Spinner wirelessSpinner;
	
	int idx;
	int numOfTimers = 1;
	ArrayList<Integer> lengths;
	ArrayList<String> names;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_stageconfig, parent, false);
		
		Bundle args = getArguments();
		idx = args.getInt(StageSetupFragment2.INDEX);
		Plant plant = (Plant) args.getSerializable(GrowTypePickerFragment.PLANT_INFO);
		lengths = args.getIntegerArrayList(StageSetupFragment2.LENGTHS);
		names = args.getStringArrayList(WirelessSetupFragment.NAMES);
		
		if (idx == 0)
			NewGrowConfigActivity.stages = new ArrayList<Stage>();
		
		TextView title = (TextView) v.findViewById(R.id.stageTitle);
		title.setText("Stage " + Integer.toString(idx + 1));
		
		timerText = (RelativeLayout) v.findViewById(R.id.timerText);
		phMin = (TextView) v.findViewById(R.id.minBox1);
		phMin.setText(Double.toString(plant.getData().get(0)));
 		
		phMax = (TextView) v.findViewById(R.id.maxBox1);
		phMax.setText(Double.toString(plant.getData().get(1)));
		
		wTempMin = (TextView) v.findViewById(R.id.minBox2);
		wTempMax = (TextView) v.findViewById(R.id.maxBox2);
		aTempMin = (TextView) v.findViewById(R.id.minBox3);
		aTempMax = (TextView) v.findViewById(R.id.maxBox3);
		humMin = (TextView) v.findViewById(R.id.minBox4);
		humMax = (TextView) v.findViewById(R.id.maxBox4);
		
		Button phMinMinus = (Button) v.findViewById(R.id.minMinus1);
		phMinMinus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				double num = Double.parseDouble(phMin.getText().toString());
				num -= 0.1;
				//num = Math.round(num * 10) / 10;
				phMin.setText(String.format("%.1f", num));
			}
		});
		
		Button phMinPlus = (Button) v.findViewById(R.id.minPlus1);
		phMinPlus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				double min = Double.parseDouble(phMin.getText().toString());
				double max = Double.parseDouble(phMax.getText().toString());
				
				if (min < max) {
					min += 0.1;
					//min = Math.round(min * 10) / 10;
					phMin.setText(String.format("%.1f", min));
				}
			}
		});

		Button phMaxMinus = (Button) v.findViewById(R.id.maxMinus1);
		phMaxMinus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				double min = Double.parseDouble(phMin.getText().toString());
				double max = Double.parseDouble(phMax.getText().toString());
				
				if (min < max) {
					max -= 0.1;
					//max = Math.round(max * 10) / 10;
					phMax.setText(String.format("%.1f", max));
				}
			}
		});
		
		Button phMaxPlus = (Button) v.findViewById(R.id.maxPlus1);
		phMaxPlus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				double num = Double.parseDouble(phMax.getText().toString());
				num += 0.1;
				//num = Math.round(num * 10) / 10;
				phMax.setText(String.format("%.1f", num));
			}
		});
		
		Button wTempMinMinus = (Button) v.findViewById(R.id.minMinus2);
		wTempMinMinus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int num = Integer.parseInt(wTempMin.getText().toString());
				num--;
				wTempMin.setText(Integer.toString(num));
			}
		});
		
		Button wTempMinPlus = (Button) v.findViewById(R.id.minPlus2);
		wTempMinPlus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int min = Integer.parseInt(wTempMin.getText().toString());
				int max = Integer.parseInt(wTempMax.getText().toString());
				
				if (min < max) {
					min++;
					wTempMin.setText(Integer.toString(min));
				}
			}
		});
		
		Button wTempMaxMinus = (Button) v.findViewById(R.id.maxMinus2);
		wTempMaxMinus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int min = Integer.parseInt(wTempMin.getText().toString());
				int max = Integer.parseInt(wTempMax.getText().toString());
				
				if (min < max) {
					max--;
					wTempMax.setText(Integer.toString(max));
				}
			}
		});
		
		Button wTempMaxPlus = (Button) v.findViewById(R.id.maxPlus2);
		wTempMaxPlus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int num = Integer.parseInt(wTempMax.getText().toString());
				num++;
				wTempMax.setText(Integer.toString(num));
			}
		});
		
		Button aTempMinMinus = (Button) v.findViewById(R.id.minMinus3);
		aTempMinMinus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int num = Integer.parseInt(aTempMin.getText().toString());
				num--;
				aTempMin.setText(Integer.toString(num));
			}
		});
		
		Button aTempMinPlus = (Button) v.findViewById(R.id.minPlus3);
		aTempMinPlus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int min = Integer.parseInt(aTempMin.getText().toString());
				int max = Integer.parseInt(aTempMax.getText().toString());
				
				if (min < max) {
					min++;
					aTempMin.setText(Integer.toString(min));
				}
			}
		});
		
		Button aTempMaxMinus = (Button) v.findViewById(R.id.maxMinus3);
		aTempMaxMinus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int min = Integer.parseInt(aTempMin.getText().toString());
				int max = Integer.parseInt(aTempMax.getText().toString());
				
				if (min < max) {
					max--;
					aTempMax.setText(Integer.toString(max));
				}
			}
		});
		
		Button aTempMaxPlus = (Button) v.findViewById(R.id.maxPlus3);
		aTempMaxPlus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int num = Integer.parseInt(aTempMax.getText().toString());
				num++;
				aTempMax.setText(Integer.toString(num));
			}
		});
		
		Button humMinMinus = (Button) v.findViewById(R.id.minMinus4);
		humMinMinus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int num = Integer.parseInt(humMin.getText().toString());
				num--;
				humMin.setText(Integer.toString(num));
			}
		});
		
		Button humMinPlus = (Button) v.findViewById(R.id.minPlus4);
		humMinPlus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int min = Integer.parseInt(humMin.getText().toString());
				int max = Integer.parseInt(humMax.getText().toString());
				
				if (min < max) {
					min++;
					humMin.setText(Integer.toString(min));
				}
			}
		});
		
		Button humMaxMinus = (Button) v.findViewById(R.id.maxMinus4);
		humMaxMinus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int min = Integer.parseInt(humMin.getText().toString());
				int max = Integer.parseInt(humMax.getText().toString());
				
				if (min < max) {
					max--;
					humMax.setText(Integer.toString(max));
				}
			}
		});
		
		Button humMaxPlus = (Button) v.findViewById(R.id.maxPlus4);
		humMaxPlus.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int num = Integer.parseInt(humMax.getText().toString());
				num++;
				humMax.setText(Integer.toString(num));
			}
		});
		
		Button nextButton = (Button) v.findViewById(R.id.next3);
		nextButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				idx++;
				if (idx >= lengths.size()) {
					
					// This is where the json needs to be created and sent to the backend
					
					
					Intent i = new Intent(getActivity(), MainActivity.class);
					startActivity(i);
				}
				else {
					Bundle args = getArguments();
					args.putInt(StageSetupFragment2.INDEX, idx);
					
					Fragment newFrag = new StageConfigFragment();
	        		newFrag.setArguments(args);
	        		
	        		FragmentTransaction ft = getFragmentManager().beginTransaction();
	        		
	        		ft.replace(R.id.fragmentContainer, newFrag);
	        		ft.addToBackStack(null);
	        		
	        		ft.commit();	
				}
			}
		});
		
		wirelessSpinner = (Spinner) v.findViewById(R.id.wirelessSpinner);
		ArrayAdapter<String> dataAdapter =  new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, names);
		wirelessSpinner.setAdapter(dataAdapter);
		
		mStartTime = new Date();
		mStartTimeButton = (Button) v.findViewById(R.id.timePickerButton1);
		mStartTimeButton.setText(new SimpleDateFormat("h:mm a").format(mStartTime));
		mStartTimeButton.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(View v) {
				picker = 1;
				FragmentManager fm = getActivity().getSupportFragmentManager();
				TimePickerFragment dialog = TimePickerFragment.newInstance(mStartTime);
				dialog.setTargetFragment(StageConfigFragment.this, REQUEST_TIME);
				dialog.show(fm, DIALOG_TIME);
			}
			
		});
		
		mEndTime = new Date();
		mEndTimeButton = (Button) v.findViewById(R.id.timePickerButton2);
		mEndTimeButton.setText(new SimpleDateFormat("h:mm a").format(mEndTime));
		mEndTimeButton.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(View v) {
				picker = 2;
				FragmentManager fm = getActivity().getSupportFragmentManager();
				TimePickerFragment dialog = TimePickerFragment.newInstance(mEndTime);
				dialog.setTargetFragment(StageConfigFragment.this, REQUEST_TIME);
				dialog.show(fm, DIALOG_TIME);
			}
			
		});
		
		mAddTimerButton = (Button) v.findViewById(R.id.addTimer);
		mAddTimerButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				TextView timeInfo = new TextView(getActivity());
				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.MATCH_PARENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
				timeInfo.setId(numOfTimers);
				if (numOfTimers != 1) {
					params.addRule(RelativeLayout.BELOW, numOfTimers - 1);
				}
				timeInfo.setLayoutParams(params);
				String timerType = (String) wirelessSpinner.getSelectedItem();
				String start = new SimpleDateFormat("h:mm a").format(mStartTime);
				String end = new SimpleDateFormat("h:mm a").format(mEndTime);
				timeInfo.setText(timerType + " " + start + " - " + end);
				timerText.addView(timeInfo);
				numOfTimers++;
			}
		});
		
		return v;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK)
			return;
		
		if (requestCode == REQUEST_TIME) {
			Date time = (Date) data.getSerializableExtra(TimePickerFragment.START_TIME);
			
			if (picker == 1) {
				mStartTimeButton.setText(new SimpleDateFormat("h:mm a").format(time));
				mStartTime = time;
			}
			else if (picker == 2) {
				mEndTimeButton.setText(new SimpleDateFormat("h:mm a").format(time));
				mEndTime = time;
			}
		}
		
	}

}
