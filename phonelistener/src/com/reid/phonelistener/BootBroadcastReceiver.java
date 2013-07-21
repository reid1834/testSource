package com.reid.phonelistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg1) {
		Intent service = new Intent(context, PhoneService.class);//显示/隐式
		context.startService(service);//Intent激活组件(Service)
	}

}
