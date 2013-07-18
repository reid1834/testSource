package com.dshine.app.download;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类
 * @author WikerYong   Email:<a href="#">yw_312@foxmail.com</a>
 * @version 2012-3-31 上午11:16:03
 */
public class DBOpenHelper extends SQLiteOpenHelper {  
  
    public DBOpenHelper(Context context) {  
        super(context, "download.db", null, 1);  
    }  
  
    @Override  
    public void onCreate(SQLiteDatabase db) {  
        try {
			db.execSQL("CREATE TABLE info(path VARCHAR(1024), thid INTEGER, done INTEGER, PRIMARY KEY(path, thid))");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(db.isOpen()&&db!=null){
				//db.close();
			}
		}
    }  
  
    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
    }  
  
}  