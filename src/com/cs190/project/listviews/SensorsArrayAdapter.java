package com.cs190.project.listviews;

import java.util.ArrayList;

import android.R.color;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs190.project.HydroApp.MainActivity;
import com.cs190.project.HydroApp.SensorModel;
import com.example.android.navigationdrawerexample.R;

public class SensorsArrayAdapter extends ArrayAdapter<SensorModel> {
	
	private Context context;
	private ArrayList<SensorModel> mSensorList = new ArrayList<SensorModel>();
    private final char degree = '\u00B0';
	
	private SparseArray<View> view = new SparseArray<View>();

	public SensorsArrayAdapter(Context context, int resource, ArrayList<SensorModel> objects) 
	{
		super(context, resource, objects);
		this.context = context;
		this.mSensorList = objects;
		 
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		/*View rowView = inflater.inflate(R.layout.sensor_list_row, parent, false);
		
		TextView name = (TextView) rowView.findViewById(R.id.label);
		TextView reading = (TextView) rowView.findViewById(R.id.value);
		Log.v("getView", mSensorList.get(position).getName());
		name.setText(mSensorList.get(position).getName());
		reading.setText(mSensorList.get(position).getReading());
		
		return rowView;*/
		
		if(view.get(position) == null)
		{
			Log.v("getView", "null");
			LayoutInflater inflater = (LayoutInflater) this.context
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			view.put(position, inflater.inflate(R.layout.sensor_list_row, parent, false));
			
		}
		
		TextView name = (TextView) view.get(position).findViewById(R.id.label);
		TextView reading = (TextView) view.get(position).findViewById(R.id.value);
		
		String myName = mSensorList.get(position).getName();
		String myReading = mSensorList.get(position).getReading();
		
		if(myName.equalsIgnoreCase("ph")){
			reading.setTextColor(pHColor(myReading));
			//((View) view.get(position).findViewById(R.id.backView1)).setBackgroundColor(color.white);
			//((ImageView) view.get(position).findViewById(R.id.imageView1)).setImageResource(R.drawable.ph);
		}
		else if(myName.contains("Temperature")){
			myReading += degree;
		}
		else if(myName.equalsIgnoreCase("humidity")){
			myReading += "%";
		}
		
		ImageView trendArrow = (ImageView) view.get(position).findViewById(R.id.trendArrow);
		if(mSensorList.get(position).getTrendArrowImageSource() != null && mSensorList.get(position).getTrendArrowImageSource() != -1){
			trendArrow.setImageResource(mSensorList.get(position).getTrendArrowImageSource());
			trendArrow.setVisibility(View.VISIBLE);
		}
		
		name.setText(myName);
		reading.setText(myReading);
		
		
		
		return view.get(position);
		

	}


	private int pHColor(String myReading) {
		return Color.parseColor("#90c84b");
	}

}
