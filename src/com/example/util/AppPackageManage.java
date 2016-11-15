package com.example.util;

import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Debug.MemoryInfo;
import android.text.format.Formatter;

import com.example.entity.AppInfornation;

public class AppPackageManage {
	Context context;
	public PackageManager packagemanager;
	private List<PackageInfo> listApp = new ArrayList<PackageInfo>();
	ActivityManager activityManager;
	private List<AppInfornation> allAppinfo = new ArrayList<AppInfornation>();
	private List<AppInfornation> userAppinfo = new ArrayList<AppInfornation>();
	List<AppInfornation> sysAppinfo = new ArrayList<AppInfornation>();
	// 进程集合
	List<RunningAppProcessInfo> allRunProcess;
	List<AppInfornation> userRunProcess;
	List<AppInfornation> systemRunProcess;
	List<AppInfornation> allProcess = new ArrayList<AppInfornation>();;

	public AppPackageManage(Context context) {
		this.context = context;
		// 直接获取一个包管理器
		packagemanager = context.getPackageManager();
		// 获得已存在的应用，并返回一个集合
		listApp = packagemanager
				.getInstalledPackages(PackageManager.GET_ACTIVITIES);
		// 通过服务方法获取一个activityManager对象
		activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		// getAppInfo();
		// getRunProgress();
	}

	public void getAppInfo() {

		// 定义三个集合
		// 一个接收系统的app，一个接收用户的app，一个接收所有的app
		for (int i = 0; i < listApp.size(); i++) {
			// 定义一个PackageInfo接收遍历集合的元素
			PackageInfo pki = listApp.get(i);
			String apppackage = pki.packageName;
			String version = pki.versionName;
			Drawable drawable = pki.applicationInfo.loadIcon(packagemanager);
			String lable = pki.applicationInfo.loadLabel(packagemanager)
					.toString();
			// 创建一个对象，把app的信息保存在对象的属性中
			AppInfornation appinfo = new AppInfornation();
			appinfo.setApppackage(apppackage);
			appinfo.setDrawable(drawable);
			appinfo.setLable(lable);
			appinfo.setVersion(version);
			appinfo.setIschecked(false);
			// 判断该应用是否为系统应用
			if ((pki.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0
					|| (pki.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
				appinfo.setUser(false);
				sysAppinfo.add(appinfo);
			} else {
				appinfo.setUser(true);
				userAppinfo.add(appinfo);
			}
			allAppinfo.add(appinfo);
		}
	}

	// 获取正在运行的进程
	public void getRunProgress() {
		allRunProcess = new ArrayList<RunningAppProcessInfo>();
		userRunProcess = new ArrayList<AppInfornation>();
		systemRunProcess = new ArrayList<AppInfornation>();
		allRunProcess = activityManager.getRunningAppProcesses();

//		getAppInfo();

		for (int i = 0; i < allRunProcess.size(); i++) {
			RunningAppProcessInfo progress = allRunProcess.get(i);

			int[] pid = { progress.pid };
			MemoryInfo[] memoryInfos = activityManager
					.getProcessMemoryInfo(pid);

			int d = memoryInfos[0].dalvikPrivateDirty;
			String mem = Formatter.formatFileSize(context, d);

			for (int j = 0; j < allAppinfo.size(); j++) {
				// 判断正在运行的线程是系统线程还是用户线程
				// 直接判断是不是用户进程，如果不是就为系统
				
				if (progress.processName.equals(allAppinfo.get(j)
						.getApppackage())) {
					allAppinfo.get(j).setRunMemory(mem);
					if (allAppinfo.get(j).isUser()) {
						// 直接把app的信息获取过来，或者可以建一个RunningAppProcessInfo的集合，接收progress；
						
						userRunProcess.add(allAppinfo.get(j));
					} else {
//						allAppinfo.get(j).setRunMemory(mem);
						systemRunProcess.add(allAppinfo.get(j));
					}

				}
			}

		}
	}

	public List<AppInfornation> getUserRunProcess() {
		return userRunProcess;
	}

	public void setUserRunProcess(List<AppInfornation> userRunProcess) {
		this.userRunProcess = userRunProcess;
	}

	public List<AppInfornation> getSystemRunProcess() {
		return systemRunProcess;
	}

	public void setSystemRunProcess(List<AppInfornation> systemRunProcess) {
		this.systemRunProcess = systemRunProcess;
	}

	public List<PackageInfo> getListApp() {
		return listApp;
	}

	public void setListApp(List<PackageInfo> listApp) {
		this.listApp = listApp;
	}

	public List<AppInfornation> getUserAppinfo() {
		return userAppinfo;
	}

	public void setUserAppinfo(List<AppInfornation> userAppinfo) {
		this.userAppinfo = userAppinfo;
	}

	public List<AppInfornation> getSysAppinfo() {
		return sysAppinfo;
	}

	public void setSysAppinfo(List<AppInfornation> sysAppinfo) {
		this.sysAppinfo = sysAppinfo;
	}

	public List<AppInfornation> getAllAppinfo() {
		return allAppinfo;
	}

	public void setAllAppinfo(List<AppInfornation> allAppinfo) {
		this.allAppinfo = allAppinfo;
	}

}
