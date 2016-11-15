package com.example.entity;

import android.graphics.drawable.Drawable;

public class GarbageCleanInfo {
	private String chineseName;
	private String englishName;
	private String apkName;
	private String filePath;
	private Drawable drawable;
	private double memSize;
	private boolean isCheck;
	
	public boolean isCheck() {
		return isCheck;
	}
	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	public Drawable getDrawable() {
		return drawable;
	}
	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}
	public double getMemSize() {
		return memSize;
	}
	public void setMemSize(double memSize) {
		this.memSize = memSize;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getApkName() {
		return apkName;
	}
	public void setApkName(String apkName) {
		this.apkName = apkName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	

}
