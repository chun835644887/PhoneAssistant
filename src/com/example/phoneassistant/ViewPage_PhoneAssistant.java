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

	// ��ʼ���ؼ�
	public void init() {
		viewpage_button = (Button) this.findViewById(R.id.pageview_button);
		linearlayout = (LinearLayout) findViewById(R.id.linearlayout_point);
		viewPager = (ViewPager) findViewById(R.id.pager_id);

	}

	public void read() {
		// �������������
		InputStream is = null;
		OutputStream os = null;
		try {
			AssetManager assetManager = this.getAssets();
			// ͨ��assetManager���÷����������ļ�����һ��������
			is = assetManager.open("db" + File.separator + "commonnum.db");
			// д���ļ���λ��
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

	// ��ȡ����ҳ��ͼƬ
	public void getimage() {
		MyTools mytools = new MyTools(this);
		String c = mytools.getString("hh");
		// �ж��ǲ��ǵ�һ�ν�������
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
			// ѭ����ȡ����ҳ��ÿһ��ͼƬ
			for (int i = 0; i < 3; i++) {
				int id = getResources().getIdentifier("adware_style_page" + i,
						"drawable", getPackageName());
				int point_id = getResources().getIdentifier(
						"adware_style_default", "drawable", getPackageName());
				imageid.add(id);
				ImageView imagerview = new ImageView(this);
				ImageView imagerpoint = new ImageView(this);

				// ����ʱ����ǵ�һ��ͼƬ��Ĭ�ϵ�һ��Ϊѡ��״̬�����ǵ�һ�ŵ�ͼƬ��Բ�����ΪĬ��
				if (i == 0) {
					imagerpoint
							.setImageResource(R.drawable.adware_style_selected);
				} else {
					imagerpoint
							.setImageResource(R.drawable.adware_style_default);
				}
				// �Ѷ�Ӧ��ͼƬ��ӵ��ؼ���
				imagerview.setImageResource(id);
				imagelist.add(imagerview);
				imagepoint.add(imagerpoint);
				//
				viewPager.setAdapter(new MyAdapter());
				// ���õ�ҳ��ı�ʱ�ļ���
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
			 * ȡ����̬ע��
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
