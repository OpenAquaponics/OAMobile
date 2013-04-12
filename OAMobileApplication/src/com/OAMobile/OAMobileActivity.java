package com.OAMobile;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.TextView;


public class OAMobileActivity extends Activity {
	
	private OAMobileData gData = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gData = (OAMobileData) getApplication();
        
//        String[] textArray={"one","two","asdasasdf asdf dsdaa"};
//        int length=textArray.length;
//        LinearLayout layout = new LinearLayout(this);
//        setContentView(layout);
//        layout.setOrientation(LinearLayout.VERTICAL);        
//        for(int i=0;i<length;i++)
//        {
//            TextView tv=new TextView(getApplicationContext());
//            tv.setText(textArray[i]);
//            layout.addView(tv);
//        }


        
    	final ActionBar actionBar = getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    
	    Tab tabA = actionBar.newTab();
	    tabA.setText("Nodes");
	    tabA.setTabListener(new TabListener<OANodeFragment>(this, "Nodes", OANodeFragment.class));
	    actionBar.addTab(tabA);
	
	    Tab tabB = actionBar.newTab();
	    tabB.setText("Tasks");
	    tabB.setTabListener(new TabListener<OASystemFragment>(this, "Tasks", OASystemFragment.class));
	    actionBar.addTab(tabB);
	    
	    Tab tabC = actionBar.newTab();
	    tabC.setText("Accounting");
	    tabC.setTabListener(new TabListener<OAAccountingFragment>(this, "Accounting", OAAccountingFragment.class));
	    actionBar.addTab(tabC);
	         
	    if(savedInstanceState != null) {
	        int savedIndex = savedInstanceState.getInt("SAVED_INDEX");
	        getActionBar().setSelectedNavigationItem(savedIndex);
	    }
	    
	    
	    ViewPager mViewPager = new ViewPager(this);

	    mViewPager.setOnPageChangeListener(
	            new ViewPager.SimpleOnPageChangeListener() {
	                @Override
	                public void onPageSelected(int position) {
	                    // When swiping between pages, select the
	                    // corresponding tab.
	                    getActionBar().setSelectedNavigationItem(position);
	                }
	            });

    }
    
    @Override
    protected void onStop(){
    	super.onStop();
    	if(gData != null) gData.saveSettings();
    }

    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	// TODO Auto-generated method stub
    	super.onSaveInstanceState(outState);
    	outState.putInt("SAVED_INDEX", getActionBar().getSelectedNavigationIndex());
    }
    
    
    public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
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
			}
			else {
			    // If it exists, simply attach it in order to show it
			    ft.attach(myFragment);
			}
		}
	
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			Fragment myFragment = myActivity.getFragmentManager().findFragmentByTag(myTag);
			if(myFragment != null) {
				// Detach the fragment, because another one is being attached
				ft.detach(myFragment);
			}
		}
	
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
		}
    }
    
    

}


