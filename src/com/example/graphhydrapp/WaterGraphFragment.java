package com.example.graphhydrapp;

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

public class WaterGraphFragment extends Fragment {
    //public static final String ARG_ITEM_NUMBER = "itmer_number";

   // public LightGraphFragment() {}
	
	 Double [] x1;//value 
	 Date [] y1;//date
	 
	public void update(){
		SensorModel waterTempSensor = MainActivity.sensorList.get(1);
		
    	ArrayList<SensorReading> waterTempReading = waterTempSensor.getData();
    	//x1 = new Double[airTempReading.size()];
    	//y1 = new Date[airTempReading.size()];
    	
    	GraphicalView mChartView = null;
		
		TimeSeries series = new TimeSeries("line");
    	for(int i = 0; i< waterTempReading.size();i++){
    		//x1[i] = airTempReading.get(i).getValue();
    		//y1[i] = airTempReading.get(i).getConvertedDate();
    		series.add(waterTempReading.get(i).getConvertedDate(), waterTempReading.get(i).getValue());	
    		//Log.v("Date", waterTempReading.get(i).getConvertedDate().toString());
    		//Log.v("Values", waterTempReading.get(i).getValue().toString());
    		
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
		mRenderer.setYTitle("Water Temperature");
		//mRenderer.setGridColor(3);
		mRenderer.setChartTitle("Water Temp Graph");
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.BLACK);
		mChartView = ChartFactory.getLineChartView( getActivity(), dataset, mRenderer);
		
		LinearLayout waterChartContainer = (LinearLayout) getView().findViewById(
  		        R.id.water_chart_container);
    	waterChartContainer.addView(mChartView);
	}
    @Override
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//    	int [] x = { 1,2,3,4,5,6,7,8,9,10};
//		int [] y = { 130, 134,145,157, 177, 69, 50,41, 33, 35};
//		GraphicalView mChartView = null;
//		TimeSeries series = new TimeSeries("line");
//		for(int i = 0; i < x.length; i++){
//			series.add(x[i], y[i]);
//		}
//		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
//		dataset.addSeries(series);
//		
//		XYSeriesRenderer renderer = new XYSeriesRenderer();
//		renderer.setPointStyle(PointStyle.CIRCLE);
//		renderer.setFillPoints(true);
//		renderer.setLineWidth(3);
//		//renderer.setColor(color.black);
		
		
//		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
//		mRenderer.addSeriesRenderer(renderer);
//		mRenderer.setXTitle("Days of Growth");
//		mRenderer.setYTitle("Water Usage");
//		mRenderer.setGridColor(3);
//		mRenderer.setChartTitle("Water Graph");
//		mRenderer.setApplyBackgroundColor(true);
//		mRenderer.setBackgroundColor(Color.BLACK);
    	
    	//mChartView = ChartFactory.getLineChartView( getActivity(), dataset, mRenderer);
    	
    	View view = (LinearLayout) inflater.inflate(R.layout.fragment_water, container, false);
  		  
//  		 LinearLayout waterChartContainer = (LinearLayout) view.findViewById(
//  		        R.id.water_chart_container);
//    	waterChartContainer.addView(mChartView);
    	
    	return view;
    }
}
