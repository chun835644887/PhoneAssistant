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

		// MEDIA_MOUNTED SD���������أ��ж��Ƿ���sd������

		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Toast.makeText(context, "SD��������", Toast.LENGTH_SHORT).show();
		} else {
			// Environment���÷��������ڴ濨��һ���ļ�
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
		// ��ȡ�豸�Ļ�������
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
		System.out.println(Build.MODEL);// ��ȡ�ֻ������ơ��汾��
		System.out.println("�ֻ��ͺ�: " + Build.MODEL + ",\nSDK�汾:"
				+ Build.VERSION.RELEASE);
		android.app.ActivityManager.MemoryInfo info = new android.app.ActivityManager.MemoryInfo();
		activityManager.getMemoryInfo(info);
		freeMemory = info.availMem;// ��ȡ��ǰ�Ŀ����ڴ�
		// availMemת��string���͵�
		String formatFileSize = Formatter.formatFileSize(context, (long) freeMemory);
		// System.out.println(formatFileSize);
		BufferedReader bfr = null;
		try {
			// ��ȡ�������ڴ�
			bfr = new BufferedReader(new FileReader(new File("/proc/meminfo")));
			// ��ȡ��һ��
			String s = bfr.readLine();
			// ��ȡ�ַ������ַ������֣�
			String[] split = s.split("\\s+");
			// System.out.println(split[1]);
			allRunMem = Long.parseLong(split[1]);
			allRunMemory=Formatter.formatFileSize(context, (long) (allRunMem*1024));
			String ss=Formatter.formatFileSize(context, (long) freeMemory);
			percentage =(allRunMem-freeMemory/1024)/allRunMem;
			
			Log.i("ttt",percentage+"percentage�ٷֱ�yyyyyyy"+allRunMemory+"QQQQQ"+ss);
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
	
//��ȡcpu������
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
//��ȡcpu�ĺ���
	public int getCpuCount() {
		// cpu�����ظ�·����
		File file = new File("/sys/devices/system/cpu");
		// ���ļ����������˵�����Ҫ���ļ�
		// ÿһ��cpu����Ӧһ���ļ��У��ļ�������cpu+����
		File[] files = file.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				if (pathname.isDirectory()) {
					// ͨ��һ��������ʽѡ����Ҫ���ļ���
					if (Pattern.matches("cpu[0-9]", pathname.getName())) {
						// ����ļ������ַ���Ҫ�󣬾ͷ��أ����Ѹ��ļ�����ӵ�������
						return true;
					}
				}
				return false;
			}
		});
		return files.length;
	}
	


	
//��ȡ������������
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
	                s="��";
	            } else {
	                bool = true;
	                s="��";
	            }
	        } catch (Exception e) {

	        } 
	        return s;
	    }
	
	//��ȡ��ǰ��ص���
//	Intent intent = new Intent();
//	intent.setAction("aa.dd.gg");
//	MyBroadCast3 myBroadCast3 = new MyBroadCast3();
//	IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
//	registerReceiver(myBroadCast3, filter);
//	// ���͹㲥
//	// sendBroadcast(intent);
//	// sendOrderedBroadcast(intent, null);
//}
//
//class MyBroadCast3 extends BroadcastReceiver {
//
//	@Override
//	public void onReceive(Context context, Intent intent) {
//		// TODO Auto-generated method stub
////		System.out.println("----�㲥3----");
//		int level = intent.getIntExtra("level", 1000);
//		System.out.println("----------------------------------"+level);
//		int temperature = intent.getIntExtra("temperature", 0);
//		System.out.println("----------------------------------"+temperature * 0.1f+"��");
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
