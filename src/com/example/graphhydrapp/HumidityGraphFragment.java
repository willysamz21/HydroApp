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

public class HumidityGraphFragment extends Fragment {
   
	Double [] x1;//value 
	Date [] y1;//date
	 
	public void update() {
		SensorModel humiditySensor = MainActivity.sensorList.get(3);
		
    	ArrayList<SensorReading> humidityReading = humiditySensor.getData();
    	//x1 = new Double[airTempReading.size()];
    	//y1 = new Date[airTempReading.size()];
    	
    	GraphicalView mChartView = null;
		
		TimeSeries series = new TimeSeries("line");
    	for(int i = 0; i< humidityReading.size();i++){
    		//x1[i] = airTempReading.get(i).getValue();
    		//y1[i] = airTempReading.get(i).getConvertedDate();
    		series.add(humidityReading.get(i).getConvertedDate(), humidityReading.get(i).getValue());	
    		Log.v("Date", humidityReading.get(i).getConvertedDate().toString());
    		Log.v("Values", humidityReading.get(i).getValue().toString());
    		
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
		mRenderer.setYTitle("Humidity");
		//mRenderer.setGridColor(3);
		mRenderer.setChartTitle("Humidity Graph");
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.BLACK);
		mChartView = ChartFactory.getLineChartView( getActivity(), dataset, mRenderer);
		
		LinearLayout humChartContainer = (LinearLayout) getView().findViewById(
  		        R.id.humidity_chart_container);
    	humChartContainer.addView(mChartView);
	}
    @Override
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//    	int [] x = { 1,2,3,4,5,6,7,8,9,10};
//		int [] y = { 50, 78,90,110 , 145, 90, 75 ,111, 30, 145};
//		GraphicalView mChartView = null;
//		
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
//		
//		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
//		mRenderer.addSeriesRenderer(renderer);
//		mRenderer.setXTitle("Days of Growth");
//		mRenderer.setYTitle("Humidity");
//		mRenderer.setGridColor(3);
//		mRenderer.setChartTitle("Humidity Graph");
//		mRenderer.setApplyBackgroundColor(true);
//		mRenderer.setBackgroundColor(Color.BLACK);
    	
    	//mChartView = ChartFactory.getLineChartView( getActivity(), dataset, mRenderer);
    	
    	View view = (LinearLayout) inflater.inflate(R.layout.fragment_humidity, container, false);
  		  
//  		 LinearLayout lightChartContainer = (LinearLayout) view.findViewById(
//  		        R.id.humidity_chart_container);
//    	lightChartContainer.addView(mChartView);
    	
    	return view;
    }
}
