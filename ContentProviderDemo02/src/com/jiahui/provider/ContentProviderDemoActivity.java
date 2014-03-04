package com.jiahui.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.jiahui.model.Person;

public class ContentProviderDemoActivity extends Activity {

	private Button btnadd, btnqueryall;
	private EditText edtname, edtage;

	private ListView lvall;

	private List<Person> persons;
private SimpleAdapter simpleAdapter;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			List<Map<String, Object>> data = (List<Map<String, Object>>) msg.obj;

			System.out.println(data.size());

			
			simpleAdapter = new SimpleAdapter(
					ContentProviderDemoActivity.this, data, R.layout.list_item,
					new String[] { "id", "name", "age" }, new int[] {
							R.id.tvId, R.id.tvname, R.id.tvage });
		
			lvall.setAdapter(simpleAdapter);
			
		}

	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		persons = new ArrayList<Person>();

		btnqueryall = (Button) this.findViewById(R.id.btnqueryall);
		btnadd = (Button) this.findViewById(R.id.btnadd);
		edtname = (EditText) this.findViewById(R.id.edtname);
		edtage = (EditText) this.findViewById(R.id.edtage);
		lvall = (ListView) this.findViewById(R.id.lvall);

		btnadd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ContentResolver contentResolver = ContentProviderDemoActivity.this
						.getContentResolver();

				Uri url = Uri
						.parse("content://com.jiahui.provider.myprovider/person");
				ContentValues values = new ContentValues();
				values.put("name", edtname.getText().toString());
				values.put("age", edtage.getText().toString());
				Uri result = contentResolver.insert(url, values);

				System.out.println(result.toString());
				
				if (ContentUris.parseId(result)>0) {
					Toast.makeText(ContentProviderDemoActivity.this, "添加成功", Toast.LENGTH_LONG).show();
					//添加成功后再启动线程查询
					MyThread thread = new MyThread(ContentProviderDemoActivity.this);
					thread.start();
				}
			}
		});
		//查询所有
		btnqueryall.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
			
				MyThread thread = new MyThread(ContentProviderDemoActivity.this);
				thread.start();
			}
		});

		lvall.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Toast.makeText(ContentProviderDemoActivity.this, position,
				// Toast.LENGTH_LONG).show();
				System.out.println("position:" + position);

				Person person = persons.get(position);
				Bundle bundle = new Bundle();
				bundle.putInt("id", person.getId());

				bundle.putString("name", person.getName());

				bundle.putInt("age", person.getAge());

				Intent intent = new Intent(ContentProviderDemoActivity.this,
						ItemActivity.class);
				intent.putExtra("item", bundle);
				startActivityForResult(intent, 1);

			}
		});

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode==2) {
			MyThread thread = new MyThread(ContentProviderDemoActivity.this);
			thread.start();

		}
			
	}


	class MyThread extends Thread {

		Context context;

		public MyThread(Context context) {
			//一定要清空。否则会 有问题，每执行一次都会把之前的全部的item加进去
			persons.clear();
			lvall.setAdapter(null);
			
			this.context = context;
		}

		@Override
		public void run() {

			Uri url = Uri
					.parse("content://com.jiahui.provider.myprovider/person");

			Cursor cursor = context.getContentResolver().query(url,
					new String[] { "_id", "name", "age" }, null, null, "_id");

			while (cursor.moveToNext()) {

				// System.out.println("_id:"
				// + cursor.getInt(cursor.getColumnIndex("_id")));
				// System.out.println("name:"
				// + cursor.getString(cursor.getColumnIndex("name")));
				// System.out.println("age:"
				// + cursor.getInt(cursor.getColumnIndex("age")));
				Person person = new Person();
				person.setId(cursor.getInt(cursor.getColumnIndex("_id")));
				person.setName(cursor.getString(cursor.getColumnIndex("name")));
				person.setAge(cursor.getInt(cursor.getColumnIndex("age")));
				persons.add(person);
			
			}

			cursor.close();
			
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

			Map<String, Object> map=null;
			for (int i = 0; i < persons.size(); i++) {

				map = new HashMap<String, Object>();

				map.put("id", persons.get(i).getId());
				map.put("name", persons.get(i).getName());

				map.put("age", persons.get(i).getAge());
				data.add(map);

			}
			if (data.size()>=persons.size()) {
				
			}
			Message msg = handler.obtainMessage();
			msg.obj = data;
			handler.sendMessage(msg);
		}

	}

}