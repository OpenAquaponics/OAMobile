
package com.androidhive.jsonparsing;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MyFragmentA extends Fragment {
	private static String json_nodes = "{\"OANodes\":[{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"ER123ER\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The indoor fish tank\",\"bPublic\":\"1\",\"bEnable\":\"1\"},{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"FG5T1QR\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The outdoor balcony system\",\"bPublic\":\"1\",\"bEnable\":\"1\"},{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"UY852RF\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The commerical system\",\"bPublic\":\"1\",\"bEnable\":\"1\"}]}";
	// JSON Node names
	private static final String TAG_OANODES = "OANodes";
	private static final String TAG_NODEID = "sNodeId";
	private static final String TAG_CHANNEL_NAME = "sChannelNames";
	private static final String TAG_DESCRIPTION = "sDescription";

	// contacts JSONArray
	JSONArray OANodes = null;
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
   Bundle savedInstanceState) {
	View myFragmentView = inflater.inflate(R.layout.fragment_a, container, false);
  
	// Hashmap for ListView
	ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

	// Creating JSON Parser instance
	JSONParser jParser = new JSONParser();

	// getting JSON string from URL
	JSONObject json = null;
	try {
		json = new JSONObject(json_nodes);
	} catch (JSONException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	try {
		// Getting Array of Contacts
		OANodes = json.getJSONArray(TAG_OANODES);

		// looping through All Contacts
		for(int i = 0; i < OANodes.length(); i++){
			JSONObject c = OANodes.getJSONObject(i);
			
			// Storing each json item in variable
			String sNodeId = c.getString(TAG_NODEID);
			String sChannelName = c.getString(TAG_CHANNEL_NAME);
			String sDescription = c.getString(TAG_DESCRIPTION);
			
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			
			// adding each child node to HashMap key => value
			map.put(TAG_NODEID, sNodeId);
			map.put(TAG_CHANNEL_NAME, sChannelName);
			map.put(TAG_DESCRIPTION, sDescription);

			Log.v("Android", sNodeId);
			
			// adding HashList to ArrayList
			contactList.add(map);
		}
	} catch (JSONException e) {
		e.printStackTrace();
	}
	
	//TextView tv = (TextView) inflater.inflate(R.string.sNodeId, container, false);
	//tv.setText("Attempt");
	
	// selecting single ListView item
	//ListView lv = (ListView) myFragmentView;

	/**
	 * Updating parsed JSON data into ListView
	 * */
	ListAdapter adapter = new SimpleAdapter(getActivity(), contactList,
			R.layout.oanode_list,
			new String[] { TAG_NODEID, TAG_CHANNEL_NAME, TAG_DESCRIPTION }, new int[] {
					R.id.sNodeId, R.id.sChannelNames, R.id.sDescription });

	//setListAdapter(adapter);
  
  
  return myFragmentView;
 }

}