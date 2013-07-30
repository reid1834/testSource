package com.snowdream.contentprovider;

import android.net.Uri;

public class Profile {
	
	/**
	 * 表格名称
	 */
	public static final String TABLE_NAME = "profile";
	
	/**
	 * 列表一，_ID，自动增加
	 */
	public static final String COLUMN_ID = "_id";
	
	/**
	 * 列表二，名称
	 */
	public static final String COLUMN_NAME = "name";
     
     
    public static final String AUTOHORITY = "com.snowdream.provider";
    public static final int ITEM = 1;
    public static final int ITEM_ID = 2;
     
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.snowdream.profile";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.snowdream.profile";
     
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTOHORITY + "/profile");
}
