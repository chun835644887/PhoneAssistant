package com.example.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

public class ReGridView extends GridView{

	public ReGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	
	//GridView 重写滚动方法，设置成不滚动
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		  if(ev.getAction() == MotionEvent.ACTION_MOVE){  
	            return true;//true:禁止滚动  
	        }  
		return super.dispatchTouchEvent(ev);
	}

}
