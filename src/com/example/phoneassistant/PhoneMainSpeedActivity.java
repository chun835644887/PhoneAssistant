package com.example.phoneassistant;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.MyAdapter.PhoneMainSpeedAdapter;
import com.example.entity.AppInfornation;
import com.example.util.AppPackageManage;
import com.example.util.GetMemory;

public class PhoneMainSpeedActivity extends Activity implements Runnable {

	List<AppInfornation> userList;
	List<AppInfornation> systemList;
	List<AppInfornation> list;
	AppPackageManage apm;
	GetMemory gm;
	Button cleanBtn;
	Button choceBtn;
	ImageButton backBtn;
	ListView speedListView;
	CheckBox allProgress;
	PhoneMainSpeedAdapter speedAdapter;
	ActivityManager activityManager;
	TextView speedTitle;
	TextView speedPhoneModle;
	TextView speedPhoneVersion;
	TextView speedProgressBarText;
	ProgressBar speedPro;
	int pro;
	double allRunMen;
	double freeRunMen;
	double per;
	// 加载数据是的圆形进度
	ProgressBar cirPro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.phone_main_speed_activity);

		apm = new AppPackageManage(this);
		gm = new GetMemory(this);

		init();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				cirPro.setVisibility(View.VISIBLE);
				getData();
				handler.sendEmptyMessage(1);
			}
		}).start();
		speedProgressBarText.setText("已用内存:" + freeRunMen / (1024 * 1024)
				+ "M/" + allRunMen / 1024 + "M");
		speedPro.setMax(100);
		speedPro.setProgress(0);

		new Thread(this).start();
		speedPhoneModle.setText(Build.MODEL);
		speedPhoneVersion.setText(Build.VERSION.RELEASE);
		choceBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (choceBtn.getText().equals("显示系统进程")) {
					choceBtn.setText("显示用户进程");
					list = systemList;
					speedAdapter.setdata(list);
					speedAdapter.notifyDataSetChanged();
				} else {
					// list.clear();
					// list.addAll(systemList);
					// speedAdapter=new
					// PhoneMainSpeedAdapter(PhoneMainSpeedActivity.this, list);
					// speedListView.setAdapter(speedAdapter);
					choceBtn.setText("显示系统进程");
					list = userList;
					speedAdapter.setdata(list);
					speedAdapter.notifyDataSetChanged();
				}
			}
		});
		speedAdapter = new PhoneMainSpeedAdapter(PhoneMainSpeedActivity.this,
				list);
		// list=systemList;
		// speedAdapter.setdata(list);
		// speedListView.setAdapter(speedAdapter);
		speedListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				// 把当前的ischecked的值赋给check，
				boolean check = list.get(position).getIschecked();
				// 把当前的check值取反，返回对象中
				list.get(position).setIschecked(!check);
				// 刷新数据
				speedAdapter.notifyDataSetChanged();
			}
		});
		allProgress.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub

				// TODO Auto-generated method stub
				// 如果全选checkbox为true，遍历集合，把集合中ischecked为false设置成true
				if (isChecked) {
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getIschecked() == false) {
							list.get(i).setIschecked(true);
						}
					}

				} else {
					// 取消全选，遍历集合，把集合中ischecked为true设置成false
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getIschecked()) {
							list.get(i).setIschecked(false);
						}
					}
				}
				// 数据刷新
				speedAdapter.notifyDataSetChanged();

			}
		});
		cleanBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				activityManager = (ActivityManager) PhoneMainSpeedActivity.this
						.getSystemService(PhoneMainSpeedActivity.this.ACTIVITY_SERVICE);

				int a;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getIschecked()) {
						activityManager.killBackgroundProcesses(list.get(i)
								.getApppackage());

					}

				}
				apm.getRunProgress();
				if (choceBtn.getText().equals("显示系统进程")) {
					list = apm.getUserRunProcess();
//					speedAdapter.setList(apm.getUserRunProcess());
				} else {
//					speedAdapter.setList(apm.getSystemRunProcess());
					list = apm.getSystemRunProcess();
				}
				speedAdapter.notifyDataSetChanged();
			}
		});
		backBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back=new Intent(PhoneMainSpeedActivity.this,PhoneMainActivity.class);
				startActivity(back);
				PhoneMainSpeedActivity.this.finish();
			}
		});
	}

	public void init() {
		backBtn=(ImageButton) this.findViewById(R.id.btn_homeasup_default);
		cirPro = (ProgressBar) findViewById(R.id.phone_main_speed_circle_progress);
		speedProgressBarText = (TextView) this
				.findViewById(R.id.phone_main_speed_mem_text);
		speedTitle = (TextView) findViewById(R.id.title_textview);
		speedTitle.setText("设置");
		cleanBtn = (Button) findViewById(R.id.phone_main_speed_clean);
		choceBtn = (Button) findViewById(R.id.phone_main_speed_choce);
		speedListView = (ListView) PhoneMainSpeedActivity.this
				.findViewById(R.id.phone_main_speed_listview);
		allProgress = (CheckBox) PhoneMainSpeedActivity.this
				.findViewById(R.id.phone_main_speed_all);

		speedPhoneModle = (TextView) this
				.findViewById(R.id.phone_main_speed_generic);
		speedPhoneVersion = (TextView) this
				.findViewById(R.id.phone_main_speed_version);

		speedPro = (ProgressBar) findViewById(R.id.phone_main_speed_progress);
	}

	public void getData() {
		// Log.i("tag",apm.getUserRunProcess().size()+"11"+apm.getSystemRunProcess().size());
		// System.out.println(apm.getUserRunProcess().size()+"000"+apm.getSystemRunProcess().size());
		apm.getAppInfo();
		apm.getRunProgress();
		gm.getPhoneInfo();
		allRunMen = gm.getAllRunMem();
		freeRunMen = gm.getFreeMemory();
		// allRunMen的单位是kb单位，freeRunMen的单位是字节单位
		per = ((double) freeRunMen / (freeRunMen + allRunMen * 1024)) * 100;
		userList = apm.getUserRunProcess();
		systemList = apm.getSystemRunProcess();
		list = systemList;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				speedPro.setProgress(pro);
			}
			if (msg.what == 1) {
				// speedAdapter = new
				// PhoneMainSpeedAdapter(PhoneMainSpeedActivity.this,
				// list);
				cirPro.setVisibility(View.GONE);
				speedProgressBarText.setText(Formatter.formatFileSize(
						PhoneMainSpeedActivity.this, (long) gm.getFreeMemory())
						+ "/"
						+ Formatter.formatFileSize(PhoneMainSpeedActivity.this,
								(long) (gm.getAllRunMem() * 1024)));
				list = systemList;
				speedAdapter.setdata(list);
				speedListView.setAdapter(speedAdapter);
			}
			// if (msg.what == 1) {
			// speedAdapter = new
			// PhoneMainSpeedAdapter(PhoneMainSpeedActivity.this, list);
			// speedListView.setAdapter(speedAdapter);
			new Thread(PhoneMainSpeedActivity.this).start();
			// Log.i("qq","msg run"+list.size());
			// }
		};
	};

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (pro < per) {

			try {
				pro++;
				Thread.sleep(50);
				handler.sendEmptyMessage(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// AsyncTask<Params, Progress, Result>

}
