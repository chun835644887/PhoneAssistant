package com.example.phoneassistant;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.example.MyAdapter.JPushReceiver;
import com.example.MyAdapter.OpenStartReceiver;
import com.example.MyAdapter.SettingAdapter;

public class PhoneSetting extends Activity {
	ListView listView;
	TextView titleText;
	SettingAdapter adapter;
	MyTools myTools;
	JPushReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_main_setting);
		init();
		titleText.setText("����");
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				myTools = new MyTools(PhoneSetting.this);
				ImageView settingIm;
				boolean isOn = false;
				String a = null;
				a = myTools.getString("state" + position);
				isOn = "0".equals(a);
				switch (position) {
				case 0:
					settingIm = (ImageView) view
							.findViewById(R.id.setting_list_image);
					if (isOn) {
						settingIm.setImageResource(R.drawable.check_switch_off);
						myTools.saveString("state" + position, "1");
					} else {
						settingIm.setImageResource(R.drawable.check_switch_on);
						myTools.saveString("state" + position, "0");
					}
					setOpenStart(isOn);
					break;
				case 2:
					settingIm = (ImageView) view
							.findViewById(R.id.setting_list_image);
					if (isOn) {
						settingIm.setImageResource(R.drawable.check_switch_off);
						JPushInterface.stopPush(PhoneSetting.this);
						myTools.saveString("state" + position, "1");
					} else {
						settingIm.setImageResource(R.drawable.check_switch_on);
						JPushInterface.resumePush(PhoneSetting.this);
						myTools.saveString("state" + position, "0");
					}
					break;
				case 1:
					settingIm = (ImageView) view
							.findViewById(R.id.setting_list_image);
					if (isOn) {
						settingIm.setImageResource(R.drawable.check_switch_off);
						myTools.saveString("state" + position, "1");
					} else {
						settingIm.setImageResource(R.drawable.check_switch_on);
						myTools.saveString("state" + position, "0");
					}
					Notification notification=new Notification();
					notification.defaults=Notification.DEFAULT_ALL;

					break;

				case 3:

					break;
				case 4:

					break;
				case 5:
					showShare();
					break;

				default:
					break;
				}
			}
		});
	}

	public void init() {
		listView = (ListView) this.findViewById(R.id.phone_main_setting);
		titleText = (TextView) this.findViewById(R.id.title_textview);
		adapter = new SettingAdapter(this);
	}

	public void setOpenStart(boolean isOpen) {
		if (isOpen) {
			getPackageManager().setComponentEnabledSetting(
					new ComponentName(getPackageName(),
							OpenStartReceiver.class.getName()),
					PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
					PackageManager.DONT_KILL_APP);
		} else {
			getPackageManager().setComponentEnabledSetting(
					new ComponentName(getPackageName(),
							OpenStartReceiver.class.getName()),
					PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
					PackageManager.DONT_KILL_APP);
		}
	}

	private void showShare() {
		ShareSDK.initSDK(this);
		OnekeyShare oks = new OnekeyShare();
		// �ر�sso��Ȩ
		oks.disableSSOWhenAuthorize();

		// ����ʱNotification��ͼ������� 2.5.9�Ժ�İ汾�����ô˷���
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
		oks.setTitle("����");
		// titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
		oks.setTitleUrl("http://sharesdk.cn");
		// text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
		oks.setText("���Ƿ����ı�");
		// ��������ͼƬ������΢����������ͼƬ��Ҫͨ����˺�����߼�д��ӿڣ�������ע�͵���������΢��
		oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
		// imagePath��ͼƬ�ı���·����Linked-In�����ƽ̨��֧�ִ˲���
		// oks.setImagePath("/sdcard/test.jpg");//ȷ��SDcard������ڴ���ͼƬ
		// url����΢�ţ��������Ѻ�����Ȧ����ʹ��
		oks.setUrl("http://sharesdk.cn");
		// comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ��
		oks.setComment("���ǲ��������ı�");
		// site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ��
		oks.setSite(getString(R.string.app_name));
		// siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ��
		oks.setSiteUrl("http://sharesdk.cn");

		// ��������GUI
		oks.show(this);
	}
}
