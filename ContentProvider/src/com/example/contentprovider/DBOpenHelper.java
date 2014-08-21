/**
 * 
 * 这个类继承SQLiteOpenHelper抽象类，用于创建数据库和表。创建数据库是条用它的父类构造方法创建。
 * @author yang.li2
 * 
 */

package com.example.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {
	private static final String TAG = "DBOpenHelper";

	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		//必须通过super调用父类当中的构造函数
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public DBOpenHelper(Context context, String name, int version) {
		this(context, name, null, version);
	}

	/**
	 * 只有当数据库执行创建的时候，才会执行这个方法。
	 * 如果更改表名，也不会创建，只有当创建数据库的时候，才会创建改表名之后的数据表
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.d(TAG, "create table");
		db.execSQL("create table " + ContentData.UserTableData.TABLE_NAME
				+ "(" + BaseColumns._ID
				+" INTEGER PRIMARY KEY autoincrement,"
				+ ContentData.UserTableData.NAME + " varchar(20),"
				+ ContentData.UserTableData.TITLE + " varchar(20),"
				+ ContentData.UserTableData.DATE_ADDED + " long, "
				+ ContentData.UserTableData.SEX + " boolean)" + ";");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
