package com.example.graphhydrapp;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
//import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
//import android.widget.TextView;

import com.cs190.project.HydroApp.MainActivity;
import com.cs190.project.HydroApp.SensorModel;
import com.cs190.project.HydroApp.SensorReading;
import com.example.android.navigationdrawerexample.R;

public class TempGraphFragment extends Fragment {
    //public static final String ARG_ITEM_NUMBER = "itmer_number";

   // public LightGraphFragment() {}
	
	 Double [] x1;//value 
	 Date [] y1;//date
	
	
	public void update( ) {
		
		SensorModel airTempSensor = MainActivity.sensorList.get(1);
		
    	ArrayList<SensorReading> airTempReading = airTempSensor.getData();
    	//x1 = new Double[airTempReading.size()];
    	//y1 = new Date[airTempReading.size()];
    	
    	GraphicalView mChartView = null;
		
		TimeSeries series = new TimeSeries("line");
    	for(int i = 0; i< airTempReading.size();i++){
    		//x1[i] = airTempReading.get(i).getValue();
    		//y1[i] = airTempReading.get(i).getConvertedDate();
    		series.add(airTempReading.get(i).getConvertedDate(), airTempReading.get(i).getValue());	
    		Log.v("Date", airTempReading.get(i).getConvertedDate().toString());
    		Log.v("Values", airTempReading.get(i).getValue().toString());
    		
    	}
    
    	XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		//dataset.addSeries(series2);
		
		//main line
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setPointStyle(PointStyle.CIRCLE);
		renderer.setFillPoints(true);
		renderer.setLineWidth(3);
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.addSeriesRenderer(renderer);
		//mRenderer.addSeriesRenderer(renderer2);
		//mRenderer.setYAxisMin(10.0);
		//mRenderer.setYAxisMax(30.0);
		renderer.setChartValuesTextSize(20);
		mRenderer.setAxisTitleTextSize(24);
		mRenderer.setXTitle("Days of Growth");
		mRenderer.setYTitle("Temperature");
		//mRenderer.setGridColor(3);
		mRenderer.setChartTitle("Temp Graph");
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.BLACK);
		mChartView = ChartFactory.getLineChartView( getActivity(), dataset, mRenderer);
		
		LinearLayout tempChartContainer = (LinearLayout) getView().findViewById(
  		        R.id.temp_chart_container);
    	tempChartContainer.addView(mChartView);
	}
    @Override
    
    
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
    	
    	//create method to do, like update
//    	SensorModel airTempSensor = MainActivity.sensorList.get(2);
//    	ArrayList<SensorReading> airTempReading = airTempSensor.getData();
//    	
//    	for(SensorReading reading : airTempReading){
//    		Double value  = reading.getValue();
//    		String date = reading.getDate();
//    	}
    	
    	//int [] x = { 1,2,3,4,5,6,7,8,9,10};
		//int [] y = { 30, 100,45, 110, 42, 126, 39,  90, 55, 145};
		//update();
		
//		GraphicalView mChartView = null;
//		
//		TimeSeries series = new TimeSeries("line");
//		for(int i = 0; i < x.length; i++){
//			series.add(x[i], y[i]);
//		}
		
//		int [] xBounds = { 1,2,3,4,5,6,7,8,9,10};
//		int [] yBounds = {20, 30, 40, 50, 60, 50, 40, 30, 20, 14};
//		
//		TimeSeries series2 = new TimeSeries("Upward Bound");
//		for(int j = 0; j < xBounds.length; j++){
//			series.add(xBounds[j], yBounds[j]);
//		}
		
		
//		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
//		dataset.addSeries(series);
//		//dataset.addSeries(series2);
//		
//		//main line
//		XYSeriesRenderer renderer = new XYSeriesRenderer();
//		renderer.setPointStyle(PointStyle.CIRCLE);
//		renderer.setFillPoints(true);
//		renderer.setLineWidth(3);
		
		//y up bound
		
		
//		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
//		mRenderer.addSeriesRenderer(renderer);
//		//mRenderer.addSeriesRenderer(renderer2);
//		
//		mRenderer.setXTitle("Days of Growth");
//		mRenderer.setYTitle("Temperature");
//		//mRenderer.setGridColor(3);
//		mRenderer.setChartTitle("Temp Graph");
//		mRenderer.setApplyBackgroundColor(true);
//		mRenderer.setBackgroundColor(Color.BLACK);
//		
    	
    	//mChartView = ChartFactory.getLineChartView( getActivity(), dataset, mRenderer);
    	
    	View view = (LinearLayout) inflater.inflate(R.layout.fragment_temp, container, false);
  		  
//  		LinearLayout tempChartContainer = (LinearLayout) view.findViewById(
//  		        R.id.temp_chart_container);
//    	tempChartContainer.addView(mChartView);
    	
    	return view;
    }
}
