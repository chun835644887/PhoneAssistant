package com.example.phoneassistant;

import com.example.util.GetMemory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Logo_Activity extends Activity implements Runnable{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loge_activity);
		new Thread(Logo_Activity.this).start();
		
	}

	@Override
	public void run() {
		
		// TODO Auto-generated method stub
		try {
			GetMemory getMemory=new GetMemory(Logo_Activity.this);
			getMemory.getPhoneInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent intent=new Intent(Logo_Activity.this,PhoneMainActivity.class);
		startActivity(intent);
		finish();
	}

}
