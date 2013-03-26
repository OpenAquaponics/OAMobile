package com.OAMobile;

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
import android.widget.LinearLayout;
import android.widget.TextView;


import com.OAMobile.OAMobileTags;
import com.OAMobile.OAItem.OANodeItem;
import com.OAMobile.OAItem.OASectionItem;


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
        
        // Get JSON values from previous intent
        String name = in.getStringExtra(NODE_ID);
        String data = in.getStringExtra(OADATA);
        String description = in.getStringExtra(DESCRIPTION);
        
		if (mChart == null) {
	        mCurrentSeries = new XYSeries(name);
	        mDataset.addSeries(mCurrentSeries);
	        mCurrentRenderer = new XYSeriesRenderer();
	        mRenderer.addSeriesRenderer(mCurrentRenderer);
        }
        
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
		        mCurrentSeries.add(OANode.getDouble(TIME_TAG), (double)i);
		        //mCurrentSeries.add((double)i, (double)i*2);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
