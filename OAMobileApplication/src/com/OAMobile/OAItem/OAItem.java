package com.OAMobile.OAItem;

import java.util.HashMap;

import android.content.Context;
import android.view.View;

import com.OAMobile.OAMobileTags;

public interface OAItem extends OAMobileTags {
		
	public View onCreateItemView(Context context);
	public ITEM_TYPE getItemType();
	
	public boolean setEnable(boolean value);
	public boolean getEnable();
	
	public boolean setPublic(boolean value);
	public boolean getPublic();
	
	public HashMap<String, String> getItem();

}
