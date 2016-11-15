package com.example.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import android.app.ActivityManager;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

public class GetMemory {
	Context context;

	ActivityManager activityManager;

	public GetMemory(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);

	}

	public double allMemory;
	public double free;

	public void memorySize() {

		externalStorageDirectory();
		dataDirectory();
		downloadCacheDirectory();
		rootDirectory();
		getOut();

		outMemory = outMemory;
		allMemory = esdBlockCount * esdSize + ddAllBlock * ddSize + dcdAllBlock
				* dcdSize + rdBlockCount * rdSize;
		free = esdFreeCount * esdSize + ddFreeCount * ddSize + dcdFreeCount
				* dcdSize + rdFreeCount * rdSize;
	}

	public double esdSize;
	public double esdFreeCount;
	public double esdBlockCount;

	public void externalStorageDirectory() {

		// MEDIA_MOUNTED SD卡正常挂载，判断是否有sd卡存在

		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Toast.makeText(context, "SD卡不存在", Toast.LENGTH_SHORT).show();
		} else {
			// Environment调用方法返回内存卡的一个文件
			File file = Environment.getExternalStorageDirectory();
			StatFs statfs = new StatFs(file.getAbsolutePath());
			esdBlockCount = statfs.getBlockCount();
			esdSize = statfs.getBlockSize();
			esdFreeCount = statfs.getFreeBlocks();

		}
	}

	public double ddFreeCount;
	public double ddSize;
	public double ddAllBlock;

	public void dataDirectory() {
		File file = Environment.getDataDirectory();
		StatFs statfs = new StatFs(file.getAbsolutePath());
		ddFreeCount = statfs.getFreeBlocks();
		ddSize = statfs.getBlockSize();
		ddAllBlock = statfs.getBlockCount();

	}

	public double dcdFreeCount;
	public double dcdSize;
	public double dcdAllBlock;

	public void downloadCacheDirectory() {
		File file = Environment.getDownloadCacheDirectory();
		StatFs statfs = new StatFs(file.getAbsolutePath());
		dcdFreeCount = statfs.getFreeBlocks();
		dcdSize = statfs.getBlockSize();
		dcdAllBlock = statfs.getBlockCount();

	}

	public double rdFreeCount;
	public double rdSize;
	public double rdBlockCount;

	public void rootDirectory() {
		File file = Environment.getRootDirectory();
		StatFs statfs = new StatFs(file.getAbsolutePath());
		rdBlockCount = statfs.getFreeBlocks();
		rdSize = statfs.getBlockSize();
		rdFreeCount = statfs.getFreeBlocks();
	}

	public double outMemory;
	public double avaiOutMemory;

	public void getOut() {
		// storage/extSdCard
		// 获取设备的环境变量
		Map<String, String> getenv = System.getenv();
		System.out.println(getenv);
		if (getenv.containsKey("SECONDARY_STORAGE")) {
			String str = getenv.get("SECONDARY_STORAGE");
			StatFs stat = new StatFs(str);
			outMemory = (double) stat.getBlockCount() * stat.getBlockSize();
			avaiOutMemory = outMemory
					- (stat.getFreeBlocks() * stat.getBlockSize());

			DecimalFormat df = new DecimalFormat(".###");
			String s = df.format(outMemory);
		}

	}
	private String allRunMemory;
	private double freeMemory;
	private double allRunMem;
	public static double percentage;
	public static DecimalFormat df = new DecimalFormat(".#");
	public void getPhoneInfo() {
		System.out.println(Build.MODEL);// 获取手机的名称、版本号
		System.out.println("手机型号: " + Build.MODEL + ",\nSDK版本:"
				+ Build.VERSION.RELEASE);
		android.app.ActivityManager.MemoryInfo info = new android.app.ActivityManager.MemoryInfo();
		activityManager.getMemoryInfo(info);
		freeMemory = info.availMem;// 获取当前的可用内存
		// availMem转成string类型的
		String formatFileSize = Formatter.formatFileSize(context, (long) freeMemory);
		// System.out.println(formatFileSize);
		BufferedReader bfr = null;
		try {
			// 读取的是总内存
			bfr = new BufferedReader(new FileReader(new File("/proc/meminfo")));
			// 读取第一行
			String s = bfr.readLine();
			// 截取字符串（字符串数字）
			String[] split = s.split("\\s+");
			// System.out.println(split[1]);
			allRunMem = Long.parseLong(split[1]);
			allRunMemory=Formatter.formatFileSize(context, (long) (allRunMem*1024));
			String ss=Formatter.formatFileSize(context, (long) freeMemory);
			percentage =(allRunMem-freeMemory/1024)/allRunMem;
			
			Log.i("ttt",percentage+"percentage百分比yyyyyyy"+allRunMemory+"QQQQQ"+ss);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bfr != null) {
				try {
					bfr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void getScreen() {
		DisplayMetrics dm=new DisplayMetrics();
		String display = dm.widthPixels + "*" + dm.heightPixels;
	}
	
//获取cpu的名字
	public String getCpuName() {
		BufferedReader br = null;
		String cpuInfo = null;
		try {
			br = new BufferedReader(new FileReader(new File("/proc/cpuinfo")));
			cpuInfo = br.readLine();
			String[] split = cpuInfo.split(":", 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cpuInfo;
	}
//获取cpu的核数
	public int getCpuCount() {
		// cpu核数载该路径下
		File file = new File("/sys/devices/system/cpu");
		// 用文件过滤器过滤掉不需要的文件
		// 每一个cpu都对应一个文件夹，文件夹名：cpu+数字
		File[] files = file.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				if (pathname.isDirectory()) {
					// 通过一个正则表达式选择需要的文件夹
					if (Pattern.matches("cpu[0-9]", pathname.getName())) {
						// 如果文件夹名字符合要求，就返回，并把该文件夹添加到集合中
						return true;
					}
				}
				return false;
			}
		});
		return files.length;
	}
	


	
//获取相机的最大像素
	public String getCamer() {
		double width = 0;
		double height = 0;
		Camera camera = Camera.open();
		Parameters parameters = camera.getParameters();
		List<Size> list = parameters.getSupportedPictureSizes();
		for (int i = 0; i < list.size(); i++) {
			// Log.i("tag",list.get(i)+"");
			if (list.get(i).width > width) {
				width = list.get(i).width;
			}
			if (list.get(i).height > height) {
				height = list.get(i).height;
			}
			// Collections.max(list);
			camera.release();
		}
		return (int)width+"*"+(int)height;
	}
	
	 public String isRoot(){
	        boolean bool = false;
String s=null;
	        try{
	            if ((!new File("/system/bin/su").exists()) && (!new File("/system/xbin/su").exists())){
	                bool = false;
	                s="否";
	            } else {
	                bool = true;
	                s="是";
	            }
	        } catch (Exception e) {

	        } 
	        return s;
	    }
	
	//获取当前电池电量
//	Intent intent = new Intent();
//	intent.setAction("aa.dd.gg");
//	MyBroadCast3 myBroadCast3 = new MyBroadCast3();
//	IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
//	registerReceiver(myBroadCast3, filter);
//	// 发送广播
//	// sendBroadcast(intent);
//	// sendOrderedBroadcast(intent, null);
//}
//
//class MyBroadCast3 extends BroadcastReceiver {
//
//	@Override
//	public void onReceive(Context context, Intent intent) {
//		// TODO Auto-generated method stub
////		System.out.println("----广播3----");
//		int level = intent.getIntExtra("level", 1000);
//		System.out.println("----------------------------------"+level);
//		int temperature = intent.getIntExtra("temperature", 0);
//		System.out.println("----------------------------------"+temperature * 0.1f+"度");
//	}
//
//}


	public String getAllRunMemory() {
		return allRunMemory;
	}

	public void setAllRunMemory(String allRunMemory) {
		this.allRunMemory = allRunMemory;
	}

	public double getFreeMemory() {
		return freeMemory;
	}

	public void setFreeMemory(long freeMemory) {
		this.freeMemory = freeMemory;
	}

	public double getAllRunMem() {
		return allRunMem;
	}

	public void setAllRunMem(long allRunMem) {
		this.allRunMem = allRunMem;
	}
}
