package com.OAMobile.OAItem;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.OAMobile.OAMobileTags;
import com.OAMobile.OANodeItemChartActivity;
import com.OAMobile.R;

public class OASectionItem implements OAItem, OAMobileTags {

	private boolean bEnable = true;
	private boolean bPublic = true;
	private final String title;
	
	public OASectionItem(String title) {
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public ITEM_TYPE getItemType() {
		return ITEM_TYPE.SEPARATOR_LIST;
	}
	
	public boolean setEnable(boolean value) { return(bEnable = value); }
	public boolean getEnable() { return bEnable; }
	public boolean setPublic(boolean value) { return(bPublic = value); }
	public boolean getPublic() { return bPublic; }
	
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getItem() {
		return (HashMap<String, String>) null;
	}
	
	public View onCreateItemView(final Context context) {
		try {
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = vi.inflate(R.layout.oasection_list, null);
	
//			v.setOnClickListener(null);
//			v.setOnLongClickListener(null);
//			v.setLongClickable(false);
			
			final TextView sectionView = (TextView) v.findViewById(R.id.sSectionName);
			sectionView.setText(this.title);
			
			return v;
		}
		catch(Exception e) {
		}
		
		return null;
	}
}
