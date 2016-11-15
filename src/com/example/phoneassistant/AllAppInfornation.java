package com.example.phoneassistant;

import java.util.List;

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

import com.example.MyAdapter.AppInfoAdapter;
import com.example.entity.AppInfornation;
import com.example.util.AppPackageManage;

public class AllAppInfornation extends Activity {

	List<AppInfornation> list;
	ListView listview;
	Button button;
	AppPackageManage apm;
	CheckBox checkbox;
	TextView softTextView;
	ImageButton back;
	AppInfoAdapter myadapter ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_progress_activity);
		init();
		softTextView.setText("�������");
		apm = new AppPackageManage(this);
		apm.getAppInfo();
		list = apm.getAllAppinfo();
		myadapter = new AppInfoAdapter(
				AllAppInfornation.this, list);
		listview.setAdapter(myadapter);
		// ���ð�ť����
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// TODO Auto-generated method stub
				// �������ϵ������û���
				for (int i = 0; i < list.size(); i++) {
					// ���item��ѡ�У������ɾ����app
					if (list.get(i).getIschecked()) {
						// ɾ��ѡ�е�app
						Intent intent = new Intent();
						intent.setAction(Intent.ACTION_DELETE);
						intent.setData(Uri.parse("package:"
								+ list.get(i).getApppackage()));
						startActivity(intent);
						list.remove(i);
						// ���ݸı䣬ˢ��������
					}
				}
				myadapter.notifyDataSetChanged();
			}
		});
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				// �ѵ�ǰ��ischecked��ֵ����check��
				boolean check = list.get(arg2).getIschecked();
				// �ѵ�ǰ��checkֵȡ�������ض�����
				list.get(arg2).setIschecked(!check);
				// ˢ������
				myadapter.notifyDataSetChanged();
			}
		});
		// ����ȫѡcheckbox�ļ���
		checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				// ���ȫѡcheckboxΪtrue���������ϣ��Ѽ�����ischeckedΪfalse���ó�true
				if (arg1) {
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getIschecked() == false) {
							list.get(i).setIschecked(true);
						}
					}

				} else {
					// ȡ��ȫѡ���������ϣ��Ѽ�����ischeckedΪtrue���ó�false
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getIschecked()) {
							list.get(i).setIschecked(false);
						}
					}
				}
				// ����ˢ��
				myadapter.notifyDataSetChanged();
			}
		});
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back = new Intent(AllAppInfornation.this,
						SoftManagerActivity.class);
				startActivity(back);
				AllAppInfornation.this.finish();
			}
		});

	}
//@Override
//protected void onResume() {
//	// TODO Auto-generated method stub
//	super.onResume();
//	// �������ϵ������û���
//	for (int i = 0; i < list.size(); i++) {
//		// ���item��ѡ�У������ɾ����app
//		if (list.get(i).getIschecked()) {
//			// ɾ��ѡ�е�app
//			Intent intent = new Intent();
//			intent.setAction(Intent.ACTION_DELETE);
//			intent.setData(Uri.parse("package:"
//					+ list.get(i).getApppackage()));
//			startActivity(intent);
//			list.remove(i);
//			i--;
//			// ���ݸı䣬ˢ��������
//			myadapter.notifyDataSetChanged();
//
//		}
//
//	}
//}
	// ��ʼ���ؼ�
	public void init() {
		back = (ImageButton) this.findViewById(R.id.btn_homeasup_default);
		softTextView = (TextView) AllAppInfornation.this
				.findViewById(R.id.title_textview);
		checkbox = (CheckBox) findViewById(R.id.checkall);
		button = (Button) findViewById(R.id.user_progress_btn1);
		listview = (ListView) findViewById(R.id.listview);
	}

}
