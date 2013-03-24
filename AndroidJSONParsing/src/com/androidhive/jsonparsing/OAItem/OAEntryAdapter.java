package com.androidhive.jsonparsing.OAItem;

import java.util.ArrayList;

import com.androidhive.jsonparsing.OAMobileTags;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class OAEntryAdapter extends ArrayAdapter<OAItem> implements OAMobileTags {

	private Context context;
	private ArrayList<OAItem> items;
	private LayoutInflater vi;

	public OAEntryAdapter(Context context,ArrayList<OAItem> items) {
		super(context, 0, items);
		this.context = context;
		this.items = items;
		this.vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
