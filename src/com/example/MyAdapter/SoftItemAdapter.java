package com.example.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phoneassistant.R;

public class SoftItemAdapter extends BaseAdapter {
	LayoutInflater layoutInflater;
	String[] softItem;

	public SoftItemAdapter(String[] softItem, Context context) {
		// TODO Auto-generated constructor stub
		this.softItem = softItem;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return softItem.length;
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

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ItemHolder itemholder;
		if (arg1 == null) {
			arg1 = layoutInflater.inflate(
					R.layout.phone_main_softmanager_listview, null);
			itemholder = new ItemHolder(arg1);
			arg1.setTag(itemholder);
		} else {
			itemholder = (ItemHolder) arg1.getTag();
		}
		itemholder.softItem.setText(softItem[arg0]);
		itemholder.softIm.setImageResource(R.drawable.ic_arrows_right);
		return arg1;
	}

	class ItemHolder {
		View view;
		TextView softItem;
		ImageView softIm;

		public ItemHolder(View view) {
			// TODO Auto-generated constructor stub
			this.view = view;
			softItem = (TextView) view
					.findViewById(R.id.phone_main_softmanager_listview_tv);
			softIm = (ImageView) view
					.findViewById(R.id.phone_main_softmanager_listview_im);
		}

	}

}
