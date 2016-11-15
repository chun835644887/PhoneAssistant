package com.example.phoneassistant;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.MyAdapter.FileItemAdapter;
import com.example.entity.FileInfo;

public class PhoneMainFileManagerItem extends Activity {

	ListView listView;
	TextView titleText;
	Button delFile;
	ImageButton backBtn;
	Intent intent;
	Bundle bundle;
	List<FileInfo> list;
	FileItemAdapter fileItemAdapter;
	CheckBox isCheck;
	RelativeLayout layout;
	View view;

	// int position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_main_file_item_list);
		init();
		intent = this.getIntent();
		bundle = intent.getExtras();
		list = (List<FileInfo>) bundle.getSerializable("list");
		Log.i("tag"," msg"+list.size());
		Log.i("tag","position"+bundle.getInt("position"));
		fileItemAdapter =new FileItemAdapter(this,list);
//		fileItemAdapter.setList(list);
		switch (bundle.getInt("position")) {
		case 0:
			Log.i("tag"," mddsdfsdfssg"+list.size());
			titleText.setText("全部文件");
			listView.setAdapter(fileItemAdapter);
			break;
		case 1:
			titleText.setText("文档管理");
			listView.setAdapter(fileItemAdapter);
			break;
		case 2:
			titleText.setText("视频管理");
			listView.setAdapter(fileItemAdapter);
			break;
		case 3:
			titleText.setText("音频管理");
			listView.setAdapter(fileItemAdapter);
			break;
		case 4:
			titleText.setText("图像管理");
			listView.setAdapter(fileItemAdapter);
			break;
		case 5:
			titleText.setText("压缩包管理");
			listView.setAdapter(fileItemAdapter);
			break;
		case 6:
			titleText.setText("程序包管理");
			listView.setAdapter(fileItemAdapter);
			break;

		default:
			break;
		}
		// layout.setOnClickListener(new)
		delFile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).isCheck()) {
						list.get(i).getFile().delete();
						list.remove(i);
					}
				}
				fileItemAdapter.notifyDataSetChanged();
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub
			}
		});
		delFile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).isCheck()) {
						// list.get(i).getFile().delete();
						list.remove(i);
					}
				}
				fileItemAdapter.notifyDataSetChanged();
			}
		});
		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent2 = new Intent(PhoneMainFileManagerItem.this,
						PhoneMainFileManager.class);
				startActivity(intent2);
				PhoneMainFileManagerItem.this.finish();
			}
		});
	}

	public void init() {
		view = getLayoutInflater().inflate(
				R.layout.phone_main_file_item_listview, null);
		layout = (RelativeLayout) view
				.findViewById(R.id.phone_main_file_item_list_rela);
		listView =(ListView) PhoneMainFileManagerItem.this.findViewById(R.id.phone_main_file_item_listview);
		isCheck = (CheckBox) view.findViewById(R.id.file_item_file_check);
		titleText = (TextView) PhoneMainFileManagerItem.this.findViewById(R.id.title_textview);
		delFile = (Button) PhoneMainFileManagerItem.this.findViewById(R.id.phone_main_file_item_del);
		backBtn = (ImageButton) this.findViewById(R.id.btn_homeasup_default);
		
	}
}
