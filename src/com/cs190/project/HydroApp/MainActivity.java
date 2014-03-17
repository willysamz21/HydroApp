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

import io.socket.CustomCallback;
import io.socket.SocketIO;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.android.navigationdrawerexample.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.graphhydrapp.HumidityGraphFragment;
import com.example.graphhydrapp.PhGraphFragment;
import com.example.graphhydrapp.TempGraphFragment;
import com.example.graphhydrapp.WaterGraphFragment;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
//import com.example.graphhydrapp.Graph;
//import com.example.graphhydrapp.Graph;
//import com.example.graphhydrapp.LightGraphFragment;
//import com.example.graphhydrapp.PowerGraph;
//import android.support.v4.app.FragmentActivity;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import android.widget.TextView;


public class MainActivity extends Activity {
	
	public static String PACKAGE_NAME;
	
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    public static TextView myMsg;
    public static ImageView myMsgLog;

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
	TimerFragment wireless = new TimerFragment();
	/**
	 * 
	 * @author Willl
	 *	GCM variables
	 */
	public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    String SENDER_ID = "955343523631";
    static final String TAG = "GCM";
    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();
    Context context;
    String regid;
    /****************************************************************/
	public static class ViewHolder {
	    public TextView textView;
	    public ImageView  imageView;
	}
	
