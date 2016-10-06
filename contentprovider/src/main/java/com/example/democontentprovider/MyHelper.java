package com.example.democontentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyHelper extends SQLiteOpenHelper{


	public MyHelper(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

   //回调方法。当第一次操作数据库，应用中没有数据库，初始化数据库，第二次不会被调用
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.beginTransaction();//db 传进来，以封装
		//事物开发人员自定义，不可被打断，原子操作
		db.execSQL(CREATE_TABLE);//创建表
		
//		ContentValues values = new ContentValues();
//		values.put(CountryCode.COUNTRY, "中国");
//		values.put(CountryCode.CODE, 86);
//		db.insertOrThrow(CountryCode.TB_NAME, CountryCode.ID, values);
		db.setTransactionSuccessful();
		db.endTransaction();
		
	}
//数据库更新
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.beginTransaction();
		Log.e("onupgrade", "onupgrade");
		db.execSQL(DROP_TABLE);
		db.execSQL(CREATE_TABLE);
		
		db.setTransactionSuccessful();
		db.endTransaction();
		
		onCreate(db);
		
	}
	private static final String[] TABLE_COLUMNS = {CountryCode.ID, CountryCode.COUNTRY, CountryCode.CODE};
	//表
	private static final String CREATE_TABLE = 
			"CREATE TABLE IF NOT EXISTS " +  CountryCode.TB_NAME + " ("
			+ TABLE_COLUMNS[0] + " INTEGER PRIMARY KEY,"	//主键
			+ TABLE_COLUMNS[1] + " VARCHAR(20),"	//国家
			+ TABLE_COLUMNS[2] + " VARCHAR(20)"	//国码
			+ ")";
	//删除表
	private static final String DROP_TABLE=
			"DROP TABLE IF EXISTS " + CountryCode.TB_NAME;

}
