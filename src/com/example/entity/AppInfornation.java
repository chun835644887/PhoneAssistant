package com.example.entity;

import android.graphics.drawable.Drawable;

public class AppInfornation {
	private String lable;
	private String version;
	private String apppackage;
	Drawable drawable;
	private Boolean ischecked;
	private boolean isUser;
	private String runMemory;
	
	public String getRunMemory() {
		return runMemory;
	}
	public void setRunMemory(String runMemory) {
		this.runMemory = runMemory;
	}
	public boolean isUser() {
		return isUser;
	}
	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getApppackage() {
		return apppackage;
	}
	public void setApppackage(String apppackage) {
		this.apppackage = apppackage;
	}
	public Drawable getDrawable() {
		return drawable;
	}
	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}
	public Boolean getIschecked() {
		return ischecked;
	}
	public void setIschecked(Boolean ischecked) {
		this.ischecked = ischecked;
	}
	
	
	

}
