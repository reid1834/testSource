package com.wirlessqa.content.provider;

import android.net.Uri;

/**
 * Profile类用于存放各种常量
 * 
 * @author www.wirelessqa.com 2013-2-26 下午11:01:46
 */
public class Profile {

    public static final String TABLE_NAME        = "profile";                                        // 表格名称

    public static final String COLUMN_ID         = "_id";                                            // 列表一,_ID,自动增加

    public static final String COLUMN_NAME       = "name";                                           // 列表二，名称

    public static final String AUTOHORITY        = "com.wirlessqa.content.provider";
    public static final int    ITEM              = 1;
    public static final int    ITEM_ID           = 2;

    // 如果操作的数据属于集合类型，那么MIME类型字符串应该以vnd.android.cursor.dir/开头，
    public static final String CONTENT_TYPE      = "vnd.android.cursor.dir/profile";
    // 如果要操作的数据属于非集合类型数据，那么MIME类型字符串应该以vnd.android.cursor.item/开头
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/profile";

    public static final Uri    CONTENT_URI       = Uri.parse("content://" + AUTOHORITY + "/profile");
}
