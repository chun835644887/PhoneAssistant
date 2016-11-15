
package com.example.MyAdapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.entity.AppInfornation;
import com.example.phoneassistant.R;

public class AppInfoAdapter extends SuperAdapter<AppInfornation>{


public AppInfoAdapter(Context context, List<AppInfornation> list) {
		super(context, list);
		// TODO Auto-generated constructor stub
		
	}

class AppInfoHolder{
	ImageView appImaview;
	TextView appLabletv;
	TextView apppackage;
	TextView appVersion;
	CheckBox checkbox;
	public AppInfoHolder(View convertView) {
		// TODO Auto-generated constructor stub
		appImaview=(ImageView) convertView.findViewById(R.id.removeapp_listview_im);
		appLabletv=(TextView) convertView.findViewById(R.id.remoapp_list_tv_name);
		apppackage=(TextView) convertView.findViewById(R.id.remoapp_list_tv_package);
		appVersion=(TextView) convertView.findViewById(R.id.removeapp_list_tv_system);
		checkbox = (CheckBox) convertView.findViewById(R.id.check);	
	}
}

@Override
public View getAdapterView(int position, View convertView, ViewGroup parent) {
	// TODO Auto-generated method stub
	AppInfoHolder holder;
	if(convertView==null){
		convertView=layoutInflater.inflate(R.layout.listview,null);
		holder=new AppInfoHolder(convertView);
		convertView.setTag(holder);
	}else{
		holder=(AppInfoHolder) convertView.getTag();
	}
	holder.appLabletv.setText(list.get(position).getLable());
	holder.apppackage.setText(list.get(position).getApppackage());
	holder.appVersion.setText(list.get(position).getVersion());
	holder.appImaview.setImageDrawable(list.get(position).getDrawable());
	holder.checkbox.setChecked(list.get(position).getIschecked());
	return convertView;
}

}
