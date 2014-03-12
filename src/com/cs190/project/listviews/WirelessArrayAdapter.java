package com.cs190.project.listviews;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs190.project.HydroApp.WirelessModel;
import com.example.android.navigationdrawerexample.R;

public class WirelessArrayAdapter extends ArrayAdapter<WirelessModel> {
	
	private Context context;
	private ArrayList<WirelessModel> mWirelessList = new ArrayList<WirelessModel>();
	private SparseArray<View> view = new SparseArray<View>();

	public WirelessArrayAdapter(Context context, int resource,
			ArrayList<WirelessModel> objects) 
	{
		super(context, resource, objects);
		this.context = context;
		this.mWirelessList = objects;
		
		Log.v("WirelessArrayAdapter", "count: " + mWirelessList.size());
		
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent)
	{	
		if(view.get(position) == null)
		{
			LayoutInflater inflater = (LayoutInflater) this.context
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			view.put(position, inflater.inflate(R.layout.wireless_row_list, parent, false));
			
		}
		
		TextView name = (TextView) view.get(position).findViewById(R.id.label);
		name.setText(mWirelessList.get(position).getName());
		
		ImageView state = (ImageView) view.get(position).findViewById(R.id.state_image);
		boolean stateIsOn = mWirelessList.get(position).getState();
		if(stateIsOn){
			state.setImageResource(R.drawable.rsz_led_green_black);
		}
		else
			state.setImageResource(R.drawable.rsz_led_red_black);

		return view.get(position);
	
		

	}

}

