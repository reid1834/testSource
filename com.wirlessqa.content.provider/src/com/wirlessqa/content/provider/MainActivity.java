package com.wirlessqa.content.provider;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

/**
 * @author www.wirelessqa.com 2013-2-26 下午11:00:29
 */
public class MainActivity extends ListActivity {

    private SimpleCursorAdapter adapter          = null;
    private Cursor              mCursor          = null;
    private ContentResolver     mContentResolver = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initAdapter();
    }

    public void initData() {
        mContentResolver = getContentResolver();
        // 删除一条记录可以用下面的方法
        // String where = "_id = '1'";
        // mContentResolver.delete(Profile.CONTENT_URI, where, null);
        // 填充数据
        for (int i = 0; i < 20; i++) {
            // ContentValues 和HashTable类似都是一种存储的机制 但是两者最大的区别就在于
            // contenvalues只能存储基本类型的数据，像string，int之类的，不能存储对象这种东西
            ContentValues values = new ContentValues();
            values.put(Profile.COLUMN_NAME, i + " 网址：www.wirelessqa.com");
            // 通过ContentResolver来向数据库插入数据
            mContentResolver.insert(Profile.CONTENT_URI, values);
        }
    }

    public void initAdapter() {
        // 查询表格，并获得Cursor
        // 查询全部数据
        mCursor = mContentResolver.query(Profile.CONTENT_URI, new String[] { Profile.COLUMN_ID, Profile.COLUMN_NAME },
                                         null, null, null);

        // 查询部分数据
        // String selection = Profile.COLUMN_ID + " LIKE '%1'";
        // mCursor = mContentResolver.query(Profile.CONTENT_URI, new String[]{Profile.COLUMN_ID,Profile.COLUMN_NAME},
        // selection, null, null);

        // 查询一个数据
        // Uri uri = ContentUris.withAppendedId(Profile.CONTENT_URI, 50);
        // mCursor = mContentResolver.query(uri, new String[]{Profile.COLUMN_ID,Profile.COLUMN_NAME}, null, null, null);

        startManagingCursor(mCursor);

        // 设置adapter
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, mCursor, new String[] {
                Profile.COLUMN_ID, Profile.COLUMN_NAME }, new int[] { android.R.id.text1, android.R.id.text2 });
        setListAdapter(adapter);
    }

}
