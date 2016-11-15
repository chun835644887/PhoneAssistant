package com.example.MyAdapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.entity.AppInfornation;
import com.example.phoneassistant.R;

public class PhoneMainSpeedAdapter extends BaseAdapter{

	
	List<AppInfornation> appList;
	LayoutInflater layoutInflater;
	public PhoneMainSpeedAdapter(Context context,List<AppInfornation>appList) {
		// TODO Auto-generated constructor stub
		layoutInflater=LayoutInflater.from(context);
		this.appList=appList;
	}
	
	
	public void setList(List<AppInfornation>list) {
		this.appList=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
//		Log.i("tag", appList.size()+"");
		return appList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setdata(List<AppInfornation> appList){
		this.appList=appList;
	}
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		RunProgress runPro;
		if(arg1==null){
			arg1=layoutInflater.inflate(R.layout.listview,null);
			runPro=new RunProgress(arg1);
			arg1.setTag(runPro);
		}else{
			runPro=(RunProgress) arg1.getTag();
		}
		runPro.runLabletv.setText(appList.get(arg0).getLable());
		runPro.runpackage.setText("内存："+appList.get(arg0).getRunMemory());
		runPro.runImaview.setImageDrawable(appList.get(arg0).getDrawable());
		if(appList.get(arg0).isUser()){
			System.out.println(appList.get(arg0).isUser()+"===============-----");
			runPro.category.setText("用户进程");
		}else{
			runPro.category.setText("系统进程");
		}
		runPro.checkbox.setChecked(appList.get(arg0).getIschecked());
		return arg1;
	}
	
	class RunProgress{
		View v;
		CheckBox checkbox;
		ImageView runImaview;
		TextView runLabletv;
		TextView runpackage;
		TextView category;
		public RunProgress(View v) {
			// TODO Auto-generated constructor stub
			runImaview=(ImageView) v.findViewById(R.id.removeapp_listview_im);
			runLabletv=(TextView) v.findViewById(R.id.remoapp_list_tv_name);
			runpackage=(TextView) v.findViewById(R.id.remoapp_list_tv_package);
			category=(TextView) v.findViewById(R.id.removeapp_list_tv_system);
			checkbox=(CheckBox) v.findViewById(R.id.check);
			
		}
	}

}
