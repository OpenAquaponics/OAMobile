package com.OAMobile;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.OAMobile.OAItem.OAEntryAdapter;
import com.OAMobile.OAItem.OAItem;
import com.OAMobile.OAItem.OANodeItem;
import com.OAMobile.OAItem.OASectionItem;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


public class OANodeFragment extends ListFragment implements OAMobileTags {

	// URL to OAServer
	private static String url = "http://192.168.1.24/index.php/v1/nestinator/OANodes/ER123ER/data";
	// Test OAServer return data
	private static String json_data = "{\"OAData\":[{\"mIdx\":\"999\",\"mTimeTag\":\"1359135697\",\"sData\":\"474,477,724\"},{\"mIdx\":\"998\",\"mTimeTag\":\"1359135687\",\"sData\":\"473,478,723\"},{\"mIdx\":\"997\",\"mTimeTag\":\"1359135677\",\"sData\":\"470,479,723\"},{\"mIdx\":\"996\",\"mTimeTag\":\"1359135667\",\"sData\":\"473,478,723\"},{\"mIdx\":\"995\",\"mTimeTag\":\"1359135657\",\"sData\":\"473,479,724\"},{\"mIdx\":\"994\",\"mTimeTag\":\"1359135647\",\"sData\":\"473,478,722\"},{\"mIdx\":\"993\",\"mTimeTag\":\"1359135637\",\"sData\":\"473,478,722\"},{\"mIdx\":\"992\",\"mTimeTag\":\"1359135627\",\"sData\":\"476,478,722\"},{\"mIdx\":\"991\",\"mTimeTag\":\"1359135617\",\"sData\":\"473,478,722\"},{\"mIdx\":\"990\",\"mTimeTag\":\"1359135607\",\"sData\":\"474,478,721\"},{\"mIdx\":\"989\",\"mTimeTag\":\"1359135597\",\"sData\":\"473,478,722\"},{\"mIdx\":\"988\",\"mTimeTag\":\"1359135587\",\"sData\":\"473,480,723\"},{\"mIdx\":\"987\",\"mTimeTag\":\"1359135577\",\"sData\":\"473,478,723\"},{\"mIdx\":\"986\",\"mTimeTag\":\"1359135567\",\"sData\":\"470,478,722\"},{\"mIdx\":\"985\",\"mTimeTag\":\"1359135557\",\"sData\":\"473,478,723\"},{\"mIdx\":\"984\",\"mTimeTag\":\"1359135547\",\"sData\":\"478,478,723\"},{\"mIdx\":\"983\",\"mTimeTag\":\"1359135537\",\"sData\":\"473,478,723\"},{\"mIdx\":\"982\",\"mTimeTag\":\"1359135527\",\"sData\":\"473,477,722\"},{\"mIdx\":\"981\",\"mTimeTag\":\"1359135517\",\"sData\":\"472,478,723\"},{\"mIdx\":\"980\",\"mTimeTag\":\"1359135507\",\"sData\":\"473,479,724\"},{\"mIdx\":\"979\",\"mTimeTag\":\"1359135497\",\"sData\":\"473,478,722\"},{\"mIdx\":\"978\",\"mTimeTag\":\"1359135487\",\"sData\":\"473,478,722\"},{\"mIdx\":\"977\",\"mTimeTag\":\"1359135477\",\"sData\":\"473,478,722\"},{\"mIdx\":\"976\",\"mTimeTag\":\"1359135467\",\"sData\":\"473,478,723\"},{\"mIdx\":\"975\",\"mTimeTag\":\"1359135457\",\"sData\":\"473,478,723\"},{\"mIdx\":\"974\",\"mTimeTag\":\"1359135447\",\"sData\":\"469,478,723\"},{\"mIdx\":\"973\",\"mTimeTag\":\"1359135437\",\"sData\":\"473,478,723\"},{\"mIdx\":\"972\",\"mTimeTag\":\"1359135427\",\"sData\":\"473,478,722\"},{\"mIdx\":\"971\",\"mTimeTag\":\"1359135417\",\"sData\":\"473,478,722\"},{\"mIdx\":\"970\",\"mTimeTag\":\"1359135407\",\"sData\":\"473,478,722\"},{\"mIdx\":\"969\",\"mTimeTag\":\"1359135397\",\"sData\":\"472,478,722\"},{\"mIdx\":\"968\",\"mTimeTag\":\"1359135387\",\"sData\":\"474,477,720\"}]}"; 
	private static String json_nodes = "{\"OANodes\":[{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"ER123ER\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The indoor fish tank\",\"bPublic\":\"1\",\"bEnable\":\"1\"},{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"FG5T1QR\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The outdoor balcony system\",\"bPublic\":\"1\",\"bEnable\":\"0\"},{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"UY852RF\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The commerical system\",\"bPublic\":\"0\",\"bEnable\":\"1\"}]}";
//	sUsername, sSystemId, sNodeId, sChannelNames, sChannelUnits, mPollingPeriod, sDescription, bPublic, bEnable

