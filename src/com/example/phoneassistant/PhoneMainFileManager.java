package com.example.phoneassistant;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.MyAdapter.FileManagerAdapter;
import com.example.MyAdapter.IFile;
import com.example.entity.FileInfo;
import com.example.util.FileTypeUtil;

public class PhoneMainFileManager extends Activity implements Runnable,IFile {

	ListView listView;
	TextView fileTitle;
	TextView findFile;
	FileManagerAdapter fileManagerAdapter;
	File file;
	Bundle bundle;
	Intent intent;
	FileTypeUtil fileTypeUtil;
	List<List<FileInfo>> list;
	TextView titleText;
	ImageButton backBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_main_filemanager);
		init();
		titleText.setText("文件管理");
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
//				list = fileTypeUtil.getList();
				Log.i("tag"," msg++++++++"+list.size());
					intent = new Intent(PhoneMainFileManager.this,
							PhoneMainFileManagerItem.class);
					bundle = new Bundle();
					/**
					 * 通过bundle把集合传值
					 */
					bundle.putSerializable("list",
							(Serializable) list.get(position));
					// Log.i("tag",list.get(position)+"");
					bundle.putInt("position", position);
					intent.putExtras(bundle);
					Log.i("tag"," msg++++++++"+list.get(position).size());
					startActivity(intent);
			}
		});

		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent back = new Intent(PhoneMainFileManager.this,
						PhoneMainActivity.class);
				startActivity(back);
				PhoneMainFileManager.this.finish();
			}
		});
	}

	// 初始化
	public void init() {
		backBtn = (ImageButton) this.findViewById(R.id.btn_homeasup_default);
		listView = (ListView) this.findViewById(R.id.phone_main_file_listview);
		fileTitle = (TextView) this.findViewById(R.id.title_textview);
		titleText = (TextView) this.findViewById(R.id.title_textview);
		findFile = (TextView) this
				.findViewById(R.id.phone_main_file_findgarbage);
		file = new File(Environment.getExternalStorageDirectory().getPath());
		fileTypeUtil = new FileTypeUtil(this,this);
		list = new ArrayList<List<FileInfo>>();
		fileManagerAdapter = new FileManagerAdapter(this, list);
		listView.setAdapter(fileManagerAdapter);
		new Thread(new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				fileTypeUtil.searchFile(file);
				list = fileTypeUtil.getList();
			}
		}).start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		fileTypeUtil.searchFile(file);
		handler.sendEmptyMessage(0);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				break;
			case 1:
				findFile.setText(Formatter.formatFileSize(PhoneMainFileManager.this,
						((Long[])msg.obj)[0]));
				fileManagerAdapter.setSize((Long[]) msg.obj);
				
				fileManagerAdapter.notifyDataSetChanged();
				break;
			default:
				break;
			}
		};
	};

	
	@Override
	public void getFileSize(Long[] size) {
		// TODO Auto-generated method stub
		Long[] size1=size;
		Message message=Message.obtain();
		message.obj=size1;
		message.what=1;
//		message.sendToTarget();
		handler.sendMessage(message);
	}

}
