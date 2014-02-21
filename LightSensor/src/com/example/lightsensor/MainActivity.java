package com.example.lightsensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{
	SensorManager sensorManager = null; //传感器管理器引用
	Sensor lightSensor = null; //光线传感器引用
	
	TextView accuracy_view = null;
	TextView value_0 = null;
	TextView value_1 = null;
	TextView value_2 = null;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //获得传感器管理器实例
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        //获得光线传感器实例
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //获得各个TextView
        accuracy_view = (TextView)findViewById(R.id.accuracy);
        value_0 = (TextView)findViewById(R.id.value_0);
        value_1 = (TextView)findViewById(R.id.value_1);
        value_2 = (TextView)findViewById(R.id.value_2);
    }


    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		 //注销
		sensorManager.unregisterListener(this, lightSensor);
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		//为传感器管理器注册监听
		sensorManager.registerListener(this,lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		 if(sensor.getType() == Sensor.TYPE_LIGHT){
			//设置将accuracy的值显示到屏幕上
			accuracy_view.setText("accuracy:"+accuracy);
		 }
	}


	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		 if(event.sensor.getType() == Sensor.TYPE_LIGHT){

			//将values的值显示到屏幕上
			float[] values = event.values;
			value_0.setText("value[0]:"+values[0]);
			value_1.setText("value[1]:"+values[1]);
			value_2.setText("value[2]:"+values[2]);
		 }
	}
    
}
