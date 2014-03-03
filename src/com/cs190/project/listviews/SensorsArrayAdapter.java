package com.cs190.project.listviews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cs190.project.HydroApp.ItemFragment;
import com.example.android.navigationdrawerexample.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SensorsArrayAdapter extends ArrayAdapter<String> {

	Context context;
	private String[] listLabels;	
//    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

    public SensorsArrayAdapter(Context context, int textViewResourceId, ArrayList<String> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		listLabels = new String[objects.size()];
		for(String s : objects){
			listLabels[objects.indexOf(s)] = s;
		}
	
//        for (int i = 0; i < objects.size(); ++i) {
//          mIdMap.put(objects.get(i), i);
//        }
	}

    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.sensor_list_row, parent, false);		
		TextView textViewLabel = (TextView) rowView.findViewById(R.id.label);
		textViewLabel.setText(listLabels[position]);
		TextView textViewValue = (TextView) rowView.findViewById(R.id.value);
		String[] values = {"3", "80 F", "58 %", "50 F"};
//		String[] values = {"", "", "", "", ""};
		textViewValue.setText(values[position]);
    
		return rowView;
    }
    
    
    
    
    
    
    
//    
//    @Override
//    public long getItemId(int position) {
//      String item = getItem(position);
//      return mIdMap.get(item);
//    }
//
//    @Override
//    public boolean hasStableIds() {
//      return true;
//    }

}
