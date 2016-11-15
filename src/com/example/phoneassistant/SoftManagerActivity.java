package com.example.phoneassistant;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Formatter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.MyAdapter.SoftItemAdapter;
import com.example.entity.PhoneMainItem;
import com.example.util.GetMemory;
import com.example.util.MemoryCircle;

public class SoftManagerActivity extends Activity implements Runnable {

	String[] softItemName = { "所有软件", "系统软件", "用户软件" };
	List<PhoneMainItem> list;
	TextView softTitle;
	ListView softItemListView;
	LinearLayout linearLayout;
	TextView inMemoryTV;
	TextView outMemoryTV;
	MemoryCircle mc;
	ImageButton back;
	
	GetMemory gm;
	ProgressBar pb1;
	ProgressBar pb2;
	int progress;
	int progress2;
	double per;
	double per2;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				pb1.setProgress(progress);
				break;
			case 1:
				pb2.setProgress(progress2);
				break;

			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_main_softmanager);
		back=(ImageButton) this.findViewById(R.id.btn_homeasup_default);
		softTitle = (TextView) findViewById(R.id.title_textview);
		softTitle.setText("软件管理");
		perMemory();
		pb1 = (ProgressBar) findViewById(R.id.phone_main_soft_in_progress);
		pb2 = (ProgressBar) findViewById(R.id.phone_main_soft_out_progress);
		pb1.setMax(100);
		pb2.setMax(100);
		pb1.setProgress(0);
		pb2.setProgress(0);
		new Thread(this).start();
		// MemoryCircle mc = new MemoryCircle(this);
		// linearLayout = (LinearLayout)
		// findViewById(R.id.phone_main_soft_circle);
		// linearLayout.addView(mc);

		SoftItemAdapter sia = new SoftItemAdapter(softItemName, this);
		softItemListView = (ListView) this
				.findViewById(R.id.phone_main_soft_listview);
		softItemListView.setAdapter(sia);
		softItemListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					Intent intent0 = new Intent(SoftManagerActivity.this,
							AllAppInfornation.class);
					startActivity(intent0);
					break;
				case 1:
					Intent intent1 = new Intent(SoftManagerActivity.this,
							PhoneMainSoftSystem.class);
					startActivity(intent1);
					break;
				case 2:
					Intent intent2 = new Intent(SoftManagerActivity.this,
							PhoneaMainSoftUser.class);
					startActivity(intent2);
					break;

				default:
					break;
				}

			}
		});
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SoftManagerActivity.this,PhoneMainActivity.class);
				startActivity(intent);
				SoftManagerActivity.this.finish();
			}
		});

	}


	public void perMemory() {
		gm = new GetMemory(this);
		gm.memorySize();
		
//		System.out.println(mc.allMemory+"-----===="+gm.outMemory);
		inMemoryTV=(TextView) findViewById(R.id.phone_main_soft_intv2);
		String inFreeM=Formatter.formatFileSize(SoftManagerActivity.this,(long) gm.free);
		String inAllM=Formatter.formatFileSize(SoftManagerActivity.this,(long) gm.allMemory);
		inMemoryTV.setText("可用："+inFreeM+"/"+inAllM);
		String outAllM=Formatter.formatFileSize(SoftManagerActivity.this,(long) gm.outMemory);
		String outFreeM=Formatter.formatFileSize(SoftManagerActivity.this,(long) gm.avaiOutMemory);
		outMemoryTV=(TextView) findViewById(R.id.phone_main_soft_outtv2);
		outMemoryTV.setText("可用："+outFreeM+"/"+outAllM);
		per = (gm.free / gm.allMemory) *100;
		per2 = (gm.avaiOutMemory / gm.outMemory) * 100;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (progress < per || progress2 < per2) {
			try {
				if (progress < per) {
					handler.sendEmptyMessage(0);
					progress++;
				}
				if (progress2 < per2) {
					progress2++;
					handler.sendEmptyMessage(1);
				}
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
