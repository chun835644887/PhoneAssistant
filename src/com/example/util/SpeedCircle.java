package com.example.util;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SpeedCircle extends View implements Runnable {

	GetMemory gm;
	int width;
	int height;
	int progress;
	double per;
	RectF rectf;
	Paint paint;
	Context context;
	Timer timer;
	TimerTask timerTask;

	// public SpeedCircle(Context context, AttributeSet attrs, int defStyle) {
	// super(context, attrs, defStyle);
	// // TODO Auto-generated constructor stub
	// }

	public SpeedCircle(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		paint = new Paint();
		gm = new GetMemory(context);
		init();
		show();
	}

	public SpeedCircle(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		paint = new Paint();
		gm = new GetMemory(context);
		init();
		// show();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// super.onDraw(canvas);
		// Log.i("yy", "cccccccccccc" + per);
		paint.setARGB(255, 200, 120, 00);
		paint.setStyle(Style.FILL);
		canvas.drawArc(rectf, -90, (float) progress, true, paint);
	}

	// 获取控件的宽高
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		width = MeasureSpec.getSize(widthMeasureSpec);
		height = MeasureSpec.getSize(heightMeasureSpec);
		rectf = new RectF(0, 0, width, height);
		// rectf=new RectF(width / 2-0,width / 2-width / 3 + 10 ,width / 2+width
		// / 3 + 10, width / 2+width / 3 + 10);
	}

	public void startCircle(double percentage) {
		Log.i("ttt", "bbbbbbbb" + percentage);
		this.per = percentage;
		show();
	}

	public void init() {

		gm.getPhoneInfo();
		gm.getAllRunMem();
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				postInvalidate();
				break;

			default:
				break;
			}
		};
	};

	private boolean isSub;
	private boolean isAdd;

	public void show() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (isSub) {
					Log.i("yy", " msg4");
					try {
						progress -= 3.6;
						Thread.sleep(50);
						postInvalidate();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// handler.sendEmptyMessage(0);

					if (progress <= 0) {
						isAdd = true;
						isSub = false;
					}
				}
				while (isAdd) {

					try {
						// Log.i("yy", progress + "yyyy" + per);
						progress += 3.6;
						Thread.sleep(50);
						postInvalidate();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// handler.sendEmptyMessage(1);
					if (progress >= per) {
						isAdd = false;
					}
				}

			}
		}).start();

	}

	public double getProgress() {
		Log.i("yy",per/3.6+ "msg");
		return per/3.6;
	}
	public boolean isSub() {
		return isSub;
	}

	public void setSub(boolean isSub) {
		this.isSub = isSub;
	}

	public boolean isAdd() {
		return isAdd;
	}

	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
