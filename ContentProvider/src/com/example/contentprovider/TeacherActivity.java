/**
 * �������������ContentProvider�Ƿ���á�ͨ��������uri�������ݿ�
 * @author yang.li2
 */

package com.example.contentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TeacherActivity extends Activity {
	private final String TAG = "TeacherActivity";
	private Button insert;
	private Button query;
	private Button update;
	private Button delete;
	private Button querys;
	Uri uri = Uri.parse("content://hb.android.contentProvider/teacher");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teacher);
		
		insert = (Button)findViewById(R.id.insert);
		query = (Button)findViewById(R.id.query);
		update = (Button)findViewById(R.id.update);
		delete = (Button)findViewById(R.id.delete);
		querys = (Button)findViewById(R.id.querys);
		
		insert.setOnClickListener(new InsertListener());
		query.setOnClickListener(new QueryListener());
		update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ContentResolver cr = getContentResolver();
				ContentValues cv = new ContentValues();
				cv.put("name", "reid");
				cv.put("date_added", "blanche");
				int uri2 = cr.update(uri, cv, "_ID=?", new String[]{"3"});
				Log.d(TAG, "updated: " + uri2);
			}
		});
		
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ContentResolver cr = getContentResolver();
				cr.delete(uri, "_ID=?", new String[]{"2"});
			}
		});
		
		querys.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ContentResolver cr = getContentResolver();
				//����idΪ1������
				Cursor c = cr.query(uri, null, null, null, null);
				c.close();
			}
		});
	}

	private class InsertListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			ContentResolver cr = getContentResolver();
			ContentValues cv = new ContentValues();
			cv.put("title", "����");
			cv.put("name", "Reid");
			cv.put("sex", true);
			Uri uri2 = cr.insert(uri, cv);
			Log.d(TAG, "uri2: " + uri2.toString());
		}
		
	}
	
	private class QueryListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			ContentResolver cr = getContentResolver();
			//����idΪ1������
			Cursor c = cr.query(uri, null, "_ID=?", new String[] {"1"}, null);
			//�������Ҫ���� c.moveToFirst���α��ƶ�����һ�����ݣ���Ȼ�����index -1 requested, with a size of 1 ����
			//cr.query���ص���һ�������
			if (c.moveToFirst() == false) {
				//Ϊ�յ�Cursor
				return;
			}
			
			int name = c.getColumnIndex("name");
			Log.d(TAG, c.getString(name));
			c.close();
		}
		
	}
}
