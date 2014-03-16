package com.cs190.project.HydroApp;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.cs190.project.UserConfiguration.Stage;
import com.example.android.navigationdrawerexample.R;

public class NewGrowConfigActivity extends FragmentActivity {
	
	public static ArrayList<Stage> stages;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newgrowconfig);
		
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
		
		if (fragment == null) {
			fragment = new GrowTypePickerFragment();
			fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
		}
	}

}
