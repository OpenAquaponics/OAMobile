package com.androidhive.jsonparsing.OAItem;

import com.androidhive.jsonparsing.OAMobileTags;


public class OANodeItem implements OAItem, OAMobileTags {

	public final String title;
	public final String subtitle;

	public OANodeItem(String title, String subtitle) {
		this.title = title;
		this.subtitle = subtitle;
	}
	
	public LIST_TYPE listType() {
		return LIST_TYPE.OANODE;
	}

}
