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

}
