package com.example.MyAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.format.Formatter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.entity.FileInfo;
import com.example.phoneassistant.R;

public class FileItemAdapter extends SuperAdapter<FileInfo>{

	public FileItemAdapter(Context context, List<FileInfo> list) {
		super(context, list);
		simpleDateFormat=new SimpleDateFormat(dataFormat);
		// TODO Auto-generated constructor stub
	}

	String dataFormat="yyyy-MM-dd";
	SimpleDateFormat simpleDateFormat;
	
	


	class FileItemHolder{
		RelativeLayout layout;
		CheckBox isCheck;
		ImageView fileIcon;
		TextView fileName;
		TextView fileTime;
		TextView fileLength;
		public FileItemHolder(View v) {
			// TODO Auto-generated constructor stub
			layout=(RelativeLayout) v.findViewById(R.id.phone_main_file_item_list_rela);
			isCheck=(CheckBox) v.findViewById(R.id.file_item_file_check);
			fileIcon=(ImageView) v.findViewById(R.id.file_item_file_im);
			fileName=(TextView) v.findViewById(R.id.file_item_file_name);
			fileTime=(TextView) v.findViewById(R.id.file_item_file_time);
			fileLength=(TextView) v.findViewById(R.id.file_item_file_filelength);
	
		}
	}

	@Override
	public View getAdapterView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		FileItemHolder holder;
		if(convertView==null){
			convertView=layoutInflater.inflate(R.layout.phone_main_file_item_listview,null);
			holder=new FileItemHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder=(FileItemHolder) convertView.getTag();
		}
		holder.isCheck.setChecked(list.get(position).isCheck());
		holder.isCheck.setClickable(true);
		holder.isCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				boolean check=isChecked;
				list.get(position).setCheck(!check);
			}
		});
		holder.fileName.setText(list.get(position).getFile().getName());
		holder.fileIcon.setImageResource(context.getResources().getIdentifier(list.get(position).getIcon(),"drawable",context.getPackageName()));
		holder.fileTime.setText(simpleDateFormat.format(list.get(position).getFile().lastModified()));
		holder.fileLength.setText(Formatter.formatFileSize(context,list.get(position).getFile().length()));
		holder.layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent1 = new Intent();
				intent1.setAction(Intent.ACTION_VIEW);
				intent1.setDataAndType(
						Uri.fromFile(list.get(position).getFile()),
						list.get(position).getMIME());
				context.startActivity(intent1);
			}
		});
		return convertView;
	}

}
