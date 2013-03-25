package com.OAMobile.OAItem;

import android.content.Context;
import android.view.View;

import com.OAMobile.OAMobileTags;

public interface OAItem extends OAMobileTags{
	
	public ITEM_TYPE getItemType();
	
	public View onCreateItemView(Context context);

}
