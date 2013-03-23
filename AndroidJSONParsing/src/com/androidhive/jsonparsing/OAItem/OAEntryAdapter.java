package com.androidhive.jsonparsing.OAItem;

import java.util.ArrayList;

import com.androidhive.jsonparsing.OAMobileTags;
import com.androidhive.jsonparsing.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class OAEntryAdapter extends ArrayAdapter<OAItem> implements OAMobileTags {

	private Context context;
	private ArrayList<OAItem> items;
	private LayoutInflater vi;

	public OAEntryAdapter(Context context,ArrayList<OAItem> items) {
		super(context,0, items);
		this.context = context;
		this.items = items;
		vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;

		final OAItem i = items.get(position);
		if (i != null) {
			if(i.listType() == LIST_TYPE.SEPERATOR){
				OASectionItem si = (OASectionItem)i;
				v = vi.inflate(R.layout.list_item_section, null);

				v.setOnClickListener(null);
				v.setOnLongClickListener(null);
				v.setLongClickable(false);
				
				final TextView sectionView = (TextView) v.findViewById(R.id.list_item_section_text);
				sectionView.setText(si.getTitle());
			}else{
				OANodeItem ei = (OANodeItem)i;
				v = vi.inflate(R.layout.list_item_entry, null);
				final TextView title = (TextView)v.findViewById(R.id.list_item_entry_title);
				final TextView subtitle = (TextView)v.findViewById(R.id.list_item_entry_summary);
				
				if (title != null) 
					title.setText(ei.title);
				if(subtitle != null)
					subtitle.setText(ei.subtitle);
			}
		}
		return v;
	}

}
