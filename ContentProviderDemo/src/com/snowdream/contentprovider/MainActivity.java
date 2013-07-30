package com.snowdream.contentprovider;

import com.snowdream.contentprovider.R;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SimpleCursorAdapter;


public class MainActivity extends ListActivity {
	private SimpleCursorAdapter adapter= null;
	private Cursor mCursor = null;
	private ContentResolver mContentResolver = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData();
		initAdapter();
	}

	public void initData(){
		mContentResolver = getContentResolver();

		//填充数据
		for (int i = 0; i < 100; i++) {
			ContentValues values = new ContentValues();
			values.put(Profile.COLUMN_NAME, "张三"+i);
			mContentResolver.insert(Profile.CONTENT_URI, values);
		}
	}


	public void initAdapter(){
		//查询表格，并获得Cursor
		//查询全部数据
		mCursor = mContentResolver.query(Profile.CONTENT_URI, new String[]{Profile.COLUMN_ID,Profile.COLUMN_NAME}, null, null, null);

		//查询部分数据
		//String selection = Profile.COLUMN_ID + " LIKE '%1'";
		//mCursor = mContentResolver.query(Profile.CONTENT_URI, new String[]{Profile.COLUMN_ID,Profile.COLUMN_NAME}, selection, null, null);


		//查询一个数据
		//Uri uri = ContentUris.withAppendedId(Profile.CONTENT_URI, 50);
		//mCursor = mContentResolver.query(uri, new String[]{Profile.COLUMN_ID,Profile.COLUMN_NAME}, null, null, null);

		startManagingCursor(mCursor);

		//设置adapter
		adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, mCursor, new String[]{Profile.COLUMN_ID,Profile.COLUMN_NAME}, new int[]{android.R.id.text1,android.R.id.text2});
		setListAdapter(adapter);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}


}
