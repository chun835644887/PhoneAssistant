package com.example.phoneassistant;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.MyAdapter.CommunicationAdapter;
import com.example.database.ITable;
import com.example.entity.TableColumnInfo;
import com.example.util.DBMyTools;

public class PhoneMainCommunicationFood extends Activity implements Runnable{

ListView listvView;
TextView textView;
TextView titleTV;
CommunicationAdapter communicationAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_main_communication_item_info);
		init();
		Intent intent=this.getIntent();
		Bundle bundle=intent.getExtras();
		switch (bundle.getInt("com")) {
		case 0:
			titleTV.setText("���͵绰");
			table=ITable.TABLE1;
			new Thread(PhoneMainCommunicationFood.this).start();
//			communicationAdapter=new CommunicationAdapter(this, ITable.TABLE1);
//			listvView.setAdapter(communicationAdapter);
			listvView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					//��ȡitem�ĵ绰����
					textView=(TextView) arg1.findViewById(R.id.phone_main_communication_listview_number);
					//�绰ת���ַ���
					String string = textView.getText().toString();
					//��绰
					Intent intent0=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+string));
					PhoneMainCommunicationFood.this.startActivity(intent0);
				}
			});
			break;
		case 1:
			titleTV.setText("��������");
			table=ITable.TABLE2;
			new Thread(PhoneMainCommunicationFood.this).start();
//			communicationAdapter=new CommunicationAdapter(this, ITable.TABLE2);
//			listvView.setAdapter(communicationAdapter);
			listvView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					//��ȡitem�ĵ绰����
					textView=(TextView) arg1.findViewById(R.id.phone_main_communication_listview_number);
					//�绰ת���ַ���
					String string = textView.getText().toString();
					//��绰
					Intent intent1=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+string));
					PhoneMainCommunicationFood.this.startActivity(intent1);
				}
			});
			break;
		case 2:
			table=ITable.TABLE3;
			new Thread(PhoneMainCommunicationFood.this).start();
			listvView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					titleTV.setText("��Ӫ��");
					//��ȡitem�ĵ绰����
					textView=(TextView) arg1.findViewById(R.id.phone_main_communication_listview_number);
					//�绰ת���ַ���
					String string = textView.getText().toString();
					//��绰
					Intent intent2=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+string));
					PhoneMainCommunicationFood.this.startActivity(intent2);
				}
			});
			break;
		case 3:
			titleTV.setText("��ݷ���");
			table=ITable.TABLE4;
			new Thread(PhoneMainCommunicationFood.this).start();
//			communicationAdapter=new CommunicationAdapter(this, ITable.TABLE4);
//			listvView.setAdapter(communicationAdapter);
			listvView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					//��ȡitem�ĵ绰����
					textView=(TextView) arg1.findViewById(R.id.phone_main_communication_listview_number);
					//�绰ת���ַ���
					String string = textView.getText().toString();
					//��绰
					Intent intent3=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+string));
					PhoneMainCommunicationFood.this.startActivity(intent3);
				}
			});
			break;
		case 4:
			titleTV.setText("��Ʊ�Ƶ�");
			table=ITable.TABLE5;
			new Thread(PhoneMainCommunicationFood.this).start();
			listvView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					//��ȡitem�ĵ绰����
					textView=(TextView) arg1.findViewById(R.id.phone_main_communication_listview_number);
					//�绰ת���ַ���
					String string = textView.getText().toString();
					//��绰
					Intent intent4=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+string));
					PhoneMainCommunicationFood.this.startActivity(intent4);
				}
			});
			break;
		case 5:
			titleTV.setText("����֤ȯ");
			table=ITable.TABLE6;
			new Thread(PhoneMainCommunicationFood.this).start();
			listvView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					//��ȡitem�ĵ绰����
					textView=(TextView) arg1.findViewById(R.id.phone_main_communication_listview_number);
					//�绰ת���ַ���
					String string = textView.getText().toString();
					//��绰
					Intent intent5=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+string));
					PhoneMainCommunicationFood.this.startActivity(intent5);
				}
			});
			break;
		case 6:
			titleTV.setText("���շ���");
			table=ITable.TABLE7;
			new Thread(PhoneMainCommunicationFood.this).start();
			listvView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					//��ȡitem�ĵ绰����
					textView=(TextView) arg1.findViewById(R.id.phone_main_communication_listview_number);
					//�绰ת���ַ���
					String string = textView.getText().toString();
					//��绰
					Intent intent6=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+string));
					PhoneMainCommunicationFood.this.startActivity(intent6);
				}
			});
			break;
		case 7:
			titleTV.setText("�ۺ����");
			table=ITable.TABLE8;
			new Thread(PhoneMainCommunicationFood.this).start();
			listvView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					//��ȡitem�ĵ绰����
					textView=(TextView) arg1.findViewById(R.id.phone_main_communication_listview_number);
					//�绰ת���ַ���
					String string = textView.getText().toString();
					//��绰
					Intent intent7=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+string));
					PhoneMainCommunicationFood.this.startActivity(intent7);
				}
			});
			break;
		default:
			break;
		}
		
	}
	DBMyTools dbMyTools;
	List<TableColumnInfo> list;
	String table;
	public void init() {
		dbMyTools=new DBMyTools(this);
		list=new ArrayList<TableColumnInfo>();
		listvView=(ListView) this.findViewById(R.id.phone_main_communication_item);
		titleTV=(TextView) this.findViewById(R.id.title_textview);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		list = dbMyTools.getInfo(table);
		communicationAdapter=new CommunicationAdapter(PhoneMainCommunicationFood.this,list,table);
		handler.sendEmptyMessage(0);
	}
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				listvView.setAdapter(communicationAdapter);
				break;

			default:
				break;
			}
		};
	};
}
