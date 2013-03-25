package com.androidhive.jsonparsing.OAItem;

import java.util.HashMap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.androidhive.jsonparsing.OAMobileTags;
import com.androidhive.jsonparsing.R;


public class OANodeItem implements OAItem, OAMobileTags {

	HashMap<String, String> map;

	@SuppressWarnings("unchecked")
	public OANodeItem(HashMap<String, String> map) {
		// TODO: This should probably build up a logical object that has properties instead of using a String map. 
		this.map = (HashMap<String, String>) map.clone();
	}
	
	public ITEM_TYPE getItemType() {
		return ITEM_TYPE.OANODE_LIST;
	}
	
	public View onCreateItemView(Context context) {
		try {
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = vi.inflate(R.layout.oanode_list, null);

			if(v.findViewById(R.id.sNodeId) != null)
				((TextView)v.findViewById(R.id.sNodeId)).setText((String) map.get(NODE_ID));
			if(v.findViewById(R.id.sChannelNames) != null)
				((TextView)v.findViewById(R.id.sChannelNames)).setText((String) map.get(CHANNEL_NAMES));
			if(v.findViewById(R.id.sDescription) != null)
				((TextView)v.findViewById(R.id.sDescription)).setText((String) map.get(DESCRIPTION));
			
	        // Toggle the option colors
			if(v.findViewById(R.id.sEnable) != null) {
		        if(((String) map.get(ENABLED)).compareTo("1") == 0) {
		        	((TextView) v.findViewById(R.id.sEnable)).setBackgroundColor(context.getResources().getColor(R.color.LightGreen));
		        }
		        else {
		        	((TextView) v.findViewById(R.id.sEnable)).setBackgroundColor(context.getResources().getColor(R.color.LightRed));
		        }
			}
	        
			if(v.findViewById(R.id.sPublic) != null) {
		        if(((String) map.get(PUBLIC)).compareTo("1") == 0) {
		        	((TextView) v.findViewById(R.id.sPublic)).setText("Public");
		        }
		        else {
		        	((TextView) v.findViewById(R.id.sPublic)).setText("Private");
		        }
			}
			
			return v;
		}
		catch(Exception e) {
		}
		
		return null;
	}
}
