package com.demo.test;

import com.demo.test.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;

public class MainActivity extends TabActivity implements OnTabChangeListener,OnGestureListener {
	private GestureDetector gestureDetector;
	private CustomTabHost tabHost;
	private TabWidget tabWidget;
	private boolean isOut = false;
	private static final int FLEEP_DISTANCE = 120;

	/** ��¼��ǰ��ҳID */
	private int currentTabID = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		tabHost = (CustomTabHost) findViewById(android.R.id.tabhost);
		tabWidget = (TabWidget) findViewById(android.R.id.tabs);
		tabHost.setOnTabChangedListener(this);

		createTab1();
		createTab2();
		//createTab3();
		//createTab4();
		
		gestureDetector = new GestureDetector(this);
		new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (gestureDetector.onTouchEvent(event)) {
					return true;
				}
				return false;
			}
		};
	}

	public void createTab1() {
		TabHost.TabSpec localTabSpec = this.tabHost.newTabSpec("0");
		View localView = getLayoutInflater().inflate(R.layout.tab_indicator, null);
		//ImageView localImageView = (ImageView) localView.findViewById(R.id.icon);
		TextView localTextView = (TextView) localView.findViewById(R.id.title);
		//localImageView.setImageResource(R.drawable.concern);
		localTextView.setText("THEME");
		Intent localIntent = new Intent(this, Tab1Activity.class);
		localTabSpec.setIndicator(localView).setContent(localIntent);
		this.tabHost.addTab(localTabSpec);
	}
	
	private void createTab2() {
		TabHost.TabSpec localTabSpec1 = this.tabHost.newTabSpec("1");
		View localView = getLayoutInflater().inflate(R.layout.tab_indicator, null);
		//ImageView localImageView = (ImageView) localView.findViewById(R.id.icon);
		TextView localTextView = (TextView) localView.findViewById(R.id.title);
		//localImageView.setImageResource(R.drawable.lastest);
		localTextView.setText("DIY THEME");
		TabHost.TabSpec localTabSpec2 = localTabSpec1.setIndicator(localView);
		Intent localIntent = new Intent(this, Tab2Activity.class);
		localTabSpec2.setContent(localIntent);
		this.tabHost.addTab(localTabSpec1);
		
	}

	private void createTab3() {
		TabHost.TabSpec localTabSpec1 = this.tabHost.newTabSpec("2");
		View localView = getLayoutInflater().inflate(R.layout.tab_indicator, null);
		//ImageView localImageView = (ImageView) localView.findViewById(R.id.icon);
		TextView localTextView = (TextView) localView.findViewById(R.id.title);
		//localImageView.setImageResource(R.drawable.concessions);
		localTextView.setText("���̹���");
		TabHost.TabSpec localTabSpec2 = localTabSpec1.setIndicator(localView);
		Intent localIntent = new Intent(this, Tab3Activity.class);
		localTabSpec2.setContent(localIntent);
		this.tabHost.addTab(localTabSpec1);
	}

	private void createTab4() {
		TabHost.TabSpec localTabSpec1 = this.tabHost.newTabSpec("3");
		View localView = getLayoutInflater().inflate(R.layout.tab_indicator, null);
		//ImageView localImageView = (ImageView) localView.findViewById(R.id.icon);
		TextView localTextView = (TextView) localView.findViewById(R.id.title);
		//localImageView.setImageResource(R.drawable.function);
		localTextView.setText("�ڵ����");
		TabHost.TabSpec localTabSpec2 = localTabSpec1.setIndicator(localView);
		Intent localIntent = new Intent(this, Tab4Activity.class);
		localTabSpec2.setContent(localIntent);
		this.tabHost.addTab(localTabSpec1);
	}


	@Override
	public void onTabChanged(String tabId) {
		//tabIdֵΪҪ�л�����tabҳ������λ��
		int tabID = Integer.valueOf(tabId);
		for (int i = 0; i < tabWidget.getChildCount(); i++) {
			if (i == tabID) {
				tabWidget.getChildAt(Integer.valueOf(i));
			} else {
				tabWidget.getChildAt(Integer.valueOf(i));
			}
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if (gestureDetector.onTouchEvent(event)) {
			event.setAction(MotionEvent.ACTION_CANCEL);
		}
		return super.dispatchTouchEvent(event);
	}
	
	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,float velocityY) {
		if (e1.getX() - e2.getX() <= (-FLEEP_DISTANCE)) {//�������һ���
			currentTabID = tabHost.getCurrentTab() - 1;
			if (currentTabID < 0) {
				currentTabID = tabHost.getTabCount() - 1;
			}
		} else if (e1.getX() - e2.getX() >= FLEEP_DISTANCE) {//�������󻬶�
			currentTabID = tabHost.getCurrentTab() + 1;
			if (currentTabID >= tabHost.getTabCount()) {
				currentTabID = 0;
			}
		}
		tabHost.setCurrentTab(currentTabID);
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,float distanceY) {
		return false;
	}
	@Override
	public void onShowPress(MotionEvent e) {
		
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

}