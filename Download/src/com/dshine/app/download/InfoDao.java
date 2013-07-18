package com.dshine.app.download;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class InfoDao {
	
	private DBOpenHelper helper = null;
	String TAG="InfoDao";

	public InfoDao(Context context) {
		helper = new DBOpenHelper(context);
	}

	public synchronized void insert(Info info) {
		Log.d(TAG, "insert");
		SQLiteDatabase db = null;
		try {
			db = helper.getWritableDatabase();
			db.execSQL("INSERT INTO info(path, thid, done) VALUES(?, ?, ?)",
					new Object[] { info.getPath(), info.getThid(), info.getDone() });
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(db!=null&&db.isOpen()){
				db.close();
			}
		}
	}

	public void delete(String path, int thid) {
		Log.d(TAG, "delete");
		SQLiteDatabase db = null;
		try {
			db = helper.getWritableDatabase();
			db.execSQL("DELETE FROM info WHERE path=? AND thid=?", new Object[] {
					path, thid });
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(db!=null&&db.isOpen()){
				db.close();
			}
		}
	}

	public synchronized void update(Info info) {
		Log.d(TAG, "update");
		SQLiteDatabase db = null;
		try {
			db = helper.getWritableDatabase();
			db.execSQL("UPDATE info SET done=? WHERE path=? AND thid=?",
					new Object[] { info.getDone(), info.getPath(), info.getThid() });
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(db!=null&&db.isOpen()){
				db.close();
			}
		}
	}

	public synchronized Info query(String path, int thid) {
		Log.d(TAG, "query");
		SQLiteDatabase db = null;
		Cursor c = null;
		Info info = null;
		try {
			db = helper.getReadableDatabase();
			c = db.rawQuery(
					"SELECT path, thid, done FROM info WHERE path=? AND thid=?",
					new String[] { path, String.valueOf(thid) });
			info = null;
			if (c.moveToNext())
				info = new Info(c.getString(0), c.getInt(1), c.getInt(2));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(c!=null&&!c.isClosed()){
				c.close();
			}
			if(db!=null&&db.isOpen()){
				db.close();
			}
		}
		return info;
	}

	public void deleteAll(String path, int len) {
		Log.d(TAG, "deleteAll");
		SQLiteDatabase db = null;
		Cursor c = null;
		try {
			db = helper.getWritableDatabase();
			c = db.rawQuery("SELECT SUM(done) FROM info WHERE path=?",
					new String[] { path });
			if (c.moveToNext()) {
				int result = c.getInt(0);
				if (result == len)
					db.execSQL("DELETE FROM info WHERE path=? ",
							new Object[] { path });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(c!=null&&!c.isClosed()){
				c.close();
			}
			if(db!=null&&db.isOpen()){
				db.close();
			}
		}
	}

	public List<String> queryUndone() {
		Log.d(TAG, "queryUndone");
		SQLiteDatabase db = null;
		Cursor c = null;
		List<String> pathList = new ArrayList<String>();
		try {
			db = helper.getReadableDatabase();
			c = db.rawQuery("SELECT DISTINCT path FROM info", null);
			pathList = new ArrayList<String>();
			while (c.moveToNext())
				pathList.add(c.getString(0));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(c!=null&&!c.isClosed()){
				c.close();
			}
			if(db!=null&&db.isOpen()){
				db.close();
			}
		}
		return pathList;
	}
	
}