package com.example.phoneassistant;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MyTools {
	
	SharedPreferences sharedPreferences;
	Editor editor ;
	public MyTools(Context context) {		
		sharedPreferences = context.getSharedPreferences("text",Context.MODE_PRIVATE);
	}
	
	public String getString(String key) {
		String a=sharedPreferences.getString(key, "0");
		return a;
		}
	public void saveString(String key,String value) {
		editor=sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

}
