package com.example.MyAdapter;

import cn.jpush.android.api.JPushInterface;

import com.example.phoneassistant.MyTools;
import com.example.phoneassistant.PhoneSetting;
import com.example.phoneassistant.R;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingAdapter extends BaseAdapter {

	String[] str = { "开机启动", "通知图标", "推送消息", "帮助说明", "意见反馈", "好友分享", "版本更新",
			"关于我们" };
	Context context;
	String s;
	MyTools myTools;
	LayoutInflater layoutInflater;

	public SettingAdapter(Context context) {

		this.context = context;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return str.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final SettingHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(
					R.layout.phone_main_setting_listview, null);
			holder = new SettingHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (SettingHolder) convertView.getTag();
		}
		holder.settingText.setText(str[position]);
		if(position<3){
		holder.settingImage.setClickable(true);
		myTools = new MyTools(context);
		s = myTools.getString("state" + position);
		if ("0".equals(s)) {
			holder.settingImage
					.setImageResource(R.drawable.check_switch_on);
		} else {
			Log.i("tag","");
			holder.settingImage
					.setImageResource(R.drawable.check_switch_off);
		}}else{
			holder.settingImage.setClickable(false);
			holder.settingImage.setImageResource(R.drawable.ic_arrows_right);
		}
		
//		holder.settingImage.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				switch (position) {
//				case 2:
//					if ("0".equals(s)) {
//						holder.settingImage
//								.setImageResource(R.drawable.check_switch_off);
//						myTools.saveString("state" + position, "1");
//						JPushInterface.stopPush(context);
//					} else {
//						holder.settingImage
//								.setImageResource(R.drawable.check_switch_on);
//						myTools.saveString("state"+position, "0");
//						JPushInterface.resumePush(context);
//					}
//					break;
//				case 1:
//					if("0".equals(s)){
//						
//					}
//					break;
//				case 0:
//					if ("0".equals(s)) {
//						holder.settingImage
//						.setImageResource(R.drawable.check_switch_off);
//				myTools.saveString("state" + position, "1");
//						context.getPackageManager()
//								.setComponentEnabledSetting(
//										new ComponentName(context
//												.getPackageName(),
//												OpenStartReceiver.class
//														.getName()),
//										PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
//										PackageManager.DONT_KILL_APP);
//					} else {
//						holder.settingImage
//						.setImageResource(R.drawable.check_switch_on);
//				myTools.saveString("state"+position, "0");
//						context.getPackageManager()
//								.setComponentEnabledSetting(
//										new ComponentName(context
//												.getPackageName(),
//												OpenStartReceiver.class
//														.getName()),
//										PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//										PackageManager.DONT_KILL_APP);
//					}
//					break;
//
//				default:
//					break;
//				}
//			}
//		});
		
//		
//		switch (position) {
//		
//		case value:
//			
//			break;
//
//		default:
//			break;
//		}
		
//		if (position < 3) {
//
//			holder.settingImage.setClickable(true);
//			myTools = new MyTools(context);
//			s = myTools.getString("state" + position);
//			if ("0".equals(s)) {
//				holder.settingImage
//						.setImageResource(R.drawable.check_switch_on);
//			} else {
//				Log.i("tag","");
//				holder.settingImage
//						.setImageResource(R.drawable.check_switch_off);
//			}
//			holder.settingImage.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					switch (position) {
//					case 2:
//						if ("0".equals(s)) {
//							holder.settingImage
//									.setImageResource(R.drawable.check_switch_off);
//							myTools.saveString("state" + position, "1");
//							JPushInterface.stopPush(context);
//						} else {
//							holder.settingImage
//									.setImageResource(R.drawable.check_switch_on);
//							myTools.saveString("state"+position, "0");
//							JPushInterface.resumePush(context);
//						}
//						break;
//					case 1:
//						if("0".equals(s)){
//							
//						}
//						break;
//					case 0:
//						if ("0".equals(s)) {
//							holder.settingImage
//							.setImageResource(R.drawable.check_switch_off);
//					myTools.saveString("state" + position, "1");
//							context.getPackageManager()
//									.setComponentEnabledSetting(
//											new ComponentName(context
//													.getPackageName(),
//													OpenStartReceiver.class
//															.getName()),
//											PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
//											PackageManager.DONT_KILL_APP);
//						} else {
//							holder.settingImage
//							.setImageResource(R.drawable.check_switch_on);
//					myTools.saveString("state"+position, "0");
//							context.getPackageManager()
//									.setComponentEnabledSetting(
//											new ComponentName(context
//													.getPackageName(),
//													OpenStartReceiver.class
//															.getName()),
//											PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//											PackageManager.DONT_KILL_APP);
//						}
//						break;
//
//					default:
//						break;
//					}
//				}
//			});
//
//		} else {
		// holder.settingImage.setImageResource(R.drawable.ic_arrows_right);
//		}
		return convertView;
	}

	class SettingHolder {
		TextView settingText;
		ImageView settingImage;

		public SettingHolder(View v) {
			// TODO Auto-generated constructor stub
			settingText = (TextView) v.findViewById(R.id.setting_list_text);
			settingImage = (ImageView) v.findViewById(R.id.setting_list_image);
		}
	}
}
