package com.jiahui.provider;

import com.jiahui.db.DBHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyProvider extends ContentProvider {

	private DBHelper dbHelper;
	// ����һ��UriMatcher��
	private static final UriMatcher MATCHER = new UriMatcher(
			UriMatcher.NO_MATCH);
	private static final int PERSONS = 1;
	private static final int PERSON = 2;
	static {
		MATCHER.addURI("com.jiahui.provider.myprovider", "person", PERSONS);
		MATCHER.addURI("com.jiahui.provider.myprovider", "person/#", PERSON);

	}
	@Override
	public boolean onCreate() {
		System.out.println("---oncreate----");
		dbHelper = new DBHelper(this.getContext());
		return false;
	}

	// ��ѯ����
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		switch (MATCHER.match(uri)) {
		case PERSONS:
			// ��ѯ���е�����
			return db.query("person", projection, selection, selectionArgs,
					null, null, sortOrder);

		case PERSON:
			// ��ѯĳ��ID������
			// ͨ��ContentUris�����������ͳ�ID
			long id = ContentUris.parseId(uri);
			String where = " _id=" + id;
			if (!"".equals(selection) && selection != null) {
				where = selection + " and " + where;

			}

			return db.query("person", projection, where, selectionArgs, null,
					null, sortOrder);
		default:

			throw new IllegalArgumentException("unknow uri" + uri.toString());
		}

	}

	// ���ص�ǰ���������ݵ�mimeType
	@Override
	public String getType(Uri uri) {
		switch (MATCHER.match(uri)) {
		case PERSONS:
			return "vnd.android.cursor.dir/person";
		case PERSON:
			return "vnd.android.cursor.item/person";
		default:
			throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
	}

	// ��������
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Uri insertUri = null;
		switch (MATCHER.match(uri)) {
		case PERSONS:

			long rowid = db.insert("person", "name", values);
			insertUri = ContentUris.withAppendedId(uri, rowid);

			break;

		default:
			throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
		return insertUri;
	}

	// ɾ������
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int count = 0;
		switch (MATCHER.match(uri)) {
		case PERSONS:
			count = db.delete("person", selection, selectionArgs);
			return count;

		case PERSON:
			long id = ContentUris.parseId(uri);
			String where = "_id=" + id;
			if (selection != null && !"".equals(selection)) {
				where = selection + " and " + where;
			}
			count = db.delete("person", where, selectionArgs);
			return count;

		default:
			throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
	}

	// ��������
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();

		int count = 0;
		switch (MATCHER.match(uri)) {
		case PERSONS:
			count = db.update("person", values, selection, selectionArgs);
			break;
		case PERSON:
			// ͨ��ContentUri������õ�ID
			long id = ContentUris.parseId(uri);
			String where = "_id=" + id;
			if (selection != null && !"".equals(selection)) {
				where = selection + " and " + where;
			}
			count = db.update("person", values, where, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unkwon Uri:" + uri.toString());
		}
		return count;
	}

}
