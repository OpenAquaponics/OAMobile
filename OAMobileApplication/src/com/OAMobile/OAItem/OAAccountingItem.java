package com.OAMobile.OAItem;

import java.util.HashMap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.OAMobile.OAMobileTags;
import com.OAMobile.R;


public class OAAccountingItem implements OAItem, OAMobileTags {

	private boolean bEnable = true;
	private boolean bPublic = true;
	private HashMap<String, String> map;

	@SuppressWarnings("unchecked")
	public OAAccountingItem(HashMap<String, String> map) {
		// TODO: This should probably build up a logical object that has properties instead of using a String map. 
		this.map = (HashMap<String, String>) map.clone();
	}
	
	public ITEM_TYPE getItemType() {
		return ITEM_TYPE.OANODE_LIST;
	}
	
	public boolean setEnable(boolean value) { return(bEnable = value); }
	public boolean getEnable() { return bEnable; }
	public boolean setPublic(boolean value) { return(bPublic = value); }
	public boolean getPublic() { return bPublic; }
	
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getItem() {
		return (HashMap<String, String>) map.clone();
	}
	
	public View onCreateItemView(Context context) {
		try {
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = vi.inflate(R.layout.oanode_list, null);

			if(v.findViewById(R.id.sNodeId) != null)
				((TextView)v.findViewById(R.id.sNodeId)).setText((String) map.get(NODE_ID));
			if(v.findViewById(R.id.sChannelNames) != null)
				((TextView)v.findViewById(R.id.sChannelNames)).setText("temp");
			if(v.findViewById(R.id.sDescription) != null)
				((TextView)v.findViewById(R.id.sDescription)).setText((String) map.get(DESCRIPTION));
			
	        // Toggle the option colors
			if(v.findViewById(R.id.sEnable) != null) {
	        	((TextView) v.findViewById(R.id.sEnable)).setBackgroundColor(context.getResources().getColor(R.color.LightGreen));
			}
	        
			if(v.findViewById(R.id.sPublic) != null) {
	        	((TextView) v.findViewById(R.id.sPublic)).setText("Private");
			}
			
			return v;
		}
		catch(Exception e) {
		}
		
		return null;
	}
}
