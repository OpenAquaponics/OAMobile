package com.androidhive.jsonparsing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import com.androidhive.jsonparsing.OAMobileTags;


public class OANodeSingleItemActivity extends Activity implements OAMobileTags {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oanode_single_item);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get JSON values from previous intent
        String name = in.getStringExtra(NODEID);
        String cost = in.getStringExtra(CHANNEL_NAME);
        String description = in.getStringExtra(DESCRIPTION);
        
        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.sNodeId);
        TextView lblCost = (TextView) findViewById(R.id.sChannelNames);
        TextView lblDesc = (TextView) findViewById(R.id.sDescription);
        
        lblName.setText(name);
        lblCost.setText(cost);
        lblDesc.setText(description);
    }
}
