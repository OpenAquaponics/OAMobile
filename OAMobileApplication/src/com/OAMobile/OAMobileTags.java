package com.OAMobile;

import java.util.Collections;
import java.util.HashMap;


public interface OAMobileTags {
	// JSON node keys
	public static final String OANODES = "OANodes";
	public static final String OADATA = "OAData";
	public static final String OAACCOUNTING = "OAAccounting";

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
	public static final String INDEX = "mIdx";
	public static final String TIME_TAG = "mTimeTag";
	public static final String DATA = "sData";
	public static final String COUNT = "mCnt";
	public static final String DATE = "dDate";
	public static final String AMOUNT = "fAmount";

	// JSON object definition
	public static final String[] JSON_OANodeInfo = { USERNAME, SYSTEM_ID, NODE_ID,
		CHANNEL_NAMES, CHANNEL_UNITS, POLLING_PERIOD, DESCRIPTION, PUBLIC, ENABLED };
	public static final String[] JSON_OANodeData = { INDEX, TIME_TAG, DATA };

	public static final String[] JSON_OAAccountingData = { INDEX, COUNT, USERNAME, GROUP_ID,
		SYSTEM_ID, NODE_ID, DATE, AMOUNT, DESCRIPTION };

	
	// ListView item type
	public enum ITEM_TYPE {
		 SEPARATOR_LIST, OANODE_LIST, OASYSTEM_LIST, OACCOUNTING_LIST, OATASK_LIST;
	}
	
	
	/* TODO: Think about how to do this mapping dynamically */
//	// Context menu filter options
//	public static final HashMap<String, String> hmFilterMenu = 
//		(HashMap<String, String>) Collections.unmodifiableMap(
//		new HashMap<String, String>() {
//			private static final long serialVersionUID = 1L;
//
//			{
//				put(SYSTEM_ID, "OASystem ID");
//				put(NODE_ID, "OANode ID");
//			}
//		});

	
}