	TempGraphFragment tempFragment = new TempGraphFragment();
	WaterGraphFragment waterFrag = new WaterGraphFragment();
	HumidityGraphFragment humFrag = new HumidityGraphFragment();
	PhGraphFragment phFrag = new PhGraphFragment();

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
	        //System.out.println("getView " + position + " " + convertView + " type = " + type);
	        if (convertView == null) {
	            holder = new ViewHolder();
	            switch (type) {
	                case TYPE_ITEM:
	                    convertView = mInflater.inflate(R.layout.drawer_list_item, null);
	                    holder.textView = (TextView)convertView.findViewById(R.id.text);
	                    holder.imageView = (ImageView)convertView.findViewById(R.id.image);
	                    break;
	                case TYPE_SEPARATOR:
	                    convertView = mInflater.inflate(R.layout.drawer_list_item2, null);
	                    holder.textView = (TextView)convertView.findViewById(R.id.textSeperator);
	                    holder.imageView = (ImageView)convertView.findViewById(R.id.image2);
	                    break;
	                case TYPE_SEPARATED:
	                    convertView = mInflater.inflate(R.layout.drawer_list_item3, null);
	                    holder.textView = (TextView)convertView.findViewById(R.id.textSeparated);
	                    holder.imageView = (ImageView)convertView.findViewById(R.id.image3);
	                    break;
	            }
	            convertView.setTag(holder);
	        } else {
	            holder = (ViewHolder)convertView.getTag();
	        }
	        holder.textView.setText(mData.get(position));
            if(mData.get(position).equalsIgnoreCase("sensors"))
            	holder.imageView.setImageResource(R.drawable.sensor);
            else if(mData.get(position).equalsIgnoreCase("timers"))
            	holder.imageView.setImageResource(R.drawable.timer);
            else if(mData.get(position).contains("Graphs"))
            	holder.imageView.setImageResource(R.drawable.graphs2);
	        return convertView;
	    }

	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();

        // Check device for Play Services APK. If check succeeds, proceed with GCM registration.
        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId(context);

            if (regid.isEmpty()) {
                registerInBackground();
            }
            else
            	registerInBackground();
        } else {
            Log.i(TAG, "No valid Google Play Services APK found.");
        }
    	
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
        	initializeSocket();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
				Log.v("ERRRRRRROR", e.toString());
		}
        
        
    }
    /**
     * Registers the application with GCM servers asynchronously.
     * <p>
     * Stores the registration ID and the app versionCode in the application's
     * shared preferences.
     */
    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(SENDER_ID);
                    msg = "Device registered, registration ID=" + regid;

                    // You should send the registration ID to your server over HTTP, so it
                    // can use GCM/HTTP or CCS to send messages to your app.
                    sendRegistrationIdToBackend();

                    // For this demo: we don't need to send it because the device will send
                    // upstream messages to a server that echo back the message using the
                    // 'from' address in the message.

                    // Persist the regID - no need to register again.
                    storeRegistrationId(context, regid);
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

            
        }.execute(null, null, null);
    }
    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    /**
     * @return Application's {@code SharedPreferences}.
     */
    private SharedPreferences getGcmPreferences(Context context) {
        // This sample app persists the registration ID in shared preferences, but
        // how you store the regID in your app is up to you.
        return getSharedPreferences(MainActivity.class.getSimpleName(),
                Context.MODE_PRIVATE);
    }
    /**
     * Stores the registration ID and the app versionCode in the application's
     * {@code SharedPreferences}.
     *
     * @param context application's context.
     * @param regId registration ID
     */
    private void storeRegistrationId(Context context, String regId) {
        final SharedPreferences prefs = getGcmPreferences(context);
        int appVersion = getAppVersion(context);
        Log.i(TAG, "Saving regId on app version " + appVersion);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.commit();
    }
    /**
     * Sends the registration ID to your server over HTTP, so it can use GCM/HTTP or CCS to send
     * messages to your app. Not needed for this demo since the device sends upstream messages
     * to a server that echoes back the message using the 'from' address in the message.
     */
    private void sendRegistrationIdToBackend() {
    	try {
    	    HttpClient client = new DefaultHttpClient();  
    	    String getURL = "http://132.239.95.181:4000/reqid/"+regid;
    	    HttpGet get = new HttpGet(getURL);
    	    HttpResponse responseGet = client.execute(get);  
    	    HttpEntity resEntityGet = responseGet.getEntity();  
    	    if (resEntityGet != null) {  
    	        // do something with the response
    	        String response = EntityUtils.toString(resEntityGet);
    	        Log.i("GET RESPONSE", response);
    	    }
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
    }
    /**
     * Gets the current registration ID for application on GCM service, if there is one.
     * <p>
     * If result is empty, the app needs to register.
     *
     * @return registration ID, or empty string if there is no existing
     *         registration ID.
     */
    private String getRegistrationId(Context context) {
        final SharedPreferences prefs = getGcmPreferences(context);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }
    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
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
    	
    	c = new CustomCallback(handler,sensors, tempFragment, wireless, waterFrag, humFrag, phFrag);
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
    	//ListFragment listFrag = null;
    	JSONObject o;
        switch (position){
        case 0:
        	fragment = sensors;
        	break;
        case 1:
        	//Controller
        	fragment = wireless;
        	break;
//        case 3:
//        	//power graph
//        	fragment = new PowerGraphFragment();
//        	break;
        case 3:
        	o = new JSONObject();
         	try {
				o.put("name", "Air Temperature");
			} catch (JSONException e) {
				e.printStackTrace();
			}
        	
        	socket.emit("data",o);
        	fragment = tempFragment;
        	break;
        case 4:
        	//ph graph
        	o = new JSONObject();
        	try {
				o.put("name", "pH");
			} catch (JSONException e) {
				e.printStackTrace();
			}
        	socket.emit("data",o);
        	fragment = phFrag;
        	break;
        case 5:
        	//humidity graph
        	o = new JSONObject();
        	try {
				o.put("name", "Humidity");
			} catch (JSONException e) {
				e.printStackTrace();
			}
        	socket.emit("data",o);
        	fragment = humFrag;
        	break;
        case 6:
        	//water graph
        	o = new JSONObject();
        	try {
				o.put("name", "Water Temperature");
			} catch (JSONException e) {
				e.printStackTrace();
			}
        	
        	socket.emit("data",o);
        	fragment = waterFrag;
        	break;
        default:
        	return;
        
        	//fragment = new ControllerFragment();
        	//break;
        }


        FragmentManager fragmentManager = getFragmentManager();
        
        
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();
        
       

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
        myMsg = (TextView) findViewById(R.id.mymsg);
        myMsgLog = (ImageView) findViewById(R.id.mymsglogg);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    @Override
    public void onStart() {
      super.onStart();
      EasyTracker.getInstance(this).activityStart(this);
    }

    @Override
    public void onStop() {
      super.onStop();
      EasyTracker.getInstance(this).activityStop(this);
    }
    
}