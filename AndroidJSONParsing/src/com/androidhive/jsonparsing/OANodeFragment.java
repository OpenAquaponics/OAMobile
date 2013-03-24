// This template example was borrowed from: http://www.androidhive.info/2012/01/android-json-parsing-tutorial/
//   Using this application as a template for building the OAMobile application

package com.androidhive.jsonparsing;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidhive.jsonparsing.OAItem.OAEntryAdapter;
import com.androidhive.jsonparsing.OAItem.OAItem;
import com.androidhive.jsonparsing.OAItem.OANodeItem;
import com.androidhive.jsonparsing.OAItem.OASectionItem;

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


public class OANodeFragment extends ListFragment implements OAMobileTags {

	// URL to OAServer
	private static String url = "http://192.168.1.24/index.php/v1/nestinator/OANodes/ER123ER/data";
	// Test OAServer return data
	private static String json_data = "{\"OAData\":[{\"mIdx\":\"999\",\"mTimeTag\":\"1359135697\",\"sData\":\"474,477,724\"},{\"mIdx\":\"998\",\"mTimeTag\":\"1359135687\",\"sData\":\"473,478,723\"},{\"mIdx\":\"997\",\"mTimeTag\":\"1359135677\",\"sData\":\"470,479,723\"},{\"mIdx\":\"996\",\"mTimeTag\":\"1359135667\",\"sData\":\"473,478,723\"},{\"mIdx\":\"995\",\"mTimeTag\":\"1359135657\",\"sData\":\"473,479,724\"},{\"mIdx\":\"994\",\"mTimeTag\":\"1359135647\",\"sData\":\"473,478,722\"},{\"mIdx\":\"993\",\"mTimeTag\":\"1359135637\",\"sData\":\"473,478,722\"},{\"mIdx\":\"992\",\"mTimeTag\":\"1359135627\",\"sData\":\"476,478,722\"},{\"mIdx\":\"991\",\"mTimeTag\":\"1359135617\",\"sData\":\"473,478,722\"},{\"mIdx\":\"990\",\"mTimeTag\":\"1359135607\",\"sData\":\"474,478,721\"},{\"mIdx\":\"989\",\"mTimeTag\":\"1359135597\",\"sData\":\"473,478,722\"},{\"mIdx\":\"988\",\"mTimeTag\":\"1359135587\",\"sData\":\"473,480,723\"},{\"mIdx\":\"987\",\"mTimeTag\":\"1359135577\",\"sData\":\"473,478,723\"},{\"mIdx\":\"986\",\"mTimeTag\":\"1359135567\",\"sData\":\"470,478,722\"},{\"mIdx\":\"985\",\"mTimeTag\":\"1359135557\",\"sData\":\"473,478,723\"},{\"mIdx\":\"984\",\"mTimeTag\":\"1359135547\",\"sData\":\"478,478,723\"},{\"mIdx\":\"983\",\"mTimeTag\":\"1359135537\",\"sData\":\"473,478,723\"},{\"mIdx\":\"982\",\"mTimeTag\":\"1359135527\",\"sData\":\"473,477,722\"},{\"mIdx\":\"981\",\"mTimeTag\":\"1359135517\",\"sData\":\"472,478,723\"},{\"mIdx\":\"980\",\"mTimeTag\":\"1359135507\",\"sData\":\"473,479,724\"},{\"mIdx\":\"979\",\"mTimeTag\":\"1359135497\",\"sData\":\"473,478,722\"},{\"mIdx\":\"978\",\"mTimeTag\":\"1359135487\",\"sData\":\"473,478,722\"},{\"mIdx\":\"977\",\"mTimeTag\":\"1359135477\",\"sData\":\"473,478,722\"},{\"mIdx\":\"976\",\"mTimeTag\":\"1359135467\",\"sData\":\"473,478,723\"},{\"mIdx\":\"975\",\"mTimeTag\":\"1359135457\",\"sData\":\"473,478,723\"},{\"mIdx\":\"974\",\"mTimeTag\":\"1359135447\",\"sData\":\"469,478,723\"},{\"mIdx\":\"973\",\"mTimeTag\":\"1359135437\",\"sData\":\"473,478,723\"},{\"mIdx\":\"972\",\"mTimeTag\":\"1359135427\",\"sData\":\"473,478,722\"},{\"mIdx\":\"971\",\"mTimeTag\":\"1359135417\",\"sData\":\"473,478,722\"},{\"mIdx\":\"970\",\"mTimeTag\":\"1359135407\",\"sData\":\"473,478,722\"},{\"mIdx\":\"969\",\"mTimeTag\":\"1359135397\",\"sData\":\"472,478,722\"},{\"mIdx\":\"968\",\"mTimeTag\":\"1359135387\",\"sData\":\"474,477,720\"}]}"; 
	private static String json_nodes = "{\"OANodes\":[{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"ER123ER\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The indoor fish tank\",\"bPublic\":\"1\",\"bEnable\":\"1\"},{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"FG5T1QR\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The outdoor balcony system\",\"bPublic\":\"1\",\"bEnable\":\"1\"},{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"UY852RF\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The commerical system\",\"bPublic\":\"1\",\"bEnable\":\"1\"}]}";

