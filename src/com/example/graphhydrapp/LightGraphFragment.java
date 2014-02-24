package com.example.graphhydrapp;

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
//import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.navigationdrawerexample.R;

public class LightGraphFragment extends Fragment {
    //public static final String ARG_ITEM_NUMBER = "itmer_number";

   // public LightGraphFragment() {}
    @Override
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	int [] x = { 1,2,3,4,5,6,7,8,9,10};
		int [] y = { 50, 78,90,110 , 145, 90, 75 ,111, 30, 145};
		GraphicalView mChartView = null;
		
		TimeSeries series = new TimeSeries("line");
		for(int i = 0; i < x.length; i++){
			series.add(x[i], y[i]);
		}
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setPointStyle(PointStyle.CIRCLE);
		renderer.setFillPoints(true);
		renderer.setLineWidth(3);
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.setXTitle("Days of Growth");
		mRenderer.setYTitle("Light Usage");
		mRenderer.setGridColor(3);
		mRenderer.setChartTitle("Light Graph");
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.BLACK);
    	
    	mChartView = ChartFactory.getLineChartView( getActivity(), dataset, mRenderer);
    	
    	View view = (LinearLayout) inflater.inflate(R.layout.fragment_light, container, false);
  		  
  		 LinearLayout lightChartContainer = (LinearLayout) view.findViewById(
  		        R.id.light_chart_container);
    	lightChartContainer.addView(mChartView);
    	
    	return view;
    }
}
