package com.reid.phonelistener;

import java.io.File;
import java.io.IOException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(new PhoneListener(), PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	private final class PhoneListener extends PhoneStateListener{
		private String incomingNumber;
		private File file;
		private MediaRecorder mediaRecorder;

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// TODO Auto-generated method stub
			super.onCallStateChanged(state, incomingNumber);
			
			try {
				switch (state) {
				case TelephonyManager.CALL_STATE_RINGING://来电
					this.incomingNumber = incomingNumber;
					break;
					
				case TelephonyManager.CALL_STATE_OFFHOOK://接通电话
					file = new File(Environment.getExternalStorageDirectory(), incomingNumber + System.currentTimeMillis() + ".3gp");
					mediaRecorder = new MediaRecorder();
					mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
					mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					mediaRecorder.setOutputFile(file.getAbsolutePath());
					mediaRecorder.prepare();
					mediaRecorder.start();
					break;
					
				case TelephonyManager.CALL_STATE_IDLE://挂断电话后回归到空闲状态
					if (mediaRecorder != null) {
						mediaRecorder.stop();
						mediaRecorder.release();
						mediaRecorder = null;
						//uploadFile();
					}
					break;
				}
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*private void uploadFile() {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
				}
			}).start();
		}*/
		
	}

}
