package com.androidhive.jsonparsing;


public interface OAMobileTags {
	// JSON node keys
	public static final String OANODES = "OANodes";

	// JSON element keys
	public static final String USERNAME = "sUsername";
	public static final String PUBLIC = "bPublic";
	public static final String ENABLED = "bEnable";
	public static final String GROUP_ID = "sGroupId";
	public static final String SYSTEM_ID = "sSystemId";
	public static final String NODE_ID = "sNodeId";
	public static final String CHANNEL_NAMES = "sChannelNames";
	public static final String CHANNEL_UNITS = "sChannelUnits";
	public static final String POLLING_PERIOD = "mPollingPeriod";
	public static final String DESCRIPTION = "sDescription";

	// JSON object definition
	public static final String[] JSON_OANodeInfo = { USERNAME, SYSTEM_ID, NODE_ID,
		CHANNEL_NAMES, CHANNEL_UNITS, POLLING_PERIOD, DESCRIPTION, PUBLIC, ENABLED };

	// ListView item type
	public enum ITEM_TYPE {
		 SEPERATOR_LIST, OANODE_LIST, OASYSTEM_LIST, OACCOUNTING_LIST, OATASK_LIST;
	}
	
}
