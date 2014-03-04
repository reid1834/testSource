package com.wirlessqa.content.provider;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * ����һ�����ݿ���
 * 
 * @author www.wirelessqa.com 2013-2-26 ����11:01:46
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME    = "wirelessqa.db"; // ���ݿ���

    private static final int    DATABASE_VERSION = 1;              // �汾��

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) throws SQLException {
        // ���������ݱ��б��뺬��"_id"����ֶΣ�����ֶ����������ģ������ʱ���ù�����ֶΣ����ݿ���Լ������ؼ���
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Profile.TABLE_NAME + "(" + Profile.COLUMN_ID
                   + " INTEGER PRIMARY KEY AUTOINCREMENT," + Profile.COLUMN_NAME + " VARCHAR NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLException {
        // ɾ�����������
        db.execSQL("DROP TABLE IF EXISTS " + Profile.TABLE_NAME + ";");
        onCreate(db);
    }
}
