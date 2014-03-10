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

import android.R.color;
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

public class PhGraphFragment extends Fragment {
    
	Double [] x1;//value 
	 Date [] y1;//date
	public void update() {
    	
		SensorModel phSensor = MainActivity.sensorList.get(0);

    	ArrayList<SensorReading> phReading = phSensor.getData();
    	//x1 = new Double[airTempReading.size()];
    	//y1 = new Date[airTempReading.size()];
    	
    	GraphicalView mChartView = null;
		
		TimeSeries series = new TimeSeries("line");
    	for(int i = 0; i< phReading.size();i++){
    		//x1[i] = airTempReading.get(i).getValue();
    		//y1[i] = airTempReading.get(i).getConvertedDate();
    		series.add(phReading.get(i).getConvertedDate(), phReading.get(i).getValue());	
    		//Log.v("Date", phReading.get(i).getConvertedDate().toString());
    		//Log.v("Values", phReading.get(i).getValue().toString());
    		
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
		mRenderer.setYTitle("pH Balancd");
		//mRenderer.setGridColor(3);
		mRenderer.setChartTitle("pH Graph");
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.BLACK);
		mChartView = ChartFactory.getLineChartView( getActivity(), dataset, mRenderer);
		
		LinearLayout phChartContainer = (LinearLayout) getView().findViewById(
  		        R.id.ph_chart_container);
    	phChartContainer.addView(mChartView);
    }
    @Override
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//    	int [] x = { 1,2,3,4,5,6,7,8,9,10};
//		int [] y = { 30, 34,45,57, 77, 89, 100,111, 123, 145};
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
//		
//		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
//		mRenderer.addSeriesRenderer(renderer);
//		mRenderer.setXTitle("Days of Growth");
//		mRenderer.setYTitle("PH Balance");
		//mRenderer.setGridColor(3);
//		mRenderer.setChartTitle("Ph Graph");
//		mRenderer.setGridColor(color.black);
//		mRenderer.setApplyBackgroundColor(true);
//		mRenderer.setBackgroundColor(Color.BLACK);
		
    	
    	//mChartView = ChartFactory.getLineChartView( getActivity(), dataset, mRenderer);
    	
    	View view = (LinearLayout) inflater.inflate(R.layout.fragment_ph, container, false);
  		  
//  		 LinearLayout phChartContainer = (LinearLayout) view.findViewById(
//  		        R.id.ph_chart_container);
//    	phChartContainer.addView(mChartView);
    	
    	return view;
    }
}
