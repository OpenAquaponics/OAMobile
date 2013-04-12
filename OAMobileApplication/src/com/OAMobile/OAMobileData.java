package com.OAMobile;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;




public class OAMobileData extends Application {
    public static final String PREFS_NAME = "OAMobilePrefsFile";
    
    JSONObject OANode = null;
    JSONObject OAAccounting = null;

    public void loadSettings() {
        
        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
    	SharedPreferences.Editor editor = settings.edit();
    	
        String sIpAddr = settings.getString("sIpAddr", "");
        if(sIpAddr.isEmpty()) {
        	// Pop up the login screen
        	editor.putString("sIpAddr", "192.168.1.24").commit();
        }
        
        String sUsername = settings.getString("sUsername", "");
        if(sUsername.isEmpty()) {
        	// Pop up the login screen
        	editor.putString("sUsername", "nestinator").commit();
        }
        
        /* Load the saved OANode data */
        String sOANodes = settings.getString("sOANodes", "");
        if(sOANodes.isEmpty()) {
        	Log.d("OAMobile", "Loading the test data for OANodes");
        	final String json_nodes = "{\"OANodes\":[{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"ER123ER\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The indoor fish tank\",\"bPublic\":\"1\",\"bEnable\":\"1\"},{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"FG5T1QR\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The outdoor balcony system\",\"bPublic\":\"1\",\"bEnable\":\"0\"},{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"UY852RF\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The commerical system\",\"bPublic\":\"0\",\"bEnable\":\"1\"},{\"sUsername\":\"nestinator\",\"sSystemId\":\"JU7E3R1\",\"sNodeId\":\"IU45WE\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The indoor fish tank\",\"bPublic\":\"1\",\"bEnable\":\"1\"},{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"LK9DF2W\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The outdoor balcony system\",\"bPublic\":\"1\",\"bEnable\":\"0\"},{\"sUsername\":\"nestinator\",\"sSystemId\":\"IK89EQ2\",\"sNodeId\":\"908TYR\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The commerical system\",\"bPublic\":\"0\",\"bEnable\":\"1\"},{\"sUsername\":\"nestinator\",\"sSystemId\":\"WE48YJU\",\"sNodeId\":\"UT6I87\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The indoor fish tank\",\"bPublic\":\"1\",\"bEnable\":\"1\"},{\"sUsername\":\"nestinator\",\"sSystemId\":\"WE48YJU\",\"sNodeId\":\"PL2E4T5\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The outdoor balcony system\",\"bPublic\":\"1\",\"bEnable\":\"0\"},{\"sUsername\":\"nestinator\",\"sSystemId\":\"WE48YJU\",\"sNodeId\":\"IDRE24R\",\"sChannelNames\":\"Water Level,Air Temp,Water Temp\",\"sChannelUnits\":\"mm,F,F\",\"mPollingPeriod\":\"10\",\"sDescription\":\"The commerical system\",\"bPublic\":\"0\",\"bEnable\":\"1\"}]}";
        	editor.putString("sOANodes", json_nodes).commit();
        	sOANodes = settings.getString("sOANodes", "");
        }
    	loadOANodes(sOANodes);

    }
    
    public void saveSettings() {
    	// Save all of the user preferences
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		//editor.putBoolean("sUsername", sUsername);
		editor.commit();
    }
    
    public void clearSettings() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
    	settings.edit().clear().commit();
    	Log.d("OAMobile", "Clearing settings");
    }
    
    
    
    
    /**************************/
    /** OANodes 			 **/
    /**************************/
    public void loadOANodes(String json_nodes) {
		try {
			OANode = new JSONObject(json_nodes);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    public JSONObject getOANodes() {
		return OANode;
    }

    
    /**************************/
    /** OAAccounting		 **/
    /**************************/
    public void loadOAAccounting(String json_nodes) {
		try {
			OAAccounting = new JSONObject(json_nodes);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    public JSONObject getOAAccounting() {
		return OAAccounting;
    }
}


