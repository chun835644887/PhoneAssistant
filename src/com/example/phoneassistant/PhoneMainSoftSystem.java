package com.example.phoneassistant;

import java.util.List;

import com.example.MyAdapter.AppInfoAdapter;
import com.example.entity.AppInfornation;
import com.example.util.AppPackageManage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class PhoneMainSoftSystem extends Activity {
	List<AppInfornation> list;
	ListView listview;
	Button button;
	AppPackageManage apm;
	CheckBox checkbox;
	TextView softTextView;
	ImageButton back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_progress_activity);
		init();
		apm = new AppPackageManage(PhoneMainSoftSystem.this);
		apm.getAppInfo();
		list = apm.getSysAppinfo();
		final AppInfoAdapter systemSoftAdapter = new AppInfoAdapter(
				PhoneMainSoftSystem.this, list);
		listview.setAdapter(systemSoftAdapter);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// 遍历集合的所有用户，
				for (int i = 0; i < list.size(); i++) {
					// 如果item被选中，则就做删除该app
					if (list.get(i).getIschecked()) {
						// 删除选中的app
						Intent intent = new Intent();
						intent.setAction(Intent.ACTION_DELETE);
						intent.setData(Uri.parse("package:"
								+ list.get(i).getApppackage()));
						startActivity(intent);
						list.remove(i);
						// 数据改变，刷新适配器
					}
				}
				systemSoftAdapter.notifyDataSetChanged();
			}
		});
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				// 把当前的ischecked的值赋给check，
				boolean check = list.get(arg2).getIschecked();
				// 把当前的check值取反，返回对象中
				list.get(arg2).setIschecked(!check);
				// 刷新数据
				systemSoftAdapter.notifyDataSetChanged();
			}
		});
		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
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
				systemSoftAdapter.notifyDataSetChanged();
			}
		});
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back = new Intent(PhoneMainSoftSystem.this,
						SoftManagerActivity.class);
				startActivity(back);
				PhoneMainSoftSystem.this.finish();
			}
		});

	}

	// 初始化控件
	public void init() {
		back=(ImageButton) this.findViewById(R.id.btn_homeasup_default);
		softTextView = (TextView) PhoneMainSoftSystem.this
				.findViewById(R.id.title_textview);
		checkbox = (CheckBox) findViewById(R.id.checkall);
		button = (Button) findViewById(R.id.user_progress_btn1);
		listview = (ListView) findViewById(R.id.listview);
	}

}
