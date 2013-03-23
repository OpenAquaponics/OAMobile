package com.androidhive.jsonparsing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OANodeListActivity extends Activity {
	
	// JSON node keys
	private static final String TAG_OANODES = "OANodes";
	private static final String TAG_NODEID = "sNodeId";
	private static final String TAG_CHANNEL_NAME = "sChannelNames";
	private static final String TAG_DESCRIPTION = "sDescription";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oanode_single_item);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get JSON values from previous intent
        String name = in.getStringExtra(TAG_NODEID);
        String cost = in.getStringExtra(TAG_CHANNEL_NAME);
        String description = in.getStringExtra(TAG_DESCRIPTION);
        
        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.sNodeId);
        TextView lblCost = (TextView) findViewById(R.id.sChannelNames);
        TextView lblDesc = (TextView) findViewById(R.id.sDescription);
        
        lblName.setText(name);
        lblCost.setText(cost);
        lblDesc.setText(description);
    }
}
