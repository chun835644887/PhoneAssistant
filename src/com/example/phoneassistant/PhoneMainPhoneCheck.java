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
	
	//��ȡ��Ļ�ķֱ��ʣ�//activity�ķ���
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
		//��ȡ�豸��
		phoneName.setText(Build.MODEL);
		phoneBattery=(ProgressBar) findViewById(R.id.phone_main_phonecheck_battery);
		phoneVersion=(TextView) findViewById(R.id.phone_main_phonecheck_phongversion1);
		//�����ֻ��汾
		phoneVersion.setText(Build.VERSION.RELEASE);
		Log.i("yy",Build.VERSION.RELEASE);
		phoneAllRun=(TextView) findViewById(R.id.phone_main_phonecheck_phoneallrun);
		//��ȡ�������ڴ�
		phoneAllRun.setText(gm.getAllRunMemory());
		phoneFreeRun=(TextView) findViewById(R.id.phone_main_phonecheck_phongversion);
		//��ȡ���е������ڴ�
		phoneFreeRun.setText(Formatter.formatFileSize(this, (long) gm.getFreeMemory()));
		phoneCpu=(TextView) findViewById(R.id.phone_main_phonecheck_cpuname);
		//��ȡCpu����
		phoneCpu.setText("Cpu����:"+gm.getCpuName());
		phoneCpuCount=(TextView) findViewById(R.id.phone_main_phonecheck_cpucount);
		//��ȡ����
		phoneCpuCount.setText("Cpu����:"+gm.getCpuCount());
		phoneScreen=(TextView) findViewById(R.id.phone_main_phonecheck_phonefen);
		//��ȡ��Ļ�ķֱ���
		phoneScreen.setText("�ֻ��ֱ���:"+getScreen());
		phoneCamera=(TextView) findViewById(R.id.phone_main_phonecheck_camerafen);
		//��ȡ����������
		phoneCamera.setText("�������:"+gm.getCamer());
		phoneJiDai=(TextView) findViewById(R.id.phone_main_phonecheck_jidaiversion);
		//���û����汾
		phoneJiDai.setText("�����汾:"+android.os.Build.VERSION.INCREMENTAL);
		phoneIsRoot=(TextView) findViewById(R.id.phone_main_phonecheck_jidairoot);
		//�ж��Ƿ�root
		phoneIsRoot.setText("�Ƿ�Rroot:"+gm.isRoot());
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
