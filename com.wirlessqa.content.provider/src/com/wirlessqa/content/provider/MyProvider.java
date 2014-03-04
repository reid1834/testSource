package com.wirlessqa.content.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 *  contentprovider�ĵ������п�����Activity,Service,Application��3��context����˭����,getContext����˭
 * @author www.wirelessqa.com 2013-2-26 ����11:01:46
 */
public class MyProvider extends ContentProvider {

    DBHelper                        mDbHelper = null;
    SQLiteDatabase                  db        = null;

    private static final UriMatcher mMatcher;
    // 1.��һ��������Ҫƥ��Uri·��ȫ����ע����
    static {
        // UriMatcher������ƥ��Uri
        // ����UriMatcher.NO_MATCH��ʾ��ƥ���κ�·���ķ�����(-1)��
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // ���match()����ƥ��content://com.wirlessqa.content.provider/profile·��������ƥ����Ϊ1
        mMatcher.addURI(Profile.AUTOHORITY, Profile.TABLE_NAME, Profile.ITEM); // �����Ҫƥ��uri�����ƥ��ͻ᷵��ƥ����
        // ���match()����ƥ�� content://com.wirlessqa.content.provider/profile/#·��������ƥ����Ϊ2
        mMatcher.addURI(Profile.AUTOHORITY, Profile.TABLE_NAME + "/#", Profile.ITEM_ID); // #��Ϊͨ���

        // ע������Ҫƥ���Uri�󣬾Ϳ���ʹ��mMatcher.match(uri)�����������Uri����ƥ�䣬���ƥ��ͷ���ƥ���룬
        // ƥ�����ǵ���addURI()��������ĵ���������������ƥ��content://com.wirelessqa.content.provider/profile·�������ص�ƥ����Ϊ1
    }

    // onCreate()������ContentProvider������ͻᱻ���ã�Androidϵͳ���к�ContentProviderֻ���ڱ���һ��ʹ����ʱ�Żᱻ������
    @Override
    public boolean onCreate() {
        mDbHelper = new DBHelper(getContext());

        db = mDbHelper.getReadableDatabase();

        return true;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
      long rowId;
      if (mMatcher.match(uri) != Profile.ITEM) {
          throw new IllegalArgumentException("Unknown URI" + uri);
      }
      rowId = db.delete(Profile.TABLE_NAME, selection, selectionArgs);
//      if(rowId>0){
//          Uri noteUri = ContentUris.withAppendedId(Profile.CONTENT_URI, rowId);
//          getContext().getContentResolver().notifyChange(noteUri, null);
//      }
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        switch (mMatcher.match(uri)) {
            case Profile.ITEM:
                return Profile.CONTENT_TYPE;
            case Profile.ITEM_ID:
                return Profile.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }
    }

    // �ⲿӦ�ó���ͨ����������� ContentProvider������ݡ�
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowId;
        // mMatcher.match(uri)�������Uri����ƥ�䣬���ƥ��ͷ���ƥ����
        if (mMatcher.match(uri) != Profile.ITEM) {
            throw new IllegalArgumentException("Unknown URI" + uri);
        }
        rowId = db.insert(Profile.TABLE_NAME, null, values); //�����ݿ����������
        if (rowId > 0) {
            // ContentUris.withAoppendedId ����Ϊ·������ID����
            Uri noteUri = ContentUris.withAppendedId(Profile.CONTENT_URI, rowId);
            // ���ⲿӦ����Ҫ��ContentProvider�е����ݽ�����ӡ�ɾ�����޸ĺͲ�ѯ����ʱ������ʹ��ContentResolver �������
            //ContentResolver������context��,ͨ��getContentResolver��ȡ
            //ContentResolver����ͨ��registerContentObserverע��۲��ߣ��۲�����ContentObserver �������ࣩ
            //һ��ContentProvider���������ݱ仯�󣬵���ContentResolver��notifyChange��������֪ͨ���۲��ߣ��ص��۲��ߵ�onChange������
            //ע��۲��߲��Ǳ���ģ�����notifyChange���Ǳ�����õ�
            getContext().getContentResolver().notifyChange(noteUri, null);
            return noteUri;
        }

        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor c = null;
        switch (mMatcher.match(uri)) {
            case Profile.ITEM:
                c = db.query(Profile.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case Profile.ITEM_ID:
                c = db.query(Profile.TABLE_NAME, projection, Profile.COLUMN_ID + "=" + uri.getLastPathSegment(),
                             selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }
        //�Ӷ���ContentService��ע��contentservice�Ĺ۲��ߣ�����۲�����cursor���ڲ���Ա��cursor��һ���ӿڣ��˴�������cursor��sqlitecursor��
        //����ÿ����ѯ���ص�cursor������contentprovider��Ӧ���ݸı�ʱ�õ�֪ͨ����Ϊ��Щcursor����һ����Աע�����contentservice�Ĺ۲���
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

}
