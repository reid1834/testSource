package com.demo.test;

import java.util.Timer;
import java.util.TimerTask;

import com.demo.test.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class Tab1Activity extends Activity {
	private static Boolean isExit = false;
	private static Boolean hasTask = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab1xml);
	}

	Timer tExit = new Timer();
	TimerTask task = new TimerTask(){

		@Override
		public void run() {
			isExit = false;
			hasTask = true;
		}
		
	};
	
	@Override
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
	  if (keyCode == KeyEvent.KEYCODE_BACK) {
		  if(!isExit){
			  isExit = true;
			  Toast.makeText(Tab1Activity.this, "再按一次退出程序", Toast.LENGTH_LONG).show();
			  if(!hasTask){
				  tExit.schedule(task, 3000);
			  }
			  return true;
		  }else{
			  finish();
			  System.exit(0);
		  }
	  }
	  return false;
	 }
}
