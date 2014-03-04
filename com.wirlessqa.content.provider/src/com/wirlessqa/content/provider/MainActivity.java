package com.wirlessqa.content.provider;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

/**
 * @author www.wirelessqa.com 2013-2-26 ����11:00:29
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
        // ɾ��һ����¼����������ķ���
        // String where = "_id = '1'";
        // mContentResolver.delete(Profile.CONTENT_URI, where, null);
        // �������
        for (int i = 0; i < 20; i++) {
            // ContentValues ��HashTable���ƶ���һ�ִ洢�Ļ��� ���������������������
            // contenvaluesֻ�ܴ洢�������͵����ݣ���string��int֮��ģ����ܴ洢�������ֶ���
            ContentValues values = new ContentValues();
            values.put(Profile.COLUMN_NAME, i + " ��ַ��www.wirelessqa.com");
            // ͨ��ContentResolver�������ݿ��������
            mContentResolver.insert(Profile.CONTENT_URI, values);
        }
    }

    public void initAdapter() {
        // ��ѯ��񣬲����Cursor
        // ��ѯȫ������
        mCursor = mContentResolver.query(Profile.CONTENT_URI, new String[] { Profile.COLUMN_ID, Profile.COLUMN_NAME },
                                         null, null, null);

        // ��ѯ��������
        // String selection = Profile.COLUMN_ID + " LIKE '%1'";
        // mCursor = mContentResolver.query(Profile.CONTENT_URI, new String[]{Profile.COLUMN_ID,Profile.COLUMN_NAME},
        // selection, null, null);

        // ��ѯһ������
        // Uri uri = ContentUris.withAppendedId(Profile.CONTENT_URI, 50);
        // mCursor = mContentResolver.query(uri, new String[]{Profile.COLUMN_ID,Profile.COLUMN_NAME}, null, null, null);

        startManagingCursor(mCursor);

        // ����adapter
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, mCursor, new String[] {
                Profile.COLUMN_ID, Profile.COLUMN_NAME }, new int[] { android.R.id.text1, android.R.id.text2 });
        setListAdapter(adapter);
    }

}
