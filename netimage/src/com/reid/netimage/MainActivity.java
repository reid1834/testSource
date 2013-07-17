package com.reid.netimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.reid.service.ImageService;

//liyang
public class MainActivity extends Activity {
	private EditText pathText;
	private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        StrictMode.setThreadPolicy(new 
        StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        
        pathText = (EditText)findViewById(R.id.imagepath);
        imageView = (ImageView)findViewById(R.id.imageView);
        Button button = (Button)this.findViewById(R.id.button);
        button.setOnClickListener(new ButtonClickListener());
    }

    private final class ButtonClickListener implements View.OnClickListener{

		public void onClick(View v) {
			// TODO Auto-generated method stub
			String path = pathText.getText().toString();
			try {
				byte[] data = ImageService.getImage(path);
				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
				imageView.setImageBitmap(bitmap);//��ʾͼƬ				
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
			}

		}
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
