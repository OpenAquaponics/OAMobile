// This template example was borrowed from: http://www.androidhive.info/2012/01/android-json-parsing-tutorial/
//   Using this application as a template for building the OAMobile application

package com.androidhive.jsonparsing;


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class AndroidJSONParsingActivity extends Activity {
    String tabs[] = new String [] {"OASystem", "OANode", "OAAccounting", "OATask" };
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        Tab tabA = actionBar.newTab();
        tabA.setText("OASystem");
        tabA.setTabListener(new TabListener<MyFragmentA>(this, "OASystem", MyFragmentA.class));
        actionBar.addTab(tabA);

        Tab tabD = actionBar.newTab();
        tabD.setText("OATask");
        tabD.setTabListener(new TabListener<MyFragmentC>(this, "OATask", MyFragmentC.class));
        actionBar.addTab(tabD);
        
        Tab tabC = actionBar.newTab();
        tabC.setText("OAAcounting");
        tabC.setTabListener(new TabListener<MyFragmentB>(this, "OAAcounting", MyFragmentB.class));
        actionBar.addTab(tabC);
             
        if (savedInstanceState != null) {
            int savedIndex = savedInstanceState.getInt("SAVED_INDEX");
            getActionBar().setSelectedNavigationItem(savedIndex);
        }

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