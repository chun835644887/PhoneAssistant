package com.example.phoneassistant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;

import com.example.MyAdapter.JPushReceiver;
import com.example.util.DBMyTools;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ViewPage_PhoneAssistant extends Activity implements Runnable {
	ArrayList<ImageView> imagelist = new ArrayList<ImageView>();
	ArrayList<ImageView> imagepoint = new ArrayList<ImageView>();
	ArrayList<Integer> imageid = new ArrayList<Integer>();
	ViewPager viewPager;
	Button viewpage_button;
	LinearLayout linearlayout;
	int index = 0;
	DBMyTools ddd;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0) {
				ddd.query();
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpage_phoneassistant_acitvity);
		init();
		getimage();
		read();

		viewpage_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent1 = new Intent(ViewPage_PhoneAssistant.this,
						Logo_Activity.class);
				startActivity(intent1);

			}
		});
	}

	// 初始化控件
	public void init() {
		viewpage_button = (Button) this.findViewById(R.id.pageview_button);
		linearlayout = (LinearLayout) findViewById(R.id.linearlayout_point);
		viewPager = (ViewPager) findViewById(R.id.pager_id);

	}

	public void read() {
		// 定义输入输出流
		InputStream is = null;
		OutputStream os = null;
		try {
			AssetManager assetManager = this.getAssets();
			// 通过assetManager调用方法打开所需文件返回一个输入流
			is = assetManager.open("db" + File.separator + "commonnum.db");
			// 写入文件的位置
			os = new FileOutputStream("data" + File.separator + "data"
					+ File.separator + getPackageName() + File.separator
					+ "databases" + File.separator + "commonnum.db");
			int length = 0;
			byte[] b = new byte[1024];
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取导航页的图片
	public void getimage() {
		MyTools mytools = new MyTools(this);
		String c = mytools.getString("hh");
		// 判断是不是第一次进入该软件
		if (c == "0") {
			ddd = new DBMyTools(ViewPage_PhoneAssistant.this);
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					ddd.readDB();
					handler.sendEmptyMessage(0);
				}
			}).start();
			mytools.saveString("hh", "aa");
			// 循环获取导航页的每一张图片
			for (int i = 0; i < 3; i++) {
				int id = getResources().getIdentifier("adware_style_page" + i,
						"drawable", getPackageName());
				int point_id = getResources().getIdentifier(
						"adware_style_default", "drawable", getPackageName());
				imageid.add(id);
				ImageView imagerview = new ImageView(this);
				ImageView imagerpoint = new ImageView(this);

				// 加载时如果是第一张图片，默认第一张为选中状态，不是第一张的图片的圆点就设为默认
				if (i == 0) {
					imagerpoint
							.setImageResource(R.drawable.adware_style_selected);
				} else {
					imagerpoint
							.setImageResource(R.drawable.adware_style_default);
				}
				// 把对应的图片添加到控件里
				imagerview.setImageResource(id);
				imagelist.add(imagerview);
				imagepoint.add(imagerpoint);
				//
				viewPager.setAdapter(new MyAdapter());
				// 设置当页面改变时的监听
				viewPager.setOnPageChangeListener(new OnPageChangeListener() {

					@Override
					public void onPageSelected(int arg0) {
						// TODO Auto-generated method stub
						if (arg0 == imagelist.size() - 1) {
							viewpage_button.setVisibility(View.VISIBLE);
						} else {
							viewpage_button.setVisibility(View.GONE);
						}
						imagepoint.get(index).setImageResource(
								R.drawable.adware_style_default);
						imagepoint.get(arg0).setImageResource(
								R.drawable.adware_style_selected);
						index = arg0;

					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onPageScrollStateChanged(int arg0) {
						// TODO Auto-generated method stub

					}
				});

			}
		} else {
			JPushInterface.init(this);
			JPushInterface.setDebugMode(true);
//			setReceiver();
			Log.i("tag","c");
			Intent intent = new Intent(ViewPage_PhoneAssistant.this,
					Logo_Activity.class);
			startActivity(intent);

		}
	}
	public void setReceiver() {
		MyTools myTools = new MyTools(ViewPage_PhoneAssistant.this);
		String c = myTools.getString("state2");
		
		if (!c.equals("0")) {
			/*
			 * 取消静态注册
			 */
			JPushInterface.stopPush(ViewPage_PhoneAssistant.this);
		}
	}

	class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imagelist.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(imagelist.get(position));
			return imagelist.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub

			container.removeView(imagelist.get(position));
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
