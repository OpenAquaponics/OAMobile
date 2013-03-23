package com.androidhive.jsonparsing.OAItem;

import com.androidhive.jsonparsing.OAMobileTags;

public class OASectionItem implements OAItem, OAMobileTags {

	private final String title;
	
	public OASectionItem(String title) {
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public LIST_TYPE listType() {
		return LIST_TYPE.SEPERATOR;
	}

}
