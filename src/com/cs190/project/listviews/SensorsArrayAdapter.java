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
    private static String phM = "";
    private static String airM = "";
    private static String waterM = "";
    private static String humidM = "";
	
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
		ImageView trendArrow = (ImageView) view.get(position).findViewById(R.id.trendArrow);
		String myName = mSensorList.get(position).getName();
		String myReading = mSensorList.get(position).getReading();
		int arrow = mSensorList.get(position).getTrend();
					
		if(myName.equalsIgnoreCase("ph")){
			if(mSensorList.get(position).isOutsideRange()){
				phM = myName + " is out of range.\n";
			}
			reading.setTextColor(pHColor(myReading));
		}
		else if(myName.contains("Temperature")){
			if(myName.contains("ater") && mSensorList.get(position).isOutsideRange()){
				waterM = myName + " is out of range.\n";
			}
			else if(mSensorList.get(position).isOutsideRange()){
				airM = myName + " is out of range.\n";
			}
			myReading += degree;
		}
		else if(myName.equalsIgnoreCase("humidity")){
			if(mSensorList.get(position).isOutsideRange()){
				humidM = myName + " is out of range.\n";
			}
			myReading += "%";
		}
			
			if(arrow == 0)
				trendArrow.setImageResource(R.drawable.downgreenarrow39x39);
			else if (arrow == 1)
				trendArrow.setImageResource(R.drawable.upgreenarrow39x39);
			else
				trendArrow.setImageResource(R.drawable.downgreenarrow39x39);
			trendArrow.setVisibility(View.VISIBLE);
		
		name.setText(myName);
		reading.setText(myReading);
		if((airM + waterM + humidM + phM).contains("is")){
				MainActivity.myMsgLog.setImageResource(R.drawable.warning);
				MainActivity.myMsg.setText(airM + waterM + humidM + phM);
		}
		else{
			MainActivity.myMsgLog.setImageResource(R.drawable.good);
			MainActivity.myMsg.setText("Everything looks good!");
		}
		MainActivity.myMsg.postInvalidate();
		
		return view.get(position);
		

	}


	private int pHColor(String myReading) {
		return Color.parseColor("#90c84b");
	}

}
