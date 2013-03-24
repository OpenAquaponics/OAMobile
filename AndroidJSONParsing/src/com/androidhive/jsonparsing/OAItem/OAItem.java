package com.androidhive.jsonparsing.OAItem;

import android.content.Context;
import android.view.View;

import com.androidhive.jsonparsing.OAMobileTags;

public interface OAItem extends OAMobileTags{
	
	public ITEM_TYPE getItemType();
	
	public View onCreateItemView(Context context);

}
