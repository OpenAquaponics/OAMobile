// This template example was borrowed from: http://www.androidhive.info/2012/01/android-json-parsing-tutorial/
//   Using this application as a template for building the OAMobile application

package com.androidhive.jsonparsing;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class OANodeActivity extends ListActivity {

	// url to make request
	//private static String url = "http://api.androidhive.info/contacts/";
	private static String url = "http://192.168.1.24/index.php/v1/nestinator/OANodes/ER123ER/data";
	private static String json_data = "{\"OAData\":[{\"mIdx\":\"999\",\"mTimeTag\":\"1359135697\",\"sData\":\"474,477,724\"},{\"mIdx\":\"998\",\"mTimeTag\":\"1359135687\",\"sData\":\"473,478,723\"},{\"mIdx\":\"997\",\"mTimeTag\":\"1359135677\",\"sData\":\"470,479,723\"},{\"mIdx\":\"996\",\"mTimeTag\":\"1359135667\",\"sData\":\"473,478,723\"},{\"mIdx\":\"995\",\"mTimeTag\":\"1359135657\",\"sData\":\"473,479,724\"},{\"mIdx\":\"994\",\"mTimeTag\":\"1359135647\",\"sData\":\"473,478,722\"},{\"mIdx\":\"993\",\"mTimeTag\":\"1359135637\",\"sData\":\"473,478,722\"},{\"mIdx\":\"992\",\"mTimeTag\":\"1359135627\",\"sData\":\"476,478,722\"},{\"mIdx\":\"991\",\"mTimeTag\":\"1359135617\",\"sData\":\"473,478,722\"},{\"mIdx\":\"990\",\"mTimeTag\":\"1359135607\",\"sData\":\"474,478,721\"},{\"mIdx\":\"989\",\"mTimeTag\":\"1359135597\",\"sData\":\"473,478,722\"},{\"mIdx\":\"988\",\"mTimeTag\":\"1359135587\",\"sData\":\"473,480,723\"},{\"mIdx\":\"987\",\"mTimeTag\":\"1359135577\",\"sData\":\"473,478,723\"},{\"mIdx\":\"986\",\"mTimeTag\":\"1359135567\",\"sData\":\"470,478,722\"},{\"mIdx\":\"985\",\"mTimeTag\":\"1359135557\",\"sData\":\"473,478,723\"},{\"mIdx\":\"984\",\"mTimeTag\":\"1359135547\",\"sData\":\"478,478,723\"},{\"mIdx\":\"983\",\"mTimeTag\":\"1359135537\",\"sData\":\"473,478,723\"},{\"mIdx\":\"982\",\"mTimeTag\":\"1359135527\",\"sData\":\"473,477,722\"},{\"mIdx\":\"981\",\"mTimeTag\":\"1359135517\",\"sData\":\"472,478,723\"},{\"mIdx\":\"980\",\"mTimeTag\":\"1359135507\",\"sData\":\"473,479,724\"},{\"mIdx\":\"979\",\"mTimeTag\":\"1359135497\",\"sData\":\"473,478,722\"},{\"mIdx\":\"978\",\"mTimeTag\":\"1359135487\",\"sData\":\"473,478,722\"},{\"mIdx\":\"977\",\"mTimeTag\":\"1359135477\",\"sData\":\"473,478,722\"},{\"mIdx\":\"976\",\"mTimeTag\":\"1359135467\",\"sData\":\"473,478,723\"},{\"mIdx\":\"975\",\"mTimeTag\":\"1359135457\",\"sData\":\"473,478,723\"},{\"mIdx\":\"974\",\"mTimeTag\":\"1359135447\",\"sData\":\"469,478,723\"},{\"mIdx\":\"973\",\"mTimeTag\":\"1359135437\",\"sData\":\"473,478,723\"},{\"mIdx\":\"972\",\"mTimeTag\":\"1359135427\",\"sData\":\"473,478,722\"},{\"mIdx\":\"971\",\"mTimeTag\":\"1359135417\",\"sData\":\"473,478,722\"},{\"mIdx\":\"970\",\"mTimeTag\":\"1359135407\",\"sData\":\"473,478,722\"},{\"mIdx\":\"969\",\"mTimeTag\":\"1359135397\",\"sData\":\"472,478,722\"},{\"mIdx\":\"968\",\"mTimeTag\":\"1359135387\",\"sData\":\"474,477,720\"}]}"; 
	private static String json_nodes = "{\"OANodes\":[{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"ER123ER\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The indoor fish tank\",\"bPublic\":\"1\",\"bEnable\":\"1\"},{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"FG5T1QR\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The outdoor balcony system\",\"bPublic\":\"1\",\"bEnable\":\"1\"},{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"UY852RF\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The commerical system\",\"bPublic\":\"1\",\"bEnable\":\"1\"}]}";

		
	// JSON Node names
	private static final String TAG_OANODES = "OANodes";
	private static final String TAG_NODEID = "sNodeId";
	private static final String TAG_CHANNEL_NAME = "sChannelNames";
	private static final String TAG_DESCRIPTION = "sDescription";

	// contacts JSONArray
	JSONArray OANodes = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// Hashmap for ListView
		ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

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

				// adding HashList to ArrayList
				contactList.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		// selecting single ListView item
		ListView lv = getListView();

        LayoutInflater inflater = getLayoutInflater();
        View header = inflater.inflate(R.layout.oanode_header, (ViewGroup) findViewById(R.id.oanode_header_layout));
        lv.addHeaderView(header, null, false);
        
		/**
		 * Updating parsed JSON data into ListView
		 * */
		ListAdapter adapter = new SimpleAdapter(this, contactList,
				R.layout.oanode_list,
				new String[] { TAG_NODEID, TAG_CHANNEL_NAME, TAG_DESCRIPTION }, new int[] {
						R.id.sNodeId, R.id.sChannelNames, R.id.sDescription });

		setListAdapter(adapter);


		
		// Launching new screen on Selecting Single ListItem
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String sNodeId = ((TextView) view.findViewById(R.id.sNodeId)).getText().toString();
				String sChannelNames = ((TextView) view.findViewById(R.id.sChannelNames)).getText().toString();
				String sDescription = ((TextView) view.findViewById(R.id.sDescription)).getText().toString();
				
				// Starting new intent
				Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
				in.putExtra(TAG_NODEID, sNodeId);
				in.putExtra(TAG_CHANNEL_NAME, sChannelNames);
				in.putExtra(TAG_DESCRIPTION, sDescription);
				startActivity(in);

			}
		});
		
		

	}

}