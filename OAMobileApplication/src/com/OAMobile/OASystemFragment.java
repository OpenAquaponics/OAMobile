package com.OAMobile;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class OASystemFragment extends ListFragment implements OAMobileTags {

	// URL to OAServer
	private static String url = "http://192.168.1.24/index.php/v1/nestinator/OANodes/ER123ER/data";
	// Test OAServer return data
	private static String json_nodes = "{\"OANodes\":[{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"ER123ER\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The indoor fish tank\",\"bPublic\":\"1\",\"bEnable\":\"1\"}]}";
	
	// contacts JSONArray
	JSONArray OANodeArray = null;
	JSONObject OANode = null;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// Hashmap for ListView
		ArrayList<HashMap<String, String>> OANodeViewList = new ArrayList<HashMap<String, String>>();

		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		//JSONObject json = jParser.getJSONFromUrl(url);
		JSONObject json = null;
		try {
			json = new JSONObject(json_nodes);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			// Extract the OANode JSON object array
			OANodeArray = json.getJSONArray(OANODES);

			// Loop through all of the ONNode JSON objects
			for(int i = 0; i < OANodeArray.length(); i++){
			
				// Extract the current JSON object
				OANode = OANodeArray.getJSONObject(i);
				
				// Create new HashList and add the OANode JSON object
				HashMap<String, String> map = new HashMap<String, String>();
				map.put(NODE_ID, OANode.getString(NODE_ID));
				map.put(CHANNEL_NAMES, OANode.getString(CHANNEL_NAMES));
				map.put(DESCRIPTION, OANode.getString(DESCRIPTION));

				// Add HashList to OANodeList ArrayList
				OANodeViewList.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
        
		// Populate the OANodeViewList UI elements
		if(getActivity() != null) {
			ListAdapter adapter = new SimpleAdapter(getActivity(), OANodeViewList, R.layout.oasystem_list,
					new String[] { NODE_ID, CHANNEL_NAMES, DESCRIPTION },
					new int[] {	R.id.sNodeId, R.id.sChannelNames, R.id.sDescription });
			setListAdapter(adapter);
		}

		// Create the single ListView upon item click/selection
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Create the new Intent, pass the input data, and start the Activity
				Intent in = new Intent(getActivity().getApplicationContext(), OANodeSingleItemActivity.class);
				in.putExtra(NODE_ID, ((TextView) view.findViewById(R.id.sNodeId)).getText().toString());
				in.putExtra(CHANNEL_NAMES, ((TextView) view.findViewById(R.id.sChannelNames)).getText().toString());
				in.putExtra(DESCRIPTION, ((TextView) view.findViewById(R.id.sDescription)).getText().toString());
				startActivity(in);
			}
		});
				

	}

}

