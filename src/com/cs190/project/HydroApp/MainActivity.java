/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cs190.project.HydroApp;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.TreeSet;

import org.json.JSONException;
import org.json.JSONObject;

import com.cs190.project.navigationdrawerexample.LoginWindow;
import com.example.android.navigationdrawerexample.R;
//import com.example.graphhydrapp.Graph;
//import com.example.graphhydrapp.Graph;
//import com.example.graphhydrapp.LightGraphFragment;
import com.example.graphhydrapp.PhGraphFragment;
import com.example.graphhydrapp.HumidityGraphFragment;
//import com.example.graphhydrapp.PowerGraph;
import com.example.graphhydrapp.PowerGraphFragment;
import com.example.graphhydrapp.TempGraphFragment;
import com.example.graphhydrapp.WaterGraphFragment;
import com.cs190.project.HydroApp.TimerFragment;

import io.socket.CustomCallback;
import io.socket.SocketIO;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.app.ListFragment;
//import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
//import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
//import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
//import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends Activity {
	
	public static String PACKAGE_NAME;
	
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mItemsTitles;
	private MyCustomAdapter mAdapter;
	
    public static SocketIO socket;
    public static CustomCallback c;
    public static ArrayList<SensorModel>sensorList = new ArrayList<SensorModel>();
    public static ArrayList<WirelessModel>wirelessList = new ArrayList<WirelessModel>();
    Handler handler = new Handler();
	boolean login = false;
	Intent loginWindow;
	SensorFragment sensors = new SensorFragment();
	TempGraphFragment tempFragment = new TempGraphFragment();
	TimerFragment wireless = new TimerFragment();

	public static class ViewHolder {
	    public TextView textView;
	}
	
	private class MyCustomAdapter extends BaseAdapter {

	    private static final int TYPE_ITEM = 0;
	    private static final int TYPE_SEPARATOR = 1;
	    private static final int TYPE_SEPARATED = 2;
	    private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 2;

	    private ArrayList<String> mData = new ArrayList<String>();
	    private LayoutInflater mInflater;

	    private TreeSet<Integer> mSeparatorsSet = new TreeSet<Integer>();
	    private TreeSet<Integer> mSeparatoredSet = new TreeSet<Integer>();

	    public MyCustomAdapter() {
	        mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }

	    public void addItem(final String item) {
	        mData.add(item);
	        notifyDataSetChanged();
	    }

	    public void addSeparatorItem(final String item) {
	        mData.add(item);
	        // save separator position
	        mSeparatorsSet.add(mData.size() - 1);
	        notifyDataSetChanged();
	    }
	    
	    public void addSeparatedItem(final String item) {
	        mData.add(item);
	        // save separator position
	        mSeparatoredSet.add(mData.size() - 1);
	        notifyDataSetChanged();
	    }

	    @Override
	    public int getItemViewType(int position) {
	        return mSeparatorsSet.contains(position) ? TYPE_SEPARATOR : (mSeparatoredSet.contains(position) ? TYPE_SEPARATED :TYPE_ITEM);
	    }

	    @Override
	    public int getViewTypeCount() {
	        return TYPE_MAX_COUNT;
	    }

	    @Override
	    public int getCount() {
	        return mData.size();
	    }

	    @Override
	    public String getItem(int position) {
	        return mData.get(position);
	    }

	    @Override
	    public long getItemId(int position) {
	        return position;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ViewHolder holder = null;
	        int type = getItemViewType(position);
	        System.out.println("getView " + position + " " + convertView + " type = " + type);
	        if (convertView == null) {
	            holder = new ViewHolder();
	            switch (type) {
	                case TYPE_ITEM:
	                    convertView = mInflater.inflate(R.layout.drawer_list_item, null);
	                    holder.textView = (TextView)convertView.findViewById(R.id.text);
	                    break;
	                case TYPE_SEPARATOR:
	                    convertView = mInflater.inflate(R.layout.drawer_list_item2, null);
	                    holder.textView = (TextView)convertView.findViewById(R.id.textSeparator);
	                    break;
	                case TYPE_SEPARATED:
	                    convertView = mInflater.inflate(R.layout.drawer_list_item3, null);
	                    holder.textView = (TextView)convertView.findViewById(R.id.textSeparated);
	                    break;
	            }
	            convertView.setTag(holder);
	        } else {
	            holder = (ViewHolder)convertView.getTag();
	        }
	        holder.textView.setText(mData.get(position));
	        return convertView;
	    }

	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    	
//        if(!login)
//        startActivity(new Intent(context, LoginWindow.class));
        PACKAGE_NAME = getApplicationContext().getPackageName();
        
        mTitle = mDrawerTitle = getTitle();
        mItemsTitles = getResources().getStringArray(R.array.items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        //mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mAdapter = new MyCustomAdapter();
        for(String item: mItemsTitles){
        	if(item.contains(":")){
        		mAdapter.addSeparatorItem(item);
        	}else if(item.contains("->")){
        		mAdapter.addSeparatedItem(item.substring(2));
        	}
        	else
        		mAdapter.addItem(item);
        }
        mDrawerList.setAdapter(mAdapter);

        //mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mItemsTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.setDrawerLockMode(1);

        if (savedInstanceState == null) {
            selectItem(0);
        }
        
        
        try {
		   // socket = new SocketIO("http://10.0.2.2:3000");
           // socket = new SocketIO("http://192.168.0.102:3000");
        	initializeSocket();
			//socket.connect("http://10.0.2.2:3000",c);
			//socket.emit("wizard:testSensors");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
				Log.v("ERRRRRRROR", e.toString());
		}
        
        

        
    }
    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
        
        // Release the Camera because we don't need it when paused
        // and other activities might need to use it.
        if (socket != null) {
        	socket.disconnect();
        	socket=null;
        }
    }
    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        // Get the Camera instance as the activity achieves full user focus
        if (socket == null) {
            try {
				initializeSocket();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Local method to handle camera init
        }
    }

    private void initializeSocket() throws MalformedURLException {

    	socket = new SocketIO();
    	
    	c = new CustomCallback(handler,sensors, tempFragment, wireless);
	    socket.connect("http://ec2-50-112-185-131.us-west-2.compute.amazonaws.com:3000",c);
	    socket.emit("initial");
		
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // If the nav drawer is open, hide action items related to the content view
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
//        return super.onPrepareOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// The action bar home/up action should open or close the drawer.
    	// ActionBarDrawerToggle will take care of this.
    	if (mDrawerToggle.onOptionsItemSelected(item)) {
    		return true;
    	}
    	return super.onOptionsItemSelected(item);
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
    	Fragment fragment = null;
    	ListFragment listFrag = null;
    	
        switch (position){
        case 0:
        	fragment = sensors;
        	break;
        case 1:
        	//Controller
        	fragment = new TimerFragment();
        	//fragment = new ControllerFragment();
        	break;
        case 3:
        	//power graph
        	fragment = new PowerGraphFragment();
        	break;
        case 4:
        	JSONObject o = new JSONObject();
        	try {
				o.put("name", "Air Temperature");
			} catch (JSONException e) {
				e.printStackTrace();
			}
        	
        	socket.emit("data",o);
        	//MainActivity.sensorList.get(0).getData().
        	fragment = new TempGraphFragment();
        	break;
        case 5:
        	//ph graph
        	fragment = new PhGraphFragment();
        	break;
        case 6:
        	//light graph
        	fragment = new HumidityGraphFragment();
        	break;
        case 7:
        	//water graph
        	fragment = new WaterGraphFragment();
        	break;
        default:
        	return;
        	//fragment = new ControllerFragment();
        	//break;
        }


        FragmentManager fragmentManager = getFragmentManager();
        
        
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        
       

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mItemsTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
 
}