package com.example.entity;

import android.content.Context;
import android.text.format.Formatter;

public class FileLength {
	public static interface ILength{
		
		public void getFileSize(String size);
		public String setFileSize(Context context,Long length);
	}
	private FileLength(){
		
	}
	static FileLength fileLength;
	public static FileLength getFileLength() {
		if(fileLength==null){
			fileLength=new FileLength();
		}
		return fileLength;
	}
	public void setLength(Context context,Long length,ILength ilength) {
		String size=Formatter.formatFileSize(context,length );
		
	}

}
