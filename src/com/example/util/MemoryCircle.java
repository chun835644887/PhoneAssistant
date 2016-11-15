package com.example.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MemoryCircle extends View implements Runnable {

	double percentage;
	GetMemory gm;
	Paint paint;
	public double free;
	public double allMemory;
	double size;
	double progress;
	Context context;
	double outMemory;

	public MemoryCircle(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MemoryCircle(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		gm = new GetMemory(context);
		this.context=context;
		this.paint = new Paint();
		memorySize();
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		percentage = (outMemory / (outMemory + allMemory)) * 360;
		System.out.println(outMemory + "=====" + allMemory + "----"
				+ percentage);
		// DecimalFormat df = new DecimalFormat(".###");
		// String s = df.format(outMemory+"-----");
		// String ss=df.format(allMemory+"=-=-=-=-");
		// System.out.println(s+"sssssssss"+ss);

		paint.setStyle(Style.FILL);
		paint.setColor(Color.BLUE);
		RectF rf1 = new RectF(310, 90, 320, 100);
		canvas.drawRect(rf1, paint);
		canvas.drawText("手机内置内存", 350, 100, paint);
		canvas.drawText("手机外置内存", 350, 170, paint);
		canvas.drawCircle(150, 150, 100, paint);
		RectF rf = new RectF(50, 50, 250, 250);
		paint.setColor(Color.RED);
		RectF rf2 = new RectF(310, 160, 320, 170);
		canvas.drawRect(rf2, paint);
		canvas.drawArc(rf, 0, (float) progress, true, paint);
	}

	public void memorySize() {

		gm.memorySize();
		this.free=gm.free;
		this.allMemory=gm.allMemory;
		this.outMemory=gm.outMemory;
		percentage=(outMemory/(outMemory+allMemory))*360;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (progress < percentage) {
			try {
				progress++;
				Thread.sleep(50);
				postInvalidate();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
