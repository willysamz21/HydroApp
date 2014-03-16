package com.cs190.project.HydroApp;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.example.android.navigationdrawerexample.R;

public class TimePickerFragment extends DialogFragment {

	public static final String START_TIME = "com.cs190.project.HydroApp.startTime";
	
	private Date mTime;
	
	@Override
	public Dialog onCreateDialog(Bundle onSavedInstanceState) {
		mTime = (Date) getArguments().getSerializable(START_TIME);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(mTime);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int min = cal.get(Calendar.MINUTE);
		
		View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_time, null);
		
		TimePicker tp = (TimePicker) v.findViewById(R.id.dialog_time_picker);
		tp.setCurrentHour(hour);
		tp.setCurrentMinute(min);
		tp.setOnTimeChangedListener(new OnTimeChangedListener() {
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY,hourOfDay);
				cal.set(Calendar.MINUTE,minute);
				cal.set(Calendar.SECOND,0);
				cal.set(Calendar.MILLISECOND,0);

				mTime = cal.getTime();
				getArguments().putSerializable(START_TIME, mTime);
			}
		});
		
		return new AlertDialog.Builder(getActivity())
			.setView(v)
			.setTitle(R.string.date_picker_title)
			.setPositiveButton(
				android.R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						sendResult(Activity.RESULT_OK);
					}
				})
			.create();
	}
	
	public static TimePickerFragment newInstance(Date time) {
		Bundle args = new Bundle();
		args.putSerializable(START_TIME, time);
		
		TimePickerFragment frag = new TimePickerFragment();
		frag.setArguments(args);
		
		return frag;
	}
	
	private void sendResult(int resultCode) {
		if (getTargetFragment() == null)
			return;
		
		Intent i = new Intent();
		i.putExtra(START_TIME, mTime);
		
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
	}
	
}
