package com.example.phoneassistant;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.MyAdapter.BatteryBroadCast;
import com.example.MyAdapter.JPushReceiver;
import com.example.entity.AppInfornation;
import com.example.util.AppPackageManage;
import com.example.util.GetMemory;
import com.example.util.ReGridView;
import com.example.util.SpeedCircle;

public class PhoneMainActivity extends Activity implements Runnable {

	int[] gridImid = { R.drawable.icon_rocket, R.drawable.icon_softmgr,
			R.drawable.icon_phonemgr, R.drawable.icon_telmgr,
			R.drawable.icon_filemgr, R.drawable.icon_sdclean };
	ImageView gridIm;
	TextView gridTv;
	TextView textPro;
	List<String> list;
	ReGridView regridview;
	TextView mainTitle;
	LinearLayout linearLayout;
	ImageButton speedButton;
	SpeedCircle sc;
	GetMemory gm;
	AppPackageManage apm;
	boolean subText;
	boolean add;
	double textProgress;
	int progress;
	TimerTask timerTask;
	Timer timer;
	BatteryBroadCast batteryBroadCast;
	ImageButton setting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_main_activity);
		init();
		getMainItem();
		mainTitle.setText("安卓易软件管家");
		regridview = (ReGridView) this.findViewById(R.id.phone_main_gridview);
		PhoneMainGridAdapter gridAdapter = new PhoneMainGridAdapter();
		regridview.setAdapter(gridAdapter);
		// 设置加速球显示的初始状态；
		int a = (int) (GetMemory.percentage * 100);
		textPro.setText(a + "");
		sc.setSub(true);
		sc.startCircle(GetMemory.percentage * 360);
		// 设置gridview的监听
		regridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					Intent intent0 = new Intent(PhoneMainActivity.this,
							PhoneMainSpeedActivity.class);

					startActivity(intent0);
					break;
				case 1:

					Intent intent1 = new Intent(PhoneMainActivity.this,
							SoftManagerActivity.class);
					startActivity(intent1);
					break;
				case 2:
					batteryBroadCast = new BatteryBroadCast();
					IntentFilter filter = new IntentFilter(
							Intent.ACTION_BATTERY_CHANGED);
					registerReceiver(batteryBroadCast, filter);
					Intent intent2 = new Intent(PhoneMainActivity.this,
							PhoneMainPhoneCheck.class);
					startActivity(intent2);
					break;
				case 3:

					Intent intent3 = new Intent(PhoneMainActivity.this,
							PhoneMainCommunicationActivity.class);
					startActivity(intent3);
					break;
				case 4:
					Intent intent4 = new Intent(PhoneMainActivity.this,
							PhoneMainFileManager.class);
					startActivity(intent4);
					break;
				case 5:
					Intent intent5 = new Intent(PhoneMainActivity.this,
							PhoneMainGarbageClean.class);
					startActivity(intent5);

					break;

				default:
					break;
				}

			}
		});

		speedButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gm = new GetMemory(PhoneMainActivity.this);
				apm = new AppPackageManage(PhoneMainActivity.this);
				apm.getRunProgress();
				gm.getPhoneInfo();
				ActivityManager activityManager = (ActivityManager) PhoneMainActivity.this
						.getSystemService(PhoneMainActivity.this.ACTIVITY_SERVICE);
				List<AppInfornation> list = apm.getUserRunProcess();
				textProgress = GetMemory.percentage * 100;
				for (int i = 0; i < list.size(); i++) {
					activityManager.killBackgroundProcesses(list.get(i)
							.getApppackage());
				}
				progress = (int) sc.getProgress();
				subText = true;
				add = false;
				setSpeedText();
				sc.setSub(true);
				sc.startCircle(GetMemory.percentage * 360);

			}
		});
		setting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent settingIntent=new Intent(PhoneMainActivity.this,PhoneSetting.class);
				startActivity(settingIntent);
			}
		});
	}

	// 初始化控件
	public void init() {
		setting=(ImageButton) this.findViewById(R.id.title_im);
		setting.setVisibility(View.VISIBLE);
		mainTitle = (TextView) findViewById(R.id.title_textview);
		speedButton = (ImageButton) findViewById(R.id.phone_main_speed_button);
		sc = (SpeedCircle) findViewById(R.id.phone_main_speedcircle);
		textPro = (TextView) findViewById(R.id.phone_main_speedcircle_text);

	}

	// 添加数据
	public void getMainItem() {
		list = new ArrayList<String>();
		list.add("手机加速");
		list.add("软件管理");
		list.add("手机检测");
		list.add("通讯大全");
		list.add("文件管理");
		list.add("垃圾清理");

	}

	public void setSpeedText() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (subText) {
					Log.i("yy", " msg3------");
					try {
						progress--;
						handler.sendEmptyMessage(0);
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (progress <= 0) {
						add = true;
						subText = false;
					}
				}
				while (add) {
					try {
						Log.i("yy", " msg4+++++++");
						progress++;
						handler.sendEmptyMessage(0);
						Thread.sleep(50);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (progress >= GetMemory.percentage * 100) {
						add = false;

					}
				}

			}
		}).start();
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				textPro.setText((int) progress + "");
			}
			if (msg.what == 1) {
				textPro.setText((int) progress + "");
			}
		};
	};

	class PhoneMainGridAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			arg1 = getLayoutInflater().inflate(R.layout.phone_main_gridview,
					null);
			gridIm = (ImageView) arg1.findViewById(R.id.phone_mian_gridview_im);
			gridTv = (TextView) arg1.findViewById(R.id.phone_main_gridview_tv);
			gridIm.setImageResource(gridImid[arg0]);
			gridTv.setText(list.get(arg0));
			return arg1;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 注销广播
		if (batteryBroadCast != null) {
			unregisterReceiver(batteryBroadCast);
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