	private static String json_accounting = "{\"OAAccounting\":[{\"mIdx\":\"1\",\"mCnt\":\"0\",\"sUsername\":\"nestinator\",\"sGroupId\":\"\",\"sSystemId\":\"\",\"sNodeId\":\"ER123ER\",\"dDate\":\"0000-00-00 00:00:00\",\"fAmount\":\"67.43\",\"sDescription\":\"Lumber for the growbed\"},{\"mIdx\":\"2\",\"mCnt\":\"1\",\"sUsername\":\"nestinator\",\"sGroupId\":\"\",\"sSystemId\":\"\",\"sNodeId\":\"ER123ER\",\"dDate\":\"0000-00-00 00:00:00\",\"fAmount\":\"5.38\",\"sDescription\":\"2 goldfish\"}]}";
	//private ArrayList<OAItem> items = new ArrayList<OAItem>();
	private ArrayList<OAItem> itemsAll = null;
	private ArrayList<OAItem> items = null;
	
	// contacts JSONArray
	JSONArray OANodeArray = null;
	JSONObject OANode = null;

	@SuppressWarnings("unchecked")
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
			if(items == null) {
				items = new ArrayList<OAItem>();
				
				// This keeps getting reloaded!!!  Might need to go in another function or clear the view and rebuild it
				//  Currently this hackish way stops it from reloading the same data
				
				// TODO: The JSON items should be saved to a DB and then the UI View
				//   should query the database instead of reading the raw JSON data
				//   This let the View UI be dynamically filter based on SystemID, GroupId, etc
				
				// Extract the OANode JSON object array
				OANodeArray = json.getJSONArray(OANODES);
				
				// TODO: Should error check the OANodeArray before using it
		        items.add(new OASectionItem("OASystem: " + OANodeArray.getJSONObject(0).getString(SYSTEM_ID)));
		        
				// Loop through all of the ONNode JSON objects
				for(int i = 0; i < OANodeArray.length(); i++){
				
					// Extract the current JSON object
					OANode = OANodeArray.getJSONObject(i);
					
					// Create new HashMap, load all of the OANode JSON object information, and add the new item
					// TODO: Consider making this a class function
					map.clear();
					for(int j = 0; j < JSON_OANodeInfo.length; j++) {
						map.put(JSON_OANodeInfo[j], OANode.getString(JSON_OANodeInfo[j]));
					}
					items.add(new OANodeItem(map));
				}
				
				
				// TODO: Should error check the OANodeArray before using it
		        items.add(new OASectionItem("OASystem: 4YTR4W2"));
		        
				// Loop through all of the ONNode JSON objects
				for(int i = 0; i < OANodeArray.length()-1; i++){
				
					// Extract the current JSON object
					OANode = OANodeArray.getJSONObject(i);
					
					// Create new HashMap, load all of the OANode JSON object information, and add the new item
					// TODO: Consider making this a class function
					map.clear();
					for(int j = 0; j < JSON_OANodeInfo.length; j++) {
						map.put(JSON_OANodeInfo[j], OANode.getString(JSON_OANodeInfo[j]));
					}
					items.add(new OANodeItem(map));
				}
				
				// TODO: Should error check the OANodeArray before using it
		        items.add(new OASectionItem("OASystem: HJ8T22S"));
		        
				// Loop through all of the ONNode JSON objects
				for(int i = 0; i < OANodeArray.length(); i++){
				
					// Extract the current JSON object
					OANode = OANodeArray.getJSONObject(i);
					
					// Create new HashMap, load all of the OANode JSON object information, and add the new item
					// TODO: Consider making this a class function
					map.clear();
					for(int j = 0; j < JSON_OANodeInfo.length; j++) {
						map.put(JSON_OANodeInfo[j], OANode.getString(JSON_OANodeInfo[j]));
					}
					
					items.add(new OANodeItem(map));
					items.add(new OANodeItem(map));
					itemsAll = new ArrayList<OAItem>(items);
				}
				
				// Build the View UI
				if(getActivity() != null) {
			        OAEntryAdapter adapter = new OAEntryAdapter(getActivity(), items);
			        setListAdapter(adapter);
				}
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
        
        
		// Create the single ListView upon item click/selection
		ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch(((OAEntryAdapter)parent.getAdapter()).getItemType(position)) {
					case OANODE_LIST: 
						// Create the new Intent, pass the input data, and start the Activity
						Intent in = new Intent(getActivity().getApplicationContext(), OANodeItemChartActivity.class);
						in.putExtra(NODE_ID, ((TextView) view.findViewById(R.id.sNodeId)).getText().toString());
						in.putExtra(DESCRIPTION, ((TextView) view.findViewById(R.id.sDescription)).getText().toString());
						in.putExtra(OADATA, json_data);  // This should be optional, otherwise the activity queries the REST server
						startActivity(in);
						break;
					case SEPARATOR_LIST:
						//((OAEntryAdapter)parent.getAdapter()).getItem(position).setEnable(false);
						//items.remove(3);
						if(itemsAll.size() == items.size()) {
							for(int i = position + 1; i < items.size() && (((OAEntryAdapter)parent.getAdapter()).getItemType(i) != OAMobileTags.ITEM_TYPE.SEPARATOR_LIST); ) {
								items.remove(i);
							}
	
							Toast toast = Toast.makeText(getActivity(), "Compressing the list", Toast.LENGTH_SHORT);
							toast.show();
						}
						else {
							//items.clear();
							//items = (ArrayList<OAItem>) itemsAll.clone();
							items = new ArrayList<OAItem>(itemsAll);
					        OAEntryAdapter adapter = new OAEntryAdapter(getActivity(), items);
					        setListAdapter(adapter);
					        
							Toast toast = Toast.makeText(getActivity(), "Expanding the list", Toast.LENGTH_SHORT);
							toast.show();
						}
						((OAEntryAdapter)parent.getAdapter()).notifyDataSetChanged();
						break;
				}
			}
		});
		
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		        AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
		        adb.setTitle("Modify OANode");
		        adb.setMessage("Would you like modify the node settings?");
		        adb.setNegativeButton("Cancel", new AlertDialog.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		            	Toast toast = Toast.makeText(getActivity(), "OANode not modified", Toast.LENGTH_SHORT);
		            	toast.show();
		            }});
		        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
		        	// TODO : This should open up a modification menu
		            public void onClick(DialogInterface dialog, int which) {
		            	Toast toast = Toast.makeText(getActivity(), "Updated OANode", Toast.LENGTH_SHORT);
		            	toast.show();
		            }});
		        adb.show();
				return false;
			}
		});

		
	}

}

