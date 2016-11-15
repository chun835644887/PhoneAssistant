package com.example.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;

public class SpeedView extends View implements Runnable{
Paint paint;
float mem;
int progress;
	public SpeedView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		paint=new Paint();
		paint.setColor(Color.YELLOW);
		paint.setStyle(Style.FILL);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		super.onDraw(canvas);
		RectF r=new RectF(100,100, 200,200);
		canvas.drawArc(r, -90, mem, true, paint);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	public void setMem(float mem) {
		this.mem=mem;
	}

}
