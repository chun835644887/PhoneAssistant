package com.example.phoneassistant;

import java.util.Timer;
import java.util.TimerTask;

import com.example.MyAdapter.BatteryBroadCast;
import com.example.util.GetMemory;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PhoneMainPhoneCheck extends Activity{

	TextView phoneName;
	TextView phoneVersion;
	TextView phoneAllRun;
	TextView phoneFreeRun;
	TextView phoneCpu;
	TextView phoneCpuCount;
	TextView phoneScreen;
	TextView phoneCamera;
	TextView phoneJiDai;
	TextView phoneIsRoot;
	ProgressBar phoneBattery;
	ProgressBar phoneBattery1;
	TextView batteryText;
	GetMemory gm;
	ImageButton back;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_main_phonecheck);
		init();
		phoneBattery.setMax(100);
		phoneBattery1.setMax(5);
		phoneBattery.setProgress(BatteryBroadCast.level);
		if(BatteryBroadCast.level>99){
			phoneBattery1.setProgress(5);
		}
	}
	
	//获取屏幕的分辨率，//activity的方法
	public String getScreen() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		String display = dm.widthPixels + "*" + dm.heightPixels;
		return display;
	}
	public void init() {
		back=(ImageButton) this.findViewById(R.id.btn_homeasup_default);
		gm=new GetMemory(this);
		gm.getAllRunMem();
		gm.getPhoneInfo();
		batteryText=(TextView) findViewById(R.id.phone_main_check_batterytext);
		phoneBattery1=(ProgressBar) findViewById(R.id.phone_main_phonecheck_battery1);
		phoneName=(TextView) findViewById(R.id.phone_main_phonecheck_phonename);
		//获取设备名
		phoneName.setText(Build.MODEL);
		phoneBattery=(ProgressBar) findViewById(R.id.phone_main_phonecheck_battery);
		phoneVersion=(TextView) findViewById(R.id.phone_main_phonecheck_phongversion1);
		//设置手机版本
		phoneVersion.setText(Build.VERSION.RELEASE);
		Log.i("yy",Build.VERSION.RELEASE);
		phoneAllRun=(TextView) findViewById(R.id.phone_main_phonecheck_phoneallrun);
		//获取总运行内存
		phoneAllRun.setText(gm.getAllRunMemory());
		phoneFreeRun=(TextView) findViewById(R.id.phone_main_phonecheck_phongversion);
		//获取空闲的运行内存
		phoneFreeRun.setText(Formatter.formatFileSize(this, (long) gm.getFreeMemory()));
		phoneCpu=(TextView) findViewById(R.id.phone_main_phonecheck_cpuname);
		//获取Cpu名字
		phoneCpu.setText("Cpu名称:"+gm.getCpuName());
		phoneCpuCount=(TextView) findViewById(R.id.phone_main_phonecheck_cpucount);
		//获取核数
		phoneCpuCount.setText("Cpu数量:"+gm.getCpuCount());
		phoneScreen=(TextView) findViewById(R.id.phone_main_phonecheck_phonefen);
		//获取屏幕的分辨率
		phoneScreen.setText("手机分辨率:"+getScreen());
		phoneCamera=(TextView) findViewById(R.id.phone_main_phonecheck_camerafen);
		//获取相机最大像素
		phoneCamera.setText("相机像素:"+gm.getCamer());
		phoneJiDai=(TextView) findViewById(R.id.phone_main_phonecheck_jidaiversion);
		//设置基带版本
		phoneJiDai.setText("基带版本:"+android.os.Build.VERSION.INCREMENTAL);
		phoneIsRoot=(TextView) findViewById(R.id.phone_main_phonecheck_jidairoot);
		//判断是否root
		phoneIsRoot.setText("是否Rroot:"+gm.isRoot());
	}
	Timer timer;
	TimerTask timerTask;
	public void batteryUp() {
		timer=new Timer();
		timerTask=new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
}
