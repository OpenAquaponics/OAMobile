package com.OAMobile;

import java.util.Date;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.OAMobile.OAMobileTags;


public class OANodeItemChartActivity extends Activity implements OAMobileTags {

    private GraphicalView mChart;
    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    private XYSeries[] mCurrentSeries = new XYSeries[2];
    private XYSeriesRenderer[] mCurrentRenderer = new XYSeriesRenderer[2];
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oanode_item_chart);
        
        // getting intent data
        Intent in = getIntent();
        java.text.DateFormat df = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        
        // Get JSON values from previous intent
        String name = in.getStringExtra(NODE_ID);
        String data = in.getStringExtra(OADATA);
        String description = in.getStringExtra(DESCRIPTION);

        
		if (mChart == null) {
			for(int i = 0; i < mCurrentSeries.length; i++) {
		        mCurrentSeries[i] = new XYSeries(name);
		        mCurrentRenderer[i] = new XYSeriesRenderer();
		        //mCurrentRenderer[i].setPointStyle(PointStyle.TRIANGLE);

		        mDataset.addSeries(i, mCurrentSeries[i]);
		        mRenderer.addSeriesRenderer(i, mCurrentRenderer[i]);
			}
			
			mCurrentRenderer[0].setColor(getResources().getColor(R.color.Black));
			mCurrentRenderer[1].setColor(getResources().getColor(R.color.Red));
			//mCurrentRenderer[2].setColor(getResources().getColor(R.color.Blue));
			
	        mRenderer.setFitLegend(true);
	        mRenderer.setShowLabels(true);
	        mRenderer.setApplyBackgroundColor(true);
	        mRenderer.setShowGrid(true);
	        
	        mRenderer.setLabelsTextSize(30);
	        mRenderer.setLabelsColor(10);
	        mRenderer.setLegendTextSize(30);
	        mRenderer.setLegendHeight(50);
	        mRenderer.setYTitle("Raw ADC counts");
	        mRenderer.setYAxisAlign(Align.LEFT, 0);
	        mRenderer.setChartTitle("Raw data plots");
	        mRenderer.setChartTitleTextSize(30);
	        //715, 730

	        mRenderer.setXLabelsAngle((float) 45.0);
	        mRenderer.setMargins(new int[] {50, 100, 150, 50});

	        
	        mRenderer.setGridColor(getResources().getColor(R.color.DarkGrey));
	        mRenderer.setBackgroundColor(getResources().getColor(R.color.ReallyLightGrey));
	        mRenderer.setAxesColor(getResources().getColor(R.color.Black));
	        mRenderer.setMarginsColor(getResources().getColor(R.color.White));
	        mRenderer.setLabelsColor(getResources().getColor(R.color.Black));
	        
	    	// contacts JSONArray
	    	JSONArray OANodeArray = null;
	    	JSONObject OANode = null;
			JSONObject json = null;
			
			try {
				json = new JSONObject(data);
				OANodeArray = json.getJSONArray(OADATA);
	
				// Loop through all of the ONNode JSON objects
				for(int i = 0; i < OANodeArray.length(); i++){
					// Extract the current JSON object
					OANode = OANodeArray.getJSONObject(i);
					//List<Integer> val = (List<Integer>) Arrays.asList(Integer.parseInt(OANode.getString(DATA).split(",")));
					String[] strarr = OANode.getString(DATA).split(",");
					int[] intArray = new int[strarr.length];
					for(int j = 0; j < strarr.length - 1; j++) {
					    intArray[j] = Integer.parseInt(strarr[j]);
				        mCurrentSeries[j].add(OANode.getDouble(TIME_TAG), (double)intArray[j]);
				        
				        if(i == 0) {
				        	mRenderer.addXTextLabel(OANode.getDouble(TIME_TAG), df.format(new Date((long) (OANode.getDouble(TIME_TAG)*1000L))));
				        }
				        else if((i % (OANodeArray.length()/3)) == 0) {
				        	mRenderer.addXTextLabel(OANode.getDouble(TIME_TAG), df.format(new Date((long) (OANode.getDouble(TIME_TAG)*1000L))));
				        }
					}

			        //Log.d("DataLen", String.valueOf(strarr.length));
			        //Log.d("Date", df.format(new Date((long) (OANode.getDouble(TIME_TAG)*1000L))));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		
        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.sNodeId);
        TextView lblDesc = (TextView) findViewById(R.id.sDescription);
        
        lblName.setText(name);
        lblDesc.setText(description);
    }


    protected void onResume() {
        super.onResume();
        LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
        if (mChart == null) {
            mChart = ChartFactory.getCubeLineChartView(this, mDataset, mRenderer, 0.3f);
            layout.addView(mChart);
        } else {
            mChart.repaint();
        }
    }

}
