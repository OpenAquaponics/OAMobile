package com.OAMobile;

import java.util.Date;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.OAMobile.OAMobileTags;


public class OANodeSingleItemActivity extends Activity implements OAMobileTags {

    private GraphicalView mChart;
    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    private XYSeries mCurrentSeries;
    private XYSeriesRenderer mCurrentRenderer;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oanode_single_item);
        
        // getting intent data
        Intent in = getIntent();
        java.text.DateFormat df = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        
        // Get JSON values from previous intent
        String name = in.getStringExtra(NODE_ID);
        String data = in.getStringExtra(OADATA);
        String description = in.getStringExtra(DESCRIPTION);

        
		if (mChart == null) {
	        mCurrentSeries = new XYSeries(name);
	        mCurrentRenderer = new XYSeriesRenderer();
	        mDataset.addSeries(0, mCurrentSeries);
	        mRenderer.addSeriesRenderer(0, mCurrentRenderer);
	        
	        mDataset.addSeries(1, mCurrentSeries);
	        mRenderer.addSeriesRenderer(1, mCurrentRenderer);

	        mRenderer.setFitLegend(true);
	        mRenderer.setShowLabels(true);
	        mRenderer.setApplyBackgroundColor(true);
	        
	        mRenderer.setLabelsTextSize(30);
	        mRenderer.setLabelsColor(10);
	        mRenderer.setLegendTextSize(30);
	        mRenderer.setLegendHeight(50);
	        mRenderer.setYTitle("Raw ADC counts");
	        mRenderer.setChartTitle("Raw data plots");

	        mRenderer.setXLabelsAngle((float) 45.0);
	        mRenderer.setMargins(new int[] {50, 100, 150, 50});
	        mRenderer.setShowGrid(true);
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
					for(int j = 0; j < strarr.length; j++) {
					    intArray[j] = Integer.parseInt(strarr[j]);
					}
					
			        mCurrentSeries.add(OANode.getDouble(TIME_TAG), (double)intArray[0]);
			        mRenderer.addXTextLabel(OANode.getDouble(TIME_TAG), df.format(new Date((long) (OANode.getDouble(TIME_TAG)*1000L))));
			        

			        Log.d("Date", OANode.getString(TIME_TAG));
			        Log.d("Date", df.format(new Date((long) (OANode.getDouble(TIME_TAG)*1000L))));
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
