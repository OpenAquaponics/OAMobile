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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AndroidJSONParsingActivity extends ListActivity {

	// url to make request
	//private static String url = "http://api.androidhive.info/contacts/";
	private static String url = "http://192.168.1.24/index.php/v1/nestinator/OANodes/ER123ER/data";
		
	// JSON Node names
	private static final String TAG_OADATA = "OAData";
	private static final String TAG_IDX = "mIdx";
	private static final String TAG_TIME = "mTimeTag";
	private static final String TAG_DATA = "sData";

	// contacts JSONArray
	JSONArray OAData = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// Hashmap for ListView
		ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		JSONObject json = jParser.getJSONFromUrl(url);

		try {
			// Getting Array of Contacts
			OAData = json.getJSONArray(TAG_OADATA);

			// looping through All Contacts
			for(int i = 0; i < OAData.length(); i++){
				JSONObject c = OAData.getJSONObject(i);
				
				// Storing each json item in variable
				String sIdx = c.getString(TAG_IDX);
				String sTimeTag = c.getString(TAG_TIME);
				String sData = c.getString(TAG_DATA);
				
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				
				// adding each child node to HashMap key => value
				map.put(TAG_IDX, sIdx);
				map.put(TAG_TIME, sTimeTag);
				map.put(TAG_DATA, sData);

				// adding HashList to ArrayList
				contactList.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		/**
		 * Updating parsed JSON data into ListView
		 * */
		ListAdapter adapter = new SimpleAdapter(this, contactList,
				R.layout.list_item,
				new String[] { TAG_IDX, TAG_TIME, TAG_DATA }, new int[] {
						R.id.name, R.id.email, R.id.mobile });

		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();

		// Launching new screen on Selecting Single ListItem
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
				String cost = ((TextView) view.findViewById(R.id.email)).getText().toString();
				String description = ((TextView) view.findViewById(R.id.mobile)).getText().toString();
				
				// Starting new intent
				Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
				in.putExtra(TAG_IDX, name);
				in.putExtra(TAG_TIME, cost);
				in.putExtra(TAG_DATA, description);
				startActivity(in);

			}
		});
		
		

	}

}