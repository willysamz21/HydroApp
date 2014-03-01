package com.cs190.project.HydroApp;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.cs190.project.UserConfiguration.JsonManip;
import com.cs190.project.UserConfiguration.Plant;
import com.example.android.navigationdrawerexample.R;

public class GrowTypePickerFragment extends Fragment {
	
	public static final String PLANT_INFO = "com.cs190.project.HydroApp.info";
	
    private ListView lv;

    ArrayAdapter<Plant> adapter;
    EditText inputSearch;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_growtypepicker, parent, false);
		
		ArrayList<Plant> plantList = JsonManip.jsonToPlantData();	
         
        lv = (ListView)v.findViewById(R.id.plantList);
        lv.setOnItemClickListener(new ListView.OnItemClickListener() {
        	
        	@Override
        	public void onItemClick(AdapterView<?> av, View view, int pos, long id) {
        		Plant info = (Plant) lv.getItemAtPosition(pos);
        		Bundle args = new Bundle();
        		args.putSerializable(PLANT_INFO, info);
        		
        		Fragment newFrag = new StageSetupFragment1();
        		newFrag.setArguments(args);
        		
        		FragmentTransaction ft = getFragmentManager().beginTransaction();
        		
        		ft.replace(R.id.fragmentContainer, newFrag);
        		ft.addToBackStack(null);
        		
        		ft.commit();
        	}

        });
        
        inputSearch = (EditText)v.findViewById(R.id.searchInput);
        
        // Adding items to listview
        adapter = new ArrayAdapter<Plant>(getActivity(), R.layout.plant_list_item, R.id.plant_name, plantList);
        lv.setAdapter(adapter);  
        
        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {
             
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                GrowTypePickerFragment.this.adapter.getFilter().filter(cs);   
            }
             
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
                 
            }
             
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub                          
            }
        });
		
		return v;
	}

}
