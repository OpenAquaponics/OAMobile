package com.OAMobile.OAItem;

import java.util.ArrayList;

import com.OAMobile.OAMobileTags;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class OAEntryAdapter extends ArrayAdapter<OAItem> implements OAMobileTags {

	private Context context;
	private ArrayList<OAItem> items;

	public OAEntryAdapter(Context context, ArrayList<OAItem> items) {
		super(context, 0, items);
		this.context = context;
		this.items = items;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		View v = null;

		final OAItem i = items.get(position);
		if (i != null) {
			v = i.onCreateItemView(this.context);
		}
		
		return v;
	}
	
	
	public OAItem getItem(int idx) {
		return((OAItem)items.get(idx));
	}
	

	public OAMobileTags.ITEM_TYPE getItemType(int idx) {
		return(((OAItem)items.get(idx)).getItemType());
	}
}


//private ArrayList<String> mData = new ArrayList<String>();
//private ArrayAdapter<String> mAdapter;
//
//@Override
//protected void onCreate(Bundle savedInstanceState) {
//    // ...
//    // Code that adds the strings
//    // Create the list adapter
//    mAdapter = new ArrayAdapter<String>(myActivity.this, android.R.layout.simple_list_item_1, mData);
//}
//
//private void removeItem(int index) {
//    mData.removeAt(index);
//    myActivity.this.runOnUiThread(new Runnable() {
//        public void run() {
//            mAdapter.notifyDataSetChanged();
//        }
//    }
//}