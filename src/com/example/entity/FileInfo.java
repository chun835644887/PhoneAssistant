package com.example.entity;

import java.io.File;
import java.io.Serializable;

public class FileInfo implements Serializable{
	/*
	 * 类实现接口Serializable，可以通过bundle把FileInfo的对象集合传值
	 */
	private File file;
	private String icon;
	private boolean isCheck;
	private String MIME=null;
	
	public String getMIME() {
		return MIME;
	}
	public void setMIME(String mIME) {
		this.MIME = mIME;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public boolean isCheck() {
		return isCheck;
	}
	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	

}
