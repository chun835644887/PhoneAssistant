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
		titleText.setText("设置");
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
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle("分享到");
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://sharesdk.cn");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("我是分享文本");
		// 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
		oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://sharesdk.cn");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://sharesdk.cn");

		// 启动分享GUI
		oks.show(this);
	}
}
