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
	
	
	//GridView ��д�������������óɲ�����
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		  if(ev.getAction() == MotionEvent.ACTION_MOVE){  
	            return true;//true:��ֹ����  
	        }  
		return super.dispatchTouchEvent(ev);
	}

}
