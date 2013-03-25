package com.OAMobile.OAItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.OAMobile.OAMobileTags;
import com.OAMobile.R;

public class OASectionItem implements OAItem, OAMobileTags {

	private final String title;
	
	public OASectionItem(String title) {
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public ITEM_TYPE getItemType() {
		return ITEM_TYPE.SEPERATOR_LIST;
	}
	
	public View onCreateItemView(Context context) {
		try {
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = vi.inflate(R.layout.oasection_list, null);
	
			v.setOnClickListener(null);
			v.setOnLongClickListener(null);
			v.setLongClickable(false);
			
			final TextView sectionView = (TextView) v.findViewById(R.id.sSectionName);
			sectionView.setText(this.title);
			
			return v;
		}
		catch(Exception e) {
		}
		
		return null;
	}
}
