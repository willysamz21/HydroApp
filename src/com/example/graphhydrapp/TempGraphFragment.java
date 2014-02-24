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

public class TempGraphFragment extends Fragment {
    //public static final String ARG_ITEM_NUMBER = "itmer_number";

   // public LightGraphFragment() {}
    @Override
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	int [] x = { 1,2,3,4,5,6,7,8,9,10};
		int [] y = { 30, 100,45, 110, 42, 126, 39,  90, 55, 145};
		GraphicalView mChartView = null;
		
		TimeSeries series = new TimeSeries("line");
		for(int i = 0; i < x.length; i++){
			series.add(x[i], y[i]);
		}
		
//		int [] xBounds = { 1,2,3,4,5,6,7,8,9,10};
//		int [] yBounds = {20, 30, 40, 50, 60, 50, 40, 30, 20, 14};
//		
//		TimeSeries series2 = new TimeSeries("Upward Bound");
//		for(int j = 0; j < xBounds.length; j++){
//			series.add(xBounds[j], yBounds[j]);
//		}
		
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		//dataset.addSeries(series2);
		
		//main line
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setPointStyle(PointStyle.CIRCLE);
		renderer.setFillPoints(true);
		renderer.setLineWidth(3);
		
		//y up bound
		
//		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
//		renderer2.setColor(Color.YELLOW);
//		renderer.setLineWidth(3);
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.addSeriesRenderer(renderer);
		//mRenderer.addSeriesRenderer(renderer2);
		
		mRenderer.setXTitle("Days of Growth");
		mRenderer.setYTitle("Temperature");
		//mRenderer.setGridColor(3);
		mRenderer.setChartTitle("Temp Graph");
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.BLACK);
		
    	
    	mChartView = ChartFactory.getLineChartView( getActivity(), dataset, mRenderer);
    	
    	View view = (LinearLayout) inflater.inflate(R.layout.fragment_temp, container, false);
  		  
  		 LinearLayout tempChartContainer = (LinearLayout) view.findViewById(
  		        R.id.temp_chart_container);
    	tempChartContainer.addView(mChartView);
    	
    	return view;
    }
}
