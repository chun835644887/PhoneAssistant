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
	// ���̼���
	List<RunningAppProcessInfo> allRunProcess;
	List<AppInfornation> userRunProcess;
	List<AppInfornation> systemRunProcess;
	List<AppInfornation> allProcess = new ArrayList<AppInfornation>();;

	public AppPackageManage(Context context) {
		this.context = context;
		// ֱ�ӻ�ȡһ����������
		packagemanager = context.getPackageManager();
		// ����Ѵ��ڵ�Ӧ�ã�������һ������
		listApp = packagemanager
				.getInstalledPackages(PackageManager.GET_ACTIVITIES);
		// ͨ�����񷽷���ȡһ��activityManager����
		activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		// getAppInfo();
		// getRunProgress();
	}

	public void getAppInfo() {

		// ������������
		// һ������ϵͳ��app��һ�������û���app��һ���������е�app
		for (int i = 0; i < listApp.size(); i++) {
			// ����һ��PackageInfo���ձ������ϵ�Ԫ��
			PackageInfo pki = listApp.get(i);
			String apppackage = pki.packageName;
			String version = pki.versionName;
			Drawable drawable = pki.applicationInfo.loadIcon(packagemanager);
			String lable = pki.applicationInfo.loadLabel(packagemanager)
					.toString();
			// ����һ�����󣬰�app����Ϣ�����ڶ����������
			AppInfornation appinfo = new AppInfornation();
			appinfo.setApppackage(apppackage);
			appinfo.setDrawable(drawable);
			appinfo.setLable(lable);
			appinfo.setVersion(version);
			appinfo.setIschecked(false);
			// �жϸ�Ӧ���Ƿ�ΪϵͳӦ��
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

	// ��ȡ�������еĽ���
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
				// �ж��������е��߳���ϵͳ�̻߳����û��߳�
				// ֱ���ж��ǲ����û����̣�������Ǿ�Ϊϵͳ
				
				if (progress.processName.equals(allAppinfo.get(j)
						.getApppackage())) {
					allAppinfo.get(j).setRunMemory(mem);
					if (allAppinfo.get(j).isUser()) {
						// ֱ�Ӱ�app����Ϣ��ȡ���������߿��Խ�һ��RunningAppProcessInfo�ļ��ϣ�����progress��
						
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
