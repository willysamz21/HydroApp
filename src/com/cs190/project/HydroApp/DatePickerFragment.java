package com.cs190.project.HydroApp;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

import com.example.android.navigationdrawerexample.R;

public class DatePickerFragment extends DialogFragment {
	
	public static final String START_DATE = "com.cs190.project.HydroApp.startDate";
	
	private Date mDate;
	
	@Override
	public Dialog onCreateDialog(Bundle onSavedInstanceState) {
		mDate = (Date) getArguments().getSerializable(START_DATE);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(mDate);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);
		
		DatePicker dp = (DatePicker) v.findViewById(R.id.dialog_date_picker);
		dp.init(year, month, day, new OnDateChangedListener() {
			
			public void onDateChanged(DatePicker view, int year, int month, int day) {
				mDate = new GregorianCalendar(year, month, day).getTime();
				getArguments().putSerializable(START_DATE, mDate);
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
	
	public static DatePickerFragment newInstance(Date date) {
		Bundle args = new Bundle();
		args.putSerializable(START_DATE, date);
		
		DatePickerFragment frag = new DatePickerFragment();
		frag.setArguments(args);
		
		return frag;
	}
	
	private void sendResult(int resultCode) {
		if (getTargetFragment() == null)
			return;
		
		Intent i = new Intent();
		i.putExtra(START_DATE, mDate);
		
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
	}
 	
}

