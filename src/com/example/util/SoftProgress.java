package com.example.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class SoftProgress extends View{

	Paint paint;
	public SoftProgress(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}
	public SoftProgress(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	public SoftProgress(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	
	@Override
	protected synchronized void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawCircle(150,200,100, paint);
	}
	public void init() {
		paint=new Paint();
		paint.setColor(Color.BLUE);
		paint.setStyle(Style.FILL);
		paint.setStrokeWidth(3);
		
	}

}
