package com.example.MyAdapter;

import java.util.List;

import android.content.Context;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.entity.FileInfo;
import com.example.phoneassistant.R;

public class FileManagerAdapter extends BaseAdapter{

	Context context;
	String[] itme={"全部","文档","视频","音频","图像","压缩包","程序包"};
	LayoutInflater layoutInflater;
	List<List<FileInfo>> list;
	Long[] size;
	public List<List<FileInfo>> getList() {
		return list;
	}
	public void setList(List<List<FileInfo>> list) {
		this.list = list;
	}
	public FileManagerAdapter(Context context,List<List<FileInfo>> list) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.list=list;
		layoutInflater=LayoutInflater.from(context);
	}
	
	public void setSize(Long[] size) {
		this.size=size;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub	
		/*
		 * 判断传size是否为空
		 */
		if(size==null){
			return 0;
		}
		return size.length;
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
		FileHolder holder;
		if(convertView==null){
			convertView=layoutInflater.inflate(R.layout.phone_main_file_listview, null);
			holder=new FileHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder=(FileHolder) convertView.getTag();
		}
		holder.fileType.setText(itme[position]);
		holder.fileLength.setText(Formatter.formatFileSize(context, size[position]));
		holder.fileImage.setImageResource(R.drawable.ic_arrows_right);
		return convertView;
	}
	class FileHolder{
		TextView fileType;
		TextView fileLength;
		ImageView fileImage;
		public FileHolder(View v) {
			fileType=(TextView) v.findViewById(R.id.phone_main_file_list_type);
			fileLength=(TextView) v.findViewById(R.id.phone_main_file_list_size);
			fileImage=(ImageView) v.findViewById(R.id.phone_main_file_list_im);
		}
	}

}