	private ArrayList<OAItem> items = new ArrayList<OAItem>();
	
//	sUsername, sSystemId, sNodeId, sChannelNames, sChannelUnits, mPollingPeriod, sDescription, bPublic, bEnable

	// contacts JSONArray
	JSONArray OANodeArray = null;
	JSONObject OANode = null;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// Hashmap for ListView
		HashMap<String, String> map = new HashMap<String, String>();

		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();
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
			
	        items.add(new OASectionItem("OASystem: R4T43DF"));
			// Loop through all of the ONNode JSON objects
			for(int i = 0; i < OANodeArray.length(); i++){
			
				// Extract the current JSON object
				OANode = OANodeArray.getJSONObject(i);
				
				// Create new HashList and add the OANode JSON object
				map.clear();
				map.put(NODEID, OANode.getString(NODEID));
				map.put(CHANNEL_NAME, OANode.getString(CHANNEL_NAME));
				map.put(DESCRIPTION, OANode.getString(DESCRIPTION));
				items.add(new OANodeItem(map));
			}
			
	        items.add(new OASectionItem("OASystem: KD42WD98"));
			// Loop through all of the ONNode JSON objects
			for(int i = 0; i < OANodeArray.length(); i++){
			
				// Extract the current JSON object
				OANode = OANodeArray.getJSONObject(i);
				
				// Create new HashList and add the OANode JSON object
				map.clear();
				map.put(NODEID, OANode.getString(NODEID));
				map.put(CHANNEL_NAME, OANode.getString(CHANNEL_NAME));
				map.put(DESCRIPTION, OANode.getString(DESCRIPTION));
				items.add(new OANodeItem(map));
			}
			
	        items.add(new OASectionItem("OASystem: ADS7523"));
			// Loop through all of the ONNode JSON objects
			for(int i = 0; i < OANodeArray.length(); i++){
			
				// Extract the current JSON object
				OANode = OANodeArray.getJSONObject(i);
				
				// Create new HashList and add the OANode JSON object
				map.clear();
				map.put(NODEID, OANode.getString(NODEID));
				map.put(CHANNEL_NAME, OANode.getString(CHANNEL_NAME));
				map.put(DESCRIPTION, OANode.getString(DESCRIPTION));
				items.add(new OANodeItem(map));
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
        
		// Build the View UI
		if(getActivity() != null) {
	        OAEntryAdapter adapter = new OAEntryAdapter(getActivity(), items);
	        setListAdapter(adapter);
		}
        
		// Create the single ListView upon item click/selection
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Create the new Intent, pass the input data, and start the Activity
				Intent in = new Intent(getActivity().getApplicationContext(), OANodeSingleItemActivity.class);
				in.putExtra(NODEID, ((TextView) view.findViewById(R.id.sNodeId)).getText().toString());
				in.putExtra(CHANNEL_NAME, ((TextView) view.findViewById(R.id.sChannelNames)).getText().toString());
				in.putExtra(DESCRIPTION, ((TextView) view.findViewById(R.id.sDescription)).getText().toString());
				startActivity(in);
			}
		});
				

	}

}

