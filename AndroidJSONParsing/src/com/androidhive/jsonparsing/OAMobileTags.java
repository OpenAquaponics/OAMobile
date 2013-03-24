package com.androidhive.jsonparsing;


public interface OAMobileTags {
	// JSON node keys
	public static final String OANODES = "OANodes";
	public static final String NODEID = "sNodeId";
	public static final String CHANNEL_NAME = "sChannelNames";
	public static final String DESCRIPTION = "sDescription";
	
	public enum ITEM_TYPE {
		 SEPERATOR_LIST, OANODE_LIST, OASYSTEM_LIST, OACCOUNTING_LIST, OATASK_LIST;
	}
	
}
