package com.example.MyAdapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.entity.TableColumnInfo;
import com.example.phoneassistant.R;
import com.example.util.DBMyTools;

public class CommunicationAdapter extends SuperAdapter<TableColumnInfo>{
	
	DBMyTools dbMyTools;
	String table;
	public CommunicationAdapter(Context context, List<TableColumnInfo> list) {
		super(context, list);
		// TODO Auto-generated constructor stub
	}
	
	public CommunicationAdapter(Context context, List<TableColumnInfo> list,String table) {
		super(context, list);
		this.list=list;
		this.context=context;
		this.table=table;
	}
	public void name() {
		
	}
	class ComHolder {
		TextView name;
		TextView number;

		public ComHolder(View v) {
			// TODO Auto-generated constructor stub
			name = (TextView) v
					.findViewById(R.id.phone_main_communication_listview_name);
			number = (TextView) v
					.findViewById(R.id.phone_main_communication_listview_number);	
		}
	}


	@Override
	public View getAdapterView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ComHolder comHolder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.communication1_action,
					null);
			comHolder = new ComHolder(convertView);
			convertView.setTag(comHolder);
		} else {
			comHolder = (ComHolder) convertView.getTag();
		}
		comHolder.name.setText(list.get(position).getName());
		comHolder.number.setText(list.get(position).getNumber());
		return convertView;
	}

}
