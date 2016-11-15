package com.example.MyAdapter;

import java.util.List;

import com.example.entity.GarbageCleanInfo;
import com.example.phoneassistant.R;

import android.content.Context;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class GarbageCleanAdapter extends BaseAdapter{
LayoutInflater inflater;
Context context;
	List<GarbageCleanInfo> list;
	public GarbageCleanAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
		inflater=LayoutInflater.from(context);
	}
	
	public void setList(List<GarbageCleanInfo> list) {
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
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
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		GarbagerHolder holder;
		if(convertView==null){
			convertView=inflater.inflate(R.layout.phone_main_garbage_listview, null);
			holder=new GarbagerHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder=(GarbagerHolder) convertView.getTag();
		}
		holder.garImage.setImageDrawable(list.get(position).getDrawable());
		holder.garNameText.setText(list.get(position).getChineseName());
		holder.garbagerLength.setText(Formatter.formatFileSize(context, (long) list.get(position).getMemSize()));
		holder.garCheck.setChecked(list.get(position).isCheck());
		return convertView;
	}
	class GarbagerHolder{
		CheckBox garCheck;
		ImageView garImage;
		TextView garNameText;
		TextView garbagerLength;
		public GarbagerHolder(View v) {
			// TODO Auto-generated constructor stub
			garCheck=(CheckBox) v.findViewById(R.id.phone_main_garbage_list_check);
			garImage=(ImageView) v.findViewById(R.id.phone_main_garbage_list_im);
			garNameText=(TextView) v.findViewById(R.id.phone_main_garbage_list_name);
			garbagerLength=(TextView) v.findViewById(R.id.phone_main_garbage_list_mem);
		}
	}

}
