package com.example.phoneassistant;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Title_Button extends LinearLayout {

	 TextView titleTV;
	public Title_Button(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.title_activity, this);
		titleTV=(TextView) findViewById(R.id.title_textview);
		Button homeback = (Button) findViewById(R.id.btn_homeasup_default);
		homeback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}

}
