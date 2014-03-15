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
    	
	Double [] x1;//value 
	Date [] y1;//date
	 
	public void update(){
		SensorModel waterTempSensor = MainActivity.sensorList.get(1);	
    	ArrayList<SensorReading> waterTempReading = waterTempSensor.getData();
    	GraphicalView mChartView = null;
		
		TimeSeries series = new TimeSeries("Grow");
		TimeSeries series2 = new TimeSeries("Max");
		TimeSeries series3 = new TimeSeries("Min");
		
    	for(int i = 0; i< waterTempReading.size();i++){		
    		series.add(waterTempReading.get(i).getConvertedDate(), waterTempReading.get(i).getValue());
    		series2.add(waterTempReading.get(i).getConvertedDate(), waterTempSensor.getMax());
    		series3.add(waterTempReading.get(i).getConvertedDate(), waterTempSensor.getMin());
    	}
    
    	XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		dataset.addSeries(series2);
		dataset.addSeries(series3);
		
		//main line  
		XYSeriesRenderer renderer = new XYSeriesRenderer();	
		renderer.setPointStyle(PointStyle.CIRCLE);
		renderer.setFillPoints(true);
		renderer.setLineWidth(3);
		//max
		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		renderer2.setColor(Color.RED);
		renderer2.setLineWidth(3);
								
		//min
		XYSeriesRenderer renderer3 = new XYSeriesRenderer();
		renderer3.setColor(Color.RED);
		renderer3.setLineWidth(3);		
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.addSeriesRenderer(renderer2);
		mRenderer.addSeriesRenderer(renderer3);
		
		//mRenderer.addSeriesRenderer(renderer);
		mRenderer.setYLabelsPadding(15);
		mRenderer.setAxisTitleTextSize(24);
		mRenderer.setZoomEnabled(true);
		mRenderer.setZoomButtonsVisible(true);
		mRenderer.setXTitle("Days of Grow");
		mRenderer.setYTitle("Temp");	
		mRenderer.setChartTitle("Water Temp Graph");
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.BLACK);
		mRenderer.setYLabelsPadding(10);	
		mRenderer.setLabelsTextSize(18);
		
		mChartView = ChartFactory.getTimeChartView( getActivity(), dataset, mRenderer, "M/dd/yy  HH:mm");
		
		LinearLayout waterChartContainer = (LinearLayout) getView().findViewById(
  		        R.id.water_chart_container);
    	waterChartContainer.addView(mChartView);
	}
    @Override
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    	View view = (LinearLayout) inflater.inflate(R.layout.fragment_water, container, false);
    	
    	return view;
    }
}
