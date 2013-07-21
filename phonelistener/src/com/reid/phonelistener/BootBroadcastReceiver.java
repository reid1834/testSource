package com.reid.phonelistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		Intent service = new Intent(context, PhoneService.class);//��ʾ/��ʽ
		context.startService(service);//Intent�������(Service)
	}

}
