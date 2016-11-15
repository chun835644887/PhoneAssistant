package com.example.MyAdapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BatteryBroadCast extends BroadcastReceiver{

	public static int level;
	public static int temperature;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		level = intent.getIntExtra("level", 1000);
		temperature = intent.getIntExtra("temperature", 0);
	}
	
}
