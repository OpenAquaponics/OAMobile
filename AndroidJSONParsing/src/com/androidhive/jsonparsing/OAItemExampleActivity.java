package com.androidhive.jsonparsing;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.androidhive.jsonparsing.OAItem.OAItem;
import com.androidhive.jsonparsing.OAItem.OASectionItem;
import com.androidhive.jsonparsing.OAItem.OANodeItem;
import com.androidhive.jsonparsing.OAItem.OAEntryAdapter;

public class OAItemExampleActivity extends ListActivity implements OAMobileTags {
    /** Called when the activity is first created. */
	
	 ArrayList<OAItem> items = new ArrayList<OAItem>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("title", "Item 1"); map.put("subtitle", "This is item 1.1");
       
        items.add(new OASectionItem("Category 1"));
        items.add(new OANodeItem("Item 1", "This is item 1.1"));
        items.add(new OANodeItem("Item 2", "This is item 1.2"));
        items.add(new OANodeItem("Item 3", "This is item 1.3"));
        
        
        items.add(new OASectionItem("Category 2"));
        items.add(new OANodeItem("Item 4", "This is item 2.1"));
        items.add(new OANodeItem("Item 5", "This is item 2.2"));
        items.add(new OANodeItem("Item 6", "This is item 2.3"));
        items.add(new OANodeItem("Item 7", "This is item 2.4"));
        
        items.add(new OASectionItem("Category 3"));
        items.add(new OANodeItem("Item 8", "This is item 3.1"));
        items.add(new OANodeItem("Item 9", "This is item 3.2"));
        items.add(new OANodeItem("Item 10", "This is item 3.3"));
        items.add(new OANodeItem("Item 11", "This is item 3.4"));
        items.add(new OANodeItem("Item 12", "This is item 3.5"));
        
        OAEntryAdapter adapter = new OAEntryAdapter(this, items);
        
        setListAdapter(adapter);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	
    	if(items.get(position).listType() != LIST_TYPE.SEPERATOR){
    		
    		OANodeItem item = (OANodeItem)items.get(position);
    		
    		Toast.makeText(this, "You clicked " + item.title , Toast.LENGTH_SHORT).show();

    		
    	}
    	
    	super.onListItemClick(l, v, position, id);
    }
}