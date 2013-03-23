// This template example was borrowed from: http://www.androidhive.info/2012/01/android-json-parsing-tutorial/
//   Using this application as a template for building the OAMobile application

package com.androidhive.jsonparsing;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;


public class OAMobileActivity extends ListActivity implements 
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {
    
    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;
    private int mCurrIdx = 0;

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
		//setContentView(R.layout.main);
		setContentView(R.layout.oanode_list);
		mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);

        
		// Hashmap for ListView
		ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

		// Creating JSON Parser instance
		// JSONParser jParser = new JSONParser();

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
		
		TextView tv = (TextView)findViewById(R.id.but_oanode);
        tv.setTextColor(getResources().getColor(R.color.Green));
        tv.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
		
       
        final ActionBar actionBar = getActionBar();
		actionBar.show(); // ??
        


//        LayoutInflater inflater = getLayoutInflater();
//        View header = inflater.inflate(R.layout.oanode_header, (ViewGroup) findViewById(R.id.oanode_header_layout));
//        lv.addHeaderView(header, null, false);
        
		/**
		 * Updating parsed JSON data into ListView
		 * */
		ListAdapter adapter = new SimpleAdapter(this, contactList,
				R.layout.oanode_list,
				new String[] { TAG_NODEID, TAG_CHANNEL_NAME, TAG_DESCRIPTION }, new int[] {
						R.id.sNodeId, R.id.sChannelNames, R.id.sDescription });
		setListAdapter(adapter);


		// selecting single ListView item
		ListView lv = getListView();
		
		// Launching new screen on Selecting Single ListItem
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String sNodeId = ((TextView) view.findViewById(R.id.sNodeId)).getText().toString();
				String sChannelNames = ((TextView) view.findViewById(R.id.sChannelNames)).getText().toString();
				String sDescription = ((TextView) view.findViewById(R.id.sDescription)).getText().toString();
				
				// Starting new intent
				Intent in = new Intent(getApplicationContext(), OANodeSingleItemActivity.class);
				in.putExtra(TAG_NODEID, sNodeId);
				in.putExtra(TAG_CHANNEL_NAME, sChannelNames);
				in.putExtra(TAG_DESCRIPTION, sDescription);
				startActivity(in);

			}
		});
	}

	
	
    @Override 
    public boolean onTouchEvent(MotionEvent event){ 
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    public boolean onDown(MotionEvent event) { 
        //Log.d(DEBUG_TAG,"onDown: " + event.toString()); 
        return true;
    }

    public boolean onFling(MotionEvent event1, MotionEvent event2, 
            float velocityX, float velocityY) {
        int resource[] = { R.id.but_oasystem, R.id.but_oanode, R.id.but_oaaccounting, R.id.but_oatask };
        
//        WindowManager mWindowManager =  (WindowManager) getSystemService(WINDOW_SERVICE);
//        Display mDisplay = mWindowManager.getDefaultDisplay();
//        mDisplay.getRotation()
//        Surface.ROTATION_0;
        
        Log.d(DEBUG_TAG, "onFling: " + velocityX + "   " + velocityY);
        if(velocityX > 1000.0) { /* Flick Right */
            mCurrIdx++;
        }
        else if(velocityX < -1000.0) { /* Flick Right */
            mCurrIdx--;
        }
        
        if(mCurrIdx < 0)
            mCurrIdx = resource.length-1;
        else if(mCurrIdx >= resource.length)
            mCurrIdx = 0;
        
        for(int i = 0; i < resource.length; i++) {
            if(i == mCurrIdx) {
                TextView tv = (TextView)findViewById(resource[i]);
                tv.setTextColor(getResources().getColor(R.color.Green));
                tv.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
            }
            else {
                TextView tv = (TextView)findViewById(resource[i]);
                tv.setTextColor(getResources().getColor(R.color.Grey));
                tv.setBackgroundColor(getResources().getColor(R.color.LightGrey));
            }
        }
        
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // use an inflater to populate the ActionBar with items
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity, menu);
        return true;
    }
    
    @Override
  	public boolean onOptionsItemSelected(MenuItem item){
    	// same as using a normal menu
    	switch(item.getItemId()) {
    	case R.id.item_refresh:
    		Toast.makeText(this, "Refreshing...", Toast.LENGTH_SHORT).show();
    		break;
    	case R.id.item_save:
    		Toast.makeText(this, "Saving...", Toast.LENGTH_SHORT).show();
    		break;
    	}
    	
  		return true;
  	}
    
    public void onLongPress(MotionEvent event) {
        //Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
        //Toast.makeText(getApplicationContext(), "Create a new OANode", Toast.LENGTH_SHORT).show();
    }

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
            float distanceY) {
        //Log.d(DEBUG_TAG, "onScroll: " + e1.toString()+e2.toString());
        return true;
    }

    public void onShowPress(MotionEvent event) {
        //Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    public boolean onSingleTapUp(MotionEvent event) {
        //Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    public boolean onDoubleTap(MotionEvent event) {
        //Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        
        String longText = "There has been a new OASystem created for nestinator.  The system has 2 OANodes.";
        //Intent intent = new Intent(this, NotificationReceiver.class);
        //PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        
        // Build notification
        // Actions are just fake
        NotificationCompat.Builder noti = new NotificationCompat.Builder(this)
                .setContentTitle("OAMobile")
                .setContentText("OASystem change")
                .setSmallIcon(R.drawable.accessories_calculator)
                //.setContentIntent(pIntent)
                //.addAction(R.drawable.icon, "Call", pIntent)
                //.addAction(R.drawable.icon, "More", pIntent)
                //.addAction(R.drawable.icon, "And more", pIntent).build()
                .setStyle(new NotificationCompat.InboxStyle().setBigContentTitle("OASystem change details").addLine(longText));
            
          
        NotificationManager notificationManager = 
          (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        
        notificationManager.notify(0, noti.build());
        
        return true;
    }

    public boolean onDoubleTapEvent(MotionEvent event) {
        //Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        return true;
    }

    public boolean onSingleTapConfirmed(MotionEvent event) {
        //Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());


        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("OAAccounting");
        adb.setMessage("Would you like to create a transaction?");
        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                Toast.makeText(getApplicationContext(), "Transaction created", Toast.LENGTH_SHORT).show();
            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                // Action for 'Cancel' Button
                dialog.cancel();
            }
        });
        adb.setIcon(R.drawable.computer_laptop);
        adb.show();


        return true;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
     // TODO Auto-generated method stub
     super.onSaveInstanceState(outState);
     outState.putInt("SAVED_INDEX", getActionBar().getSelectedNavigationIndex());
    }

    
    
    
    public static class TabListener<T extends Fragment> 
        implements ActionBar.TabListener{
        
           private final Activity myActivity;
           private final String myTag;
           private final Class<T> myClass;

           public TabListener(Activity activity, String tag, Class<T> cls) {
               myActivity = activity;
               myTag = tag;
               myClass = cls;
           }

     public void onTabSelected(Tab tab, FragmentTransaction ft) {

      Fragment myFragment = myActivity.getFragmentManager().findFragmentByTag(myTag);
      
      // Check if the fragment is already initialized
            if (myFragment == null) {
                // If not, instantiate and add it to the activity
                myFragment = Fragment.instantiate(myActivity, myClass.getName());
                ft.add(android.R.id.content, myFragment, myTag);
            } else {
                // If it exists, simply attach it in order to show it
                ft.attach(myFragment);
            }
      
     }

     public void onTabUnselected(Tab tab, FragmentTransaction ft) {
      
    	Fragment myFragment = myActivity.getFragmentManager().findFragmentByTag(myTag);
      
      	if (myFragment != null) {
                // Detach the fragment, because another one is being attached
                ft.detach(myFragment);
        }
      
     }

     public void onTabReselected(Tab tab, FragmentTransaction ft) {
      // TODO Auto-generated method stub
      
     }
        
   }
    
    
}