package com.example.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.MyAdapter.BatteryBroadCast;

public class BatteryHead extends View implements Runnable{
Paint paint;
	public BatteryHead(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint=new Paint();
		paint.setStyle(Style.FILL);
		paint.setColor(Color.GRAY);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		RectF r=new RectF(0, 70, 10,120);
		canvas.drawRect(r, paint);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(BatteryBroadCast.level==100){
				paint.setColor(Color.GREEN);
				postInvalidate();
				
			}
		}
		
	}
	
	
	

}
