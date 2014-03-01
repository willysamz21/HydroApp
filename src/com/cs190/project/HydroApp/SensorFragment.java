package com.cs190.project.HydroApp;

import io.socket.SocketIO;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.navigationdrawerexample.R;

public class SensorFragment extends Fragment{
      
		private TextView phText;
		private TextView airTempText;
		private TextView waterTempText;
		private TextView humidityText;
		
      
		@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        	
        	 View rootView =  inflater.inflate(R.layout.fragment_sensors, container, false);
        	 phText = (TextView)rootView.findViewById(R.id.TV1);
             airTempText = (TextView) rootView.findViewById(R.id.T2);
             waterTempText = (TextView) rootView.findViewById(R.id.T3);
             humidityText = (TextView) rootView.findViewById(R.id.T4);
                   	
        	 return rootView;
        }
       	public void update(String ph, String air, String water, String humidity ) {
			phText.setText(ph);
			airTempText.setText(air);
			waterTempText.setText(water);
			humidityText.setText(humidity);
		}

    }