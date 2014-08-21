/**
 * 
 * �ṩContentProvider����ĸ��ֳ��������ⲿ������Ҫ���ʵ�ʱ�򣬾Ϳ��Բο���Щ�����������ݡ�
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
	//�������ݿ��ʱ�򣬶�������ϰ汾��Ϣ�����ұ������4
	public static final int DATABASE_VERSION = 4;
	public static final String USERS_TABLE_NAME = "teacher";
	
	public static final class UserTableData implements BaseColumns {
		public static final String TABLE_NAME = "teacher";
		//Uri,�ⲿ������Ҫ���ʾ���ͨ�����Uri���ʵģ����Uri������Ψһ�ġ�
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "teacher");
		
		//���ݼ���MIME�����ַ�����Ӧ����vnd.android.cursor.dir/��ͷ
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/hb.android.teachers";
		//��һ���ݵ�MIME�����ַ���Ӧ����vnd.android.cursor.item/��ͷ
		public static final String CONTENT_TYPE_ITEM = "vnd.android.cursor.item/hb.android.teacher";
		
		/* �Զ���ƥ���� */
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
