package com.wirlessqa.content.provider;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 定义一个数据库类
 * 
 * @author www.wirelessqa.com 2013-2-26 下午11:01:46
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME    = "wirelessqa.db"; // 数据库名

    private static final int    DATABASE_VERSION = 1;              // 版本号

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) throws SQLException {
        // 创建的数据表中必须含有"_id"这个字段，这个字段是自增长的，插入的时候不用管这个字段，数据库会自己递增地加上
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Profile.TABLE_NAME + "(" + Profile.COLUMN_ID
                   + " INTEGER PRIMARY KEY AUTOINCREMENT," + Profile.COLUMN_NAME + " VARCHAR NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) throws SQLException {
        // 删除并创建表格
        db.execSQL("DROP TABLE IF EXISTS " + Profile.TABLE_NAME + ";");
        onCreate(db);
    }
}
