/**
 * 
 * �������ⲿ�����ṩ�����ڲ����ݵ�һ���ӿ�
 * @author yang.li2
 * 
 */

package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class TeacherContentProvider extends ContentProvider {
	private DBOpenHelper dbOpenHelper = null;

	/**
	 * ��һ���ص���������ContentProvider������ʱ�򣬾ͻ����У��ڶ�������Ϊָ�����ݿ����ƣ������ָ����
	 * �ͻ��Ҳ������ݿ⣻
	 * ������ݿ���ڵ�������ǲ����ٴ���һ�����ݿ�ġ�����Ȼ�״ε���������Ҳ�����������ݿ�������
	 * SQLiteDatabase��getWritableDatabase, getReadableDatabase���������е�һ���Żᴴ�����ݿ⣩
	 */
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		//��������DBOpenHelper�Ĺ��캯������һ�����ݿ�
		dbOpenHelper = new DBOpenHelper(this.getContext(), ContentData.DATABSE_NAME, ContentData.DATABASE_VERSION);
		return true;
	}
	
	/**
	 * ��ִ�����������ʱ�����û�����ݿ⣬���ᴴ����ͬʱҲ�ᴴ�����������û�б�������ִ��insert��ʱ��ͻᱨ��
	 * �������������Ҳ����ȫ������sql�����Ϥ��Ȼ�����db.execSQL(sql)ִ�С�
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		SQLiteDatabase db =  dbOpenHelper.getWritableDatabase();
		long id = 0;
		
		switch (ContentData.UserTableData.uriMatcher.match(uri)) {
		case ContentData.UserTableData.TEACHERS:
			id = db.insert("teacher", null, values);	//���ص��Ǽ�¼���кţ�����Ϊint��ʵ���Ͼ�������ֵ
			return ContentUris.withAppendedId(uri, id);
		case ContentData.UserTableData.TEACHER:
			id = db.insert("teacher", null, values);
			String path = uri.toString();
			return Uri.parse(path.substring(0, path.lastIndexOf("/")) + id); //�滻��id
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		int count = 0;
		switch (ContentData.UserTableData.uriMatcher.match(uri)) {
		case ContentData.UserTableData.TEACHERS:
			count = db.delete("teacher", selection, selectionArgs);
			break;
		case ContentData.UserTableData.TEACHER:
			//����ķ������ڴ�URI�н�����id����������·��content://hb.android.teacherProvider/teacher/10
			//���н���������ֵΪ10
			long personid = ContentUris.parseId(uri);
			String where = "_ID=" + personid; //ɾ��ָ��id�ļ�¼
			where += !TextUtils.isEmpty(selection) ? "and (" + selection  + ")" : ""; //����������������
			count = db.delete("teacher", where, selectionArgs);
			break;

		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		db.close();
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		int count = 0;
		switch (ContentData.UserTableData.uriMatcher.match(uri)) {
		case ContentData.UserTableData.TEACHERS:
			count = db.update("teacher", values, selection, selectionArgs);
			break;
		case ContentData.UserTableData.TEACHER:
			//���淽�����ڴ�URI�н�����id����������·��content://com.ljq.provider.personprovider/person/10
			//���н������Ż���Ϊ10
			long personid = ContentUris.parseId(uri);
			String where = "_ID=" + personid; //��ȡָ��id�ļ�¼
			where += !TextUtils.isEmpty(selection) ? " and (" + selection + ")" : ""; //����������������
			count = db.update("teacher", values, where, selectionArgs);
			break;

		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		db.close();
		return count;
	}
	
	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (ContentData.UserTableData.uriMatcher.match(uri)) {
		case ContentData.UserTableData.TEACHERS:
			return ContentData.UserTableData.CONTENT_TYPE;
		case ContentData.UserTableData.TEACHER:
			return ContentData.UserTableData.CONTENT_TYPE_ITEM;

		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		switch (ContentData.UserTableData.uriMatcher.match(uri)) {
		case ContentData.UserTableData.TEACHERS:
			return db.query("teacher", projection, selection, selectionArgs, null, null, sortOrder);
		case ContentData.UserTableData.TEACHER:
			//���н���������ֵΪ10
			long personid = ContentUris.parseId(uri);
			String where = "_ID=" + personid; //��ȡָ��id�ļ�¼
			where += !TextUtils.isEmpty(selection) ? " and {" + selection + ")" : ""; //����������������
			return db.query("teacher", projection, where, selectionArgs, null, null, sortOrder);

		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}

}
