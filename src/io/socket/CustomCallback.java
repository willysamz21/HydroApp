package io.socket;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.cs190.project.HydroApp.MainActivity;
import com.cs190.project.HydroApp.SensorFragment;
import com.cs190.project.HydroApp.SensorModel;
import com.cs190.project.HydroApp.SensorReading;
import com.cs190.project.UserConfiguration.Plant;
import com.cs190.project.listviews.SensorsArrayAdapter;
import com.example.android.navigationdrawerexample.R;
import com.example.graphhydrapp.TempGraphFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Fragment;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

public class CustomCallback implements IOCallback{

	private SensorFragment sensors;
	private TempGraphFragment tempFragment;
	private Handler handler;
	private String s;
	private String air;
	private String water;
	private String humidity;
	private String ph;

	public CustomCallback(Handler h, SensorFragment sensors, TempGraphFragment tempFragment){
		this.sensors = sensors;
		handler = h;
		this.tempFragment = tempFragment;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	@Override
    public void onMessage(JSONObject json, IOAcknowledge ack) {
        try {
            Log.v("OK","Server said:" + json.toString(2));
            Log.v("MSG",json.get("name").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(String data, IOAcknowledge ack) {
        Log.v("OK","Server said: " + data);
    }

    @Override
    public void onError(SocketIOException socketIOException) {
        Log.v("OK","an Error occured");
        socketIOException.printStackTrace();
    }

    @Override
    public void onDisconnect() {
        Log.v("OK","Connection terminated.");
    }
    @Override
    public void onConnect() {
        Log.v("OK","Connection established");
    }
    @Override
    public void on(String event, IOAcknowledge ack, Object... args) {
    	Log.v("OK","Server triggered event '" + event + "'");
    	Gson g = new Gson();
    	JSONObject hm;
    	
    	if(event.equals("sensorModules")){
    		String result = args[0].toString();
    		Type sensorListModels = new TypeToken<ArrayList<SensorModel>>(){}.getType();
    		ArrayList <SensorModel> holder = g.fromJson(result, sensorListModels);
    		MainActivity.sensorList.clear();
    		MainActivity.sensorList.addAll(holder);
    		//sensors.adapter.notifyDataSetChanged();
    		handler.post(new Runnable(){
				@Override
				public void run() {
					sensors.createSensors();
				}});
    		
    		
    		Log.v("OK","Created Sensor List");
       	}
    	else if(event.equals("dataReadings")){
    		JSONObject o = (JSONObject) args[0];
    		Type sensorReadingList = new TypeToken<ArrayList<SensorReading>>(){}.getType();
    		
    		try {
				String modelName = (String) o.get("name");
				ArrayList<SensorReading> ar = g.fromJson(o.getString("data").toString()	, sensorReadingList);
				
				for(SensorModel sn : MainActivity.sensorList){
					if(sn.getName().equals(modelName)){
						sn.setData(ar);
					}	
				}
	    		
				if(modelName.equals("Water Temperature")){
					handler.post(new Runnable(){
						@Override
						public void run() {
							tempFragment.update();
							
						}
					});
					
				}
				
    		} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	else if(event.subSequence(0, 5).equals("wizard:"))
    	{
    		
    		try {
				hm = (JSONObject)args[0];
				 s = hm.getString("data");
				Log.v("DATA",s);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
	        if(event.equals("wizard:current:phSensor")){
	    		ph = new String(s);
	        }
	        else if(event.equals("wizard:current:tempSensor")){
				air = new String(s);
	        }
	        else if(event.equals("wizard:current:humiditySensor")){
				humidity = new String(s);
	        }
	        else if(event.equals("wizard:current:waterTempSensor")){
				water = new String(s);
	        }
	        handler.post(new Runnable(){
				@Override
				public void run() {
					sensors.update(ph, air, water, humidity);
					
				}
			});
    	}
    	else{
    		//put the rest of your shit here
    	}
    	
    }

}
