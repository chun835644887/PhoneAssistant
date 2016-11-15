package com.example.phoneassistant;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Formatter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.MyAdapter.GarbageCleanAdapter;
import com.example.database.ITable;
import com.example.entity.GarbageCleanInfo;
import com.example.util.DBMyTools;

public class PhoneMainGarbageClean extends Activity {
	ListView listView;
	TextView garbageTitle;
	Button garbageClean;
	TextView allGarbage;
	CheckBox allCheckBox;
	DBMyTools dbMyTools;
	GarbageCleanAdapter garbageCleanAdapter;
	List<GarbageCleanInfo> list;
	ImageButton back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_main_garbage);
		init();
		garbageTitle.setText("垃圾清理");
		dbMyTools = new DBMyTools(this);
		list = dbMyTools.getGarbageIm(dbMyTools.getCleanApp(ITable.SOFTDETAIL));
		list = dbMyTools.getGarbageSize(list);
		garbageCleanAdapter = new GarbageCleanAdapter(this);
		garbageCleanAdapter.setList(list);
		allGarbage.setText(dbMyTools.getFileSize());
		listView.setAdapter(garbageCleanAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				boolean check = list.get(position).isCheck();
				list.get(position).setCheck(!check);
				garbageCleanAdapter.notifyDataSetChanged();
			}
		});

		allCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).isCheck() == false) {
							list.get(i).setCheck(true);
							// garbageCleanAdapter.notifyDataSetChanged();
						}
					}
				} else {
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).isCheck() == true) {
							list.get(i).setCheck(false);
						}
					}
				}
				garbageCleanAdapter.notifyDataSetChanged();
			}
		});
		garbageClean.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Long allFind =(long) 0;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).isCheck()) {
						String path = Environment.getExternalStorageDirectory()
								.getPath() + list.get(i).getFilePath();
						File file = new File(path);
						dbMyTools.delFile(file);
							list.remove(i);
							i--;
					} else {
//把未选中的***累加
						allFind+=(long)list.get(i).getMemSize();
					}
					// dbMyTools.g
//					list.get(i).setCheck(false);
				}
				// dbMyTools.getGarbageSize(list);
				// allGarbage.setText(dbMyTools.getFileSize());
				allGarbage.setText(Formatter.formatFileSize(PhoneMainGarbageClean.this,allFind));
				garbageCleanAdapter.notifyDataSetChanged();
			}
		});
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent intent=new Intent(PhoneMainGarbageClean.this,PhoneMainActivity.class);
			startActivity(intent);
			}
		});
	}

	public void init() {
		back=(ImageButton) this.findViewById(R.id.btn_homeasup_default);
		listView = (ListView) this
				.findViewById(R.id.phone_main_garbage_listview);
		garbageTitle = (TextView) this.findViewById(R.id.title_textview);
		garbageClean = (Button) this
				.findViewById(R.id.phone_main_garbage_clean);
		allCheckBox = (CheckBox) this
				.findViewById(R.id.phone_main_garbage_allcheck);
		allGarbage = (TextView) this
				.findViewById(R.id.phone_main_garbage_findgarbage);
	}

}
