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

public class PhoneaMainSoftUser extends Activity{
	TextView softUserTitle;
	List<AppInfornation> list;
	ListView userListView;
	CheckBox checkbox;
	Button button;
	ImageButton back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_progress_activity);
		init();
		softUserTitle.setText("�û����");
		AppPackageManage apkm=new AppPackageManage(PhoneaMainSoftUser.this);
		apkm.getAppInfo();
		list=apkm.getUserAppinfo();
		final AppInfoAdapter userAppAdapter=new AppInfoAdapter(PhoneaMainSoftUser.this, list);
		userListView.setAdapter(userAppAdapter);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				// TODO Auto-generated method stub
				for (int i = 0; i < list.size(); i++) {

					if (list.get(i).getIschecked()) {
						Intent intent = new Intent();
						intent.setAction(Intent.ACTION_DELETE);
						intent.setData(Uri.parse("package:"
								+ list.get(i).getApppackage()));
						startActivity(intent);
						list.remove(i);
					}
				}
				userAppAdapter.notifyDataSetChanged();
			
			}
		});
		userListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//�ѵ�ǰ��ischecked��ֵ����check��
				boolean check = list.get(arg2).getIschecked();
				//�ѵ�ǰ��checkֵȡ�������ض�����
				list.get(arg2).setIschecked(!check);
				//ˢ������
				userAppAdapter.notifyDataSetChanged();
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
					//ȡ��ȫѡ���������ϣ��Ѽ�����ischeckedΪtrue���ó�false
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getIschecked()) {
							list.get(i).setIschecked(false);
						}
					}
				}
				//����ˢ��
				userAppAdapter.notifyDataSetChanged();
			}
			
		});
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back=new Intent(PhoneaMainSoftUser.this,SoftManagerActivity.class);
				startActivity(back);
				PhoneaMainSoftUser.this.finish();
			}
		});
	}
	
	public void init() {
		back=(ImageButton) this.findViewById(R.id.btn_homeasup_default);
		softUserTitle=(TextView) findViewById(R.id.title_textview);
		userListView=(ListView) findViewById(R.id.listview);
		checkbox = (CheckBox) findViewById(R.id.checkall);
		button = (Button) findViewById(R.id.user_progress_btn1);
	}

}
