package com.example.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.text.format.Formatter;
import android.util.Log;

import com.example.database.DBHelp;
import com.example.database.ITable;
import com.example.entity.GarbageCleanInfo;
import com.example.entity.TableColumnInfo;

public class DBMyTools {

	Context context;
	DBHelp dbHelp;
	SQLiteDatabase db;
	AssetManager assetManager;
	Cursor query;
	SQLiteDatabase db1;
	PackageManager packageManager;

	public DBMyTools(Context context) {
		this.context = context;
		dbHelp = new DBHelp(context);
		String path = "data" + File.separator + "data" + File.separator
				+ context.getPackageName() + File.separator + "databases"
				+ File.separator + "clearpath.db";
		db = dbHelp.getReadableDatabase();
		db1 = SQLiteDatabase.openOrCreateDatabase(new File(path), null);
		assetManager = context.getAssets();
	}

	// �����е����ݿ�������ӵ��Լ������ݿ���
	public void readDB() {
		// �������������
		InputStream is = null;
		OutputStream os = null;
		try {
			// ͨ��assetManager���÷����������ļ�����һ��������
			is = assetManager.open("db" + File.separator + "commonnum.db");
			// д���ļ���λ��
			os = new FileOutputStream("data" + File.separator + "data"
					+ File.separator + context.getPackageName()
					+ File.separator + "databases" + File.separator
					+ "commonnum.db");
			int length = 0;
			byte[] b = new byte[1024];
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		InputStream isClean = null;
		OutputStream osClean = null;
		try {
			// ��ȡ���ݿ��ļ�
			isClean = assetManager.open("db" + File.separator + "clearpath.db");
			File file = new File("data" + File.separator + "data"
					+ File.separator + context.getPackageName()
					+ File.separator + "databases");
			if (!file.isDirectory()) {
				file.mkdirs();
			}
			osClean = new FileOutputStream("data" + File.separator + "data"
					+ File.separator + context.getPackageName()
					+ File.separator + "databases" + File.separator
					+ "clearpath.db");
			int length1 = 0;
			byte[] bCle = new byte[1024];
			while ((length1 = isClean.read(bCle)) != -1) {
				osClean.write(bCle, 0, length1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (isClean != null) {
					isClean.close();
				}
				if (osClean != null) {
					osClean.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * ͨ���α�������ݿ�������
	 */
	public void query() {

		Cursor query = db1.query("softdetail", null, null, null, null, null,
				null);
		while (query.moveToNext()) {
			String string = query.getString(query
					.getColumnIndex("softChinesename"));
			String string2 = query.getString(query.getColumnIndex("_id"));
		}
		db1.close();
	}

	public List<TableColumnInfo> getInfo(String table) {
		// ����һ�ű�
		Cursor query = db.query(table, null, null, null, null, null, null);
		List<TableColumnInfo> list = new ArrayList<TableColumnInfo>();
		// �������ϵ�ÿһ����Ϣ
		while (query.moveToNext()) {
			// ��һ������ȥ���ձ��е���Ϣ
			TableColumnInfo tableColumnInfo = new TableColumnInfo();
			tableColumnInfo.setName(query.getString(query
					.getColumnIndex(ITable.NAME)));
			tableColumnInfo.setNumber(query.getString(query
					.getColumnIndex(ITable.NUMBER)));
			// ��ӵ�������
			list.add(tableColumnInfo);
		}

		return list;
	}

	//
	public List<GarbageCleanInfo> getCleanApp(String table) {
		Cursor query1 = db1.query(table, null, null, null, null, null, null);
		List<GarbageCleanInfo> list = new ArrayList<GarbageCleanInfo>();
		while (query1.moveToNext()) {
			File file = new File(Environment.getExternalStorageDirectory()
					.getPath()
					+ query1.getString(query1.getColumnIndex(ITable.FILE_PATH)));
			if (file.isDirectory()) {
				GarbageCleanInfo garbageCleanInfo = new GarbageCleanInfo();
				garbageCleanInfo.setChineseName(query1.getString(query1
						.getColumnIndex(ITable.CHINESE_NAME)));
				garbageCleanInfo.setEnglishName(query1.getString(query1
						.getColumnIndex(ITable.ENGLISH_NAME)));
				garbageCleanInfo.setApkName(query1.getString(query1
						.getColumnIndex(ITable.APK_NAME)));
				garbageCleanInfo.setFilePath(query1.getString(query1
						.getColumnIndex(ITable.FILE_PATH)));

				list.add(garbageCleanInfo);
			}
		}
		db1.close();
		return list;
	}

	double fileLength = 0;

	// ��ȡ����·���µ�ÿһ���ļ��Ĵ�С
	public void getGarbage(File file) {

		File[] file1 = file.listFiles();
		for (File file2 : file1) {
			if (file2.isDirectory()) {
				getGarbage(file2);
			} else {
				fileLength += file2.length();
			}
		}
	}

	private String fileSize;

	// ��ȡͼƬ,��ȡ�����С
	public List<GarbageCleanInfo> getGarbageIm(List<GarbageCleanInfo> list1) {
		packageManager = context.getPackageManager();
		DecimalFormat df = new DecimalFormat(".##");
		for (int i = 0; i < list1.size(); i++) {
			Log.i("tag", "msg" + list1.size());
			try {
				Drawable drawable = packageManager.getApplicationIcon(list1
						.get(i).getApkName());
				list1.get(i).setDrawable(drawable);
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list1;
	}

	public List<GarbageCleanInfo> getGarbageSize(List<GarbageCleanInfo> list1) {
		double allFileLength = 0;
		for (int i = 0; i < list1.size(); i++) {
			double garbage = 0;
			File file = new File(Environment.getExternalStorageDirectory()
					.getPath() + list1.get(i).getFilePath());
			File[] listFiles = file.listFiles();
			for (File file2 : listFiles) {
				if (file2.isDirectory()) {
					getGarbage(file2);
				} else {
					garbage += file2.length();
				}
			}
			allFileLength += garbage;
//			String s = Formatter.formatFileSize(context, (long) garbage);
			list1.get(i).setMemSize(garbage);
			list1.get(i).setCheck(false);
		}
		fileSize = Formatter.formatFileSize(context, (long) allFileLength);
		return list1;
	}

	// ɾ������Ŀ¼�µ��ļ�
	public void delFile(File file) {
		File[] files = file.listFiles();
		for (File file2 : files) {
			if (file2.isDirectory()) {
				delFile(file2);
			} else {
				file2.delete();
			}
		}

	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

}
