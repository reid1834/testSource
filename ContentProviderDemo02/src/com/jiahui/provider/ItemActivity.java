package com.jiahui.provider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ItemActivity extends Activity {

	private EditText edt_item_name;
	private EditText edt_item_age;
	private EditText edt_item_id;
	private Button btndel, btnupdate;

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.item);

		edt_item_id = (EditText) this.findViewById(R.id.edt_item_id);
		edt_item_id.setEnabled(false);// 控制不可用
		edt_item_name = (EditText) this.findViewById(R.id.edt_item_name);
		edt_item_age = (EditText) this.findViewById(R.id.edt_item_age);
		// 得到传过来的数据
		btndel = (Button) this.findViewById(R.id.btndel);
		btnupdate = (Button) this.findViewById(R.id.btnupdate);

		intent = getIntent();

		Bundle bundle = intent.getBundleExtra("item");

		int id = bundle.getInt("id");
		System.out.println("id----" + id);
		String name = bundle.getString("name");
		int age = bundle.getInt("age");

		edt_item_id.setText(String.valueOf(id));
		edt_item_name.setText(name);
		edt_item_age.setText(String.valueOf(age));

		btndel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				ContentResolver contentResolver = ItemActivity.this
						.getContentResolver();
				// 构建Uri
				String url = "content://com.jiahui.provider.myprovider/person/"
						+ edt_item_id.getText();
				Uri uri = Uri.parse(url);

				int result = contentResolver.delete(uri, null, null);
				System.out.println("delete result:" + result);

				if (result >= 1) {
					Toast.makeText(ItemActivity.this, "删除成功", Toast.LENGTH_LONG)
							.show();
					ItemActivity.this.setResult(2);
					ItemActivity.this.finish();
				}

			}
		});

		btnupdate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				ContentResolver contentResolver = ItemActivity.this
						.getContentResolver();
				// 构建Uri
				String url = "content://com.jiahui.provider.myprovider/person/"
						+ edt_item_id.getText();
				Uri uri = Uri.parse(url);
				ContentValues values = new ContentValues();
				values.put("name", edt_item_name.getText().toString());
				values.put("age",
						Integer.parseInt(edt_item_age.getText().toString()));
				int result = contentResolver.update(uri, values, null, null);
				System.out.println("update result:" + result);
				if (result >= 1) {
					Toast.makeText(ItemActivity.this, "更新成功", Toast.LENGTH_LONG)
							.show();
					ItemActivity.this.setResult(2);
					ItemActivity.this.finish();
				}

			}
		});

	}

}
