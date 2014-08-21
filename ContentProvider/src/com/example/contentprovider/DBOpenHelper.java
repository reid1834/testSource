/**
 * 
 * �����̳�SQLiteOpenHelper�����࣬���ڴ������ݿ�ͱ��������ݿ����������ĸ��๹�췽��������
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
		//����ͨ��super���ø��൱�еĹ��캯��
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public DBOpenHelper(Context context, String name, int version) {
		this(context, name, null, version);
	}

	/**
	 * ֻ�е����ݿ�ִ�д�����ʱ�򣬲Ż�ִ�����������
	 * ������ı�����Ҳ���ᴴ����ֻ�е��������ݿ��ʱ�򣬲Żᴴ���ı���֮������ݱ�
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
