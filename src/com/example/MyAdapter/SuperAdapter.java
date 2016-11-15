package com.example.MyAdapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class SuperAdapter<T> extends BaseAdapter{
	Context context;
	protected LayoutInflater layoutInflater;
	protected List<T> list;
public SuperAdapter(Context context,List<T> list) {
	// TODO Auto-generated constructor stub
    this.context=context;
    /*
     * layoutInflater=LayoutInflater.from(context);
     */
    layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.list=list;
}
public SuperAdapter(Context context) {
	// TODO Auto-generated constructor stub
	this.context=context;
	layoutInflater=LayoutInflater.from(context);
}

public void reFreshAdapter() {
	Activity activity=(Activity) context;
	activity.runOnUiThread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			notifyDataSetChanged();
		}
	});
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list==null?0:list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getAdapterView(position, convertView, parent);
	}
	public abstract View getAdapterView(int position, View convertView, ViewGroup parent);

}
