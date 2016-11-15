package com.example.phoneassistant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class PhoneMainCommunicationActivity extends Activity{
	TextView titleTV;
	TextView food;
	TextView publicService;
	TextView carrieroperator;
	TextView mailService;
	TextView airHotel;
	TextView bankSecurities;
	TextView insuranceServices;
	TextView saleService;
	Intent intent;
	Bundle bundle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.communication_activity);
		init();
		food.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				Intent intentFood=new Intent(PhoneMainCommunicationActivity.this,PhoneMainCommunicationFood.class);
//				startActivity(intentFood);
				intent =new Intent();
				intent.setClass(PhoneMainCommunicationActivity.this,PhoneMainCommunicationFood.class);
				bundle=new Bundle();
				bundle.putInt("com", 0);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		publicService.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				intent =new Intent();
				intent.setClass(PhoneMainCommunicationActivity.this,PhoneMainCommunicationFood.class);
				bundle=new Bundle();
				bundle.putInt("com", 1);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	carrieroperator.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				intent =new Intent();
				intent.setClass(PhoneMainCommunicationActivity.this,PhoneMainCommunicationFood.class);
				bundle=new Bundle();
				bundle.putInt("com", 2);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	mailService.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			intent =new Intent();
			intent.setClass(PhoneMainCommunicationActivity.this,PhoneMainCommunicationFood.class);
			bundle=new Bundle();
			bundle.putInt("com", 3);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	});
	airHotel.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			intent =new Intent();
			intent.setClass(PhoneMainCommunicationActivity.this,PhoneMainCommunicationFood.class);
			bundle=new Bundle();
			bundle.putInt("com", 4);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	});
	bankSecurities.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			intent =new Intent();
			intent.setClass(PhoneMainCommunicationActivity.this,PhoneMainCommunicationFood.class);
			bundle=new Bundle();
			bundle.putInt("com", 5);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	});
	insuranceServices.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			intent =new Intent();
			intent.setClass(PhoneMainCommunicationActivity.this,PhoneMainCommunicationFood.class);
			bundle=new Bundle();
			bundle.putInt("com", 6);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	});
	publicService.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			intent =new Intent();
			intent.setClass(PhoneMainCommunicationActivity.this,PhoneMainCommunicationFood.class);
			bundle=new Bundle();
			bundle.putInt("com", 7);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	});
	}
	public void init() {
		titleTV=(TextView) this.findViewById(R.id.title_textview);
		titleTV.setText("通讯大全");
		food=(TextView) this.findViewById(R.id.com_food_phone);
		publicService=(TextView) this.findViewById(R.id.com_public_service);
		carrieroperator=(TextView) this.findViewById(R.id.com_carrieroperator);
		mailService=(TextView) this.findViewById(R.id.com_fast_mail_service);
		airHotel=(TextView) this.findViewById(R.id.com_air_ticket_hotel);
		bankSecurities=(TextView) this.findViewById(R.id.com_bank_securities);
		insuranceServices=(TextView) this.findViewById(R.id.com_Insurance_services);
		saleService=(TextView) this.findViewById(R.id.com_sale_service);
	}

}
