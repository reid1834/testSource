/**
 * 
 * 提供ContentProvider对外的各种常量，当外部数据需要访问的时候，就可以参考这些常量操作数据。
 * @author yang.li2
 *
 */

package com.example.contentprovider;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

public class ContentData {
	public static final String AUTHORITY = "hb.android.contentProvider";
	public static final String DATABSE_NAME = "teacher.db";
	//创建数据库的时候，都必须加上版本信息；并且必须大于4
	public static final int DATABASE_VERSION = 4;
	public static final String USERS_TABLE_NAME = "teacher";
	
	public static final class UserTableData implements BaseColumns {
		public static final String TABLE_NAME = "teacher";
		//Uri,外部程序需要访问就是通过这个Uri访问的，这个Uri必须是唯一的。
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "teacher");
		
		//数据集的MIME类型字符串则应该以vnd.android.cursor.dir/开头
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/hb.android.teachers";
		//单一数据的MIME类型字符串应该以vnd.android.cursor.item/开头
		public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/hb.android.teacher";
		
		/* 自定义匹配码 */
		public static final int TEACHERS = 1;
		public static final int TEACHER = 2;
		
		public static final String TITLE = "title";
		public static final String NAME = "name";
		public static final String DATE_ADDED = "date_added";
		public static final String SEX = "SEX";
		public static final String DEFAULT_SORT_ORDER = "_id desc";
		
		public static final UriMatcher uriMatcher;
		static {
			uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
			uriMatcher.addURI(ContentData.AUTHORITY, "teacher", TEACHERS);
			uriMatcher.addURI(ContentData.AUTHORITY, "teacher/#", TEACHER);
		}
	}
}
