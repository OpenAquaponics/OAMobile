package com.androidhive.jsonparsing.OAItem;

import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.androidhive.jsonparsing.OAMobileTags;
import com.androidhive.jsonparsing.R;


public class OANodeItem implements OAItem, OAMobileTags {

	HashMap<String, String> map;

	@SuppressWarnings("unchecked")
	public OANodeItem(HashMap<String, String> map) {
		this.map = (HashMap<String, String>) map.clone();
	}
	
	public ITEM_TYPE getItemType() {
		return ITEM_TYPE.OANODE_LIST;
	}
	
	public View onCreateItemView(Context context) {
		try {
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = vi.inflate(R.layout.oanode_list, null);

			((TextView)v.findViewById(R.id.sNodeId)).setText((String) map.get(NODEID));
			((TextView)v.findViewById(R.id.sChannelNames)).setText((String) map.get(CHANNEL_NAME));
			((TextView)v.findViewById(R.id.sDescription)).setText((String) map.get(DESCRIPTION));
			
			return v;
		}
		catch(Exception e) {
		}
		
		return null;
	}
}
